package engine.common.render.layer

import engine.common.event.Event

@Suppress("EmptyFunctionBlock")
abstract class Layer(
    private val name: String = "Layer",
    private val debugName: String
) {
    abstract fun onAttach()

    abstract fun onDetach()

    abstract fun onUpdate()

    abstract fun onEvent(event: Event)

    fun getName(): String = debugName
}
