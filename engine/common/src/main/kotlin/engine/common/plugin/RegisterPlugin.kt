package engine.common.plugin

/**
 * Will find and load a plugin via Reflection
 *
 * @param order
 * if order is minus -> we skip load in order
 * if set -> load will in order from lower to higher
 *
 *
 * also:
 * @see engine.common.reflection.ReflectionManager
 * @see engine.common.plugin.PluginManager.loadPlugins
 * */
annotation class RegisterPlugin(
    val order: Int = -1
)
