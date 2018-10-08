package linhui.skysoft.com.sensortestdemo;

import android.app.Activity;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import java.util.List;

import static android.content.Context.SENSOR_SERVICE;

public class MainActivity extends Activity implements SensorEventListener {

    private final String TAG = "tiny";
    SensorManager mSensorManager;
    Sensor accelerometer, magnetic,stepCounter;

    private float[] accelerometerValues = new float[3];
    private float[] magneticValues = new float[3];

    float mSteps = 0;

    TextView tv ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(TAG,"oncreate done, call setcontentview");
        setContentView(R.layout.activity_main);
        Log.i(TAG,"setcontentview done");

        mSensorManager = (SensorManager)getSystemService(SENSOR_SERVICE);

        List<Sensor> sensorList = mSensorManager.getSensorList(Sensor.TYPE_ALL);
        Log.i(TAG,"Sensor size:"+sensorList.size());
        for (Sensor sensor : sensorList) {
            Log.i(TAG,"Supported Sensor: "+sensor.getName());
        }


        // deprecated
        // mOritentation = mSensorManager.getDefaultSensor(Sensor.TYPE_ORIENTATION);

        //accelerometer = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
       // magnetic = mSensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);
        //calculateOrientation();

        stepCounter = mSensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER);
        if(stepCounter != null){
            mSensorManager.registerListener(this,stepCounter,1000000);
        }
        else{
            Log.e(TAG,"no step counter sensor found");
        }
    }

    public void calculateOrientation()
    {
        float[] values = new float[3];
        float[] R = new float[9];
        SensorManager.getRotationMatrix(R,null,accelerometerValues,magneticValues);
        SensorManager.getOrientation(R,values);
        Log.i(TAG,"  Orientation: X:"+values[0]+"-Y:"+values[1]+"-Z:"+values[2]);
    }

    protected void onPause() {
        Log.d(TAG,"onPause");
        //mSensorManager.unregisterListener(this);
        super.onPause();
    }

    protected void onDestroy(){
        Log.i(TAG,"ondestroy");
        super.onDestroy();
    }

    protected void onResume() {
        Log.i(TAG,"onResume");
        //mSensorManager.registerListener(this, accelerometer,4000000);
       // mSensorManager.registerListener(this, magnetic, SensorManager.SENSOR_DELAY_UI);
        super.onResume();
    }

    protected void onStop(){
        Log.i(TAG,"onstop");
        super.onStop();
    }

    protected void onRestart(){
        Log.i(TAG,"onrestart");
        super.onRestart();
    }

    protected void onStart()
    {
        Log.i(TAG,"onstart");
        super.onStart();
    }

    public void onContentChanged(){
        Log.i(TAG,"oncontent changed");
        tv = (TextView)findViewById(R.id.tv_step);
        //super.onContentChanged();
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
       /* if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            Log.i(TAG,"   accelerometer     .......");
            accelerometerValues = event.values;
        }
        if (event.sensor.getType() == Sensor.TYPE_MAGNETIC_FIELD) {
            magneticValues = event.values;
        }*/
       Log.i(TAG,"Detected step changes:"+event.values[0]);
       mSteps = event.values[0];
       tv.setText("您今天走了"+String.valueOf((int)mSteps)+"步");
        //calculateOrientation();
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
       // Log.i(TAG,"onAccuracyChanged");
    }
}

