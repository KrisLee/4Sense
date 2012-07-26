package esa.gmes.layout;

import esa.gmes.R;
import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

public class AchievementToast {

	public static void buildToast(Context context) {
		LayoutInflater inflater = LayoutInflater.from(context);
		View toastRoot = inflater.inflate(R.layout.custom_toast, null);
		 
		Toast toast = new Toast(context);
		 
		toast.setView(toastRoot);
		toast.setGravity(Gravity.CENTER_HORIZONTAL | Gravity.BOTTOM,
		        0, 50);
		toast.setDuration(Toast.LENGTH_LONG);
		toast.show();
	}
}
