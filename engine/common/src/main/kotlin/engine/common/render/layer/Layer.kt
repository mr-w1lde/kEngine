package engine.common.render.layer

import engine.common.event.Event

@Suppress("EmptyFunctionBlock")
abstract class Layer(
    private val debugName: String
) : LayerInterface {
    fun getName(): String = debugName
}
