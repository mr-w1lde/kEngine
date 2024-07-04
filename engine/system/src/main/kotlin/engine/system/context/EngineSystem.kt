package engine.system.context

import engine.common.log.log
import engine.common.system.System
import engine.common.system.console.Console
import engine.system.context.console.EngineConsole
import engine.common.system.console.variables.render.VSyncVariable
import engine.common.system.console.variables.render.WindowXVariable
import engine.common.system.console.variables.render.WindowYVariable
import org.lwjgl.Version
import org.lwjgl.glfw.GLFW.*
import org.lwjgl.glfw.GLFWErrorCallback

class EngineSystem : System {
    override val console: Console = EngineConsole()

    fun initializeGlfw() {
        log.debug("LWJGL Version ${Version.getVersion()}")

        if (!glfwInit()) {
            throw IllegalStateException("Unable to initialize GLFW")
        }

        glfwSetErrorCallback { error: Int, descriptionId: Long ->
            val str = GLFWErrorCallback.getDescription(descriptionId)
            log.error("[LWJGL] error: $error, description: $str")
        }
    }

    fun registerVariables() {
        console.registerVariable(
            setOf(
                VSyncVariable,
                WindowXVariable,
                WindowYVariable,
            )
        )
    }

    fun shutdownGlfw() {
        log.debug("onShutdown")
        glfwTerminate()
        glfwSetErrorCallback(null)?.free()
    }
}
