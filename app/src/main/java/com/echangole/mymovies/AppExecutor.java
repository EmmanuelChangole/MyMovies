package com.echangole.mymovies;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

public class AppExecutor
{
    private static AppExecutor instance;
    public static AppExecutor getInstance()
    {

        if(instance==null)
        {
            instance=new AppExecutor();

        }
        return instance;
    }

    private final ScheduledExecutorService mNetworkId= Executors.newScheduledThreadPool(3);
    public ScheduledExecutorService networkIO()
    {
        return mNetworkId;
    }

}
