package com.example.myapplication.activity;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.myapplication.Adapter.SplashpagerAdapter;

/**
 * Created by luo on 2016/8/19.
 */
public class SplashActivity extends Activity implements ViewPager.OnPageChangeListener{
    private ViewPager viewpager;
    private ImageView imageView;
    private LinearLayout ll_point;
    private SplashpagerAdapter adapter ;
    private SharedPreferences preferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_activity);
        imageView= (ImageView) findViewById(R.id.vi_image);
        viewpager = (ViewPager) findViewById(R.id.view_pager);
        viewpager = (ViewPager) findViewById(R.id.view_pager);
      // (MyApplication)getApplication();
        viewpager.addOnPageChangeListener(this);
        ll_point = (LinearLayout)findViewById(R.id.ll_point);
        adapter = new SplashpagerAdapter(this);
        viewpager.setAdapter(adapter);
        initPoint();
        new MyAsyncTask().execute();
    }
    public void initPoint(){
        for (int i=0;i<adapter.getCount();i++){
            ImageView imageView=new ImageView(this);
            if (i==0){
                imageView.setImageResource(R.drawable.selected);
            }else {
                imageView.setImageResource(R.drawable.not_selected);
                int wrapContent=LinearLayout.LayoutParams.WRAP_CONTENT;
                LinearLayout.LayoutParams layoutParams=new LinearLayout.LayoutParams(wrapContent,wrapContent);
                int margin=getResources().getDimensionPixelOffset(R.dimen.activity_llpoint_marginleft);
                layoutParams.setMargins(margin,0,0,0);
                imageView.setLayoutParams(layoutParams);
            }
            ll_point.addView(imageView);
        }
    }
    public void updatepage(int position){
            for (int i=0;i<adapter.getCount();i++){
               ImageView imageView= (ImageView) ll_point.getChildAt(i);
                imageView.setImageResource(R.drawable.not_selected);
            }
        ImageView imageView= (ImageView) ll_point.getChildAt(position);
        imageView.setImageResource(R.drawable.selected);
    }
    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        updatepage( position);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    class MyAsyncTask extends AsyncTask<Void,Void,Void>{

        @Override
        protected Void doInBackground(Void... voids) {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            imageView.setVisibility(View.INVISIBLE);
        }
    }
}
