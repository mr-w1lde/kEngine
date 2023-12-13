package game

import engine.common.event.Event
import engine.common.event.EventDispatcher
import engine.common.getInput
import engine.common.getRender
import engine.common.input.InputEventListener
import engine.common.log.log
import engine.common.plugin.EnginePlugin
import engine.common.plugin.RegisterPlugin
import engine.common.render.layer.Layer

private const val PLUGIN_NAME = "Game"

class ExampleLayer : Layer(name = "Example", debugName = "ExampleLayer") {
    override fun onAttach() { }

    override fun onDetach() { }

    override fun onUpdate() {
        log.info("ExampleLayer::onUpdate")
    }

    override fun onEvent(event: Event) {
        log.trace("{}", event)
    }

}

@RegisterPlugin
class GameMainPlugin : EnginePlugin, InputEventListener {
    override val name: String
        get() = PLUGIN_NAME

    override fun onInitialize() {
        log.info("Initializing Game Plugin")

        getInput().registerAnyInputEventListener(this)
        getRender().layerStack().pushLayer(ExampleLayer())
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
