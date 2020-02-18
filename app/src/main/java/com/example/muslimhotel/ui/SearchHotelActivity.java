package com.example.muslimhotel.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.os.Bundle;
import android.util.Log;

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
    private SearchAdapter adapter;
    private ArrayList<SearchHotel> list = new ArrayList<>();
    private RecyclerView recyclerView;
    private StaggeredGridLayoutManager setGapStrategy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_hotel);

        recyclerView = (RecyclerView)findViewById(R.id.rv1);


        adapter = new SearchAdapter(list,this);
        setGapStrategy = new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL);
        setGapStrategy.setGapStrategy(StaggeredGridLayoutManager.GAP_HANDLING_NONE);
        recyclerView.setLayoutManager(setGapStrategy);
        recyclerView.setAdapter(adapter);
        getDataHotel("asdf","asdf","asdf","asdf");
    }

    public void  getDataHotel(String tglAwal,String tglAkhir,String jKamar,String jOrang) {
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
                        list.add(get);
                        adapter.notifyDataSetChanged();


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
                prams.put("tanggal_awal", "2020-02-17");
                prams.put("tanggal_akhir", "2020-02-19");
                prams.put("jumlah_people", "5");
                prams.put("jumlah_bedrooms", "1");
                return prams;
            }
        };

        AppController.getInstance().addToRequestQueue(srGetDataHotel);

    }


}
