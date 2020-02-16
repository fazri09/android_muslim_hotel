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
import com.example.muslimhotel.model.AuthorsHotel;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class AuthorsHotelAdapter extends RecyclerView.Adapter<AuthorsHotelAdapter.ViewHolder> {

    private ArrayList<AuthorsHotel> list;
    private Context context;
    public AuthorsHotelAdapter(ArrayList<AuthorsHotel> list, Context context){
        this.list = list;
        this.context = context;
    }
    @NonNull
    @Override
    public AuthorsHotelAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_authors_choice, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AuthorsHotelAdapter.ViewHolder holder, int position) {
        AuthorsHotel data = list.get(position);
        Picasso.with(context).load(data.getGambarHotel()).into(holder.ivGambar);
        holder.tvReview.setText("("+String.valueOf(data.getReview()+") Review "));
        holder.tvTempat.setText(data.getTempat());
        holder.tvDeskripsi.setText(data.getNmHotel());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView ivGambar;
        private TextView tvReview,tvTempat,tvDeskripsi;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            ivGambar = itemView.findViewById(R.id.imageacid);
            tvDeskripsi = itemView.findViewById(R.id.tvcardac2);
            tvReview = itemView.findViewById(R.id.tv_review);
            tvTempat = itemView.findViewById(R.id.tv_tempat);
        }
    }
}
