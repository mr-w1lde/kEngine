package engine.common.system.console

import kotlin.reflect.KClass

interface Console {
    fun <V : ConsoleVariable<*>> registerVariable(obj: V)

    fun <V : ConsoleVariable<*>> registerVariable(set: Set<V>)

    fun <V : ConsoleVariable<T>, T> getVariableValue(clazz: KClass<V>): T?

    fun <V : ConsoleVariable<T>, T> setVariableValue(clazz: KClass<V>, value: T): Boolean
}
