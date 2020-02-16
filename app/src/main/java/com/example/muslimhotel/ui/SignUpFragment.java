package com.example.muslimhotel.ui;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.muslimhotel.R;
import com.example.muslimhotel.app.AppController;
import com.example.muslimhotel.util.Server;

import java.util.HashMap;
import java.util.Map;

public class SignUpFragment extends Fragment {

    private EditText et_nama, et_email, et_password, et_cpassword;
    private TextView submit, password_ga_sesuai;
    private String nama, email, password, password2;
    ProgressDialog pDialog;
    String tag_json_obj = "json_obj_req";


    private String url_register = Server.URL_PROD + "/crud/regis_aksi";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_sign_up, container, false);

        et_nama = (EditText) v.findViewById(R.id.et_nama);
        et_email = (EditText) v.findViewById(R.id.et_email);
        et_password = (EditText) v.findViewById(R.id.et_password);
        et_cpassword = (EditText) v.findViewById(R.id.et_cpassword);
        password_ga_sesuai = (TextView) v.findViewById(R.id.password_ga_sesuai);
        submit = (TextView) v.findViewById(R.id.submit);

        return v;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        penyesuaianPassword();

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nama = et_nama.getText().toString();
                email = et_email.getText().toString();
                password = et_password.getText().toString();
                password2 = et_cpassword.getText().toString();
                reqSignupApi(nama, email, password);
            }
        });
    }

    private void reqSignupApi(final String nama, final String email, final String password) {
        pDialog = new ProgressDialog(getActivity());
        pDialog.setCancelable(false);
        pDialog.setMessage("Register ...");
        showDialog();

        StringRequest strReq = new StringRequest(Request.Method.POST, url_register  , new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                Log.e("cekresponse", "Register Response: " + response.toString());
                hideDialog();
                Toast.makeText(getActivity(), "Registrasi Berhasil", Toast.LENGTH_LONG).show();
            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("logerror", "Login Error: " + error.getMessage());
                Toast.makeText(getActivity(),
                        error.getMessage(), Toast.LENGTH_LONG).show();

                hideDialog();

            }
        }) {

            @Override
            protected Map<String, String> getParams() {
                // Posting parameters to login url
                Map<String, String> params = new HashMap<String, String>();
                params.put("nama", nama);
                params.put("email", email);
                params.put("password", password);

                return params;
            }

        };

        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(strReq, tag_json_obj);
    }

    private void penyesuaianPassword() {
        et_cpassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {


                if (et_password.getText().toString().equalsIgnoreCase(et_cpassword.getText().toString())) {
                    password_ga_sesuai.setVisibility(View.GONE);
                } else {
                    password_ga_sesuai.setVisibility(View.VISIBLE);
                }
            }
        });

        et_password.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

                if (!et_cpassword.getText().toString().equalsIgnoreCase("")) {
                    if (et_password.getText().toString().equalsIgnoreCase(et_cpassword.getText().toString())) {
                        password_ga_sesuai.setVisibility(View.GONE);
                    } else {
                        password_ga_sesuai.setVisibility(View.VISIBLE);
                    }
                } else {
                    password_ga_sesuai.setVisibility(View.GONE);
                }

            }
        });

    }

    private void showDialog() {
        if (!pDialog.isShowing())
            pDialog.show();
    }

    private void hideDialog() {
        if (pDialog.isShowing())
            pDialog.dismiss();
    }
}
