package esa.gmes.activity;

import esa.gmes.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class QuizQuestionActivity extends Activity implements View.OnClickListener {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.quiz_question);
		
		ImageView imgAnswer1 = (ImageView) findViewById(R.id.img_answer_1);
		ImageView imgAnswer2 = (ImageView) findViewById(R.id.img_answer_2);
		ImageView imgAnswer3 = (ImageView) findViewById(R.id.img_answer_3);
		
		imgAnswer1.setOnClickListener(this);
		imgAnswer2.setOnClickListener(this);
		imgAnswer3.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		MainscreenActivity.point();
		Intent i = new Intent(this, QuizAnswerActivity.class);
		startActivity(i);
		finish();
	}
}
