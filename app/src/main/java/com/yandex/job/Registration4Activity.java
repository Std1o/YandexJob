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

import java.util.HashMap;
import java.util.Map;

public class Registration4Activity extends AppCompatActivity {
    
    TextView tvConsent;

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
    }

    public void onClick(View v) {
        request();
    }

    private void request() {

        Map<String, String> params = new HashMap<>();
        params.put("command", "addDriver");
        params.put("famaly", "famaly");
        params.put("name", "name");
        params.put("patronymic", "patronymic");
        params.put("driverPhone", "89101234567");
        params.put("work", "own");
        params.put("car_mark", "car_mark");
        params.put("car_model", "car_model");
        params.put("car_color", "car_color");
        params.put("car_type", "car_type");
        params.put("car_year", "2010");
        params.put("login", "login");
        params.put("pass", "pass");
        params.put("card", "card");
        params.put("birthday", "11-01-2020");
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
