package engine.input.context

import engine.common.event.*
import engine.common.getRender
import engine.common.input.ApplicationEventListener
import engine.common.input.Input
import engine.common.input.InputEventListener
import engine.common.log.log
import engine.input.exception.InputRuntimeException
import org.lwjgl.glfw.Callbacks
import org.lwjgl.glfw.GLFW

internal class BaseInput : Input {
    private val anyInputEventListeners = mutableSetOf<InputEventListener>()

    private val applicationEventListener = mutableSetOf<ApplicationEventListener>()

    override fun registerAnyInputEventListener(clazz: InputEventListener) {
        anyInputEventListeners.add(clazz)
    }

    override fun removeAnyInputEventListener(clazz: InputEventListener) {
        anyInputEventListeners.remove(clazz)
    }

    override fun registerApplicationEventListener(clazz: ApplicationEventListener) {
        applicationEventListener.add(clazz)
    }

    override fun removeApplicationEventListener(clazz: ApplicationEventListener) {
        applicationEventListener.remove(clazz)
    }

    fun registerGlfwCallbacks() {
        log.debug("Registering Glfw Callbacks")
        val windowId = getRender().mainWindow?.id ?: throw InputRuntimeException(
            "mainWindow is null to register Glfw Callbacks"
        )

        // This is application event
        GLFW.glfwSetWindowCloseCallback(windowId) {
            notifyAllApplicationEventListeners(WindowClosedEvent)
        }

        GLFW.glfwSetKeyCallback(windowId) { _: Long, key: Int, _: Int, action: Int, _: Int ->
            when (action) {
                GLFW.GLFW_PRESS -> notifyAllAnyInputEventListeners(KeyPressedEvent(key))
                GLFW.GLFW_RELEASE -> notifyAllAnyInputEventListeners(KeyReleasedEvent(key))
                GLFW.GLFW_REPEAT -> notifyAllAnyInputEventListeners(KeyPressedEvent(key, isLongPress = true))
            }
        }

        GLFW.glfwSetMouseButtonCallback(windowId) { _: Long, button: Int, action: Int, _: Int ->
            when (action) {
                GLFW.GLFW_PRESS -> notifyAllAnyInputEventListeners(MouseButtonPressedEvent(button))
                GLFW.GLFW_RELEASE -> notifyAllAnyInputEventListeners(MouseButtonReleasedEvent(button))
            }
        }

        GLFW.glfwSetScrollCallback(windowId) { _: Long, xOffset: Double, yOffset: Double ->
            notifyAllAnyInputEventListeners(MouseScrolledEvent(xOffset, yOffset))
        }

        GLFW.glfwSetCursorPosCallback(windowId) { _: Long, x: Double, y: Double ->
            notifyAllAnyInputEventListeners(MouseMovedEvent(x, y))
        }
    }

    fun unregisterGflwCallbacks() {
        log.debug("Unregistering Glfw Callbacks")
        val windowId = getRender().mainWindow?.id ?: throw InputRuntimeException(
            "mainWindow is null to unregister Glfw Callbacks"
        )

        Callbacks.glfwFreeCallbacks(windowId)
    }

    // TODO -> Might be slow, maybe implement some async bus
    private fun notifyAllAnyInputEventListeners(event: Event) =
        anyInputEventListeners.forEach { it.onAnyInputEvent(event) }

    private fun notifyAllApplicationEventListeners(event: Event) =
        applicationEventListener.forEach { it.onApplicationEvent(event) }
}
