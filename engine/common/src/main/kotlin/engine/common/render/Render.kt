package engine.common.render

import engine.common.SubSystem
import engine.common.render.window.Window
import engine.common.render.window.WindowProps

/**
 * Engine Render SubSystem
 * */
interface Render : SubSystem {
    var mainWindow: Window?

    fun createWindow(props: WindowProps = WindowProps()): Window
}
