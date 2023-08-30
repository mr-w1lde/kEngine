package game

import engine.common.event.Event
import engine.common.event.EventDispatcher
import engine.common.event.KeyPressedEvent
import engine.common.gEngine
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

        gEngine.input?.registerAnyInputEventListener(this)
    }

    override fun onGameStart() {
        log.trace("onGameStart")
    }

    override fun onGameStop() {
        log.trace("onGameStop")
    }

    override fun onShutdown() {
        log.trace("onShutdown")
        gEngine.input?.registerAnyInputEventListener(this)
    }

    override fun onAnyInputEvent(event: Event) {
        EventDispatcher<KeyPressedEvent>(event) {
            log.trace("onAnyInputEvent : KeyPressedEvent : {}", it.keyCode)
            true
        }
    }
}
