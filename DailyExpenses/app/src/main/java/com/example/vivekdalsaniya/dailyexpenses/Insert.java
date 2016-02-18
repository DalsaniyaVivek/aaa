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
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import java.util.Calendar;

/**
 * Created by Vivek Dalsaniya on 1/5/2016.
 */
public class Insert extends AppCompatActivity {
    private DatePicker datePicker;
    private Calendar calendar;
    private int year, month, day;
    private EditText e3;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.insert);
        ActionBar actionbar = getSupportActionBar();
        actionbar.setTitle("Insert");
        calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);
        TextView t1=(TextView)findViewById(R.id.name1);
        final EditText e1=(EditText)findViewById(R.id.name);
        TextView t2=(TextView)findViewById(R.id.price1);
        final EditText e2=(EditText)findViewById(R.id.price);
        TextView b2=(TextView)findViewById(R.id.date1);
        e3=(EditText)findViewById(R.id.date);
        Button b1=(Button)findViewById(R.id.button);
        showDate(year,month+1,day);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean didItWork =true;
                try {
                    String date = e3.getText().toString();
                    String name = e1.getText().toString();
                    String prices = e2.getText().toString();
                    datacode entry = new datacode(Insert.this);
                    entry.open();
                    entry.createEntry(date,name,prices);
                    entry.close();
                }
                catch (Exception e)
                {
                    didItWork=false;
                }
                finally {
                    if(didItWork)
                    {
                        Dialog d = new Dialog(Insert.this);
                        d.setTitle("Heck Yea!");
                        TextView tv=new TextView(Insert.this);
                        tv.setText("Success");
                        d.setContentView(tv);
                        d.show();
                    }



                }
            }
        });

    }
    public void setDate(View view) {
        showDialog(999);
        Toast.makeText(getApplicationContext(), "ca", Toast.LENGTH_SHORT)
                .show();
    }



    protected Dialog onCreateDialog(int id) {
        // TODO Auto-generated method stub
        if (id == 999) {
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
        e3.setText(new StringBuilder().append(day).append("/")
                .append(month).append("/").append(year));
    }


}
