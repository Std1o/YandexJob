package com.yandex.job;

/**
 * Created by ovix on 10/10/17.
 */

import android.content.Context;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.yandex.job.fragments.Fragment1;
import com.yandex.job.fragments.Fragment2;


public class CustomPageAdapter extends FragmentPagerAdapter {


    protected Context mContext;


    public CustomPageAdapter(FragmentManager fm, Context context) {
        super(fm);
        mContext = context;
    }

    @Override
    public Fragment getItem(int position) {


        Fragment fragment1 = new Fragment1();
        Fragment fragment2 = new Fragment2();
        Fragment1.position = position;
        Fragment2.position = position;

        return (position % 2 == 0) ? fragment1 : fragment2;

    }


    @Override
    public int getCount() {
        return AutoparkActivity.list.size();
    }
}