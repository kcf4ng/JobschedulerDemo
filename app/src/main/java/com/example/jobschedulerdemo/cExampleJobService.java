package com.example.jobschedulerdemo;

import android.app.job.JobParameters;
import android.app.job.JobService;
import android.util.Log;

public class cExampleJobService extends JobService {
    private static final String TAG = "Test";
    private boolean jobCancelled = false;


    private void doBackgroundWork(final JobParameters params) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i = 0; i<100; i++){

                    Log.d(TAG,"run: "+i);

                    if(jobCancelled){
                        return;
                    }

                    try {
                        Thread.sleep(1000)  ;
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }

                Log.d(TAG,"Job Finished");
                jobFinished(params, false);
            }
        }).start();
    }

    @Override
    public boolean onStartJob(JobParameters params) {
        Log.d(TAG,"Job Start");
        doBackgroundWork(params);
        return true;
    }

    @Override
    public boolean onStopJob(JobParameters params) {
        Log.d(TAG,"Job Canceld Before Completion");
        jobCancelled = true;
        return true;

    }
}
