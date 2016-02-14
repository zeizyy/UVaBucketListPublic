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
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Button;

public class DetailActivity extends AppCompatActivity {

    Bucket bucket;

    // edit view
    CheckBox checkBox_edit;
    EditText title_edit;
    EditText description_edit;
    Button button_save;
    Button button_cancel;

    // display view
    CheckBox checkBox_display;
    TextView description_display;
    Button button_edit;
    Button button_delete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // comment
        super.onCreate(savedInstanceState);
        final int id = getIntent().getExtras().getInt("id");
        bucket = MainActivity.db.getBucket(id);

        setContentView(R.layout.activity_detail);

        // display part
        checkBox_display = (CheckBox) findViewById(R.id.detail_checkbox_display);
        checkBox_display.setChecked(bucket.isChecked());
        checkBox_display.setText(bucket.getTitle());
        checkBox_display.setOnCheckedChangeListener(new CheckBox.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                MainActivity.adapter.setChecked(bucket.getId(), isChecked);
            }
        });

        description_display = (TextView) findViewById(R.id.textView_detail_description);
        description_display.setText(bucket.getDescription());

        // edit part
        checkBox_edit = (CheckBox) findViewById(R.id.detail_checkbox);
        checkBox_edit.setVisibility(View.INVISIBLE);
        title_edit = (EditText)findViewById(R.id.detail_title);
        title_edit.setVisibility(View.INVISIBLE);
        description_edit = (EditText) findViewById(R.id.textView_detail_description_edit);
        description_edit.setVisibility(View.INVISIBLE);
        button_save = (Button)findViewById(R.id.button3);
        button_save.setVisibility(View.INVISIBLE);
        button_cancel = (Button)findViewById(R.id.button4);
        button_cancel.setVisibility(View.INVISIBLE);



        // buttons
        button_edit = (Button) findViewById(R.id.button);
        button_edit.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // display part be invisible
                checkBox_display.setVisibility(View.INVISIBLE);
                description_display.setVisibility(View.INVISIBLE);
                button_delete.setVisibility(View.INVISIBLE);
                button_edit.setVisibility(View.INVISIBLE);

                // edit part be visible
                checkBox_edit.setVisibility(View.VISIBLE);
                checkBox_edit.setChecked(bucket.isChecked());
                checkBox_edit.setOnCheckedChangeListener(new CheckBox.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        MainActivity.adapter.setChecked(bucket.getId(), isChecked);
                    }
                });

                title_edit.setVisibility(View.VISIBLE);
                title_edit.setText(bucket.getTitle());


                description_edit.setVisibility(View.VISIBLE);
                description_edit.setText(bucket.getDescription());

                button_save.setVisibility(View.VISIBLE);
                button_save.setOnClickListener(new View.OnClickListener(){
                    public void onClick(View v){
                        bucket.setTitle(title_edit.getText().toString());
                        bucket.setDescription(description_edit.getText().toString());
                        MainActivity.adapter.updateItem(bucket);
                        finish();
                        startActivity(getIntent());
                    }
                    });

                button_cancel.setVisibility(View.VISIBLE);
                button_cancel.setOnClickListener(new View.OnClickListener(){
                    public void onClick(View v) {
//                        finish();
//                        startActivity(getIntent());
                        // display to visible
                        checkBox_display.setVisibility(View.VISIBLE);
                        description_display.setVisibility(View.VISIBLE);
                        button_edit.setVisibility(View.VISIBLE);
                        button_delete.setVisibility(View.VISIBLE);

                        // edit to invisible
                        checkBox_edit.setVisibility(View.INVISIBLE);
                        title_edit.setVisibility(View.INVISIBLE);
                        description_edit.setVisibility(View.INVISIBLE);
                        button_save.setVisibility(View.INVISIBLE);
                        button_cancel.setVisibility(View.INVISIBLE);
                    }
                });
            }
        });

        button_delete = (Button) findViewById(R.id.button2);
        button_delete.setOnClickListener(new View.OnClickListener() {
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
