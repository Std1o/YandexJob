package com.yandex.job;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
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
import com.yandex.job.gmailHelper.GMailSender;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

public class Registration4Activity extends AppCompatActivity {
    
    TextView tvConsent;
    EditText etLogin, etPass, etConfirmPass, etCard;
    PrefManager prefManager;

    String recipient = "tempmailfortesttt@gmail.com";
    String senderMail = "yandex.job.app@gmail.com";
    String senderPassword = "yourpassword";

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
        prefManager = new PrefManager(this);
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
        if (etLogin.getText().toString().isEmpty() || etPass.getText().toString().isEmpty() || etConfirmPass.getText().toString().isEmpty() || etCard.getText().toString().isEmpty()) {
            if (etLogin.getText().toString().isEmpty()) {
                etLogin.setError(getString(R.string.et_empty_error));
            }
            if (etPass.getText().toString().isEmpty()) {
                etPass.setError(getString(R.string.et_empty_error));
            }
            if (etConfirmPass.getText().toString().isEmpty()) {
                etConfirmPass.setError(getString(R.string.et_empty_error));
            }
            if (etCard.getText().toString().isEmpty()) {
                etCard.setError(getString(R.string.et_empty_error));
            }
        }
        else {
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
    }

    private String getMessage() {
        String message = "Фамилия: " + RegistrationData.lastName
                + "\nИмя: " + RegistrationData.name
                + "\nОтчество: " + RegistrationData.middleName
                + "\nДата рождения: " + RegistrationData.birthDate
                + "\nНомер телефона: " + RegistrationData.phone
                + "\nПараметр владения автомобилем: " + RegistrationData.workType;

        if (RegistrationData.workType.equals("own")) {
            message += "\n\nМарка: " + RegistrationData.brand
                    + "\nМодель: " + RegistrationData.model
                    + "\nТип ТС: " + RegistrationData.TSType
                    + "\nЦвет: " + RegistrationData.color
                    + "\nГод выпуска: " + RegistrationData.year;
        }
        message += "\n\n Номер карты: " + RegistrationData.card;
        return message;
    }

    private void sendMessage() {
        Thread sender = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    GMailSender sender = new GMailSender(senderMail, senderPassword);
                    sender.sendMail(getResources().getString(R.string.app_name), getMessage(),
                            senderMail,
                            recipient);
                } catch (Exception e) {
                    Log.e("mylog", "Error: " + e.getMessage());
                }
            }
        });
        sender.start();
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
                        try {
                            String status = response.getString("st");
                            String id = response.getString("ID");
                            if (status.equals("1")) {
                                prefManager.setId(id);
                                uploadPhotos("foto_1", Registration1Activity.photo1);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
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

    private void uploadPhotos(final String photoType, String image) {
        Map<String, String> params = new HashMap<>();
        params.put("image", image);
        params.put("foto", photoType);
        params.put("id", prefManager.getId());
        System.out.println(params);


        JsonObjectRequest postRequest = new JsonObjectRequest(Request.Method.POST, "http://some-company.svkcom.ru/api/upload.php",

                new JSONObject(params),
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        System.out.println(response);
                        if (photoType.equals("foto_1")) {
                            uploadPhotos("foto_2", Registration3Activity.photo2);
                        }
                        else if (photoType.equals("foto_2")) {
                            uploadPhotos("foto_3", Registration3Activity.photo3);
                        }
                        else if (photoType.equals("foto_3")) {
                            uploadPhotos("foto_4", Registration3Activity.photo4);
                        }
                        else if (photoType.equals("foto_4")) {
                            uploadPhotos("foto_5", Registration3Activity.photo5);
                        }
                        else if (photoType.equals("foto_5")){
                            sendMessage();
                            prefManager.setName(RegistrationData.name);
                            prefManager.setLastName(RegistrationData.lastName);
                            prefManager.setUserPhoto(Registration1Activity.photo1);
                            prefManager.setLoggedIn(true);
                            startActivity(new Intent(Registration4Activity.this, MainActivity.class));
                            finish();
                        }
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
