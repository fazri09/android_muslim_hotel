package com.example.muslimhotel.ui;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.muslimhotel.R;

import org.w3c.dom.Text;

public class HomeFragment extends Fragment {
    AlertDialog.Builder dialog;
    LayoutInflater inflater;
    View dialogView;
    TextView tvunderline;
    Button btnOk;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_home, container, false);
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
