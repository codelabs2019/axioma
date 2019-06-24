package com.cjbensan.axiomaapp.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.cjbensan.axiomaapp.R;

public class TopicsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_topics);

        Intent intent = getIntent();
        String name = intent.getStringExtra("NAME");

        TextView courseTxt = (TextView) findViewById(R.id.txt_course);
        courseTxt.setText(name);
    }
}
