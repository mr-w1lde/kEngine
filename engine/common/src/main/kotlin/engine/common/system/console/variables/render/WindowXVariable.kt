package engine.common.system.console.variables.render

import engine.common.system.console.ConsoleVariable
import engine.common.system.console.VariableGroup
import kotlin.reflect.KClass

object WindowXVariable : ConsoleVariable<Int> {
    override val group: VariableGroup = VariableGroup.Render

    override val consoleName: String = "window.x"

    override val defaultValue: Int = 1280

    override val klass: KClass<Int> = Int::class
}
