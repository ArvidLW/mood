package com.study.zlwm.mood.bean;

import java.sql.Date;
/**
 * Created by 31351 on 2016/8/13.
 */
public class MoodData {
    public int moodScore;
    public String mood;
    public Date date;
    public String reason;
    public MoodData(int moodScore,String mood,Date date,String reason)
    {
        this.moodScore=moodScore;
        this.mood=mood;
        this.date=date;
        this.reason=reason;
    }
}
