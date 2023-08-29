package engine.common.plugin

import engine.common.log.log
import engine.common.reflection.ReflectionManager

object PluginManager {
    private val plugins = mutableSetOf<EnginePlugin>()

    fun loadPlugins() {
        ReflectionManager.getEnginePlugins().forEach {
            val newInstance = it.getConstructor().newInstance()
            log.info("Loading plugin class: ${it.simpleName} with PluginName: ${newInstance.name}")
            plugins.add(newInstance)
        }
    }

    fun sendOnInitializeToAll() {
        plugins.forEach {
            it.onInitialize()
        }
    }
}