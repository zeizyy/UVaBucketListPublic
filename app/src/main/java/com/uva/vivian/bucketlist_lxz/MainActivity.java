package com.uva.vivian.bucketlist_lxz;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

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

        final TextView textView = (TextView) findViewById(R.id.textView);
        TextView.OnClickListener onClickListener = new TextView.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Context context = getApplicationContext();
//                CharSequence text = "Hello toast!";
//                int duration = Toast.LENGTH_SHORT;
//
//                Toast toast = Toast.makeText(context, text, duration);
//                toast.show();
                Intent intent = new Intent(v.getContext(), DetailActivity.class);
                intent.putExtra("title", ((TextView) v).getText());
                startActivity(intent);
            }
        };
        textView.setOnClickListener(onClickListener);

        final TextView textView2 = (TextView) findViewById(R.id.textView2);
        textView2.setOnClickListener(onClickListener);

        // read csv file to dynamically generate the list
        InputStream inputStream = getResources().openRawResource(R.raw.uva111things);
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        String line = null;
        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.listLinearLayout);
        try {
            line = reader.readLine();
            while (line != null) {
                CheckBox checkBox = new CheckBox(this);

                TextView tv = new TextView(this);
                tv.setText(line);
                tv.setOnClickListener(onClickListener);
                tv.setGravity(Gravity.CENTER_VERTICAL);

                LinearLayout oneRow = new LinearLayout(this);
                oneRow.addView(checkBox);
                oneRow.addView(tv);

                linearLayout.addView(oneRow);
                line = reader.readLine();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
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
