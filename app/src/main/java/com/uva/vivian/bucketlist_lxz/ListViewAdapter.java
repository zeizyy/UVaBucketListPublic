package com.uva.vivian.bucketlist_lxz;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.view.LayoutInflater;
import android.widget.CompoundButton;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by xiongqian on 2/4/16.
 * .
 */
public class ListViewAdapter extends ArrayAdapter<String> {
    private static LayoutInflater inflater = null;
    static BucketOpenHelper db;

    Context context;
    HashMap<String, Integer> mIdMap = new HashMap<String, Integer>();
    ArrayList<String> itemList = new ArrayList<>();
    ArrayList<Boolean> checked = new ArrayList<>();

    /**
     * Constructor
     *
     * @param context            The current context.
     * @param resource           The resource ID for a layout file containing a layout to use when
     *                           instantiating views.
     * @param textViewResourceId The id of the TextView within the layout resource to be populated
     * @param objects            The objects to represent in the ListView.
     */
    public ListViewAdapter(Context context, int resource, int textViewResourceId, List<String> objects, ArrayList<Boolean> checked, BucketOpenHelper bucketOpenHelper) {
        super(context, resource, textViewResourceId, objects);
        inflater = (LayoutInflater) context.
                getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.context = context;
        for (int i = 0; i < objects.size(); ++i) {
            mIdMap.put(objects.get(i), i);
            itemList.add(objects.get(i));
        }
        this.checked = checked;
        db = bucketOpenHelper;
    }

    @Override
    public long getItemId(int position) {
        String item = getItem(position);
        return mIdMap.get(item);
    }


    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View rowView = convertView;

        if (rowView == null) {
            rowView = inflater.inflate(R.layout.list_row, null);
            ViewHolder viewHolder = new ViewHolder(context, (TextView) rowView.findViewById(R.id.textView_list_item), (CheckBox) rowView.findViewById(R.id.checkBox_list_item));
            rowView.setTag(viewHolder);
        }

        final ViewHolder holder = (ViewHolder) rowView.getTag();
        holder.textView.setText(itemList.get(position));
        holder.checkBox.setChecked(checked.get(position));
        holder.setBucketID(position+1);

        return rowView;
    }


    private class ViewHolder {
        TextView textView;
        CheckBox checkBox;
        Context context;
        int bucketID;

        public ViewHolder(Context context, TextView textView, CheckBox checkBox) {
            this.context = context;

            this.textView = textView;
            this.checkBox = checkBox;

            this.textView.setOnClickListener(onClickListener);
            this.checkBox.setOnClickListener(onClickListener_c);
        }

        public void setBucketID (int bucketID) {
            this.bucketID = bucketID;
        }


        private TextView.OnClickListener onClickListener = new TextView.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), DetailActivity.class);
                intent.putExtra("title", ((TextView) v).getText());
                context.startActivity(intent);
            }
        };

        private CheckBox.OnClickListener onClickListener_c = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isChecked = ((CheckBox) v).isChecked();
                db.setFlag(bucketID, isChecked);
                checked.set(bucketID-1,isChecked);
            }
        };
    }


}
