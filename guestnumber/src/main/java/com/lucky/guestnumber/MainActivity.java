package com.lucky.guestnumber;

import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.games.Games;
import com.google.example.games.basegameutils.BaseGameActivity;
import com.google.example.games.basegameutils.BaseGameUtils;

import java.util.Random;


public class MainActivity extends BaseGameActivity implements
        GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener, OnClickListener {

    private Button button0, button1, button2, button3, button4, button5,
            button6, button7, button8, button9, buttonAgain;
    private int number;
    private Random rand;
    private TextView info;
    private int numGuesses = 0;

    private GoogleApiClient mGoogleApiClient;

    private static int RC_SIGN_IN = 9001;

    private boolean mResolvingConnectionFailure = false;
    private boolean mAutoStartSignInflow = true;
    private boolean mSignInClicked = false;

    Button sign_in, sign_out;




    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {
        if (mResolvingConnectionFailure) {
            // already resolving
            return;
        }

        // if the sign-in button was clicked or if auto sign-in is enabled,
        // launch the sign-in flow
        if (mSignInClicked || mAutoStartSignInflow) {
            mAutoStartSignInflow = false;
            mSignInClicked = false;
            mResolvingConnectionFailure = true;

            // Attempt to resolve the connection failure using BaseGameUtils.
            // The R.string.signin_other_error value should reference a generic
            // error string in your strings.xml file, such as "There was
            // an issue with sign-in, please try again later."
            if (!BaseGameUtils.resolveConnectionFailure(this,
                    mGoogleApiClient, connectionResult,
                    RC_SIGN_IN, String.valueOf(R.string.signin_other_error))) {
                mResolvingConnectionFailure = false;
            }
        }

        // Put code here to display the sign-in button
    }

    // Call when the sign-in button is clicked
    private void signInClicked() {
        mSignInClicked = true;
        mGoogleApiClient.connect();
    }

    // Call when the sign-out button is clicked
    private void signOutclicked() {
        mSignInClicked = false;
        Games.signOut(mGoogleApiClient);
    }

    @Override
    public void onConnectionSuspended(int i) {
        // Attempt to reconnect
        mGoogleApiClient.connect();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Create the Google Api Client with access to the Play Games services
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(Games.API).addScope(Games.SCOPE_GAMES)
                        // add other APIs and scopes here as needed
                .build();

        //

        findViewById(R.id.sign_in_button).setOnClickListener((View.OnClickListener) this);
        findViewById(R.id.sign_out_button).setOnClickListener((View.OnClickListener) this);

        button0 = (Button) findViewById(R.id.btn0);
        button1 = (Button) findViewById(R.id.btn1);
        button2 = (Button) findViewById(R.id.btn2);
        button3 = (Button) findViewById(R.id.btn3);
        button4 = (Button) findViewById(R.id.btn4);
        button5 = (Button) findViewById(R.id.btn5);
        button6 = (Button) findViewById(R.id.btn6);
        button7 = (Button) findViewById(R.id.btn7);
        button8 = (Button) findViewById(R.id.btn8);
        button9 = (Button) findViewById(R.id.btn9);
        buttonAgain = (Button) findViewById(R.id.btnAgain);

        info = (TextView) findViewById(R.id.guess_text);

        rand = new Random();
        number = rand.nextInt(10);
    }

    private void disableNumbers() {
        button0.setEnabled(false);
        button0.setTextColor(Color.parseColor("#ff000033"));
        button1.setEnabled(false);
        button1.setTextColor(Color.parseColor("#ff000033"));
        button2.setEnabled(false);
        button2.setTextColor(Color.parseColor("#ff000033"));
        button3.setEnabled(false);
        button3.setTextColor(Color.parseColor("#ff000033"));
        button4.setEnabled(false);
        button4.setTextColor(Color.parseColor("#ff000033"));
        button5.setEnabled(false);
        button5.setTextColor(Color.parseColor("#ff000033"));
        button6.setEnabled(false);
        button6.setTextColor(Color.parseColor("#ff000033"));
        button7.setEnabled(false);
        button7.setTextColor(Color.parseColor("#ff000033"));
        button8.setEnabled(false);
        button8.setTextColor(Color.parseColor("#ff000033"));
        button9.setEnabled(false);
        button9.setTextColor(Color.parseColor("#ff000033"));
        buttonAgain.setEnabled(true);
        buttonAgain.setTextColor(Color.parseColor("#ff000033"));
    }

    private void enableNumbers() {
        button0.setEnabled(true);
        button0.setTextColor(Color.WHITE);
        button1.setEnabled(true);
        button1.setTextColor(Color.WHITE);
        button2.setEnabled(true);
        button2.setTextColor(Color.WHITE);
        button3.setEnabled(true);
        button3.setTextColor(Color.WHITE);
        button4.setEnabled(true);
        button4.setTextColor(Color.WHITE);
        button5.setEnabled(true);
        button5.setTextColor(Color.WHITE);
        button6.setEnabled(true);
        button6.setTextColor(Color.WHITE);
        button7.setEnabled(true);
        button7.setTextColor(Color.WHITE);
        button8.setEnabled(true);
        button8.setTextColor(Color.WHITE);
        button9.setEnabled(true);
        button9.setTextColor(Color.WHITE);
        buttonAgain.setEnabled(false);
        buttonAgain.setTextColor(Color.parseColor("#ffffff00"));
    }

    public void btnPressed(View v) {
        int btn = Integer.parseInt(v.getTag().toString());
        if (btn < 0) {
            //again btn
            numGuesses = 0;
            number = rand.nextInt(10);
            enableNumbers();
            info.setText("Set the number!");
        } else {
            //number button
            numGuesses++;
            if (btn == number) {
                info.setText("Yes! It was " + number);
                if (getApiClient().isConnected()) {
                    Games.Achievements.unlock(getApiClient(),
                            getString(R.string.correct_guess_achievement));
                    Games.Leaderboards.submitScore(getApiClient(),
                            getString(R.string.number_guesses_leaderboard),
                            numGuesses);
                }
                disableNumbers();
            } else if (numGuesses == 5) {
                info.setText("No! It was " + number);
                disableNumbers();
            } else
                info.setText("Try again!");
        }
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.sign_in_button) {
            beginUserInitiatedSignIn();
        } else if (view.getId() == R.id.sign_out_button) {
            signOut();
            findViewById(R.id.sign_in_button).setVisibility(View.VISIBLE);
            findViewById(R.id.sign_out_button).setVisibility(View.GONE);
        } else if (view.getId() == R.id.show_achievements) {
            startActivityForResult(Games.Achievements.getAchievementsIntent(
                    getApiClient()), 1);
        } else if (view.getId() == R.id.show_leaderboard) {
            startActivityForResult(Games.Leaderboards.getLeaderboardIntent(
                            getApiClient(), getString(R.string.number_guesses_leaderboard)),
                    2);
        }
    }

    public void onSignInSucceeded() {
        findViewById(R.id.sign_in_button).setVisibility(View.GONE);
        findViewById(R.id.sign_out_button).setVisibility(View.VISIBLE);
    }

    @Override
    public void onSignInFailed() {
        findViewById(R.id.sign_in_button).setVisibility(View.VISIBLE);
        findViewById(R.id.sign_out_button).setVisibility(View.GONE);
    }

    @Override
    protected void onStart() {
        super.onStart();
        mGoogleApiClient.connect();
    }

    @Override
    protected void onStop() {
        super.onStop();
        mGoogleApiClient.disconnect();
    }

    @Override
    public void onConnected(Bundle connectionHint) {
        // The player is signed in. Hide the sign-in button and allow the
        // player to proceed.
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
