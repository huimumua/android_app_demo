package linhui.skysoft.com.alarmtestdemo;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.util.Log;

/**
 * Created by test on 2018/9/17.
 */

public class alarmTestPage extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test_page);
        Log.i("tiny","alarm test page started");

    }
}
