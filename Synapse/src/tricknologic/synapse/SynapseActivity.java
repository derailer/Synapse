package tricknologic.synapse;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

public class SynapseActivity extends Activity {
	
	private SynapseView _synapseView;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Global.context = this;
        _synapseView = new SynapseView(this);
        setContentView(_synapseView);
        //setContentView(R.layout.main);
    }
    
    public void onAddNew(View view) {
    	System.out.println("onAddNew()");
    }
}