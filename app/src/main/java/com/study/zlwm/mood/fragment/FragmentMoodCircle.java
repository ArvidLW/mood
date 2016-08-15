package com.study.zlwm.mood.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.study.zlwm.mood.R;
import com.study.zlwm.mood.bean.MoodCircleData;
import com.study.zlwm.mood.bean.MoodData;
import com.study.zlwm.mood.redefview.My3SimpleAdapter;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by 31351 on 2016/8/15.
 */
public class FragmentMoodCircle extends Fragment {
    View view;
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view= inflater.inflate(R.layout.fragment_mood_circle,container,false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        ListView listView= (ListView) view.findViewById(R.id.mood_plan_list);

//        getValuesFormDB();
//        listItems = new ArrayList<>();
//        Map<String,Object> listItem;
//        for(int i=0;i<planMap.size();++i)
//        {
//
//            listItem= new HashMap<>();
//            MoodPlanInfo moodPlanInfo=planMap.get(String.valueOf(i+1));
//            listItem.put("week","星期"+(i+1));
//            listItem.put("tag",moodPlanInfo.tag1+","+moodPlanInfo.tag2+","+moodPlanInfo.tag2);
//            listItem.put("simCon",moodPlanInfo.plan);
//            listItems.add(listItem);
//        }
        List<Map<String,Object>> moodDataList=new ArrayList<>();

        List<MoodCircleData> circleDatalist=new ArrayList();

        circleDatalist.add(new MoodCircleData(R.drawable.user_head_image1,"lw","很好啊，想出去看哈风景勒","成都","我分享了心情计划,去看看吧"));
        circleDatalist.add(new MoodCircleData(R.drawable.user_head_image1,"nice",null,"成都","我分享了心情计划,去看看吧"));
        circleDatalist.add(new MoodCircleData(R.drawable.user_head_image1,"y.x",null,"成都","我分享了心情计划,去看看吧"));
        circleDatalist.add(new MoodCircleData(R.drawable.user_head_image1,"mm","红色心情，有你一份","美国","我分享了心情计划,去看看吧"));
        circleDatalist.add(new MoodCircleData(R.drawable.user_head_image1,"gg","天空天空星","成都","我分享了心情计划,去看看吧"));
        circleDatalist.add(new MoodCircleData(R.drawable.user_head_image1,"Arvid","好心情就得有计划啊，哈哈，真不错","上海","我分享了心情计划,去看看吧"));
        circleDatalist.add(new MoodCircleData(R.drawable.user_head_image1,"释然","海边很不错啊，天空是蓝色","烟台","我分享了心情计划,去看看吧"));
        circleDatalist.add(new MoodCircleData(R.drawable.user_head_image1,"老纳","一骑绝尘，小青去哪","成都","我分享了心情计划,去看看吧"));
        circleDatalist.add(new MoodCircleData(R.drawable.user_head_image1,"开发者","敬请期待更加完善","成都","这里先展现一下哦，我们还将继续完善哈！！！"));

        for(int i=0; i< 9;++i)
        {
            Map moodDataMap=new HashMap();

//            int moodScore,String mood,Date date,String reason
            Date date=new Date(System.currentTimeMillis());
            MoodCircleData mcd=circleDatalist.get(i);
            moodDataMap.put("head_image",mcd.head_image);
            moodDataMap.put("user_name",mcd.user_name);
            if(mcd.user_mood==null)
            {
                moodDataMap.put("user_mood",mcd.user_plan);
            }else {
                moodDataMap.put("user_mood",mcd.user_mood);
            }

            moodDataMap.put("user_location",mcd.user_location);
            //moodDataMap.put("user_plan",mcd.user_plan);
            MoodData moodData=new MoodData(80,"wwww",date,"eeeee");

            moodDataList.add(moodDataMap);
        }

        int colors[]={R.color.w1_yellow,R.color.w2_blue,R.color.w3_light_red,
                R.color.burlywood,R.color.lightblue,R.color.peachpuff,R.color.turquoise};

        My3SimpleAdapter simpleAdapter = new My3SimpleAdapter(getContext(), moodDataList, R.layout.mood_circle_item,
                new String[]{"head_image","user_name","user_mood","user_location"},
                new int[]{ R.id.user_head_image, R.id.user_name, R.id.user_mood,R.id.user_location});
        listView.setAdapter(simpleAdapter);
    }
}
