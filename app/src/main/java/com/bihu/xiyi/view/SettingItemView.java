package com.bihu.xiyi.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.bihu.xiyi.R;

/**
 * Created by luo on 2016/8/27.
 */
public class SettingItemView extends FrameLayout {
    public SettingItemView(Context context) {
        this(context,null);
    }
    private ImageView iv_left;
    private TextView tv_right_text;
    private TextView tv_left;
    private ImageView im_right;
    public SettingItemView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public SettingItemView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
       View.inflate(context, R.layout.view_setting_item,this);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.SettingItemView);
        Drawable leftDrawble = typedArray.getDrawable(R.styleable.SettingItemView_leftDrawble);
        Drawable rightDrawble = typedArray.getDrawable(R.styleable.SettingItemView_rightDrawblw);
        String lestString = typedArray.getString(R.styleable.SettingItemView_leftText);
        String rightText = typedArray.getString(R.styleable.SettingItemView_rightText);
        boolean rightTextShow = typedArray.getBoolean(R.styleable.SettingItemView_rightTextShow, false);
        leftDrawble.setBounds(0,0,leftDrawble.getIntrinsicWidth(),leftDrawble.getIntrinsicHeight());
        rightDrawble.setBounds(0,0,leftDrawble.getIntrinsicWidth(),leftDrawble.getIntrinsicHeight());

        iv_left= (ImageView) findViewById(R.id.iv_left);
        im_right= (ImageView) findViewById(R.id.iv_right);
        tv_left= (TextView) findViewById(R.id.tv_left_text);
        tv_right_text= (TextView) findViewById(R.id.tv_right_text);
        
        iv_left.setImageDrawable(leftDrawble);
        tv_left.setText(lestString);
        im_right.setImageDrawable(rightDrawble);
        tv_right_text.setText(rightText);
        if(rightTextShow)
        tv_right_text.setVisibility(View.VISIBLE);
        else
            tv_right_text.setVisibility(View.INVISIBLE);
    }
}
