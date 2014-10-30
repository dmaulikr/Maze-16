package jp.nita.maze;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import android.opengl.GLES20;
import android.opengl.GLSurfaceView.Renderer;

public class MazeRenderer implements Renderer {

	private String vertexShaderCode = 
	        "attribute vec2 vPos; void main() { gl_Position = vec4(vPos, 0.0, 1.0); }";

	private String fragmentShaderCode = 
	       "void main() { gl_FragColor = vec4(1.0, 0.0, 0.0, 1.0); }";

	private int mProgram;

	private FloatBuffer mVertexBuffer;

	@Override
	public void onSurfaceCreated(GL10 gl, EGLConfig config) {

		int vshader = GLES20.glCreateShader(GLES20.GL_VERTEX_SHADER);
		int fshader = GLES20.glCreateShader(GLES20.GL_FRAGMENT_SHADER);

		GLES20.glShaderSource(vshader, vertexShaderCode);
		GLES20.glCompileShader(vshader);

		GLES20.glShaderSource(fshader, fragmentShaderCode);
		GLES20.glCompileShader(fshader);

		mProgram = GLES20.glCreateProgram(); 

		GLES20.glAttachShader(mProgram, vshader);
		GLES20.glAttachShader(mProgram, fshader);

		GLES20.glLinkProgram(mProgram);

		GLES20.glClearColor(0.0f, 0.0f, 0.5f, 1.0f);

		float[] vertexList = {0.0f, 0.5f, 0.5f, -0.5f, -0.5f, -0.5f};

		ByteBuffer bb = ByteBuffer.allocateDirect(vertexList.length * 4);
		bb.order(ByteOrder.nativeOrder());
		mVertexBuffer = bb.asFloatBuffer();
		mVertexBuffer.put(vertexList);
		mVertexBuffer.position(0);

	}
 
	@Override
	public void onSurfaceChanged(GL10 gl, int width, int height) {
		GLES20.glViewport(0, 0, width, height);
	}

	@Override
	public void onDrawFrame(GL10 gl) {

		GLES20.glClear(GLES20.GL_COLOR_BUFFER_BIT);

		GLES20.glUseProgram(mProgram);

		int vPos = GLES20.glGetAttribLocation(mProgram, "vPos");
		GLES20.glEnableVertexAttribArray(vPos);

		GLES20.glVertexAttribPointer(vPos, 2, GLES20.GL_FLOAT, false, 0, mVertexBuffer);

		GLES20.glDrawArrays(GLES20.GL_TRIANGLES, 0, 3);

		GLES20.glDisableVertexAttribArray(vPos);

	}

}