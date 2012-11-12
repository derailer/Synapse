package tricknologic.synapse;

public class Entity_Paddle extends Entity {
	
	/**
	 * Paddle
	 */
	public Entity_Paddle() {
		super();
	}

	/**
	 * init()
	 */
	@Override
	public void init() {
		// super.init();
		
		// for now default to creating a cube primitive
		_mesh.createPrimitive_Cube(2.0f, 2.0f, 2.0f);
	}
}
