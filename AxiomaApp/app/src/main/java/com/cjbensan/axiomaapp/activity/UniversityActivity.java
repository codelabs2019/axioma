package com.cjbensan.axiomaapp.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.cjbensan.axiomaapp.R;
import com.cjbensan.axiomaapp.adapter.UniversityAdapter;
import com.cjbensan.axiomaapp.domain.University;

import java.util.ArrayList;
import java.util.List;

public class UniversityActivity extends AppCompatActivity {

    public static final String UNIVERSITY = "com.cjbensan.axiomaapp.UNIVERSITY";

    private List<University> items ;
    private RecyclerView recyclerView;
    private UniversityAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_university);

        items = new ArrayList<University>();
        adapter = new UniversityAdapter(items, new UniversityAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(University item) {
                Intent intent = new Intent(UniversityActivity.this, UserSessionActivity.class);
                //intent.putExtra(UNIVERSITY, item.getName());
                startActivity(intent);
            }
        });

        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this, 2);
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);

        setupUniversityData();
    }

    private void setupUniversityData() {
        items.add(new University("Univ. Nacional de Ingeniería", R.drawable.stark));
        items.add(new University("Univ. Nacional Mayor de San Marcos", R.drawable.stark));
        items.add(new University("Univ. Nacional del Callao", R.drawable.stark));
        items.add(new University("Univ. Nacional Federico Villarreal", R.drawable.stark));
        items.add(new University("Univ. Nacional Agraria La Molina", R.drawable.stark));
        items.add(new University("Pontificia Univ. Católica del Perú", R.drawable.stark));
        items.add(new University("Universidad Peruana Cayetano Heredia", R.drawable.stark));
        items.add(new University("Univ.del Pacífico", R.drawable.stark));
        items.add(new University("Univ. de Lima", R.drawable.stark));
        items.add(new University("Univ. Ricardo Palma", R.drawable.stark));
        items.add(new University("Univ. Peruana de Ciencias Aplicadas", R.drawable.stark));

        adapter.notifyDataSetChanged();
    }
}
