package engine.render.context.imgui.layer

import engine.common.event.*
import engine.common.render.layer.Layer
import engine.common.render.window.Window
import imgui.ImGui
import imgui.flag.ImGuiBackendFlags
import imgui.flag.ImGuiConfigFlags
import imgui.flag.ImGuiKey
import imgui.gl3.ImGuiImplGl3
import imgui.type.ImBoolean
import org.lwjgl.glfw.GLFW.*

private const val GLSL_VERSION = "#version 130"

class ImGuiLayer(
    private val windowRef: Window,
    private var mTime: Float = 0.0f,
) : Layer("ImGuiLayer") {
  // private val imguiGlfw = ImGuiImplGlfw()

  private val imguiGl3 = ImGuiImplGl3()

  override fun onAttach() {
    ImGui.createContext()
    ImGui.styleColorsDark()

    ImGui.getIO().apply {
      addConfigFlags(ImGuiConfigFlags.NavEnableKeyboard or ImGuiConfigFlags.DockingEnable)
      backendFlags = ImGuiBackendFlags.HasSetMousePos or ImGuiBackendFlags.HasMouseCursors

      setKeyMap(ImGuiKey.Space, GLFW_KEY_SPACE)
    }

    ImGui.init()

    // imguiGlfw.init(windowRef.id, true)
    imguiGl3.init(GLSL_VERSION)
  }

  override fun onDetach() {
    imguiGl3.dispose()
    // imguiGlfw.dispose()

    ImGui.destroyContext()
  }

  override fun onUpdate() {
    val io = ImGui.getIO()
    io.setDisplaySize(windowRef.width.toFloat(), windowRef.height.toFloat())

    val time = glfwGetTime().toFloat()
    ImGui.getIO().deltaTime = if (mTime > 0.0f) (time - mTime) else (1.0f / 60.0f)
    mTime = time

    // imguiGlfw.newFrame()
    ImGui.newFrame()

    ImGui.showDemoWindow(ImBoolean(true))

    ImGui.render()
    imguiGl3.renderDrawData(ImGui.getDrawData())

    if (ImGui.getIO().hasConfigFlags(ImGuiConfigFlags.ViewportsEnable)) {
      val backupWindowPtr = glfwGetCurrentContext()
      ImGui.updatePlatformWindows()
      ImGui.renderPlatformWindowsDefault()
      glfwMakeContextCurrent(backupWindowPtr)
    }
  }

  override fun onEvent(event: Event) {
    EventDispatcher<MouseButtonPressedEvent>(event, ::onMouseButtonPressedEvent)
    EventDispatcher<MouseButtonReleasedEvent>(event, ::onMouseButtonReleasedEvent)
    EventDispatcher<MouseMovedEvent>(event, ::onMouseMoveEvent)
    EventDispatcher<MouseScrolledEvent>(event, ::onMouseScrollEvent)
    EventDispatcher<KeyPressedEvent>(event, ::onKeyPressedEvent)
    EventDispatcher<KeyReleasedEvent>(event, ::onKeyReleasedEvent)
    EventDispatcher<KeyTypedEvent>(event, ::onKeyTypedEvent)
  }

  private fun onMouseButtonPressedEvent(event: MouseButtonPressedEvent): Boolean {
    ImGui.getIO().setMouseDown(event.button, true)

    return false
  }

  private fun onMouseButtonReleasedEvent(event: MouseButtonReleasedEvent): Boolean {
    ImGui.getIO().setMouseDown(event.button, false)

    return false
  }

  private fun onMouseMoveEvent(event: MouseMovedEvent): Boolean {
    ImGui.getIO().setMousePos(event.x.toFloat(), event.y.toFloat())

    return false
  }

  private fun onMouseScrollEvent(event: MouseScrolledEvent): Boolean {
    ImGui.getIO().apply {
      mouseWheelH += event.xOffset.toFloat()
      mouseWheel += event.yOffset.toFloat()
    }

    return false
  }

  private fun onKeyPressedEvent(event: KeyPressedEvent): Boolean {
    ImGui.getIO().apply {
      setKeysDown(event.keyCode, true)

      keyCtrl = getKeysDown(GLFW_KEY_LEFT_CONTROL) || getKeysDown(GLFW_KEY_RIGHT_CONTROL)
      keyShift = getKeysDown(GLFW_KEY_LEFT_SHIFT) || getKeysDown(GLFW_KEY_RIGHT_SHIFT)
      keyAlt = getKeysDown(GLFW_KEY_LEFT_SHIFT) || getKeysDown(GLFW_KEY_RIGHT_SHIFT)
      keySuper = getKeysDown(GLFW_KEY_LEFT_SUPER) || getKeysDown(GLFW_KEY_RIGHT_SUPER)
    }

    return false
  }

  private fun onKeyReleasedEvent(event: KeyReleasedEvent): Boolean {
    ImGui.getIO().setKeysDown(event.keyCode, false)

    return false
  }

  private fun onKeyTypedEvent(event: KeyTypedEvent): Boolean {
    if (event.keyCode in 1..0xffff) {
      ImGui.getIO().addInputCharacter(event.keyCode)
    }

    return false
  }
}
