package com.yandex.job.ui.home;

import android.os.Bundle;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.yandex.job.LoggedOutActivity;
import com.yandex.job.R;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        /*if (LoggedOutActivity.toolbar != null) {
            LoggedOutActivity.toolbar.setTitle(Html.fromHtml("Официальный партнер " + "<font color='#EE0000'>Я</font>" + "ндексТакси\n«АвтоСтиль»"));
            LoggedOutActivity.toolbar.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        }*/
        return root;
    }
}