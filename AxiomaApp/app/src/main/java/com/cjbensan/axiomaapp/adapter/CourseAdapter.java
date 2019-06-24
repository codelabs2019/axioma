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
import com.cjbensan.axiomaapp.domain.Course;

import java.util.List;

public class CourseAdapter extends RecyclerView.Adapter<CourseAdapter.MyViewHolder> {

    private final List<Course> items;
    private final OnItemClickListener listener;

    public CourseAdapter(List<Course> items, OnItemClickListener listener) {
        this.items = items;
        this.listener = listener;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private ImageView thumbnail;
        private TextView name;
        private TextView teacher;
        private TextView classes;
        private TextView exercises;

        public MyViewHolder(View view) {
            super(view);
            thumbnail = (ImageView) view.findViewById(R.id.img_thumbnail);
            name = (TextView) view.findViewById(R.id.txt_name);
            teacher = (TextView) view.findViewById(R.id.txt_teacher);
            classes = (TextView) view.findViewById(R.id.txt_classes);
            exercises = (TextView) view.findViewById(R.id.txt_exercises);
        }

        public void bind(final Course item, final OnItemClickListener listener) {
            thumbnail.setImageResource(item.getThumbnail());
            name.setText(item.getName());
            teacher.setText(item.getTeacher());
            classes.setText(item.getClasses());
            exercises.setText(item.getExercises());

            CardView cardView = itemView.findViewById(R.id.card_view);
            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onItemClick(item);
                }
            });
        }
    }


    public interface OnItemClickListener {
        void onItemClick(Course item);
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.card_view_course, viewGroup, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        myViewHolder.bind(items.get(i), listener);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}
