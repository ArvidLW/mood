package com.study.zlwm.mood;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by 31351 on 2016/7/14.
 */
public abstract class Users_db extends SQLiteOpenHelper {
    public SQLiteDatabase dbread,dbwrite;
    private static String Current_User;

    public Users_db(Context context) {
        super(context, "users_db", null, 1);//version版本号标记数据库结构信息以及是否更新，通常如果需要更新数据结构，则设置数大于当前版本号就行
        dbread = getReadableDatabase();
        dbwrite = getWritableDatabase();
    }

    /**
     * 在需要同步的Activity里，实现这个函数
     * 函数的功能是上传一个用户的一条心情记录
     */
    public abstract void syc(String user_name,
                             String mood,
                             String reasontag,
                             String reasondesc,
                             String moodimage,
                             Date date);

    /**
     * 上传当前用户中，所有未同步的心情记录,使用前要确保实现syc函数
     */
    public void syc_item(){
        Cursor c = dbread.rawQuery("SELECT * FROM "+Current_User+" WHERE issynchro = 0",null);
        while(c.moveToNext()){
            syc(Current_User,c.getString(1),c.getString(2),c.getString(3),c.getString(4),new Date(c.getString(5)));
        }
        ContentValues cv = new ContentValues();
        cv.put("issynchro", 1);
        dbwrite.update(Current_User, cv, "issynchro=?", new String[]{0+""});
    }

    /**
     * 设置当前登录的用户,在登录界面，
     * 或者切换用户时,调用一次就好
     */
    public void setUser(String name){
        Current_User = name;
        if(!check_user(Current_User))
            create_user(Current_User);
    }
    /**
     * 为当前的用户增加一条心情记录
     */
    public void add_mood_item(String mood,String reasontag,String reasondesc,String moodimage){
        ContentValues cv=new ContentValues();
        cv.put("mood", mood);
        cv.put("reasontag", reasontag);
        cv.put("reasondesc", reasondesc);
        cv.put("moodimage", moodimage);
        cv.put("dateandtime", new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date()));
        cv.put("issynchro", 0);
        dbwrite.insert(Current_User, null, cv);
    }

    /**
     * 查询从过去n天到现在的所有心情记录
     */
    public Cursor query_past(int n){
        Date d = new Date();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        String past = df.format(new Date(d.getTime() - (long)24*3600*n*1000));
        past += " 00:00:00";
        return dbread.rawQuery("SELECT * FROM "+Current_User+" WHERE dateandtime > ?", new String[]{past});
    }

    /**
     * 在activity关闭时调用
     */
    public void db_close(){
        dbread.close();
        dbwrite.close();
    }

    private void create_user(String name){
        dbwrite.execSQL("CREATE TABLE "+name+"(" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "mood TEXT DEFAULT NULL," +
                "reasontag TEXT DEFAULT NULL," +
                "reasondesc TEXT DEFAULT NULL," +
                "moodimage TEXT DEFAULT NULL," +
                "dateandtime TEXT DEFAULT NULL," +
                "issynchro INTEGER DEFAULT 0 " +
                ")");
    }

    private boolean check_user(String name){
        return dbread.rawQuery(
                "SELECT name FROM sqlite_master WHERE type='table' AND name='"+name +"' ",
                null).moveToNext();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }
}
