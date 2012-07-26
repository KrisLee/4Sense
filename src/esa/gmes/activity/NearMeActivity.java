package esa.gmes.activity;

import esa.gmes.R;
import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;

public class NearMeActivity extends Activity {
	private int viewStatus;
	
	private Drawable originalImg;
	private Drawable pressedImg;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.near_me);
		
		originalImg = getResources().getDrawable(R.drawable.gallery_1);
		pressedImg = getResources().getDrawable(R.drawable.gallery_1_pressed);
		
		viewStatus = View.INVISIBLE;
		
		final ImageView imgFire = (ImageView) findViewById(R.id.img_fire);
		imgFire.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				viewStatus = (viewStatus == View.VISIBLE ? View.INVISIBLE : View.VISIBLE);
				imgFire.setImageDrawable((viewStatus == View.VISIBLE ? pressedImg : originalImg));
				findViewById(R.id.rl_window).setVisibility(viewStatus);
			}
		});
	}
}
