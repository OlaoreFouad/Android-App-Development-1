package com.example.moviedirectory.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.moviedirectory.R;
import com.example.moviedirectory.models.Movie;
import com.squareup.picasso.Picasso;

public class MovieActivity extends AppCompatActivity {

    private ImageView poster;
    private TextView title, category, actors, plot, directors, releaseYear;
    private Bundle extras;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie);

        poster = findViewById(R.id.movieImageDetailId);
        title = findViewById(R.id.movieTitleDetailId);
        category = findViewById(R.id.movieCategoryDetailId);
        actors = findViewById(R.id.movieActorsDetailId);
        plot = findViewById(R.id.moviePlotDetailId);
        directors = findViewById(R.id.movieDirectorDetailId);
        releaseYear = findViewById(R.id.movieReleaseYearDetailId);

        extras = getIntent().getExtras();
        Movie movie = (Movie) extras.getSerializable("movie");

        Picasso.get()
                .load(movie.getPoster())
                .error(android.R.color.holo_blue_dark)
                .into(poster);

        title.setText(movie.getTitle());
        category.setText("Category: " + movie.getMovieType());
        actors.setText("Actors: " + movie.getActors());
        plot.setText(movie.getPlot());
        directors.setText("Directed by: " + movie.getDirector());
        releaseYear.setText(movie.getYear());

    }
}
