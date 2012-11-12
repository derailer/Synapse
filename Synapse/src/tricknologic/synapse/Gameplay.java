package tricknologic.synapse;

public class Gameplay {
	private Entity_Paddle _playerPaddle = new Entity_Paddle();
	private Entity_Paddle _enemyPaddle	= new Entity_Paddle();
	private Entity_Ball _ball 			= new Entity_Ball();
	
	/**
	 * constructor
	 */
	public Gameplay() {
	}
	
	/**
	 * init()
	 */
	public void init() {
		// create the player paddle
		_playerPaddle.init();
		_playerPaddle.setPosition(-4.0f, 0.0f, -20.0f);
		_playerPaddle.setAngles(20.0f, 0.0f, 0.0f);
		
		// create the opposition paddle
		_enemyPaddle.init();
		_enemyPaddle.setPosition(4.0f, 0.0f, -20.0f);
		_enemyPaddle.setAngles(20.0f, 0.0f, 0.0f);
		
		// create the game ball
		_ball.init();
		_ball.setPosition(0.0f, 0.0f, -20.0f);
		_ball.setAngles(20.0f, 0.0f, 0.0f);
		
		// add entities to the scene
		SceneManager.getInstance().addEntity(_playerPaddle);
		SceneManager.getInstance().addEntity(_enemyPaddle);
		SceneManager.getInstance().addEntity(_ball);
	}
	
	/**
	 * update()
	 */
	public void update() {
		// update the scene objects to be rendered etc.
		SceneManager.getInstance().update();
	}
}
