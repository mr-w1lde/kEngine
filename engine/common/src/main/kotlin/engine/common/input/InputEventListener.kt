package engine.common.input

import engine.common.event.Event

interface InputEventListener {
    fun onAnyInputEvent(event: Event)
}
