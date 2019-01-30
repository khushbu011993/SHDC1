package biz.thinkcomputers.shdc.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

import biz.thinkcomputers.shdc.R;
import biz.thinkcomputers.shdc.model.Circular;

public class CircularAdapter extends BaseAdapter {

    Context context;
    List<Circular> data;
    LayoutInflater layoutInflater;

    public CircularAdapter(Context context,List<Circular> data){
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
    public View getView(int position, View convertView, ViewGroup parent) {

        if(convertView == null){

            layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);


        }
        return null;
    }
}
