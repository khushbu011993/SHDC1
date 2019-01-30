package biz.thinkcomputers.shdc.student.viewattendance;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.achartengine.ChartFactory;
import org.achartengine.GraphicalView;
import org.achartengine.model.CategorySeries;
import org.achartengine.renderer.DefaultRenderer;
import org.achartengine.renderer.SimpleSeriesRenderer;

import java.util.List;
import biz.thinkcomputers.shdc.PieChart;
import biz.thinkcomputers.shdc.R;
import biz.thinkcomputers.shdc.model.Student;


public class StudentViewAttendanceListViewAdapter extends BaseAdapter {

    Context context;
    LayoutInflater layoutInflater;
    int layoutResourceId;
    private  List<Student> stList;
    ViewHolder holder;
    int present;
    int absent;
    int percentage;
    int totalLecture;


    public StudentViewAttendanceListViewAdapter(Context context, int layoutResourceId,List<Student> stList) {
        this.context = context;
        this.layoutResourceId = layoutResourceId;
        this.stList = stList;
    }


    @Override
    public int getCount() {
        return stList.size();
    }

    @Override
    public Object getItem(int position) {
        return stList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {


        present = stList.get(position).getPresent();
        absent = stList.get(position).getAbsent();
        percentage=stList.get(position).getPercentage();
        totalLecture=stList.get(position).getTotalLectures();

        PieChart pieChart = new PieChart();
        GraphicalView graphicalView = pieChart.getGraphicalView(context,present,absent);
        // Get the data item for this position/
        if (convertView == null) {
            layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            // Check if an existing view is being reused, otherwise inflate the view

            //convertView = layoutInflater.inflate(R.layout.list_markattendance, parent, false);
            //resultp = stList.get(position);
            //holder = new ViewHolder();

            convertView = layoutInflater.inflate(R.layout.list_item_student_view_attendance, parent, false);
            holder = new ViewHolder();



            //LinearLayout pieGraph = (LinearLayout)convertView.findViewById(R.id.chartView);
            //pieGraph.addView(graphicalView);
            holder.sNo = (TextView) convertView.findViewById(R.id.student_serialNo);
            holder.subjectName = (TextView) convertView.findViewById(R.id.subject_name);
            holder.pieChartView = (LinearLayout)convertView.findViewById(R.id.chartView);
            holder.totalTecture = (TextView) convertView.findViewById(R.id.lecture_count);
            holder.percentage = (TextView) convertView.findViewById(R.id.percentage_count);
            holder.presentTv = (TextView) convertView.findViewById(R.id.color_present_id);
            holder.absentTv = (TextView) convertView.findViewById(R.id.color_absent_id);





            //holder.pieChartView.addView(openChart());

            //holder.pieChartView.setVisibility(View.VISIBLE);
            convertView.setTag(holder);





        }else {
            holder = (ViewHolder) convertView.getTag();
        }

        //holder.pieChartView.addView(graphicalView);

        //holder.pieChartView.setVisibility(View.VISIBLE);
        holder.pieChartView.addView(graphicalView);
        String percent = String.format("%d", percentage)+" %";

        holder.sNo.setText(stList.get(position).getsNo());
        holder.subjectName.setText(stList.get(position).getCodeName());
        holder.totalTecture.setText(String.format("%d", totalLecture));
        holder.percentage.setText(percent);
        holder.presentTv.setText(String.format("%d", present));
        holder.absentTv.setText(String.format("%d", absent));


        return convertView;
    }


   /* private View openChart(){

        CategorySeries series = new CategorySeries("Pie Chart");

        int[] portion = {20,80};
        String[] seriesName = new String[]{"Present","Absent"};

        int numSeries = 2;
        for(int i=0;i<numSeries;i++){
            series.add(seriesName[i],portion[i]  );
        }

        DefaultRenderer defaultRenderer = new DefaultRenderer();

        SimpleSeriesRenderer simpleSeriesRenderer = null;

        int[] color ={Color.GREEN,Color.RED};
        for(int i=0;i<numSeries;i++){
            simpleSeriesRenderer = new SimpleSeriesRenderer();
            simpleSeriesRenderer.setColor(color[i]);
            defaultRenderer.addSeriesRenderer(simpleSeriesRenderer);
        }

        return ChartFactory.getPieChartView(context, series, defaultRenderer);
    }*/
        public static class ViewHolder {
            public TextView sNo;
            public TextView subjectName;
            public LinearLayout pieChartView;
            public TextView totalTecture;
            public TextView percentage;
            public TextView presentTv;
            public TextView absentTv;

        }
    }

