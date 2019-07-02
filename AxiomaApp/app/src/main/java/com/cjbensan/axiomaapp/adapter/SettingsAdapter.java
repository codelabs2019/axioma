package com.cjbensan.axiomaapp.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.cjbensan.axiomaapp.R;
import com.cjbensan.axiomaapp.domain.SettingsRow;
import com.cjbensan.axiomaapp.fragment.SettingsFragment;

import java.util.List;

public class SettingsAdapter extends RecyclerView.Adapter<SettingsAdapter.ViewHolder> {

    private final List<SettingsRow> items;
    private final OnItemClickListener listener;

    public SettingsAdapter(List<SettingsRow> items, OnItemClickListener listener) {
        this.items = items;
        this.listener = listener;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView label;
        private TextView value;

        public ViewHolder(View view) {
            super(view);
            label = (TextView) view.findViewById(R.id.txt_label);
            value = (TextView) view.findViewById(R.id.txt_value);
        }

        public void bind(final SettingsRow item, final OnItemClickListener listener) {
            label.setText(item.getLabel());
            value.setText(item.getValue());

            if (item.getId().equals(SettingsFragment.DELETE)) {
                label.setTextColor(0xFFFF5252);
            }

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onItemClick(item);
                }
            });
        }
    }

    public interface OnItemClickListener {
        void onItemClick(SettingsRow item);
    }

    @NonNull
    @Override
    public SettingsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.settings_list_row, viewGroup, false);

        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull SettingsAdapter.ViewHolder viewHolder, int i) {
        viewHolder.bind(items.get(i), listener);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}
