package com.yandex.job;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import android.text.Html;
import android.view.View;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.navigation.NavigationView;

import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.Menu;

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
                startActivity(new Intent(this, RegistrationActivity.class));
                break;
            case R.id.btnAuth:
                break;
            case R.id.btnAutopark:
                break;
        }
    }
}
