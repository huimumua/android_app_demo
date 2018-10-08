package tiny.com.sensortest;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity implements SensorEventListener {

    private final String TAG = "tiny";
    SensorManager mSensorManager;
    Sensor accelerometer, magnetic;

    private float[] accelerometerValues = new float[3];
    private float[] magneticValues = new float[3];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mSensorManager = (SensorManager)getSystemService(SENSOR_SERVICE);
        // deprecated
        // mOritentation = mSensorManager.getDefaultSensor(Sensor.TYPE_ORIENTATION);

        // 初始化加速度传感器
        accelerometer = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        // 初始化地磁场传感器
        magnetic = mSensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);
        calculateOrientation();
    }

    public void calculateOrientation()
    {
        float[] values = new float[3];
        float[] R = new float[9];
        SensorManager.getRotationMatrix(R,null,accelerometerValues,magneticValues);
        SensorManager.getOrientation(R,values);
        Log.i(TAG,"Orientation: X:"+values[0]+"-Y:"+values[1]+"-Z:"+values[2]);
    }


    protected void onPause() {
        super.onPause();
        mSensorManager.unregisterListener(this);
    }

    protected void onResume() {
        Log.i(TAG,"onResume");
        super.onResume();
        mSensorManager.registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_NORMAL);
        mSensorManager.registerListener(this, magnetic, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        Log.i(TAG,"call stack==>",new Throwable());
        if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            accelerometerValues = event.values;
        }
        if (event.sensor.getType() == Sensor.TYPE_MAGNETIC_FIELD) {
            magneticValues = event.values;
        }
        calculateOrientation();
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        Log.i(TAG,"onAccuracyChanged");

    }
}
