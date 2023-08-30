package engine.common

import engine.common.input.Input
import engine.common.render.Render

interface Engine {
    var input: Input?
    var render: Render?
}

lateinit var gEngine: Engine
