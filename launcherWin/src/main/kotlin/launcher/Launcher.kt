package launcher

import engine.common.GameApplication

class Launcher

fun main(args: Array<String>) {
    GameApplication.run(Launcher::class, args)
}
