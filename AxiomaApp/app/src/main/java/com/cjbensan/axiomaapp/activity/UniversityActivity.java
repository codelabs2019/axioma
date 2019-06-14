package com.cjbensan.axiomaapp.activity;


import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;

import com.cjbensan.axiomaapp.R;
import com.cjbensan.axiomaapp.adapter.CardPagerAdapter;
import com.cjbensan.axiomaapp.domain.University;
import com.cjbensan.axiomaapp.fragment.CardFragment;

public class UniversityActivity extends AppCompatActivity {

    public static final int[] thumbnails = {
            R.drawable.uni,
            R.drawable.unmsm,
            R.drawable.unac,
            R.drawable.unfv,
            R.drawable.unalm,
            R.drawable.unt,
            R.drawable.unp,
            R.drawable.unsa,
            R.drawable.uncp,
            R.drawable.unsaac,
            R.drawable.unap,
            R.drawable.pucp
    };

    public static final String[] codes = {
            "uni",
            "unmsm",
            "unac",
            "unfv",
            "unalm",
            "unt",
            "unp",
            "unsa",
            "uncp",
            "unsaac",
            "unap",
            "pucp"
    };

    public static final String[] names = {
            "UNIVERSIDAD NACIONAL DE INGENIERIA",
            "UNIVERSIDAD NACIONAL MAYOR DE SAN MARCOS",
            "UNIVERSIDAD NACIONAL DEL CALLAO",
            "UNIVERSIDAD NACIONAL FEDERICO VILLARREAL",
            "UNIVERSIDAD NACIONAL AGRARIA LA MOLINA",
            "UNIVERSIDAD NACIONAL DE TRUJILLO",
            "UNIVERSIDAD NACIONAL DE PIURA",
            "UNIVERSIDAD NACIONAL DE SAN AGUSTIN",
            "UNIVERSIDAD NACIONAL DEL CENTRO DEL PERU",
            "UNIVERSIDAD NACIONAL DE SAN ANTONIO ABAD",
            "UNIVERSIDAD NACIONAL DEL ALTIPLANO",
            "PONTIFICIA UNIVERSIDAD CATOLICA DEL PERU"

    };

    private String[] dates = {
            "05-07-09/08/2019",
            "14-15/09/2019",
            "27/07/2019",
            "07/04/2019",
            "04/08/2019",
            "20/08/2019",
            "27/08/2019",
            "04/08/2019",
            "12/10/2019",
            "17/04/2019",
            "21/07/2019",
            "14/02/2019"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_university);

        final CardPagerAdapter adapter = new CardPagerAdapter(getSupportFragmentManager());

        for (int i = 0; i < thumbnails.length; i++) {
            adapter.add(CardFragment.getInstance(
                    new University(thumbnails[i], codes[i], names[i], dates[i])
            ));
        }

        ViewPager viewPager = (ViewPager) findViewById(R.id.view_pager);
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            private float lastOffset;

            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                int currentPosition;
                int nextPosition;
                float offset;
                boolean goingLeft = lastOffset > positionOffset;

                if (goingLeft) {
                    currentPosition = position + 1;
                    nextPosition = position;
                    offset = 1 - positionOffset;
                }
                else {
                    currentPosition = position;
                    nextPosition = position + 1;
                    offset = positionOffset;
                }

                if (nextPosition > adapter.getCount() - 1 || currentPosition > adapter.getCount() - 1) {
                    return;
                }

                CardView currentCard = adapter.getCardView(currentPosition);
                if (currentCard != null) {
                    currentCard.setScaleX((float) (1 + 0.2 * (1 - offset)));
                    currentCard.setScaleY((float) (1 + 0.2 * (1 - offset)));
                    currentCard.setCardElevation(CardFragment.BASE_ELEVATION + 16.0f * (1 - offset));
                }

                CardView nextCard = adapter.getCardView(nextPosition);
                if (nextCard != null) {
                    nextCard.setScaleX((float) (1 + 0.2 * offset));
                    nextCard.setScaleY((float) (1 + 0.2 * offset));
                    nextCard.setCardElevation(CardFragment.BASE_ELEVATION + 16.0f * offset);
                }

                lastOffset = positionOffset;
            }

            @Override
            public void onPageSelected(int i) {

            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
    }
}
