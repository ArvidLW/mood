package com.study.zlwm.mood.activity;

/**
 * Created by 31351 on 2016/8/13.
 */

import android.content.Intent;
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
import com.study.zlwm.mood.database.SqlDB;
import com.study.zlwm.mood.global.GlobalInfo;

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
                //MyDBHelper myDBHelper=new MyDBHelper(getBaseContext(),"zlwm_mood.db",null,1);
                user_id=user_name_et.getText().toString().trim();
                name="心情";
                String password=user_pass_et.getText().toString().trim();
                if(user_id.isEmpty()||password.isEmpty())
                {
                    Toast.makeText(getBaseContext(),"用户名或密码不对",Toast.LENGTH_SHORT).show();
                    return;
                }
                //SQLiteDatabase db=myDBHelper.getWritableDatabase();
                SQLiteDatabase db= SqlDB.getSqlDB(getBaseContext());
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
                cursor.close();

            }
        });

    }
    public void dataSetAndSave()
    {
//        //登录成功后共享变量更新
//        SharedPreferences preferences=getSharedPreferences("userInfo", Context.MODE_PRIVATE);
//        SharedPreferences.Editor editor=preferences.edit();
//        editor.putString("user_id",user_id);
//        editor.putString("name",name);
//        editor.commit();

        Intent intent = new Intent();
        intent.putExtra("user_id", user_id);
        intent.putExtra("name",name);
        setResult(1, intent);// 设置resultCode，onActivityResult()中能获取到
        finish();

//        //设置全局变量
        GlobalInfo globalInfo= (GlobalInfo) getApplication();
        String old_user_id=globalInfo.getTel_id();//得到以前的userid
        System.out.println("ooooooo:"+old_user_id);
        globalInfo.setTel_id(user_id);
        globalInfo.setUsername(name);

        //设置数据库，如果为tel_id为未登录那么将其更新为现在的id,即默认数据库变为现有的
        if(old_user_id.equals("未登录")){
            SQLiteDatabase db=SqlDB.getSqlDB(getBaseContext());
            db.execSQL("update mood_plan set tel_id=? where tel_id=?",new String[]{user_id,old_user_id});
        }

    }

    @Override
    //返回先前的activity
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
//                Intent intent = new Intent();
//                intent.putExtra("user_id", user_id);
//                intent.putExtra("name",name);
//                setResult(1, intent);// 设置resultCode，onActivityResult()中能获取到
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }



}
