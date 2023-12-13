package engine.common.event

class KeyPressedEvent(
    val keyCode: Int,
    val isLongPress: Boolean = false
) : Event(eventType, flags) {
    override fun toString(): String = "KeyPressed(keyCode: $keyCode, isLongPress $isLongPress)"

    companion object {
        val eventType = EventType.KeyPressed
        val flags = EventClassification.Input or EventClassification.Keyboard
    }
}

class KeyReleasedEvent(
    val keyCode: Int,
) : Event(eventType, flags) {
    override fun toString(): String = "KeyReleased(keyCode: $keyCode)"

    companion object {
        val eventType = EventType.KeyReleased
        val flags = EventClassification.Input or EventClassification.Keyboard
    }
}

class KeyTypedEvent(
    val keyCode: Int,
) : Event(eventType, flags) {
    override fun toString(): String = "KeyTyped(keyCode: $keyCode)"

    companion object {
        val eventType = EventType.KeyTyped
        val flags = EventClassification.Input or EventClassification.Keyboard
    }
}
