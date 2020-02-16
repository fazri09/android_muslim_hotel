package com.example.muslimhotel;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.Image;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.ViewFlipper;

import com.example.muslimhotel.ui.HomeActivity;
import com.example.muslimhotel.ui.LoginFragment;
import com.example.muslimhotel.ui.SigninActivity;
import com.tomer.fadingtextview.FadingTextView;

public class MainActivity extends AppCompatActivity {

    private ViewFlipper vliper_image, vliper_loading;
    private TextView skip;
    private FadingTextView faddingTextView;
    private RelativeLayout relativeLogin;
    @SuppressLint("WrongConstant")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(MainActivity.this);

        if (sp.getBoolean(LoginFragment.PREF_IS_LOGIN, false)) {
            startActivity(new Intent(MainActivity.this, HomeActivity.class));
            finish();
            return;
        }

        int images[] = {R.drawable.screen1, R.drawable.screen2, R.drawable.screen3};
        int loadingimage[] = {R.drawable.garisbiru1, R.drawable.garisbiru2, R.drawable.garisbiru3};

        vliper_image = (ViewFlipper) findViewById(R.id.vliper_image);
        vliper_loading = (ViewFlipper) findViewById(R.id.vliper_loading);
        faddingTextView = (FadingTextView) findViewById(R.id.faddingTextView);
        relativeLogin = (RelativeLayout) findViewById(R.id.relativeLogin);

        relativeLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, SigninActivity.class);
                startActivity(intent);
                finish();
            }
        });
        skip = (TextView) findViewById(R.id.skip);

        faddingTextView.setTimeout(FadingTextView.SECONDS,2);

        for (int i = 0; i < images.length; i++){
            flipperImages(images[i]);
        }
//
        for (int a = 0; a < loadingimage.length; a++){
            flipperLoading(loadingimage[a]);
        }

        String underl = "<u>SKIP FOR NOW</u>";
        skip.setText(Html.fromHtml(underl));

    }

    private void flipperLoading(int i) {

        ImageView imageView = new ImageView(MainActivity.this);
        imageView.setBackgroundResource(i);

        vliper_loading.addView(imageView);
        vliper_loading.setFlipInterval(2450);
        vliper_loading.setAutoStart(true);

        vliper_image.setOutAnimation(MainActivity.this, android.R.anim.slide_out_right);
        vliper_image.setInAnimation(MainActivity.this, android.R.anim.slide_in_left);
    }

    private void flipperImages(int image) {

        ImageView imageView = new ImageView(MainActivity.this);
        imageView.setBackgroundResource(image);

        vliper_image.addView(imageView);
        vliper_image.setFlipInterval(2450);
        vliper_image.setAutoStart(true);

        vliper_image.setOutAnimation(MainActivity.this, android.R.anim.slide_out_right);
        vliper_image.setInAnimation(MainActivity.this, android.R.anim.slide_in_left);

    }
}
