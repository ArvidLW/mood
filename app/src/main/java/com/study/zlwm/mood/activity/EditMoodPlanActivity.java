package com.study.zlwm.mood.activity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.study.zlwm.mood.R;
import com.study.zlwm.mood.database.SqlDB;

/**
 * Created by 31351 on 2016/7/12.
 */
public class EditMoodPlanActivity extends AppCompatActivity {


    private Bundle bundle;
    private String[] planData;
    private TextView dayOfWeek;
    private EditText planTag1;
    private EditText planTag2;
    private EditText planTag3;
    private EditText planContent;
    private Button saveButton;

    private String plan_id;
    private String dayofweek;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_mood_plan);
        Toolbar toolbar = (Toolbar) findViewById(R.id.mood_edit_toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);//程序应用图标对应id为home

        //Intent intent=getIntent();
        this.bundle=getIntent().getExtras();
        //专门保存免得把bundle结kill了得不到值
        plan_id=bundle.getString("plan_id");
        dayofweek=bundle.getString("dayofweek");

//        Toast.makeText(getBaseContext(),"得到："
//                +bundle.getString("plan_id")+"|"
//                +bundle.getString("dayofweek")+"|"
//                +bundle.getString("tag1")+"|"
//                +bundle.getString("tag2")+"|"
//                +bundle.getString("tag3")+"|"
//                +bundle.getString("plan")+"|"
//                ,Toast.LENGTH_SHORT).show();
        setViewContent();
        saveButton= (Button) findViewById(R.id.save_day_plan);
        saveButton.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(savePlan()){
                    Toast.makeText(getBaseContext(),"保存成功",Toast.LENGTH_SHORT).show();
                }
            }
        });




    }

    public void setViewContent()
    {
        dayOfWeek= (TextView) findViewById(R.id.dayofweek);
        planTag1= (EditText) findViewById(R.id.mood_plan_tag1);
        planTag2= (EditText) findViewById(R.id.mood_plan_tag2);
        planTag3= (EditText) findViewById(R.id.mood_plan_tag3);
        planContent= (EditText) findViewById(R.id.mood_plan_content);

        dayOfWeek.setText("星期"+dayofweek);
        planTag1.setText(bundle.getString("tag1"));
        planTag2.setText(bundle.getString("tag2"));
        planTag3.setText(bundle.getString("tag3"));
        planContent.setText(bundle.getString("plan"));

    }
    public boolean savePlan(){

        planData=new String[6];

        planData[0]= planTag1.getText().toString();
        planData[1]= planTag2.getText().toString();
        planData[2]= planTag3.getText().toString();
        planData[3]= planContent.getText().toString();
        planData[4]= plan_id;
        planData[5]= dayofweek;

        SQLiteDatabase db= SqlDB.getSqlDB(getBaseContext());
        db.execSQL("update mood_plan set tag1=?,tag2=?,tag3=?,plan=? where plan_id=? and dayofweek=?",planData);
        //isSaved=true;
        return true;

    }
    public void setResultValue()
    {
        if(planData!=null)
        {
            Intent intent = new Intent();
            Bundle bd=new Bundle();
            bd.putString("tag1",planData[0]);
            bd.putString("tag2",planData[1]);
            bd.putString("tag3",planData[2]);
            bd.putString("plan_content",planData[3]);
            bd.putString("dayofweek",planData[5]);
            intent.putExtras(bd);
//            intent.putExtra("user_id", user_id);
//            intent.putExtra("name",name);
            setResult(2, intent);// 设置resultCode，onActivityResult()中能获取到
        }

    }


    @Override
    //返回先前的activity
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                setResultValue();
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }


}
