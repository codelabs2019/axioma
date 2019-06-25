package com.cjbensan.axiomaapp.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.cjbensan.axiomaapp.R;
import com.cjbensan.axiomaapp.domain.Topic;

import java.util.List;

public class TopicAdapter extends RecyclerView.Adapter<TopicAdapter.TopicViewHolder> {

    private final List<Topic> items;
    private final OnItemClickListener listener;

    public TopicAdapter(List<Topic> items, OnItemClickListener listener) {
        this.items = items;
        this.listener = listener;
    }

    public class TopicViewHolder extends RecyclerView.ViewHolder {
        private TextView number;
        private TextView name;
        private TextView classes;
        private TextView exercises;

        public TopicViewHolder(View view) {
            super(view);

            number = (TextView) view.findViewById(R.id.txt_number);
            name = (TextView) view.findViewById(R.id.txt_name);
            classes = (TextView) view.findViewById(R.id.txt_classes);
            exercises = (TextView) view.findViewById(R.id.txt_exercises);
        }

        public void bind(final Topic item, final OnItemClickListener listener) {
            number.setText(item.getNumber());
            name.setText(item.getName());
            classes.setText(String.format("%s clases", item.getClasses()));
            exercises.setText(String.format("%s ejercicios", item.getExercises()));

            CardView cardView = itemView.findViewById(R.id.card_view_topic);
            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onItemClick(item);
                }
            });
        }

    }

    public interface OnItemClickListener {
        void onItemClick(Topic item);
    }

    @NonNull
    @Override
    public TopicViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.card_view_topic, viewGroup, false);

        return new TopicViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull TopicViewHolder topicViewHolder, int i) {
        topicViewHolder.bind(items.get(i), listener);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}
