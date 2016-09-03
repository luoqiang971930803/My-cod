package com.bihu.xiyi.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v4.view.PagerAdapter;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bihu.xiyi.R;
import com.bihu.xiyi.activity.MainActivity;
import com.bihu.xiyi.activity.SplashActivity;
//import com.bihu.xiyi.activity.SplashActivity;

/**
 * Created by SteveCaesar on 2016/8/7.
 */
public class YinDaoPagerAdapter extends PagerAdapter implements View.OnClickListener{
    private Context context;

    /**
     * 指定容器中显示的页面数量
     */
    public YinDaoPagerAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    /**
     * 创建项时调用@mipmap/ic_launcher
     */
    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        if(position==2){//创建第三页
           View view=View.inflate(context,R.layout.view_yindao_three,null);
            view.findViewById(R.id.tv_lijikaishi).setOnClickListener(this);
            //将view挂载到容器控件
            container.addView(view);
            return view;
        }
        ImageView imageView = new ImageView(context);
        //填充父容器
        int matchParent = ViewGroup.LayoutParams.MATCH_PARENT;
        //设置宽高
        imageView.setLayoutParams(new ViewGroup.LayoutParams(matchParent, matchParent));
        //设置图片宽高拉伸至控件宽高
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        switch (position) {
            case 0:
                imageView.setImageResource(R.drawable.welcome_one);
                break;
            case 1:
                imageView.setImageResource(R.drawable.welcome_two);
                break;
        }
        //将view挂载到容器控件
        container.addView(imageView);
        return imageView;
    }

    /**
     * 销毁项时调用
     */
    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        Log.d("bihu", "destroyItem");
        container.removeView((View) object);
    }

    @Override
    public void onClick(View v) {
        //打开首页，关闭引导页
        ((SplashActivity)context).startMainAsy();
    }
}
