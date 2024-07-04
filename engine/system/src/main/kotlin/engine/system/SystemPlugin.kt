package engine.system

import engine.common.SubSystem
import engine.common.log.log
import engine.common.plugin.EnginePlugin
import engine.common.plugin.RegisterPlugin
import engine.common.plugin.SYSTEM_PLUGIN_ORDER
import engine.system.context.EngineSystem

private const val PLUGIN_NAME = "System"

@RegisterPlugin(order = SYSTEM_PLUGIN_ORDER)
class SystemPlugin : EnginePlugin {
    private lateinit var systemSubSystem: EngineSystem

    override val name: String
        get() = PLUGIN_NAME

    override fun onInitialize() {
        log.info("Initializing System Plugin")
        // Initialize access
        systemSubSystem = SubSystem
            .register { EngineSystem() }
            .apply { registerVariables() }
            .also(EngineSystem::initializeGlfw)
    }

    override fun onShutdown() {
        systemSubSystem.shutdownGlfw()
    }
}
