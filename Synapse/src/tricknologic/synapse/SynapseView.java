package tricknologic.synapse;

import android.content.Context;
import android.opengl.GLSurfaceView;
import android.view.MotionEvent;

public class SynapseView extends GLSurfaceView {
	
	private SynapseRenderer _renderer;
	
	public SynapseView(Context context) {
		super(context);
		_renderer = new SynapseRenderer();
		setRenderer(_renderer);
		
		setFocusableInTouchMode(true);
	}
	
	public boolean onTouchEvent(final MotionEvent event) {
		queueEvent(new Runnable() {
			public void run() {
				//_renderer.setColor(event.getX() / getWidth(), event.getY() / getHeight(), 1.0f);
				//_renderer.setAngle(event.getY(), event.getX());
			}
		});
		return true;
	}
}