package com.study.zlwm.mood.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.study.zlwm.mood.bean.MoodCardInfo;
import com.study.zlwm.mood.fragment.MoodCardFragment;

import java.util.ArrayList;

/**
 * Created by 31351 on 2016/8/10.
 */
public class MoodFragmentAdapter extends FragmentPagerAdapter {
    private ArrayList<MoodCardFragment> moodcardlist;
    private ArrayList<String> titleList=new ArrayList<>();
    public MoodFragmentAdapter(FragmentManager fm, ArrayList<MoodCardInfo> moodcardinfolist) {
        super(fm);

        moodcardlist=new ArrayList<>();
        MoodCardInfo moodInfo;
        for(int i=0; i< moodcardinfolist.size();++i)
        {

            moodInfo=moodcardinfolist.get(i);
            moodcardlist.add(MoodCardFragment.newInstance(moodInfo.drawable_mood,moodInfo.color,moodInfo.mood));
        }
//        moodcardlist.add(MoodCardFragment.newInstance(R.drawable.mood_afraid,R.color.blueviolet,"afraid"));
//        moodcardlist.add(MoodCardFragment.newInstance(R.drawable.mood_depressed,R.color.gray,"depressed"));
//        moodcardlist.add(MoodCardFragment.newInstance(R.drawable.mood_fidget,R.color.brown,"fidget"));
//        moodcardlist.add(MoodCardFragment.newInstance(R.drawable.mood_happy,R.color.yellow,"happy"));
//        moodcardlist.add(MoodCardFragment.newInstance(R.drawable.mood_nervous,R.color.royalblue,"nervous"));
//        moodcardlist.add(MoodCardFragment.newInstance(R.drawable.mood_quiet,R.color.cornflowerblue,"quiet"));
//        moodcardlist.add(MoodCardFragment.newInstance(R.drawable.mood_sad,R.color.darkkhaki,"sad"));
//        moodcardlist.add(MoodCardFragment.newInstance(R.drawable.mood_satisfied,R.color.orangered,"satisfied"));
        //System.out.println("hhhhhhhh");

       /* titleList.add("11");
        titleList.add("21");
        titleList.add("31");
        titleList.add("41");
        titleList.add("51");
        titleList.add("61");
        titleList.add("71");
        titleList.add("81");*/
    }

    @Override
    public Fragment getItem(int position) {
        return moodcardlist.get(position);
    }

    @Override
    public int getCount() {
        return moodcardlist.size();
    }

   /* @Override
    public CharSequence getPageTitle(int position) {
        return titleList.get(position);
    }
*/

}
