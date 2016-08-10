package com.study.zlwm.mood;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.study.zlwm.mood.fragment.MoodCardFragment;

import java.util.ArrayList;

/**
 * Created by 31351 on 2016/8/10.
 */
public class MoodFragmentAdapter extends FragmentPagerAdapter {
    private ArrayList<MoodCardFragment> moodcardlist=new ArrayList<>();

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

    }

    @Override
    public Fragment getItem(int position) {
        return moodcardlist.get(position);
    }

    @Override
    public int getCount() {
        return moodcardlist.size();
    }
}