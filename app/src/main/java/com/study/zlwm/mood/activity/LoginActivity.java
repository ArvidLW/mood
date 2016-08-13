package com.study.zlwm.mood.activity;

/**
 * Created by 31351 on 2016/8/13.
 */

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.study.zlwm.mood.R;
import com.study.zlwm.mood.database.MyDBHelper;

public class LoginActivity extends AppCompatActivity {
    private EditText user_name_et;
    private EditText user_pass_et;
    private Button login_bt;
    private String user_id;
    private String name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_register);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);//程序应用图标对应id为home

        user_name_et= (EditText) findViewById(R.id.user_name_et);
        user_pass_et= (EditText) findViewById(R.id.user_pass_et);
        login_bt= (Button) findViewById(R.id.submit_bt);
        login_bt.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(getBaseContext(),"来了",Toast.LENGTH_SHORT);
                //SQLiteOpenHelper 的构造函数，当数据库不存在时，就会创建数据库，然后打开数据库
                MyDBHelper myDBHelper=new MyDBHelper(getBaseContext(),"zlwm_mood.db",null,1);
                user_id=user_name_et.getText().toString().trim();
                name="刘炜";
                String password=user_pass_et.getText().toString().trim();
                if(user_id.isEmpty()||password.isEmpty())
                {
                    Toast.makeText(getBaseContext(),"用户名或密码不对",Toast.LENGTH_SHORT).show();
                    return;
                }
                SQLiteDatabase db=myDBHelper.getWritableDatabase();
                Cursor cursor=db.rawQuery("select * from user where tel_id=? and password=?",new String[]{user_id,password});
                if(cursor.getCount()==0)
                {
                    //db.execSQL("insert into user ('tel_id','name','password') values(?,?,?)",new String[]{user_id,"刘炜",password});
                    Toast.makeText(getBaseContext(),"用户名或密码不对",Toast.LENGTH_SHORT).show();

                }
                else{
                    Toast.makeText(getBaseContext(),"登录成功",Toast.LENGTH_SHORT).show();
                    //进行登录成功的相关操作
                    //回传值，关闭activity
                    dataSetAndSave();
                    // 可以设置全局变量

                }

            }
        });

    }
    public void dataSetAndSave()
    {
        SharedPreferences preferences=getSharedPreferences("userInfo", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=preferences.edit();
        editor.putString("user_id",user_id);
        editor.putString("name",name);
        editor.commit();

        Intent intent = new Intent();
        intent.putExtra("user_id", user_id);
        intent.putExtra("name",name);
        setResult(1, intent);// 设置resultCode，onActivityResult()中能获取到
        finish();
//      GlobalInfo globalInfo= (GlobalInfo) getApplication();
//      globalInfo.setTel_id(user_id);
//      globalInfo.setUsername(name);
    }

    @Override
    //返回先前的activity
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                Intent intent = new Intent();
                intent.putExtra("user_id", user_id);
                intent.putExtra("name",name);
                setResult(1, intent);// 设置resultCode，onActivityResult()中能获取到
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }



}
