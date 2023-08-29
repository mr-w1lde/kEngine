package game

import engine.common.gEngine
import engine.common.log.log
import engine.common.plugin.EnginePlugin
import engine.common.plugin.RegisterPlugin

@RegisterPlugin
class EntryPoint : EnginePlugin {
    override val name: String
        get() = "GameMain"

    override fun onInitialize() {
        log.trace("onInitialize")
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
