package com.bihu.xiyi;

import android.test.ApplicationTestCase;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

/**
 * Created by luo on 2016/8/18.
 */
public class VolleyTest extends ApplicationTestCase {

    public VolleyTest(Class applicationClass) {
        super(applicationClass);
    }
    public void test1(){
        String url="http://www.baidu.com";
        RequestQueue requestQueuet=Volley.newRequestQueue(getContext());
        Response.Listener listene=new Response.Listener<String>(){

                @Override
                public void onResponse(String s) {

                }
            };
        Response.ErrorListener errorListener=new Response.ErrorListener(){

            @Override
            public void onErrorResponse(VolleyError volleyError) {

            }
        };
        StringRequest request=new StringRequest(Request.Method.GET,url,listene,errorListener);
        requestQueuet.add(request);
    }

}
