package com.study.zlwm.mood.redefview;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.SimpleAdapter;

import com.study.zlwm.mood.R;

import java.util.List;
import java.util.Map;

/**
 * Created by 31351 on 2016/8/15.
 */
public class MySimpleAdapter extends SimpleAdapter {
    private int[] colors;
    /**
     * Constructor
     *
     * @param context  The context where the View associated with this SimpleAdapter is running
     * @param data     A List of Maps. Each entry in the List corresponds to one row in the list. The
     *                 Maps contain the data for each row, and should include all the entries specified in
     *                 "from"
     * @param resource Resource identifier of a view layout that defines the views for this list
     *                 item. The layout file should include at least those named views defined in "to"
     * @param from     A list of column names that will be added to the Map associated with each
     *                 item.
     * @param to       The views that should display column in the "from" parameter. These should all be
     *                 TextViews. The first N views in this list are given the values of the first N columns
     */
    public MySimpleAdapter(Context context, List<? extends Map<String, ?>> data, int resource, String[] from, int[] to,int[] colors) {
        super(context, data, resource, from, to);
        this.colors=colors;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final int mPosition = position;
        convertView = super.getView(position, convertView, parent);
        LinearLayout plan_item_layout= (LinearLayout) convertView.findViewById(R.id.mood_plan_item);
        plan_item_layout.setBackgroundColor(convertView.getResources().getColor(colors[mPosition]));
        //System.out.println("hhhhhh:"+mPosition);

        return convertView;
    }
//    //调用更新Data
//    public void setData(List<? extends Map<String, ?>> data){
//
//    }

}
