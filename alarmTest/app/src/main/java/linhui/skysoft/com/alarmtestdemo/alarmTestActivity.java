package linhui.skysoft.com.alarmtestdemo;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class alarmTestActivity extends AppCompatActivity {
    AlarmManager am;
    MyBroadcastReceiver br = new MyBroadcastReceiver();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm_test);
        registerReceiver(br, new IntentFilter());

        am = (AlarmManager) getSystemService(Context.ALARM_SERVICE);

        Intent intent = new Intent(this,MyBroadcastReceiver.class);
        PendingIntent pi = PendingIntent.getBroadcast(this, 0, intent, PendingIntent.FLAG_CANCEL_CURRENT);

        //am.setRepeating(AlarmManager.RTC_WAKEUP, SystemClock.currentThreadTimeMillis()+100, 300, pi);
       // am.setExact(AlarmManager.ELAPSED_REALTIME_WAKEUP, SystemClock.elapsedRealtime(), pi);
        am.set(AlarmManager.ELAPSED_REALTIME_WAKEUP, SystemClock.elapsedRealtime()+100, pi);
        //am.set(AlarmManager.ELAPSED_REALTIME, SystemClock.elapsedRealtime()+10000,pi);
    }

/*     class MyBroadcastReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            Log.i("tiny","    broadcast recevier onreceive");
            Intent intent1 = new Intent(getApplicationContext(), MyBroadcastReceiver.class);
            PendingIntent pi = PendingIntent.getActivity(getApplicationContext(), 0, intent1, PendingIntent.FLAG_CANCEL_CURRENT);

            //am.setRepeating(AlarmManager.RTC_WAKEUP, SystemClock.currentThreadTimeMillis()+100, 300, pi);
            // am.setExact(AlarmManager.ELAPSED_REALTIME_WAKEUP, SystemClock.elapsedRealtime(), pi);
            am.setExactAndAllowWhileIdle(AlarmManager.ELAPSED_REALTIME_WAKEUP, SystemClock.elapsedRealtime()+100, pi);

            Intent intent2 = new Intent(getApplicationContext(), linhui.skysoft.com.alarmtestdemo.alarmTestPage.class);
            startActivity(intent2);
        }
    };*/

}
