package engine.common.render

import engine.common.render.window.Window
import engine.common.render.window.WindowProps

interface Render {
    var mainWindow: Window?

    fun createWindow(props: WindowProps = WindowProps()): Window
}
