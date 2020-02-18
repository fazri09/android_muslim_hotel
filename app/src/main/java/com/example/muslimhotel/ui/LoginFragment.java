package com.example.muslimhotel.ui;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

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

public class LoginFragment extends Fragment {

    private TextView btnLogin, password_salah;
    private EditText et_email,et_password;
    ProgressDialog pDialog;

    String tag_json_obj = "json_obj_req";
    private String url_login = Server.URL_PROD + "/crud/login_aksi";

    public static final String PREF_IS_LOGIN = "is_login";
    public static final String PREF_EMAIL = "data_email";
    public static final String PREF_PASSWORD = "data_password";
    public static final String PREF_NAMA = "data_nama";
    public static final String PREF_ID = "data_id";



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_login, container, false);

        btnLogin = (TextView) v.findViewById(R.id.btnLogin);
        et_email = (EditText) v.findViewById(R.id.et_email);
        et_password = (EditText) v.findViewById(R.id.et_password);
        password_salah = (TextView) v.findViewById(R.id.password_salah);
        return v;
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        password_salah.setVisibility(View.GONE);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = et_email.getText().toString();
                String password = et_password.getText().toString();
                reqLoginApi(email, password);

            }
        });
    }

    private void reqLoginApi(final String email, final String password) {
        pDialog = new ProgressDialog(getActivity());
        pDialog.setCancelable(false);
        pDialog.setMessage("Mohon Tunggu ...");
        showDialog();

        StringRequest strReq = new StringRequest(Request.Method.POST, url_login  , new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                Log.d("cekresponse", "Register Response: " + response.toString());
                hideDialog();
                try {

                    JSONObject object = new JSONObject(response);

                    String status = object.getString("status");
                    String id = object.getString("id");
                    String nama = object.getString("nama");
                    String email = object.getString("email");
                    String password = object.getString("password");
                    Log.d("cekstatus", "onResponse: "+status);

                    if (status.equalsIgnoreCase("1")){
                        //set session
                        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
                        SharedPreferences.Editor editor = preferences.edit();
                        editor.putBoolean(PREF_IS_LOGIN, true);
                        editor.putString(PREF_NAMA, nama);
                        editor.putString(PREF_PASSWORD, password);
                        editor.putString(PREF_EMAIL, email);
                        editor.putString(PREF_ID, id);
                        editor.commit();


                        //intent
                        Toast.makeText(getActivity(), "Login Berhasil", Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(getActivity(), MainActivity.class);
                        startActivity(intent);
                        getActivity().finish();
                    }else{
                        password_salah.setVisibility(View.VISIBLE);
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("logerror", "Login Error: " + error.getMessage());
                Toast.makeText(getActivity(),
                        error.getMessage(), Toast.LENGTH_LONG).show();

                hideDialog();

            }
        }) {

            @Override
            protected Map<String, String> getParams() {
                // Posting parameters to login url
                Map<String, String> params = new HashMap<String, String>();
                params.put("email", email);
                params.put("password", password);

                return params;
            }

        };

        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(strReq, tag_json_obj);
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
