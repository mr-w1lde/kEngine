package engine.common.event

import java.util.function.Function

object EventDispatcher {
    inline operator fun <reified T : Event> invoke(event: Event, function: Function<T, Boolean>): Boolean {
        if (event::class != T::class) {
            return false
        }

        event.isHandled = function.apply(event as T)
        return true
    }
}
