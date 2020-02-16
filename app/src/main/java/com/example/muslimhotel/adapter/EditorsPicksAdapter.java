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
import com.example.muslimhotel.model.EditorsPicks;
import com.example.muslimhotel.model.Promo;

import java.util.ArrayList;

public class EditorsPicksAdapter extends RecyclerView.Adapter<EditorsPicksAdapter.ViewHolder> {
    private ArrayList<EditorsPicks> list;
    private Context context;
    public EditorsPicksAdapter(ArrayList<EditorsPicks> list, Context context){
        this.list = list;
        this.context = context;

    }
    @NonNull
    @Override
    public EditorsPicksAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_editorspack, parent, false);
        return new EditorsPicksAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EditorsPicksAdapter.ViewHolder holder, int position) {
        EditorsPicks data = list.get(position);
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
            ivGambar = itemView.findViewById(R.id.iv_gambar_hotel);
            tvDeskripsi = itemView.findViewById(R.id.deskripsi);
            tvReview = itemView.findViewById(R.id.review);
            tvTempat = itemView.findViewById(R.id.tv_tempat);
        }
    }
}
