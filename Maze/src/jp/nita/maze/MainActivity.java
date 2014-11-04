package jp.nita.maze;

import android.app.Activity;
import android.opengl.GLSurfaceView;
import android.os.Bundle;

public class MainActivity extends Activity {

	private GLSurfaceView mGLSurfaceView;

	@Override
	public void onCreate(final Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		mGLSurfaceView = new GLSurfaceView(this);
		mGLSurfaceView.setEGLContextClientVersion(1);
		mGLSurfaceView.setRenderer(new MazeRenderer());
		setContentView(mGLSurfaceView);
	}

	@Override
	public void onResume() {
		super.onResume();
		mGLSurfaceView.onResume();
	}

	@Override
	public void onPause() {
		super.onPause();
		mGLSurfaceView.onPause();
	}
}
