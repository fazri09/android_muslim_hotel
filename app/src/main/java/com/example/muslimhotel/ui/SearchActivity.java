package com.example.muslimhotel.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.muslimhotel.R;

import java.util.Calendar;

public class SearchActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {

    Button checkindatebutton,checkoutdatebutton,nextbutton;
    TextView tvspinpeople,tvspinbedroom;
    Spinner spinnerpeople,spinnerbedroom;
    int buttonidentifier=0;
    ArrayAdapter<String> peoplearradapter,bedroomarradapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        nextbutton = findViewById(R.id.nextbuttonsearch);
        tvspinbedroom = findViewById(R.id.bedroomtvspin);
        tvspinpeople = findViewById(R.id.peopletvspin);
        spinnerpeople = findViewById(R.id.spinnersearchpeople);
        spinnerbedroom = findViewById(R.id.spinnersearchbedroom);
        checkindatebutton = findViewById(R.id.buttoncheckin);
        checkoutdatebutton = findViewById(R.id.buttoncheckout);
        nextbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (checkindatebutton.getText().equals("Choose Date")||checkoutdatebutton.getText().equals("Choose Date")) {
                    Toast.makeText(SearchActivity.this,"Please Specify The Date",Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(SearchActivity.this,"Check-In Date: "+checkindatebutton.getText()+"\n"+"Check-Out Date: "+checkoutdatebutton.getText()+"\n"+"People: "+tvspinpeople.getText()+"\n"+"Bedrooms: "+tvspinbedroom.getText(),Toast.LENGTH_LONG).show();
                }
            }
        });
        spinnerbedroom.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String selecteditem = spinnerbedroom.getItemAtPosition(i).toString();
                if (selecteditem.equals("1")) {
                    tvspinbedroom.setText("1");
                }else if (selecteditem.equals("2")) {
                    tvspinbedroom.setText("2");
                }else if (selecteditem.equals("3")) {
                    tvspinbedroom.setText("3");
                }else {
                    tvspinbedroom.setText("1");
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
                    tvspinpeople.setText("1");
                }else if (selecteditem.equals("2")) {
                    tvspinpeople.setText("2");
                }else if (selecteditem.equals("3")) {
                    tvspinpeople.setText("3");
                }else if (selecteditem.equals("4")) {
                    tvspinpeople.setText("4");
                }else if (selecteditem.equals("5")) {
                    tvspinpeople.setText("5");
                }else {
                    tvspinpeople.setText("1");
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
                buttonidentifier=1;
                showdatepickerdialogue();
            }
        });
        checkoutdatebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buttonidentifier=0;
                showdatepickerdialogue();
            }
        });
    }
    private void showdatepickerdialogue() {
        DatePickerDialog datePickerDialog = new DatePickerDialog(this,this, Calendar.getInstance().get(Calendar.YEAR),Calendar.getInstance().get(Calendar.MONTH),Calendar.getInstance().get(Calendar.DAY_OF_MONTH));
        datePickerDialog.show();
    }

    @Override
    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
        String month="February";
        switch (i1) {
            case 0:
                month="January";
                break;
            case 1:
                month="February";
                break;
            case 2:
                month="March";
                break;
            case 3:
                month="April";
                break;
            case 4:
                month="May";
                break;
            case 5:
                month="June";
                break;
            case 6:
                month="July";
                break;
            case 7:
                month="August";
                break;
            case 8:
                month="September";
                break;
            case 9:
                month="October";
                break;
            case 10:
                month="November";
                break;
            case 11:
                month="December";
                break;
                default:
                    month="January";
                    break;

        }
        String pickeddate = i2+" "+month+" "+i;
        if (buttonidentifier==1) {
            checkindatebutton.setText(pickeddate);
        }else {
            checkoutdatebutton.setText(pickeddate);
        }

    }
}
