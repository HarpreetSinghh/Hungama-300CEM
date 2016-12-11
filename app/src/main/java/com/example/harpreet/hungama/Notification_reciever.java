package com.example.harpreet.hungama;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;

/**
 * Created by Harpreet on 03/12/2016.
 */
public class Notification_reciever extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {

        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        // when the user clicks, it will redirect it to this activity which is 'Login Activity'

        Intent login_intent = new Intent(context,LoginActivity.class);
        login_intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        //instance of the pending intent to show the intent into the notification.

        PendingIntent pendingIntent = PendingIntent.getActivities(context,100, new Intent[]{login_intent},PendingIntent.FLAG_UPDATE_CURRENT);

        // this builds the notification.

        NotificationCompat.Builder builder = new NotificationCompat.Builder(context)
                .setContentIntent(pendingIntent)
                .setSmallIcon(R.drawable.ic_hungama)
                .setContentTitle("Movie Update")
                .setContentText("Check out a new movie review on Desi Hungama")
                // this will make notification dismissable when user swips it away.
                .setAutoCancel(true);


        // notification id is numeric value and it has to be unique for each notification. if you reuse and send notification, it will cancel existing notification and cancel the ID.
        int notificationId = 1;
        NotificationManagerCompat mgr =
                NotificationManagerCompat.from(context);
        // creates the notification object and passes it to the manager and manager sends the notification.
        mgr.notify(notificationId, builder.build());

        notificationManager.notify(100,builder.build());

    }
}