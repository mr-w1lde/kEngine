package engine.common.render.window

interface Window {
    val id: Long

    val width: Int

    val height: Int

    fun onUpdate()

    fun shutdown()

    fun setVSync(enable: Boolean)

    fun isVSync(): Boolean
}
