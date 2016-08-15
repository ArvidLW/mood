package com.study.zlwm.mood.activity;

/**
 * Created by 31351 on 2016/8/13.
 */

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

public class RegisterActivity extends AppCompatActivity {
    private EditText user_name_et;
    private EditText user_pass_et;
    private EditText user_tel;
    private Button register_bt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);//程序应用图标对应id为home

        user_tel= (EditText) findViewById(R.id.user_tel);
        user_name_et= (EditText) findViewById(R.id.user_name_et);
        user_pass_et= (EditText) findViewById(R.id.user_pass_et);
        register_bt= (Button) findViewById(R.id.submit_bt);
        register_bt.setText("注册");
        register_bt.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(getBaseContext(),"来了",Toast.LENGTH_SHORT);
                //SQLiteOpenHelper 的构造函数，当数据库不存在时，就会创建数据库，然后打开数据库
                //MyDBHelper myDBHelper=new MyDBHelper(getBaseContext(),"zlwm_mood.db",null,1);
                String user_id=user_tel.getText().toString().trim();
                String user_name=user_name_et.getText().toString().trim();
                String password=user_pass_et.getText().toString().trim();
                if(user_id.isEmpty()||password.isEmpty())
                {
                    Toast.makeText(getBaseContext(),"用户名或密码不对",Toast.LENGTH_SHORT).show();
                    return;
                }
                SQLiteDatabase db= SqlDB.getSqlDB(getBaseContext());
                Cursor cursor=db.rawQuery("select * from user where tel_id=?",new String[]{user_id});
                if(cursor.getCount()==0)
                {
                    db.execSQL("insert into user ('tel_id','name','password') values(?,?,?)",new String[]{user_id,user_name,password});
                    Toast.makeText(getBaseContext(),"注册成功了",Toast.LENGTH_SHORT).show();

                }
                else{
                    Toast.makeText(getBaseContext(),"已经注册过该用户了",Toast.LENGTH_SHORT).show();
                }
                cursor.close();

            }
        });

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
