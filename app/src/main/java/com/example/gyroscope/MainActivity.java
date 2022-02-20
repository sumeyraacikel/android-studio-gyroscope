package com.example.gyroscope;

import androidx.appcompat.app.AppCompatActivity;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    private SensorManager sensorManager;
    private Sensor gyroscopeSensor;
    private SensorEventListener gyroscopeEventListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        gyroscopeSensor = sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);

        if( gyroscopeSensor == null){

            Toast.makeText(this, "the device no gyroscope", Toast.LENGTH_SHORT).show();
            finish();

        }

        gyroscopeEventListener = new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent sensorEvent) {
            if (sensorEvent.values[2]> 0.5f){
                getWindow().getDecorView().setBackgroundColor(Color.BLUE);
            } else if (sensorEvent.values[2] < -0.5f){
                getWindow().getDecorView().setBackgroundColor(Color.YELLOW);
            }
            }
            @Override
            public void onAccuracyChanged(Sensor sensor, int accuracy) {
            }
        };
    }

    @Override
    public void onResume(){
        super.onResume();
        sensorManager.registerListener(gyroscopeEventListener, gyroscopeSensor, sensorManager.SENSOR_DELAY_FASTEST);
    }
    @Override
    public void onPause(){
        super.onPause();
        sensorManager.unregisterListener(gyroscopeEventListener);
    }

}