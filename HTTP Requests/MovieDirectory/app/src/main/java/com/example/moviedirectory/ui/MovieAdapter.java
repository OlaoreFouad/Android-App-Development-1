package com.example.moviedirectory.ui;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.moviedirectory.R;
import com.example.moviedirectory.activities.MovieActivity;
import com.example.moviedirectory.models.Movie;
import com.squareup.picasso.Picasso;

import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MyViewHolder> {

    private Context ctx;
    private List<Movie> movies;

    public MovieAdapter(Context ctx, List<Movie> movies) {
        this.ctx = ctx;
        this.movies = movies;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(ctx).inflate(R.layout.movie_item, viewGroup,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        Movie movie = movies.get(i);

//        Populate the views with data!

        Picasso.get()
                .load(movie.getPoster())
                .error(android.R.drawable.ic_btn_speak_now)
                .into(myViewHolder.image);

        myViewHolder.title.setText(movie.getTitle());
        myViewHolder.category.setText("Category: " + movie.getMovieType());
        myViewHolder.releaseYear.setText("Year: " + movie.getYear());
    }

    @Override
    public int getItemCount() {
        return this.movies.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        ImageView image;
        TextView title, category, releaseYear;
        CardView movieCard;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            image = itemView.findViewById(R.id.movieImageId);
            title = itemView.findViewById(R.id.movieTitleId);
            category = itemView.findViewById(R.id.movieCategoryId);
            releaseYear = itemView.findViewById(R.id.movieReleaseYearId);
            movieCard = itemView.findViewById(R.id.movieCardId);

            movieCard.setOnClickListener(this);
            image.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.movieImageId: {
                    //TODO: add modal image logic here!
                }
                break;
                case R.id.movieCardId: {
                    int position = getAdapterPosition();
                    Movie movie = movies.get(position);

                    Intent movieIntent = new Intent(ctx, MovieActivity.class);
                    movieIntent.putExtra("movie", movie);

                    ctx.startActivity(movieIntent);

                }
                break;
            }
         }
    }
}
