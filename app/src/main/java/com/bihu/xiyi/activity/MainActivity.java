package com.bihu.xiyi.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bihu.xiyi.MyApplication;
import com.bihu.xiyi.R;
import com.bihu.xiyi.adapter.MainparAdaper;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnPageChange;

public class MainActivity extends FragmentActivity {
    private SharedPreferences preferences;
    @BindView(R.id.tv_wang) TextView tv_wang;
    @BindView(R.id.te_mi) EditText te_mi;
    @BindView(R.id.te_zhang) EditText te_zhang;
    @BindView(R.id.bu_deng)
    Button bu_deng;
    @BindView(R.id.tab_layout)
    TabLayout tab_layout;
    @BindView(R.id.vp_pager)
    ViewPager vp_pager;
    @BindView(R.id.activity_login) View  activity_login;
    private static final String TAG=MainActivity.class.getName();
    private RequestQueue requestQueue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        //添加下划线
        tv_wang.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG);
        //得到配置对象
        preferences=((MyApplication)getApplication()).getSharedPreferences();
        //是否第一次启动，值:false
        preferences.edit().putBoolean(MyApplication.ConfigKey.is_one_start,false).commit();

    }

    @OnClick(R.id.bu_deng)
    public void onClick(View view){
        String password=te_mi.getText().toString();
        String username=te_zhang.getText().toString();
        String params="username=%s&password=%s";
        params=String.format(params,username,password);
        String url="https://api.bmob.cn/1/login?"+params;
        //创建请求队列
        requestQueue=((MyApplication)getApplication()).getRequestQueues();

        //请求成功回调的接口
        Response.Listener<String> listener = new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
                loginsuccessInit();
                activity_login.setVisibility(View.INVISIBLE);
            }
        };
        //请求失败回调的接口
        Response.ErrorListener errorListener = new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError volleyError) {
                    if (volleyError==null)
                        return;
                String data=new String(volleyError.networkResponse.data);
                try {
                    int code=new JSONObject(data).getInt("code");
                    if(code==MyApplication.WebApi.ERRORCODE_101){//用户名或密码不正确
                        Toast.makeText(MainActivity.this,"用户名或密码不正确",Toast.LENGTH_SHORT).show();
                        return;
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                Log.d(TAG,"登陆失败："+volleyError.getMessage());
            }
        };
        //创建请求对象
        StringRequest request=new StringRequest(Request.Method.GET,url,listener,errorListener){
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String,String> headers=new HashMap<String,String>();
                headers.put("Content-Type","application/json");
                headers.put("X-Bmob-Application-Id",MyApplication.WebApi.header_X_Bmob_Application_Id);
                headers.put("X-Bmob-REST-API-Key",MyApplication.WebApi.header_X_Bmob_REST_API_Key);
                return headers;
            }
        };
        //添加请求到请求队列
        requestQueue.add(request);
    }
    @OnClick(R.id.tv_register)
    public  void register(View view){
        Intent intent=new Intent();
        intent.setClass(this,RegisterActivity.class);
        startActivityForResult(intent,0);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data!=null){
            String username=data.getStringExtra("username");
            String password=data.getStringExtra("password");
            te_zhang.setText(username);
            te_mi.setText(password);
        }
    }

    public void loginsuccessInit(){
        MainparAdaper adapter = new MainparAdaper(getSupportFragmentManager(), this);
        vp_pager.setAdapter(adapter);
        //tab_layout.setupWithViewPager(vp_pager);
        for(int i=0;i<adapter.getCount();i++){
            TabLayout.Tab tab = tab_layout.newTab().setCustomView(adapter.getTabView(i));
            if(i==0)
            tab_layout.addTab(tab,true);
            else
                tab_layout.addTab(tab,false);
        }
        tab_layout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener(){
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
               int position=tab.getPosition();
                vp_pager.setCurrentItem(position);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }
    @OnPageChange(R.id.vp_pager)
    public void onPageSecelted(int position){
        //int currentItem=vp_pager.getCurrentItem();
        tab_layout.getTabAt(position).select();
    }
}
