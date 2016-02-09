package com.uva.vivian.bucketlist_lxz;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.util.SparseArray;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.view.LayoutInflater;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by xiongqian on 2/4/16.
 * .
 */
public class ListViewAdapter extends ArrayAdapter<Bucket> {
    private static LayoutInflater inflater = null;
    private static BucketOpenHelper db;
    Context context;
    //    HashMap<Bucket, Integer> mIdMap = new HashMap<Bucket, Integer>();
    private SparseArray<Bucket> idToBucketMap = new SparseArray<>();
    private ArrayList<Bucket> bucketList = new ArrayList<>();
    private int lastPosition = -1;

    /**
     * Constructor
     *
     * @param context            The current context.
     * @param resource           The resource ID for a layout file containing a layout to use when
     *                           instantiating views.
     * @param textViewResourceId The id of the TextView within the layout resource to be populated
     * @param objects            The objects to represent in the ListView.
     */
    public ListViewAdapter(Context context, int resource, int textViewResourceId, List<Bucket> objects, BucketOpenHelper bucketOpenHelper) {
        super(context, resource, textViewResourceId, objects);
        inflater = (LayoutInflater) context.
                getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.context = context;
        for (Bucket bucket : objects) {
//            mIdMap.put(bucket, bucket.getId());
            idToBucketMap.put(bucket.getId(), bucket);
            bucketList.add(bucket);
        }
        db = bucketOpenHelper;
    }
//
//    @Override
//    public long getItemId(int position) {
//        Bucket item = getItem(position);
//        return mIdMap.get(item);
//    }


    @Override
    public int getCount() {
        return bucketList.size();
    }


    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View rowView = convertView;

        if (rowView == null) {
            rowView = inflater.inflate(R.layout.list_row, parent, false);
            ViewHolder viewHolder = new ViewHolder(context, (TextView) rowView.findViewById(R.id.textView_list_item), (CheckBox) rowView.findViewById(R.id.checkBox_list_item));
            rowView.setTag(viewHolder);
        }

        final ViewHolder holder = (ViewHolder) rowView.getTag();
        holder.position = position;
        holder.textView.setText(bucketList.get(position).getTitle());
        holder.checkBox.setChecked(bucketList.get(position).isChecked());

        Animation animation = AnimationUtils.loadAnimation(getContext(), (position > lastPosition) ? R.anim.up_from_bottom : R.anim.down_from_top);
        holder.textView.startAnimation(animation);
        lastPosition = position;

        return rowView;
    }

    public boolean setChecked(int id, boolean checked) {
        Bucket bucket = idToBucketMap.get(id);
        bucket.setChecked(checked);
//        this.notifyDataSetChanged(); // this will trigger animation
        return db.setFlag(bucket.getId(), checked);
    }

    public boolean removeItem(int position) {
        Bucket bucket = bucketList.get(position);
        bucketList.remove(position);
        this.notifyDataSetChanged();
        return db.removeBucket(bucket.getId());
    }

    private class ViewHolder {
        int position;
        TextView textView;
        CheckBox checkBox;
        Context context;

        public ViewHolder(final Context context, TextView textView, CheckBox checkBox) {
            this.context = context;

            this.checkBox = checkBox;
            this.checkBox.setOnClickListener(onClickListener_c);

            this.textView = textView;
            textView.setOnTouchListener(new OnSwipeTouchListener(this.context) {
                public void onSwipeLeft() {
                    removeItem(position);
                }

                public void onClick() {
                    Log.d("TextView onCLick", "Clicked");
                    Intent intent = new Intent(context, DetailActivity.class);
                    intent.putExtra("id", bucketList.get(position).getId());
                    context.startActivity(intent);
                }
            });
        }

        private CheckBox.OnClickListener onClickListener_c = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bucket bucket = bucketList.get(position);
                setChecked(bucket.getId(), !bucket.isChecked());
            }
        };
    }


}
