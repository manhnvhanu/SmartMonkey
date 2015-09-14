package com.luckyluke.smartmonkey;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.SystemClock;
import android.preference.PreferenceManager;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;


///**
//	Class nay giup nguoi dung nhap gia tri
//
//	1.Get cac gia tri tu class game_play.java -> cho vao arrylist
//	2. Hien dialog de nguoi dung nhap so lieu -> cho vao arraylist
//			//Buoc nay khong the thuc hien duoc, ket qua arraylist emmpty nen khong so sanh duoc
//	3. So sanh 2 arraylist
//		neu 2 arraylist giong nhau
//			cap nhat diem, tiep tuc choi
//
//		neu khac nhau
//			game tam dung
//
//
//**/

public class user_input extends Activity implements OnFocusChangeListener, OnClickListener {


    public EditText e1;
    public EditText e2;
    public EditText e3;
    public EditText e4;
    public EditText e5;
    public EditText e6;
    public EditText e7;
    public EditText e8;
    public EditText e9;
    int bestScore, CurrentScore;

    ProgressBar progressBar;

    SharedPreferences app_preferences;
    SharedPreferences.Editor editor;

    TextView tvHighScore, tvCurrentScore;
    Button btn1;
    Button btn2;
    Button btn3;
    Button btn4;
    Button btn5;
    Button btn6;
    Button btn7;
    Button btn8;
    Button btn9;
    ImageButton btnReset;

    AsyncTask_bar myProgressBar = new AsyncTask_bar();
    //Store result
    private ArrayList<String> resultList = new ArrayList<>();


    //Method to compare two array
    public static boolean compareArrList(ArrayList<String> x, ArrayList<String> z) {

        return x.toString().contentEquals(z.toString());
    }

    public void submitResult() {

        final MediaPlayer correctSound = MediaPlayer.create(this, R.raw.correct_sound);
        final MediaPlayer wrongSound = MediaPlayer.create(this, R.raw.wrong_sound);

        LayoutInflater inflater = getLayoutInflater();

        View layout = inflater.inflate(R.layout.toast_win_lose_layout,
                (ViewGroup) findViewById(R.id.toast_layout_root));

        TextView text = (TextView) layout.findViewById(R.id.text);

        //Get the result
        String str1 = e1.getText().toString();

        //Add to the arrayList
        resultList.set(0, str1);

        String str2 = e2.getText().toString();
        resultList.set(1, str2);

        String str3 = e3.getText().toString();
        resultList.set(2, str3);

        String str4 = e4.getText().toString();
        resultList.set(3, str4);

        String str5 = e5.getText().toString();
        resultList.set(4, str5);

        String str6 = e6.getText().toString();
        resultList.set(5, str6);

        String str7 = e7.getText().toString();
        resultList.set(6, str7);

        String str8 = e8.getText().toString();
        resultList.set(7, str8);

        String str9 = e9.getText().toString();
        resultList.set(8, str9);

        //Get the ArrayList of random number from previous Activity
        ArrayList<String> checkList = game_play.getList();

        //Compare two array
        final boolean isEquals = compareArrList(checkList, resultList);

        if (isEquals) {


            //Play the correct sound
            correctSound.start();

            //Display toast notification
            text.setText(R.string.correctAnswer);

            Toast toast = new Toast(getApplicationContext());
            toast.setGravity(Gravity.BOTTOM, 0, 150);
            toast.setDuration(Toast.LENGTH_SHORT);
            toast.setView(layout);
            toast.show();


            int temp = CurrentScore + 1;

            editor = app_preferences.edit();
            editor.putInt("CurrentScore", temp);
            editor.apply(); // Very important

            //Update high score
            if (temp > bestScore) {
                editor = app_preferences.edit();
                editor.putInt("HighScore", temp);
                editor.apply(); // Very important
                achievementDisplay(temp);
            }


            //Start new intent
            Intent intent = new Intent(user_input.this, game_play.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            finish();

        } else {

            editor = app_preferences.edit();
            editor.putInt("CurrentScore", 0);
            editor.apply(); // Very important

            wrongSound.start();

            text.setText(R.string.wrongAnswer);

            Toast toast = new Toast(getApplicationContext());
            toast.setGravity(Gravity.BOTTOM, 0, 50);
            toast.setDuration(Toast.LENGTH_LONG);
            toast.setView(layout);
            toast.show();

            //Start new intent
            Intent intent = new Intent(user_input.this, MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            finish();

        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        stopAsyncOperation();
    }

    private void stopAsyncOperation() {
        if (myProgressBar != null) {
            if (myProgressBar.getStatus().equals(AsyncTask.Status.RUNNING)) {
                myProgressBar.cancel(true);
            }
        }
    }



    public void value() {

        //Initalise the arrayList
        resultList.add("1");
        resultList.add("2");
        resultList.add("3");
        resultList.add("4");
        resultList.add("5");
        resultList.add("6");
        resultList.add("7");
        resultList.add("8");
        resultList.add("9");

        //Initialise the Edittext view
        e1 = (EditText) findViewById(R.id.e1);
        e1.setOnFocusChangeListener(this);

        e2 = (EditText) findViewById(R.id.e2);
        e2.setOnFocusChangeListener(this);

        e3 = (EditText) findViewById(R.id.e3);
        e3.setOnFocusChangeListener(this);


        e4 = (EditText) findViewById(R.id.e4);
        e4.setOnFocusChangeListener(this);

        e5 = (EditText) findViewById(R.id.e5);
        e5.setOnFocusChangeListener(this);

        e6 = (EditText) findViewById(R.id.e6);
        e6.setOnFocusChangeListener(this);

        e7 = (EditText) findViewById(R.id.e7);
        e7.setOnFocusChangeListener(this);

        e8 = (EditText) findViewById(R.id.e8);
        e8.setOnFocusChangeListener(this);

        e9 = (EditText) findViewById(R.id.e9);
        e9.setOnFocusChangeListener(this);


        //Initialise the text view
        tvHighScore = (TextView) findViewById(R.id.tvHighScore);
        tvCurrentScore = (TextView) findViewById(R.id.tvCurrentScore);

        // Get the app's shared preferences
        app_preferences = PreferenceManager.getDefaultSharedPreferences(this);

        // Get the value for the run counter
        bestScore = app_preferences.getInt("HighScore", 0);
        CurrentScore = app_preferences.getInt("CurrentScore", 0);

        //Set the score to the screen
        tvHighScore.setText(String.valueOf(bestScore));
        tvCurrentScore.setText(String.valueOf(CurrentScore));

    }


    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(user_input.this);
        builder.setMessage("Come back to home screen?")
                .setPositiveButton(R.string.okString, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                        SharedPreferences prefs = user_input.this.getPreferences(MODE_PRIVATE);
                        editor = prefs.edit();
                        editor.putInt("CurrentScore", 0);
                        editor.apply(); // Very important

                        stopAsyncOperation();

                        Intent intent = new Intent(user_input.this, MainActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);
                        finish();
                    }
                })
                .setNegativeButton(R.string.cancelString, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // User cancelled the dialog
                    }
                });
        // Create the AlertDialog object and return it
        builder.show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.acitivity_user_input);


        //Create progress bar
        progressBar = (ProgressBar) findViewById(R.id.progress_bar);
        progressBar.setProgress(0);
        myProgressBar.execute();
        //Get the user input
        value();

        btnReset = (ImageButton) findViewById(R.id.btnReset);
        btnReset.setOnClickListener(this);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.user_input, menu);
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

    public void achievementDisplay(int score) {

        LayoutInflater inflater = getLayoutInflater();
        View layout = inflater.inflate(R.layout.toast_achievement_layout,
                (ViewGroup) findViewById(R.id.toast_achievment_layout));

        Toast toast = new Toast(getApplicationContext());

        TextView tv1 = (TextView) layout.findViewById(R.id.tv1);
        TextView tv2 = (TextView) layout.findViewById(R.id.tv2);
        ImageView img = (ImageView) layout.findViewById(R.id.symbol);

        switch (score) {
            case 1:
                tv1.setText(R.string.point01);
                tv2.setText("");
                img.setBackgroundResource(R.drawable.easy_as_pie);

                toast.setGravity(Gravity.TOP, 0, 0);
                toast.setDuration(Toast.LENGTH_LONG);
                toast.setView(layout);
                toast.show();

                break;

            case 2:

                tv1.setText(R.string.point05);
                tv2.setText(R.string.point05_explained);
                img.setBackgroundResource(R.drawable.banastic);

                toast.setGravity(Gravity.TOP, 0, 0);
                toast.setDuration(Toast.LENGTH_LONG);
                toast.setView(layout);
                toast.show();

                break;
            case 3:
                tv1.setText(R.string.point10);
                tv2.setText("");
                img.setBackgroundResource(R.drawable.five_th_grader);

                toast.setGravity(Gravity.TOP, 0, 0);
                toast.setDuration(Toast.LENGTH_LONG);
                toast.setView(layout);
                toast.show();

                break;
            case 4:

                tv1.setText(R.string.point20);
                tv2.setText("");
                img.setBackgroundResource(R.drawable.monkey_star);

                toast.setGravity(Gravity.TOP, 0, 0);
                toast.setDuration(Toast.LENGTH_LONG);
                toast.setView(layout);
                toast.show();

                break;
            case 5:

                tv1.setText(R.string.point30);
                tv2.setText("");
                img.setBackgroundResource(R.drawable.fast_n_furious);

                toast.setGravity(Gravity.TOP, 0, 0);
                toast.setDuration(Toast.LENGTH_LONG);
                toast.setView(layout);
                toast.show();

                break;


            case 6:

                tv1.setText(R.string.point50);
                tv2.setText(R.string.point50_explained);
                img.setBackgroundResource(R.drawable.chimpion);

                toast.setGravity(Gravity.TOP, 0, 0);
                toast.setDuration(Toast.LENGTH_LONG);
                toast.setView(layout);
                toast.show();

                break;

            case 7:

                tv1.setText(R.string.point100);
                tv2.setText("Amazing Brain!");
                img.setBackgroundResource(R.drawable.unstopable);

                toast.setGravity(Gravity.TOP, 0, 0);
                toast.setDuration(Toast.LENGTH_LONG);
                toast.setView(layout);
                toast.show();

                break;
        }
    }

    public void clear() {
        e1 = (EditText) findViewById(R.id.e1);
        e1.setText("");

        e2 = (EditText) findViewById(R.id.e2);
        e2.setText("");

        e3 = (EditText) findViewById(R.id.e3);
        e3.setText("");


        e4 = (EditText) findViewById(R.id.e4);
        e4.setText("");

        e5 = (EditText) findViewById(R.id.e5);
        e5.setText("");

        e6 = (EditText) findViewById(R.id.e6);
        e6.setText("");

        e7 = (EditText) findViewById(R.id.e7);
        e7.setText("");

        e8 = (EditText) findViewById(R.id.e8);
        e8.setText("");

        e9 = (EditText) findViewById(R.id.e9);
        e9.setText("");
    }

    @Override
    public void onFocusChange(View v, boolean hasFocus) {

        final EditText edt = (EditText) findViewById(v.getId());

        if (hasFocus) {

            //very important, if not, calling the soft keyboard
            InputMethodManager im = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            im.hideSoftInputFromWindow(edt.getWindowToken(), 0);

            //Initialise custom dialog
            final Dialog dialog = new Dialog(user_input.this, R.style.cust_dialog);
            dialog.setContentView(R.layout.input_dialog);
            dialog.setTitle("Select a number: ");
            dialog.show();

            btn1 = (Button) dialog.findViewById(R.id.val1);
            btn1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    edt.setText("1");
                    dialog.cancel();
                }
            });


            btn2 = (Button) dialog.findViewById(R.id.val2);
            btn2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    edt.setText("2");
                    dialog.cancel();
                }
            });
            btn3 = (Button) dialog.findViewById(R.id.val3);
            btn3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    edt.setText("3");
                    dialog.cancel();
                }
            });

            btn4 = (Button) dialog.findViewById(R.id.val4);
            btn4.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    edt.setText("4");
                    dialog.cancel();
                }
            });

            btn5 = (Button) dialog.findViewById(R.id.val5);
            btn5.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    edt.setText("5");
                    dialog.cancel();
                }
            });

            btn6 = (Button) dialog.findViewById(R.id.val6);
            btn6.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    edt.setText("6");
                    dialog.cancel();
                }
            });

            btn7 = (Button) dialog.findViewById(R.id.val7);
            btn7.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    edt.setText("7");
                    dialog.cancel();
                }
            });

            btn8 = (Button) dialog.findViewById(R.id.val8);
            btn8.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    edt.setText("8");
                    dialog.cancel();
                }
            });

            btn9 = (Button) dialog.findViewById(R.id.val9);
            btn9.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    edt.setText("9");
                    dialog.cancel();
                }
            });


        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnReset:
                clear();
                break;
        }
    }

    private class AsyncTask_bar extends AsyncTask<Void, Integer, Void> {

        int myProgress;

        @Override
        protected void onPostExecute(Void result) {

            submitResult();
        }

        @Override
        protected void onPreExecute() {
            // TODO Auto-generated method stub

            myProgress = 0;
        }


        @Override
        protected Void doInBackground(Void... params) {
            // TODO Auto-generated method stub
            while (myProgress < 350) {
                myProgress = myProgress + 1;
                publishProgress(myProgress);
                SystemClock.sleep(30);
            }
            return null;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            // TODO Auto-generated method stub
            progressBar.setProgress(values[0]);
        }

    }
}


