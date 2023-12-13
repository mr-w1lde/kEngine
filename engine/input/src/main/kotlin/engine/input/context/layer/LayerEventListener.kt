package engine.input.context.layer

import engine.common.event.Event
import engine.common.getRender
import engine.common.input.ApplicationEventListener
import engine.common.input.InputEventListener

class LayerEventListener : ApplicationEventListener, InputEventListener {
    override fun onApplicationEvent(event: Event) {
        notifyOnEventAllLayers(event)
    }

    override fun onAnyInputEvent(event: Event) {
        notifyOnEventAllLayers(event)
    }

    private fun notifyOnEventAllLayers(event: Event) {
        getRender().layerStack().getLayers().forEach {
            it.onEvent(event)
            if (event.isHandled) return@forEach
        }
    }
}
