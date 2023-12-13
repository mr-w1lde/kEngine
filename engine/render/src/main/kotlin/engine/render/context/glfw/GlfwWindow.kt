package engine.render.context.glfw

import engine.common.log.log
import engine.common.platform.isARMArchitecture
import engine.common.platform.isDarwinPlatform
import engine.common.render.window.Window
import engine.common.render.window.WindowProps
import engine.render.context.imgui.ImGuiOpenGlWrapper
import engine.render.exception.RenderRuntimeException
import org.lwjgl.glfw.GLFW.*
import org.lwjgl.opengl.GL
import org.lwjgl.system.MemoryUtil

class GlfwWindow private constructor(
    override val id: Long,
    override val width: Int,
    override val height: Int,
) : Window {
    private var isVSync = false

    override fun onUpdate() {
        glfwPollEvents()
        glfwSwapBuffers(id)
    }

    override fun shutdown() {
        glfwDestroyWindow(id)
    }

    override fun setVSync(enable: Boolean) {
        if (enable) {
            glfwSwapInterval(1)
        } else {
            glfwSwapInterval(0)
        }

        isVSync = enable
    }

    override fun isVSync(): Boolean = isVSync

    companion object {
        fun create(props: WindowProps): Window {
            log.debug("Creating Window: (${props.title}) (${props.width}x${props.height})")

            // Configure GLFW
            glfwDefaultWindowHints() // optional, the current window hints are already the default
            glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE) // the window will stay hidden after creation
            glfwWindowHint(GLFW_RESIZABLE, GLFW_FALSE) // the window will be resizable

            if (isDarwinPlatform()) {
                glfwWindowHint(GLFW_COCOA_RETINA_FRAMEBUFFER, GLFW_FALSE)
            }

            val windowId = glfwCreateWindow(props.width, props.height, props.title, MemoryUtil.NULL, MemoryUtil.NULL)

            assert(windowId == MemoryUtil.NULL) {
                throw RenderRuntimeException("Failed to create the GLFW window")
            }

            return ImGuiOpenGlWrapper(
                window = GlfwWindow(
                    id = windowId,
                    width = props.width,
                    height = props.height
                ).also { window ->
                    glfwMakeContextCurrent(window.id)
                    glfwShowWindow(window.id)
                    window.setVSync(props.vSync)

                    GL.createCapabilities() // Should we initialize it here?
                },
                enableGui = !isARMArchitecture() // ImGui JNI Version is not supported for ARM (yet)
            )
        }
    }
}
