package com.example.gjwls.indoorapi;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.indooratlas.android.sdk.IALocation;
import com.indooratlas.android.sdk.IALocationListener;
import com.indooratlas.android.sdk.IALocationManager;
import com.indooratlas.android.sdk.IALocationRequest;

public class MainActivity extends AppCompatActivity {

    IALocationManager mLocationManager;

    IALocationListener mLocationListener = new IALocationListener() {
        @Override
        public void onLocationChanged(IALocation iaLocation) {
            TextView txtLoc = (TextView) findViewById(R.id.textView);
            Log.e(this.getClass().getName(),String.valueOf("---------------------------------"+iaLocation.getLatitude() +","+iaLocation.getLongitude()));
            txtLoc.setText(String.valueOf(iaLocation.getLatitude() +","+iaLocation.getLongitude()));
        }

        @Override
        public void onStatusChanged(String s, int i, Bundle bundle) {

        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mLocationManager = IALocationManager.create(this);
    }
    protected void onResume(){
        super.onResume();
        mLocationManager.requestLocationUpdates(IALocationRequest.create(),mLocationListener);
    }

    protected void onPause(){
        mLocationManager.removeLocationUpdates(mLocationListener);
        super.onPause();
    }

    protected void onDestroy(){
        mLocationManager.destroy();
        super.onDestroy();
    }
}
