package com.yandex.job;

import android.content.Intent;
import android.os.Bundle;

import android.view.View;

import androidx.navigation.ui.AppBarConfiguration;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class LoggedOutActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    public static Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logged_out);
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
