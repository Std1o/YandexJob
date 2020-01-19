package com.yandex.job;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.EditText;

public class Registration2Activity extends AppCompatActivity {

    EditText etBrand, etModel, etTSType, etColor, etReleaseYear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration2);
        String title = "Регистрация: шаг №2";
        SpannableString s = new SpannableString(title);
        s.setSpan(new ForegroundColorSpan(Color.BLACK), 0, title.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        getSupportActionBar().setTitle(s);
        initViews();
    }

    public void onClick(View v) {
        getDataFromEt();
        startActivity(new Intent(this, Registration3Activity.class));
    }

    private void initViews() {
        etBrand = findViewById(R.id.etBrand);
        etModel = findViewById(R.id.etModel);
        etTSType = findViewById(R.id.etTSType);
        etColor = findViewById(R.id.etColor);
        etReleaseYear = findViewById(R.id.etReleaseYear);
    }

    private void getDataFromEt() {
        RegistrationData.brand = etBrand.getText().toString();
        RegistrationData.model = etModel.getText().toString();
        RegistrationData.TSType = etTSType.getText().toString();
        RegistrationData.color = etColor.getText().toString();
        RegistrationData.year = etReleaseYear.getText().toString();
    }
}
