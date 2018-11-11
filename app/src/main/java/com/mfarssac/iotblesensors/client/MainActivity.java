/**
 * Copyright 2016 Google Inc. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.mfarssac.iotblesensors.client;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.quickstart.fcm.R;
import com.mfarssac.iotblesensors.client.model.BleSensorData;
import com.mfarssac.iotblesensors.client.model.IoTTelemetryData;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private Handler mHandler;
    private List<BleSensorData> mBleSensorData;
    private String time1, time2, temp1, temp2;
    private TextView  textViewTemp1, textViewTemp2, textViewTime1, textViewTime2, textViewTimeNow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mHandler = new Handler();
        mBleSensorData = new ArrayList<BleSensorData>();
        textViewTemp1 = (TextView) findViewById(R.id.temp1);
        textViewTemp2 = (TextView) findViewById(R.id.temp2);
        textViewTime1 = (TextView) findViewById(R.id.time1);
        textViewTime2 = (TextView) findViewById(R.id.time2);
        textViewTimeNow = (TextView)findViewById(R.id.timeNow);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ref = database.getReference("messages");

        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                int childs = (int) dataSnapshot.getChildrenCount();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        ref.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String prevChildKey) {

                IoTTelemetryData reading = dataSnapshot.getValue(IoTTelemetryData.class);

                mBleSensorData = reading.getTelemetryData();

                if (mBleSensorData!=null) {
                    if (mBleSensorData.size()>0) {
                        time1 = String.valueOf(mBleSensorData.get(0).getTimestamp_temperature());
                        temp1 = String.valueOf(mBleSensorData.get(0).getTemperature());
                    }
                    if (mBleSensorData.size()>1) {
                        time2 = String.valueOf(mBleSensorData.get(1).getTimestamp_temperature());
                        temp2 = String.valueOf(mBleSensorData.get(1).getTemperature());
                    }
                }
            }
            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String prevChildKey) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String prevChildKey) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                textViewTimeNow.setText(getTimeNow());
                updateUI();
                mHandler.postDelayed(this, 500);
            }
        };

        mHandler.post(runnable);
    }

    private void updateUI() {

        if (temp1 != null)
            textViewTemp1.setText(temp1 + "º C");

        if (temp2 != null)
            textViewTemp2.setText(temp2 + "º C");

        if (time1 != null)
            textViewTime2.setText(getTime(time1));

        if (time2 != null)
            textViewTime1.setText(getTime(time2));
    }


    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    private String getTimeNow() {
        return getTime(String.valueOf(System.currentTimeMillis()));
    }

    private String getTime(String time) {

        long measureTime = Long.valueOf(time);

        int hh = (int) ((measureTime / 1000) /3600) %24;
        int mm = (int) ((measureTime / 1000) /60) %60;
        int ss = (int) ((measureTime / 1000)) %60;

        return String.format(Locale.GERMANY, "%2d:%02d:%02d", hh, mm, ss);
    }
}
