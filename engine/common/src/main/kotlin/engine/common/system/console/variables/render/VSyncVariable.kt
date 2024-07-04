package engine.common.system.console.variables.render

import engine.common.system.console.ConsoleVariable
import engine.common.system.console.VariableGroup
import kotlin.reflect.KClass

object VSyncVariable : ConsoleVariable<Boolean> {
    override val group: VariableGroup = VariableGroup.Render

    override val consoleName: String = "vsync"

    override val defaultValue: Boolean = true

    override val klass: KClass<Boolean> = Boolean::class
}
