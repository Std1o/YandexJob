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

public class Registration1Activity extends AppCompatActivity {

    EditText etName, etLastName, etMiddleName, etBirthDate, etUserPhone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration1);
        String title = "Регистрация: шаг №1";
        SpannableString s = new SpannableString(title);
        s.setSpan(new ForegroundColorSpan(Color.BLACK), 0, title.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        getSupportActionBar().setTitle(s);
        initViews();
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnUpload:
                break;
            case R.id.btnContinue:
                getDataFromEt();
                startActivity(new Intent(this, Registration2Activity.class));
                break;
        }
    }

    private void initViews() {
        etName = findViewById(R.id.etName);
        etLastName = findViewById(R.id.etLastName);
        etMiddleName = findViewById(R.id.etMiddleName);
        etBirthDate = findViewById(R.id.etBirthDate);
        etUserPhone = findViewById(R.id.etUserPhone);
    }

    private void getDataFromEt() {
        RegistrationData.name = etName.getText().toString();
        RegistrationData.lastName = etLastName.getText().toString();
        RegistrationData.middleName = etMiddleName.getText().toString();
        RegistrationData.birthDate = etBirthDate.getText().toString();
        RegistrationData.phone = etUserPhone.getText().toString();
    }
}
