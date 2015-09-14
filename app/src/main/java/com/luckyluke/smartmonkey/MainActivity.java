package com.luckyluke.smartmonkey;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Class chính
 */

public class MainActivity extends Activity implements
        View.OnClickListener {


    Button btnStart, btnAchiement, btnShare, btnAbout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        LayoutInflater inflater = getLayoutInflater();
        final View layout = inflater.inflate(R.layout.toast_achievement_layout,
                (ViewGroup) findViewById(R.id.toast_achievment_layout));

        final Toast toast = new Toast(getApplicationContext());

        final TextView tv1 = (TextView) layout.findViewById(R.id.tv1);
        final TextView tv2 = (TextView) layout.findViewById(R.id.tv2);
        final ImageView img = (ImageView) layout.findViewById(R.id.symbol);


        btnStart = (Button) findViewById(R.id.btnStart);
        btnAchiement = (Button) findViewById(R.id.btnAchievement);
        btnShare = (Button) findViewById(R.id.btnShare);
        btnAbout = (Button) findViewById(R.id.btnAboutUs);

        btnStart.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
//                // Perform action on click
//                Intent intent = new Intent(MainActivity.this, game_play.class);
//                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
//                startActivity(intent);
//                finish();

                tv1.setText(R.string.point01);
                tv2.setText("");
                img.setBackgroundResource(R.drawable.easy_as_pie);

                toast.setGravity(Gravity.TOP, 0, 0);
                toast.setDuration(Toast.LENGTH_LONG);
                toast.setView(layout);
                toast.show();
            }
        });

        btnAchiement.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Perform action on click
                Intent intent = new Intent(MainActivity.this, Achievement.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                finish();
            }
        });

        btnAbout.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Perform action on click
                Intent intent = new Intent(MainActivity.this, about_us.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                finish();
            }
        });


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
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setMessage("QUIT GAME?")
                .setPositiveButton(R.string.okString, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        System.exit(0);
                    }
                })
                .setNegativeButton(R.string.cancelString, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                    }
                });
        // Create the AlertDialog object and return it
        builder.show();
    }


    @Override
    public void onClick(View v) {

    }
}
