package engine.common.render.window

data class WindowProps(
    val title: String = "kEngine Application",
    val width: Int = 1920,
    val height: Int = 1080,
    val vSync: Boolean = true
)
