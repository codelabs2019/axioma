package com.cjbensan.axiomaapp.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.cjbensan.axiomaapp.R;
import com.cjbensan.axiomaapp.domain.TopicExercise;

import java.util.List;

public class TopicExerciseAdapter extends RecyclerView.Adapter<TopicExerciseAdapter.TopicExerciseViewHolder> {

    private final List<TopicExercise> items;
    private final OnItemClickListener listener;

    public TopicExerciseAdapter(List<TopicExercise> items, OnItemClickListener listener) {
        this.items = items;
        this.listener = listener;
    }

    public class TopicExerciseViewHolder extends RecyclerView.ViewHolder {

        private TextView number;
        private TextView name;

        public TopicExerciseViewHolder(View view) {
            super(view);

            number = (TextView) view.findViewById(R.id.txt_number);
            name = (TextView) view.findViewById(R.id.txt_class_name);
        }

        public void bind(final TopicExercise item, final OnItemClickListener listener) {
            number.setText(item.getNumber());
            name.setText(item.getName());

            CardView cardView = itemView.findViewById(R.id.card_view_topic_exercise);
            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onItemClick(item);
                }
            });
        }
    }

    public interface OnItemClickListener {
        void onItemClick(TopicExercise item);
    }

    @NonNull
    @Override
    public TopicExerciseViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.card_view_topic_exercise, viewGroup, false);

        return new TopicExerciseViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull TopicExerciseViewHolder exerciseViewHolder, int i) {
        exerciseViewHolder.bind(items.get(i), listener);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}
