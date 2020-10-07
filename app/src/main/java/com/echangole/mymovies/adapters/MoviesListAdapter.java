package com.echangole.mymovies.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

import com.bumptech.glide.Glide;
import com.echangole.mymovies.R;
import com.echangole.mymovies.model.Movie;
import com.echangole.mymovies.model.MovieList;
import com.echangole.mymovies.utils.Constants;

import java.util.List;

public class MoviesListAdapter extends ArrayAdapter<MovieList>
{
    private List<MovieList> movies;
    private static final String TAG = "MovieListAdapter";
    private Context context;

    private Movie movieInfo;

    public MoviesListAdapter(Context context, List<MovieList> movies) {
        super(context, R.layout.movie_list_item,movies);
        this.movies = movies;
        this.context=context;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View rowView = inflater.inflate(R.layout.movie_list_item, parent, false);
        TextView title = (TextView) rowView.findViewById(R.id.movieTitle);
        TextView genre = (TextView) rowView.findViewById(R.id.movieGenre);
        RatingBar rating = (RatingBar) rowView.findViewById(R.id.movieRating);
        TextView plot = (TextView) rowView.findViewById(R.id.moviePlot);
        ImageView poster = (ImageView) rowView.findViewById(R.id.moviePoster);

        title.setText(movies.get(position).getOriginal_name());
        if (!title.getText().toString().isEmpty()) {
            title.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.transparent));
        }
        plot.setText(movies.get(position).getOverview());
        if (!title.getText().toString().isEmpty()) {
            title.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.transparent));
        }
        genre.setText(movies.get(position).getMedia_type());
        if (!title.getText().toString().isEmpty()) {
            title.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.transparent));
        }
        try {
            rating.setRating(movies.get(position).getVote_average());
        } catch (Exception e) {
            rating.setVisibility(View.INVISIBLE);
            rating.setRating(0);
        }

        if (rating.getRating() != 0) {
            plot.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.transparent));
        }




        Glide.with(context)
                .load(Constants.IMG_URL_500X+movies.get(position).getPoster_path())
                .into(poster);


        return rowView;
    }


}
