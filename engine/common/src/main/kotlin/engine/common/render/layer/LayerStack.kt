package engine.common.render.layer

interface LayerStack {
    fun pushLayer(layer: Layer)

    fun pushOverlay(overlay: Layer)

    fun popLayer(layer: Layer)

    fun popOverlay(overlay: Layer)
}
