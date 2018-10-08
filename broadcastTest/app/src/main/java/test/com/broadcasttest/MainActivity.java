package test.com.broadcasttest;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private static String TAG = "linhui";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        while(true){
            Log.i(TAG,"anr");
        }
    }

    BroadcastReceiver mountReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            Log.i(TAG,"onReceive, call stack ==>",new Throwable());
            if(intent.getAction().equals("android.intent.action.MEDIA_MOUNTED")){
                Toast toast = Toast.makeText(getApplicationContext(),"media mount",Toast.LENGTH_LONG);
                toast.show();
            }
        }
    };

    @Override
    protected void onResume(){
        super.onResume();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(Intent.ACTION_MEDIA_CHECKING);
        intentFilter.addAction(Intent.ACTION_MEDIA_EJECT);
        intentFilter.addAction(Intent.ACTION_MEDIA_MOUNTED);
        intentFilter.addAction(Intent.ACTION_MEDIA_UNMOUNTED);
        intentFilter.addAction(Intent.ACTION_MEDIA_REMOVED);
        intentFilter.addAction(Intent.ACTION_MEDIA_BAD_REMOVAL);
        intentFilter.addDataScheme("file");

        registerReceiver(mountReceiver,intentFilter);
    }

    @Override
    protected  void onStop(){
        super.onStop();
        unregisterReceiver(mountReceiver);
    }
}
