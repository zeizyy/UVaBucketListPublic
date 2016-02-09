package com.uva.vivian.bucketlist_lxz;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class CreateActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);
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

    public void createBucket(View view) {
        Context context = this.getApplicationContext();
        EditText nameField = (EditText) findViewById(R.id.editText_create_title);
        String name = nameField.getText().toString();
        EditText descriptionField = (EditText) findViewById(R.id.editText_create_description);
        String description = descriptionField.getText().toString();
        if (name.isEmpty() || description.isEmpty()) {
            Toast.makeText(context, "Missing required field(s)", Toast.LENGTH_SHORT).show();
            return;
        }
        Bucket bucket = new Bucket(name, description);
        MainActivity.adapter.insertItem(bucket, MainActivity.adapter.getCount());
//        MainActivity.adapter.insertItem(bucket, 0); // insert at front
        Toast.makeText(context, "New Bucket Created!", Toast.LENGTH_SHORT).show();
//        Intent intent = new Intent(context, MainActivity.class);
//        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//        startActivity(intent);
        finish();
    }

}
