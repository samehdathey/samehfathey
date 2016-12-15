package de.mglaubitz.sillyracer

import android.opengl.GLES20
import java.nio.ByteBuffer
import java.nio.ByteOrder
import java.nio.FloatBuffer


class Triangle {

    // Set color with red, green, blue and alpha (opacity) values
    private var color = Color.rgbPartsWithAlpha(Color.RED, 1f)

    // number of coordinates per vertex in this array
    private val COORDS_PER_VERTEX = 3
    private val triangleCoords = floatArrayOf(
        // in counterclockwise order:
        0.0f, 0.622008459f, 0.0f, // top
        -0.5f, -0.311004243f, 0.0f, // bottom left
        0.5f, -0.311004243f, 0.0f  // bottom right
    )

    private val vertexBuffer: FloatBuffer
    private val vertexCount = triangleCoords.size / COORDS_PER_VERTEX
    private val vertexStride = COORDS_PER_VERTEX * 4 // 4 bytes per vertex

    init {
        // initialize vertex byte buffer for shape coordinates
        val bb = ByteBuffer.allocateDirect(
                // (number of coordinate values * 4 bytes per float)
                triangleCoords.size * 4).apply {

            // use the device hardware's native byte order
            order(ByteOrder.nativeOrder())
        }

        // create a floating point buffer from the ByteBuffer
        vertexBuffer = bb.asFloatBuffer().apply {
            put(triangleCoords)
            position(0)
        }
    }

    fun draw() {
        GLES20.glUseProgram(shaderProgram)

        // get handle to vertex shader's vPosition member
        val positionHandle = GLES20.glGetAttribLocation(shaderProgram, "vPosition")

        // Enable a handle to the triangle vertices
        GLES20.glEnableVertexAttribArray(positionHandle)

        // Prepare the triangle coordinate data
        GLES20.glVertexAttribPointer(positionHandle, COORDS_PER_VERTEX,
                GLES20.GL_FLOAT, false,
                vertexStride, vertexBuffer)

        // get handle to fragment shader's vColor member
        val mColorHandle = GLES20.glGetUniformLocation(shaderProgram, "vColor")

        // Set color for drawing the triangle
        GLES20.glUniform4fv(mColorHandle, 1, color, 0)

        // Draw the triangle
        GLES20.glDrawArrays(GLES20.GL_TRIANGLES, 0, vertexCount)

        // Disable vertex array
        GLES20.glDisableVertexAttribArray(positionHandle)
    }

    companion object {

        private var shaderProgram: Int = -1

        fun initShaderProgram() {
            val vertexShader = ShaderUtils.loadShader(GLES20.GL_VERTEX_SHADER, vertexShaderCode)
            val fragmentShader = ShaderUtils.loadShader(GLES20.GL_FRAGMENT_SHADER, fragmentShaderCode)

            // create empty OpenGL ES Program
            shaderProgram = GLES20.glCreateProgram()

            // add the vertex shader to program
            GLES20.glAttachShader(shaderProgram, vertexShader)

            // add the fragment shader to program
            GLES20.glAttachShader(shaderProgram, fragmentShader)

            // creates OpenGL ES program executables
            GLES20.glLinkProgram(shaderProgram)
        }

        private val vertexShaderCode = """
            attribute vec4 vPosition;
            void main() {
                gl_Position = vPosition;
            }
        """

        private val fragmentShaderCode = """
            precision mediump float;
            uniform vec4 vColor;
            void main() {
              gl_FragColor = vColor;
            }
        """

    }

}