package com.study.zlwm.mood.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.study.zlwm.mood.R;

import java.util.ArrayList;
import java.util.List;

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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_mood_route_state, container, false);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        chartCensus= (PieChartView) view.findViewById(R.id.chart_census);
        chartCensus.setOnValueTouchListener(new ValueTouchListener());
        generateData();
    }

    private void generateData() {
        int numValues = 8;

        List<SliceValue> values = new ArrayList<SliceValue>();
        for (int i = 0; i < numValues; i++) {
            //SliceValue sliceValue = new SliceValue((float) Math.random() * 30 + 15, getResources().getColor(SyncStateContract.Constants.moodColor[i]));
            SliceValue sliceValue = new SliceValue((float) Math.random() * 30 + 15, 0xFF00FFFF);
            values.add(sliceValue);
        }

        data = new PieChartData(values);
        data.setHasLabels(true);

        chartCensus.setPieChartData(data);

    }


    /**
     * 监听事件
     */
    private class ValueTouchListener implements PieChartOnValueSelectListener {

        @Override
        public void onValueSelected(int arcIndex, SliceValue value) {
            Toast.makeText(getActivity(), "Selected: " + value, Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onValueDeselected() {

        }

    }

}