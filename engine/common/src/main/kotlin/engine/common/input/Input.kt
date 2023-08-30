package engine.common.input

interface Input {
    fun registerAnyInputEventListener(clazz: InputEventListener)

    fun removeAnyInputEventListener(clazz: InputEventListener)

    // TODO -> maybe not here?
    fun registerApplicationEventListener(clazz: ApplicationEventListener)

    fun removeApplicationEventListener(clazz: ApplicationEventListener)
}
