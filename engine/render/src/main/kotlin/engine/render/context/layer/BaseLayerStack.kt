package engine.render.context.layer

import engine.common.render.layer.Layer
import engine.common.render.layer.LayerStack

class BaseLayerStack : LayerStack {
    private val layers = mutableListOf<Layer>()

    private var layerInsertIndex: Int = 0

    override fun pushLayer(layer: Layer) {
        layers.add(layer)
        layerInsertIndex++
    }

    override fun pushOverlay(overlay: Layer) {
        layers.add(overlay)
    }

    override fun popLayer(layer: Layer) {
        val it = layers.indexOfFirst { it == layer }
        if (it in 0 until layerInsertIndex) {
            layer.onDetach()
            layers.removeAt(it)
            layerInsertIndex--
        }
    }

    override fun popOverlay(overlay: Layer) {
        val it = layers.indexOfLast { it == overlay }
        if (it >= layerInsertIndex) {
            overlay.onDetach()
            layers.removeAt(it)
        }
    }

    fun getLayers() = layers
}
