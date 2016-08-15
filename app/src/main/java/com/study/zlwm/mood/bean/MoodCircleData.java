package com.study.zlwm.mood.bean;

/**
 * Created by 31351 on 2016/8/15.
 */
public class MoodCircleData {
//    moodDataMap.put("head_image",R.drawable.user_head_image1);
//    moodDataMap.put("user_name","lw");
//    moodDataMap.put("user_mood","很好啊");
//    moodDataMap.put("user_location","成都");
//    moodDataMap.put("user_plan","计划url");
    public int head_image;
    public String user_name;
    public String user_mood;
    public String user_location;
    public String user_plan;

    public MoodCircleData(int head_image,String user_name,String user_mood,String user_location,String user_plan)
    {
        this.head_image=head_image;
        this.user_name=user_name;
        this.user_mood=user_mood;
        this.user_location=user_location;
        this.user_plan=user_plan;
    }
}
