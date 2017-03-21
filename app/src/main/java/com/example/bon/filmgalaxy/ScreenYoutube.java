package com.example.bon.filmgalaxy;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.example.bon.filmgalaxy.app.LinkInternet;
import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

public class ScreenYoutube extends YouTubeBaseActivity {
    YouTubePlayerView youTubePlayerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen_youtube);
        youTubePlayerView = (YouTubePlayerView)findViewById(R.id.Youtube);
        Intent intent = getIntent();
        final String Key = intent.getStringExtra("IdYoutube");

        Toast.makeText(this, Key, Toast.LENGTH_SHORT).show();
        youTubePlayerView.initialize(LinkInternet.Link_Youtube, new YouTubePlayer.OnInitializedListener() {
            @Override
            public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
                youTubePlayer.setFullscreen(true);
                youTubePlayer.loadVideo(Key);
            }

            @Override
            public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {

            }
        });
    }
}
