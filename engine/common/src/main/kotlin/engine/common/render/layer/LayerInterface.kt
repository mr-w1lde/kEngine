package engine.common.render.layer

import engine.common.event.Event

internal interface LayerInterface {
    fun onAttach() { }

    fun onDetach() { }

    fun onUpdate() { }

    fun onEvent(event: Event) { }
}
