package com.study.zlwm.mood.redefview;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SimpleAdapter;

import java.util.List;
import java.util.Map;

/**
 * Created by 31351 on 2016/8/15.
 */
public class My3SimpleAdapter extends SimpleAdapter {

    public My3SimpleAdapter(Context context, List<? extends Map<String, ?>> data, int resource, String[] from, int[] to) {
        super(context, data, resource, from, to);
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return super.getView(position,convertView,parent);
//        final int mPosition = position;
//        convertView = super.getView(position, convertView, parent);

//        TextView plan_item_layout= (TextView) convertView.findViewById(R.id.mood_color);
//        plan_item_layout.setBackgroundColor(convertView.getResources().getColor(moodInfolist.get(position).color));
//        //System.out.println("hhhhhh:"+mPosition);
//
//
//       return convertView;
    }
}
