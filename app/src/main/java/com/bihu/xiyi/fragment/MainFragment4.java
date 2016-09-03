package com.bihu.xiyi.fragment;

import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.bihu.xiyi.R;
import com.bihu.xiyi.view.SettingItemView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by luo on 2016/8/18.
 */
public class MainFragment4 extends Fragment{


    private View viewPopup;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_setting,null);
        ButterKnife.bind(this,view);
        return view;
    }
   @OnClick(R.id.popup_item)
    public void onClick(View view){
       //Toast.makeText(getContext(),"QWE",Toast.LENGTH_LONG).show();
        viewPopup =View.inflate(getContext(),R.layout.popupwin_share,null);
        PopupWindow popupWindow=new PopupWindow(viewPopup, WindowManager.LayoutParams.MATCH_PARENT,WindowManager.LayoutParams.WRAP_CONTENT,true);
        popupWindow.setBackgroundDrawable(new BitmapDrawable());
        popupWindow.setAnimationStyle(R.style.mypopupWindow_anim);
        popupWindow.showAtLocation(getView(),Gravity.BOTTOM,0,0);
    }
}

