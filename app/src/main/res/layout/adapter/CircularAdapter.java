package layout.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

import in.thinkcomputers.icampus.model.Circular;

/**
 * Created by think on 8/9/2017.
 */
public class CircularAdapter extends BaseAdapter {

    Context context;
    List<Circular> data;

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
        return null;
    }
}
