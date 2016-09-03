package com.bihu.xiyi.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.bihu.xiyi.R;
import com.bihu.xiyi.activity.CameraActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by luo on 2016/8/18.
 */
public class MainFragment1 extends Fragment{
    private  View view;
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.fragment_home,null);
        ButterKnife.bind(this,view);
        return view;
    }
    @OnClick(R.id.tv_view1)
    public void onclick(View view){
        if (checkCameraHardware(getContext())){
            Intent intent=new Intent();
            intent.setClass(getActivity(),CameraActivity.class);
            startActivity(intent);
        }else {
            Toast.makeText(getContext(),"没有摄像头",Toast.LENGTH_LONG).show();
        }
    }
    /** Check if this device has a camera */
    private boolean checkCameraHardware(Context context) {
        if (context.getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA)){
            // this device has a camera
            return true;
        } else {
            // no camera on this device
            return false;
        }
    }
}

