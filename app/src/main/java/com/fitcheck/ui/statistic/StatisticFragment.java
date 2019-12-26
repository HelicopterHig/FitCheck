package com.fitcheck.ui.statistic;


import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.fitcheck.Activity_statistic;
import com.fitcheck.LocalDataBase.DatabaseHandler;
import com.fitcheck.R;
import com.fitcheck.ui.elementAdapter.ExerciseAdapter;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.model.GradientColor;

import java.util.ArrayList;
import java.util.List;

public  class StatisticFragment extends Fragment {
        //implements DemoBase {

   // private RecyclerView recyclerView;
  //  private RecyclerView.LayoutManager layoutManager;
    private ExerciseAdapter gameAdapter;
    //private ArrayList<ElementExercise> itemExerciseArrayList;
    List<BarEntry> entries3;
    BarChart mpGroupBar;
    ListView lv;
    BarChart chartBar;
    List<BarEntry> entries;
    EditText calories;
    Button button;

    DatabaseHandler db;
    public static String server_name = "94.141.168.185:8008";


    /*public static StatisticFragment newInstance() {
        StatisticFragment fragment = new StatisticFragment();
        return fragment;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public StatisticFragment() {}*/

    private StatisticViewModel statisticViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.fragment_statistic, container, false);


        Button button = (Button) view.findViewById(R.id.open_next);
        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                Intent intent = new Intent(getActivity(), Activity_statistic.class);
                getActivity().startActivity(intent);
            }
        });

        // Typeface font = Typeface.createFromAsset(getActivity().getAssets(), "fonts/roboto_thin.xml");
        db =  new DatabaseHandler(view.getContext());
        button = (Button) view.findViewById(R.id.button);
        calories = (EditText) view.findViewById(R.id.calories);
        if (calories.getText().length() > 0) {
            int[] b = new int[calories.getText().length()];
            for (int i = 0; i < b.length; i++) {
                b[i] = Integer.parseInt(String.valueOf(calories.getText().charAt(i)));
            }
        }


            lv = (ListView) view.findViewById(R.id.listView1);
        entries = new ArrayList<>();
        entries.add((new BarEntry(0f, 1500f, "Sunday")));
        entries.add((new BarEntry(1f, 500f, "Sunday")));
        ArrayList<ChartItem> list = new ArrayList<>();


                list.add(new BarChartItem(generateDataBar( 1), getContext()));
                list.add(new BarChartItem(sec_dataDataBar(1),getContext()));
                list.add(new PieChartItem(generateDataPie(), getContext()));
                list.add(new PieChartItem(generatePie(), getContext()));
                list.add(new BarChartItem(third_dataDataBar(1),getContext()));
                list.add(new BarChartItem(four_dataDataBar(1),getContext()));
                list.add(new BarChartItem(generateEmptyDataBar(1),getContext()));

        ChartDataAdapter cda = new ChartDataAdapter(getActivity(), list);
        lv.setAdapter(cda);




       /* mpGroupBar = (BarChart) view.findViewById(R.id.mp_groupBarChart);
        chartBar = (BarChart) view.findViewById(R.id.chartBar);


        List<BarEntry> entries = new ArrayList<>();
        entries.add(new BarEntry(0f, 1500f, "Sunday"));
        entries.add(new BarEntry(1f, 1000f, "Monday"));
        entries.add(new BarEntry(2f, 500f, "Tuesday"));
        entries.add(new BarEntry(3f, 1000f, "Wednesday"));
        entries.add(new BarEntry(4f, 2000f, "Thursday"));
        entries.add(new BarEntry(5f, 900f, "Friday"));
        entries.add(new BarEntry(6f, 1450f, "Saturday"));

        int startColor1 = getResources().getColor(R.color.gradient_start);
        int endColor1 = getResources().getColor(R.color.gradient_end);

        List<GradientColor> gradientColors = new ArrayList<>();
        gradientColors.add(new GradientColor(startColor1, endColor1));

        BarDataSet bSet = new BarDataSet(entries, "Marks");
        bSet.setGradientColors(gradientColors);


        ArrayList<String> barFactors = new ArrayList<>();
        int size = barFactors.size();
        System.out.println("size of before after  " + size);
        barFactors.add("Mon");

        barFactors.add("Tue");
        barFactors.add("Wed");
        barFactors.add("Thu");
        barFactors.add("Fri");
        barFactors.add("Sat");
        barFactors.add("Sun");
        size = barFactors.size();
        System.out.println("size of  array after  " + size);


        mpGroupBar.animateY(2000);


        XAxis xAxis = mpGroupBar.getXAxis();
        xAxis.setGranularity(1f);
        xAxis.setGranularityEnabled(true);
        BarData data = new BarData(bSet);
        data.setBarWidth(0.7f); // set custom bar width
        data.setValueTextSize(12);
        Description description = new Description();
        description.setTextColor(R.color.colorPrimary);
        description.setText("Current values calories");

        //description.setTextSize(14);
        mpGroupBar.setDescription(description);
        mpGroupBar.setData(data);
        mpGroupBar.setFitBars(true); // make the x-axis fit exactly all bars
        mpGroupBar.invalidate(); // refresh
        mpGroupBar.getXAxis().setValueFormatter(new IndexAxisValueFormatter(barFactors));

        Legend l = mpGroupBar.getLegend();
        l.setFormSize(10f); // set the size of the legend forms/shapes
        l.setForm(Legend.LegendForm.CIRCLE); // set what type of form/shape should be used
        //  l.setTypeface(font);
        l.setTextSize(12f);
        l.setTextColor(Color.BLACK);
        List<LegendEntry> lentries = new ArrayList<>();

        for (int i = 0; i < barFactors.size(); i++) {
            LegendEntry entry = new LegendEntry();
            //   entry.formColor = ColorTemplate.VORDIPLOM_COLORS[i];
            // entry.formColor = Color.GREEN;
            //entry.formColor = Color.RED;
            System.out.println(entry.formColor);
            entry.label = barFactors.get(i);
            System.out.println(entry.label);
            lentries.add(entry);
            System.out.println(barFactors.size());

        }
        l.setXEntrySpace(5f); // set the space between the legend entries on the x-axis
        l.setYEntrySpace(5f);
        l.setCustom(lentries);



        List<BarEntry> entries = new ArrayList<>();
        entries.add(new BarEntry(0f, 300f, "Sunday"));
        entries.add(new BarEntry(1f, 100f, "Monday"));
        entries.add(new BarEntry(2f, 50f, "Tuesday"));
        entries.add(new BarEntry(3f, 100f, "Wednesday"));
        entries.add(new BarEntry(4f, 200f, "Thursday"));
        entries.add(new BarEntry(5f, 90f, "Friday"));
        entries.add(new BarEntry(6f, 150f, "Saturday"));

        int startColor2 = getResources().getColor(R.color.gradient_start2);
        int endColor2 = getResources().getColor(R.color.gradient_end2);

        List<GradientColor> gradientColors2 = new ArrayList<>();
        gradientColors2.add(new GradientColor(startColor2, endColor2));

        BarDataSet aSet = new BarDataSet(entries, "Marks");
        aSet.setGradientColors(gradientColors2);


        ArrayList<String> barFactors2 = new ArrayList<>();
        int size2 = barFactors2.size();
        System.out.println("size of before after  " + size2);
        barFactors2.add("Mon");

        barFactors2.add("Tue");
        barFactors2.add("Wed");
        barFactors2.add("Thu");
        barFactors2.add("Fri");
        barFactors2.add("Sat");
        barFactors2.add("Sun");
        size2 = barFactors2.size();
        System.out.println("size of  array after  " + size2);


        chartBar.animateY(2000);


        XAxis xxAxis = chartBar.getXAxis();
        xxAxis.setGranularity(1f);
        xxAxis.setGranularityEnabled(true);

        LimitLine ll1 = new LimitLine(150f, "");
        ll1.setLineWidth(4f);
        ll1.enableDashedLine(10f, 10f, 0f);
        ll1.setLineColor(Color.argb(60, 100, 100, 120));
        ll1.setLabelPosition(LimitLine.LimitLabelPosition.RIGHT_TOP);
        ll1.setTextSize(10f);


        YAxis leftAxis = chartBar.getAxisLeft();
        leftAxis.removeAllLimitLines(); // reset all limit lines to avoid overlapping lines
        leftAxis.addLimitLine(ll1);
        BarData data2 = new BarData(aSet);
        data2.setBarWidth(0.9f); // set custom bar width
        data2.setValueTextSize(12);
        Description description2 = new Description();
        description2.setTextColor(R.color.colorBlack);
        description2.setText("Current values CARBONOHIDRATES");
        //description.setTextSize(14);
        chartBar.setDescription(description2);
        chartBar.setData(data2);
        chartBar.setFitBars(true); // make the x-axis fit exactly all bars
        chartBar.invalidate(); // refresh
        chartBar.getXAxis().setValueFormatter(new IndexAxisValueFormatter(barFactors2));

        Legend l2 = chartBar.getLegend();
        l2.setFormSize(10f); // set the size of the legend forms/shapes
        l2.setForm(Legend.LegendForm.CIRCLE); // set what type of form/shape should be used
        //  l.setTypeface(font);
        l2.setTextSize(12f);
        l2.setTextColor(Color.BLACK);
        List<LegendEntry> lentries2 = new ArrayList<>();

        for (int i = 0; i < barFactors2.size(); i++) {
            LegendEntry entry2 = new LegendEntry();
            //   entry.formColor = ColorTemplate.VORDIPLOM_COLORS[i];
            // entry.formColor = Color.GREEN;
            //entry.formColor = Color.RED;
            System.out.println(entry2.formColor);
            entry2.label = barFactors2.get(i);
            System.out.println(entry2.label);
            lentries2.add(entry2);
            System.out.println(barFactors2.size());

        }
        l2.setXEntrySpace(5f); // set the space between the legend entries on the x-axis
        l2.setYEntrySpace(5f);
        l2.setCustom(lentries2);*/

        //return inflater.inflate(R.layout.fragment_statistic, container, false);
        return view;



    }



    private class ChartDataAdapter extends ArrayAdapter<ChartItem> {

        ChartDataAdapter(Context context, List<ChartItem> objects) {
            super(context, 0, objects);
        }

        @NonNull
        @Override
        public View getView(int position, View convertView, @NonNull ViewGroup parent) {
            //noinspection ConstantConditions
            return getItem(position).getView(position, convertView, getContext());
        }

        @Override
        public int getItemViewType(int position) {
            // return the views type
            ChartItem ci = getItem(position);
            return ci != null ? ci.getItemType() : 0;
        }

        @Override
        public int getViewTypeCount() {
            return 3; // we have 3 different item-types
        }
    }

    /**
     * generates a random ChartData object with just one DataSet
     *
     * @return Line data
     */


    /**
     * generates a random ChartData object with just one DataSet
     *
     * @return Bar data
     */
    private BarData generateDataBar(int cnt) {

     //  ArrayList<BarEntry> entries = new ArrayList<>();

        //for (int i = 0; i < 12; i++) {
        //   entries.add(new BarEntry(i, (int) (Math.random() * 70) + 30));
     //   }

      //  BarDataSet d = new BarDataSet(entries, "New DataSet " + cnt);
        //d.setColors(ColorTemplate.VORDIPLOM_COLORS);
        //d.setHighLightAlpha(255);

       // BarData cd = new BarData(d);
       // cd.setBarWidth(0.9f);
       // return cd;

      /*  ArrayList<EditText> editTextList = new ArrayList<EditText>();

        for(int i = 0; i < rootLayout.getChildCount(); i++) {
            if(rootLayout.getChildAt(i) instanceof EditText) {
                editTextList.add( (EditText) rootLayout.getChildAt(i));
            }
        }*/

        /*List<BarEntry> entries = new ArrayList<>();
        db.addDiet(new Diet(1,0,"jar",1500,1500,"Sunday","w"));
        db.addDiet(new Diet(1,1,"jar",1000,1800,"Tuesday","r"));
        db.addDiet(new Diet(1,2,"jar",1000,2000,"Wednesday","t"));
        db.addDiet(new Diet(1,3,"jar",1000,100,"Thursday","15"));


        List<Diet> diet = db.getAllDiet();
        for ( Diet d: diet){
            entries.add(new BarEntry(d.get_diet_id(), d.get_calories(), d.get_day_week()));
            entries.add(new BarEntry(d.get_diet_id(), d.get_calories(), d.get_day_week()));
            entries.add(new BarEntry(d.get_diet_id(), d.get_calories(), "Wednesday"));
            entries.add(new BarEntry(d.get_diet_id(), d.get_calories(), "Wednesday"));
            entries.add(new BarEntry(d.get_diet_id(), d.get_calories(), "Wednesday"));

        }*/

        List<BarEntry> entries = new ArrayList<>();
        entries.add(new BarEntry(0f, 1500f, "Sunday"));
        entries.add(new BarEntry(1f, 1000f, "Monday"));
        entries.add(new BarEntry(2, 500f, "Tuesday"));
        entries.add(new BarEntry(3, 1000f, "Wednesday"));
        entries.add(new BarEntry(4f, 2000f, "Thursday"));
        entries.add(new BarEntry(5f, 900f, "Friday"));
        entries.add(new BarEntry(6f, 1450f, "Saturday"));

        int startColor1 = getResources().getColor(R.color.gradient_start);
        int endColor1 = getResources().getColor(R.color.gradient_end);

        List<GradientColor> gradientColors = new ArrayList<>();
        gradientColors.add(new GradientColor(startColor1, endColor1));

        BarDataSet bSet = new BarDataSet(entries, "Углеводы");
        bSet.setGradientColors(gradientColors);

        BarData data1 = new BarData(bSet);
        data1.setBarWidth(0.7f); // set custom bar width
        data1.setValueTextSize(12);

        Description description2 = new Description();
        description2.setTextColor(R.color.colorBlack);
        description2.setTextSize(15);
        description2.setText("Current values CARBONOHIDRATES");

/*
        ArrayList<String> barFactors = new ArrayList<>();
        int size = barFactors.size();
        System.out.println("size of before after  " + size);
        barFactors.add("Mon");

        barFactors.add("Tue");
        barFactors.add("Wed");
        barFactors.add("Thu");
        barFactors.add("Fri");
        barFactors.add("Sat");
        barFactors.add("Sun");
        size = barFactors.size();
        System.out.println("size of  array after  " + size);


        Legend l2 = chartBar.getLegend();
        l2.setFormSize(10f); // set the size of the legend forms/shapes
        l2.setForm(Legend.LegendForm.CIRCLE); // set what type of form/shape should be used
        //  l.setTypeface(font);
        l2.setTextSize(12f);
        l2.setTextColor(Color.BLACK);
        List<LegendEntry> lentries2 = new ArrayList<>();

        for (int i = 0; i < barFactors.size(); i++) {
            LegendEntry entry2 = new LegendEntry();
            //   entry.formColor = ColorTemplate.VORDIPLOM_COLORS[i];
            // entry.formColor = Color.GREEN;
            //entry.formColor = Color.RED;
            System.out.println(entry2.formColor);
            entry2.label = barFactors.get(i);
            System.out.println(entry2.label);
            lentries2.add(entry2);
            System.out.println(barFactors.size());

        }
        l2.setXEntrySpace(5f); // set the space between the legend entries on the x-axis
        l2.setYEntrySpace(5f);
        l2.setCustom(lentries2);*/

        return data1;
    }

    /**
     * generates a random ChartData object with just one DataSet
     *
     * @return Pie data
     */
    private PieData generateDataPie() {

        ArrayList<PieEntry> entries = new ArrayList<>();

        for (int i = 0; i < 4; i++) {
            entries.add(new PieEntry((float) ((Math.random() * 70) + 30), "Норма потребления " + (i+1)));
        }

        PieDataSet d = new PieDataSet(entries, "");

        // space between slices
        d.setSliceSpace(2f);
       // d.setColors(ColorTemplate.VORDIPLOM_COLORS);
       /* int startColor1 = getResources().getColor(R.color.gradient_start);
        int endColor1 = getResources().getColor(R.color.gradient_end);

        List<GradientColor> gradientColors = new ArrayList<>();
        gradientColors.add(new GradientColor(startColor1, endColor1));


        d.setGradientColors(gradientColors);*/

        final int[] MY_COLORS = {
                Color.rgb(192,0,0),
                Color.rgb(229, 93, 135),
                Color.rgb(255,192,0),
                Color.rgb(95, 195, 1),
                Color.rgb(75, 0, 130),
                Color.rgb(0,176,80),
                Color.rgb(79,129,189)};
        ArrayList<Integer> colors = new ArrayList<Integer>();

        for(int c: MY_COLORS) colors.add(c);

        d.setColors(colors);

        return new PieData(d);
    }

    private PieData generatePie() {

        ArrayList<PieEntry> entries = new ArrayList<>();

        for (int i = 0; i < 4; i++) {
            entries.add(new PieEntry((float) ((Math.random() * 70) + 30), "Общий показатель" + (i+1)));
        }

        PieDataSet d = new PieDataSet(entries, "");

        // space between slices
        d.setSliceSpace(2f);
        // d.setColors(ColorTemplate.VORDIPLOM_COLORS);
       /* int startColor1 = getResources().getColor(R.color.gradient_start);
        int endColor1 = getResources().getColor(R.color.gradient_end);

        List<GradientColor> gradientColors = new ArrayList<>();
        gradientColors.add(new GradientColor(startColor1, endColor1));


        d.setGradientColors(gradientColors);*/

        final int[] MY_COLORS = {
                Color.rgb(199, 21, 133),
                Color.rgb(229, 93, 135),
                Color.rgb(255,192,0),
                Color.rgb(0, 0, 139),
                Color.rgb(146,0,139),
                Color.rgb(75, 0, 130),
                Color.rgb(79,129,189)};
        ArrayList<Integer> colors = new ArrayList<Integer>();

        for(int c: MY_COLORS) colors.add(c);

        d.setColors(colors);

        return new PieData(d);
    }
    private BarData sec_dataDataBar(int cn) {

        //  ArrayList<BarEntry> entries = new ArrayList<>();

        //for (int i = 0; i < 12; i++) {
        //   entries.add(new BarEntry(i, (int) (Math.random() * 70) + 30));
        //   }

        //  BarDataSet d = new BarDataSet(entries, "New DataSet " + cnt);
        //d.setColors(ColorTemplate.VORDIPLOM_COLORS);
        //d.setHighLightAlpha(255);

        // BarData cd = new BarData(d);
        // cd.setBarWidth(0.9f);
        // return cd;


        List<BarEntry> entries2 = new ArrayList<>();
        entries.add(new BarEntry(0f, 100f, "Sunday"));
        entries.add(new BarEntry(1f, 10f, "Monday"));
        entries.add(new BarEntry(2f, 15f, "Tuesday"));
        entries.add(new BarEntry(3f, 60f, "Wednesday"));
        entries.add(new BarEntry(4f, 70f, "Thursday"));
        entries.add(new BarEntry(5f, 90f, "Friday"));
        entries.add(new BarEntry(6f, 67f, "Saturday"));

        int startColor2 = getResources().getColor(R.color.gradient_start2);
        int endColor2 = getResources().getColor(R.color.gradient_end2);

        List<GradientColor> gradientColors2 = new ArrayList<>();
        gradientColors2.add(new GradientColor(startColor2, endColor2));

        BarDataSet aSet = new BarDataSet(entries2, "Жиры");
        aSet.setGradientColors(gradientColors2);
        BarData data2 = new BarData(aSet);
        data2.setBarWidth(0.9f); // set custom bar width
        data2.setValueTextSize(12);

        return data2;
    }

    private BarData third_dataDataBar(int cn) {

        //  ArrayList<BarEntry> entries = new ArrayList<>();

        //for (int i = 0; i < 12; i++) {
        //   entries.add(new BarEntry(i, (int) (Math.random() * 70) + 30));
        //   }

        //  BarDataSet d = new BarDataSet(entries, "New DataSet " + cnt);
        //d.setColors(ColorTemplate.VORDIPLOM_COLORS);
        //d.setHighLightAlpha(255);

        // BarData cd = new BarData(d);
        // cd.setBarWidth(0.9f);
        // return cd;



        List<BarEntry> entries3 = new ArrayList<>();

        entries.add(new BarEntry(0f, 200f, "Sunday"));
        entries.add(new BarEntry(1f, 300f, "Monday"));
        entries.add(new BarEntry(2f, 160f, "Tuesday"));
        entries.add(new BarEntry(3f, 100f, "Wednesday"));
        entries.add(new BarEntry(4f, 220f, "Thursday"));
        entries.add(new BarEntry(5f, 111f, "Friday"));
        entries.add(new BarEntry(6f, 89f, "Saturday"));


        ArrayList<String> labels = new ArrayList<String>();
        labels.add("January");
        labels.add("February");
        labels.add("March");
        labels.add("April");
        labels.add("May");
        labels.add("June");

        int startColor2 = getResources().getColor(R.color.gradient_start3);
        int endColor2 = getResources().getColor(R.color.gradient_end3);

        List<GradientColor> gradientColors2 = new ArrayList<>();
        gradientColors2.add(new GradientColor(startColor2, endColor2));

        BarDataSet aSet = new BarDataSet(entries3, "Белки");
        aSet.setGradientColors(gradientColors2);
        BarData data2 = new BarData(aSet);
        data2.setBarWidth(0.9f); // set custom bar width
        data2.setValueTextSize(12);

        return data2;
    }

    private BarData four_dataDataBar(int cn) {

        //  ArrayList<BarEntry> entries = new ArrayList<>();

        //for (int i = 0; i < 12; i++) {
        //   entries.add(new BarEntry(i, (int) (Math.random() * 70) + 30));
        //   }

        //  BarDataSet d = new BarDataSet(entries, "New DataSet " + cnt);
        //d.setColors(ColorTemplate.VORDIPLOM_COLORS);
        //d.setHighLightAlpha(255);

        // BarData cd = new BarData(d);
        // cd.setBarWidth(0.9f);
        // return cd;


        List<BarEntry> entries4 = new ArrayList<>();
        entries.add(new BarEntry(0f, 3000f, "Sunday"));
        entries.add(new BarEntry(1f, 1000f, "Monday"));
        entries.add(new BarEntry(2f, 2000f, "Tuesday"));
        entries.add(new BarEntry(3f, 1400f, "Wednesday"));
        entries.add(new BarEntry(4f, 500f, "Thursday"));
        entries.add(new BarEntry(5f, 2000f, "Friday"));
        entries.add(new BarEntry(6f, 1679f, "Saturday"));

        int startColor2 = getResources().getColor(R.color.gradient_start4);
        int endColor2 = getResources().getColor(R.color.gradient_end4);

        List<GradientColor> gradientColors2 = new ArrayList<>();
        gradientColors2.add(new GradientColor(startColor2, endColor2));

        BarDataSet aSet = new BarDataSet(entries4, "Калории");
        aSet.setGradientColors(gradientColors2);
        BarData data2 = new BarData(aSet);
        data2.setBarWidth(0.9f); // set custom bar width
        data2.setValueTextSize(12);

        return data2;
    }

    private BarData generateEmptyDataBar(int cnt) {

        //  ArrayList<BarEntry> entries = new ArrayList<>();

        //for (int i = 0; i < 12; i++) {
        //   entries.add(new BarEntry(i, (int) (Math.random() * 70) + 30));
        //   }

        //  BarDataSet d = new BarDataSet(entries, "New DataSet " + cnt);
        //d.setColors(ColorTemplate.VORDIPLOM_COLORS);
        //d.setHighLightAlpha(255);

        // BarData cd = new BarData(d);
        // cd.setBarWidth(0.9f);
        // return cd;

       /* List<BarEntry> entries = new ArrayList<>();
        entries.add(new BarEntry(0f, 1500f, "Sunday"));
        entries.add(new BarEntry(1f, 1000f, "Monday"));
        entries.add(new BarEntry(2f, 500f, "Tuesday"));
        entries.add(new BarEntry(3f, 1000f, "Wednesday"));
        entries.add(new BarEntry(4f, 2000f, "Thursday"));
        entries.add(new BarEntry(5f, 900f, "Friday"));
        entries.add(new BarEntry(6f, 1450f, "Saturday"));

        int startColor1 = getResources().getColor(R.color.gradient_start);
        int endColor1 = getResources().getColor(R.color.gradient_end);

        List<GradientColor> gradientColors = new ArrayList<>();
        gradientColors.add(new GradientColor(startColor1, endColor1));

        BarDataSet bSet = new BarDataSet(entries, "Marks");
        bSet.setGradientColors(gradientColors);

        BarData data = new BarData(bSet);
        data.setBarWidth(0.7f); // set custom bar width
        data.setValueTextSize(12);

        Description description2 = new Description();
        description2.setTextColor(R.color.colorBlack);
        description2.setText("Current values CARBONOHIDRATES");

/*
        ArrayList<String> barFactors = new ArrayList<>();
        int size = barFactors.size();
        System.out.println("size of before after  " + size);
        barFactors.add("Mon");

        barFactors.add("Tue");
        barFactors.add("Wed");
        barFactors.add("Thu");
        barFactors.add("Fri");
        barFactors.add("Sat");
        barFactors.add("Sun");
        size = barFactors.size();
        System.out.println("size of  array after  " + size);


        Legend l2 = chartBar.getLegend();
        l2.setFormSize(10f); // set the size of the legend forms/shapes
        l2.setForm(Legend.LegendForm.CIRCLE); // set what type of form/shape should be used
        //  l.setTypeface(font);
        l2.setTextSize(12f);
        l2.setTextColor(Color.BLACK);
        List<LegendEntry> lentries2 = new ArrayList<>();

        for (int i = 0; i < barFactors.size(); i++) {
            LegendEntry entry2 = new LegendEntry();
            //   entry.formColor = ColorTemplate.VORDIPLOM_COLORS[i];
            // entry.formColor = Color.GREEN;
            //entry.formColor = Color.RED;
            System.out.println(entry2.formColor);
            entry2.label = barFactors.get(i);
            System.out.println(entry2.label);
            lentries2.add(entry2);
            System.out.println(barFactors.size());

        }
        l2.setXEntrySpace(5f); // set the space between the legend entries on the x-axis
        l2.setYEntrySpace(5f);
        l2.setCustom(lentries2);*/

        return null;
    }

/*
    class Diet extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... voids) {
            try {
                String myURL = "http://" + server_name + "/trainer?name="+name+ "&surname=" + surname+"&email="+email+"&password=" + pass +"&phone_num="+number+"&gender="+getGender();

                InputStream is;
                byte[] data = null;
                try {
                    URL url = new URL(myURL);

                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    conn.setReadTimeout(10000);
                    conn.setConnectTimeout(15000);
                    conn.setRequestMethod("GET");

                    conn.connect();

                    int responseCode = conn.getResponseCode();

                    ByteArrayOutputStream baos = new ByteArrayOutputStream();

                    if (responseCode == 200){
                        is = conn.getInputStream();

                        byte[] buffer = new byte[8192];
                        int bytesRead;

                        while ((bytesRead = is.read(buffer)) != -1){
                            baos.write(buffer, 0, bytesRead);
                        }

                        data = baos.toByteArray();

                        String resultString = new String(data, "UTF-8");

                    }else {
                        conn.disconnect();
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }
    }*/




}


