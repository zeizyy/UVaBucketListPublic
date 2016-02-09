package com.uva.vivian.bucketlist_lxz;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // comment
        super.onCreate(savedInstanceState);
        int id = getIntent().getExtras().getInt("id");
        final Bucket bucket = MainActivity.db.getBucket(id);

        setContentView(R.layout.activity_detail);
        CheckBox checkBox = (CheckBox) findViewById(R.id.checkBox_detail_title);
        checkBox.setText(bucket.getTitle());
        checkBox.setChecked(bucket.isChecked());
        checkBox.setOnCheckedChangeListener(new CheckBox.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                MainActivity.adapter.setChecked(bucket.getId(), isChecked);
            }
        });

        TextView textView = (TextView) findViewById(R.id.textView_detail_description);
        textView.setText(bucket.getDes());

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

}
