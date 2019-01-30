package biz.thinkcomputers.shdc.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.SpinnerAdapter;
import android.widget.TextView;

import java.util.List;

import biz.thinkcomputers.shdc.R;
import biz.thinkcomputers.shdc.model.Term;

public class TermAdapter extends BaseAdapter implements SpinnerAdapter{
    private Context context;
    private List<Term> data;

    public TermAdapter(Context context, List<Term> data) {
        this.context = context;
        this.data = data;


    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup viewGroup) {
        TextView txt;

        LayoutInflater layoutInflater = LayoutInflater.from(context);
        txt = (TextView)layoutInflater.inflate(R.layout.spinner_dropdown,viewGroup,false);

        txt.setText(data.get(i).getText());

        return txt;

    }
}
