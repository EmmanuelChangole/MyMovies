package com.echangole.mymovies;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

import com.echangole.mymovies.adapters.MovieListAdapter;
import com.echangole.mymovies.adapters.MoviesListAdapter;
import com.echangole.mymovies.model.MovieList;
import com.echangole.mymovies.model.Movies;
import com.echangole.mymovies.response.TrendResponse;
import com.echangole.mymovies.utils.TypefaceUtil;
import com.echangole.mymovies.viewmodel.MovieListViewModel;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private SearchView searchView;

    private ListView movieList;

    private final ArrayList<Movies> movieSkeleton = new ArrayList<Movies>();
    private MovieListViewModel movieListViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TypefaceUtil.overrideFont(getApplicationContext(), "SERIF", "fonts/gotham-book.ttf");

        Window w = getWindow();
        w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        movieListViewModel= new ViewModelProvider(this).get(MovieListViewModel.class);
        subscribe();

        searchView=(SearchView)findViewById(R.id.my_search);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s)
            {
                searchMove(s,1);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s)
            {
                searchMove(s,1);
                return false;
            }
        });

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        myToolbar.setTitle(R.string.display_name);

        setSupportActionBar(myToolbar);

        movieList = (ListView) findViewById(R.id.movieListItem);

        for(int i = 0; i < 10; i++) movieSkeleton.add(new Movies());

        final MovieListAdapter movieListAdapter = new MovieListAdapter(this, movieSkeleton);
        movieList.setAdapter(movieListAdapter);
      //  searchMove("hitman", 1);
          getTrending();
        movieList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapter, View v, int position, long arg3) {
                try {
                    Movies movie = (Movies) adapter.getItemAtPosition(position);
                    Log.d(TAG, movie.getId() + ": id");
                    Intent i = new Intent(getApplicationContext(), MovieDetailActivity.class);
                    i.putExtra("id", String.valueOf(movie.getId()));
                    i.putExtra("title", movie.getTitle());
                    startActivity(i);
                } catch (Exception e)
                {
                    MovieList movie = (MovieList) adapter.getItemAtPosition(position);
                    Log.d(TAG, movie.getId() + ": id");
                    Intent i = new Intent(getApplicationContext(), MovieDetailActivity.class);
                    i.putExtra("id", String.valueOf(movie.getId()));
                    i.putExtra("title", movie.getOriginal_name());
                    startActivity(i);

                }


            }
        });

    }

    private void subscribe()
    {
        movieListViewModel.getMovie().observe(this, new Observer<List<Movies>>() {
            @Override
            public void onChanged(List<Movies> movies)
            {
                if(movies!=null)
                {


                    MovieListAdapter adapter=new MovieListAdapter(getApplicationContext(),movies);
                    movieList.setAdapter(adapter);



                    for (Movies movies1:movies)
                    {

                        Log.d(TAG,movies1.getTitle());
                        Toast.makeText(getApplicationContext(),movies1.getTitle(),Toast.LENGTH_LONG).show();
                    }
                }


            }
        });

        movieListViewModel.getTrend().observe(this, new Observer<TrendResponse>() {
            @Override
            public void onChanged(TrendResponse threadingResponse) {
                if(threadingResponse!=null)
                {
                   List<MovieList> movieLists=threadingResponse.getTrend();
                    MoviesListAdapter adapter=new MoviesListAdapter(getApplicationContext(),movieLists);
                    movieList.setAdapter(adapter);
                   Log.d(TAG,movieLists.get(0).toString());


                }

            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        getTrending();

    }

    public void searchMove(String query,int pageNumber)
    {
        if(pageNumber==0)
        {
            pageNumber=1;
        }
        movieListViewModel.searchMovie(query,pageNumber);

    }

    private void getTrending()
    {
        /*
        APIClient apiClient = new APIClient();
        Retrofit retrofit = apiClient.getClient();

        APIInterface client = retrofit.create(APIInterface.class);

          Call<ThreadingResponse>  response=client.getThread(Constants.API_KEY);
          response.enqueue(new Callback<ThreadingResponse>() {
              @Override
              public void onResponse(Call<ThreadingResponse> call, Response<ThreadingResponse> response)
              {
                  if(response.code()==200)
                  {
                      Log.d(TAG,response.body().getThreading().get(0).toString());

                      ArrayList<MovieList> movies = new ArrayList(response.body().getThreading());

                      final MoviesListAdapter movieListAdapter = new MoviesListAdapter(getBaseContext(), movies);

                      movieList.setAdapter(movieListAdapter);


                  }
                  else
                      {
                          Log.d(TAG,"An error occurred");
                      }

              }

              @Override
              public void onFailure(Call<ThreadingResponse> call, Throwable t) {

              }
          });

            */
        movieListViewModel.getTrending();



    }


}