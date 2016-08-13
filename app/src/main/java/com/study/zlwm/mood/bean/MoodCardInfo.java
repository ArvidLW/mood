package com.study.zlwm.mood.bean;

/**
 * Created by 31351 on 2016/8/13.
 */
public class MoodCardInfo {

    public int drawable_mood;
    public int color;
    public String mood;
    public MoodCardInfo(int drawable_mood,int color,String mood){

        this.drawable_mood=drawable_mood;
        this.color=color;
        this.mood=mood;
    }
}
