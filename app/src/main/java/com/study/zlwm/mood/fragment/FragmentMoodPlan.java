package com.study.zlwm.mood.fragment;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.study.zlwm.mood.R;
import com.study.zlwm.mood.activity.EditMoodPlanActivity;
import com.study.zlwm.mood.bean.MoodPlanInfo;
import com.study.zlwm.mood.database.SqlDB;
import com.study.zlwm.mood.global.GlobalInfo;
import com.study.zlwm.mood.redefview.MySimpleAdapter;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by lw on 2016/7/5.
 */
public class FragmentMoodPlan extends Fragment {
    @Nullable
//    private View myview;
    private ListView listView;
    private Map<String,MoodPlanInfo> planMap;
    private String user_id;
    private String user_name;
    private List<Map<String,Object>> listItems;
    private MySimpleAdapter simpleAdapter;

    //private Map map;
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_mood_plan,container,false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        listView= (ListView) view.findViewById(R.id.mood_plan_list);
        getValuesFormDB();
        listItems = new ArrayList<>();
        Map<String,Object> listItem;
        for(int i=0;i<planMap.size();++i)
        {

            listItem= new HashMap<>();
            MoodPlanInfo moodPlanInfo=planMap.get(String.valueOf(i+1));
            listItem.put("week","星期"+(i+1));
            listItem.put("tag",moodPlanInfo.tag1+","+moodPlanInfo.tag2+","+moodPlanInfo.tag2);
            listItem.put("simCon",moodPlanInfo.plan);
            listItems.add(listItem);
        }
        int colors[]={R.color.w1_yellow,R.color.w2_blue,R.color.w3_light_red,
                R.color.burlywood,R.color.lightblue,R.color.peachpuff,R.color.turquoise};

        simpleAdapter = new MySimpleAdapter(getContext(), listItems, R.layout.mood_plan_item,
                new String[]{"week","tag","simCon","myTag"},
                new int[]{ R.id.week, R.id.moodTag, R.id.moodSimCon},colors );
        listView.setAdapter(simpleAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                System.out.println("pppppppppp:"+position);
                Intent intent=new Intent();
                intent.setClass(getContext(),EditMoodPlanActivity.class);
                //传值可以用intent键值对，也可以用bundle
                //当用bundle时用putExtras否则用putExtra

                Bundle bundle=new Bundle();
                String p=String.valueOf(position+1);
                MoodPlanInfo thePlanInfo=planMap.get(p);
//                bundle.putString("thePlanInfo",thePlanInfo);
                bundle.putString("dayofweek",p);
                bundle.putString("plan_id",thePlanInfo.plan_id);
                bundle.putString("tag1",thePlanInfo.tag1);
                bundle.putString("tag2",thePlanInfo.tag2);
                bundle.putString("tag3",thePlanInfo.tag3);
                bundle.putString("plan",thePlanInfo.plan);
                intent.putExtras(bundle);
//                intent.putExtra("dayofweek",p);
//                intent.putExtra("plan_id",planMap.get(p).plan_id);
                startActivityForResult(intent,2);

            }
        });

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        //super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 2 && resultCode == 2) {
            Bundle bundle = data.getExtras();

            //Map map=listItems.get(Integer.parseInt(bundle.getString("dayofweek"))-1);
//            Map map=new HashMap();
            String dow=bundle.getString("dayofweek");
            //LinearLayout viewItem= (LinearLayout) ((ListView)getView().findViewById(R.id.mood_plan_ );
            //listView= (ListView) getView().findViewById(R.id.mood_plan_list);
            int p=Integer.parseInt(dow)-1;
            LinearLayout viewItem= (LinearLayout) listView.getChildAt(p-listView.getFirstVisiblePosition());
//            if(viewItem==null)
//            {
//                System.out.println("aaaaaaaaa:为空");
//            }
            TextView moodTag= (TextView) viewItem.findViewById(R.id.moodTag);
            TextView simCon= (TextView) viewItem.findViewById(R.id.moodSimCon);
            String tag1=bundle.getString("tag1");
            String tag2=bundle.getString("tag2");
            String tag3=bundle.getString("tag3");
            String plan=bundle.getString("plan_content");
            moodTag.setText(tag1+","+tag2+","+tag3);
            simCon.setText(plan);

            //更新planmap中dow对应的数据
            MoodPlanInfo planInfo=planMap.get(dow);
            planMap.remove(dow);
            planMap.put(dow,new MoodPlanInfo(dow,tag1,tag2,tag3,plan,planInfo.plan_id,planInfo.plan_name));

            Map item=new HashMap<>();
            item.put("week","星期"+dow);
            item.put("tag",tag1+","+tag2+","+tag3);
            item.put("simCon",plan);

            listItems.set(p,item);
            simpleAdapter.notifyDataSetChanged();


//            listItem= new HashMap<>();
//            MoodPlanInfo moodPlanInfo=planMap.get(String.valueOf(i+1));
//            listItem.put("week","星期"+(i+1));
//            listItem.put("tag",moodPlanInfo.tag1+","+moodPlanInfo.tag2+","+moodPlanInfo.tag2);
//            listItem.put("simCon",moodPlanInfo.plan);
//            listItems.add(listItem);
            //listView.setAdapter();

        }
    }

    //要有一个默认计划
    //如果没有登录，显示默认计划，登录了如果没有计划显示默认计划，但可以改。也就是没有登录则显示默认计划
    public void getValuesFormDB(){

        SQLiteDatabase db= SqlDB.getSqlDB(getContext());
        GlobalInfo globalInfo= (GlobalInfo) getActivity().getApplication();
        user_id=globalInfo.getTel_id();
        user_name=globalInfo.getUsername();
        Cursor cursor;
        planMap=new HashMap();
        String sql="select dayofweek,tag1,tag2,tag3,plan,plan_id,plan_name from mood_plan where tel_id = ? and iscurrent=1";
        //现在用户默认为指路为码所以就有用户了。

        //如果用户没登录，则创建默认计划tel_id=12345678901
        //如果用户登录了,有目录默认计划,isCurrent因为可能有多个计划
        //默认登录用户为指路为码，那么以后有新的用户，当为指路为码时再触发改变tel_id
        cursor=db.rawQuery(sql,new String[]{user_id});
        if(cursor.getCount() > 0){
            while(cursor.moveToNext())
            {
                //System.out.println("nnnn:"+cursor.getString(0));
                planMap.put(cursor.getString(0),new MoodPlanInfo(cursor.getString(0),cursor.getString(1),cursor.getString(2),cursor.getString(3),cursor.getString(4),cursor.getString(5),cursor.getString(6)));
            }
        }else{
            createDefaultMoodPlan(db,user_id);
            cursor=db.rawQuery(sql,new String[]{user_id});
            while(cursor.moveToNext())
            {
               // System.out.println("llll:"+cursor.getString(0));
                planMap.put(cursor.getString(0),new MoodPlanInfo(cursor.getString(0),cursor.getString(1),cursor.getString(2),cursor.getString(3),cursor.getString(4),cursor.getString(5),cursor.getString(6)));
            }
        }
        cursor.close();

    }
    public void createDefaultMoodPlan(SQLiteDatabase db,String userId){
        final String INSERT_DEFAULT_SQL="insert into mood_plan ('tel_id','tag1','tag2','tag3','plan','plan_id','plan_name','dayofweek','dateandtime','iscurrent','issyn') values(?,'高兴','淡然','加油','看开看开,风吹裤裆子',?,'默认',?,'2016-08-14','1','0')";
        //创建默认心情计划，并设置唯一id,和随便的tel_id,当用户登录时把这个tel_id改成它的，那当手机上另一个用户登录时呢？查不到时再创建
        //并设置为当前计划
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyyMMddHHmmss");
        String plan_id=simpleDateFormat.format(new Date());

        for(int i=0;i<7;++i)
        {
            db.execSQL(INSERT_DEFAULT_SQL,new String[]{userId,plan_id,String.valueOf(i+1)});
        }
    }
    /*
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String[] array = {"a","b","c","d","e","f","g"};
        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1, array);
        //Log.d("eeee",listView.toString());
        listView.setAdapter(adapter);
    }
    注意生命周期*/
}
