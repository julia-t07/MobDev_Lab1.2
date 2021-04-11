package ua.kpi.comsys.io8223;

import android.graphics.Color;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;
import com.llollox.androidtoggleswitch.widgets.ToggleSwitch;
import java.util.ArrayList;
import java.util.List;
public class SecondFragment extends Fragment {
    private GraphView coordPlot;
    private PieChart pieChart;
    private ToggleSwitch toggleSwitch;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_drawing, container, false);
    }
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        coordPlot = (GraphView) view.findViewById(R.id.coordPlot);
        pieChart = (PieChart) view.findViewById(R.id.pieChart);
        toggleSwitch = (ToggleSwitch) view.findViewById(R.id.toggleGraphs);
        pieChart.setUsePercentValues(true);
        toggleSwitch.setOnChangeListener(new ToggleSwitch.OnChangeListener() {
            @Override
            public void onToggleSwitchChanged(int position) {
                if (position == 0) {
                    Plot();
                } else {
                    Diagram();
                }
            }
        });
        toggleSwitch.setCheckedPosition(0);
        Plot();
    }
    public void Plot() {
        LineGraphSeries<DataPoint> series = new LineGraphSeries<>();
        pieChart.setVisibility(View.INVISIBLE);
        coordPlot.setVisibility(View.VISIBLE);
        double x, y;
        x = -10;
        int points = 1200;
        for (int i = 0; i < points; i++) {
            x += 0.01;
            y = Math.pow(x, 3); //y=x^3
            series.appendData(new DataPoint(x, y), true, 1200);
        }
        series.setAnimated(true);
        series.setColor(Color.RED);
        coordPlot.removeAllSeries();
        coordPlot.addSeries(series);
        coordPlot.getViewport().setXAxisBoundsManual(true);
        coordPlot.getViewport().setMinX(-3);
        coordPlot.getViewport().setMaxX(3);
        coordPlot.getViewport().setYAxisBoundsManual(true);
        coordPlot.getViewport().setMinY(-75);
        coordPlot.getViewport().setMaxY(75);
        coordPlot.getViewport().setScalable(true);
        coordPlot.getViewport().setScalableY(true);
        coordPlot.getGridLabelRenderer().setHorizontalLabelsVisible(false);
        coordPlot.getGridLabelRenderer().setVerticalLabelsVisible(false);
    }
    public void Diagram() {
        coordPlot.setVisibility(View.INVISIBLE);
        pieChart.setVisibility(View.VISIBLE);
        List<PieEntry> values = new ArrayList<>();
        values.add(new PieEntry(15f));//yellow
        values.add(new PieEntry(25f));//brown
        values.add(new PieEntry(45f));//gray
        values.add(new PieEntry(10f));//red
        values.add(new PieEntry(5f));//violet
        PieDataSet pieDataSet = new PieDataSet(values, "Pie Chart");
        final int[] Color_pie = {Color.rgb(255,255,0),
                Color.rgb(102,51,0), Color.rgb(160,160,160),Color.rgb(255,0,0),Color.rgb(204,0,204)};
        final int[] Color_text = {Color.rgb(255,255,255),Color.rgb(255,255,255),Color.rgb(255,255,255),Color.rgb(255,255,255),Color.rgb(255,255,255)};
        ArrayList<Integer> colors = new ArrayList<>();
        ArrayList<Integer> textColors = new ArrayList<>();
        for(int c: Color_pie) {
            colors.add(c);
        }
        for (int c: Color_text) {
            textColors.add(c);
        }
        pieDataSet.setValueTextColors(textColors);
        pieDataSet.setColors(colors);
        PieData pieData = new PieData(pieDataSet);
        pieChart.setData(pieData);
        pieChart.getDescription().setEnabled(false);
        pieData.setValueTextSize(12);
        pieChart.getLegend().setEnabled(false);
        pieChart.setHoleRadius(45f);
        pieChart.setTransparentCircleRadius(0f);
        pieChart.animateY(1000, Easing.EaseInOutExpo);
    }
}