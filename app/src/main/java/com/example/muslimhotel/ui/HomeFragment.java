package com.example.muslimhotel.ui;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;
import androidx.viewpager.widget.ViewPager;

import android.preference.PreferenceManager;
import android.text.Html;
import android.text.InputType;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.muslimhotel.R;
import com.example.muslimhotel.adapter.AuthorsHotelAdapter;
import com.example.muslimhotel.adapter.EditorsPicksAdapter;
import com.example.muslimhotel.adapter.HotelDiscoverAdapter;
import com.example.muslimhotel.adapter.PromoAdapter;
import com.example.muslimhotel.adapter.VPAdapter;
import com.example.muslimhotel.app.AppController;
import com.example.muslimhotel.model.AuthorsHotel;
import com.example.muslimhotel.model.EditorsPicks;
import com.example.muslimhotel.model.HotelDiscover;
import com.example.muslimhotel.model.Promo;
import com.example.muslimhotel.util.Server;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HomeFragment extends Fragment {
    AlertDialog.Builder dialog;
    LayoutInflater inflater;
    View dialogView;
    TextView tvunderline;
    Button btnOk,searchbuttonhome;
    EditText etintent;

    private ViewPager viewPager;
    private String TAG = "HomeFragment";
    private RecyclerView rLvPromo,rlvPicks,rlvDiscover,rlvAuthors;
    private ArrayList<Promo> listPromo = new ArrayList<>();
    private PromoAdapter adapterPromo;
    private EditorsPicksAdapter adapterEditors;
    private StaggeredGridLayoutManager staggered;
    private ArrayList <EditorsPicks> listPicks = new ArrayList<>();
    private ArrayList <HotelDiscover> lisHotelDiscover = new ArrayList<>();
    private ArrayList<AuthorsHotel> listAuthor = new ArrayList<>();
    private AuthorsHotelAdapter adapterAuthors;
    private HotelDiscoverAdapter adapterDiscover;
    private String URLGetDataEditor = Server.URL_PROD+"/crud/editor";
    private String URLGetDataDiscover = Server.URL_PROD+"/crud/hotel_near";
    private String URLGetDataAuthors = Server.URL_PROD+"/crud/author";
    private String URLUserUpdateLama = Server.URL_PROD+"/crud/ubah_pengguna";

    private RelativeLayout btn_search;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_home, container, false);
        etintent = v.findViewById(R.id.etintent);
        rLvPromo = (RecyclerView)v.findViewById(R.id.rV1);
        rlvPicks = (RecyclerView)v.findViewById(R.id.rV2);
        rlvDiscover = (RecyclerView)v.findViewById(R.id.rV3);
        rlvAuthors= (RecyclerView)v.findViewById(R.id.rV4);
        btn_search = (RelativeLayout)v.findViewById(R.id.btn_search);
        searchbuttonhome = v.findViewById(R.id.searchbuttonhome);

        listPromo.add(new Promo(R.drawable.promo1));
        listPromo.add(new Promo(R.drawable.promo1));
        adapterPromo = new PromoAdapter(listPromo,getActivity());
        rLvPromo.setHasFixedSize(true);
        staggered = new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.HORIZONTAL);
        staggered.setGapStrategy(StaggeredGridLayoutManager.GAP_HANDLING_NONE);
        rLvPromo.setLayoutManager(staggered);
        rLvPromo.setAdapter(adapterPromo);



        adapterEditors = new EditorsPicksAdapter(listPicks,getActivity());
        rlvPicks.setHasFixedSize(true);
        staggered = new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.HORIZONTAL);
        staggered.setGapStrategy(StaggeredGridLayoutManager.GAP_HANDLING_NONE);
        rlvPicks.setLayoutManager(staggered);
        rlvPicks.setAdapter(adapterEditors);


        adapterDiscover= new HotelDiscoverAdapter(lisHotelDiscover,getActivity());
        rlvDiscover.setHasFixedSize(true);
        staggered = new StaggeredGridLayoutManager(1,StaggeredGridLayoutManager.HORIZONTAL);
        staggered.setGapStrategy(StaggeredGridLayoutManager.GAP_HANDLING_NONE);
        rlvDiscover.setLayoutManager(staggered);
        rlvDiscover.setAdapter(adapterDiscover);
        searchbuttonhome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intenttosearch = new Intent(getActivity(),SearchActivity.class);
                intenttosearch.putExtra("hotelnamevalue",etintent.getText().toString());
                startActivity(intenttosearch);
            }
        });




        etintent.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int i, KeyEvent keyEvent) {
                if (i == KeyEvent.KEYCODE_ENTER&&keyEvent.getAction()==KeyEvent.ACTION_DOWN) {
                    Intent intenttosearch = new Intent(getActivity(),SearchActivity.class);
                    intenttosearch.putExtra("hotelnamevalue",etintent.getText().toString());
                    startActivity(intenttosearch);
                    return true;
                }
                return false;
            }
        });

//        etintent.setOnEditorActionListener(new EditText.OnEditorActionListener() {
//            @Override
//            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
//                if (i== EditorInfo.IME_ACTION_DONE){
//                    System.out.println("test");
//                    etintent.performClick();
//                    return true;
//                }else {
//                    return false;
//                }
//            }
//        });

//        btn_search.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intenttosearch = new Intent(getActivity(),SearchActivity.class);
//                intenttosearch.putExtra("hotelnamevalue",etintent.getText().toString());
//                startActivity(intenttosearch);
//            }
//        });
        adapterAuthors= new AuthorsHotelAdapter(listAuthor,getActivity());
        rlvAuthors.setHasFixedSize(true);
        staggered = new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.HORIZONTAL);
        staggered.setGapStrategy(StaggeredGridLayoutManager.GAP_HANDLING_NONE);
        rlvAuthors.setLayoutManager(staggered);
        rlvAuthors.setAdapter(adapterAuthors);
        viewPager = v.findViewById(R.id.viewpager);
        VPAdapter vpAdapter = new VPAdapter(v.getContext());
        viewPager.setAdapter(vpAdapter);


        SharedPreferences spa = PreferenceManager.getDefaultSharedPreferences(getActivity());
        String sess_pengguna = spa.getString(LoginFragment.PREF_PENGGUNA, null);

        Log.d("ceksessionpengguna", "onCreateView: "+sess_pengguna);
        if (sess_pengguna.equalsIgnoreCase("baru")){
            showDialogPertama();
        }
        getDataEditors();
        getDataDiscover();
        getDataAuthors();
        return v;
    }

    private void getDataEditors(){
        StringRequest srDataEditors = new StringRequest(Request.Method.GET, URLGetDataEditor, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray array = new JSONArray(response);
                    for (int i = 0; i< array.length(); i++){
                        JSONObject data = array.getJSONObject(i);

                        EditorsPicks mEditors = new EditorsPicks();
                        mEditors.setGambarHotel(data.getString("img_hotel"));
                        mEditors.setIidHotel(data.getString("id_hotel"));
                        mEditors.setNmHotel(data.getString("nama_hotel"));
                        mEditors.setScorelHotel(data.getString("score_hotel"));
                        mEditors.setReview(data.getString("reviewer_hotel"));
                        mEditors.setTempat(data.getString("kota_hotel"));
                        mEditors.setTempat(data.getString("alamat_hotel"));
                        listPicks.add(mEditors);
                        adapterEditors.notifyDataSetChanged();
                    }

                    Log.d(TAG, "onResponse: "+array);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        AppController.getInstance().addToRequestQueue(srDataEditors);

    }







    private void getDataDiscover(){
        StringRequest srDataEditors = new StringRequest(Request.Method.GET, URLGetDataDiscover, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray array = new JSONArray(response);
                    for (int i = 0; i< array.length(); i++){
                        JSONObject data = array.getJSONObject(i);

                        HotelDiscover mHotelDiscover = new HotelDiscover();
                        mHotelDiscover.setGambarHotel(data.getString("img_hotel"));
                        mHotelDiscover.setNmHotel(data.getString("nama_hotel"));
                        mHotelDiscover.setTempatHotel(data.getString("alamat_hotel"));
                        mHotelDiscover.setDeskripsiHotel(data.getString("kota_hotel"));
                        lisHotelDiscover.add(mHotelDiscover);
                        adapterDiscover.notifyDataSetChanged();
                    }

                    Log.d(TAG, "onResponse: "+array);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        AppController.getInstance().addToRequestQueue(srDataEditors);
    }



    private void getDataAuthors(){
        StringRequest srDataEditors = new StringRequest(Request.Method.GET, URLGetDataAuthors, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray array = new JSONArray(response);
                    for (int i = 0; i< array.length(); i++){
                        JSONObject data = array.getJSONObject(i);

                        AuthorsHotel mAuthors = new AuthorsHotel();
                        mAuthors.setGambarHotel(data.getString("img_hotel"));
                        mAuthors.setNmHotel(data.getString("nama_hotel"));
                        mAuthors.setTempat(data.getString("alamat_hotel"));
                        mAuthors.setScoreHotel(data.getString("score_hotel"));
                        mAuthors.setReview(data.getString("reviewer_hotel"));

                        listAuthor.add(mAuthors);
                        adapterAuthors.notifyDataSetChanged();
                    }

                    Log.d(TAG, "onResponse: "+array);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        AppController.getInstance().addToRequestQueue(srDataEditors);
    }

    private void showDialogPertama() {
        final Dialog dialog = new Dialog(getActivity());
        dialog.setContentView(R.layout.item_dialog_home);
        dialog.setCancelable(false);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));


        tvunderline = dialog.findViewById(R.id.tvunderline);
        String undr = "<u>What is Hotel Halal Features</u>";
        tvunderline.setText(Html.fromHtml(undr));
        btnOk = dialog.findViewById(R.id.btnOk);


        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(getActivity());
                String sess_id = sp.getString(LoginFragment.PREF_ID, null);
                updateUserLama(sess_id);
            }
        });
        dialog.show();
    }

    private void updateUserLama(final String sess_id) {
        StringRequest srUpdateUser = new StringRequest(Request.Method.POST, URLUserUpdateLama, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("responseuser","Update" +response);
                //set session
                SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
                SharedPreferences.Editor editor = preferences.edit();
                editor.putString(LoginFragment.PREF_PENGGUNA, "lama");
                editor.commit();

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("responseuser","error" +error);
                Toast.makeText(getActivity(),"Server Issue", Toast.LENGTH_SHORT).show();
                SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
                SharedPreferences.Editor editor = preferences.edit();
                editor.putString(LoginFragment.PREF_PENGGUNA, "lama");
                editor.commit();
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String, String> prams = new HashMap<>();
                prams.put("id_user", sess_id);
                return prams;
            }
        };

        AppController.getInstance().addToRequestQueue(srUpdateUser);
    }
}
