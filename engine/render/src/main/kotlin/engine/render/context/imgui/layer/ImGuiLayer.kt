package engine.render.context.imgui.layer

import engine.common.event.Event
import engine.common.render.layer.Layer
import engine.common.render.window.Window
import imgui.ImGui
import imgui.flag.ImGuiBackendFlags
import imgui.flag.ImGuiConfigFlags
import imgui.gl3.ImGuiImplGl3
import imgui.glfw.ImGuiImplGlfw
import imgui.type.ImBoolean
import org.lwjgl.glfw.GLFW
import org.lwjgl.glfw.GLFW.glfwGetTime

private const val GLSL_VERSION = "#version 130"

class ImGuiLayer(
    private val windowRef: Window,
    private var mTime: Float = 0.0f,
) : Layer("ImGuiLayer") {
  private val imguiGlfw = ImGuiImplGlfw()

  private val imguiGl3 = ImGuiImplGl3()

  override fun onAttach() {
    ImGui.createContext()
    ImGui.styleColorsDark()

    ImGui.getIO().apply {
      //addConfigFlags(ImGuiConfigFlags.ViewportsEnable)
      backendFlags = ImGuiBackendFlags.HasSetMousePos or ImGuiBackendFlags.HasMouseCursors
    }

    imguiGlfw.init(windowRef.id, true)
    imguiGl3.init(GLSL_VERSION)
  }

  override fun onDetach() {
    imguiGl3.dispose()
    imguiGlfw.dispose()

    ImGui.destroyContext()
  }

  override fun onUpdate() {
    val io = ImGui.getIO()
    io.setDisplaySize(windowRef.width.toFloat(), windowRef.height.toFloat())

    val time = glfwGetTime().toFloat()
    ImGui.getIO().deltaTime = if (mTime > 0.0f) (time - mTime) else (1.0f / 60.0f)
    mTime = time

    imguiGlfw.newFrame()
    ImGui.newFrame()

    ImGui.showDemoWindow(ImBoolean(true))

    ImGui.render()
    imguiGl3.renderDrawData(ImGui.getDrawData())

    if (ImGui.getIO().hasConfigFlags(ImGuiConfigFlags.ViewportsEnable)) {
      val backupWindowPtr = GLFW.glfwGetCurrentContext()
      ImGui.updatePlatformWindows()
      ImGui.renderPlatformWindowsDefault()
      GLFW.glfwMakeContextCurrent(backupWindowPtr)
    }
  }
}
