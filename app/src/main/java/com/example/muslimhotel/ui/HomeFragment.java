package com.example.muslimhotel.ui;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.muslimhotel.R;
import com.example.muslimhotel.adapter.EditorsPicksAdapter;
import com.example.muslimhotel.adapter.HotelDiscoverAdapter;
import com.example.muslimhotel.adapter.PromoAdapter;
import com.example.muslimhotel.model.EditorsPicks;
import com.example.muslimhotel.model.HotelDiscover;
import com.example.muslimhotel.model.Promo;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {
    AlertDialog.Builder dialog;
    LayoutInflater inflater;
    View dialogView;
    TextView tvunderline;
    Button btnOk;
    private RecyclerView rLvPromo,rlvPicks,rlvDiscover;
    private ArrayList<Promo> listPromo = new ArrayList<>();
    private PromoAdapter adapterPromo;
    private EditorsPicksAdapter adapterEditors;
    private StaggeredGridLayoutManager staggered;
    private ArrayList <EditorsPicks> listPicks = new ArrayList<>();
    private ArrayList <HotelDiscover> lisHotelDiscover = new ArrayList<>();
    private HotelDiscoverAdapter adapterDiscover;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_home, container, false);

        rLvPromo = (RecyclerView)v.findViewById(R.id.rV1);
        rlvPicks = (RecyclerView)v.findViewById(R.id.rV2);
        rlvDiscover = (RecyclerView)v.findViewById(R.id.rV3);

        listPromo.add(new Promo(R.drawable.promo1));
        listPromo.add(new Promo(R.drawable.promo1));
        adapterPromo = new PromoAdapter(listPromo,getActivity());
        rLvPromo.setHasFixedSize(true);
        staggered = new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.HORIZONTAL);
        staggered.setGapStrategy(StaggeredGridLayoutManager.GAP_HANDLING_NONE);
        rLvPromo.setLayoutManager(staggered);
        rLvPromo.setAdapter(adapterPromo);


        listPicks.add(new EditorsPicks("Phulay Bay",235,R.drawable.editor1));
        listPicks.add(new EditorsPicks("Phulay Bay",235,R.drawable.editor1));
        adapterEditors = new EditorsPicksAdapter(listPicks,getActivity());
        rlvPicks.setHasFixedSize(true);
        staggered = new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.HORIZONTAL);
        staggered.setGapStrategy(StaggeredGridLayoutManager.GAP_HANDLING_NONE);
        rlvPicks.setLayoutManager(staggered);
        rlvPicks.setAdapter(adapterEditors);

        lisHotelDiscover.add(new HotelDiscover("HotelDiscover",R.drawable.dischotel,"There are more than 2,500 people recommend The Ledbury","127 Ledbury Road, London W11 2AQ"));
        lisHotelDiscover.add(new HotelDiscover("HotelDiscover",R.drawable.dischotel,"There are more than 2,500 people recommend The Ledbury","127 Ledbury Road, London W11 2AQ"));

        adapterDiscover= new HotelDiscoverAdapter(lisHotelDiscover,getActivity());
        rlvDiscover.setHasFixedSize(true);
        staggered = new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.HORIZONTAL);
        staggered.setGapStrategy(StaggeredGridLayoutManager.GAP_HANDLING_NONE);
        rlvDiscover.setLayoutManager(staggered);
        rlvDiscover.setAdapter(adapterDiscover);

        showDialogPertama();
        return v;
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
