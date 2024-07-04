package engine.common.system.console.variables.render

import engine.common.system.console.ConsoleVariable
import engine.common.system.console.VariableGroup
import kotlin.reflect.KClass

object WindowYVariable : ConsoleVariable<Int> {
    override val group: VariableGroup = VariableGroup.Render

    override val consoleName: String = "window.y"

    override val defaultValue: Int = 720

    override val klass: KClass<Int> = Int::class
}
