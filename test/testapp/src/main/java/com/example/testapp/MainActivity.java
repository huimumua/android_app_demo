package com.example.testapp;

import android.Manifest;
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import java.io.File;
import android.os.Environment;
import android.util.Log;
import android.widget.Toast;


import java.io.FileNotFoundException;
import java.io.FileOutputStream;

public class MainActivity extends AppCompatActivity {

    static final String TAG = "tiny";

    public static final int REQUEST_EXTERNAL_PERMISSION_CODE = 666;

    ////////////////////////////////////////////////////////////

    private void initBroadcast() {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(Intent.ACTION_MEDIA_MOUNTED);
        intentFilter.addAction(Intent.ACTION_MEDIA_UNMOUNTED);
        intentFilter.addAction(Intent.ACTION_MEDIA_REMOVED);
        intentFilter.addAction(Intent.ACTION_MEDIA_BAD_REMOVAL);
        intentFilter.addAction(Intent.ACTION_MEDIA_EJECT);
        intentFilter.addAction(Intent.ACTION_MEDIA_CHECKING);
        intentFilter.addDataScheme("file");
        registerReceiver(broadcastRec, intentFilter);//注册监听函数
    }

    private final BroadcastReceiver broadcastRec = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            Log.i(TAG, "received broadcast ==> "+action);
            if (action.equals("android.intent.action.MEDIA_MOUNTED")) {
                Log.i(TAG, "BroadcastReceiver = MEDIA_MOUNTED");
            } else if(action.equals("android.intent.action.MEDIA_UNMOUNTED")) {
                Log.i(TAG, "BroadcastReceiver = ACTION_MEDIA_UNMOUNTED");
            }else if(action.equals("android.intent.action.MEDIA_BAD_REMOVAL")) {
                Log.i(TAG, "BroadcastReceiver = MEDIA_BAD_REMOVAL");
            }else if(action.equals("android.intent.action.MEDIA_EJECT")) {
                Log.i(TAG, "BroadcastReceiver = MEDIA_EJECT");
            }else if(action.equals("android.intent.action.MEDIA_REMOVED")) {
                Log.i(TAG, "BroadcastReceiver = ACTION_MEDIA_REMOVED");
            }else{
                Log.i(TAG,"no action compared in receiver....");
            }
        }
    };
    ///////////////////////////////////////////////////////////

    public static final String[] PERMISSIONS_EXTERNAL_STORAGE = {
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
    };

    public boolean checkExternalStoragePermission(Activity activity) {
        if (android.os.Build.VERSION.SDK_INT < android.os.Build.VERSION_CODES.JELLY_BEAN) {
            return true;
        }

        int readStoragePermissionState = ContextCompat.checkSelfPermission(activity, Manifest.permission.READ_EXTERNAL_STORAGE);
        int writeStoragePermissionState = ContextCompat.checkSelfPermission(activity, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        boolean externalStoragePermissionGranted = readStoragePermissionState == PackageManager.PERMISSION_GRANTED &&
                writeStoragePermissionState == PackageManager.PERMISSION_GRANTED;
        if (!externalStoragePermissionGranted) {
            requestPermissions(PERMISSIONS_EXTERNAL_STORAGE, REQUEST_EXTERNAL_PERMISSION_CODE);
        }

        return externalStoragePermissionGranted;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            if (requestCode == REQUEST_EXTERNAL_PERMISSION_CODE) {
                if (checkExternalStoragePermission(MainActivity.this)) {
                    // Continue with your action after permission request succeed
                }
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Log.i(TAG, "call stack ==>", new Throwable());
        setContentView(R.layout.activity_main);

        initBroadcast();
        /*checkExternalStoragePermission(MainActivity.this);

        String en = Environment.getExternalStorageState();
        //获取SDCard状态,如果SDCard插入了手机且为非写保护状态
        if (en.equals(Environment.MEDIA_MOUNTED)) {
            try {
                Log.i(TAG,"save To sdcard start...."+en);
                writeSDcard("aaa");
                Log.i(TAG,"save to sdcard done...");
                Toast.makeText(getApplicationContext(), "save successfully!", Toast.LENGTH_SHORT).show();
            } catch (Exception e) {
                Toast.makeText(getApplicationContext(), "save failed.....", Toast.LENGTH_SHORT).show();
            }
        } else {
            Log.i(TAG,"sdcard not ready...");
            //提示用户SDCard不存在或者为写保护状态
            Toast.makeText(getApplicationContext(), "SDCard不存在或者为写保护状态", Toast.LENGTH_SHORT).show();
        }*/
    }

  private void writeSDcard(String str) {
      try {
          // 判断是否存在SD卡
          if (Environment.getExternalStorageState().equals(
                  Environment.MEDIA_MOUNTED)) {

              Log.i(TAG,"sd card mounted.");
              // get SDcard path
              File sdDire = Environment.getExternalStorageDirectory();
              Log.i(TAG,"sdcard path:"+sdDire.toString()+" path="+sdDire.getCanonicalPath());
              try {
                  FileOutputStream outFileStream = new FileOutputStream(
                          sdDire.getCanonicalPath() + "/test.txt");
                  Log.i(TAG,"can not write data to file");
                  outFileStream.write(str.getBytes());
                  outFileStream.close();
                  Toast.makeText(this, "The data save to text.txt successfully!", Toast.LENGTH_LONG)
                          .show();
              } catch (FileNotFoundException e){
                  e.printStackTrace();
              }
          }
      } catch (Exception e) {
          e.printStackTrace();
      }
  }


}
