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
		_velocity = new Vector(0.0f, 0.05f, 0.0f);
		_mesh.createPrimitive_Cube(1.0f, 1.0f, 1.0f);
	}
	
	/**
	 * update()
	 */
	@Override
	public void update() {
		if (_location.y > 2.0f) {
			_velocity.y = -0.05f;
			_location.y = 2.0f;
		}
		else if (_location.y < -2.0f) {
			_velocity.y = 0.05f;
			_location.y = -2.0f;
		}
		super.update();
	}
}
