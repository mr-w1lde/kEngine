package engine.common.plugin

interface EnginePlugin {
    /**
     * A Plugin name
     * */
    val name: String


    /**
     * Gets called whenever the engine initializes a plugin for the first time.
     * */
    fun onInitialize()

    /**
     * Gets called whenever the Engine starts your game
     * */
    fun onGameStart()

    /**
     * Gets called whenever the Engine stops your game
     * */
    fun onGameStop()

    /**
     * Gets called whenever the engine is trying to shut down a plugin.
     * */
    fun onShutdown()
}
