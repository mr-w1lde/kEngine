package engine.render.context.glfw

import engine.common.log.log
import engine.common.render.window.Window
import engine.common.render.window.WindowProps
import org.lwjgl.glfw.GLFW
import org.lwjgl.system.MemoryUtil

class GlfwWindow private constructor(
    override val id: Long,
    override val width: Int,
    override val height: Int,
) : Window {
    private var isVSync = false

    override fun onUpdate() {
        GLFW.glfwPollEvents()
        GLFW.glfwSwapBuffers(id)
    }

    override fun shutdown() {
        GLFW.glfwDestroyWindow(id)
    }

    override fun setVSync(enable: Boolean) {
        if (enable) {
            GLFW.glfwSwapInterval(1)
        } else {
            GLFW.glfwSwapInterval(0)
        }

        isVSync = enable
    }

    override fun isVSync(): Boolean = isVSync

    companion object {
        fun create(props: WindowProps): Window {
            log.debug("Creating Window: (${props.title}) (${props.width}x${props.height})")

            // Configure GLFW
            GLFW.glfwDefaultWindowHints() // optional, the current window hints are already the default
            GLFW.glfwWindowHint(GLFW.GLFW_VISIBLE, GLFW.GLFW_FALSE) // the window will stay hidden after creation
            GLFW.glfwWindowHint(GLFW.GLFW_RESIZABLE, GLFW.GLFW_FALSE) // the window will be resizable

            val windowId =
                GLFW.glfwCreateWindow(props.width, props.height, props.title, MemoryUtil.NULL, MemoryUtil.NULL)

            assert(windowId == MemoryUtil.NULL) { throw RuntimeException("Failed to create the GLFW window") }

            return GlfwWindow(
                id = windowId,
                width = props.width,
                height = props.height
            ).also { window ->
                GLFW.glfwMakeContextCurrent(window.id)
                GLFW.glfwShowWindow(window.id)
                window.setVSync(false)
            }
        }
    }
}
