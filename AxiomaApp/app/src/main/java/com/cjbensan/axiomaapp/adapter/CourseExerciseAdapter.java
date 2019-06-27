package com.cjbensan.axiomaapp.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.cjbensan.axiomaapp.R;
import com.cjbensan.axiomaapp.domain.CourseExercise;

import java.util.List;

public class CourseExerciseAdapter extends RecyclerView.Adapter<CourseExerciseAdapter.CourseExerciseViewHolder> {

    private final List<CourseExercise> items;
    private final OnItemClickListener listener;

    public CourseExerciseAdapter(List<CourseExercise> items, OnItemClickListener listener) {
        this.items = items;
        this.listener = listener;
    }

    public class CourseExerciseViewHolder extends RecyclerView.ViewHolder {
        private ImageView thumbnail;
        private TextView name;

        public CourseExerciseViewHolder(View view) {
            super(view);

            thumbnail = (ImageView) view.findViewById(R.id.img_thumbnail);
            name = (TextView) view.findViewById(R.id.txt_name);
        }

        public void bind(final CourseExercise item, final OnItemClickListener listener) {
            thumbnail.setImageResource(item.getThumbnail());
            name.setText(item.getName());

            CardView cardView = itemView.findViewById(R.id.card_view_course_exercise);
            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onItemClick(item);
                }
            });
        }
    }

    public interface OnItemClickListener {
        void onItemClick(CourseExercise item);
    }

    @NonNull
    @Override
    public CourseExerciseViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.card_view_course_exercise, viewGroup, false);

        return new CourseExerciseViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull CourseExerciseViewHolder courseExerciseViewHolder, int i) {
        courseExerciseViewHolder.bind(items.get(i), listener);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}
