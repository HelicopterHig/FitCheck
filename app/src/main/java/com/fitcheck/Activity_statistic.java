package com.fitcheck;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.fitcheck.LocalDataBase.DatabaseHandler;
import com.fitcheck.LocalDataBase.Stats;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.LegendEntry;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.model.GradientColor;

import java.util.ArrayList;
import java.util.List;

public class Activity_statistic extends AppCompatActivity {

    BarChart mpGroupBar;
    BarChart chartBar;
    BarChart ch;
    DatabaseHandler db;
    List<Stats> stats;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistic);

        db = new DatabaseHandler(this);
        stats = db.getAllStats();


        final TextView textView1 = findViewById(R.id.callTod);
        final TextView textView2 = findViewById(R.id.callAll);
        final TextView textView3 = findViewById(R.id.belAll);
        final TextView textView4 = findViewById(R.id.zirAll);
        final TextView textView5 = findViewById(R.id.uglAll);

        int callT = 0, callA = 0, bel = 0, zir = 0, ugl = 0;

        Bundle extras = getIntent().getExtras();
        callT = extras.getInt("call");


        mpGroupBar = (BarChart) findViewById(R.id.chart1);
        chartBar = (BarChart) findViewById(R.id.chart2);
        ch = (BarChart) findViewById(R.id.chart3);


        List<BarEntry> entries = new ArrayList<>();

        int count = 0;
        for (Stats st : stats){
            count++;
            entries.add(new BarEntry(count, st.get_squirrels(), String.valueOf(count)));
            bel+=st.get_squirrels();
        }


        int startColor1 = getResources().getColor(R.color.gradient_start);
        int endColor1 = getResources().getColor(R.color.gradient_end);

        List<GradientColor> gradientColors = new ArrayList<>();
        gradientColors.add(new GradientColor(startColor1, endColor1));

        BarDataSet bSet = new BarDataSet(entries, "Marks");
        bSet.setGradientColors(gradientColors);


        ArrayList<String> barFactors = new ArrayList<>();
        int size = barFactors.size();
        System.out.println("size of before after  " + size);
        for (int i = 0; i < size; i++){
            barFactors.add(String.valueOf(i+1));
        }

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
        description.setText("Белки");

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



        List<BarEntry> entries1 = new ArrayList<>();
        count = 0;
        for (Stats st : stats){
            count++;
            entries1.add(new BarEntry(count, st.get_fats(), String.valueOf(count)));
            zir+=st.get_fats();
        }

        int startColor2 = getResources().getColor(R.color.gradient_start2);
        int endColor2 = getResources().getColor(R.color.gradient_end2);

        List<GradientColor> gradientColors2 = new ArrayList<>();
        gradientColors2.add(new GradientColor(startColor2, endColor2));

        BarDataSet aSet = new BarDataSet(entries1, "Marks");
        aSet.setGradientColors(gradientColors2);


        ArrayList<String> barFactors2 = new ArrayList<>();
        int size2 = barFactors2.size();
        System.out.println("size of before after  " + size2);
        for (int i = 0; i < size2; i++){
            barFactors.add(String.valueOf(i+1));
        }

        size2 = barFactors2.size();
        System.out.println("size of  array after  " + size2);


        chartBar.animateY(2000);


        XAxis xxAxis = chartBar.getXAxis();
        xxAxis.setGranularity(1f);
        xxAxis.setGranularityEnabled(true);

        /*LimitLine ll1 = new LimitLine(150f, "");
        ll1.setLineWidth(4f);
        ll1.enableDashedLine(10f, 10f, 0f);
        ll1.setLineColor(Color.argb(60, 100, 100, 120));
        ll1.setLabelPosition(LimitLine.LimitLabelPosition.RIGHT_TOP);
        ll1.setTextSize(10f);

        YAxis leftAxis = chartBar.getAxisLeft();
        leftAxis.removeAllLimitLines(); // reset all limit lines to avoid overlapping lines
        leftAxis.addLimitLine(ll1);*/
        BarData data2 = new BarData(aSet);
        data2.setBarWidth(0.9f); // set custom bar width
        data2.setValueTextSize(12);
        Description description2 = new Description();
        description2.setTextColor(R.color.colorBlack);
        description2.setText("Жиры");
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
        l2.setCustom(lentries2);


        List<BarEntry> entries3 = new ArrayList<>();
        for (Stats st : stats){
            count++;
            entries3.add(new BarEntry(count, st.get_carbohydrates(), String.valueOf(count)));
            ugl+=st.get_carbohydrates();
        }

        int startColor3 = getResources().getColor(R.color.gradient_start3);
        int endColor3 = getResources().getColor(R.color.gradient_end3);

        List<GradientColor> gradientColors3 = new ArrayList<>();
        gradientColors3.add(new GradientColor(startColor3, endColor3));

        BarDataSet Sete = new BarDataSet(entries3, "Marks");
        Sete.setGradientColors(gradientColors3);


        ArrayList<String> barFactors4 = new ArrayList<>();
        int size4 = barFactors4.size();
        System.out.println("size of before after  " + size4);
        for (int i = 0; i < size4; i++){
            barFactors.add(String.valueOf(i+1));
        }
        size4 = barFactors4.size();
        System.out.println("size of  array after  " + size4);


        ch.animateY(2000);


        XAxis xxAxis4 = ch.getXAxis();
        xxAxis4.setGranularity(1f);
        xxAxis4.setGranularityEnabled(true);

        callA = ugl * 4 + bel * 4 + zir * 9;

        textView1.setText("Каллорий за прием: "+callT);
        textView2.setText("Всего каллорий: "+callA);
        textView3.setText("Всего белокв: "+bel);
        textView4.setText("Всего жиров: "+zir);
        textView5.setText("Всего углеводов: "+ugl);


        /*LimitLine ll1 = new LimitLine(150f, "");
        ll1.setLineWidth(4f);
        ll1.enableDashedLine(10f, 10f, 0f);
        ll1.setLineColor(Color.argb(60, 100, 100, 120));
        ll1.setLabelPosition(LimitLine.LimitLabelPosition.RIGHT_TOP);
        ll1.setTextSize(10f);

        YAxis leftAxis = chartBar.getAxisLeft();
        leftAxis.removeAllLimitLines(); // reset all limit lines to avoid overlapping lines
        leftAxis.addLimitLine(ll1);*/
        BarData data4 = new BarData(Sete);
        data4.setBarWidth(0.9f); // set custom bar width
        data4.setValueTextSize(12);
        Description description4 = new Description();
        description4.setTextColor(R.color.colorBlack);
        description4.setText("Углеводы");
        //description.setTextSize(14);
        ch.setDescription(description4);
        ch.setData(data4);
        ch.setFitBars(true); // make the x-axis fit exactly all bars
        ch.invalidate(); // refresh
        ch.getXAxis().setValueFormatter(new IndexAxisValueFormatter(barFactors4));

        Legend l4 = ch.getLegend();
        l4.setFormSize(10f); // set the size of the legend forms/shapes
        l4.setForm(Legend.LegendForm.CIRCLE); // set what type of form/shape should be used
        //  l.setTypeface(font);
        l4.setTextSize(12f);
        l4.setTextColor(Color.BLACK);
        List<LegendEntry> lentries4 = new ArrayList<>();

        for (int i = 0; i < barFactors4.size(); i++) {
            LegendEntry entry4 = new LegendEntry();
            //   entry.formColor = ColorTemplate.VORDIPLOM_COLORS[i];
            // entry.formColor = Color.GREEN;
            //entry.formColor = Color.RED;
            System.out.println(entry4.formColor);
            entry4.label = barFactors4.get(i);
            System.out.println(entry4.label);
            lentries4.add(entry4);
            System.out.println(barFactors4.size());

        }
        l4.setXEntrySpace(5f); // set the space between the legend entries on the x-axis
        l4.setYEntrySpace(5f);
        l4.setCustom(lentries4);

    }
}
