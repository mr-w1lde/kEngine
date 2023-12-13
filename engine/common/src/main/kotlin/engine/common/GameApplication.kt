package engine.common

import engine.common.event.Event
import engine.common.event.EventDispatcher
import engine.common.event.WindowClosedEvent
import engine.common.input.ApplicationEventListener
import engine.common.log.log
import engine.common.plugin.PluginManager
import java.util.concurrent.TimeUnit
import kotlin.concurrent.Volatile
import kotlin.reflect.KClass

object GameApplication {
    private lateinit var primarySource: KClass<*>
    private lateinit var appArgs: Array<String>

    private val applicationEventsHandler = ApplicationEventsHandler()

    @Volatile
    private var isRunning: Boolean = false

    @Suppress("TooGenericExceptionCaught")
    fun run(primarySource: KClass<*>, args: Array<String>) {
        val startTime = System.nanoTime()

        GameApplication.primarySource = primarySource
        appArgs = args

        log.debug("Initializing Game Application")

        try {
            init(startTime)
            run()
        } catch (ex: Exception) {
            log.error("kENGINE FATAL ERROR", ex)
            throw ex
        } finally {
            shutdown()
        }
    }

    @Suppress("TooGenericExceptionThrown")
    private fun init(startTime: Long) {
        PluginManager.loadPlugins()
        PluginManager.sendOnInitializeToAll()

        getInput().registerApplicationEventListener(applicationEventsHandler)

        val endTime = System.nanoTime() - startTime
        val endTimeInMillis = TimeUnit.NANOSECONDS.toMillis(endTime)
        log.debug("Application init took $endTimeInMillis ms")
    }

    private fun run() {
        isRunning = true
        // Main Game-Loop Thread
        while (isRunning) {
            getRender().update()
        }
    }

    private fun shutdown() {
        log.trace("Shutting down the Application")
        // maybe some async?
        getInput().removeApplicationEventListener(applicationEventsHandler)
        PluginManager.sendOnShutdownToAll()
    }

    private class ApplicationEventsHandler : ApplicationEventListener {
        override fun onApplicationEvent(event: Event) {
            EventDispatcher(event, ::onWindowClose)
        }

        private fun onWindowClose(event: WindowClosedEvent): Boolean {
            isRunning = false
            return true
        }
    }
}
