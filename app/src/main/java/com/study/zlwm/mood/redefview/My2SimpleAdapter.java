package com.study.zlwm.mood.redefview;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.study.zlwm.mood.R;
import com.study.zlwm.mood.bean.MoodInfo;

import java.util.List;
import java.util.Map;

/**
 * Created by 31351 on 2016/8/15.
 */
public class My2SimpleAdapter extends SimpleAdapter {
    private List<MoodInfo> moodInfolist;

    public My2SimpleAdapter(Context context, List<? extends Map<String, ?>> data, int resource, String[] from, int[] to, List<MoodInfo> moodInfos) {
        super(context, data, resource, from, to);
        moodInfolist=moodInfos;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final int mPosition = position;
        convertView = super.getView(position, convertView, parent);

        TextView plan_item_layout= (TextView) convertView.findViewById(R.id.mood_color);
        plan_item_layout.setBackgroundColor(convertView.getResources().getColor(moodInfolist.get(position).color));
        //System.out.println("hhhhhh:"+mPosition);

        return convertView;
    }
}
