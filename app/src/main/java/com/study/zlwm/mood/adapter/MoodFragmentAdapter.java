package com.study.zlwm.mood.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.study.zlwm.mood.R;
import com.study.zlwm.mood.fragment.MoodCardFragment;

import java.util.ArrayList;

/**
 * Created by 31351 on 2016/8/10.
 */
public class MoodFragmentAdapter extends FragmentPagerAdapter {
    private ArrayList<MoodCardFragment> moodcardlist=new ArrayList<>();
    private ArrayList<String> titleList=new ArrayList<>();

    public MoodFragmentAdapter(FragmentManager fm) {
        super(fm);
        moodcardlist.add(MoodCardFragment.newInstance(R.drawable.mood_afraid,R.color.blueviolet));
        moodcardlist.add(MoodCardFragment.newInstance(R.drawable.mood_depressed,R.color.gray));
        moodcardlist.add(MoodCardFragment.newInstance(R.drawable.mood_fidget,R.color.brown));
        moodcardlist.add(MoodCardFragment.newInstance(R.drawable.mood_happy,R.color.yellow));
        moodcardlist.add(MoodCardFragment.newInstance(R.drawable.mood_nervous,R.color.royalblue));
        moodcardlist.add(MoodCardFragment.newInstance(R.drawable.mood_quiet,R.color.cornflowerblue));
        moodcardlist.add(MoodCardFragment.newInstance(R.drawable.mood_sad,R.color.darkkhaki));
        moodcardlist.add(MoodCardFragment.newInstance(R.drawable.mood_satisfied,R.color.orangered));

        titleList.add("1");
        titleList.add("2");
        titleList.add("3");
        titleList.add("4");
        titleList.add("5");
        titleList.add("6");
        titleList.add("7");
        titleList.add("8");
    }

    @Override
    public Fragment getItem(int position) {
        return moodcardlist.get(position);
    }

    @Override
    public int getCount() {
        return moodcardlist.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titleList.get(position);
    }
}
