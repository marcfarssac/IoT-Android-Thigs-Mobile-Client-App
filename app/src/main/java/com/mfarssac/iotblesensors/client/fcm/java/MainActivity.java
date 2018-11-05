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

package com.mfarssac.iotblesensors.client.fcm.java;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.quickstart.fcm.R;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private BroadcastReceiver mTempReadBroadcastReceiver;
    private boolean receiverRegistered;
    private Handler mHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mHandler = new Handler();

        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                // do your stuff - don't create a new runnable here!

                TextView textViewTimeNow = (TextView)findViewById(R.id.timeNow);
                textViewTimeNow.setText(getTimeNow());
                mHandler.postDelayed(this, 1000);
            }
        };

// start it with:
        mHandler.post(runnable);

        mTempReadBroadcastReceiver = new BroadcastReceiver() {

            @Override
            public void onReceive(Context context, Intent intent) {

                String time, temp1, temp2, temp3;

                if (intent!=null) {
                    time = intent.getStringExtra("TIME");
                    temp1 = intent.getStringExtra("TEMP1");
                    temp2 = intent.getStringExtra("TEMP2");
                    temp3 = intent.getStringExtra("TEMP3");

                    TextView textViewTemp1 = (TextView)findViewById(R.id.temp1);
                    textViewTemp1.setText(temp3 + "º C");
                    TextView textViewTemp2 = (TextView)findViewById(R.id.temp2);
                    textViewTemp2.setText(temp2+ "º C");
                    TextView textViewTemp3 = (TextView)findViewById(R.id.temp3);
                    textViewTemp3.setText(temp1+ "º C");

                    TextView textViewTime1 = (TextView)findViewById(R.id.time1);
                    textViewTime1.setText(getTime(time));
                    TextView textViewTime2 = (TextView)findViewById(R.id.time2);
                    textViewTime2.setText(getTime(time));
                    TextView textViewTime3 = (TextView)findViewById(R.id.time3);
                    textViewTime3.setText(getTime(time));
                }


            }
        };
        registerReceiver();

//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//            // Create channel to show notifications.
//            String channelId  = getString(R.string.default_notification_channel_id);
//            String channelName = getString(R.string.default_notification_channel_name);
//            NotificationManager notificationManager =
//                    getSystemService(NotificationManager.class);
//            notificationManager.createNotificationChannel(new NotificationChannel(channelId,
//                    channelName, NotificationManager.IMPORTANCE_LOW));
//        }

        // If a notification message is tapped, any data accompanying the notification
        // message is available in the intent extras. In this sample the launcher
        // intent is fired when the notification is tapped, so any accompanying data would
        // be handled here. If you want a different intent fired, set the click_action
        // field of the notification message to the desired intent. The launcher intent
        // is used when no click_action is specified.
        //
        // Handle possible data accompanying notification message.
        // [START handle_data_extras]
        if (getIntent().getExtras() != null) {
            for (String key : getIntent().getExtras().keySet()) {
                Object value = getIntent().getExtras().get(key);
                Log.d(TAG, "Key: " + key + " Value: " + value);
            }
        }
        // [END handle_data_extras]

        FirebaseMessaging.getInstance().subscribeToTopic("iot-temp")
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        String msg = getString(R.string.connected_to_sensors);
                        if (!task.isSuccessful()) {
                            msg = getString(R.string.msg_subscribe_failed);
                        }
                        Log.d(TAG, msg);
                        Toast.makeText(MainActivity.this, msg, Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void registerReceiver() {
        if (!receiverRegistered) {
            LocalBroadcastManager.getInstance(this).registerReceiver(mTempReadBroadcastReceiver,
                    new IntentFilter(Preferences.TEMPERATURE_RECEIVED));
            receiverRegistered = true;
        }
    }

    private void unregisterReceiver() {
        if (receiverRegistered) {
            LocalBroadcastManager.getInstance(this).unregisterReceiver(mTempReadBroadcastReceiver);
            receiverRegistered = false;
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver();
    }

    @Override
    protected void onResume() {
        super.onResume();
        registerReceiver();
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
