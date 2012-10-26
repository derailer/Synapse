package tricknologic.synapse;

import java.util.ArrayList;

/**
 * SceneManager singleton
 * @author Patrick
 *
 */
public class SceneManager {
	/**
	 * singleton member and accessor
	 */
	private static SceneManager _instance = null;
	public static SceneManager getInstance() {
		if (_instance == null)
			_instance = new SceneManager();
		return _instance;
	}
	
	// list of entities in the scene
	private ArrayList<Entity> _entityList = new ArrayList<Entity>();
	
	/**
	 * constructor
	 */
	public SceneManager() {
		_entityList.clear();
	}
	
	/**
	 * addEntity
	 * @param ent
	 */
	public boolean addEntity(Entity ent) {
		if (_entityList.contains(ent))
			return false;
		
		_entityList.add(ent);
		return true;
	}
	
	/**
	 * removeEntity
	 * @param ent
	 */
	public boolean removeEntity(Entity ent) {
		if (!_entityList.contains(ent))
			return false;
		
		_entityList.remove(ent);
		return true;
	}
	
	/**
	 * update
	 */
	public void update() {
		for (Entity ent : _entityList) {
			ent.update();
		}
	}
	
	/**
	 * destroyAll
	 */
	public void destroyAll() {
		for (Entity ent : _entityList) {
			ent.destroy();
		}
		
		_entityList.clear();
	}
}
