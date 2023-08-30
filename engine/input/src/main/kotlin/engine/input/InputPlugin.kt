package engine.input

import engine.common.gEngine
import engine.common.log.log
import engine.common.plugin.EnginePlugin
import engine.common.plugin.INPUT_PLUGIN_ORDER
import engine.common.plugin.RegisterPlugin
import engine.input.context.InputImpl

private const val PLUGIN_NAME = "Input"

@RegisterPlugin(order = INPUT_PLUGIN_ORDER)
class InputPlugin : EnginePlugin {
    override val name: String
        get() = PLUGIN_NAME

    override fun onInitialize() {
        log.info("Initializing Input Plugin")
        gEngine.input = InputImpl().also {
            it.registerGlfwCallbacks()
        }
    }

    override fun onShutdown() {
        (gEngine.input as InputImpl).unregisterGflwCallbacks()
        log.debug("onShutdown")
    }
}
