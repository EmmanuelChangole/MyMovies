package com.echangole.mymovies.client;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.echangole.mymovies.AppExecutor;
import com.echangole.mymovies.model.Movie;
import com.echangole.mymovies.model.Movies;
import com.echangole.mymovies.response.SearchResponse;
import com.echangole.mymovies.response.TrendResponse;
import com.echangole.mymovies.utils.Constants;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import retrofit2.Call;
import retrofit2.Response;

import static com.echangole.mymovies.utils.Constants.API_KEY;

public class MovieClient
{
    private static final String TAG = "MovieClient";
    private static  MovieClient instance;
    private MutableLiveData<List<Movies>> mMovies;
    private MutableLiveData<TrendResponse> trendingMovie;
    private RetrieveMoviesRunnable retrieveMoviesRunnable;
    private MutableLiveData<Movie> mMovie;


    public static  MovieClient getInstance()
    {
        if(instance==null)
        {
            instance=new MovieClient();
        }

        return instance;
    }

    private MovieClient ()
    {
        mMovies=new MutableLiveData<>();
        mMovie=new MutableLiveData<>();
        trendingMovie=new MutableLiveData<TrendResponse>();
    }

    public LiveData<List<Movies>> getMovie()
    {
        return mMovies;

    }
    public LiveData<Movie> getmMovies()
    {
        return mMovie;
    }

    public LiveData<TrendResponse> getTrendingMovie() {
        return trendingMovie;
    }

    public void searchMovie(String query, int page_number)
    {

        if(retrieveMoviesRunnable!=null)
            retrieveMoviesRunnable=null;
        retrieveMoviesRunnable=new RetrieveMoviesRunnable("SearchMovie",query,page_number);


        final Future handler = AppExecutor.getInstance().networkIO().submit(retrieveMoviesRunnable);

        AppExecutor.getInstance().networkIO().schedule(new Runnable() {
            @Override
            public void run() {
                Log.d(TAG,"Failed  to search movies");
                //lets the user know its time out
                handler.cancel(true);
            }
        }, Constants.NETWORK_TIMEOUT, TimeUnit.MINUTES);


    }

    public void getMovie(String id)
    {
        if(retrieveMoviesRunnable!=null)
            retrieveMoviesRunnable=null;
        retrieveMoviesRunnable=new RetrieveMoviesRunnable("getMovie",id);
        final Future handler = AppExecutor.getInstance().networkIO().submit(retrieveMoviesRunnable);

        AppExecutor.getInstance().networkIO().schedule(new Runnable() {
            @Override
            public void run() {
                Log.d(TAG,"Failed  to search movies");
                //lets the user know its time out
                handler.cancel(true);
            }
        }, Constants.NETWORK_TIMEOUT, TimeUnit.MINUTES);


    }

    public void  getTrend()
    {
        if(retrieveMoviesRunnable!=null)
            retrieveMoviesRunnable=null;
        retrieveMoviesRunnable=new RetrieveMoviesRunnable("trend");
        final Future handler = AppExecutor.getInstance().networkIO().submit(retrieveMoviesRunnable);

        AppExecutor.getInstance().networkIO().schedule(new Runnable() {
            @Override
            public void run() {
                Log.d(TAG,"Failed  to search movies");
                //lets the user know its time out
                handler.cancel(true);
            }
        }, Constants.NETWORK_TIMEOUT, TimeUnit.MINUTES);

    }
    private class  RetrieveMoviesRunnable implements Runnable
    {
      private String query;
      private int pageNumber;
      private String id;
      boolean cancelRequest;
      private String type;

        public RetrieveMoviesRunnable(String type,String query, int pageNumber) {
            this.query = query;
            this.pageNumber = pageNumber;
            this.type=type;
            cancelRequest=false;
        }
        public RetrieveMoviesRunnable(String type,String id) {
            this.id = id;
            this.type=type;
            cancelRequest=false;
        }

        public RetrieveMoviesRunnable(String type)
        {
            this.type=type;

        }

        @Override
        public void run()
        {
            if(type.matches("SearchMovie"))
            {
                try {
                    Response response=getMovie(query,pageNumber).execute();
                    if(cancelRequest==true)
                    {
                        return;
                    }
                    if(response.code()==200)
                    {
                        Log.d(TAG,"Ok");
                        SearchResponse response1=(SearchResponse)response.body();
                        Log.d(TAG,response1.getSearch().size()+"");

                        ArrayList<Movies> searchResponses=response1.getSearch();
                        if(pageNumber==1)
                        {
                            mMovies.postValue(searchResponses);
                        }
                        else
                        {
                            List<Movies> currentList=response1.getSearch();
                            currentList.addAll(searchResponses);
                            mMovies.postValue(currentList);

                        }


                    }

                    else
                    {
                        String error =response.errorBody().string();
                        Log.e(TAG,error+"Error occurred");
                        mMovies.postValue(null);
                    }


                } catch (IOException e) {
                    e.printStackTrace();
                    mMovies.postValue(null);
                }
            }

            else if(type.matches("getMovie"))
                {
                    try {
                        Response response=getMovie(id).execute();
                        if(cancelRequest==true)
                        {
                            return;
                        }
                        if(response.code()==200)
                        {
                            Log.d(TAG,"Ok");
                           Movie response1=(Movie)response.body();
                            Log.d(TAG,response1.toString());
                            mMovie.postValue(response1);


                        }

                        else
                        {
                            String error =response.errorBody().string();
                            Log.e(TAG,error+"Error occurred");
                            mMovies.postValue(null);
                        }


                    } catch (IOException e) {
                        e.printStackTrace();
                        mMovies.postValue(null);
                    }

                }

            else
                {
                    try {
                        Response response=getMovie().execute();
                        if(cancelRequest==true)
                        {
                            return;
                        }
                        if(response.code()==200)
                        {
                            Log.d(TAG,"Ok");
                            TrendResponse threadingResponse=(TrendResponse)response.body();
                            Log.d(TAG,threadingResponse.toString());
                            trendingMovie.postValue(threadingResponse);


                        }

                        else
                        {
                            String error =response.errorBody().string();
                            Log.e(TAG,error+"Error occurred");
                            mMovies.postValue(null);
                        }


                    } catch (IOException e) {
                        e.printStackTrace();
                        mMovies.postValue(null);
                    }
                }




        }

        private Call<SearchResponse> getMovie(String query,int pageNumber)
        {
            return APIClient.getClient().getMovieList
                    (
                            API_KEY,
                            query,
                            pageNumber

                    );
        }
        private Call<Movie> getMovie(String id) {
            return APIClient.getClient().getMovie
                    (
                            id,
                            API_KEY

                    );

        }

        private Call<TrendResponse> getMovie() {
            return APIClient.getClient().getThread
                    (
                            API_KEY
                    );
        }

        private void cancelRequest()
        {
            Log.d(TAG,"Canceling request");
            cancelRequest=true;
        }
    }

}
