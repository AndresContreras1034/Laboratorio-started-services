package com.edupilot.startedservices;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

public class MainActivity extends AppCompatActivity {

    private final ActivityResultLauncher<String> permissionLauncher =
            registerForActivityResult(new ActivityResultContracts.RequestPermission(),
                    granted -> { });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU
                && ContextCompat.checkSelfPermission(this,
                Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {
            permissionLauncher.launch(Manifest.permission.POST_NOTIFICATIONS);
        }
    }

    public void onClick(View view) {
        Intent intent = new Intent(this, DelayedMessageService.class);
        intent.putExtra(DelayedMessageService.EXTRA_TITLE, getString(R.string.question));
        intent.putExtra(DelayedMessageService.EXTRA_MESSAGE, getString(R.string.response));
        intent.putExtra(DelayedMessageService.EXTRA_NOTIFICATION_ID, 5453);
        startService(intent);
    }

    public void onGradeClick(View view) {
        Intent intent = new Intent(this, DelayedMessageService.class);
        intent.putExtra(DelayedMessageService.EXTRA_TITLE, getString(R.string.grade_title));
        intent.putExtra(DelayedMessageService.EXTRA_MESSAGE, getString(R.string.grade_message));
        intent.putExtra(DelayedMessageService.EXTRA_NOTIFICATION_ID, 7001);
        startService(intent);
    }
}