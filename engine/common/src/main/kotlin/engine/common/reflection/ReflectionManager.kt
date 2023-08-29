package engine.common.reflection

import engine.common.plugin.EnginePlugin
import engine.common.plugin.RegisterPlugin
import org.reflections.Reflections
import org.reflections.scanners.Scanners.TypesAnnotated
import org.reflections.scanners.Scanners.MethodsAnnotated
import org.reflections.util.ConfigurationBuilder
import org.reflections.util.FilterBuilder

// TODO -> In Common?
object ReflectionManager {
    private val reflections = Reflections(
        ConfigurationBuilder()
            .forPackages("game", "engine", "plugin")
            .filterInputsBy(
                FilterBuilder()
                    .excludePackage("engine.common")
            )
            .setScanners(TypesAnnotated, MethodsAnnotated)
    )

    @Suppress("UNCHECKED_CAST")
    fun getEnginePlugins(): Set<Class<out EnginePlugin>> =
        reflections.get(
            TypesAnnotated.with(RegisterPlugin::class.java).asClass<EnginePlugin>()
        ) as Set<Class<out EnginePlugin>>
}