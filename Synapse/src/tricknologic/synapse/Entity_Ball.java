package tricknologic.synapse;

public class Entity_Ball extends Entity {
	
	/**
	 * constructor
	 */
	public Entity_Ball() {
		
	}
	
	/**
	 * init()
	 */
	@Override
	public void init() {
		_mesh.createPrimitive_Cube(1.0f, 1.0f, 1.0f);
	}
	
	/**
	 * update()
	 */
	@Override
	public void update() {
		super.update();
	}
}
