package com.example.muslimhotel.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.muslimhotel.R;

public class HotelDetailActivity extends AppCompatActivity {

    Button expandbutton;
    TextView turkeytv;
    TextView expandabletextview;
    ImageView othrpicture;
    int identifier=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hotel_detail);
        othrpicture = findViewById(R.id.otherpctr);
        turkeytv = findViewById(R.id.turkeytv);
        turkeytv.setText(Html.fromHtml("<u>Turkey</u>"));
        othrpicture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intenttopict = new Intent(HotelDetailActivity.this,OtherPicturesActivity.class);
                startActivity(intenttopict);
            }
        });
        expandbutton = findViewById(R.id.expandbutton);
        expandabletextview = findViewById(R.id.expandabletv);
        expandbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (identifier==0) {
                    expandabletextview.setMaxLines(100);
                    expandbutton.setBackgroundResource(R.drawable.uarrow);
                    identifier=1;
                }else {
                    expandabletextview.setMaxLines(4);
                    expandbutton.setBackgroundResource(R.drawable.darrow);
                    identifier=0;
                }
            }
        });
    }
}
