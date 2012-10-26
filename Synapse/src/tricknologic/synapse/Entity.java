package tricknologic.synapse;

import javax.microedition.khronos.opengles.GL10;

public class Entity {
	
	// positional information
	private Vector _location = new Vector();
	private Vector _angles = new Vector();
	
	// visual representation of the entity
	private Mesh _mesh;
	
	// attributes
	private boolean _visible = true;

	/**
	 * Entity
	 */
	public Entity() {
		_mesh = new Mesh();		
	}

	/**
	 * setPosition
	 * @param x
	 * @param y
	 * @param z
	 */
	public void setPosition(float x, float y, float z) {
		_location.x = x;
		_location.y = y;
		_location.z = z;
	}
	
	/**
	 * setAngles
	 * @param x
	 * @param y
	 * @param z
	 */
	public void setAngles(float x, float y, float z) {
		_angles.x = x;
		_angles.y = y;
		_angles.z = z;
	}
	
	/**
	 * init
	 */
	public void init() {
		// for now default to creating a cube primitive
		_mesh.createPrimitive(0);
	}
	
	/**
	 * update
	 */
	public void update() {
		// render if we are marked visible
		if (_visible) {
			render();
		}
	}
	
	/**
	 * render
	 */
	public void render() {
		GL10 gl = Global.gl;
		gl.glPushMatrix();
		
		// for now apply a constant y rotation just to see
		_angles.y += 1.0f;
		
		// apply transforms
		gl.glTranslatef(_location.x, _location.y, _location.z);
		gl.glRotatef(_angles.x, 1.0f, 0.0f, 0.0f);
		gl.glRotatef(_angles.y, 0.0f, 1.0f, 0.0f);
		gl.glRotatef(_angles.z, 0.0f, 0.0f, 1.0f);
		
		// render the object
		_mesh.render();
		
		gl.glPopMatrix();
	}
	
	/**
	 * destroy
	 */
	public void destroy() {
		
	}
}
