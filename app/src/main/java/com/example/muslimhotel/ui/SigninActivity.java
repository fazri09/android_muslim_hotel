package com.example.muslimhotel.ui;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.example.muslimhotel.R;
import com.example.muslimhotel.adapter.SigninAdapter;
import com.google.android.material.tabs.TabLayout;


public class SigninActivity extends AppCompatActivity {
    private ViewPager viewPager;
    private TabLayout tabLayout;
    private TabLayout.Tab login;
    private TabLayout.Tab signUp;
    private SigninAdapter signinAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);
        viewPager = (ViewPager) findViewById(R.id.pager);
        tabLayout = (TabLayout)findViewById(R.id.slide);




        signinAdapter = new SigninAdapter(getSupportFragmentManager(),this);
        viewPager.setAdapter(signinAdapter);


//        if (getIntent().getIntExtra("login",0) == 1){

            tabLayout.setSelectedTabIndicatorColor(getResources().getColor(R.color.white));
            tabLayout.setTabTextColors(getResources().getColor(R.color.blue),getResources().getColor(R.color.white));
            login = tabLayout.newTab();
            signUp = tabLayout.newTab();
            setTextTabLayout();

            tabLayout.addTab(login,0);
            tabLayout.addTab(signUp,1);

//        }else{
//
//            viewPager.setCurrentItem(1);
//
//            tabLayout.setSelectedTabIndicatorColor(getResources().getColor(R.color.blue));
//            tabLayout.setTabTextColors(getResources().getColor(R.color.white),getResources().getColor(R.color.blue));
//            login = tabLayout.newTab();
//            signUp = tabLayout.newTab();
//            setTextTabLayout();
//
//            tabLayout.addTab(login,0);
//            tabLayout.addTab(signUp,1);
//
//        }



        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {

                viewPager.setCurrentItem(tab.getPosition());
                if (tab.getPosition() == 0){
                    tabLayout.setSelectedTabIndicatorColor(getResources().getColor(R.color.white));
                    tabLayout.setTabTextColors(getResources().getColor(R.color.blue),getResources().getColor(R.color.white));

                    // do something
                }
                if (tab.getPosition() == 1){
                    tabLayout.setSelectedTabIndicatorColor(getResources().getColor(R.color.blue));
                    tabLayout.setTabTextColors(getResources().getColor(R.color.white),getResources().getColor(R.color.blue));

                    // do something
                }

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
//
//        if(getIntent().getExtras()==null){
//
//            TabLayout tabLayout = (TabLayout) findViewById(R.id.slide);
//            TabLayout.Tab tab = tabLayout.getTabAt(1);
//            tab.select();
//        }
//        else {
//            TabLayout tabLayout = (TabLayout) findViewById(R.id.slide);
//            TabLayout.Tab tab = tabLayout.getTabAt(0);
//            tab.select();
//        }

    }

    private void setTextTabLayout(){
        signUp.setText("SIGN UP");
        login.setText("LOGIN");

    }
}
