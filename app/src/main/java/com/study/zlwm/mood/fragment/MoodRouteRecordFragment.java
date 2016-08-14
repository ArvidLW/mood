package com.study.zlwm.mood.fragment;


import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.study.zlwm.mood.R;
import com.study.zlwm.mood.bean.MoodData;
import com.study.zlwm.mood.database.SqlDB;
import com.study.zlwm.mood.global.GlobalInfo;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import lecho.lib.hellocharts.listener.LineChartOnValueSelectListener;
import lecho.lib.hellocharts.model.Axis;
import lecho.lib.hellocharts.model.AxisValue;
import lecho.lib.hellocharts.model.Line;
import lecho.lib.hellocharts.model.LineChartData;
import lecho.lib.hellocharts.model.PointValue;
import lecho.lib.hellocharts.model.ValueShape;
import lecho.lib.hellocharts.model.Viewport;
import lecho.lib.hellocharts.view.LineChartView;

/**
 * A simple {@link Fragment} subclass.
 * 曲线fragment
 */
public class MoodRouteRecordFragment extends Fragment {

    private LineChartView chartCurve;
    private ImageView curveImage;
    private TextView curveText;
    List<MoodData> moodDatalist;
    SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd");
    private View view;
    private int maxNumberOfLines = 4;//最大为4条线，用于增加画线时用
    private int numberOfPoints = 12;
    float[][] randomNumbersTab = new float[maxNumberOfLines][numberOfPoints];

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_mood_route_record, container, false);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        chartCurve= (LineChartView) view.findViewById(R.id.chart_curve);
        //chartCurve= (LineChartView) view.findViewById(R.id.chart_curve);
        curveImage= (ImageView) view.findViewById(R.id.curve_image);
        curveText= (TextView) view.findViewById(R.id.curve_text);

        chartCurve.setOnValueTouchListener(new ValueTouchListener());
        //curveImage.setImageDrawable(getResources().getDrawable(R.drawable.mood_afraid));
        curveImage.setImageDrawable(getResources().getDrawable(getResources().getIdentifier("mood_happy","drawable",getContext().getPackageName())));
        curveText.setText("我的童年，存在于灿烂的阳光之下：妈妈的宽容，" +
                "让唠叨远在天边；老师认为我优秀，将批评隐藏在表扬当中；有一群热情的朋友，" +
                "将孤独驱赶得无影无踪……总而言之，我总有一个影子一般的伙伴--快乐。");
        // 产生一些随机值。
        generateData();

        // 禁用视图重新计算，更多信息参见togglecubic()方法。
        chartCurve.setViewportCalculationEnabled(false);

        resetViewport();

    }



    private void getValuesFormDB(){

        moodDatalist=new ArrayList<>();
        //设置全局变量
        GlobalInfo globalInfo= (GlobalInfo) getActivity().getApplication();
        String user_id=globalInfo.getTel_id();
        String name=globalInfo.getUsername();



        SQLiteDatabase db= SqlDB.getSqlDB(getContext());
        Cursor cursor=db.rawQuery("select mood,moodscore,reason,dateandtime from mood where tel_id=?",new String[]{user_id});
        try {
            if(cursor.getCount()<7){
                fillData(7-cursor.getCount());
            }
            while(cursor.moveToNext()){

                moodDatalist.add(new MoodData(Integer.parseInt(cursor.getString(1)),cursor.getString(0),new Date(simpleDateFormat.parse(cursor.getString(3)).getTime() ),cursor.getString(2)));

            }
        }  catch (ParseException e) {
            e.printStackTrace();
        }
    }

    /**
     * 随机数据
     */
    private void generateValues() {
        for (int i = 0; i < 1; ++i) {
            for (int j = 0; j < numberOfPoints; ++j) {
                randomNumbersTab[i][j] = (float) Math.random() * 100f;
            }
        }
    }
    private void fillData(int n){
        generateValues();
        try {
            for(int i=0;i<n-1;++i){
                int j=i+1;
                moodDatalist.add(new MoodData((int) randomNumbersTab[0][i],"happy",new Date(simpleDateFormat.parse("2016-08-0"+j).getTime() ),"我就么滴吧"));
                //System.out.println("XXXXXXXXX"+randomNumbersTab[0][i]);
            }
            moodDatalist.add(new MoodData((int) randomNumbersTab[0][n],"satisfied",new Date(simpleDateFormat.parse("2016-08-13").getTime() ),"来来来最后一天"));

        }catch (ParseException e) {
            e.printStackTrace();
        }
    }


    private void generateData() {
        getValuesFormDB();
        List<Line> lines = new ArrayList<Line>();

//        List<PointValue> pointValues = new ArrayList<PointValue>();
//        for (int j = 0; j < numberOfPoints; ++j) {
//            pointValues.add(new PointValue(j, randomNumbersTab[0][j]));
//        }
        List<PointValue> pointValues = new ArrayList<PointValue>();
        for (int j = 0; j < moodDatalist.size(); ++j) {
            pointValues.add(new PointValue(j, moodDatalist.get(j).moodScore));
            System.out.println("ssssss"+moodDatalist.get(j).moodScore);
        }

//        moodDatalist=new ArrayList<>();
//        for (int j = 0; j < numberOfPoints-1; ++j) {
//            moodDatalist.add(new MoodData(30,"afraid",new Date(15,3,11),"lwww"));
//        }
//        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd");
//        try {
//            moodDatalist.add(new MoodData(70,"happy",new Date(simpleDateFormat.parse("2015-10-04").getTime() ) ,"qsdfsdf") );
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
        //moodDatalist.add(new MoodData(70,"happy",new Date(30,3,11)));

        Line line = new Line(pointValues);
        line.setColor(getResources().getColor(R.color.blue));
        line.setShape(ValueShape.CIRCLE);
        line.setFilled(true);
        line.setHasLabels(true);
        line.setHasLines(true);
        line.setCubic(true);
        //line.setHasLabelsOnlyForSelected(hasLabelForSelected);
        line.setHasPoints(true);
        line.setPointColor(getResources().getColor(R.color.blue));
        lines.add(line);

        LineChartData data = new LineChartData(lines);

        Axis axisX = new Axis();


        List<AxisValue> mAxisXValues = new ArrayList<AxisValue>();
        //String[] date = {"10-22","11-22","12-22","1-22","6-22","5-23","5-22","6-22999999999999","5-23","5-22","11","12"};//X轴的标注
        for (int i = 0; i < moodDatalist.size(); i++) {
            //mAxisXValues.add(new AxisValue(i).setLabel(date[i]));
            mAxisXValues.add(new AxisValue(i).setLabel(moodDatalist.get(i).date.toString()));
        }
        axisX.setValues(mAxisXValues);
        axisX.setHasTiltedLabels(true);
        //axisX.setMaxLabelChars(7); //最多几个X轴坐标，意思就是你的缩放让X轴上数据的个数7<=x<=mAxisXValues.length

        Axis axisY = new Axis().setHasLines(true);

        //axisX.setName("日期");
        axisY.setName("心情指数");
        data.setAxisXBottom(axisX);
        data.setAxisYLeft(axisY);

        data.setBaseValue(Float.NEGATIVE_INFINITY);



        chartCurve.setLineChartData(data);
    }

    private void resetViewport() {
        // Reset viewport height range to (0,100)
        final Viewport v = new Viewport(chartCurve.getMaximumViewport());
        v.bottom = 0;
        v.top = 105;
        v.left = 0;
        v.right = 7;

        chartCurve.setCurrentViewport(v);
        //final Viewport maxV=new Viewport(chartCurve.getMaximumViewport());
        v.right=16;
        chartCurve.setMaximumViewport(v);

    }

    private class ValueTouchListener implements LineChartOnValueSelectListener {

        @Override
        public void onValueSelected(int lineIndex, int pointIndex, PointValue value) {
            //Toast.makeText(getActivity(), "Selected: " + value, Toast.LENGTH_SHORT).show();
           // System.out.println("rrrrr"+moodDatalist.get(pointIndex).mood);
            curveImage.setImageDrawable(getResources().getDrawable(getResources().getIdentifier("mood_"+moodDatalist.get(pointIndex).mood.toString(),"drawable",getContext().getPackageName())));
            //curveImage.setImageDrawable(getResources().getDrawable(getResources().getIdentifier("mood_afraid","drawable",getContext().getPackageName())));
            curveText.setText(moodDatalist.get(pointIndex).reason);
        }

        @Override
        public void onValueDeselected() {

        }

    }

}
