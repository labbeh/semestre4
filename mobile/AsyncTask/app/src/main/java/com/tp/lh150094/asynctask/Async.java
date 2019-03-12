package com.tp.lh150094.asynctask;

import android.os.AsyncTask;
import android.widget.TextView;

/**
 * Created by lh150094 on 12/03/19.
 */

public class Async extends AsyncTask<Integer, Integer, Integer> {
    private TextView tv;

    public Async(TextView tv){
        this.tv = tv;
    }

    @Override
    protected void onPreExecute(){
        super.onPreExecute();
        tv.setText("Progression: 0%");

    }


    public void onProgressUpdate(Integer... progress){
        super.onProgressUpdate(progress);
        tv.setText("Progression: "+ progress[0].intValue() +"% ...");
    }

   /* public void onPostExecute(Integer... result){
        super.onPostExecute(result);
    }*/


    @Override
    protected Integer doInBackground(Integer... integers) {
        return null;
    }
}
