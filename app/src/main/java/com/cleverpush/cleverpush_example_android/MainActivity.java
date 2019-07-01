package com.cleverpush.cleverpush_example_android;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.cleverpush.CleverPush;
import com.cleverpush.NotificationOpenedResult;
import com.cleverpush.listener.NotificationOpenedListener;
import com.cleverpush.listener.SubscribedListener;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // TODO: We can specify the Channel ID here (or in AndroidManifest.xml), but do not have to. If we leave it out CleverPush tries to find it via the App's Package Name. The Package Name has to be set in the CleverPush channel settings.
        final CleverPush cleverPush = CleverPush.getInstance(this);

        cleverPush.init(new NotificationOpenedListener() {
            @Override
            public void notificationOpened(NotificationOpenedResult result) {
                System.out.println("Opened CleverPush Notification with URL: " + result.getNotification().getUrl());
            }
        }, new SubscribedListener() {
            @Override
            public void subscribed(String subscriptionId) {
                System.out.println("CleverPush Subscription ID: " + subscriptionId);
            };
        });

        Button btn = findViewById(R.id.topics_dialog_button);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cleverPush.showTopicsDialog();
            }
        });
    }
}
