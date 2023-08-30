package engine.system

import engine.common.log.log
import engine.common.plugin.EnginePlugin
import engine.common.plugin.RegisterPlugin
import engine.common.plugin.SYSTEM_PLUGIN_ORDER
import org.lwjgl.Version
import org.lwjgl.glfw.GLFW.*
import org.lwjgl.glfw.GLFWErrorCallback

private const val PLUGIN_NAME = "System"

@RegisterPlugin(order = SYSTEM_PLUGIN_ORDER)
class SystemPlugin : EnginePlugin {
    override val name: String
        get() = PLUGIN_NAME

    override fun onInitialize() {
        log.info("Initializing System Plugin")
        log.debug("LWJGL Version ${Version.getVersion()}")

        // TODO -> Some Glfw class
        if (!glfwInit()) {
            throw IllegalStateException("Unable to initialize GLFW")
        }

        glfwSetErrorCallback { error: Int, descriptionId: Long ->
            val str = GLFWErrorCallback.getDescription(descriptionId)
            log.error("[LWJGL] error: $error, description: $str")
        }
    }

    override fun onShutdown() {
        log.debug("onShutdown")
        // TODO -> Some Glfw class
        glfwTerminate()
        glfwSetErrorCallback(null)?.free()
    }
}
