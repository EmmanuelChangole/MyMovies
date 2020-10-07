package com.echangole.mymovies;

import android.os.Bundle;

import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.bumptech.glide.Glide;
import com.echangole.mymovies.model.Movie;
import com.echangole.mymovies.utils.Constants;
import com.echangole.mymovies.viewmodel.MovieListViewModel;

public class MovieDetailActivity extends AppCompatActivity {

    private Movie movieInfo;
    private MovieListViewModel movieListViewModel;
    private static final String TAG = "MovieDetailActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);

        Bundle extras = getIntent().getExtras();

        if (extras == null) {
            return;
        }

        String id = extras.getString("id");

        //getMovieInfo(id);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(extras.getString("title"));
        toolbar.setNavigationIcon(R.drawable.ic_action_arrow_back);

        setSupportActionBar(toolbar);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        movieListViewModel= new ViewModelProvider(this).get(MovieListViewModel.class);
        subscribe();
        getMovie(id);


    }

    private void getMovie(String id)
    {
        movieListViewModel.getMovie(id);
    }

    private void setView(Movie movie)
    {
        movieInfo=movie;
        if(movieInfo!=null) {


            ImageView movieDetailBG = (ImageView) findViewById(R.id.movieDetailBG);
            ImageView movieDetailPoster = (ImageView) findViewById(R.id.movieDetailPoster);


            TextView movieDetailTitle = (TextView) findViewById(R.id.movieDetailTitle);
            movieDetailTitle.setText(movieInfo.getTitle());
            movieDetailTitle.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.transparent));
            Glide.with(this)
                    .load(Constants.IMG_URL_500X+movieInfo.getPoster_path())
                    .into(movieDetailPoster);
            Glide.with(this)
                    .load(Constants.IMG_URL_ORIGINAL+movieInfo.getPoster_path())
                    .into(movieDetailBG);



            /*new DownloadImage(movieDetailBG).execute(Constants.IMG_URL_ORIGINAL + movieInfo.getPoster_path());
            new DownloadImage(movieDetailPoster).execute(Constants.IMG_URL_500X + movieInfo.getPoster_path());*/

            TextView movieDetailMetaInfo = (TextView) findViewById(R.id.movieDetailMetaInfo);
            movieDetailMetaInfo.setText(movieInfo.getGenres().get(0).getName() + " | " + movieInfo.getRuntime());
            movieDetailMetaInfo.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.transparent));

            TextView movieDetailGenre = (TextView) findViewById(R.id.movieDetailGenre);
            movieDetailGenre.setText(movieInfo.getGenres().get(0).getName());
            movieDetailGenre.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.transparent));

            RatingBar rating = (RatingBar) findViewById(R.id.movieDetailRating);
            try {
                rating.setRating(movieInfo.getVote_average());
            } catch (Exception e) {
                rating.setVisibility(View.INVISIBLE);
                rating.setRating(0);
            }

            TextView movieDetailRatingText = (TextView) findViewById(R.id.movieDetailRatingText);
            movieDetailRatingText.setText(movieInfo.getVote_average() + "/10");

            TextView tvDetails = (TextView) findViewById(R.id.tvDetails);
            tvDetails.setText(movieInfo.getOverview());
        }
    }

    private void subscribe()
    {


        movieListViewModel.getmMovie().observe(this, new Observer<Movie>() {

            @Override
            public void onChanged(Movie movie)
            {
              movieInfo=movie;
               setView(movie);

            }
        });
    }

  /*  private void getMovieInfo(String id) {
        APIClient apiClient = new APIClient();
        Retrofit retrofit = apiClient.getClient(getApplicationContext());

        APIInterface client = retrofit.create(APIInterface.class);

        Call<Movie> call = client.getMovie(id,Constants.API_KEY);
        Toast.makeText(this, id, Toast.LENGTH_SHORT).show();

        call.enqueue(new Callback<Movie>() {
            @Override
            public void onResponse(Call<Movie> call, Response<Movie> response) {
                movieInfo = response.body();

                ImageView movieDetailBG = (ImageView) findViewById(R.id.movieDetailBG);
                ImageView movieDetailPoster = (ImageView) findViewById(R.id.movieDetailPoster);

                if(response.code()==200)
                {
                     new DownloadImage(movieDetailBG).execute(Constants.IMG_URL_ORIGINAL+movieInfo.getPoster_path());
                    new DownloadImage(movieDetailPoster).execute(Constants.IMG_URL_500X+movieInfo.getPoster_path());

                    TextView movieDetailTitle = (TextView) findViewById(R.id.movieDetailTitle);
                    movieDetailTitle.setText(movieInfo.getTitle());
                    movieDetailTitle.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.transparent));

                    TextView movieDetailMetaInfo = (TextView) findViewById(R.id.movieDetailMetaInfo);
                    movieDetailMetaInfo.setText(movieInfo.getGenres().get(0).getName()+ " | " + movieInfo.getRuntime());
                    movieDetailMetaInfo.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.transparent));

                    TextView movieDetailGenre = (TextView) findViewById(R.id.movieDetailGenre);
                    movieDetailGenre.setText(movieInfo.getGenres().get(0).getName());
                    movieDetailGenre.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.transparent));

                    RatingBar rating = (RatingBar) findViewById(R.id.movieDetailRating);
                    try {
                        rating.setRating(movieInfo.getVote_average());
                    } catch (Exception e) {
                        rating.setVisibility(View.INVISIBLE);
                        rating.setRating(0);
                    }

                    TextView movieDetailRatingText = (TextView) findViewById(R.id.movieDetailRatingText);
                    movieDetailRatingText.setText(movieInfo.getVote_average() + "/10");

                    TextView tvDetails = (TextView) findViewById(R.id.tvDetails);
                    tvDetails.setText(movieInfo.getOverview());


                }


            }

            @Override
            public void onFailure(Call<Movie> call, Throwable t) {
                Toast.makeText(getApplicationContext(), getApplicationContext().getString(R.string.api_movie_fail), Toast.LENGTH_LONG).show();
            }
        });

    }*/

    private String getHighResPoster(String url) {
        return url.replace("w500", "original");

    }
}
