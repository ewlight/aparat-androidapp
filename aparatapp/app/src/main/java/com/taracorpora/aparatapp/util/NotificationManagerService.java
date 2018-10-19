package com.taracorpora.aparatapp.util;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;

import com.taracorpora.aparatapp.MainActivity;
import com.taracorpora.aparatapp.R;

import static android.content.Context.NOTIFICATION_SERVICE;

public class NotificationManagerService {

    public static final String CHANNEL_ID = "aparat-app";
    public static final String CHANNEL_NAME = "APARAT";
    public static final String CHANNEL_DESCRIPTION = "Aplikasi Pengingat Rapat";

    private Context mCtx;
    private static NotificationManagerService mInstance;

    private NotificationManagerService(Context context) {
        mCtx = context;
    }

    public static synchronized NotificationManagerService getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new NotificationManagerService(context);
        }
        return mInstance;
    }

    public void displayNotification(String title, String body) {

        NotificationCompat.Builder mBuilder =
                new NotificationCompat.Builder(mCtx, CHANNEL_ID)
                        .setSmallIcon(R.drawable.logo)
                        .setContentTitle(title)
                        .setContentText(body);



        Intent resultIntent = new Intent(mCtx, MainActivity.class);


        PendingIntent pendingIntent = PendingIntent.getActivity(mCtx, 0, resultIntent, PendingIntent.FLAG_UPDATE_CURRENT);


        mBuilder.setContentIntent(pendingIntent);

        NotificationManager mNotifyMgr =
                (NotificationManager) mCtx.getSystemService(NOTIFICATION_SERVICE);


        if (mNotifyMgr != null) {
            mNotifyMgr.notify(1, mBuilder.build());
        }
    }
}
