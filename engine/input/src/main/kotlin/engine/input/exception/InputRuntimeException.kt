package engine.input.exception

data class InputRuntimeException(
    override val message: String,
    override val cause: Throwable? = null
) : RuntimeException()
