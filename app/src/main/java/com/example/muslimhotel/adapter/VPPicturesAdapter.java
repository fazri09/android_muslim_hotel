package com.example.muslimhotel.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.example.muslimhotel.R;

public class VPPicturesAdapter extends PagerAdapter {

    private Context thiscontext;
    private int[] imageids = new int[]{R.drawable.dindet,R.drawable.beachdet,R.drawable.beddet,R.drawable.stairdet};

    public VPPicturesAdapter(Context context){
        thiscontext=context;
    }

    @Override
    public int getCount() {
        return imageids.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view==object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        ImageView imageView = new ImageView(thiscontext);
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        imageView.setImageResource(imageids[position]);

        container.addView(imageView,0);
        return imageView;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((ImageView)object);
    }
}
