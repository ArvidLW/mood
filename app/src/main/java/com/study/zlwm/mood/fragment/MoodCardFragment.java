package com.study.zlwm.mood.fragment;

import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.study.zlwm.mood.R;
import com.study.zlwm.mood.bean.MoodToScore;
import com.study.zlwm.mood.database.SqlDB;
import com.study.zlwm.mood.redefview.HYSGEditText;

import java.sql.Date;

/**
 * Created by 31351 on 2016/8/11.
 */
public class MoodCardFragment extends Fragment{
    private int drawable_mood;
    private int color;
    private String mood;
    private String user_id;
    private String name;
    public static MoodCardFragment newInstance(int drawable_mood, int color, String mood){
        MoodCardFragment fragment=new MoodCardFragment();
        Bundle args=new Bundle();
        args.putInt("drawable_mood",drawable_mood);
        args.putInt("color",color);
        args.putString("mood",mood);
        fragment.setArguments(args);
        return fragment;
    }
    @TargetApi(Build.VERSION_CODES.M)
    @Override
    public View onCreateView(final LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.mood_card_fragment,container,false);
        view.setBackgroundColor(getResources().getColor(color));
        ImageView imageView= (ImageView) view.findViewById(R.id.mood_face);
        imageView.setImageDrawable(getResources().getDrawable(drawable_mood));//outofmemory
        final TextView textView_plus= (TextView) view.findViewById(R.id.plus);
        textView_plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(),"点击了我:"+mood,Toast.LENGTH_SHORT).show();
                final View view_dialog=inflater.inflate(R.layout.fragment_dialog, null);
                final Dialog dialog=new AlertDialog.Builder(getContext()).setView(view_dialog).show();

                TextView yes_submit= (TextView) view_dialog.findViewById(R.id.yes_submit);
                yes_submit.setOnClickListener(new View.OnClickListener(){

                    @Override
                    public void onClick(View view) {

                        HYSGEditText hysgEditText= (HYSGEditText) view_dialog.findViewById(R.id.myedit);
                        String reason=hysgEditText.getText().toString();
                        Toast.makeText(getContext(),"这是你编辑的内容："+reason,Toast.LENGTH_SHORT).show();
                        Date nowDate=new Date(System.currentTimeMillis());//获得当前时间
                        String date=nowDate.toString();
                        SQLiteDatabase db=SqlDB.getSqlDB(getContext());
                        Cursor cursor=db.rawQuery("select * from mood where dateandtime=?",new String[]{date});

                        if(cursor.getCount()==0){
                            getUserInfo();
                            db.execSQL("insert into mood ('tel_id','mood','moodscore','reason','dateandtime') values(?,?,?,?,?)"
                                    ,new String[] {user_id, mood, MoodToScore.getMoodScore(mood), reason, date} );
//                            db.execSQL("insert into mood ('tel_id','dateandtime') values(?,?)"
//                                    ,new String[] {user_id, date} );
                            Toast.makeText(getContext(),"记录成功",Toast.LENGTH_SHORT).show();

                        }else{
                            Toast.makeText(getContext(),"你发了一条了，要更新它吗",Toast.LENGTH_SHORT).show();
                        }
                        dialog.dismiss();

                    }
                });
                //textView_plus.setTextColor(0xffffffff);
            }
        });
        return view;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle args = getArguments();
        if (args != null) {
            drawable_mood = args.getInt("drawable_mood");
            color=args.getInt("color");
            mood=args.getString("mood");
        }
        //System.out.println("wwwwwww");
    }
//    public int getColor()
//    {
//        return color;
//    }
    public void getUserInfo() {
        SharedPreferences preferences=getContext().getSharedPreferences("userInfo", Context.MODE_PRIVATE);
        //第二个参数为默认值
        user_id=preferences.getString("user_id", "指路为码");
        name=preferences.getString("name", "华为比赛");

    }
}
