package launcher

import engine.common.plugin.PluginManager

fun main(args: Array<String>) {
    println("Launcher for Darwin ARM")

    PluginManager.loadPlugins()
    PluginManager.sendOnInitializeToAll()
}
