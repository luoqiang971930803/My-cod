package com.bihu.xiyi.cache;

import android.annotation.TargetApi;
import android.graphics.Bitmap;
import android.os.Build;
import android.util.LruCache;

import com.android.volley.toolbox.ImageLoader;

/**
 * Created by luo on 2016/8/25.
 */
@TargetApi(Build.VERSION_CODES.HONEYCOMB_MR1)
public class ImageCache implements ImageLoader.ImageCache {
   private final int size=10*1024*1024;
    LruCache<String,Bitmap> lruCache=new LruCache<String,Bitmap>(size){
        @Override
        protected int sizeOf(String key, Bitmap value) {
            return value.getRowBytes()*value.getHeight();
        }
    };
    @Override
    public Bitmap getBitmap(String s) {
        return lruCache.get(s);
    }

    @Override
    public void putBitmap(String s, Bitmap bitmap) {
        lruCache.put(s,bitmap);
    }
}
