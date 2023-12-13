package engine.render.context.imgui.example

import ch.qos.logback.classic.LoggerContext
import ch.qos.logback.classic.spi.ILoggingEvent
import ch.qos.logback.core.AppenderBase
import imgui.ImGui
import imgui.flag.ImGuiInputTextFlags
import imgui.flag.ImGuiWindowFlags
import imgui.type.ImString
import org.slf4j.LoggerFactory

class ImGuiConsole {
    private val consoleBuffer = StringBuilder()

    private val loggerContext = LoggerFactory.getILoggerFactory() as LoggerContext

    init {
        val logbackLogger = loggerContext.getLogger(org.slf4j.Logger.ROOT_LOGGER_NAME)
        logbackLogger.addAppender(object : AppenderBase<ILoggingEvent>() {
            override fun append(event: ILoggingEvent) {
                val log = "${event.level}: ${event.loggerName} - ${event.formattedMessage}"

                consoleBuffer.appendLine(log)
            }
        }.also { it.start() })
    }

    fun render() {
        ImGui.begin("Console", ImGuiWindowFlags.HorizontalScrollbar)

        ImGui.textWrapped(consoleBuffer.toString())


        val inputFlags = ImGuiInputTextFlags.None or ImGuiInputTextFlags.EnterReturnsTrue
        if (ImGui.inputText("##input", ImString(""), inputFlags)) {
            //val inputText = ImGui.getIO().
        }

        ImGui.end()
    }
}