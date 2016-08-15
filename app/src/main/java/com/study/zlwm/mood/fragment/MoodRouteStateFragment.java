package com.study.zlwm.mood.fragment;


import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.TextView;

import com.study.zlwm.mood.R;
import com.study.zlwm.mood.bean.MoodInfo;
import com.study.zlwm.mood.bean.MoodToColor;
import com.study.zlwm.mood.bean.MoodToScore;
import com.study.zlwm.mood.database.SqlDB;
import com.study.zlwm.mood.global.GlobalInfo;
import com.study.zlwm.mood.redefview.My2SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import lecho.lib.hellocharts.listener.PieChartOnValueSelectListener;
import lecho.lib.hellocharts.model.PieChartData;
import lecho.lib.hellocharts.model.SliceValue;
import lecho.lib.hellocharts.view.PieChartView;

/**
 * A simple {@link Fragment} subclass.
 * 统计图表
 */
public class MoodRouteStateFragment extends Fragment {

    private View view;
    private PieChartView chartCensus;
    private PieChartData data;
    List<MoodInfo> moodInfoList=new ArrayList<>();
    private String user_id;
    private String user_name;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_mood_route_state, container, false);
        getUserInfo();

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        chartCensus= (PieChartView) view.findViewById(R.id.chart_census);
        chartCensus.setOnValueTouchListener(new ValueTouchListener());
        generateData();

        //LinearLayout cutline= (LinearLayout) view.findViewById(R.id.image_cutline);
        //cutline.findViewById();
        final GridView gridView= (GridView) view.findViewById(R.id.grid_cutline);
//        String[] iconName = { "通讯录", "日历", "照相机", "时钟", "游戏", "短信", "铃声",
//                "设置", "语音", "天气", "浏览器", "视频" };

        List<Map<String, Object>> data_list = new ArrayList<>();
        //获取数据

        for(int i=0;i<moodInfoList.size();++i)
        {
            Map map=new HashMap();
            map.put("text",moodInfoList.get(i).mood);
            data_list.add(map);
        }
        //新建适配器
        String [] from ={"text"};
        int [] to = {R.id.mood_cutline};
        //sim_adapter = new SimpleAdapter(this, data_list, R.layout.item, from, to);
        //配置适配器
        gridView.setAdapter(new My2SimpleAdapter(getContext(), data_list, R.layout.cutline_item, from, to,moodInfoList));
        //gridView
       // View gridItemMap= (View) gridView.getItemAtPosition(0);

        //http://www.cnblogs.com/Couch-potato/archive/2012/12/11/2813460.html
//        Handler handler = new Handler();
//        handler.postDelayed(new Runnable() {
//            public void run() {
//                // TODO Auto-generated method stub
//                for(int i=0;i<moodInfoList.size();++i)
//                {
//                    gridView.getChildAt(i).findViewById(R.id.mood_color).setBackgroundColor(getResources().getColor(moodInfoList.get(i).color));
//                }
//
//            }
//        }, 500);
        //gridView.
        //System.out.println("111111"+gridItemMap.toString());
    }
    private void getValuesFormDB(){
        int count;
        Cursor cursor;
        moodInfoList=new ArrayList<>();
        SQLiteDatabase db= SqlDB.getSqlDB(getContext());
        MoodToColor mColor=new MoodToColor();

        Iterator iter = mColor.moodColor.entrySet().iterator();
        while (iter.hasNext()) {
            Map.Entry entry = (Map.Entry) iter.next();
            String theMood = entry.getKey().toString();
            String theColor = entry.getValue().toString();
            cursor=db.rawQuery("select count(*) from mood where mood = ? and tel_id= ?",new String[]{theMood,user_id});
            //System.out.println("colors:"+theMood+"|"+theColor);
            cursor.moveToNext();
            count =Integer.parseInt(cursor.getString(0));
            moodInfoList.add(new MoodInfo(0,Integer.parseInt(theColor),theMood,count));
        }
    }

    private void generateData() {
        getValuesFormDB();
        int totalScore=0;
        int totalCount=0;
//        List<SliceValue> values = new ArrayList<SliceValue>();
//        for (int i = 0; i < numValues; i++) {
//            //SliceValue sliceValue = new SliceValue((float) Math.random() * 30 + 15, getResources().getColor(SyncStateContract.Constants.moodColor[i]));
//            SliceValue sliceValue = new SliceValue((float) Math.random() * 100, 0xFF00FFFF);
//            values.add(sliceValue);
//        }
        List<SliceValue> values = new ArrayList<SliceValue>();
        for (int i = 0; i < moodInfoList.size(); i++) {
            //SliceValue sliceValue = new SliceValue((float) Math.random() * 30 + 15, getResources().getColor(SyncStateContract.Constants.moodColor[i]));
            MoodInfo moodInfo=moodInfoList.get(i);
            SliceValue sliceValue = new SliceValue(moodInfo.count, getResources().getColor(moodInfo.color));
            //System.out.println("slicecolors:"+moodInfo.color);
            values.add(sliceValue);

            //计算平均心情指数
            //String mm=moodInfo.mood;
            totalScore+=moodInfo.count*Integer.parseInt(MoodToScore.getMoodScore(moodInfo.mood));
            totalCount+=moodInfo.count;

        }
        //http://blog.csdn.net/gaobaoshen1/article/details/51680342
        data = new PieChartData(values);
        data.setHasLabels(true);//显示数据
        data.setHasLabelsOutside(true);
        data.setHasLabelsOnlyForSelected(false);//不用点击显示占的百分比
        data.setHasLabelsOutside(false);//占的百分比是否显示在饼图外面
        data.setHasCenterCircle(true);//是否是环形显示
        data.setCenterCircleScale(0.5f);////设置环形的大小级别
        //data.setValueLabelBackgroundColor(Color.TRANSPARENT);////设置值得背景透明
        data.setValueLabelBackgroundEnabled(true);//数据背景不显示
        data.setValueLabelsTextColor(Color.WHITE);
        data.setCenterText1("It's me");
        data.setCenterText1FontSize(20);

        chartCensus.setPieChartData(data);

        TextView avergeMoodView= (TextView) view.findViewById(R.id.average_mood);
        if(totalCount==0)
        {
            avergeMoodView.setText("木有");
            return;
        }
        int average_mood=totalScore/totalCount;
        avergeMoodView.setText(String.valueOf(average_mood));

    }


    /**
     * 监听事件
     */
    private class ValueTouchListener implements PieChartOnValueSelectListener {

        @Override
        public void onValueSelected(int arcIndex, SliceValue value) {
            //Toast.makeText(getActivity(), "Selected: " + value, Toast.LENGTH_SHORT).show();

        }

        @Override
        public void onValueDeselected() {

        }

    }
    private void getUserInfo()
    {
        GlobalInfo globalInfo= (GlobalInfo) getActivity().getApplication();
        user_id=globalInfo.getTel_id();
        user_name=globalInfo.getUsername();
    }

}