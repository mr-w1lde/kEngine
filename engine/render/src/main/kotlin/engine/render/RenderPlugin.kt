package engine.render

import engine.common.SubSystem
import engine.common.log.log
import engine.common.platform.isARMArchitecture
import engine.common.plugin.EnginePlugin
import engine.common.plugin.RENDER_PLUGIN_ORDER
import engine.common.plugin.RegisterPlugin
import engine.common.render.layer.Layer
import engine.render.context.BaseRender
import engine.render.context.imgui.layer.ImGuiLayer

private const val PLUGIN_NAME = "Render"

@RegisterPlugin(
    order = RENDER_PLUGIN_ORDER
)
class RenderPlugin : EnginePlugin {
    private lateinit var renderSubSystem: BaseRender

    override val name: String
        get() = PLUGIN_NAME

    override fun onInitialize() {
        log.info("Initializing Render Plugin")

        // Initialize access
        renderSubSystem = SubSystem.register { BaseRender() }.also {
            it.createWindow()
        }

        //Init ImGui
        if (!isARMArchitecture()) {
            log.info("Initializing ImGuiLayer because is not ARM Architecture (not supported yet)")
            renderSubSystem.layerStack().pushLayer(ImGuiLayer(renderSubSystem.mainWindow!!))
        }
    }

    override fun onShutdown() {
        log.debug("onShutdown")

        renderSubSystem.layerStack().getLayers().forEach(Layer::onDetach)
        renderSubSystem.mainWindow?.shutdown()
    }
}
