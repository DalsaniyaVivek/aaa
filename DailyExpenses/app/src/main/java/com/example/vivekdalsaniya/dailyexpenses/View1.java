package com.example.vivekdalsaniya.dailyexpenses;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

/**
 * Created by Vivek Dalsaniya on 1/14/2016.
 */
public class View1 extends AppCompatActivity {
    private DatePicker datePicker;
    private Calendar calendar;
    private int year, month, day;
    private EditText e1;
    Spinner spinnerDropDown,spinnerDropDown1;
    String[] months = {
            "January",
            "February",
            "March",
            "April",
            "May",
            "June",
            "July",
            "August",
            "September","October","November","December"};
    String[] years = {
            "2015","2016","2017","2018","2019","2020","2021","2022","2023"
    };
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);
            setContentView(R.layout.view);
            android.support.v7.app.ActionBar actionBar = getSupportActionBar();
            actionBar.setTitle("View1");
            calendar = Calendar.getInstance();
            year = calendar.get(Calendar.YEAR);
            month = calendar.get(Calendar.MONTH);
            day = calendar.get(Calendar.DAY_OF_MONTH);
            e1 = (EditText) findViewById(R.id.ve1);
            TextView t1 = (TextView) findViewById(R.id.vt2);
            spinnerDropDown=(Spinner)findViewById(R.id.vs1);
            spinnerDropDown1=(Spinner)findViewById(R.id.vs2);
            TextView t2=(TextView)findViewById(R.id.vt5);
            TextView t3=(TextView)findViewById(R.id.vt7);
            showDate(year, month + 1, day);
            ArrayAdapter<String> adapter= new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item ,months);
            ArrayAdapter<String> adapter1=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,years);
            spinnerDropDown.setAdapter(adapter);
            spinnerDropDown1.setAdapter(adapter1);
            Button b1=(Button)findViewById(R.id.vb1);
            Button b2=(Button)findViewById(R.id.vb2);
            t3.setOnClickListener(new android.view.View.OnClickListener() {
                @Override
                public void onClick(android.view.View v) {
                    dataview d3=new dataview();
                    d3.viewFull1();
                    Intent i1=new Intent(View1.this,dataview.class);
                    startActivity(i1);
                }
            });
            b1.setOnClickListener(new android.view.View.OnClickListener() {
                @Override
                public void onClick(android.view.View v) {
                    dataview d4=new dataview();
                    d4.specDate();
                    Intent i2=new Intent(View1.this,dataview.class);
                    i2.putExtra("a",e1.getText().toString());
                    startActivity(i2);
                }
            });
            b2.setOnClickListener(new android.view.View.OnClickListener() {
                @Override
                public void onClick(android.view.View v) {
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
                    dataview d5=new dataview();
                    d5.setMonth();
                    Intent i3=new Intent(View1.this,dataview.class);
                    i3.putExtra("b",x);
                    i3.putExtra("c",y);
                    startActivity(i3);

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
        showDialog(990);
        Toast.makeText(getApplicationContext(), "ca", Toast.LENGTH_SHORT)
                .show();
    }



    protected Dialog onCreateDialog(int id) {
        // TODO Auto-generated method stub
        if (id == 990) {
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
