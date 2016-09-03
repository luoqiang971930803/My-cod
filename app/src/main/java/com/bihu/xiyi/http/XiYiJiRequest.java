package com.bihu.xiyi.http;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.bihu.xiyi.MyApplication;
import com.bihu.xiyi.entity.XiYiJi;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by luo on 2016/8/24.
 */
public class XiYiJiRequest {
     private RequestQueue requestQueue;

     public XiYiJiRequest(RequestQueue requestQueue) {
          this.requestQueue = requestQueue;
     }
     public void setRequestQueue(int count,int page,final RerequestListener rerequestListener) {
          int skip=(page-1)*count;
          String url = "https://api.bmob.cn/1/classes/XiYiJi?limit=%s&skip=%s";
          url = String.format(url,count,skip);
          Response.Listener<java.lang.String> listener = new Response.Listener<java.lang.String>() {
               @Override
               public void onResponse(String s) {
                  /*Gson gson=new Gson();
                    XiYiJi date = gson.fr
                    ArrayList<XiYiJi> list=new ArrayList<XiYiJi>();
                    list.add(date);
                    rerequestListener.success(list);*/
                    try {
                         JSONArray jsonArray = new JSONObject(s).getJSONArray("results");
                         ArrayList<XiYiJi> list=new ArrayList<XiYiJi>();
                         for (int i=0;i<jsonArray.length();i++){
                              JSONObject jsonObject=jsonArray.getJSONObject(i);
                              String objectId = jsonObject.getString("objectId");
                              String img = jsonObject.getString("img");
                              String floor = jsonObject.getString("floor");
                              XiYiJi xiyiji=new XiYiJi(floor,objectId,img);
                              list.add(xiyiji);
                         }
                         rerequestListener.success(list);
                    } catch (JSONException e) {
                         rerequestListener.networkError(null);
                         e.printStackTrace();
                    }
               }
          };
          Response.ErrorListener errorListener = new Response.ErrorListener() {
               @Override
               public void onErrorResponse(VolleyError volleyError) {
                    rerequestListener.networkError(null);
               }
          };
          StringRequest request = new StringRequest(Request.Method.GET, url, listener, errorListener){
               @Override
               public Map<String, String> getHeaders() throws AuthFailureError {
                    Map<String, String> headers = new HashMap<String, String>();
                    headers.put("Content-Type", "application/json");
                    headers.put("X-Bmob-Application-Id", MyApplication.WebApi.header_X_Bmob_Application_Id);
                    headers.put("X-Bmob-REST-API-Key", MyApplication.WebApi.header_X_Bmob_REST_API_Key);
                    return headers;
               }
          };
          requestQueue.add(request);
     }
          public interface RerequestListener{
               public abstract void success(ArrayList<XiYiJi> list);
               public abstract void networkError(VolleyError volleyError);
          }

}

