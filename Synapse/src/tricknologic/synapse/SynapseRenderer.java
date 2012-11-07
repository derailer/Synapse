package tricknologic.synapse;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10; 

import android.opengl.GLSurfaceView;
import android.opengl.GLU;

public class SynapseRenderer implements GLSurfaceView.Renderer {

	private float _red 		= 0.3f;
	private float _green 	= 0.6f;
	private float _blue 	= 0.15f;
	
	Gameplay _gameplay = new Gameplay();
	
	private float[] _lightAmbient = {0.5f, 0.5f, 0.5f, 1.0f};
	private float[] _lightDiffuse = {1.0f, 1.0f, 1.0f, 1.0f};
	private float[] _lightPosition = {0.0f, 0.0f, 2.0f, 1.0f};
	
	private FloatBuffer _lightAmbientBuffer;
	private FloatBuffer _lightDiffuseBuffer;
	private FloatBuffer _lightPositionBuffer;
	
	/**
	 * constructor
	 */
	public SynapseRenderer() {
	}
		
	/**
	 * initLights
	 */
	private void initLights() {
		GL10 gl = Global.gl;
		
		// allocate all the buffers etc.
		ByteBuffer byteBuf = ByteBuffer.allocateDirect(_lightAmbient.length * 4);
		byteBuf.order(ByteOrder.nativeOrder());
		_lightAmbientBuffer = byteBuf.asFloatBuffer();
		_lightAmbientBuffer.put(_lightAmbient);
		_lightAmbientBuffer.position(0);
		
		byteBuf = ByteBuffer.allocateDirect(_lightDiffuse.length * 4);
		byteBuf.order(ByteOrder.nativeOrder());
		_lightDiffuseBuffer = byteBuf.asFloatBuffer();
		_lightDiffuseBuffer.put(_lightDiffuse);
		_lightDiffuseBuffer.position(0);
		
		byteBuf = ByteBuffer.allocateDirect(_lightPosition.length * 4);
		byteBuf.order(ByteOrder.nativeOrder());
		_lightPositionBuffer = byteBuf.asFloatBuffer();
		_lightPositionBuffer.put(_lightPosition);
		_lightPositionBuffer.position(0);
		
		// setup light parameters
		gl.glLightfv(GL10.GL_LIGHT0, GL10.GL_AMBIENT, _lightAmbientBuffer);
		gl.glLightfv(GL10.GL_LIGHT0, GL10.GL_DIFFUSE, _lightDiffuseBuffer);
		gl.glLightfv(GL10.GL_LIGHT0, GL10.GL_POSITION, _lightPositionBuffer);
		gl.glEnable(GL10.GL_LIGHT0);
	}
	
	/**
	 * setClearColor
	 * @param r
	 * @param g
	 * @param b
	 */
	public void setClearColor(float r, float g, float b) {
		_red 	= r;
		_green 	= g;
		_blue 	= b;
	}
	
	/**
	 * onSurfaceCreated
	 */
	@Override
	public void onSurfaceCreated(GL10 gl, EGLConfig config) {
		Global.gl = gl;
		
		gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
		gl.glEnableClientState(GL10.GL_COLOR_ARRAY);
		gl.glEnableClientState(GL10.GL_TEXTURE_COORD_ARRAY);
		
		gl.glEnable(GL10.GL_TEXTURE_2D);
		gl.glShadeModel(GL10.GL_SMOOTH);
		gl.glEnable(GL10.GL_CULL_FACE);
		gl.glClearDepthf(1.0f);
		gl.glEnable(GL10.GL_DEPTH_TEST);
		gl.glDepthFunc(GL10.GL_LEQUAL);
		
		gl.glHint(GL10.GL_PERSPECTIVE_CORRECTION_HINT, GL10.GL_NICEST);
		
		initLights();
		
		_gameplay.init();
	}
	
	/**
	 * onSurfaceChanged
	 */
	@Override
	public void onSurfaceChanged(GL10 gl, int w, int h) {
		Global.gl = gl;
		
		gl.glViewport(0, 0, w, h);
		
		gl.glMatrixMode(GL10.GL_PROJECTION);
		gl.glLoadIdentity();
		
		GLU.gluPerspective(gl, 45.0f,(float)w / (float)h, 0.1f, 100.0f);
		
		gl.glMatrixMode(GL10.GL_MODELVIEW);
		gl.glLoadIdentity();
	}
	
	/**
	 * onDrawFrame
	 */
	@Override
	public void onDrawFrame(GL10 gl) {
		// store a reference to the gl context
		// this is so we don't have to pass it to every function
		Global.gl = gl;
		
		// clear the screen and buffers and reset the matrix
		gl.glClearColor(_red, _green, _blue, 1.0f);
		gl.glLoadIdentity();
		gl.glClear(GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT);
		
		// update gameplay which will invoke scenemanager updates
		_gameplay.update();
	}
}
