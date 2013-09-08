package com.biscotti.prodeapp;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

public class PreloadActivity extends Activity {
	private Context context;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		context = this;
		requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, 
                                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        
        setContentView(R.layout.activity_preload);
        Button btnPlay = (Button) findViewById(R.id.playbutton);
        btnPlay.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Activity activity = (Activity)context;
				Intent intent = new Intent();
				intent.setClass(context, Boleta.class);
				activity.startActivity(intent);
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.preload, menu);
		return true;
	}

}
