package game

import engine.common.event.Event
import engine.common.event.EventDispatcher
import engine.common.getInput
import engine.common.input.InputEventListener
import engine.common.log.log
import engine.common.plugin.EnginePlugin
import engine.common.plugin.RegisterPlugin

private const val PLUGIN_NAME = "Game"

@RegisterPlugin
class GameMainPlugin : EnginePlugin, InputEventListener {
    override val name: String
        get() = PLUGIN_NAME

    override fun onInitialize() {
        log.info("Initializing Game Plugin")

        getInput().registerAnyInputEventListener(this)
    }

    override fun onGameStart() {
        log.trace("onGameStart")
    }

    override fun onGameStop() {
        log.trace("onGameStop")
    }

    override fun onShutdown() {
        log.trace("onShutdown")
        getInput().removeAnyInputEventListener(this)
    }

    override fun onAnyInputEvent(event: Event) {
        EventDispatcher<Event>(event) {
            log.trace("onAnyInputEvent, {}", it.toString())
            true
        }
        log.trace("{}", event.toString())
    }
}
