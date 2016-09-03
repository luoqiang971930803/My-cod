package com.bihu.xiyi.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.bihu.xiyi.R;
import com.bihu.xiyi.fragment.MainFragment1;
import com.bihu.xiyi.fragment.MainFragment2;
import com.bihu.xiyi.fragment.MainFragment3;
import com.bihu.xiyi.fragment.MainFragment4;

import java.util.ArrayList;



/**
 * Created by luo on 2016/8/18.
 */
public class MainparAdaper extends FragmentPagerAdapter {
    private ArrayList<Fragment> fragment;
    private ArrayList<String> title;
    private Context context;
    public MainparAdaper(FragmentManager fm, Context context) {
        super(fm);
        this.context=context;
        fragment=new ArrayList<Fragment>();
        title=new ArrayList<String>();
        fragment.add(new MainFragment1());
        fragment.add(new MainFragment2());
        fragment.add(new MainFragment3());
        fragment.add(new MainFragment4());
        title.add("首页");
        title.add("洗衣机");
        title.add("充值");
        title.add("我的");
    }

    @Override
    public Fragment getItem(int position) {
        return fragment.get(position);
    }

    @Override
    public int getCount() {

        return 4;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return title.get(position);
    }
    public View getTabView(int position){
        TextView textView=new TextView(context);
        textView.setText(getPageTitle(position));
        textView.setGravity(Gravity.CENTER);
        Drawable drawble=null;
        switch (position){
            case 0:
         drawble = context.getResources().getDrawable(R.drawable.selector_navigation_home);
                break;
            case 1:
                drawble = context.getResources().getDrawable(R.drawable.selector_navigation_washer);
                break;
            case 2:
                drawble = context.getResources().getDrawable(R.drawable.selector_navigation_recharge);
                break;
            case 3:
                drawble = context.getResources().getDrawable(R.drawable.selector_navigation_my);
                break;
        }
        textView.setCompoundDrawablesWithIntrinsicBounds(null,drawble,null,null);
        return textView;
    }
}
