package com.example.vivekdalsaniya.dailyexpenses;

import android.app.ActionBar;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.sql.SQLException;
import java.util.Calendar;

/**
 * Created by Vivek Dalsaniya on 1/5/2016.
 */
public class Edit extends AppCompatActivity {
    EditText e3,e4;
    private DatePicker datePicker;
    private Calendar calendar;
    private int year, month, day;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit);
        android.support.v7.app.ActionBar actionBar=getSupportActionBar();
        actionBar.setTitle("Edit");
        TextView t1=(TextView)findViewById(R.id.name1);
        final EditText e1=(EditText)findViewById(R.id.name);
        TextView t2=(TextView)findViewById(R.id.price1);
        final EditText e2=(EditText)findViewById(R.id.price);
        TextView t3=(TextView)findViewById(R.id.id1);
        e3=(EditText)findViewById(R.id.id2);
        e4=(EditText)findViewById(R.id.temp3);
        final Button b1=(Button)findViewById(R.id.button);
        Button b2=(Button)findViewById(R.id.temp5);
        ImageButton i1=(ImageButton)findViewById(R.id.imageButton);
        i1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(Edit.this,Find.class);
                startActivity(i);
            }
        });
   /*     TextView t4=(TextView)findViewById(R.id.temp1);
        TextView t5=(TextView)findViewById(R.id.temp2);
        t4.setOnClickListener(new View1.OnClickListener() {
            @Override
            public void onClick(View1 v) {
                Intent i1=new Intent(Edit.this,Find.class);
                startActivity(i1);
            }
        });
        t5.setOnClickListener(new View1.OnClickListener() {
            @Override
            public void onClick(View1 v) {
                Intent i2=new Intent(Edit.this, com.example.vivekdalsaniya.dailyexpenses.View1.class);
                startActivity(i2);
            }
        });   */
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s=e3.getText().toString();
                long l=Long.parseLong(s);
                datacode hon=new datacode(Edit.this);
                try {
                    hon.open();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                String returnedName=hon.getName(l);
                String returnedPrices=hon.getPrices(l);
                String returnedDate=hon.getDate(l);
                hon.close();
                e1.setText(returnedName);
                e2.setText(returnedPrices);
                e4.setText(returnedDate);
                String x[]=returnedDate.split("/");
             //   datePicker.init(Integer.parseInt(x[0]),Integer.parseInt(x[1]),Integer.parseInt(x[2]),null);
                b1.setEnabled(true);
            }
        });

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mName=e1.getText().toString();
                String mPrices=e2.getText().toString();
                String sRow=e3.getText().toString();
                long lRow=Long.parseLong(sRow);
                datacode ex=new datacode(Edit.this);
                try {
                    ex.open();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                ex.updateEntry(lRow,mName,mPrices);
                ex.close();

            }
        });
     /*   b2.setOnClickListener(new View1.OnClickListener() {
            @Override
            public void onClick(View1 v) {
                String s=e3.getText().toString();
                long l=Long.parseLong(s);
                datacode hon=new datacode(Edit.this);
                try {
                    hon.open();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                String returnedName=hon.getName(l);
                String returnedPrices=hon.getPrices(l);
                hon.close();
                e1.setText(returnedName);
                e2.setText(returnedPrices);
            }
        });*/
    }
    public void setDate(View view) {
        showDialog(992);
        Toast.makeText(getApplicationContext(), "ca", Toast.LENGTH_SHORT)
                .show();
    }

    protected Dialog onCreateDialog(int id) {
        // TODO Auto-generated method stub
        if (id == 992) {
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
            showDate(arg1, arg2+1, arg3);
        }
    };

    private void showDate(int year, int month, int day) {
        e4.setText(new StringBuilder().append(day).append("/")
                .append(month).append("/").append(year));
    }
}
       /*<TextView
                android:layout_height="wrap_content"
                android:layout_width="wrap_content"
                android:textSize="15dp"
                android:text="Go to Find Menu"
                android:layout_alignParentRight="true"
                android:layout_marginRight="10dp"
                android:layout_gravity="right"
                android:id="@+id/temp1"
                android:layout_marginTop="5dp"
                android:layout_marginBottom="7dp"
                />
            <TextView
                android:layout_width="wrap_content"
                android:layout_height="wrap_content"
                android:textSize="15dp"
                android:text="Go to View1 Menu"
                android:layout_marginRight="10dp"
                android:layout_gravity="right"
                android:id="@+id/temp2"
                android:layout_marginBottom="15dp"/>     */

