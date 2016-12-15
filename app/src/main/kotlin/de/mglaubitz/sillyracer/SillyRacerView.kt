package de.mglaubitz.sillyracer

import android.content.Context
import android.opengl.GLSurfaceView
import android.util.AttributeSet

class SillyRacerView @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null) : GLSurfaceView(context, attrs) {

    init {
        setEGLContextClientVersion(2)
        setRenderer(SillyRacerRenderer())
        renderMode = GLSurfaceView.RENDERMODE_WHEN_DIRTY
    }
}