package engine.render.context

import engine.common.render.Render
import engine.common.render.window.Window
import engine.common.render.window.WindowProps
import engine.render.context.glfw.GlfwWindow
import java.lang.RuntimeException

class RenderImpl : Render {
    override var mainWindow: Window? = null

    @Synchronized
    override fun createWindow(props: WindowProps): Window {
        if (mainWindow != null) {
            // Support now only one Window
            throw RuntimeException("Window already exist!")
        }

        mainWindow = GlfwWindow.create(props)
        return mainWindow!!
    }
}
