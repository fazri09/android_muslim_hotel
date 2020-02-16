package com.example.muslimhotel.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.muslimhotel.R;


import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity implements View.OnClickListener{
    private final List<Navigation> listNav = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        listNav.add(new Navigation(R.id.nav_search, R.id.iv_search, R.drawable.searchenabled, R.drawable.search));
        listNav.add(new Navigation(R.id.nav_favorite, R.id.iv_favorite, R.drawable.favoriteenabled, R.drawable.iconawesomeheart));
        listNav.add(new Navigation(R.id.nav_profile, R.id.iv_profile, R.drawable.profileenabled, R.drawable.ic_person_24px));
        for (Navigation i : listNav){
            findViewById(i.container).setOnClickListener(this);
        }

        loadFragmment(new HomeFragment());
        navigationSelection(R.id.nav_search);

    }

    public void loadFragmment(Fragment fragment){
        getSupportFragmentManager().beginTransaction().replace(R.id.container, fragment).commit();
    }

    private void navigationSelection(int navId){
        for (Navigation i : listNav){
            boolean b = navId == i.container;
            ((ImageView)findViewById(i.image)).setImageResource(b ? i.imageSelected : i.imageUnselected);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.nav_search:{
                navigationSelection(R.id.nav_search);
                loadFragmment(new HomeFragment());
            }break;
            case R.id.nav_favorite:{
                navigationSelection(R.id.nav_favorite);
                loadFragmment(new HomeFragment());
            }break;
            case R.id.nav_profile:{
                navigationSelection(R.id.nav_profile);
                loadFragmment(new UserProfileFragment());
            }break;

        }
    }

    private class Navigation{
        public int container;
        public int image;
        public int imageSelected, imageUnselected;

        public Navigation(int container, int image, int imageSelected, int imageUnselected) {
            this.container = container;
            this.image = image;
            this.imageSelected = imageSelected;
            this.imageUnselected = imageUnselected;
        }
    }
}
