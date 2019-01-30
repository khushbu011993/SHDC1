package layout.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import in.thinkcomputers.icampus.R;
import in.thinkcomputers.icampus.model.Degree;

/**
 * Created by DELL1545 on 4/6/2017.
 */
public class DegreeAdapter extends BaseAdapter  {
    private Context context;
    private List<Degree> data;

    public DegreeAdapter(Context context, List<Degree> data) {
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
        if (view!=null){
            txt= (TextView)view;
        }else{
            LayoutInflater layoutInflater = LayoutInflater.from(context);
            txt = (TextView)layoutInflater.inflate(R.layout.spinner_dropdown,viewGroup,false);
        }
        txt.setText(data.get(i).getDegree_Name());

        return txt;

    }



}
