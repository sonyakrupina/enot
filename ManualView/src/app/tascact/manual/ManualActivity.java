package app.tascact.manual;

import android.app.Activity;
import android.os.Bundle;

public class ManualActivity extends Activity {
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(new ManualView(this));
	}
}