package engine.common.render.window

data class WindowProps(
    val title: String = "kEngine Application",
    val width: Int = 2160,
    val height: Int = 1440,
    val vSync: Boolean = true
)
