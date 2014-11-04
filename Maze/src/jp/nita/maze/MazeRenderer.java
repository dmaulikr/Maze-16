package jp.nita.maze;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import android.opengl.GLES10;
import android.opengl.GLES20;
import android.opengl.GLSurfaceView.Renderer;
import android.util.Log;

public class MazeRenderer implements Renderer {

	@Override
	public void onSurfaceCreated(GL10 gl, EGLConfig config) {


	}
 
	@Override
	public void onSurfaceChanged(GL10 gl, int width, int height) {
		GLES10.glViewport(0, 0, width, height);
	}

	@Override
	public void onDrawFrame(GL10 gl) {
        gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
         
        float[] vertices = new float[3 * 4];
         
        int vertexIdx = 0;
 
        vertices[vertexIdx++] = 0.5f;
        vertices[vertexIdx++] = 0.5f;
        vertices[vertexIdx++] = 0.5f;
 
        vertices[vertexIdx++] = -0.5f;
        vertices[vertexIdx++] = 0.5f;
        vertices[vertexIdx++] = 0.5f;
 
        vertices[vertexIdx++] = -0.5f;
        vertices[vertexIdx++] = -0.5f;
        vertices[vertexIdx++] = 0.5f;
        
        vertices[vertexIdx++] = 0.5f;
        vertices[vertexIdx++] = -0.5f;
        vertices[vertexIdx++] = 0.5f;
         
        ByteBuffer bb = ByteBuffer.allocateDirect(vertices.length * 4);
        bb.order(ByteOrder.nativeOrder());
 
        FloatBuffer fb = bb.asFloatBuffer();
        fb.put(vertices);
 
        gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
 
        gl.glVertexPointer(3, GL10.GL_FLOAT, 0, fb);
         
        gl.glDrawArrays(GL10.GL_TRIANGLE_STRIP, 0, 4);
	}

}