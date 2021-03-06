package com.example.muslimhotel.ui;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.preference.PreferenceManager;
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
import com.example.muslimhotel.MainActivity;
import com.example.muslimhotel.R;
import com.example.muslimhotel.app.AppController;
import com.example.muslimhotel.util.Server;

import org.json.JSONException;
import org.json.JSONObject;

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
                String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

                if (password2.equalsIgnoreCase("")){
                    Toast.makeText(getActivity(), "Confrim Password Tidak Boleh Kosong", Toast.LENGTH_LONG).show();
                }else if (password.equalsIgnoreCase("")){
                    Toast.makeText(getActivity(), "Password Tidak Boleh Kosong", Toast.LENGTH_LONG).show();
                }else if (!email.contains("@")){
                    Toast.makeText(getActivity(), "Email Harus Mengandung @", Toast.LENGTH_LONG).show();
                }else  if (email.equalsIgnoreCase("")){
                    Toast.makeText(getActivity(), "Email Tidak Boleh Kosong", Toast.LENGTH_LONG).show();
                }else if (!email.contains(".")){
                    Toast.makeText(getActivity(), "Email harus mengandung .(titik)", Toast.LENGTH_LONG).show();
                }else if (email.contains(" ")){
                    Toast.makeText(getActivity(), "Email tidak boleh ada spasi", Toast.LENGTH_LONG).show();
                }else if (!email.matches(emailPattern)){
                    Toast.makeText(getActivity(),"Email tidak valid",Toast.LENGTH_SHORT).show();
                }else {
                    if (password.equalsIgnoreCase(password2)){
                        reqSignupApi(nama, email, password);
                    }else{
                        password_ga_sesuai.setVisibility(View.VISIBLE);
                    }
                }
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

                try {

                    JSONObject object = new JSONObject(response);

                    String status = object.getString("status");
                    Log.d("cekstatus", "onResponse: "+status);


                    /*
                    status = 2 > gagal, emailnya udah ada di db
                    status = 1 > sukses masukin data register ke db
                    status = 0 > gagal, server down/ada bug codingan

                     */
                    if (status.equalsIgnoreCase("1")){
                        hideDialog();
                        Toast.makeText(getActivity(), "Registrasi Berhasil", Toast.LENGTH_LONG).show();
                        startActivity(new Intent(getActivity(), SigninActivity.class));
                       getActivity().finish();
                    }else if (status.equalsIgnoreCase("2")){
                        hideDialog();
                        Toast.makeText(getActivity(), "Registrasi Gagal, Email Sudah Tersedia", Toast.LENGTH_LONG).show();
                    }else{
                        hideDialog();
                        Toast.makeText(getActivity(), "Server Sibuk", Toast.LENGTH_LONG).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    hideDialog();
                    Toast.makeText(getActivity(), "Ada Kesalahan Merespon", Toast.LENGTH_LONG).show();

                }
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
