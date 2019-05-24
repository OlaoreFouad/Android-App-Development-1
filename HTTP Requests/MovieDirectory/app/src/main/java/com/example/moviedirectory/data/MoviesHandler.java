package com.example.moviedirectory.data;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.moviedirectory.models.Movie;
import com.example.moviedirectory.utils.MovieUtil;
import com.example.moviedirectory.utils.Prefs;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MoviesHandler {

    private RequestQueue queue;
    private Context ctx;
    private Prefs prefs;
    private List<Movie> movieList;

    public MoviesHandler(Context context) {
        queue = Volley.newRequestQueue(context);
        this.ctx = context;
        prefs = new Prefs(ctx);
        this.movieList = new ArrayList<>();

        queue = Volley.newRequestQueue(ctx);
    }

    public void populateMovies() {

        movieList.clear();

        final JsonObjectRequest movieObjectRequest = new JsonObjectRequest(Request.Method.GET,
                MovieUtil.URL_LEFT + prefs.getSearch() + MovieUtil.API_KEY, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                try {
                    JSONArray movieArray = response.getJSONArray("Search");


                    for (int i = 0; i < movieArray.length(); i++) {
                        JSONObject movieObject = movieArray.getJSONObject(i);

                        Log.i("MA", "onResponse: " + movieObject.getString("Title"));

                        Movie movie = new Movie();
                        movie.setTitle(movieObject.getString("Title"));
                        movie.setYear(movieObject.getString("Year"));
                        movie.setMovieType(movieObject.getString("Type"));
                        movie.setPoster(movieObject.getString("Poster"));

                        getMoreData(movieObject.getString("imdbID"), movie);

                        movieList.add(movie);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.e("Foodie!!!, " + error.getMessage());
            }
        });

        queue.add(movieObjectRequest);
    }

    public List<Movie> getMovies() {
        return this.movieList;
    }

    public void getMoreData(String imdbID, final Movie movie) {

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, MovieUtil.URL + imdbID
                + MovieUtil.API_KEY + MovieUtil.URL_RIGHT, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    movie.setActors(response.getString("Actors"));
                    movie.setPlot(response.getString("Plot"));
                    movie.setDirector(response.getString("Director"));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.e(error.getMessage());
            }
        });

        queue.add(jsonObjectRequest);
    }

}
