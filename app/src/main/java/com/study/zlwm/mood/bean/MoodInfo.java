package com.study.zlwm.mood.bean;

/**
 * Created by 31351 on 2016/8/14.
 */
public class MoodInfo extends MoodCardInfo{
    public int count;
    public  MoodInfo(int drawable_mood, int color, String mood,int count) {
        super(drawable_mood, color, mood);
        this.count=count;
    }
}
