package com.bihu.xiyi.activity;

import android.app.Activity;
import android.hardware.Camera;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;

import com.bihu.xiyi.R;
import com.bihu.xiyi.view.CameraPreview;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Enumeration;

import butterknife.OnClick;

/**
 * Created by luo on 2016/8/31.
 */
public class CameraActivity extends Activity {
    private Camera mCamera;
    private CameraPreview mPreview;
    private static final String TAG=CameraActivity.class.getName();
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.camera_preview);

        // Create an instance of Camera
        mCamera = getCameraInstance();
        mCamera.setDisplayOrientation(90);
        // Create our Preview view and set it as the content of our activity.
        mPreview = new CameraPreview(this, mCamera);
        FrameLayout preview = (FrameLayout) findViewById(R.id.camera_preview);
        preview.addView(mPreview);
    }
    /** A safe way to get an instance of the Camera object. */
    public static Camera getCameraInstance(){
        Camera c = null;
        try {
            c = Camera.open(); // attempt to get a Camera instance
        }
        catch (Exception e){
            // Camera is not available (in use or does not exist)
        }
        return c; // returns null if camera is unavailable
    }
    @OnClick(R.id.button_capture)
    public void onClick(View view){
        mCamera.takePicture(null, null, mPicture);

    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        mCamera.release();
    }
    private Camera.PictureCallback mPicture = new Camera.PictureCallback() {

        @Override
        public void onPictureTaken(byte[] data, Camera camera) {

            File pictureFile = new File(Environment.getDataDirectory(),"Android/data/"+"xxx.jpi");
            if (pictureFile == null){
                return;
            }
            File pictureFiles = pictureFile.getParentFile();
            if(!(pictureFiles.exists())){
                pictureFiles.mkdirs();
            }
            try {
                FileOutputStream fos = new FileOutputStream(pictureFile);
                fos.write(data);
                fos.close();
            } catch (FileNotFoundException e) {
                Log.d(TAG, "File not found: " + e.getMessage());
            } catch (IOException e) {
                Log.d(TAG, "Error accessing file: " + e.getMessage());
            }
        }
    };
}