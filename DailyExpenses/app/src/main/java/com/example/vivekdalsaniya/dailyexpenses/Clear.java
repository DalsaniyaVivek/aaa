package com.example.vivekdalsaniya.dailyexpenses;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.sql.SQLException;
import java.util.Calendar;

/**
 * Created by Vivek Dalsaniya on 1/22/2016.
 */
public class Clear extends AppCompatActivity {
    private DatePicker datePicker;
    private Calendar calendar;
    private int year, month, day;
    private EditText e1;
    Spinner spinnerDropDown, spinnerDropDown1;
    String[] months = {
            "January",
            "February",
            "March",
            "April",
            "May",
            "June",
            "July",
            "August",
            "September", "October", "November", "December"};
    String[] years = {
            "2015", "2016", "2017", "2018", "2019", "2020", "2021", "2022", "2023"
    };

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setContentView(R.layout.clear);
        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Clear");
        calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);
        e1 = (EditText) findViewById(R.id.ce1);
        spinnerDropDown=(Spinner)findViewById(R.id.cs1);
        spinnerDropDown1=(Spinner)findViewById(R.id.cs2);
        showDate(year, month + 1, day);
        ArrayAdapter<String> adapter= new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item ,months);
        ArrayAdapter<String> adapter1=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,years);
        spinnerDropDown.setAdapter(adapter);
        spinnerDropDown1.setAdapter(adapter1);
        Button b1=(Button)findViewById(R.id.cb1);
        Button b2=(Button)findViewById(R.id.cb2);
        b1.setOnClickListener(new android.view.View.OnClickListener() {
            @Override
            public void onClick(android.view.View v) {
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(Clear.this);
                alertDialogBuilder.setMessage("Are you sure,You wanted to make decision");

                alertDialogBuilder.setPositiveButton("yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {
                        Toast.makeText(Clear.this, "You clicked yes button", Toast.LENGTH_LONG).show();
                        String sDate = e1.getText().toString();
                        datacode ex2 = new datacode(Clear.this);
                        try {
                            ex2.open();
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                        ex2.deleteEntryByDate(sDate);
                        ex2.close();
                    }
                });

                alertDialogBuilder.setNegativeButton("No",new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                });

                AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialog.show();
            }
        });

        b2.setOnClickListener(new android.view.View.OnClickListener() {
            @Override
            public void onClick(android.view.View v) {
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(Clear.this);
                alertDialogBuilder.setMessage("Are you sure,You wanted to make decision");

                alertDialogBuilder.setPositiveButton("yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {
                        String x=spinnerDropDown.getSelectedItem().toString();
                        String y=spinnerDropDown1.getSelectedItem().toString();
                        if(x.equals("January")){
                            x="1";
                        }else if(x.equals("February")){
                            x="2";
                        }else if(x.equals("March")){
                            x="3";
                        }else if(x.equals("April")){
                            x="4";
                        }else if(x.equals("May")){
                            x="5";
                        }else if(x.equals("June")){
                            x="6";
                        }else if(x.equals("July")){
                            x="7";
                        }else if(x.equals("August")){
                            x="8";
                        }else if(x.equals("September")){
                            x="9";
                        }else if(x.equals("October")){
                            x="10";
                        }else if(x.equals("November")){
                            x="11";
                        }else{
                            x="12";
                        }
                        datacode ex3 = new datacode(Clear.this);
                        try {
                            ex3.open();
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                        ex3.deleteEntryByMonth(x,y);
                        ex3.close();

                    }
                });

                alertDialogBuilder.setNegativeButton("No",new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                });

                AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialog.show();
            }
        });

    }



    public void onItemSelected(AdapterView<?> parent, View1 view1,
                               int position, long id) {
        // Get select item
        Toast.makeText(parent.getContext(),
                "OnItemSelectedListener : " + parent.getItemAtPosition(position).toString(),
                Toast.LENGTH_SHORT).show();


    }


    public void setDate(android.view.View view) {
        showDialog(989);
        Toast.makeText(getApplicationContext(), "ca", Toast.LENGTH_SHORT)
                .show();
    }



    protected Dialog onCreateDialog(int id) {
        // TODO Auto-generated method stub
        if (id == 989) {
            return new DatePickerDialog(this, myDateListener, year, month, day);
        }

        return null;
    }

    private DatePickerDialog.OnDateSetListener myDateListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker arg0, int arg1, int arg2, int arg3) {
            // TODO Auto-generated method stub
            // arg1 = year
            // arg2 = month
            // arg3 = day
            showDate(arg1, arg2 + 1, arg3);
        }
    };



    private void showDate(int year, int month, int day) {
        e1.setText(new StringBuilder().append(day).append("/")
                .append(month).append("/").append(year));
    }
}
