package com.example.flixster;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.flixster.models.Movie;

import org.parceler.Parcels;

import jp.wasabeef.glide.transformations.RoundedCornersTransformation;

public class MovieDetailsActivity extends AppCompatActivity {

    // the movie to display
    Movie movie;

    TextView tvTitleDetails;
    TextView tvOverviewDetails;
    RatingBar rbVoteAverage;
    ImageView ivBackdropDetails;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);

        tvTitleDetails = findViewById(R.id.tvTitleDetails);
        tvOverviewDetails = findViewById(R.id.tvOverviewDetails);
        rbVoteAverage = findViewById(R.id.rbVoteAverage);
        ivBackdropDetails = findViewById(R.id.ivBackdropDetails);



        // unwrap the movie passed in via intent, using its simple name as a key
        movie = (Movie) Parcels.unwrap(getIntent().getParcelableExtra(Movie.class.getSimpleName()));
        Log.d("MovieDetailsActivity", String.format("Showing details for '%s'", movie.getTitle()));


        tvTitleDetails.setText(movie.getTitle());
        tvOverviewDetails.setText(movie.getOverview());

        int radius = 10; // corner radius, higher value = more rounded
        int margin = 0;


        Glide.with(this)
                .load(movie.getBackdropPath())
                .centerInside() // scale image to fill the entire ImageView
                .transform(new RoundedCornersTransformation(radius, margin))
                .placeholder(R.drawable.flicks_movie_placeholder)
                .into(ivBackdropDetails);


        // vote average is 0..10, convert to 0..5 by dividing by 2
        float voteAverage = movie.getVoteAverage().floatValue();
        rbVoteAverage.setRating(voteAverage / 2.0f);
    }
}