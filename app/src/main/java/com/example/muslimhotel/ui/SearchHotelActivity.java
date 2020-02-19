package com.example.muslimhotel.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.EditText;
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
    private String URLGetDataHotel = Server.URL_PROD+"/crud/search_hotel_by_kota";
    private TextView tvCheckIn,tvCheckOut,tvJhotel,tvBadAndPeople;
    private SearchAdapter adapter;
    private ArrayList<SearchHotel> list = new ArrayList<>();
    private RecyclerView recyclerView;
    private StaggeredGridLayoutManager setGapStrategy;
    private EditText et_search;
    private String tglAwal,tglAkhir,jOrang,jKamar,jQueryKota;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_hotel);

        recyclerView = (RecyclerView)findViewById(R.id.rv1);
        tvCheckIn = (TextView)findViewById(R.id.tv_checkIn);
        tvCheckOut = (TextView)findViewById(R.id.tv_checkOut);
        tvJhotel = (TextView)findViewById(R.id.tv_jhotel);
        tvBadAndPeople = (TextView)findViewById(R.id.tv_bed_and_people);
        et_search = (EditText) findViewById(R.id.et_search);


        tglAwal = getIntent().getStringExtra("checkin");
        tglAkhir = getIntent().getStringExtra("checkout");
        jOrang = getIntent().getStringExtra("people");
        jKamar = getIntent().getStringExtra("bedroom");
        jQueryKota = getIntent().getStringExtra("query_kota");


        if (tglAwal.equalsIgnoreCase("9")){
            tvCheckIn.setText("");
            tvCheckOut.setText("");
            tvBadAndPeople.setText("-");
            et_search.setText(jQueryKota);
            adapter = new SearchAdapter(list, this);
            setGapStrategy = new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL);
            setGapStrategy.setGapStrategy(StaggeredGridLayoutManager.GAP_HANDLING_NONE);
            recyclerView.setLayoutManager(setGapStrategy);
            recyclerView.setAdapter(adapter);
        }else {
            Log.d(TAG, "onCreate: " + tglAwal + " " + tglAkhir + " " + jOrang + " " + jKamar + " " + jQueryKota);

            tvCheckIn.setText(tglAwal);
            tvCheckOut.setText(tglAkhir);
            tvBadAndPeople.setText(jKamar + " Bedroom - " + jOrang + " People");
            et_search.setText(jQueryKota);
            adapter = new SearchAdapter(list, this);
            setGapStrategy = new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL);
            setGapStrategy.setGapStrategy(StaggeredGridLayoutManager.GAP_HANDLING_NONE);
            recyclerView.setLayoutManager(setGapStrategy);
            recyclerView.setAdapter(adapter);
        }
        getDataHotel(tglAwal,tglAkhir,jKamar,jOrang, jQueryKota);

//        et_search.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//
//            }
//
//            @Override
//            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//
//            }
//
//            @Override
//            public void afterTextChanged(Editable editable) {
//                jQueryKota = et_search.getText().toString();
//                getDataHotel(tglAwal,tglAkhir,jKamar,jOrang, jQueryKota);
//
//            }
//        });

    }

    public void  getDataHotel(final String tglAwal, final String tglAkhir, final String jKamar, final String jOrang, final String jQueryKota) {
        final ProgressDialog dialog = ProgressDialog.show(SearchHotelActivity.this, "",
                "Mohon Tunggu", true);
        dialog.setCancelable(false);
        StringRequest srGetDataHotel = new StringRequest(Request.Method.POST, URLGetDataHotel, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    dialog.dismiss();
                    Log.d("cekresponse", "onResponse: "+response);
                    JSONArray array = new JSONArray(response);
                    Log.d(TAG, "onResponse: "+array.length());
                    for (int i = 0; i < array.length(); i++) {
                        JSONObject data = array.getJSONObject(i);
                        SearchHotel get = new SearchHotel();
                        if (data.getString("messages").equalsIgnoreCase("success")){
                            dialog.dismiss();
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
                            get.setTglAwalHotel(data.getString("tanggal_awal"));
                            get.setTglAkhirHotel(data.getString("tanggal_akhir"));

                            tvJhotel.setText(data.getString("total_rows")+" Hotel found");
                            list.add(get);
                            adapter.notifyDataSetChanged();
                        }else if (data.getString("messages").equalsIgnoreCase("gagal")){
                            dialog.dismiss();
                            tvJhotel.setText("0 Hotel found");
                            Intent intent = new Intent(SearchHotelActivity.this,HomeActivity.class);
                            Toast.makeText(SearchHotelActivity.this,"Data tidak ada",Toast.LENGTH_SHORT).show();
                            startActivity(intent);
                            finish();

                        }else {
                            dialog.dismiss();
                            tvJhotel.setText("0 Hotel found");
                            Intent intent = new Intent(SearchHotelActivity.this,HomeActivity.class);
                            Toast.makeText(SearchHotelActivity.this,"Tanggal Check In tidak boleh melebihin tanggal Check Out",Toast.LENGTH_SHORT).show();
                            startActivity(intent);
                            finish();
                        }
                    }


                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                dialog.dismiss();
                Toast.makeText(SearchHotelActivity.this,"Server Error",Toast.LENGTH_SHORT).show();

            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String, String> prams = new HashMap<>();
                prams.put("tanggal_awal", tglAwal);
                prams.put("tanggal_akhir", tglAkhir);
                prams.put("jumlah_people", jOrang);
                prams.put("jumlah_bedrooms", jKamar);
                prams.put("query_kota", jQueryKota);
                return prams;
            }
        };

        AppController.getInstance().addToRequestQueue(srGetDataHotel);

    }


}
