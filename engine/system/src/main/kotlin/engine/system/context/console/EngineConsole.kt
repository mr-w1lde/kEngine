package engine.system.context.console

import engine.common.log.log
import engine.common.system.console.Console
import engine.common.system.console.ConsoleVariable
import org.ini4j.Ini
import java.io.File
import kotlin.reflect.KClass


class EngineConsole : Console {
    private val variablesMap = mutableMapOf<KClass<*>, Any?>()

    private var engineConfig: Ini = Ini(
        File("engine.ini")
    )

    override fun <V : ConsoleVariable<*>> registerVariable(obj: V) {
        variablesMap[obj::class] = parseVariableFromConfig(obj)
    }

    override fun <V : ConsoleVariable<*>> registerVariable(set: Set<V>) {
        set.forEach { registerVariable(it) }
    }

    override fun <V : ConsoleVariable<T>, T> getVariableValue(clazz: KClass<V>): T? {
        return variablesMap[clazz]?.let { it as? T }
    }

    override fun <V : ConsoleVariable<T>, T> setVariableValue(clazz: KClass<V>, value: T): Boolean {
        variablesMap[clazz] ?: return false
        variablesMap[clazz] = value as Any?

        return true
    }

    private fun parseVariableFromConfig(obj: ConsoleVariable<*>): Any? {
        return try {
            engineConfig[obj.group.name]?.get(obj.consoleName, obj.klass.java)
        } catch (ex: Exception) {
            log.warn("Couldn't parse ${obj.group}:${obj.consoleName} variable, using default value - ${obj.defaultValue}", ex)
            obj.defaultValue
        }
    }
}
