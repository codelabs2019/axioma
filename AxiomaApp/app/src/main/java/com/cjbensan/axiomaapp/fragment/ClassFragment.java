package com.cjbensan.axiomaapp.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.cjbensan.axiomaapp.R;
import com.cjbensan.axiomaapp.util.YouTubeConfig;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerSupportFragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class ClassFragment extends Fragment {


    public ClassFragment() {
        // Required empty public constructor
    }

    public static ClassFragment getInstance(String className, String videoId) {
        Bundle args = new Bundle();
        args.putString("className", className);
        args.putString("videoId", videoId);

        ClassFragment classFragment = new ClassFragment();
        classFragment.setArguments(args);

        return classFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_class, container, false);

        YouTubePlayerSupportFragment youTubePlayerFragment = YouTubePlayerSupportFragment.newInstance();
        youTubePlayerFragment.initialize(YouTubeConfig.getApiKey(), new YouTubePlayer.OnInitializedListener() {
            @Override
            public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
                if (!b) {
                    youTubePlayer.setPlayerStyle(YouTubePlayer.PlayerStyle.DEFAULT);
                    youTubePlayer.cueVideo(getArguments().getString("videoId"));
                    youTubePlayer.addFullscreenControlFlag(YouTubePlayer.FULLSCREEN_FLAG_ALWAYS_FULLSCREEN_IN_LANDSCAPE );
                }
            }

            @Override
            public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {
                Log.e("TAG", "Youtube Player View initialization failed");
            }
        });

        getChildFragmentManager().beginTransaction().add(R.id.youtube_player_container, youTubePlayerFragment).commit();

        TextView classNameTxt = (TextView) view.findViewById(R.id.txt_class_name);
        classNameTxt.setText(getArguments().getString("className"));

        return view;
    }

}
