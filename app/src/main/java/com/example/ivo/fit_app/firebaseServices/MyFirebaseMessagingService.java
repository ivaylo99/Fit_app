package com.example.ivo.fit_app.firebaseServices;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.example.ivo.fit_app.Login_Activity;
import com.example.ivo.fit_app.R;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import static android.content.ContentValues.TAG;

/**
 * Created by Ivo on 15.2.2018 г..
 */

public class MyFirebaseMessagingService extends FirebaseMessagingService{
    private final String TAG = "JSA-FCM";

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {

        if (remoteMessage.getNotification() != null) {
            Log.e(TAG, "Title: " + remoteMessage.getNotification().getTitle());
            Log.e(TAG, "Body: " + remoteMessage.getNotification().getBody());
        }

        if (remoteMessage.getData().size() > 0) {
            Log.e(TAG, "Data: " + remoteMessage.getData());
        }
    }

}
