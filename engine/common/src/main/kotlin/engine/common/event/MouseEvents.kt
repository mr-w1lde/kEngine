package engine.common.event

class MouseMovedEvent(
    val x: Double,
    val y: Double,
) : Event(eventType, flags) {
    override fun toString(): String = "MouseMoved(x: $x, y: $y)"

    companion object {
        val eventType = EventType.MouseMoved
        val flags = EventClassification.Mouse or EventClassification.Input
    }
}

class MouseScrolledEvent(
    val xOffset: Double,
    val yOffset: Double,
) : Event(eventType, flags) {
    override fun toString(): String = "MouseScrolled(x: $xOffset, y: $yOffset)"

    companion object {
        val eventType = EventType.MouseScrolled
        val flags = EventClassification.Mouse or EventClassification.Input
    }
}

class MouseButtonPressedEvent(
    val button: Int
) : Event(eventType, flags) {
    override fun toString(): String = "MouseButtonPressed(button: $button)"

    companion object {
        val eventType = EventType.MouseButtonPressed
        val flags = EventClassification.MouseButton or EventClassification.Input
    }
}

class MouseButtonReleasedEvent(
    val button: Int,
) : Event(eventType, flags) {
    override fun toString(): String = "MouseButtonReleased(button: $button)"

    companion object {
        val eventType = EventType.MouseButtonReleased
        val flags = EventClassification.MouseButton or EventClassification.Input
    }
}
