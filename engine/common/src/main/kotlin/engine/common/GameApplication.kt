package engine.common

import engine.common.log.log
import engine.common.plugin.PluginManager
import kotlin.reflect.KClass

// TODO -> Not in Common?
object GameApplication {
    private lateinit var primarySource: KClass<*>
    private lateinit var appArgs: Array<String>

    @Suppress("TooGenericExceptionCaught")
    fun run(primarySource: KClass<*>, args: Array<String>) {
        val startTime = System.nanoTime()

        GameApplication.primarySource = primarySource
        appArgs = args

        gEngine = object : Engine {}

        log.debug("Initializing Game Application")

        try {
            init()
            run()
        } catch (ex: Exception) {
            log.error("kENGINE FATAL ERROR", ex)
            throw ex
        } finally {
            shutdown()
        }
    }

    private fun init() {
        PluginManager.loadPlugins()
        PluginManager.sendOnInitializeToAll()
    }

    private fun run() {
        // TODO -> Loop
    }

    private fun shutdown() {
        log.trace("Shutting down the Application")
        // maybe some async
        PluginManager.sendOnShutdownToAll()
    }
}
