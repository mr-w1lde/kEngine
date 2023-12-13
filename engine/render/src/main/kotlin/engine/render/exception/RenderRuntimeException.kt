package engine.render.exception

data class RenderRuntimeException(
    override val message: String,
    override val cause: Throwable? = null
) : RuntimeException()
