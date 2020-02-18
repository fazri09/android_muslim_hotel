package com.example.muslimhotel.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.example.muslimhotel.R;
import com.example.muslimhotel.adapter.VPPicturesAdapter;

public class OtherPicturesActivity extends AppCompatActivity {

    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_other_pictures);
        viewPager = findViewById(R.id.viewpagerpictures);
        VPPicturesAdapter vpPicturesAdapter = new VPPicturesAdapter(this);
        viewPager.setAdapter(vpPicturesAdapter);
    }
}
