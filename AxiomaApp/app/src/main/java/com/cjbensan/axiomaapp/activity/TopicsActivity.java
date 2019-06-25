package com.cjbensan.axiomaapp.activity;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.cjbensan.axiomaapp.R;
import com.cjbensan.axiomaapp.adapter.TopicAdapter;
import com.cjbensan.axiomaapp.domain.Topic;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class TopicsActivity extends AppCompatActivity {

    public static final String[] names = {
            "Potenciación y radicación en R",
            "Radicales dobles y racionalizacion",
            "Desigualdades e inecuaciones",
            "Valor absoluto",
            "Números complejos",
            "Polinomios",
            "Productos notables",
            "División de polinomios",
            "Binomio de Newton y cocientes notables",
            "Raíces de un polinomio",
            "Factorización de polinomios",
            "MCD y MCM de polinomios",
            "Ecuaciones",
            "Determinantes y sistemas de ecuaciones",
            "Inecuaciones"
    };

    private List<Topic> items = new ArrayList<>();
    private RecyclerView recyclerView;
    private TopicAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_topics);

        getWindow().setStatusBarColor(ResourcesCompat.getColor(
                getResources(), R.color.colorIndigo, null));

        Intent intent = getIntent();
        int thumbnailCourse = intent.getIntExtra("THUMBNAIL", 0);
        String nameCourse = intent.getStringExtra("NAME");

        Toolbar toolbar = (Toolbar) findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Typeface typeface = ResourcesCompat.getFont(this, R.font.varela_round);
        CollapsingToolbarLayout ctbl =
                (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar_layout);

        ctbl.setExpandedTitleTextAppearance(R.style.ExpandedAppBar);
        ctbl.setExpandedTitleTypeface(typeface);
        ctbl.setCollapsedTitleTextAppearance(R.style.CollapsedAppBar);
        ctbl.setCollapsedTitleTypeface(typeface);
        ctbl.setTitle(nameCourse);

        CircleImageView thumbnailImg = (CircleImageView) findViewById(R.id.img_thumbnail);
        thumbnailImg.setImageResource(thumbnailCourse);

        adapter = new TopicAdapter(items, new TopicAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Topic item) {

            }
        });

        RecyclerView.LayoutManager manager = new LinearLayoutManager(this);
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view_topics);
        recyclerView.setLayoutManager(manager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);

        setupTopicsData();
    }

    private void setupTopicsData() {
        for (int i = 0; i < names.length; i++) {
            items.add(new Topic(String.format("%s", i + 1), names[i], "5", "10"));
        }

        adapter.notifyDataSetChanged();
    }
}
