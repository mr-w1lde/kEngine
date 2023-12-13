package engine.common.input

import engine.common.SubSystem

/**
 * Engine Input SubSystem
 * */
interface Input : SubSystem {
    fun registerAnyInputEventListener(clazz: InputEventListener)

    fun removeAnyInputEventListener(clazz: InputEventListener)

    // TODO -> maybe not here?
    fun registerApplicationEventListener(clazz: ApplicationEventListener)

    fun removeApplicationEventListener(clazz: ApplicationEventListener)
}
