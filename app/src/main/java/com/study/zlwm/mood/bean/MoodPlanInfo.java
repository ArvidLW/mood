package com.study.zlwm.mood.bean;

/**
 * Created by 31351 on 2016/8/14.
 */
public class MoodPlanInfo {
    public String dayofweek;
    public String tag1;
    public String tag2;
    public String tag3;
    public String plan;
    public String plan_id;
    public String plan_name;
    public MoodPlanInfo(String dayofweek, String tag1, String tag2, String tag3, String plan, String plan_id, String plan_name)
    {
        this.dayofweek=dayofweek;
        this.tag1=tag1;
        this.tag2=tag2;
        this.tag3=tag3;
        this.plan=plan;
        this.plan_id=plan_id;
        this.plan_name=plan_name;
    }
}
