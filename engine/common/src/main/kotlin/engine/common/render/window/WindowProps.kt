package engine.common.render.window

import engine.common.getSystem
import engine.common.system.console.variables.render.VSyncVariable
import engine.common.system.console.variables.render.WindowXVariable
import engine.common.system.console.variables.render.WindowYVariable

data class WindowProps(
    val title: String = "kEngine Application",
    val width: Int = getSystem().console.getVariableValue(WindowXVariable::class) ?: 1280,
    val height: Int = getSystem().console.getVariableValue(WindowYVariable::class) ?: 720,
    val vSync: Boolean = getSystem().console.getVariableValue(VSyncVariable::class) ?: true
)
