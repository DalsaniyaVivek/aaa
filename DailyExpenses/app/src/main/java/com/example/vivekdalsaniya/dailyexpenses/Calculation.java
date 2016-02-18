package com.example.vivekdalsaniya.dailyexpenses;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Vivek Dalsaniya on 1/9/2016.
 */
public class Calculation extends AppCompatActivity {
    private DatePicker datePicker;
    private Calendar calendar;
    private int year, month, day;
    private EditText e1,e2,e3,e4;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calculation);
        ActionBar actionbar = getSupportActionBar();
        actionbar.setTitle("Calculation");
        calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);
        e1=(EditText)findViewById(R.id.ee1);
        e2=(EditText)findViewById(R.id.ee2);
        e3=(EditText)findViewById(R.id.ee3);
        e4=(EditText)findViewById(R.id.cae1);
        Button b1=(Button)findViewById(R.id.bb1);
        Button b2=(Button)findViewById(R.id.bb2);
        Button b3=(Button)findViewById(R.id.bb3);
        showDate(year,month+1,day);
        showDate1(year, month + 1, day);
        showDate2(year,month+1,day);
        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Date date=null,date1 = null;
                SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
                String dateInString = e1.getText().toString();
                String dateInString1 = e2.getText().toString();
                try {
                    date = formatter.parse(dateInString);
                    date1 = formatter.parse(dateInString1);

                } catch (ParseException e) {
                    e.printStackTrace();
                }
                datacode info1=new datacode(Calculation.this);
                try {
                    info1.open();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                int data=info1.countCustom(date,date1);
                info1.close();
                String data1= String.valueOf(data);
                e3.setText(data1);
            }
        });
    }


    public void setstartDate(View view) {
        showDialog(999);
        Toast.makeText(getApplicationContext(), "ca", Toast.LENGTH_SHORT)
                .show();
    }

    public void setendDate(View view){
        showDialog(998);
        Toast.makeText(getApplicationContext(), "cal", Toast.LENGTH_SHORT)
                .show();

    }

    public void setDateforDate(View view){
        showDialog(981);
        Toast.makeText(getApplicationContext(), "cal", Toast.LENGTH_SHORT)
                .show();
    }

    protected Dialog onCreateDialog(int id) {
        // TODO Auto-generated method stub
        if (id == 999) {
            return new DatePickerDialog(this, myDateListener, year, month, day);
        }
        if(id == 998) {
            return new DatePickerDialog(this, myDateListener1, year, month, day);
        }
        if(id == 981)
            return new DatePickerDialog(this ,myDateListener2, year, month, day );
        return null;
    }

    private DatePickerDialog.OnDateSetListener myDateListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker arg0, int arg1, int arg2, int arg3) {
            // TODO Auto-generated method stub
            // arg1 = year
            // arg2 = month
            // arg3 = day
            showDate(arg1, arg2+1, arg3);
        }
    };

    private DatePickerDialog.OnDateSetListener myDateListener1 = new DatePickerDialog.OnDateSetListener(){

        public void onDateSet(DatePicker arg0, int arg1, int arg2, int arg3) {
            // TODO Auto-generated method stub
            // arg1 = year
            // arg2 = month
            // arg3 = day
            showDate1(arg1, arg2 + 1, arg3);
        }
    };

    private DatePickerDialog.OnDateSetListener myDateListener2 = new DatePickerDialog.OnDateSetListener(){

        public void onDateSet(DatePicker arg0, int arg1, int arg2, int arg3) {
            // TODO Auto-generated method stub
            // arg1 = year
            // arg2 = month
            // arg3 = day
            showDate1(arg1, arg2 + 1, arg3);
        }
    };

    private void showDate(int year, int month, int day) {
        e1.setText(new StringBuilder().append(day).append("/")
                .append(month).append("/").append(year));
    }

    private void showDate1(int year, int month, int day) {
        e2.setText(new StringBuilder().append(day).append("/")
                .append(month).append("/").append(year));
    }

    private void showDate2(int year, int month, int day) {
        e4.setText(new StringBuilder().append(day).append("/")
                .append(month).append("/").append(year));
    }
}
