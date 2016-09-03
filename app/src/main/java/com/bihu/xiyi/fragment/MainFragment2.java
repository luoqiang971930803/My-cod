package com.bihu.xiyi.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.bihu.xiyi.MyApplication;
import com.bihu.xiyi.R;
import com.bihu.xiyi.adapter.XiYiJiAdapter;
import com.bihu.xiyi.entity.XiYiJi;
import com.bihu.xiyi.http.XiYiJiRequest;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by luo on 2016/8/18.
 */
public class MainFragment2 extends Fragment implements SwipeRefreshLayout.OnRefreshListener{
    private ImageLoader imageLoader;
    @BindView(R.id.list_view) ListView listView;
    @BindView(R.id.swipe_refersh)
    SwipeRefreshLayout swipeRefresh;
    private XiYiJiRequest xiYiJiRequest;
    private  XiYiJiAdapter Adapter;
    private int page=1;
    private View view;
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (view==null) {
            view = inflater.inflate(R.layout.fragment_xiyiji, null);
            ButterKnife.bind(this, view);

            RequestQueue requestQueues = ((MyApplication) (getActivity().getApplication())).getRequestQueues();
            imageLoader = ((MyApplication) (getActivity().getApplication())).getImageLoader();
            xiYiJiRequest = new XiYiJiRequest(requestQueues);
            swipeRefresh.setOnRefreshListener(MainFragment2.this);
            Adapter = new XiYiJiAdapter(new ArrayList(), getContext(), imageLoader);
            listView.setAdapter(Adapter);
            getData();
        }
        return view;
    }

    @Override
    public void onRefresh() {
        Log.d("","bihu");
        page++;
        getData();
    }
    public void getData(){

        xiYiJiRequest.setRequestQueue(2,page,new XiYiJiRequest.RerequestListener(){
            public  void success(ArrayList<XiYiJi> list){
                    Adapter.add(list);
                    swipeRefresh.setRefreshing(false);
            }
            public  void networkError(VolleyError volleyError){

            }
        });
    }
}

