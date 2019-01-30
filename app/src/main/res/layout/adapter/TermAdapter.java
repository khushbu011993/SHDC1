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
import in.thinkcomputers.icampus.model.Term;

/**
 * Created by DELL1545 on 4/13/2017.
 */
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
    public View getView(int position, View convertView, ViewGroup parent) {
        TextView txt;
        if (convertView!=null){
            txt= (TextView)convertView;
        }else{
            LayoutInflater layoutInflater = LayoutInflater.from(context);
            txt = (TextView)layoutInflater.inflate(R.layout.spinner_dropdown,parent,false);
        }

        txt.setText(data.get(position).getText());
        return txt;

    }
}
