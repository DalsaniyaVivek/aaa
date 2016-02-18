package com.example.vivekdalsaniya.dailyexpenses;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.Calendar;

/**
 * Created by Vivek Dalsaniya on 1/13/2016.
 */
public class Find extends Activity {
    TextView t1, t2, t3, t4, t5, t6, t7, t8;
    EditText e1,e2,e3,e4;
    Button b1, b2, b3, b4;
    private DatePicker datePicker;
    private Calendar calendar;
    private int year, month, day;
    int x,y;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.find);
        Toolbar actionBar = (Toolbar) findViewById(R.id.actionBar);
        actionBar.setBackgroundColor(Color.parseColor("#800080"));
        actionBar.setTitle("Find");
        actionBar.setTitleTextColor(Color.parseColor("#ffffff"));
        t1 = (TextView) findViewById(R.id.ftextView2);
        t2 = (TextView) findViewById(R.id.ftextView3);
        t3 = (TextView) findViewById(R.id.ftextView4);
        t4 = (TextView) findViewById(R.id.ftextView5);
        t5 = (TextView) findViewById(R.id.ftextView6);
        t6 = (TextView) findViewById(R.id.ftextView7);

        e1 = (EditText)findViewById(R.id.feditText);
        e2 = (EditText)findViewById(R.id.feditText2);
        e3 = (EditText)findViewById(R.id.feditText3);

        b1 = (Button) findViewById(R.id.fbutton1);
        b2 = (Button) findViewById(R.id.fbutton2);
        b3 = (Button) findViewById(R.id.fbutton3);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 dataview d2=new dataview();
                d2.setById();
                Intent i2=new Intent(Find.this,dataview.class);
                i2.putExtra("y",e1.getText().toString());
                startActivity(i2);
            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dataview d1=new dataview();
                d1.setByName();
                Intent i1=new Intent(Find.this,dataview.class);
                i1.putExtra("x",e2.getText().toString());
                startActivity(i1);
            }
        });
        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dataview d3=new dataview();
                d3.setByPrice();
                Intent i3=new Intent(Find.this,dataview.class);
                i3.putExtra("z",e3.getText().toString());
                startActivity(i3);
            }
        });

    }

}
