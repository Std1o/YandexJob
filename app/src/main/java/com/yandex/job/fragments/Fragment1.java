package com.yandex.job.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.yandex.job.AutoparkActivity;
import com.yandex.job.R;


/**
 * Created by ovix on 10/10/17.
 */

public class Fragment1 extends Fragment {

    Boolean isStarted = false;
    Boolean isVisible = false;
    public static int position = 0;

    public TextView brand;
    public TextView color;
    public TextView price;
    public TextView year;
    public ImageView photo;
    public TextView trans;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle SaveInstanceState){
        View rootView = inflater.inflate(R.layout.auto_park_item1, container, false);
        initViews(rootView);
        setInfo();
        return rootView;

    }

    private void initViews(View view) {
        brand = view.findViewById(R.id.tvBrand);
        color = view.findViewById(R.id.tvColor);
        price = view.findViewById(R.id.tvPrice);
        year = view.findViewById(R.id.tvYear);
        photo = view.findViewById(R.id.ivPhoto);
        trans = view.findViewById(R.id.tvTrans);
    }

    private void setInfo() {
        brand.setText(AutoparkActivity.list.get(position).brand);
        color.setText(AutoparkActivity.list.get(position).color);
        price.setText(AutoparkActivity.list.get(position).price);
        year.setText(AutoparkActivity.list.get(position).year);
        Glide.with(getContext())
                .load("http://some-company.svkcom.ru/" +
                        AutoparkActivity.list.get(position).photo)
                .apply(new RequestOptions()
                        .override(900, 600)
                        .placeholder(R.drawable.progress_animation)
                        .dontAnimate()
                        .dontTransform())
                .into(photo);
        trans.setText(AutoparkActivity.list.get(position).trans);
    }

    @Override
    public void onStart() {
        super.onStart();
        isStarted = true;
        if (isVisible && isStarted){

        }
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        isVisible = isVisibleToUser;
        if (isStarted && isVisible) {

        }
    }
}
