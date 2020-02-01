package com.yandex.job;

import android.content.Intent;
import android.os.Bundle;

import android.util.Log;
import android.view.View;

import androidx.navigation.ui.AppBarConfiguration;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import net.khirr.android.privacypolicy.PrivacyPolicyDialog;

public class LoggedOutActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    public static Toolbar toolbar;
    PrefManager prefManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logged_out);
        prefManager = new PrefManager(this);
        if (!prefManager.isAccepted()) {
            checkPolicy();
        }
    }

    private void checkPolicy() {
        PrivacyPolicyDialog dialog = new PrivacyPolicyDialog(this,
                "https://yadi.sk/i/eT11_pJySyT3-Q",
                "https://yadi.sk/i/oHwtliYRFJbqnQ");
        dialog.addPoliceLine("This application uses a unique user identifier for advertising purposes, it is shared with third-party companies.");
        dialog.addPoliceLine("This application sends error reports, installation and send it to a server of the Fabric.io company to analyze and process it.");
        dialog.addPoliceLine("This application requires internet access and must collect the following information: Installed applications and history of installed applications, ip address, unique installation id, token to send notifications, version of the application, time zone and information about the language of the device.");
        dialog.addPoliceLine("All details about the use of data are available in our Privacy Policies, as well as all Terms of Service links below.");

        dialog.setOnClickListener(new PrivacyPolicyDialog.OnClickListener() {
            @Override
            public void onAccept(boolean isFirstTime) {
                prefManager.setAccepted(true);
            }

            @Override
            public void onCancel() {
                Log.e("MainActivity", "Policies not accepted");
                finish();
            }
        });
        dialog.show();
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnReg:
                startActivity(new Intent(this, Registration1Activity.class));
                break;
            case R.id.btnAuth:
                startActivity(new Intent(this, AuthActivity.class));
                break;
            case R.id.btnAutopark:
                startActivity(new Intent(this, AutoparkActivity.class));
                break;
        }
    }
}
