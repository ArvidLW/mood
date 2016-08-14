package com.study.zlwm.mood.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.study.zlwm.mood.R;
import com.study.zlwm.mood.adapter.MoodFragmentAdapter;
import com.study.zlwm.mood.bean.MoodCardInfo;

import java.util.ArrayList;

/**
 * Created by 31351 on 2016/7/5.
 */
public class FragmentMyMood extends Fragment{
    private View view;
    private ArrayList<MoodCardInfo> moodCardInfoList;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.fragment_my_mood,container,false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        ViewPager viewPager= (ViewPager) view.findViewById(R.id.view_pager);
        TabLayout tableLayout= (TabLayout) view.findViewById(R.id.tabs);
        tableLayout.setTabMode(TabLayout.MODE_FIXED);
        addMoodCardInfo();
        MoodFragmentAdapter moodFragmentAdapter=new MoodFragmentAdapter(getChildFragmentManager(),moodCardInfoList);
        viewPager.setAdapter(moodFragmentAdapter);
        viewPager.setCurrentItem(0);
        tableLayout.setupWithViewPager(viewPager);
        //MoodCardInfo cardInfo;

        //int[] colors={R.color.blueviolet,R.color.gray,R.color.brown,R.color.yellow,R.color.royalblue,R.color.cornflowerblue,R.color.darkkhaki,R.color.orangered};
        for (int i = 0; i < moodCardInfoList.size(); i++) {
           // cardInfo=moodCardInfolist.get(i);
            TextView tv = new TextView(getActivity());

            tv.setWidth(viewPager.getWidth());
            //tv.setHeight(1000);
            tv.setBackgroundColor( getResources().getColor(moodCardInfoList.get(i).color));
            tableLayout.getTabAt(i).setCustomView(tv);
        }

    }

    public void addMoodCardInfo()
    {
        moodCardInfoList=new ArrayList<>();
        moodCardInfoList.add(new MoodCardInfo(R.drawable.mood_afraid,R.color.blueviolet,"afraid"));
        moodCardInfoList.add(new MoodCardInfo(R.drawable.mood_depressed,R.color.gray,"depressed"));
        moodCardInfoList.add(new MoodCardInfo(R.drawable.mood_fidget,R.color.brown,"fidget"));
        moodCardInfoList.add(new MoodCardInfo(R.drawable.mood_happy,R.color.yellow,"happy"));
        moodCardInfoList.add(new MoodCardInfo(R.drawable.mood_nervous,R.color.royalblue,"nervous"));
        moodCardInfoList.add(new MoodCardInfo(R.drawable.mood_quiet,R.color.cornflowerblue,"quiet"));
        moodCardInfoList.add(new MoodCardInfo(R.drawable.mood_sad,R.color.darkkhaki,"sad"));
        moodCardInfoList.add(new MoodCardInfo(R.drawable.mood_satisfied,R.color.orangered,"satisfied"));


    }

}

