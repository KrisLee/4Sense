package esa.gmes.logic;

import esa.gmes.R;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.ImageView;

public class ImageAdapter extends BaseAdapter {
    private Context mContext;

    private Integer[] mImageIds = {
            R.drawable.gallery_1,
            R.drawable.gallery_2,
            R.drawable.gallery_3
    };

    public ImageAdapter(Context c) {
        mContext = c;
    }

    public int getCount() {
        return mImageIds.length;
    }

    public Object getItem(int position) {
        return position;
    }

    public long getItemId(int position) {
        return position;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView i = new ImageView(mContext);

        i.setImageResource(mImageIds[position]);
        
        Gallery.LayoutParams lp = new Gallery.LayoutParams(70, 70);
        
        
        i.setLayoutParams(lp);
        //i.setScaleType(ImageView.ScaleType.FIT_XY);
        //i.setBackgroundResource(mGalleryItemBackground);

        return i;
    }
}