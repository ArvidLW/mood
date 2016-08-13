package com.study.zlwm.mood.global;

import android.app.Application;

/**
 * Created by 31351 on 2016/8/14.
 */
public class GlobalInfo extends Application {
    private String tel_id;
    private String username;

    public String getTel_id() {
        return tel_id;
    }

    public String getUsername() {
        return username;
    }

    public void setTel_id(String tel_id) {
        this.tel_id = tel_id;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
