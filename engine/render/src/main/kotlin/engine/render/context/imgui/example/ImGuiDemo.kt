package engine.render.context.imgui.example

import imgui.ImGui

class ImGuiDemo {
    private var showText = false

    fun imgui() {
        ImGui.begin("Cool Window")

        if (ImGui.button("I am a button")) {
            showText = true
        }

        if (showText) {
            ImGui.text("You clicked a button")
            ImGui.sameLine()
            if (ImGui.button("Stop showing text")) {
                showText = false
            }
        }

        ImGui.end()
    }
}
