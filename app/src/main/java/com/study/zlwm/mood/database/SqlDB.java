package com.study.zlwm.mood.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by 31351 on 2016/8/14.
 */
public class SqlDB {
    private  SQLiteDatabase sqLiteDatabase;
    public SqlDB(Context context) {
        MyDBHelper myDBHelper=new MyDBHelper(context,"zlwm_mood.db",null,1);
        sqLiteDatabase=myDBHelper.getWritableDatabase();
    }
    public static SQLiteDatabase getSqlDB(Context context)
    {
        SqlDB sqlDB=new SqlDB(context);
        return sqlDB.sqLiteDatabase;
    }
}
