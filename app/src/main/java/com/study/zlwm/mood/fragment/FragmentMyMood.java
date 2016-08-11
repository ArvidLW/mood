package com.study.zlwm.mood.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.study.zlwm.mood.MoodFragmentAdapter;
import com.study.zlwm.mood.R;

/**
 * Created by 31351 on 2016/7/5.
 */
public class FragmentMyMood extends Fragment{
    private View view;
    private ViewPager viewPager;
    private MoodFragmentAdapter moodFragmentAdapter;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.fragment_my_mood,container,false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        viewPager= (ViewPager) view.findViewById(R.id.view_pager);
        moodFragmentAdapter=new MoodFragmentAdapter(getFragmentManager());
        viewPager.setAdapter(moodFragmentAdapter);
        viewPager.setCurrentItem(0);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }
}
