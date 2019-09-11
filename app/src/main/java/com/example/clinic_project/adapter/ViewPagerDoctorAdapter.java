package com.example.clinic_project.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.clinic_project.R;

public class ViewPagerDoctorAdapter extends PagerAdapter {

    Context mcontext;

    public ViewPagerDoctorAdapter(Context context) {
        this.mcontext = context;
    }

    @Override
    public int getCount() {
        return images.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == ((ImageView) object);
    }

    private int [] images = new int[
            ]{R.drawable.img_hospital2, R.drawable.img_hospital2, R.drawable.img_hospital2};


    public Object instantiateItem (ViewGroup container, int position) {

        ImageView imageView = new ImageView(mcontext);
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        imageView.setImageResource(images[position]);
        ((ViewPager) container).addView(imageView, 0);
        return imageView;
    }

    public void destroyItem(ViewGroup container, int position, Object object){
        ((ViewPager) container).removeView((ImageView) object);
    }

}