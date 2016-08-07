package com.study.zlwm.mood;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by lw on 2016/7/5.
 */
public class FragmentMoodPlan extends Fragment {
    @Nullable
    private View myview;
    private ListView listView;
    private String[] weeks =new String[]{"星期一","星期二","星期三","星期四","星期五","星期六","星期天"};
    private String[] tags = new String[]{"淡然","无所谓","坚定","从容","奋斗","青城山","泸沽湖"};
    private String[] simCons = new String[]{"看开看开，风吹裤裆","心有千千节，小事","学习学习……","我的沧海呢","100……","好地方哦","女儿国"};
    private int[] mytag ={0,0,0,0,0,0,0};
    //private Map map;
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //return super.onCreateView(inflater, container, savedInstanceState);
        // myview=inflater.inflate(R.layout.fragment_mood_plan,container,false);
        //View rootView = inflater.inflate(R.layout.fragment_mood_plan, null);
        //Log.d("sdfsdf","进入Fragment页面");
        /*listView= (ListView) myview.findViewById(android.R.id.list);
        if(listView ==null)
        {
            Log.d("sdfsdf","空空空");
        }
        else{
            Log.d("sdfsdf","不不不空空空");
        }*/
        //Log.d("sdfsdf","33333不不不空空空");
        //return myview;
        return inflater.inflate(R.layout.fragment_mood_plan,container,false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        listView= (ListView) view.findViewById(R.id.mood_plan_list);

        final List<Map<String,Object>> listItems = new ArrayList<>();
        for(int i=0;i<weeks.length;++i)
        {
            Map<String,Object> listItem= new HashMap<>();
            listItem.put("week",weeks[i]);
            listItem.put("tag",tags[i]);
            listItem.put("simCon",simCons[i]);
            listItem.put("myTag",mytag[i]);
            listItems.add(listItem);
        }

        final SimpleAdapter simpleAdapter = new SimpleAdapter(getContext(), listItems, R.layout.mood_plan_item,
                new String[]{"week","tag","simCon","myTag"},
                new int[]{ R.id.week, R.id.moodTag, R.id.moodSimCon} );
        listView.setAdapter(simpleAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                System.out.println(position);
                Intent intent=new Intent();
                intent.setClass(getContext(),EditMoodPlan.class);
                startActivity(intent);

                //view.setVisibility(View.INVISIBLE);
            }
        });
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener(){

            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                //View simContent=view.findViewById(R.id.detailInfo);
                //simContent.setVisibility(View.INVISIBLE);
                System.out.println(position);
                System.out.println(id);

                view.setVisibility(View.INVISIBLE);
                //view.getTextAlignment();
                //view.set
                Map ss=(Map)parent.getItemAtPosition(position);
                String hp=ss.get("myTag").toString();
                /*if(hp.equals("0"))
                {
                    view.setVisibility(View.INVISIBLE);

                }
                else{
                    view.setVisibility(View.VISIBLE);
                }*/
                System.out.println("刘炜"+hp);
                Log.d("view",view.toString());
                //simContent.setMinimumHeight(0);
                return false;
            }
        });
        //String[] array = {"a","b","c","d","e","f","g"};
        //final ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1, array);
        //Log.d("eeee",listView.toString());
        //listView.setAdapter(adapter);

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
