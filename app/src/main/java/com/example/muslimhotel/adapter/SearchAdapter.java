package com.example.muslimhotel.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import androidx.recyclerview.widget.RecyclerView;

import com.example.muslimhotel.R;
import com.example.muslimhotel.model.SearchHotel;
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
        SearchHotel data = list.get(position);
        Log.d("adapter", "onBindViewHolder: "+data.getDeskripsi());
        Picasso.with(context).load(data.getGambarHotel()).into(holder.ivBanner);
        holder.tvHarga.setText(data.getHargaHotel());
        holder.tvnmHotel.setText(data.getNmHotel());
        holder.tvreview.setText(data.getScoreHotel());
        holder.tvkotaHotel.setText(data.getKotaHotel());
        holder.deskripsi.setText(data.getDeskripsi());
        holder.tvscore.setText(data.getScoreHotel());
        holder.tvreview.setText("("+data.getReaview()+") Review");
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView ivBanner;
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
        }
    }
}
