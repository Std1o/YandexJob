package com.yandex.job;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.text.Html;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.method.LinkMovementMethod;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

public class Registration4Activity extends AppCompatActivity {
    
    TextView tvConsent;
    EditText etLogin, etPass, etConfirmPass, etCard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration4);

        String title = "Регистрация: шаг №4";
        SpannableString s = new SpannableString(title);
        s.setSpan(new ForegroundColorSpan(Color.BLACK), 0, title.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        getSupportActionBar().setTitle(s);
        
        tvConsent = findViewById(R.id.tvConsent);
        tvConsent.setMovementMethod(LinkMovementMethod.getInstance());
        initViews();
    }

    public void onClick(View v) {
        getDataFromEt();
    }

    private void initViews() {
        etLogin = findViewById(R.id.etLogin);
        etPass = findViewById(R.id.etPass);
        etConfirmPass = findViewById(R.id.etConfirmPass);
        etCard = findViewById(R.id.etCard);
    }

    private void getDataFromEt() {
        if (etPass.getText().toString().equals(etConfirmPass.getText().toString())) {
            RegistrationData.login = etLogin.getText().toString();
            RegistrationData.password = etPass.getText().toString();
            RegistrationData.card = etCard.getText().toString();
            request();
        }
        else {
            etConfirmPass.setError("Пароли не совпадают");
        }
    }

    private void request() {

        Map<String, String> params = new HashMap<>();
        params.put("command", "addDriver");
        params.put("famaly", RegistrationData.lastName);
        params.put("name", RegistrationData.name);
        params.put("patronymic", RegistrationData.middleName);
        params.put("driverPhone", RegistrationData.phone);
        params.put("work", RegistrationData.workType);
        params.put("car_mark", RegistrationData.brand);
        params.put("car_model", RegistrationData.model);
        params.put("car_color", RegistrationData.color);
        params.put("car_type", RegistrationData.TSType);
        params.put("car_year", RegistrationData.year);
        params.put("login", RegistrationData.login);
        params.put("pass", RegistrationData.password);
        params.put("card", RegistrationData.card);
        params.put("birthday", RegistrationData.birthDate);
        System.out.println(params);


        JsonObjectRequest postRequest = new JsonObjectRequest(Request.Method.POST, "http://some-company.svkcom.ru/api/addDriver.php",

                new JSONObject(params),
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        System.out.println(response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(), "Ошибка связи с сервером", Toast.LENGTH_SHORT).show();
                        Log.e("666", "Autorize - " + error);
                    }
                });
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(postRequest);
    }
}
