package engine.common

import engine.common.input.Input
import engine.common.render.Render
import kotlin.reflect.KClass
import kotlin.reflect.full.isSubclassOf
import kotlin.reflect.full.superclasses

private val engineSubSystems = mutableMapOf<KClass<*>, SubSystem>()

/**
 * SubSystem simple dependency system
 *
 * @see engine.common.input.Input
 * @see engine.input.InputPlugin.onInitialize
 * */
interface SubSystem {
    companion object {
        @Suppress("TooGenericExceptionThrown")
        fun <T : SubSystem> register(block: () -> T): T {
            val subSystem = block()
            val subSystemKlass = subSystem::class
                .superclasses
                .find { it.isSubclassOf(SubSystem::class) }
                ?: throw RuntimeException("SubSystem interface for ${subSystem::class.simpleName} not found")

            synchronized(this) {
                assert(engineSubSystems.containsKey(subSystemKlass)) {
                    throw RuntimeException("SubSystem already registered!")
                }

                engineSubSystems[subSystemKlass] = subSystem
            }

            return subSystem
        }
    }
}


/**
 * SubSystem simple dependency system
 * Before use make sure that SubSystem already registered by the system
 *
 * @see SubSystem.register
 * */
@Suppress("unchecked_cast")
fun <T : SubSystem> getSubSystem(kClass: KClass<T>): T {
    return (engineSubSystems[kClass] as? T)
        ?: throw NoSuchElementException("SubSystem<${kClass.simpleName}> not found")
}

// ENGINE READY TO USE FUNC

fun getInput() = getSubSystem(Input::class)
fun getRender() = getSubSystem(Render::class)
