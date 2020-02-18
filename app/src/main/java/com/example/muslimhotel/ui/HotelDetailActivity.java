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
    TextView tvKotaHotel;
    TextView tvDeskripsi, tvNamaHotel, tvHarga, tvReviewerHotel, tvScoreHotel, tvTanggalCheck, tvPeopleBedroom;
    ImageView othrpicture;
    int identifier=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hotel_detail);
        othrpicture = (ImageView) findViewById(R.id.otherpctr);
        expandbutton = (Button) findViewById(R.id.expandbutton);
        tvDeskripsi = (TextView) findViewById(R.id.tvDeskripsi);
        tvKotaHotel = (TextView)findViewById(R.id.tvKotaHotel);

        tvNamaHotel = (TextView)findViewById(R.id.tvNamaHotel);
        tvHarga = (TextView)findViewById(R.id.tvHarga);
        tvReviewerHotel = (TextView)findViewById(R.id.tvReviewerHotel);
        tvScoreHotel = (TextView)findViewById(R.id.tvScoreHotel);
        tvTanggalCheck = (TextView)findViewById(R.id.tvTanggalCheck);
        tvPeopleBedroom = (TextView)findViewById(R.id.tvPeopleBedroom);

        tvKotaHotel.setText(Html.fromHtml("<u>Turkey</u>"));
        othrpicture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intenttopict = new Intent(HotelDetailActivity.this,OtherPicturesActivity.class);
                startActivity(intenttopict);
            }
        });
        expandbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (identifier==0) {
                    tvDeskripsi.setMaxLines(100);
                    expandbutton.setBackgroundResource(R.drawable.uarrow);
                    identifier=1;
                }else {
                    tvDeskripsi.setMaxLines(4);
                    expandbutton.setBackgroundResource(R.drawable.darrow);
                    identifier=0;
                }
            }
        });
    }
}
