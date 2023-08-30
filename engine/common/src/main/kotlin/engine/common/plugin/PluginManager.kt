package engine.common.plugin

import engine.common.log.log
import engine.common.reflection.ReflectionManager

object PluginManager {
    private val plugins = mutableMapOf<String, EnginePlugin>()

    fun loadPlugins() {
        ReflectionManager.getEnginePlugins()
            .sortByOrder()
            .forEach {
                val newInstance = it.getConstructor().newInstance()
                validateOnExist(newInstance.name)

                log.info("Loading plugin class: ${it.simpleName} with PluginName: ${newInstance.name}")
                plugins[newInstance.name] = newInstance
            }
    }

    fun sendOnInitializeToAll() {
        plugins.values.forEach {
            it.onInitialize()
        }
    }

    fun sendOnShutdownToAll() {
        plugins.values.reversed().forEach {
            it.onShutdown()
        }
    }

    private fun Set<Class<out EnginePlugin>>.sortByOrder(): Set<Class<out EnginePlugin>> {
        val max = size
        return sortedWith(compareBy {
            val order = (it.annotations[0] as RegisterPlugin).order
            if (order == -1) max + 2 else order
        }).toSet()
    }

    private fun validateOnExist(instanceName: String) {
        if (plugins[instanceName] != null) {
            throw PluginAlreadyExistInMemory(
                "Plugin with name \"$instanceName\" already registered by system " +
                        "with class \"${plugins[instanceName]!!::class.qualifiedName}\""
            )
        }
    }
}
