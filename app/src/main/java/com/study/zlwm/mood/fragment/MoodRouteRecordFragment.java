package com.study.zlwm.mood.fragment;


import android.support.v4.app.Fragment;

//import lecho.lib.hellocharts.model.Line;
//import lecho.lib.hellocharts.model.LineChartData;
//import lecho.lib.hellocharts.model.PointValue;
//import lecho.lib.hellocharts.model.Viewport;
//import lecho.lib.hellocharts.view.LineChartView;

/**
 * A simple {@link Fragment} subclass.
 * 曲线fragment
 */
public class MoodRouteRecordFragment extends Fragment {
   //ValueShape valueShape=new ValueShape();

//    @Bind(R.id.chart_curve) LineChartView chartCurve;
//    @Bind(R.id.curve_image) ImageView curveImage;
//    @Bind(R.id.curve_text) TextView curveText;
//    private int maxNumberOfLines = 4;
//    private int numberOfPoints = 12;
//    float[][] randomNumbersTab = new float[maxNumberOfLines][numberOfPoints];
//
//    public static MoodRouteRecordFragment getNewInstance() {
//        return new MoodRouteRecordFragment();
//    }
//
//
//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                             Bundle savedInstanceState) {
//        View view = inflater.inflate(R.layout.fragment_curve, container, false);
//        return view;
//    }
//
//    @Override
//    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
//        super.onActivityCreated(savedInstanceState);
//        //Linechart
//    }
//
//    private void init() {
//        chartCurve.setOnValueTouchListener(new ValueTouchListener());
//
//        generateValues();
//
//        generateData();
//
//        chartCurve.setViewportCalculationEnabled(false);
//
//        resetViewport();
//
//
//        Glide.with(getActivity())
//                .load(R.drawable.happy)
//                .centerCrop()
//                .into(curveImage);
//
//        curveText.setText("我的童年，存在于灿烂的阳光之下：妈妈的宽容，" +
//                "让唠叨远在天边；老师认为我优秀，将批评隐藏在表扬当中；有一群热情的朋友，" +
//                "将孤独驱赶得无影无踪……总而言之，我总有一个影子一般的伙伴--快乐。");
//
//    }
//
//    /**
//     * 随机数据
//     */
//    private void generateValues() {
//        for (int i = 0; i < maxNumberOfLines; ++i) {
//            for (int j = 0; j < numberOfPoints; ++j) {
//                randomNumbersTab[i][j] = (float) Math.random() * 100f;
//            }
//        }
//    }
//
//
//    private void generateData() {
//
//        List<Line> lines = new ArrayList<Line>();
//
//        List<PointValue> values = new ArrayList<PointValue>();
//        for (int j = 0; j < numberOfPoints; ++j) {
//            values.add(new PointValue(j, randomNumbersTab[0][j]));
//        }
//
//        Line line = new Line(values);
//        line.setColor(getResources().getColor(R.color.card_calm));
//        line.setShape(ValueShape.CIRCLE);
//        line.setFilled(true);
//        line.setHasLabels(true);
//        line.setHasLines(true);
//        line.setHasPoints(true);
//        line.setPointColor(getResources().getColor(R.color.card_calm));
//        lines.add(line);
//
//        LineChartData data = new LineChartData(lines);
//
//        Axis axisX = new Axis();
//        Axis axisY = new Axis().setHasLines(true);
//        axisX.setName("星期");
//        axisY.setName("心情指数");
//        data.setAxisXBottom(axisX);
//        data.setAxisYLeft(axisY);
//
//        data.setBaseValue(Float.NEGATIVE_INFINITY);
//        chartCurve.setLineChartData(data);
//    }
//
//    private void resetViewport() {
//        // Reset viewport height range to (0,100)
//        final Viewport v = new Viewport(chartCurve.getMaximumViewport());
//        v.bottom = 0;
//        v.top = 100;
//        v.left = 0;
//        v.right = 7;
//        chartCurve.setMaximumViewport(v);
//        chartCurve.setCurrentViewport(v);
//    }
//
//    private class ValueTouchListener implements LineChartOnValueSelectListener {
//
//        @Override
//        public void onValueSelected(int lineIndex, int pointIndex, PointValue value) {
//            Toast.makeText(getActivity(), "Selected: " + value, Toast.LENGTH_SHORT).show();
//        }
//
//        @Override
//        public void onValueDeselected() {
//
//        }
//
//    }
//
//
//    @Override
//    public void onDestroyView() {
//        super.onDestroyView();
//        ButterKnife.unbind(this);
//    }
}
