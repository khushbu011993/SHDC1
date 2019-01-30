package layout.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.SpinnerAdapter;
import android.widget.TextView;

import java.util.List;

import in.thinkcomputers.icampus.R;
import in.thinkcomputers.icampus.model.Stream;

/**
 * Created by DELL1545 on 4/12/2017.
 */
public class StreamAdapter extends BaseAdapter implements SpinnerAdapter {
    Context context;
    List<Stream> data;

    public StreamAdapter(Context context, List<Stream> data) {
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
    public View getView(int position, View view, ViewGroup viewGroup) {
        TextView txt;
        if (view!=null){
            txt= (TextView)view;
        }else{
            LayoutInflater layoutInflater = LayoutInflater.from(context);
            txt = (TextView)layoutInflater.inflate(R.layout.spinner_dropdown,viewGroup,false);
        }

        txt.setText(data.get(position).getStream_Name());
        return txt;
    }
}
