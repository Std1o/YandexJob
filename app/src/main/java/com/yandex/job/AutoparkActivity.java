package com.yandex.job;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.CustomTarget;
import com.bumptech.glide.request.transition.Transition;
import com.github.ybq.parallaxviewpager.ParallaxViewPager;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class AutoparkActivity extends AppCompatActivity {

    public static ArrayList<AutoParkModel> list = new ArrayList<>();

    public TextView brand;
    public TextView color;
    public TextView price;
    public TextView year;
    public ImageView ivItem;
    public TextView trans;
    private ParallaxViewPager mParallaxViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_autopark);

        String title = "Наш автопарк";
        SpannableString s = new SpannableString(title);
        s.setSpan(new ForegroundColorSpan(Color.BLACK), 0, title.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        getSupportActionBar().setTitle(s);
        getAuto();
        list = new ArrayList<>();
        mParallaxViewPager = (ParallaxViewPager) findViewById(R.id.viewpager);
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
                                initViewPager();
                            }
                            for (AutoParkModel model : list) {
                                System.out.println(model.brand);
                            }
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

    private void initViewPager() {
        PagerAdapter adapter = new PagerAdapter() {

            @Override
            public boolean isViewFromObject(View arg0, Object arg1) {
                return arg0 == arg1;
            }

            @Override
            public void destroyItem(ViewGroup container, int position,
                                    Object obj) {
                container.removeView((View) obj);
            }

            @Override
            public Object instantiateItem(ViewGroup container, int position) {
                View view = View.inflate(container.getContext(), R.layout.pager_item, null);
                initViews(view);
                brand.setText(AutoparkActivity.list.get(position % list.size()).brand);
                color.setText("Цвет: " + AutoparkActivity.list.get(position % list.size()).color);
                price.setText("Стоимость аренды: " + AutoparkActivity.list.get(position % list.size()).price);
                year.setText("Год выпуска: " + AutoparkActivity.list.get(position % list.size()).year);
                trans.setText(AutoparkActivity.list.get(position % list.size()).trans);
                Glide.with(AutoparkActivity.this)
                        .load("http://some-company.svkcom.ru" +
                                AutoparkActivity.list.get(position % list.size()).photo)
                        .apply(new RequestOptions().placeholder(R.drawable.progress_animation))
                        .into(ivItem);
                container.addView(view, ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.MATCH_PARENT);
                return view;
            }

            @Override
            public int getCount() {
                return list.size();
            }
        };
        mParallaxViewPager.setAdapter(adapter);
    }

    private void initViews(View view) {
        brand = view.findViewById(R.id.tvBrand);
        color = view.findViewById(R.id.tvColor);
        price = view.findViewById(R.id.tvPrice);
        year = view.findViewById(R.id.tvYear);
        ivItem = view.findViewById(R.id.item_img);
        trans = view.findViewById(R.id.tvTrans);
    }
}
