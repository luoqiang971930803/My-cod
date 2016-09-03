package com.bihu.xiyi.activity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.media.MediaMetadataCompat;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bihu.xiyi.MyApplication;
import com.bihu.xiyi.R;
import com.bihu.xiyi.adapter.YinDaoPagerAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnItemClick;
import butterknife.OnPageChange;

/**
 * Created by luo on 2016/8/16.
 */
public class SplashActivity extends Activity  {
    private final String TAG = SplashActivity.class.getName();
    private SharedPreferences preferences;
    private MyAsy mAsy;
   // private ImageView iv_load;
    //实现多页分页效果，可以左右互滑
   // private ViewPager vp_Pager;
    private YinDaoPagerAdapter adapter;
    //底部圆点容器
    //private LinearLayout ll_point;
    @BindView(R.id.iv_load) ImageView iv_load;
    @BindView(R.id.vp_pager )ViewPager vp_Pager;
    @BindView(R.id.ll_point) LinearLayout ll_point;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        ButterKnife.bind(this);
        preferences = ((MyApplication) getApplication()).getSharedPreferences();
        adapter = new YinDaoPagerAdapter(this);

        //iv_load = (ImageView) findViewById(R.id.iv_load);
        //vp_Pager = (ViewPager) findViewById(R.id.vp_pager);
        //添加viewpager页面改变监听器
        //vp_Pager.addOnPageChangeListener(this);
        //ll_point = (LinearLayout) findViewById(R.id.ll_point);
        initPoint();

        //设置分页适配器，用来给viewpager挂载view
        vp_Pager.setAdapter(adapter);
        mAsy = new MyAsy();
        mAsy.execute();
    }

    private void initPoint() {
        for (int i = 0; i < adapter.getCount(); i++) {
            ImageView imageView = new ImageView(this);
            if (i == 0) {
                imageView.setImageResource(R.drawable.selected);
            } else {
                LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                //通过key得到配置的值，该值单位为px(也就是该函会将dp转换为px)
                int marginLeft = getResources().getDimensionPixelOffset(R.dimen.point_margin_left);
                Log.d("bihu", "marginLeft:" + marginLeft);
                //设置左外边距
                lp.setMargins(marginLeft, 0, 0, 0);
                //设置布局属性
                imageView.setLayoutParams(lp);
                //设置没选中状态的图片
                imageView.setImageResource(R.drawable.not_selected);
            }
            ll_point.addView(imageView);
        }
    }

    /**
     * 更新圆点效果
     */
    private void updatePoint(int position) {
        for (int i = 0; i < ll_point.getChildCount(); i++) {
            ImageView imageView = (ImageView) ll_point.getChildAt(i);
            imageView.setImageResource(R.drawable.not_selected);
        }
        ImageView imageView = (ImageView) ll_point.getChildAt(position);
        imageView.setImageResource(R.drawable.selected);
    }


    @OnPageChange(R.id.vp_pager)
    public void onPageSelected(int position) {
        updatePoint(position);
    }


    private class MyAsy extends AsyncTask<Void, Void, Void> {
        @Override
        protected Void doInBackground(Void... params) {
            //1.初始化资源
            //2.检查更新
            try {
                Thread.sleep(3000);
            } catch (Exception ex) {
                Log.d(TAG, ex.getMessage());
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            //打开主界面
//            Intent intent=new Intent();
//            intent.setClass(SplashActivity.this,MainActivity.class);
//            startActivity(intent);
            //干掉splash页
//            SplashActivity.this.finish();
            //读取是否第一次启动的配置
            boolean isOne = preferences.getBoolean(MyApplication.ConfigKey.is_one_start, true);
            if (isOne) {
                //隐藏imageview
                iv_load.setVisibility(View.INVISIBLE);
            } else {
                startMainAsy();
            }
            //iv_load.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //取消异步任务
        mAsy.cancel(true);
        //取消页面改变监听器

    }

    /**
     * 打开首页，关闭引导页
     */
    public void startMainAsy() {
        Intent intent = new Intent();
        intent.setClass(this, MainActivity.class);
        startActivity(intent);
        //关闭引导页
        finish();
    }


}

