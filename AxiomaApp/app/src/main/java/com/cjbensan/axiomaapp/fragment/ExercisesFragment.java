package com.cjbensan.axiomaapp.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cjbensan.axiomaapp.R;
import com.cjbensan.axiomaapp.activity.TopicExerciseActivity;
import com.cjbensan.axiomaapp.adapter.CourseExerciseAdapter;
import com.cjbensan.axiomaapp.domain.CourseExercise;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class ExercisesFragment extends Fragment {

    public static final int[] thumbnails = {
            R.drawable.algebra,
            R.drawable.aritmetica,
            R.drawable.geometria,
            R.drawable.trigonometria,
            R.drawable.fisica,
            R.drawable.quimica,
            R.drawable.estadistica,
            R.drawable.rm,
            R.drawable.rv,
            R.drawable.economia,
            R.drawable.historia,
            R.drawable.geografia,
            R.drawable.lenguaje,
            R.drawable.literatura,
            R.drawable.psicologia
    };

    public static final String[] names = {
            "Algebra", "Aritmética", "Geometría", "Trigonometría", "Física",
            "Química", "Estadística", "Raz. Matemático", "Raz. Verbal", "Economía",
            "Historia", "Geografía", "Lenguaje", "Literatura", "Psicología"
    };

    private List<CourseExercise> items = new ArrayList<>();
    private RecyclerView recyclerView;
    private CourseExerciseAdapter adapter;

    public ExercisesFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        adapter = new CourseExerciseAdapter(items, new CourseExerciseAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(CourseExercise item) {
                Intent intent = new Intent(getContext(), TopicExerciseActivity.class);
                startActivity(intent);
            }
        });

        setupCourseExercisesData();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_exercises, container, false);

        setupRecyclerView(view);

        return view;
    }

    private void setupCourseExercisesData() {
        for (int i = 0; i < thumbnails.length; i++) {
            items.add(new CourseExercise(thumbnails[i], names[i]));
        }

        adapter.notifyDataSetChanged();
    }

    private void setupRecyclerView(View view) {
        RecyclerView.LayoutManager manager =
                new LinearLayoutManager(getActivity().getApplicationContext());

        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view_course_exercises);
        recyclerView.setLayoutManager(manager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
    }
}
