package com.example.muslimhotel.ui;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;
import androidx.viewpager.widget.ViewPager;

import android.text.Html;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

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
import java.util.List;
import java.util.Map;

public class HomeFragment extends Fragment {
    AlertDialog.Builder dialog;
    LayoutInflater inflater;
    View dialogView;
    TextView tvunderline;
    Button btnOk;
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

        etintent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intenttosearch = new Intent(getActivity(),SearchActivity.class);
                startActivity(intenttosearch);
            }
        });
        adapterAuthors= new AuthorsHotelAdapter(listAuthor,getActivity());
        rlvAuthors.setHasFixedSize(true);
        staggered = new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.HORIZONTAL);
        staggered.setGapStrategy(StaggeredGridLayoutManager.GAP_HANDLING_NONE);
        rlvAuthors.setLayoutManager(staggered);
        rlvAuthors.setAdapter(adapterAuthors);
        viewPager = v.findViewById(R.id.viewpager);
        VPAdapter vpAdapter = new VPAdapter(v.getContext());
        viewPager.setAdapter(vpAdapter);

        showDialogPertama();
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

        tvunderline = dialog.findViewById(R.id.tvunderline);
        String undr = "<u>What is Hotel Halal Features</u>";
        tvunderline.setText(Html.fromHtml(undr));
        btnOk = dialog.findViewById(R.id.btnOk);


        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }
}
