package com.example.muslimhotel.adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.muslimhotel.R;
import com.example.muslimhotel.model.SearchHotel;
import com.example.muslimhotel.ui.HotelDetailActivity;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.ViewHolder> {
    private ArrayList<SearchHotel> list;
    private Context context;
    public SearchAdapter(ArrayList<SearchHotel> list, Context context){
        this.list = list;
        this.context = context;
    }
    @NonNull
    @Override
    public SearchAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_search_hotel, parent, false);
        return new SearchAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SearchAdapter.ViewHolder holder, int position) {
        final SearchHotel data = list.get(position);
        Log.d("adapter", "onBindViewHolder: "+data.getDeskripsi());
        Picasso.with(context).load(data.getGambarHotel()).into(holder.ivBanner);
        holder.tvHarga.setText("IDR "+data.getHargaHotel());
        holder.tvnmHotel.setText(data.getNmHotel());
        holder.tvreview.setText(data.getScoreHotel());
        holder.tvkotaHotel.setText(data.getKotaHotel());
        holder.tvscore.setText(data.getScoreHotel());
        holder.tvreview.setText("("+data.getReaview()+") Review");

        holder.cvOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context, HotelDetailActivity.class);
                i.putExtra("nmHotel",data.getNmHotel());
                i.putExtra("kotaHotel",data.getKotaHotel());
                i.putExtra("gambarHotel",data.getGambarHotel());
                i.putExtra("hgHotel",data.getHargaHotel());
                i.putExtra("review",data.getReaview());
                i.putExtra("scoreHotel",data.getScoreHotel());
                i.putExtra("deskripsi",data.getDeskripsi());
                i.putExtra("cekIn",data.getTglAwalHotel());
                i.putExtra("cekOut",data.getTglAkhirHotel());
                i.putExtra("orang",data.getjPeopleTersedia());
                i.putExtra("bedroom",data.getjKamarHotel());
                context.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView ivBanner;
        private CardView cvOne;
        private TextView tvnmHotel,tvkotaHotel,tvreview,tvscore,tvtersedia,tvkamar,deskripsi,tvHarga;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ivBanner = (ImageView)itemView.findViewById(R.id.iv_banner);
            tvnmHotel = (TextView)itemView.findViewById(R.id.tv_nama);
            tvkotaHotel = (TextView)itemView.findViewById(R.id.tv_tempat);
            tvscore = (TextView)itemView.findViewById(R.id.gradbox);
            deskripsi = (TextView)itemView.findViewById(R.id.deskripsi);
            tvHarga = (TextView)itemView.findViewById(R.id.tv_harga);
            tvreview = (TextView)itemView.findViewById(R.id.tv_view);
            cvOne = (CardView) itemView.findViewById(R.id.cv_one);
        }
    }
}
