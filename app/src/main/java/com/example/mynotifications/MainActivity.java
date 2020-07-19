package com.example.mynotifications;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.RemoteViews;

public class MainActivity extends AppCompatActivity {
    public final String CHANNEL_ID = "simple notification";
    public static final int NOTIFICATION_ID = 001;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void displayNotification(View view)
    {
        //creating notification channel
        createNotificationChannel();

        //tap action on notification
        Intent intent = new Intent(this,TapActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);

        PendingIntent pendingIntent = PendingIntent.getActivity(this,0,intent,PendingIntent.FLAG_ONE_SHOT);

        //creating simple notification
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this,CHANNEL_ID);
        builder.setSmallIcon(R.drawable.ic_sms_notifications);
        builder.setContentTitle("Simple Notification");
        builder.setContentText("This is a simple notification...");
        builder.setPriority(NotificationCompat.PRIORITY_DEFAULT);
        builder.setContentIntent(pendingIntent);
        builder.setAutoCancel(true);

        NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(this);
        notificationManagerCompat.notify(NOTIFICATION_ID,builder.build());
    }

    public void displayNotification1(View view)
    {
        //creating notification channel
        createNotificationChannel();

        //tap on yes button
        Intent YesIntent = new Intent(this,YesActivity.class);
        YesIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);

        PendingIntent YesPendingIntent = PendingIntent.getActivity(this,0,YesIntent,PendingIntent.FLAG_ONE_SHOT);

        //tap on no button
        Intent NoIntent = new Intent(this,NoActivity.class);
        NoIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);

        PendingIntent NoPendingIntent = PendingIntent.getActivity(this,0,NoIntent,PendingIntent.FLAG_ONE_SHOT);

        //creating simple notification
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this,CHANNEL_ID);
        builder.setSmallIcon(R.drawable.ic_sms_notifications);
        builder.setContentTitle("Simple Notification");
        builder.setContentText("This is a simple notification...");
        builder.setPriority(NotificationCompat.PRIORITY_DEFAULT);
        builder.addAction(R.drawable.ic_check_button,"Yes",YesPendingIntent);
        builder.addAction(R.drawable.ic_close_button,"No",NoPendingIntent);

        NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(this);
        notificationManagerCompat.notify(NOTIFICATION_ID,builder.build());
    }

    public void displayNotification2(View view)
    {
        //creating notification channel
        createNotificationChannel();

        //creating simple notification
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this,CHANNEL_ID);
        builder.setSmallIcon(R.drawable.ic_sms_notifications);
        builder.setContentTitle("Simple Notification");
        builder.setContentText("This is a simple notification...");
        builder.setPriority(NotificationCompat.PRIORITY_DEFAULT);

        Bitmap bitmap = BitmapFactory.decodeResource(getResources(),R.drawable.pic);
        builder.setLargeIcon(bitmap);
        builder.setStyle(new NotificationCompat.BigPictureStyle().bigPicture(bitmap).bigLargeIcon(null));

        NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(this);
        notificationManagerCompat.notify(NOTIFICATION_ID,builder.build());
    }

    private void createNotificationChannel()
    {
        if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.O)
        {
            CharSequence name = "Personal Notification";
            String description = "Include all the personal notifications";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;

            NotificationChannel notificationChannel = new NotificationChannel(CHANNEL_ID,name,importance);
            notificationChannel.setDescription(description);

            NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
            notificationManager.createNotificationChannel(notificationChannel);
        }
    }

    public void displayNotification3(View view)
    {
        //creating notification channel
        createNotificationChannel();

        //creating simple notification
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this,CHANNEL_ID);
        builder.setSmallIcon(R.drawable.ic_sms_notifications);
        builder.setContentTitle("Simple Notification");
        builder.setContentText("This is a simple notification...");
        builder.setPriority(NotificationCompat.PRIORITY_DEFAULT);
        builder.setStyle(new NotificationCompat.BigTextStyle().bigText(getString(R.string.big_text_style_notification)));

        NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(this);
        notificationManagerCompat.notify(NOTIFICATION_ID,builder.build());
    }

    public void displayNotification4(View view)
    {
        //creating notification channel
        createNotificationChannel();

        //expanded and collapsed notification
        RemoteViews collapsed_layout = new RemoteViews(getPackageName(),R.layout.custom_collapsed);
        RemoteViews expanded_layout = new RemoteViews(getPackageName(),R.layout.custom_expanded);

        //creating simple notification
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this,CHANNEL_ID);
        builder.setSmallIcon(R.drawable.ic_sms_notifications);
        builder.setPriority(NotificationCompat.PRIORITY_DEFAULT);
        builder.setStyle(new NotificationCompat.DecoratedCustomViewStyle());
        builder.setCustomContentView(collapsed_layout);
        builder.setCustomBigContentView(expanded_layout);

        NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(this);
        notificationManagerCompat.notify(NOTIFICATION_ID,builder.build());
    }

    public void displayNotification5(View view)
    {
        //creating notification channel
        createNotificationChannel();

        //creating simple notification
        final NotificationCompat.Builder builder = new NotificationCompat.Builder(this,CHANNEL_ID);
        builder.setSmallIcon(R.drawable.ic_download);
        builder.setContentTitle("Image Download");
        builder.setContentText("Download in progress");
        builder.setPriority(NotificationCompat.PRIORITY_DEFAULT);

        final NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(this);

        final int max_progress = 100;
        int current_progress = 0;
        builder.setProgress(max_progress,current_progress,false);

        notificationManagerCompat.notify(NOTIFICATION_ID,builder.build());

        Thread thread = new Thread()
        {
            @Override
            public void run() {
                int count = 0;
                try
                {
                    while (count<=100)
                    {
                        count = count +10;
                        sleep(1000);
                        builder.setProgress(max_progress,count,false);
                        notificationManagerCompat.notify(NOTIFICATION_ID,builder.build());
                    }
                    builder.setContentText("Download Complete");
                    builder.setProgress(0,0,false);
                    notificationManagerCompat.notify(NOTIFICATION_ID,builder.build());
                }catch (InterruptedException e)
                {}
            }
        };
        thread.start();
    }
}