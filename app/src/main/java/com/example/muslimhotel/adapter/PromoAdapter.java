package com.example.muslimhotel.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.muslimhotel.R;
import com.example.muslimhotel.model.Promo;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class PromoAdapter extends RecyclerView.Adapter<PromoAdapter.ViewHolder> {

    private ArrayList<Promo> list;
    private Context context;


    public PromoAdapter (ArrayList<Promo> list, Context context){
        this.list = list;
        this.context = context;
    }


    @NonNull
    @Override
    public PromoAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_promo, parent, false);
    return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Promo data = list.get(position);
        Log.d("adapter", "onBindViewHolder: "+data.gambarPromo);
        Picasso.with(context).load(data.getGambarPromo()).into(holder.ivGambar);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView ivGambar;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

             ivGambar = itemView.findViewById(R.id.gambar_promo);

        }
    }
}
