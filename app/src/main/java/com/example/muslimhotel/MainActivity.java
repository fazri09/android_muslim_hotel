package com.example.muslimhotel;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.ViewFlipper;

import com.example.muslimhotel.ui.HomeActivity;
import com.tomer.fadingtextview.FadingTextView;

public class MainActivity extends AppCompatActivity {

    private ViewFlipper vliper_image;
    private TextView skip;
    private FadingTextView faddingTextView;
    private RelativeLayout relativeLogin;
    @SuppressLint("WrongConstant")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        int images[] = {R.drawable.background, R.drawable.fb, R.drawable.google};

        vliper_image = (ViewFlipper) findViewById(R.id.vliper_image);
        faddingTextView = (FadingTextView) findViewById(R.id.faddingTextView);
        relativeLogin = (RelativeLayout) findViewById(R.id.relativeLogin);

        relativeLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, HomeActivity.class);
                startActivity(intent);
                finish();
            }
        });
        skip = (TextView) findViewById(R.id.skip);

        faddingTextView.setTimeout(FadingTextView.SECONDS,2);

        for (int i = 0; i < images.length; i++){
            flipperImages(images[i]);
        }

        String underl = "<u>SKIP FOR NOW</u>";
        skip.setText(Html.fromHtml(underl));

    }

    private void flipperImages(int image) {

        ImageView imageView = new ImageView(MainActivity.this);
        imageView.setBackgroundResource(image);

        vliper_image.addView(imageView);
        vliper_image.setFlipInterval(2500);
        vliper_image.setAutoStart(true);

        vliper_image.setOutAnimation(MainActivity.this, android.R.anim.slide_out_right);
        vliper_image.setInAnimation(MainActivity.this, android.R.anim.slide_in_left);

    }
}
