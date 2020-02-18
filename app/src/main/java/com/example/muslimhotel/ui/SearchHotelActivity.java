package com.example.muslimhotel.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.muslimhotel.R;
import com.example.muslimhotel.adapter.SearchAdapter;
import com.example.muslimhotel.app.AppController;
import com.example.muslimhotel.model.SearchHotel;
import com.example.muslimhotel.util.Server;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class SearchHotelActivity extends AppCompatActivity {
    private String TAG = "SearchHotelActivvity";
    private String URLGetDataHotel = Server.URL_PROD+"/crud/search_hotel";
    private TextView tvCheckIn,tvCheckOut,tvJhotel,tvBadAndPeople;
    private SearchAdapter adapter;
    private ArrayList<SearchHotel> list = new ArrayList<>();
    private RecyclerView recyclerView;
    private StaggeredGridLayoutManager setGapStrategy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_hotel);

        recyclerView = (RecyclerView)findViewById(R.id.rv1);
        tvCheckIn = (TextView)findViewById(R.id.tv_checkIn);
        tvCheckOut = (TextView)findViewById(R.id.tv_checkOut);
        tvJhotel = (TextView)findViewById(R.id.tv_jhotel);
        tvBadAndPeople = (TextView)findViewById(R.id.tv_bed_and_people);


        String tglAwal = getIntent().getStringExtra("checkin");
        String tglAkhir = getIntent().getStringExtra("checkout");
        String jOrang = getIntent().getStringExtra("people");
        String jKamar = getIntent().getStringExtra("bedroom");
        Log.d(TAG, "onCreate: "+tglAwal+" "+tglAkhir+" "+jOrang+" "+jKamar);

        tvCheckIn.setText(tglAwal);
        tvCheckOut.setText(tglAkhir);
        tvBadAndPeople.setText(jKamar+" Bedroom - "+jOrang+" People");
        adapter = new SearchAdapter(list,this);
        setGapStrategy = new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL);
        setGapStrategy.setGapStrategy(StaggeredGridLayoutManager.GAP_HANDLING_NONE);
        recyclerView.setLayoutManager(setGapStrategy);
        recyclerView.setAdapter(adapter);
        getDataHotel(tglAwal,tglAkhir,jKamar,jOrang);
    }

    public void  getDataHotel(final String tglAwal, final String tglAkhir, final String jKamar, final String jOrang) {
        StringRequest srGetDataHotel = new StringRequest(Request.Method.POST, URLGetDataHotel, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    Log.d(TAG, "onResponse: "+response);
                    JSONArray array = new JSONArray(response);
                    Log.d(TAG, "onResponse: "+array.length());
                    for (int i = 0; i < array.length(); i++) {
                        JSONObject data = array.getJSONObject(i);
                        SearchHotel get = new SearchHotel();
                        if (data.getString("messages").equalsIgnoreCase("success")){
                            Log.d(TAG, "onResponse: "+data.getString("id_hotel"));
                            get.setHargaHotel(data.getString("harga"));
                            get.setGambarHotel(data.getString("img_hotel"));
                            get.setIdHotel(data.getString("id_hotel"));
                            get.setjKamarHotel(data.getString("bedrooms_tersedia"));
                            get.setjPeopleTersedia(data.getString("people_tersedia"));
                            get.setNmHotel(data.getString("nama_hotel"));
                            get.setKotaHotel(data.getString("kota_hotel"));
                            get.setScoreHotel(data.getString("score_hotel"));
                            get.setReaview(data.getString("reviewer_hotel"));
                            get.setDeskripsi(data.getString("deskripsi"));

//                        tvJhotel.setText();
                            list.add(get);
                            adapter.notifyDataSetChanged();
                        }else if (data.getString("messages").equalsIgnoreCase("gagal")){
                            Intent intent = new Intent(SearchHotelActivity.this,SearchActivity.class);
                            Toast.makeText(SearchHotelActivity.this,"Data tida ada",Toast.LENGTH_SHORT).show();
                            startActivity(intent);

                        }else {
                            Intent intent = new Intent(SearchHotelActivity.this,SearchActivity.class);
                            Toast.makeText(SearchHotelActivity.this,"Tanggal Check In tidak boleh melebihin tanggal Check Out",Toast.LENGTH_SHORT).show();
                            startActivity(intent);
                        }


                    }


                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String, String> prams = new HashMap<>();
                prams.put("tanggal_awal", tglAwal);
                prams.put("tanggal_akhir", tglAkhir);
                prams.put("jumlah_people", jOrang);
                prams.put("jumlah_bedrooms", jKamar);
                return prams;
            }
        };

        AppController.getInstance().addToRequestQueue(srGetDataHotel);

    }


}
