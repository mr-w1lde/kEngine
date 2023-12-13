package engine.common.render.layer

@Suppress("EmptyFunctionBlock")
abstract class Layer(
    private val debugName: String
) : LayerInterface {
    fun getName(): String = debugName
}
