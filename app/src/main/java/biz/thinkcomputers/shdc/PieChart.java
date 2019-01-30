package biz.thinkcomputers.shdc;

import android.content.Context;

import android.graphics.Color;


import org.achartengine.ChartFactory;
import org.achartengine.GraphicalView;
import org.achartengine.model.CategorySeries;
import org.achartengine.renderer.DefaultRenderer;
import org.achartengine.renderer.SimpleSeriesRenderer;

/**
 * Created by think on 8/30/2017.
 */
public class PieChart {

    public GraphicalView getGraphicalView(Context context,int present,int absent){

        CategorySeries series = new CategorySeries("Pie Chart");

        int[] portion = {present,absent};
        String[] seriesName = new String[]{"Present","Absent"};

        int numSeries = 2;
        for(int i=0;i<numSeries;i++){
            series.add(seriesName[i],portion[i]);

        }

        DefaultRenderer defaultRenderer = new DefaultRenderer();

        SimpleSeriesRenderer simpleSeriesRenderer = null;

        int[] color ={Color.GREEN,Color.RED};
        for(int i=0;i<numSeries;i++){
            simpleSeriesRenderer = new SimpleSeriesRenderer();
            simpleSeriesRenderer.setColor(color[i]);
            defaultRenderer.addSeriesRenderer(simpleSeriesRenderer);

        }
        defaultRenderer.setInScroll(true);
        defaultRenderer.setZoomEnabled(false);
        defaultRenderer.setPanEnabled(false);
        defaultRenderer.setLegendTextSize(20);
        defaultRenderer.setShowLabels(false);
        defaultRenderer.setLabelsColor(R.color.black);
        return ChartFactory.getPieChartView(context,series,defaultRenderer);
    }

    /*public static final int COLOR_GREEN = Color.parseColor("#62c51a");
    public static final int COLOR_ORANGE = Color.parseColor("#ff6c0a");
    public static final int COLOR_BLUE = Color.parseColor("#23bae9");

    public static GraphicalView getNewInstance(Context context, int income, int costs)
    {
        return ChartFactory.getPieChartView(context, getDataSet(context, income, costs), getRenderer());
    }

    private static DefaultRenderer getRenderer()
    {
        int[] colors = new int[] { COLOR_GREEN, COLOR_ORANGE, COLOR_BLUE };

        DefaultRenderer defaultRenderer = new DefaultRenderer();
        for (int color : colors)
        {
            SimpleSeriesRenderer simpleRenderer = new SimpleSeriesRenderer();
            simpleRenderer.setColor(color);
            defaultRenderer.addSeriesRenderer(simpleRenderer);
        }
        defaultRenderer.setShowLabels(false);
        defaultRenderer.setShowLegend(false);
        return defaultRenderer;
    }

    private static CategorySeries getDataSet(Context context, int income, int costs)
    {
        CategorySeries series = new CategorySeries("Chart");
        series.add(context.getString(R.string.income), income);
        series.add(context.getString(R.string.costs), costs);
        series.add(context.getString(R.string.total), income - costs);
        return series;
    }*/
}
