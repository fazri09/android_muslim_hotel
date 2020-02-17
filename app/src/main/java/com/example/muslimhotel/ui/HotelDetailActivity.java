package com.example.muslimhotel.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.muslimhotel.R;

public class HotelDetailActivity extends AppCompatActivity {

    Button expandbutton;
    TextView expandabletextview;
    int identifier=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hotel_detail);
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
