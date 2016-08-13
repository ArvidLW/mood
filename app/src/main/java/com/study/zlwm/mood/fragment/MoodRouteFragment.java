package com.study.zlwm.mood.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.study.zlwm.mood.R;
import com.study.zlwm.mood.adapter.MoodRouteSAdapter;

/**
 * Created by 31351 on 2016/8/13.
 */
public class MoodRouteFragment extends Fragment {
    private View view;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.fragment_mood_route,container,false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        ViewPager viewPager= (ViewPager) view.findViewById(R.id.route_view_pager);
        TabLayout tableLayout= (TabLayout) view.findViewById(R.id.route_tabs);
        tableLayout.setTabMode(TabLayout.MODE_FIXED);

        MoodRouteSAdapter moodRouteSAdapter=new MoodRouteSAdapter(getChildFragmentManager());
        viewPager.setAdapter(moodRouteSAdapter);
        viewPager.setCurrentItem(0);
        tableLayout.setupWithViewPager(viewPager);
    }
}
