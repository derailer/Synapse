package tricknologic.synapse;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.ShortBuffer;

import javax.microedition.khronos.opengles.GL10;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.opengl.GLUtils;

public class Mesh {
	
	// mesh data
	private short[] _indices;
	private float[] _vertices;
	private float[] _texCoords;
	private float[] _colors;
	private ShortBuffer _indexBuffer;
	private FloatBuffer _vertexBuffer;
	private FloatBuffer _texCoordBuffer;
	private FloatBuffer _colorBuffer;
	private int[] _textures = new int[1];
	
	/**
	 * Mesh
	 */
	public Mesh() {
	}
	
	/**
	 * load
	 * @param meshId
	 */
	public void load(int meshId) {
		//InputStream is = _context.getResources().openRawResource(meshId);
		
		
	}
	
	/**
	 * createPrimitive
	 * @param type
	 */
	public void createPrimitive(int type) {
		switch (type)
		{
		case 0:
			createPrimitive_Cube(2.0f, 2.0f, 2.0f);
			break;
		default:
			break;
		}
	}
	
	/**
	 * createCube
	 */
	public void createPrimitive_Cube(float width, float height, float depth) {
		if (width <= 0.0f || height <= 0.0f || depth <= 0.0f)
			return;
		
		float w = Math.abs(width) / 2.0f;
		float h = Math.abs(height) / 2.0f;
		float d = Math.abs(depth) / 2.0f;
		
		_vertices = new float[] {
				//Vertices according to faces
	    		-w, -h, d, //Vertex 0
	    		w, -h, d,  //v1
	    		-w, h, d,  //v2
	    		w, h, d,   //v3
	    		
	    		w, -h, d,	//...
	    		w, -h, -d,    		
	    		w, h, d,
	    		w, h, -d,
	    		
	    		w, -h, -d,
	    		-w, -h, -d,    		
	    		w, h, -d,
	    		-w, h, -d,
	    		
	    		-w, -h, -d,
	    		-w, -h, d,    		
	    		-w, h, -d,
	    		-w, h, d,
	    		
	    		-w, -h, -d,
	    		w, -h, -d,    		
	    		-w, -h, d,
	    		w, -h, d,
	    		
	    		-w, h, d,
	    		w, h, d,    		
	    		-w, h, -d,
	    		w, h, -d,
		};
		
		_texCoords = new float[] {
	    		//Mapping coordinates for the vertices
	    		0.0f, 0.0f,
	    		0.0f, 1.0f,
	    		1.0f, 0.0f,
	    		1.0f, 1.0f, 
	    		
	    		0.0f, 0.0f,
	    		0.0f, 1.0f,
	    		1.0f, 0.0f,
	    		1.0f, 1.0f,
	    		
	    		0.0f, 0.0f,
	    		0.0f, 1.0f,
	    		1.0f, 0.0f,
	    		1.0f, 1.0f,
	    		
	    		0.0f, 0.0f,
	    		0.0f, 1.0f,
	    		1.0f, 0.0f,
	    		1.0f, 1.0f,
	    		
	    		0.0f, 0.0f,
	    		0.0f, 1.0f,
	    		1.0f, 0.0f,
	    		1.0f, 1.0f,
	    		
	    		0.0f, 0.0f,
	    		0.0f, 1.0f,
	    		1.0f, 0.0f,
	    		1.0f, 1.0f,
		};
		
		_colors = new float[] {
				1.0f, 0.0f, 0.0f, 1.0f,
				0.0f, 1.0f, 0.0f, 1.0f,
				0.0f, 0.0f, 1.0f, 1.0f,
		};

		_indices = new short[] {
				//Faces definition
	    		0,1,3, 0,3,2, 			//Face front
	    		4,5,7, 4,7,6, 			//Face right
	    		8,9,11, 8,11,10, 		//... 
	    		12,13,15, 12,15,14, 	
	    		16,17,19, 16,19,18, 	
	    		20,21,23, 20,23,22,
		};

		// set the texture to use
		// for now just default to the crate texture
		loadTexture(R.drawable.crate);
		
		// put all the vertex data into our buffers
		allocateBuffers();
	}
	
	/**
	 * render
	 */
	public void render() {
		GL10 gl = Global.gl;
		
		gl.glColor4f(0.5f, 0.0f, 0.0f, 0.5f);
		
		gl.glEnable(GL10.GL_TEXTURE_2D);
		gl.glFrontFace(GL10.GL_CCW);
		gl.glBindTexture(GL10.GL_TEXTURE_2D, _textures[0]);
		
		gl.glEnable(GL10.GL_LIGHTING);
		
		// set buffer pointers and draw!
		gl.glVertexPointer(3, GL10.GL_FLOAT, 0, _vertexBuffer);
		gl.glTexCoordPointer(2, GL10.GL_FLOAT, 0, _texCoordBuffer);
		//gl.glColorPointer(4, GL10.GL_FLOAT, 0, _colorBuffer);
		gl.glDrawElements(GL10.GL_TRIANGLES, _indices.length, GL10.GL_UNSIGNED_SHORT, _indexBuffer);
	}
	
	/**
	 * loadTexture
	 * @param id
	 */
	private void loadTexture(int id) {
		GL10 gl = Global.gl;
		
		InputStream is = Global.context.getResources().openRawResource(R.drawable.crate);
		Bitmap bitmap = null;
		try {
			bitmap = BitmapFactory.decodeStream(is);
		} finally {
			try {
				is.close();
				is = null;
			} catch(IOException e) {
			}
		}
		
		gl.glGenTextures(1, _textures, 0);
		gl.glBindTexture(GL10.GL_TEXTURE_2D, _textures[0]);
		
		//Create Nearest Filtered Texture
		gl.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_MIN_FILTER, GL10.GL_NEAREST);
		gl.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_MAG_FILTER, GL10.GL_LINEAR);

		//Different possible texture parameters, e.g. GL10.GL_CLAMP_TO_EDGE
		gl.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_WRAP_S, GL10.GL_REPEAT);
		gl.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_WRAP_T, GL10.GL_REPEAT);

		GLUtils.texImage2D(GL10.GL_TEXTURE_2D, 0, bitmap, 0);
		
		bitmap.recycle();
	}
	
	/**
	 * allocateBuffers
	 */
	private void allocateBuffers() {
		ByteBuffer vbb = ByteBuffer.allocateDirect(_vertices.length * 4);
		vbb.order(ByteOrder.nativeOrder());
		_vertexBuffer = vbb.asFloatBuffer();
		
		ByteBuffer tbb = ByteBuffer.allocateDirect(_texCoords.length * 4);
		tbb.order(ByteOrder.nativeOrder());
		_texCoordBuffer = tbb.asFloatBuffer();
		
		ByteBuffer ibb = ByteBuffer.allocateDirect(_indices.length * 2);
		ibb.order(ByteOrder.nativeOrder());
		_indexBuffer = ibb.asShortBuffer();
		
		ByteBuffer cbb = ByteBuffer.allocateDirect(_colors.length * 4);
		cbb.order(ByteOrder.nativeOrder());
		_colorBuffer = cbb.asFloatBuffer();
		
		_vertexBuffer.put(_vertices);
		_vertexBuffer.position(0);
		_texCoordBuffer.put(_texCoords);
		_texCoordBuffer.position(0);
		_indexBuffer.put(_indices);
		_indexBuffer.position(0);
		_colorBuffer.put(_colors);
		_colorBuffer.position(0);			
	}
}
