package com.uva.vivian.bucketlist_lxz;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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

        // read csv file to dynamically generate the list
        InputStream inputStream = getResources().openRawResource(R.raw.uva111things);
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        ListView listView = (ListView) findViewById(R.id.listView);
        ArrayList<String> lines = new ArrayList<>();
        ArrayList<Boolean> checked = new ArrayList<>();
        try {
            String line;
            String [] split;
            while ((line = reader.readLine())  != null) {
                split = line.split(",");
                lines.add(split[0]);
                if(split[1].equals("1")){
                    checked.add(true);
                } else {
                    checked.add(false);
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        ListAdapter customAdapter = new CustomAdapter(this, R.id.listView, R.id.textView1, lines, checked);
        listView.setAdapter(customAdapter);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
