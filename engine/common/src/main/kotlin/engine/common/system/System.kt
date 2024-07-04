package engine.common.system

import engine.common.SubSystem
import engine.common.system.console.Console

/**
 * Engine System SubSystem
 * */
interface System : SubSystem {
    val console: Console
}
