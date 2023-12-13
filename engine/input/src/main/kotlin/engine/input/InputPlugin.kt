package engine.input

import engine.common.SubSystem
import engine.common.log.log
import engine.common.plugin.EnginePlugin
import engine.common.plugin.INPUT_PLUGIN_ORDER
import engine.common.plugin.RegisterPlugin
import engine.input.context.BaseInput

private const val PLUGIN_NAME = "Input"

@RegisterPlugin(order = INPUT_PLUGIN_ORDER)
class InputPlugin : EnginePlugin {
    private lateinit var inputSystem: BaseInput

    override val name: String
        get() = PLUGIN_NAME

    override fun onInitialize() {
        log.info("Initializing Input Plugin")
        inputSystem = SubSystem.register { BaseInput() }.also {
            it.registerGlfwCallbacks()
        }
    }

    override fun onShutdown() {
        inputSystem.unregisterGflwCallbacks()
        log.debug("onShutdown")
    }
}
