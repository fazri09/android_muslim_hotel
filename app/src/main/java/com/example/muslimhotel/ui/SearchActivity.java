package com.example.muslimhotel.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.muslimhotel.R;
import com.example.muslimhotel.model.SearchHotel;

import java.util.Calendar;

public class SearchActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {

    Button checkindatebutton, checkoutdatebutton, nextbutton;
    TextView  tvskip;
    Spinner spinnerpeople, spinnerbedroom;
    int buttonidentifier = 0;
    ArrayAdapter<String> peoplearradapter, bedroomarradapter;
    String getCheckIn,getCheckOut;
    private String hotelnamevalue;


    EditText ethotelname;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        Intent intent2 = getIntent();
        ethotelname = findViewById(R.id.ethotelname);
        hotelnamevalue = intent2.getStringExtra("hotelnamevalue");
        ethotelname.setText(hotelnamevalue);
        tvskip = findViewById(R.id.tvskip);
        nextbutton = findViewById(R.id.nextbuttonsearch);
        spinnerpeople = findViewById(R.id.spinnersearchpeople);
        spinnerbedroom = findViewById(R.id.spinnersearchbedroom);
        checkindatebutton = findViewById(R.id.buttoncheckin);
        checkoutdatebutton = findViewById(R.id.buttoncheckout);
        tvskip.setText(Html.fromHtml("<u>Skip For Now</u>"));


        tvskip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SearchActivity.this, SearchHotelActivity.class);
                intent.putExtra("checkin","9");
                intent.putExtra("checkout","10");
                intent.putExtra("people","1");
                intent.putExtra("bedroom","1");
                intent.putExtra("query_kota",hotelnamevalue);
                startActivity(intent);
            }
        });
        nextbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (checkindatebutton.getText().equals("Choose Date") || checkoutdatebutton.getText().equals("Choose Date")) {
                    Toast.makeText(SearchActivity.this,"Ada data yang kosong", Toast.LENGTH_SHORT).show();
                } else {

                    Intent intent = new Intent(SearchActivity.this, SearchHotelActivity.class);
                    intent.putExtra("checkin",getCheckIn);
                    intent.putExtra("checkout",getCheckOut);
                    intent.putExtra("people",spinnerpeople.getSelectedItem().toString());
                    intent.putExtra("bedroom",spinnerbedroom.getSelectedItem().toString());
                    intent.putExtra("query_kota",hotelnamevalue);
                    startActivity(intent);
                }

            }


        });
        spinnerbedroom.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String selecteditem = spinnerbedroom.getItemAtPosition(i).toString();
                if (selecteditem.equals("1")) {
                    spinnerbedroom.setSelection(0);
                } else if (selecteditem.equals("2")) {
                    spinnerbedroom.setSelection(1);
                } else if (selecteditem.equals("3")) {
                    spinnerbedroom.setSelection(2);
                } else {
                    spinnerbedroom.setSelection(0);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        spinnerpeople.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String selecteditem = spinnerpeople.getItemAtPosition(i).toString();
                if (selecteditem.equals("1")) {
                    spinnerpeople.setSelection(0);
                } else if (selecteditem.equals("2")) {
                    spinnerpeople.setSelection(1);
                } else if (selecteditem.equals("3")) {
                    spinnerpeople.setSelection(2);
                } else if (selecteditem.equals("4")) {
                    spinnerpeople.setSelection(3);
                } else if (selecteditem.equals("5")) {
                    spinnerpeople.setSelection(4);
                } else {
                    spinnerpeople.setSelection(0);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        bedroomarradapter = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_list_item_1,
                getResources().getStringArray(R.array.spinnerbedroomlist)
        );
        bedroomarradapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        spinnerbedroom.setAdapter(bedroomarradapter);
        peoplearradapter = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_list_item_1,
                getResources().getStringArray(R.array.spinnerpeoplelist)
        );
        peoplearradapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        spinnerpeople.setAdapter(peoplearradapter);
        checkindatebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buttonidentifier = 1;
                showdatepickerdialogue();
            }
        });
        checkoutdatebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buttonidentifier = 0;
                showdatepickerdialogue();
            }
        });
    }

    private void showdatepickerdialogue() {
        DatePickerDialog datePickerDialog = new DatePickerDialog(this, this, Calendar.getInstance().get(Calendar.YEAR), Calendar.getInstance().get(Calendar.MONTH), Calendar.getInstance().get(Calendar.DAY_OF_MONTH));
        datePickerDialog.show();
    }

    @Override
    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
        String month = "February";
        switch (i1) {
            case 0:
                month = "January";
                break;
            case 1:
                month = "February";
                break;
            case 2:
                month = "March";
                break;
            case 3:
                month = "April";
                break;
            case 4:
                month = "May";
                break;
            case 5:
                month = "June";
                break;
            case 6:
                month = "July";
                break;
            case 7:
                month = "August";
                break;
            case 8:
                month = "September";
                break;
            case 9:
                month = "October";
                break;
            case 10:
                month = "November";
                break;
            case 11:
                month = "December";
                break;
            default:
                month = "January";
                break;

        }
        String pickeddate = i2 + " " + month + " " + i;
        String bulan;
        if (String.valueOf(i1+1).length() < 2){
            bulan = "0"+String.valueOf(i1+1);
        }else {
            bulan = String.valueOf(i1+1);
        }

        String tgl;
        if (String.valueOf(i2).length() < 2){
            tgl = "0"+String.valueOf(i2);
        }else {
            tgl = String.valueOf(i2);
        }


        if (buttonidentifier == 1) {
            checkindatebutton.setText(pickeddate);
            getCheckIn = String.valueOf(i)+"-"+bulan+"-"+tgl;
        } else {
            checkoutdatebutton.setText(pickeddate);
            getCheckOut = String.valueOf(i)+"-"+bulan+"-"+tgl;
        }

    }

    @Override
    public void onBackPressed(){
        Intent intent = new Intent(SearchActivity.this, HomeActivity.class);
        startActivity(intent);
        finish();
    }
}
