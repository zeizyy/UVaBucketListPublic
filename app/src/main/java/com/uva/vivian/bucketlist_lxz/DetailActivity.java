package com.uva.vivian.bucketlist_lxz;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Button;
import android.widget.Toast;

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
        textView.setText(bucket.getDescription());

        Button edit_button = (Button) findViewById(R.id.button);
        edit_button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Perform action on click
            }
        });

        Button delete_button = (Button) findViewById(R.id.button2);
        delete_button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                AlertDialog alertDialog = new AlertDialog.Builder(
                        DetailActivity.this).create();

                // Setting Dialog Title
                alertDialog.setTitle("Delete from list?");

                // Setting Dialog Message
                alertDialog.setMessage("Your are trying to delete \"" + bucket.getTitle() + "\", click on OK to continue, or click anywhere to cancel.");

                // Setting Icon to Dialog
                alertDialog.setIcon(R.drawable.delete);

                // Setting OK Button
                alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // Write your code here to execute after dialog closed
//                        Toast.makeText(getApplicationContext(), "You clicked on OK", Toast.LENGTH_SHORT).show();
//                        MainActivity.db.removeBucket(bucket.getId());
                        MainActivity.adapter.removeItemById(bucket.getId());
//                        Intent intent = new Intent(DetailActivity.this, MainActivity.class);
//                        startActivity(intent);
                        finish();
                    }
                });

                // Showing Alert Message
                alertDialog.show();
            }
        });

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
