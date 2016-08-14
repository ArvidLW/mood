package com.study.zlwm.mood.bean;

import com.study.zlwm.mood.R;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by 31351 on 2016/8/14.
 */
public class MoodToColor {
    public static Map moodColor=new HashMap();
    public MoodToColor(){
        //moodColor=new HashMap();
        moodColor.put("happy",R.color.yellow);
        moodColor.put("satisfied",R.color.orangered);
        moodColor.put("quiet",R.color.cornflowerblue);
        moodColor.put("sad",R.color.darkkhaki);
        moodColor.put("fidget",R.color.brown);
        moodColor.put("afraid",R.color.blueviolet);
        moodColor.put("nervous",R.color.royalblue);
        moodColor.put("depressed",R.color.gray);
//        moodCardInfoList.add(new MoodCardInfo(R.drawable.mood_afraid,R.color.blueviolet,"afraid"));
//        moodCardInfoList.add(new MoodCardInfo(R.drawable.mood_depressed,R.color.gray,"depressed"));
//        moodCardInfoList.add(new MoodCardInfo(R.drawable.mood_fidget,R.color.brown,"fidget"));
//        moodCardInfoList.add(new MoodCardInfo(R.drawable.mood_happy,R.color.yellow,"happy"));
//        moodCardInfoList.add(new MoodCardInfo(R.drawable.mood_nervous,R.color.royalblue,"nervous"));
//        moodCardInfoList.add(new MoodCardInfo(R.drawable.mood_quiet,R.color.cornflowerblue,"quiet"));
//        moodCardInfoList.add(new MoodCardInfo(R.drawable.mood_sad,R.color.darkkhaki,"sad"));
//        moodCardInfoList.add(new MoodCardInfo(R.drawable.mood_satisfied,R.color.orangered,"satisfied"));
    }
    public static String getMoodColor(String s){
        if(moodColor.isEmpty())
        {
            MoodToColor map=new MoodToColor();
            return map.moodColor.get(s).toString();
        }else {
            return moodColor.get(s).toString();
        }

    }
}
