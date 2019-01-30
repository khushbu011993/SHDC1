package biz.thinkcomputers.shdc.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import biz.thinkcomputers.shdc.R;
import biz.thinkcomputers.shdc.model.Degree;
import biz.thinkcomputers.shdc.model.Subject;

/**
 * Created by Dheeraj on 8/18/2017.
 */
public class SubjectAdapter extends BaseAdapter {

    private Context context;
    private List<Subject> data;

    public SubjectAdapter(Context context, List<Subject> data) {
        this.context = context;
        this.data = data;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int i) {
        return data.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        TextView txt;

        LayoutInflater layoutInflater = LayoutInflater.from(context);
        txt = (TextView)layoutInflater.inflate(R.layout.spinner_dropdown,viewGroup,false);


        txt.setText(data.get(i).getSubject_Name());
        txt.setTextSize(18);

        return txt;

    }

}