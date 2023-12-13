package engine.render.context.imgui

import engine.common.log.log
import engine.common.render.window.Window
import imgui.ImGui
import imgui.gl3.ImGuiImplGl3
import imgui.glfw.ImGuiImplGlfw

class ImGuiOpenGlWrapper(
        private val window: Window,
        private val enableGui: Boolean = true
) : Window by window {
    private val imguiGlfw by lazy { ImGuiImplGlfw() }

    private val imguiGl3 by lazy { ImGuiImplGl3() }

    init {
        if (!enableGui) {
            log.info("Skipping initialize ImGui")
        }

        log.info("Initializing ImGui for OpenGL")

        ImGui.createContext()
        imguiGlfw.init(window.id, true)
        imguiGl3.init(null)
    }

    override fun shutdown() {
        if (!enableGui) {
            window.shutdown()
            return
        }

        imguiGl3.dispose()
        imguiGlfw.dispose()
        ImGui.destroyContext()

        window.shutdown()
    }
}
