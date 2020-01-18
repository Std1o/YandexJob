package com.yandex.job;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Html;
import android.text.Spannable;
import android.text.method.LinkMovementMethod;
import android.widget.TextView;

public class Registration4Activity extends AppCompatActivity {
    
    TextView tvConsent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration4);
        
        tvConsent = findViewById(R.id.tvConsent);
        tvConsent.setMovementMethod(LinkMovementMethod.getInstance());
    }
}
