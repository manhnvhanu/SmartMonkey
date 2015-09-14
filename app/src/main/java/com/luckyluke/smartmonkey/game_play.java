package com.luckyluke.smartmonkey;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;


/**
 * Class tạo số ngẫu nhiên
 */

public class game_play extends Activity {

    static ArrayList<String> secondList;

    TextView tv1, tv2, tv3, tv4, tv5, tv6, tv7, tv8, tv9;
    ProgressBar progressBar;
    AsyncTask_bar myProgressBar = new AsyncTask_bar();



    //Create random integer array
    protected static ArrayList<String> GenNumber() {

        ArrayList<String> initialList = new ArrayList<>();

        initialList.add("1"); //Add element
        initialList.add("2");
        initialList.add("3");
        initialList.add("4");
        initialList.add("5");
        initialList.add("6");
        initialList.add("7");
        initialList.add("8");
        initialList.add("9");

        Collections.shuffle(initialList); //Random the position

        return initialList;
    }

    public static ArrayList<String> getList() {
        return secondList;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_play);

        secondList = GenNumber();

        MainGamePlay();

        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        progressBar.setProgress(0);

        myProgressBar.execute();

    }


    @Override
    protected void onStop() {
        super.onStop();
        stopAsyncOperation();
    }

    private void stopAsyncOperation(){
        if( myProgressBar != null){
            if( myProgressBar.getStatus().equals( AsyncTask.Status.RUNNING )){
                myProgressBar.cancel( true );
            }
        }
    }

    //Method gán giá trị text cho các button
    private void MainGamePlay() {

        tv1 = (TextView) findViewById(R.id.tv1);
        tv1.setText(secondList.get(0));

        tv2 = (TextView) findViewById(R.id.tv2);
        tv2.setText(secondList.get(1));

        tv3 = (TextView) findViewById(R.id.tv3);
        tv3.setText(secondList.get(2));

        tv4 = (TextView) findViewById(R.id.tv4);
        tv4.setText(secondList.get(3));

        tv5 = (TextView) findViewById(R.id.tv5);
        tv5.setText(secondList.get(4));

        tv6 = (TextView) findViewById(R.id.tv6);
        tv6.setText(secondList.get(5));

        tv7 = (TextView) findViewById(R.id.tv7);
        tv7.setText(secondList.get(6));

        tv8 = (TextView) findViewById(R.id.tv8);
        tv8.setText(secondList.get(7));

        tv9 = (TextView) findViewById(R.id.tv9);
        tv9.setText(secondList.get(8));

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.game_play, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up btn, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {

    }

    private class AsyncTask_bar extends AsyncTask<Void, Integer, Void> {

        int myProgress;

        @Override
        protected void onPostExecute(Void result) {

            //On Completion, start intent user_input.class
            Intent intent = new Intent(game_play.this, user_input.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            finish();
        }

        @Override
        protected void onPreExecute() {
            // TODO Auto-generated method stub
            myProgress = 0;
        }


        @Override
        protected Void doInBackground(Void... params) {

            while (myProgress < 110) {
                myProgress = myProgress + 1;
                publishProgress(myProgress);
                SystemClock.sleep(30);
            }
            return null;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            // TODO Auto-generated method stub
            // Update progressBar
            progressBar.setProgress(values[0]);
        }

    }

}
