package com.study.zlwm.mood.bean;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by 31351 on 2016/8/14.
 */
public class MoodToScore {
    public static Map moodScore=new HashMap();
    public MoodToScore(){
        moodScore.put("happy",100);
        moodScore.put("satisfied",95);
        moodScore.put("quiet",70);
        moodScore.put("sad",50);
        moodScore.put("fidget",30);
        moodScore.put("afraid",10);
        moodScore.put("nervous",10);
        moodScore.put("depressed",0);
    }
    public static String getMoodScore(String s){
        if(moodScore.isEmpty())
        {
            MoodToScore map=new MoodToScore();
            return map.moodScore.get(s).toString();
        }
        else{
            return moodScore.get(s).toString();
        }


    }

}
