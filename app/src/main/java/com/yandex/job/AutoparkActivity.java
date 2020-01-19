package com.yandex.job;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.graphics.Color;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class AutoparkActivity extends AppCompatActivity {

    public static ArrayList<AutoParkModel> list = new ArrayList<>();
    CustomPageAdapter mCustomPageAdapter;
    ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_autopark);

        String title = "Наш автопарк";
        SpannableString s = new SpannableString(title);
        s.setSpan(new ForegroundColorSpan(Color.BLACK), 0, title.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        getSupportActionBar().setTitle(s);
        getAuto();
    }

    private void getAuto() {
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "http://some-company.svkcom.ru/api/listCar.php";
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        JSONObject obj;

                        try {
                            obj = new JSONObject(response);
                            System.out.println(obj);
                            JSONArray jsonArray = obj.getJSONArray("car");
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject carObject = jsonArray.getJSONObject(i);
                                list.add(new AutoParkModel(carObject.getString("ID"),
                                        carObject.getString("marka"),
                                        carObject.getString("color"),
                                        carObject.getString("price"),
                                        carObject.getString("year"),
                                        carObject.getString("foto"),
                                        carObject.getString("trans")));
                            }
                            setPagerAdapter();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            }
        });
        queue.add(stringRequest);
    }

    private void setPagerAdapter() {
        mCustomPageAdapter = new CustomPageAdapter(getSupportFragmentManager(), this);

        //getPosisi.getItem();

        mViewPager = (ViewPager) findViewById(R.id.pager);
        mViewPager.setAdapter(mCustomPageAdapter);

        mViewPager.getCurrentItem();
    }
}
