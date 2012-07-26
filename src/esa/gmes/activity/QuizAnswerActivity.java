package esa.gmes.activity;

import esa.gmes.R;
import esa.gmes.layout.AchievementToast;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class QuizAnswerActivity extends Activity {
	
	boolean isFBActive;
	boolean isTWActive;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.quiz_answer);
		
		isFBActive = false;
		isTWActive = false;
		
		Button btnOk = (Button) findViewById(R.id.btn_ok);
		btnOk.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				finish();
			}
		});
		
		final Button btnFB = (Button) findViewById(R.id.img_facebook);
		final Button btnTW = (Button) findViewById(R.id.img_twitter);
		
		btnFB.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				isFBActive = !isFBActive;
				btnFB.setSelected(isFBActive);
			}
		});
		
		btnTW.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				isTWActive = !isTWActive;
				btnTW.setSelected(isTWActive);
			}
		});
		
		AchievementToast.buildToast(this);
	}
}
