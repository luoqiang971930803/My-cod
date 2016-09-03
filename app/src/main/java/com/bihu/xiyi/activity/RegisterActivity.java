package com.bihu.xiyi.activity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by luo on 2016/8/17.
 */
public class RegisterActivity extends Activity {
    //private MyAsy myAsy;
    private RequestQueue requestQueue;
    private Handler handler=new Handler();
    @BindView(R.id.bt_yanzhengma)
    Button bt_yanzhengma;
    @BindView(R.id.te_zhang)
    EditText te_zhang;
    @BindView(R.id.te_mi)
    EditText te_mi;
    private Runnable runnable;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        requestQueue=((MyApplication)getApplication()).getRequestQueues();
        ButterKnife.bind(this);
    }
    @OnClick(R.id.bt_yanzhengma)
   public void  onClick(View view){
        final String phoneNumber=te_zhang.getText().toString();
        if(!phoneNumber.matches("^1(3[0-9]|4[57]|5[0-35-9]|7[01678]|8[0-9])\\d{8}$")){
            Toast.makeText(this,R.string.alert_phNumber_NoPattern,Toast.LENGTH_SHORT).show();
            return;
        }
//        myAsy=new MyAsy();
//        myAsy.execute();
        daojiyi();
        String url = "https://api.bmob.cn/1/requestSmsCode";
        Response.Listener listener=new Response.Listener<String>(){
            public void onResponse(String s) {
                Log.d("bihu", s);
            }
        };
        Response.ErrorListener errorListener=new Response.ErrorListener(){
            public void onErrorResponse(VolleyError volleyError) {
                if(volleyError==null)
                    return;
                //Log.d("bihu", new String(volleyError.networkResponse.data));
            }
        };
        StringRequest request=new StringRequest(Request.Method.POST,url,listener,errorListener){
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String,String> headers=new HashMap<String,String>();
                headers.put("Content-Type","application/json");
                headers.put("X-Bmob-Application-Id",MyApplication.WebApi.header_X_Bmob_Application_Id);
                headers.put("X-Bmob-REST-API-Key",MyApplication.WebApi.header_X_Bmob_REST_API_Key);
                return headers;
            }
            @Override
            public byte[] getBody() throws AuthFailureError {
                String str = "{\"mobilePhoneNumber\": \"%s\"}";
                str=String.format(str,phoneNumber);
                return str.getBytes();
            }
        };
        requestQueue.add(request);
   }
    public  void  daojiyi(){
        runnable =new Runnable() {
              private int count=60;
               @Override
               public void run() {
                    Log.d("HAHA",""+count);
                    if (count==0){
                        //bt_yanzhengma.setText(R.string.register1_verification_code);
                        bt_yanzhengma.setEnabled(true);
                        return;
                    }
                    count--;
                    //String verification=String.format((getResources().getString(R.string.register_verification_code)),count );
                    //bt_yanzhengma.setText(verification);
                  handler.postDelayed(this,1000);
               }
          };
            bt_yanzhengma.setEnabled(false);
           handler.postDelayed(runnable,0);
    }
    @OnClick(R.id.bu_zhu)
     public void register(View view){
        Log.d("bihu", "haha----------------------111");
       final String phoneNumber=te_zhang.getText().toString();
        String mima=te_mi.getText().toString();
        String code=bt_yanzhengma.getText().toString();
//        if(phoneNumber!=null||code!=null){
//            Toast.makeText(RegisterActivity.this,R.string.not_network,Toast.LENGTH_SHORT).show();
//            return;
//        }
        String url = "https://api.bmob.cn/1/verifySmsCode/"+code;
        Response.Listener listener=new Response.Listener<String>(){
            public void onResponse(String s) {
                Log.d("bihu", s);
            }
        };
         Response.ErrorListener errorListener=new Response.ErrorListener(){
             public void onErrorResponse(VolleyError volleyError) {
                 Log.d("bihu", new String(volleyError.networkResponse.data));
             }
         };
         StringRequest request=new StringRequest(Request.Method.POST,url,listener,errorListener){
             @Override
             public Map<String, String> getHeaders() throws AuthFailureError {
                 Map<String,String> headers=new HashMap<String,String>();
                 headers.put("Content-Type","application/json");
                 headers.put("X-Bmob-Application-Id",MyApplication.WebApi.header_X_Bmob_Application_Id);
                 headers.put("X-Bmob-REST-API-Key",MyApplication.WebApi.header_X_Bmob_REST_API_Key);
                 return headers;
             }

             @Override
             public byte[] getBody() throws AuthFailureError {
                 String str = "{\"mobilePhoneNumber\": \"%s\"}";
                 str=String.format(str,phoneNumber);
                 return str.getBytes();
             }
         };
     }
    @Override
    protected void onDestroy() {
        super.onDestroy();

        if (handler != null) {
            handler.removeCallbacks(runnable);
        }
    }
    private void regsiterNetworkUser(final String username, final String password){
     String  url="";
        Response.Listener<String> listener=new Response.Listener<String>(){

            @Override
            public void onResponse(String s) {
                Intent intent=new Intent();
                intent.putExtra("username",username);
                intent.putExtra("password",password);
                setResult(0,intent);
                finish();
            }
        };
        Response.ErrorListener errorListener=new Response.ErrorListener(){

            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Toast.makeText(RegisterActivity.this,R.string.error_network,Toast.LENGTH_SHORT).show();
            }
        };
      RequestQueue requestQueue= Volley.newRequestQueue(this);
      StringRequest request=new StringRequest(Request.Method.POST,url,listener,errorListener){
          @Override
          public Map<String, String> getHeaders() throws AuthFailureError {
              Map<String,String> headers=new HashMap<String,String>();
              headers.put("Content-Type","application/json");
              headers.put("X-Bmob-Application-Id",MyApplication.WebApi.header_X_Bmob_Application_Id);
              headers.put("X-Bmob-REST-API-Key",MyApplication.WebApi.header_X_Bmob_REST_API_Key);
              return headers;
          }

          @Override
          public byte[] getBody() throws AuthFailureError {
              String body = "{\"username\":\"%s\",\"password\":\"%s\"}";
              body=String.format(body,username,password);
              return body.getBytes();
          }
      };
        requestQueue.add(request);
    }

}

