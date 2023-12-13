package engine.render.context.imgui

import engine.common.log.log
import engine.common.render.window.Window
import imgui.ImGui
import imgui.gl3.ImGuiImplGl3
import imgui.glfw.ImGuiImplGlfw

// TODO -> Figure out of something???
class ImGuiOpenGlWrapper(private val window: Window) : Window by window {
    private val imguiGlfw = ImGuiImplGlfw()
    private val imguiGl3 = ImGuiImplGl3()

    fun init() {
        log.info("Initializing ImGui for OpenGL")
        ImGui.createContext()
        //imguiGlfw.init(window.id, true)
        //imguiGl3.init(null)
    }

    override fun shutdown() {
        //imguiGl3.dispose()
        //imguiGlfw.dispose()
        //ImGui.destroyContext()
        window.shutdown()
    }
}