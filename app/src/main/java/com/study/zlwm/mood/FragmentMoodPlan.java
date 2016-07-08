package com.study.zlwm.mood;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by 31351 on 2016/7/5.
 */
public class FragmentMoodPlan extends Fragment {
    @Nullable
    private View myview;
    private ListView listView;
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
        listView= (ListView) view.findViewById(android.R.id.list);
        /*if(listView==null)
        {
            Log.d("sdfsdf","sdfsdflwwwwwwlwww");
        }
        else {
            Log.d("sdfsdf","nnnnsdfsdflwwwwwwlwww");

        }*/
        String[] array = {"a","b","c","d","e","f","g"};
        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1, array);
        //Log.d("eeee",listView.toString());
        listView.setAdapter(adapter);

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
