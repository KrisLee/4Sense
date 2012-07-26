package esa.gmes.activity;

import java.io.File;
import java.io.IOException;

import esa.gmes.R;
import esa.gmes.logic.ImageAdapter;
import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Gallery;
import android.widget.ImageView;

public class ShareActivity extends Activity {
	private ImageView imgCamera;
	boolean isFBActive;
	boolean isTWActive;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.share);
		
		File file = (File) getIntent().getExtras().get("file");
		Bitmap photo = BitmapFactory.decodeFile(file.getAbsolutePath());
/*
		BitmapFactory.Options options = new BitmapFactory.Options();
        options.inSampleSize = 4;
		String myphoto= Environment.getExternalStorageDirectory() + "/images/gmes123.jpg";

    	Bitmap bitmap = BitmapFactory.decodeFile( myphoto, options );
    */
		imgCamera = (ImageView) findViewById(R.id.img_camera);
		imgCamera.setImageBitmap(photo);
		
		isFBActive = false;
		isTWActive = false;

		Gallery g = (Gallery) findViewById(R.id.gallery);
		g.setAdapter(new ImageAdapter(this));

		Button btnOk = (Button) findViewById(R.id.btn_ok);
		btnOk.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				
				try {
					MainscreenActivity.share("User: +userid+ Latitude: + lat+ Logtitude:+log+ Type+ type+Timestamp:+ time");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
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
	}
}
