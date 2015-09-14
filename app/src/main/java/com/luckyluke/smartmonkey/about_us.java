package com.luckyluke.smartmonkey;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;


public class about_us extends Activity implements OnClickListener {

    ImageButton btnFb, btnGplus, fbManh, fbTu, fbDiep, fbHung, fbTA, fbMinh;

    //Link to facebook and goole plus
    String FBManh = "https://www.facebook.com/KingAmongThePig";
    String gPlusManh = "https://plus.google.com/117517996201729598513/posts";

    String urlTu = "https://www.facebook.com/tutq26";
    String urlDiep = "https://www.facebook.com/lun.chan.129";
    String urlHung = "https://www.facebook.com/viethung28";
    String urlTA = "https://www.facebook.com/poe.cun";
    String urlMinh = "https://www.facebook.com/markenzdannis";

    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);

        btnFb = (ImageButton) findViewById(R.id.btnFb);
        btnGplus = (ImageButton) findViewById(R.id.btnGplus);
        fbManh = (ImageButton) findViewById(R.id.fbManh);
        fbTu = (ImageButton) findViewById(R.id.btnPie);
        fbDiep = (ImageButton) findViewById(R.id.btnBanana);
        fbHung = (ImageButton) findViewById(R.id.btnFive);
        fbTA = (ImageButton) findViewById(R.id.btnStar);
        fbMinh = (ImageButton) findViewById(R.id.btnFast);

        btnFb.setOnClickListener(this);
        btnGplus.setOnClickListener(this);

        fbManh.setOnClickListener(this);
        fbTu.setOnClickListener(this);
        fbDiep.setOnClickListener(this);
        fbHung.setOnClickListener(this);
        fbTA.setOnClickListener(this);
        fbMinh.setOnClickListener(this);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_about_us, menu);
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

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(about_us.this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        finish();
    }

    @Override
    public void onClick(View v) {

        switch(v.getId()) {
            case R.id.btnFb:
                try {
                    intent = new Intent(Intent.ACTION_VIEW, Uri.parse(FBManh));

                    startActivity(intent);
                } catch (Exception e) {
                    Log.e("Exception Caught", e.toString());
                }
                break;
            case R.id.btnGplus:
                try {
                    intent = new Intent(Intent.ACTION_VIEW, Uri.parse(gPlusManh));

                    startActivity(intent);
                } catch (Exception e) {
                    Log.e("Exception Caught", e.toString());
                }
                break;

            case R.id.fbManh:
                try {
                    intent = new Intent(Intent.ACTION_VIEW, Uri.parse(FBManh));

                    startActivity(intent);
                } catch (Exception e) {
                    Log.e("Exception Caught", e.toString());
                }
                break;

            case R.id.btnPie:
                try {
                    intent = new Intent(Intent.ACTION_VIEW, Uri.parse(urlTu));

                    startActivity(intent);
                } catch (Exception e) {
                    Log.e("Exception Caught", e.toString());
                }
                break;

            case R.id.btnBanana:
                try {
                    intent = new Intent(Intent.ACTION_VIEW, Uri.parse(urlDiep));

                    startActivity(intent);
                } catch (Exception e) {
                    Log.e("Exception Caught", e.toString());
                }
                break;

            case R.id.btnFive:
                try {
                    intent = new Intent(Intent.ACTION_VIEW, Uri.parse(urlHung));

                    startActivity(intent);
                } catch (Exception e) {
                    Log.e("Exception Caught", e.toString());
                }
                break;

            case R.id.btnStar:
                try {
                    intent = new Intent(Intent.ACTION_VIEW, Uri.parse(urlTA));

                    startActivity(intent);
                } catch (Exception e) {
                    Log.e("Exception Caught", e.toString());
                }
                break;

            case R.id.btnFast:
                try {
                    intent = new Intent(Intent.ACTION_VIEW, Uri.parse(urlMinh));

                    startActivity(intent);
                } catch (Exception e) {
                    Log.e("Exception Caught", e.toString());
                }
                break;

        }
    }
}
