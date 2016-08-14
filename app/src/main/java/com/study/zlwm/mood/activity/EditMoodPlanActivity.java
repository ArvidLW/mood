package com.study.zlwm.mood.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;

import com.study.zlwm.mood.R;

/**
 * Created by 31351 on 2016/7/12.
 */
public class EditMoodPlanActivity extends AppCompatActivity {
    private EditText user_name_et;
    private EditText user_pass_et;
    private Button register_bt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_mood_plan);
        Toolbar toolbar = (Toolbar) findViewById(R.id.mood_edit_toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);//程序应用图标对应id为home



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
