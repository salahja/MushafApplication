package mufradat;

import android.content.Context;
import android.content.res.TypedArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mushafconsolidated.R;

public class MCustomerAdapter extends BaseAdapter {

    private Context context;
    private String[] surahNameList;
    private TypedArray imgs;
    private LayoutInflater inflater;

    // Constructor
    public MCustomerAdapter(Context context, String[] surahNameList, TypedArray imgs) {
        this.context = context;
        this.surahNameList = surahNameList;
        this.imgs = imgs;
        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return surahNameList.length;
    }

    @Override
    public Object getItem(int position) {
        return surahNameList[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
    @Override
    public boolean isEnabled(int position) {
        // Disable the first item (position 0)
        return position != 0;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View rowView = convertView;

        if (rowView == null) {
            rowView = inflater.inflate(R.layout.row, parent, false);
        }

        // Get the references to the views
        ImageView imgIcon = rowView.findViewById(R.id.imgIcon);
        TextView txtTitle = rowView.findViewById(R.id.txtTitle);

        // Set the data
        imgIcon.setImageResource(imgs.getResourceId(position, -1));
        txtTitle.setText(surahNameList[position]);

        return rowView;
    }
}
