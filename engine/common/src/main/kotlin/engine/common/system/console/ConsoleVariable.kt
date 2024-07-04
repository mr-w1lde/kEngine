package engine.common.system.console

import kotlin.reflect.KClass

interface ConsoleVariable<T : Any> {
    val group: VariableGroup

    val consoleName: String

    val defaultValue: T?

    val klass: KClass<T>
}
