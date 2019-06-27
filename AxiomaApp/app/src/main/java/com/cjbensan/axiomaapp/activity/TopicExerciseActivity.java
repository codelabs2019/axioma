package com.cjbensan.axiomaapp.activity;

import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.cjbensan.axiomaapp.R;
import com.cjbensan.axiomaapp.adapter.TopicExerciseAdapter;
import com.cjbensan.axiomaapp.domain.TopicExercise;

import java.util.ArrayList;
import java.util.List;

public class TopicExerciseActivity extends AppCompatActivity {

    public static final String[] topics = {
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

    private List<TopicExercise> items = new ArrayList<>();
    private RecyclerView recyclerView;
    private TopicExerciseAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercises_topic);

        getWindow().setStatusBarColor(ResourcesCompat.getColor(
                getResources(), R.color.colorIndigo, null));

        adapter = new TopicExerciseAdapter(items, new TopicExerciseAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(TopicExercise item) {

            }
        });

        setupTopicExerciseData();

        setupToolbar();
        setupRecyclerView();
    }

    private void setupToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_exercises);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void setupRecyclerView() {
        RecyclerView.LayoutManager manager = new LinearLayoutManager(this);

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view_topic_exercises);
        recyclerView.setLayoutManager(manager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
    }

    private void setupTopicExerciseData() {
        for (int i = 0; i < topics.length; i++) {
            items.add(new TopicExercise(String.format("%d", i + 1), topics[i]));
        }


        adapter.notifyDataSetChanged();
    }
}
