package engine.render

import engine.common.SubSystem
import engine.common.log.log
import engine.common.plugin.EnginePlugin
import engine.common.plugin.RENDER_PLUGIN_ORDER
import engine.common.plugin.RegisterPlugin
import engine.render.context.RenderImpl

private const val PLUGIN_NAME = "Render"

@RegisterPlugin(
    order = RENDER_PLUGIN_ORDER
)
class RenderPlugin : EnginePlugin {
    private lateinit var renderSubSystem: RenderImpl

    override val name: String
        get() = PLUGIN_NAME

    override fun onInitialize() {
        log.info("Initializing Render Plugin")

        // Initialize access
        renderSubSystem = SubSystem.register { RenderImpl() }.also {
            it.createWindow()
        }
    }

    override fun onShutdown() {
        log.debug("onShutdown")
        renderSubSystem.mainWindow?.shutdown()
    }
}
