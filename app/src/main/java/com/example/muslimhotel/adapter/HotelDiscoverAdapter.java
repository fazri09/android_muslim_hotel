package com.example.muslimhotel.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.muslimhotel.R;
import com.example.muslimhotel.model.HotelDiscover;
import com.example.muslimhotel.model.Promo;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class HotelDiscoverAdapter extends RecyclerView.Adapter<HotelDiscoverAdapter.VIewHolder> {

    private ArrayList<HotelDiscover> list;
    private Context context;

    public HotelDiscoverAdapter(ArrayList<HotelDiscover> list, Context context){
        this.list = list;
        this.context = context;
    }
    @NonNull
    @Override
    public HotelDiscoverAdapter.VIewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_card_discover_hotel, parent, false);
        return new VIewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HotelDiscoverAdapter.VIewHolder holder, int position) {
    HotelDiscover data = list.get(position);
        Picasso.with(context).load(data.getGambarHotel()).into(holder.ivGambar);
        holder.tvNmHotel.setText(data.getNmHotel());
        holder.tvTempat.setText(data.getTempatHotel());
        holder.tvDeskripsi.setText(data.getDeskripsiHotel());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class VIewHolder extends RecyclerView.ViewHolder {
        private ImageView ivGambar;
        private TextView tvNmHotel,tvTempat,tvDeskripsi;

        public VIewHolder(@NonNull View itemView) {
            super(itemView);

            ivGambar = itemView.findViewById(R.id.imgcard1);
            tvTempat = itemView.findViewById(R.id.tvcard1);
            tvNmHotel = itemView.findViewById(R.id.tvcard2);
            tvDeskripsi = itemView.findViewById(R.id.tv_deskripsi);
        }
    }
}
