package com.study.zlwm.mood.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by 31351 on 2016/7/14.
 */
public class MyDBHelper extends SQLiteOpenHelper {
    //注册成功后将id和密码保存到本地，然后本地可以登录。关于过期什么的以后再说吧
    //那我本地可以先注册一个号嘛。

    /**是SQLiteOpenHelper 的构造函数，当数据库不存在时，就会创建数据库，
    然后打开数据库（过程已经被封装起来了），再调用onCreate (SQLiteDatabase db)方法来执行创建表之类的操作。
    当数据库存在时，SQLiteOpenHelper 就不会调用onCreate (SQLiteDatabase db)方法了，
    它会检测版本号，若传入的版本号高于当前的，就会执行onUpgrade()方法来更新数据库和版本号。
    */
    public MyDBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context,name,factory,version);
    }

    final String CERATE_USER_TABLE_SQL="create table if not exists user(" +
            "tel_id integer primary key," +
            "name text default null," +
            "password text default null," +
            "sex text default null," +
            "issyn integer default 0)";
    //tel_id关联键
    final String CREATE_MOOD_TABLE_SQL="create table if not exists mood(" +
            "id integer primary key autoincrement," +
            "tel_id text not null," +
            "mood text default null," +
            "moodscore integer null," +
            "reason text default null," +
            "image text default null," +
            "dateandtime text default null," +
            "issyn integer default 0)";
    /**
     * 注意一个心情计划由一周七天的计划组成，但是一条信息存储一天的，并用plan_id进行关联
     */
    final String CREATE_MOOD_PLAN_SQL="create table if not exists mood_plan(" +
            "id integer primary key autoincrement," +
            "tel_id text not null," +
            "tag1 text default null," +
            "tag2 text default null," +
            "tag3 text default null," +
            "plan text default null," +
            "plan_id text not null," + //记录哪一个编号，这个编号最好能在服务器上保证唯一
            "plan_name text not null," +
            "dayofweek text not null," + //记录是一周的第几天
            "dateandtime text default null," +
            "iscurrent integer default 0," +
            "issyn integer default 0)";
    //0表示未同步
//    final String INSERT_DEFAULT_SQL="insert into mood_plan values('',12345678901,'高兴','淡然','加油','看开看开,风吹裤裆子',?,'默认',?,'2016-08-14','0')";


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
        db.execSQL(CERATE_USER_TABLE_SQL);//检测有没有表 if not exists 时创建
        db.execSQL(CREATE_MOOD_TABLE_SQL);
        db.execSQL(CREATE_MOOD_PLAN_SQL);

        //创建默认心情计划，并设置唯一id,和随便的tel_id,当用户登录时把这个tel_id改成它的，那当手机上另一个用户登录时呢？查不到时再创建
//        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyyMMddHHmmss");
//        String plan_id=simpleDateFormat.format(new Date());
//        for(int i=0;i<7;++i)
//        {
//            db.execSQL(INSERT_DEFAULT_SQL,new String[]{plan_id,String.valueOf(i)});
//        }

    }
    //当打开数据库时传入的版本号与当前的版本号不同时会调用该方法,当登录时
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }

//    public void insertDefaultMoodPlan()
//    {
//        String
//        final String INSERT_DEFAULT_SQL="insert into mood_plan values('',12345678901,'高兴','淡然','加油','看开看开,风吹裤裆子',?,'默认',?,'2016-08-14','0')";
//    }

}
