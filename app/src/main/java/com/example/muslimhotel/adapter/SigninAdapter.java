package com.example.muslimhotel.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;


import com.example.muslimhotel.ui.LoginFragment;
import com.example.muslimhotel.ui.SignUpFragment;

public class SigninAdapter extends FragmentStatePagerAdapter {

    public SigninAdapter(FragmentManager fm) {
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        if (position == 0){
            return new LoginFragment();
        }else {

            return new SignUpFragment();
        }
    }

    @Override
    public int getCount() {
        return 2;
    }


//    String tabTitles[] = new String[]{"LOGIN", "SIGN UP"};
//    Context context;
//
//    public SigninAdapter(FragmentManager fm, Context context) {
//        this.context = context;
//    }
//
//    @Override
//    public Fragment getItem(int position) {
//        switch (position) {
//            case 0:
//
//                return new LoginFragment();
//
//        }
//
//        return null;
//    }
//
//    @Override
//    public int getCount() {
//        return tabTitles.length;
//    }
//
//    @Override
//    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
//        return false;
//    }
//
//    public CharSequence getPageTitle(int position) {
//        return tabTitles[position];
//    }
//
//    public View getTabView(int position) {
//        View tab = LayoutInflater.from(context).inflate(R.layout.custom_tab, null);
//        TextView tv1 = (TextView) tab.findViewById(R.id.custom_text1);
//        TextView tv2 = (TextView) tab.findViewById(R.id.custom_text2);
//        switch (position){
//            case 0:
//
//        }
//
//        tv2.setText(tabTitles[1]);
//        return  tab;
//    }

}
