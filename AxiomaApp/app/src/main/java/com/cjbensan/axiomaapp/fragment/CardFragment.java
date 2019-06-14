package com.cjbensan.axiomaapp.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.cjbensan.axiomaapp.R;
import com.cjbensan.axiomaapp.activity.UserSessionActivity;
import com.cjbensan.axiomaapp.domain.University;

public class CardFragment extends Fragment {

    private CardView cardView;
    public static final float BASE_ELEVATION = 6.0f;

    public static CardFragment getInstance(University university) {
        Bundle args = new Bundle();
        args.putInt("thumbnail", university.getThumbnail());
        args.putString("code", university.getCode());
        args.putString("name", university.getName());
        args.putString("date", university.getDate());

        CardFragment cardFragment = new CardFragment();
        cardFragment.setArguments(args);

        return cardFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_card, container, false);

        ImageView thumbnailImg = (ImageView) view.findViewById(R.id.img_thumbnail);
        thumbnailImg.setImageResource(getArguments().getInt("thumbnail"));

        String name = getArguments().getString("name");
        String[] tokens = name.split(" ");
        String name1 = tokens[0] + " " + tokens[1];
        String name2 = name.substring(name1.length() + 1);

        TextView name1Txt = (TextView) view.findViewById(R.id.txt_name_1);
        name1Txt.setText(name1);

        TextView name2Txt = (TextView) view.findViewById(R.id.txt_name_2);
        name2Txt.setText(name2);

        TextView dateTxt = (TextView) view.findViewById(R.id.txt_date);
        dateTxt.setText(getArguments().getString("date"));

        cardView = (CardView) view.findViewById(R.id.card_view);
        cardView.setCardElevation(BASE_ELEVATION);
        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), UserSessionActivity.class);
                intent.putExtra("CODE", getArguments().getString("code"));
                startActivity(intent);
            }
        });

        return view;
    }

    public CardView getCardView() {
        return cardView;
    }

}
