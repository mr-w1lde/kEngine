package engine.system

import engine.common.log.log
import engine.common.plugin.EnginePlugin
import engine.common.plugin.RegisterPlugin
import engine.common.plugin.SYSTEM_PLUGIN_ORDER

private const val PLUGIN_NAME = "System"

@RegisterPlugin(order = SYSTEM_PLUGIN_ORDER)
class SystemPlugin : EnginePlugin {
    override val name: String
        get() = PLUGIN_NAME

    override fun onInitialize() {
        log.debug("onInitialize")
    }

    override fun onShutdown() {
        log.debug("onShutdown")
    }
}
