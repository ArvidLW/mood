package com.study.zlwm.mood.activity;

/**
 * Created by 31351 on 2016/8/13.
 */

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;

import com.study.zlwm.mood.R;

public class LoginActivity extends AppCompatActivity {
    private EditText user_name_et;
    private EditText user_pass_et;
    private Button login_bt;
    private Button return_bt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);//程序应用图标对应id为home

        user_name_et= (EditText) findViewById(R.id.user_name_et);
        user_pass_et= (EditText) findViewById(R.id.user_pass_et);
        login_bt= (Button) findViewById(R.id.login_bt);
        return_bt= (Button) findViewById(R.id.return_bt);

    }


    @Override
    //返回先前的activity
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }



}
