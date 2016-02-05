package com.uva.vivian.bucketlist_lxz;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.view.LayoutInflater;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by xiongqian on 2/4/16.
 * .
 */
public class CustomAdapter extends ArrayAdapter<String> {
    private static LayoutInflater inflater = null;
    private TextView.OnClickListener onClickListener = new TextView.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(v.getContext(), DetailActivity.class);
            intent.putExtra("title", ((TextView) v).getText());
            context.startActivity(intent);
        }
    };
    Context context;

    HashMap<String, Integer> mIdMap = new HashMap<String, Integer>();
    ArrayList<String> itemList = new ArrayList<>();
    ArrayList<Boolean> checked = new ArrayList<>();
    BucketOpenHelper db;
    /**
     * Constructor
     *
     * @param context            The current context.
     * @param resource           The resource ID for a layout file containing a layout to use when
     *                           instantiating views.
     * @param textViewResourceId The id of the TextView within the layout resource to be populated
     * @param objects            The objects to represent in the ListView.
     */
    public CustomAdapter(Context context, int resource, int textViewResourceId, List<String> objects, ArrayList<Boolean> checked, BucketOpenHelper db) {
        super(context, resource, textViewResourceId, objects);
        inflater = (LayoutInflater) context.
                getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.context = context;
        for (int i = 0; i < objects.size(); ++i) {
            mIdMap.put(objects.get(i), i);
            itemList.add(objects.get(i));
        }
        this.checked = checked;
        this.db = db;
    }

    @Override
    public long getItemId(int position) {
        String item = getItem(position);
        return mIdMap.get(item);
    }

    private static class ViewHolder {
        TextView textView;
        CheckBox checkBox;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View rowView = convertView;

        if (rowView == null) {
            rowView = inflater.inflate(R.layout.list_row, null);
            ViewHolder viewHolder = new ViewHolder();
            viewHolder.textView = (TextView) rowView.findViewById(R.id.textView1);
            viewHolder.checkBox = (CheckBox) rowView.findViewById(R.id.checkBox1);
            viewHolder.textView.setOnClickListener(onClickListener);
            rowView.setTag(viewHolder);
        }

        final ViewHolder holder = (ViewHolder) rowView.getTag();
        holder.textView.setText(itemList.get(position));
        holder.checkBox.setChecked(checked.get(position)); // hard coded
        holder.checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String thing = (String) holder.textView.getText();
                db.toggleFlag(thing);
                checked.set(position,!checked.get(position));
//                Log.i("Database_name",db.getDatabaseName());
            }
        });
        return rowView;
    }
}
