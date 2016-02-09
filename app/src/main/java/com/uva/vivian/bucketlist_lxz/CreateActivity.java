package com.uva.vivian.bucketlist_lxz;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class CreateActivity extends AppCompatActivity {

    private EditText nameField;
    private EditText descriptionField;
    private Button submitButton;

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


        nameField = (EditText) findViewById(R.id.editText_create_title);
        descriptionField = (EditText) findViewById(R.id.editText_create_description);
        submitButton = (Button) findViewById(R.id.button_create_bucket);
        submitButton.setEnabled(false);

        TextWatcher textWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                enableSubmitIfReady();
            }
        };
        nameField.addTextChangedListener(textWatcher);
        descriptionField.addTextChangedListener(textWatcher);
    }

    public void enableSubmitIfReady() {
        boolean isReady = !descriptionField.getText().toString().isEmpty() && !nameField.getText().toString().isEmpty();
        submitButton.setEnabled(isReady);
    }

    public void createBucket(View view) {
        Context context = this.getApplicationContext();
        String name = nameField.getText().toString();
        String description = descriptionField.getText().toString();
        if (name.isEmpty() || description.isEmpty()) {
            Toast.makeText(context, "Missing required field(s)", Toast.LENGTH_SHORT).show();
            return;
        }

        Bucket bucket = new Bucket(name, description);
        if (MainActivity.adapter.insertItem(bucket)) {
            Toast.makeText(context, "New Bucket Created!", Toast.LENGTH_SHORT).show();
            finish();
        } else {
            Toast.makeText(context, "Fail to create a new bucket :(", Toast.LENGTH_SHORT).show();
        }

//        MainActivity.adapter.insertItem(bucket, 0); // insert at front
//        Intent intent = new Intent(context, MainActivity.class);
//        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//        startActivity(intent);
    }

}
