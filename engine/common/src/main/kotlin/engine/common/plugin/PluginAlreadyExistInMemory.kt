package engine.common.plugin

import java.lang.RuntimeException

data class PluginAlreadyExistInMemory(override val message: String) : RuntimeException()
