package com.example.muslimhotel.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.muslimhotel.R;
import com.squareup.picasso.Picasso;

public class HotelDetailActivity extends AppCompatActivity {

    Button expandbutton;
    TextView tvKotaHotel;
    TextView tvDeskripsi, tvNamaHotel, tvHarga, tvReviewerHotel, tvScoreHotel, tvTanggalCheck, tvPeopleBedroom;
    ImageView othrpicture, iv_gambar_hotel;
    private String bulan;
    private String i_namahotel, i_kotahotel, i_gambarhotel, i_hargahotel, i_reviewhotel, i_scorehotel, i_deskripsihotel, i_cekin, i_cekout, i_people, i_bedrooms;
    int identifier=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hotel_detail);
        othrpicture = (ImageView) findViewById(R.id.otherpctr);
        expandbutton = (Button) findViewById(R.id.expandbutton);
        tvDeskripsi = (TextView) findViewById(R.id.tvDeskripsi);
        iv_gambar_hotel = (ImageView) findViewById(R.id.iv_gambar_hotel);
        tvKotaHotel = (TextView)findViewById(R.id.tvKotaHotel);

        tvNamaHotel = (TextView)findViewById(R.id.tvNamaHotel);
        tvHarga = (TextView)findViewById(R.id.tvHarga);
        tvReviewerHotel = (TextView)findViewById(R.id.tvReviewerHotel);
        tvScoreHotel = (TextView)findViewById(R.id.tvScoreHotel);
        tvTanggalCheck = (TextView)findViewById(R.id.tvTanggalCheck);
        tvPeopleBedroom = (TextView)findViewById(R.id.tvPeopleBedroom);


        i_namahotel = getIntent().getStringExtra("nmHotel");
        i_kotahotel = getIntent().getStringExtra("kotaHotel");
        i_gambarhotel = getIntent().getStringExtra("gambarHotel");
        i_hargahotel = getIntent().getStringExtra("hgHotel");
        i_reviewhotel = getIntent().getStringExtra("review");
        i_scorehotel = getIntent().getStringExtra("scoreHotel");
        i_deskripsihotel = getIntent().getStringExtra("deskripsi");
        i_cekin = getIntent().getStringExtra("cekIn");
        i_cekout = getIntent().getStringExtra("cekOut");
        i_people = getIntent().getStringExtra("orang");
        i_bedrooms = getIntent().getStringExtra("bedroom");

        tvNamaHotel.setText(i_namahotel);
        tvKotaHotel.setText(Html.fromHtml("<u>"+i_kotahotel+"</u>"));
        tvHarga.setText("IDR "+i_hargahotel);
        tvReviewerHotel.setText("("+i_reviewhotel+")"+" Reviews");

        Log.d("cekinsad", "onCreate: "+i_reviewhotel);
        tvScoreHotel.setText(i_scorehotel);
        tvDeskripsi.setText(i_deskripsihotel);

        tvPeopleBedroom.setText(i_bedrooms+" Bedroom-"+i_people+" People");

        String cekin_total = i_cekin;

        String[] strCekin = cekin_total.split("-");

        String cekinTahun = strCekin[0]; // tahun
        String cekinBulan = strCekin[1]; // bulan
        String cekinTanggal = strCekin[2]; // tanggal


        String cekout_total = i_cekout;

        String[] strCekout = cekout_total.split("-");

        String cekoutTahun = strCekout[0]; // tahun
        String cekoutBulan = strCekout[1]; // bulan
        String cekoutTanggal = strCekout[2]; // tanggal

        if (cekoutBulan.equalsIgnoreCase("01")){
            bulan = "Jan";
        }else if (cekoutBulan.equalsIgnoreCase("02")){
            bulan = "Feb";
        }else if (cekoutBulan.equalsIgnoreCase("03")){
            bulan = "Mar";
        }else if (cekoutBulan.equalsIgnoreCase("04")){
            bulan = "Apr";
        }else if (cekoutBulan.equalsIgnoreCase("05")){
            bulan = "Mei";
        }else if (cekoutBulan.equalsIgnoreCase("06")){
            bulan = "Jun";
        }else if (cekoutBulan.equalsIgnoreCase("07")){
            bulan = "Jul";
        }else if (cekoutBulan.equalsIgnoreCase("08")){
            bulan = "Aug";
        }else if (cekoutBulan.equalsIgnoreCase("09")){
            bulan = "Sep";
        }else if (cekoutBulan.equalsIgnoreCase("10")){
            bulan = "Oct";
        }else if (cekoutBulan.equalsIgnoreCase("11")){
            bulan = "Nov";
        }else if (cekoutBulan.equalsIgnoreCase("12")){
            bulan = "Dec";
        }else{
            bulan = null;
        }

        Log.d("ohyes", "onCreate: "+cekinTahun+"$"+cekinBulan+"$"+cekinTanggal+bulan);

        tvTanggalCheck.setText(cekinTanggal+" - "+cekoutTanggal+" "+bulan+" "+cekoutTahun);

        Picasso.with(HotelDetailActivity.this).load(i_gambarhotel).into(iv_gambar_hotel);

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
