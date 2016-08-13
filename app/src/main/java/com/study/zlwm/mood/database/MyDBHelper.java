package com.study.zlwm.mood.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
/**
 * Created by 31351 on 2016/7/14.
 */
public class MyDBHelper extends SQLiteOpenHelper {

    final String CERATE_USER_TABLE_SQL="create table user(" +
            "tel_id integer primary key," +
            "name text default null," +
            "password text default null," +
            "sex text default null)," +
            "issyn integer default 0)";
    //tel_id关联键
    final String CREATE_MOOD_TABLE_SQL="create table mood(" +
            "id integer primary key autoincrement," +
            "tel_id integer not null," +
            "mood text default null," +
            "moodscrore integer null," +
            "reason text default null," +
            "image text default null," +
            "dateandtime text default null," +
            "issyn integer default 0)";
    final String CREATE_MOOD_PLAN_SQL="create table mood(" +
            "id integer primary key autoincrement," +
            "tel_id integer not null," +
            "mood text default null," +
            "tag1 text default null," +
            "tag2 text default null," +
            "tag3 text default null," +
            "reason text default null," +
            "dateandtime text default null," +
            "issyn integer default 0)";
    //0表示未同步

    //调用父类构造器
    public MyDBHelper(Context context,String name,int version) {
        super(context, name, null, version);//version版本号标记数据库结构信息以及是否更新，通常如果需要更新数据结构，则设置数大于当前版本号就行
        //dbread = getReadableDatabase();
        //dbwrite = getWritableDatabase();
    }
    /**
     * 当数据库首次创建时执行该方法，一般将创建表等初始化操作放在该方法中执行.
     * 重写onCreate方法，调用execSQL方法创建表
     * */
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CERATE_USER_TABLE_SQL);
        db.execSQL(CREATE_MOOD_TABLE_SQL);
        db.execSQL(CREATE_MOOD_PLAN_SQL);
    }
    //当打开数据库时传入的版本号与当前的版本号不同时会调用该方法
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }

}
