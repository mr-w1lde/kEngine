package engine.render.context

import engine.common.render.Render
import engine.common.render.layer.LayerStack
import engine.common.render.window.Window
import engine.common.render.window.WindowProps
import engine.render.context.glfw.GlfwWindow
import engine.render.context.layer.BaseLayerStack
import engine.render.exception.RenderRuntimeException
import org.lwjgl.opengl.GL11.*

internal class EngineRender : Render {
    private val layerStack = BaseLayerStack()

    override var mainWindow: Window? = null

    @Synchronized
    override fun createWindow(props: WindowProps): Window {
        if (mainWindow != null) {
            // Support now only one Window
            throw RenderRuntimeException("mainWindow already exist!")
        }

        mainWindow = GlfwWindow.create(props)
        return mainWindow!!
    }

    override fun update() {
        glClearColor(0F, 0F, 0F, 1F)
        glClear(GL_COLOR_BUFFER_BIT)

        layerStack.getLayers().forEach {
            it.onUpdate()
        }

        mainWindow!!.onUpdate()
    }

    override fun layerStack(): LayerStack = layerStack
}
