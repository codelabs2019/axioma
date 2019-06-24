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
import com.cjbensan.axiomaapp.activity.TopicsActivity;
import com.cjbensan.axiomaapp.adapter.CourseAdapter;
import com.cjbensan.axiomaapp.domain.Course;

import java.util.ArrayList;
import java.util.List;


public class CoursesFragment extends Fragment {

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

    public static final String[] teachers = {
            "Jorge Pérez",
            "Carlos Santana",
            "Pedro Reyes",
            "Carolina Quintana",
            "Liliana Portocarrero",
            "Manuel Ortega",
            "Sandra Cornejo",
            "José Montero",
            "Susan León",
            "Nicolás Salinas",
            "Benicio Benavides",
            "Alfredo Arancibia",
            "Jocelyn Quispe",
            "Jasmyn Zurita",
            "Alan Ortega"
    };

    private List<Course> items = new ArrayList<>();
    private RecyclerView recyclerView;
    private CourseAdapter adapter;

    public CoursesFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        adapter = new CourseAdapter(items, new CourseAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Course item) {
                Intent intent = new Intent(getContext(), TopicsActivity.class);
                intent.putExtra("NAME", item.getName());
                startActivity(intent);
            }
        });

        setupCoursesData();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_courses, container, false);

        setupRecyclerView(view);

        return view;
    }

    private void setupCoursesData() {
        for (int i = 0; i < thumbnails.length; i++) {
            items.add(new Course(
                    thumbnails[i],
                    names[i],
                    teachers[i],
                    "30 clases",
                    "100 ejercicios"));
        }

        adapter.notifyDataSetChanged();
    }

    private void setupRecyclerView(View view) {
        RecyclerView.LayoutManager manager =
                new LinearLayoutManager(getActivity().getApplicationContext());

        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(manager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
    }
}
