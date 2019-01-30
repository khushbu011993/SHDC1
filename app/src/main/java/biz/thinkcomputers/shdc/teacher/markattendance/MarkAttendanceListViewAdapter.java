package biz.thinkcomputers.shdc.teacher.markattendance;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import java.util.List;

import biz.thinkcomputers.shdc.R;
import biz.thinkcomputers.shdc.model.Student;




public class MarkAttendanceListViewAdapter extends BaseAdapter {
    Context context;
    LayoutInflater layoutInflater;
    int layoutResourceId;
    int pos;
    //ArrayList<HashMap<String, List<String>>> data;
   // HashMap<String, List<String>> resultp = new HashMap<String, List<String>>();

    private final List<Student> stList;

    //in.thinkcomputers.icampus.MarkAttendanceStudentList mark = new in.thinkcomputers.icampus.MarkAttendanceStudentList();
    ViewHolder holder;
   /* boolean[] selection;*/


    public MarkAttendanceListViewAdapter(Context context,int layoutResourceId,List<Student> stList) {
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
    public View getView(final int position, View convertView, ViewGroup parent) {

        // Get the data item for this position
        if (convertView == null) {
            layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            // Check if an existing view is being reused, otherwise inflate the view

            convertView = layoutInflater.inflate(R.layout.list_markattendance, parent, false);
            //resultp = stList.get(position);
            holder = new ViewHolder();


            // Lookup view for data population
            holder.sNo = (TextView) convertView.findViewById(R.id.serialNo);
            holder.sRollNo = (TextView) convertView.findViewById(R.id.rollNo);
            holder.student_name = (TextView) convertView.findViewById(R.id.student_name);
            holder.chkSelected = (CheckBox) convertView.findViewById(R.id.checkbox);
            //convertView.setTag(holder);

          //box

            convertView.setTag(holder);
            convertView.setTag(R.id.serialNo, holder.sNo);
            convertView.setTag(R.id.rollNo, holder.sRollNo);
            convertView.setTag(R.id.student_name, holder.student_name);
            convertView.setTag(R.id.checkbox, holder.chkSelected);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.chkSelected.setTag(position); // This line is important.

        holder.sNo.setText(stList.get(position).getsNo());
        holder.sRollNo.setText(stList.get(position).getsRollNo());
        holder.student_name.setText(stList.get(position).getStudent_name());
        holder.chkSelected.setChecked(stList.get(position).isChecked());
         /*else {
            holder = (ViewHolder) convertView.getTag();
        }

      // Student student = (Student) getItem(position);
        holder.sNo.setText(stList.get(position).getsNo());
        holder.sRollNo.setText(stList.get(position).getsRollNo());
        holder.student_name.setText(stList.get(position).getStudent_name());
        holder.chkSelected.setChecked(stList.get(position).isChecked());
        holder.chkSelected.setTag(stList.get(position));
          pos = position;
        holder.chkSelected.setOnCheckedChangeListener(null);
        holder.chkSelected.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                CheckBox checkBox1 = (CheckBox) compoundButton;
                Student student = (Student) checkBox1.getTag();
                student.setIsChecked(false);
                stList.get(pos).setIsChecked(false);

                //resultp.get("Stdnt_Id");
            }
        });*/
        return convertView;
    }

    public static class ViewHolder  {
        public TextView sNo;
        public TextView sRollNo;
        public TextView student_name;
        public CheckBox chkSelected;
    }

    // method to access in activity after updating selection
   //lis
}