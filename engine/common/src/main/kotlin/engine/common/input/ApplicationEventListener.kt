package engine.common.input

import engine.common.event.Event

interface ApplicationEventListener {
    fun onApplicationEvent(event: Event)
}
