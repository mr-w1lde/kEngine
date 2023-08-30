package engine.common.event

private fun bit(x: Int) = 1 shl x

private interface WithSifting {
    val flag: Int
}

enum class EventType {
    WindowClose,
    KeyPressed,
    KeyReleased,
    MouseMoved,
    MouseScrolled,
    MouseButtonPressed,
    MouseButtonReleased,
}

@Suppress("MagicNumber")
enum class EventClassification(override val flag: Int) : WithSifting {
    Application(bit(0)),

    Input(bit(1)),
    Keyboard(bit(2)),
    Mouse(bit(3)),
    MouseButton(bit(4)),
}

infix fun <T : WithSifting> T.or(other: T): Int = flag or other.flag

abstract class Event(
    val eventType: EventType,
    private val classificationFlags: Int
) {
    var isHandled: Boolean = false

    fun isInClassification(classification: EventClassification): Boolean {
        return (classificationFlags and classification.flag) != 0
    }

    override fun toString(): String = eventType.name
}
