package com.yandex.job;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
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
import com.yandex.job.fragments.Fragment1;
import com.yandex.job.fragments.Fragment2;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class AutoparkActivity extends AppCompatActivity {

    public static ArrayList<AutoParkModel> list = new ArrayList<>();
    CustomPageAdapter mCustomPageAdapter;
    ViewPager mViewPager;

    public static int position = 0;

    public TextView brand;
    public TextView color;
    public TextView price;
    public TextView year;
    public ImageView photo;
    public TextView trans;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_autopark);

        String title = "Наш автопарк";
        SpannableString s = new SpannableString(title);
        s.setSpan(new ForegroundColorSpan(Color.BLACK), 0, title.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        getSupportActionBar().setTitle(s);
        getAuto();
        initViews();
        position = 0;
        list = new ArrayList<>();
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
                                setInfo();
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

    private void initViews() {
        brand = findViewById(R.id.tvBrand);
        color = findViewById(R.id.tvColor);
        price = findViewById(R.id.tvPrice);
        year = findViewById(R.id.tvYear);
        photo = findViewById(R.id.ivPhoto);
        trans = findViewById(R.id.tvTrans);
    }

    private void setInfo() {
        brand.setText(AutoparkActivity.list.get(position).brand);
        color.setText(AutoparkActivity.list.get(position).color);
        price.setText(AutoparkActivity.list.get(position).price);
        year.setText(AutoparkActivity.list.get(position).year);
        Glide.with(this)
                .asBitmap()
                .load("http://some-company.svkcom.ru/" +
                        AutoparkActivity.list.get(position).photo)
                .into(new CustomTarget<Bitmap>() {
                    @Override
                    public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
                        ImageViewAnimatedChange(getApplicationContext(), photo, resource);
                    }

                    @Override
                    public void onLoadCleared(@Nullable Drawable placeholder) {
                    }
                });
        trans.setText(AutoparkActivity.list.get(position).trans);
    }

    public static void ImageViewAnimatedChange(Context c, final ImageView v, final Bitmap new_image) {
        v.setImageBitmap(new_image);
        final Animation anim_in  = AnimationUtils.loadAnimation(c, android.R.anim.fade_in);
        anim_in.setAnimationListener(new Animation.AnimationListener() {
            @Override public void onAnimationStart(Animation animation) {}
            @Override public void onAnimationRepeat(Animation animation) {}
            @Override public void onAnimationEnd(Animation animation) {}
        });
        v.startAnimation(anim_in);
    }
    
    public void onClick(View v) {
        position++;
        if (position < list.size()) {
            setInfo();
        }
    }
    
    

    /*private void setPagerAdapter() {
        mCustomPageAdapter = new CustomPageAdapter(getSupportFragmentManager(), this);

        //getPosisi.getItem();

        mViewPager = (ViewPager) findViewById(R.id.pager);
        mViewPager.setAdapter(mCustomPageAdapter);

        mViewPager.getCurrentItem();
        mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                Fragment1.position = position;
                Fragment2.position = position;

                System.out.println(position);
                System.out.println(AutoparkActivity.list.get(position).brand);
                mCustomPageAdapter.getItem(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }*/
}
