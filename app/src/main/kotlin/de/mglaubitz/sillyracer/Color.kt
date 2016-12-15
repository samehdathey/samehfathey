package de.mglaubitz.sillyracer

object Color {

    val BLACK = 0xFF000000.toInt()
    val DKGRAY = 0xFF444444.toInt()
    val GRAY = 0xFF888888.toInt()
    val LTGRAY = 0xFFCCCCCC.toInt()
    val WHITE = 0xFFFFFFFF.toInt()
    val RED = 0xFFFF0000.toInt()
    val GREEN = 0xFF00FF00.toInt()
    val BLUE = 0xFF0000FF.toInt()
    val YELLOW = 0xFFFFFF00.toInt()
    val CYAN = 0xFF00FFFF.toInt()
    val MAGENTA = 0xFFFF00FF.toInt()
    val TRANSPARENT = 0

    fun alpha(color: Int): Int {
        return color.ushr(24)
    }

    fun red(color: Int): Int {
        return color shr 16 and 0xFF
    }

    fun green(color: Int): Int {
        return color shr 8 and 0xFF
    }

    fun blue(color: Int): Int {
        return color and 0xFF
    }


    fun rgbPartsWithAlpha(color: Int, alpha: Float = 1f): FloatArray {
        return floatArrayOf(red(color) / 255f, green(color) / 255f, blue(color) / 255f, alpha)
    }

}