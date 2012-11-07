package tricknologic.synapse;

public class Gameplay {
	private int _NUM_ENTITIES = 3;
	private Entity[] _entity = new Entity[_NUM_ENTITIES];
	
	public Gameplay() {
	}
	
	public void init() {
		float[] spawnLocs = { 
				-4.0f, 0.0f, -20.0f,
				0.0f, 0.0f, -20.0f,
				4.0f, 0.0f, -20.0f,
		};
		for (int idx = 0; idx < _NUM_ENTITIES; idx++)
		{
			_entity[idx] = new Entity();
			_entity[idx].setPosition(spawnLocs[idx * 3], spawnLocs[idx * 3 + 1], spawnLocs[idx * 3 + 2]);
			_entity[idx].setAngles(20.0f, 0.0f, 0.0f);
			_entity[idx].init();
			SceneManager.getInstance().addEntity(_entity[idx]);
		}		
	}
	
	public void update() {
		// update the scene objects to be rendered etc.
		SceneManager.getInstance().update();
	}
}
