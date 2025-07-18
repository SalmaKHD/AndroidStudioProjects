package com.salmakhd.android.shahabseries.navigationapplication;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class SenfiService extends Service {

    final class newThread implements Runnable {
        int serviceId;
        newThread(int serviceId) {
            serviceId = serviceId;
        }
        @Override
        public void run() {
            synchronized (this) {
                int x = 0;
                while(x<20) {
                    try {
                        wait(1500);

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    x++;
                }
                stopSelf(serviceId);
            }
        }
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Toast.makeText(this, "Service Started", Toast.LENGTH_LONG).show();
        Thread thread = new Thread(new newThread(startId));
        thread.start();
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        Toast.makeText(this, "Service Stopped", Toast.LENGTH_LONG).show();
        super.onDestroy();
    }
}
