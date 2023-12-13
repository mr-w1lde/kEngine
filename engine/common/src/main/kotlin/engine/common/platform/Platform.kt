package engine.common.platform

import org.lwjgl.system.Platform

fun isARMArchitecture(): Boolean = Platform.getArchitecture() ===
        Platform.Architecture.ARM64 ||
        Platform.getArchitecture() === Platform.Architecture.ARM32

fun isWindowsPlatform(): Boolean = Platform.get() === Platform.WINDOWS

fun isLinuxPlatform(): Boolean = Platform.get() === Platform.LINUX

fun isDarwinPlatform(): Boolean = Platform.get() === Platform.MACOSX
