package com.example.myapplication.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.myapplication.activity.MainActivity;

/**
 * Created by luo on 2016/8/19.
 */
public class SplashpagerAdapter extends PagerAdapter implements View.OnClickListener{
    private Context context;
    public SplashpagerAdapter(Context context){
        this.context=context;
    }
    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view==object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        ImageView imageView=new ImageView(context);
        int match=ViewGroup.LayoutParams.MATCH_PARENT;
        imageView.setLayoutParams(new ViewGroup.LayoutParams(match,match));
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        if(position==2){
            View view =  View.inflate(context, R.layout.shape_drawable, null);
            TextView textView= (TextView) ((FrameLayout)view).getChildAt(1);
            textView.setOnClickListener(this);
            container.addView(view);
            return view;
        }
        switch (position){
            case 0:
                imageView.setImageResource(R.drawable.welcome_one);
                break;
            case 1:
                imageView.setImageResource(R.drawable.welcome_two);
                break;
        }
        container.addView(imageView);
        return imageView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View)object);
    }

    @Override
    public void onClick(View view) {
        Intent intent=new Intent();
        intent.setClass(context, MainActivity.class);
        context.startActivity(intent);
    }
}
