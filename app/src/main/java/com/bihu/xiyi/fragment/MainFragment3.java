package com.bihu.xiyi.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by luo on 2016/8/18.
 */
public class MainFragment3 extends Fragment{
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        TextView tv = new TextView(getActivity());
        tv.setText("页面3");
        tv.setTextSize(45);

        return tv;
    }
}

