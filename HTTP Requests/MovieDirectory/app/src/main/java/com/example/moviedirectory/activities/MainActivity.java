package com.example.moviedirectory.activities;

import android.content.DialogInterface;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.moviedirectory.R;
import com.example.moviedirectory.data.MoviesHandler;
import com.example.moviedirectory.models.Movie;
import com.example.moviedirectory.ui.MovieAdapter;
import com.example.moviedirectory.utils.Prefs;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView movieRecyclerView;
    private MovieAdapter movieAdapter;
    private MoviesHandler moviesHandler;
    private List<Movie> movies;

    private FloatingActionButton searchFab;
    private Prefs prefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        moviesHandler = new MoviesHandler(this);
        moviesHandler.populateMovies();

        movies = moviesHandler.getMovies();

        Log.i("MActivity", "onCreate: " + movies.size());

        prefs = new Prefs(this);

        movieRecyclerView = findViewById(R.id.movieListId);
        searchFab = findViewById(R.id.searchButtonId);

        searchFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog();
            }
        });

        movieAdapter = new MovieAdapter(this, movies);

        movieRecyclerView.setHasFixedSize(true);
        movieRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        movieRecyclerView.setAdapter(movieAdapter);
        movieAdapter.notifyDataSetChanged();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_main, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        switch (id) {
            case R.id.search: {
                showDialog();
            }
            break;
            default:
                break;
        }

        return true;
    }

    private void showDialog() {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);

        View search_view = LayoutInflater.from(this).inflate(R.layout.search_dialog, null);

        final EditText search_edit_text = search_view.findViewById(R.id.dialog_search_edit_text_id);

        dialogBuilder.setView(search_view)
                .setPositiveButton("Search", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (!search_edit_text.getText().toString().isEmpty()) {
                            String search_query = search_edit_text.getText().toString();
                            prefs.setSearch(search_query);

                            movies.clear();

                            moviesHandler.populateMovies();
                            movies = moviesHandler.getMovies();
                            movieAdapter.notifyDataSetChanged();
                        }
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(MainActivity.this, "Okay!", Toast.LENGTH_SHORT).show();
                    }
                });

        AlertDialog dialog = dialogBuilder.create();
        dialog.show();
    }
}