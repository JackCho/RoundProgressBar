package com.example.roundprogressbar;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.example.circlepregress.R;

public class MainActivity extends Activity {
	private RoundProgressBar mRoundProgressBar1, mRoundProgressBar2 ,mRoundProgressBar3, mRoundProgressBar4, mRoundProgressBar5;
	private int progress = 0;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_cricle_progress);
		
		mRoundProgressBar1 = (RoundProgressBar) findViewById(R.id.roundProgressBar1);
		mRoundProgressBar2 = (RoundProgressBar) findViewById(R.id.roundProgressBar2);
		mRoundProgressBar3 = (RoundProgressBar) findViewById(R.id.roundProgressBar3);
		mRoundProgressBar4 = (RoundProgressBar) findViewById(R.id.roundProgressBar4);
		mRoundProgressBar5 = (RoundProgressBar) findViewById(R.id.roundProgressBar5);
		
		((Button)findViewById(R.id.button1)).setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if(progress <= 100){
					progress += 10;
					mRoundProgressBar1.setProgress(progress);
					mRoundProgressBar2.setProgress(progress);
					mRoundProgressBar3.setProgress(progress);
					mRoundProgressBar4.setProgress(progress);
					mRoundProgressBar5.setProgress(progress);
				}
			}
		});
	}
}
