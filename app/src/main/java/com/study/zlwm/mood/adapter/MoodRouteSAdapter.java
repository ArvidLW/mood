package com.study.zlwm.mood.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.study.zlwm.mood.fragment.MoodRouteRecordFragment;
import com.study.zlwm.mood.fragment.MoodRouteStateFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 31351 on 2016/8/13.
 */
public class MoodRouteSAdapter extends FragmentStatePagerAdapter{
    private List<Fragment> fragmentList=new ArrayList<>();
    private List<String> tileList=new ArrayList<>();

    public MoodRouteSAdapter(FragmentManager fm) {
        super(fm);
        fragmentList.add(new MoodRouteRecordFragment());
        fragmentList.add(new MoodRouteStateFragment());
        tileList.add("心情曲线");
        tileList.add("心情统计");
    }

    @Override
    public Fragment getItem(int position) {
        return fragmentList.get(position);
    }

    @Override
    public int getCount() {
        return fragmentList.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return tileList.get(position);
    }

}
