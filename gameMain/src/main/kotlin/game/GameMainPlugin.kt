package game

import engine.common.gEngine
import engine.common.log.log
import engine.common.plugin.EnginePlugin
import engine.common.plugin.RegisterPlugin

private const val PLUGIN_NAME = "Game"

@RegisterPlugin
class GameMainPlugin : EnginePlugin {
    override val name: String
        get() = PLUGIN_NAME

    override fun onInitialize() {
        log.trace("onInitialize")
        log.trace("{}", gEngine)
        log.trace("{}", gEngine)
    }

    override fun onGameStart() {
        log.trace("onGameStart")
    }

    override fun onGameStop() {
        log.trace("onGameStop")
    }

    override fun onShutdown() {
        log.trace("onShutdown")
    }
}
