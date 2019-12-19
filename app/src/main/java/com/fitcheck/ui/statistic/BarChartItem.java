package com.fitcheck.ui.statistic;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;

import com.fitcheck.R;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.XAxis.XAxisPosition;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.ChartData;

public class BarChartItem extends ChartItem {

   // private final Typeface mTf;

    public BarChartItem(ChartData<?> cd, Context c) {
        super(cd);

       // mTf = Typeface.createFromAsset(c.getAssets(), "OpenSans-Regular.ttf");
    }

    @Override
    public int getItemType() {
        return TYPE_BARCHART;
    }

    @SuppressLint("InflateParams")
    @Override
    public View getView(int position, View convertView, Context c) {

        ViewHolder holder;

        if (convertView == null) {

            holder = new ViewHolder();

            convertView = LayoutInflater.from(c).inflate(
                    R.layout.list_item_barchart, null);
            holder.chart = convertView.findViewById(R.id.mp_groupBarChart);
//            holder.chart_2 = convertView.findViewById(R.id.chart_2);

            convertView.setTag(holder);

        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        // apply styling
        holder.chart.getDescription().setEnabled(false);
        holder.chart.setDrawGridBackground(false);
        holder.chart.setDrawBarShadow(false);




        XAxis xAxis = holder.chart.getXAxis();
        xAxis.setPosition(XAxisPosition.BOTTOM);
       // xAxis.setTypeface(mTf);
        xAxis.setDrawGridLines(false);
        xAxis.setDrawAxisLine(true);

        YAxis leftAxis = holder.chart.getAxisLeft();
      //  leftAxis.setTypeface(mTf);
        leftAxis.setLabelCount(5, false);
        leftAxis.setSpaceTop(20f);
        leftAxis.setAxisMinimum(0f); // this replaces setStartAtZero(true)

        YAxis rightAxis = holder.chart.getAxisRight();
      //  rightAxis.setTypeface(mTf);
        rightAxis.setLabelCount(5, false);
        rightAxis.setSpaceTop(20f);
        rightAxis.setAxisMinimum(0f); // this replaces setStartAtZero(true)

       // mChartData.setValueTypeface(mTf);

        // set data
        holder.chart.setData((BarData) mChartData);
        holder.chart.setFitBars(true);

        // do not forget to refresh the chart
//        holder.chart.invalidate();
        holder.chart.animateY(700);



       /* holder.chart_2.getDescription().setEnabled(false);
        holder.chart_2.setDrawGridBackground(false);
        holder.chart_2.setDrawBarShadow(false);



        XAxis xAxis_2 = holder.chart_2.getXAxis();
        xAxis_2.setPosition(XAxisPosition.BOTTOM);
        // xAxis.setTypeface(mTf);
        xAxis_2.setDrawGridLines(false);
        xAxis_2.setDrawAxisLine(true);


        YAxis leftAxis_2 = holder.chart_2.getAxisLeft();
        //  leftAxis.setTypeface(mTf);
        leftAxis_2.setLabelCount(5, false);
        leftAxis_2.setSpaceTop(20f);
        leftAxis_2.setAxisMinimum(0f); // this replaces setStartAtZero(true)

        YAxis rightAxis_2 = holder.chart_2.getAxisRight();

        rightAxis_2.setLabelCount(5, false);
        rightAxis_2.setSpaceTop(20f);
        rightAxis_2.setAxisMinimum(0f); // this replaces setStartAtZero(true)

        // mChartData.setValueTypeface(mTf);

        // set data
        holder.chart_2.setData((BarData) mChartData);
        holder.chart_2.setFitBars(true);

        // do not forget to refresh the chart
//        holder.chart.invalidate();
        holder.chart_2.animateY(700);*/


        return convertView;
    }

    private static class ViewHolder {
        BarChart chart;
        //BarChart chart_2;
    }
}