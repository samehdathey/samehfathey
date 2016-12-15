package de.mglaubitz.sillyracer

import android.opengl.GLES20
import android.opengl.GLSurfaceView
import javax.microedition.khronos.egl.EGLConfig
import javax.microedition.khronos.opengles.GL10


class SillyRacerRenderer : GLSurfaceView.Renderer {

    private var triangle: Triangle? = null

    override fun onDrawFrame(gl: GL10) {
        // Redraw background color
        GLES20.glClear(GLES20.GL_COLOR_BUFFER_BIT)
        triangle?.draw()
    }

    override fun onSurfaceChanged(gl: GL10, width: Int, height: Int) {
        GLES20.glViewport(0, 0, width, height)
    }

    override fun onSurfaceCreated(gl: GL10, glConfig: EGLConfig) {
        GLES20.glClearColor(0.0f, 0.0f, 0.0f, 1.0f)

        Triangle.initShaderProgram()
        triangle = Triangle()
    }

}