package com.example.dell.broadcastreceivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.FrameLayout;

public class MainActivity extends AppCompatActivity {
    FrameLayout frl;
    InnerChargerReceiver icr;
    IntentFilter chargefilter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        frl = findViewById(R.id.frl);

        icr = new InnerChargerReceiver();
        chargefilter = new IntentFilter();
        chargefilter.addAction(Intent.ACTION_POWER_CONNECTED);
        chargefilter.addAction(Intent.ACTION_POWER_DISCONNECTED);
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        registerReceiver(icr, chargefilter);
    }

    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(icr);
    }

    public  class InnerChargerReceiver extends BroadcastReceiver{
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent == null)
                return;
            if (intent.getAction().equals(Intent.ACTION_POWER_CONNECTED))
            {
                 frl.setBackgroundColor(Color.GREEN);
            }
            if (intent.getAction().equals(Intent.ACTION_POWER_DISCONNECTED))
            {
                frl.setBackgroundColor(Color.RED);
            }
        }
    }
}
