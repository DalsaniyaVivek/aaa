package com.example.vivekdalsaniya.dailyexpenses;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.SQLException;

/**
 * Created by Vivek Dalsaniya on 1/5/2016.
 */
public class Delete extends AppCompatActivity {
    EditText e1;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.delete);
        android.support.v7.app.ActionBar actionBar=getSupportActionBar();
        actionBar.setTitle("Delete");
        TextView t1=(TextView)findViewById(R.id.tv1);
        TextView t2=(TextView)findViewById(R.id.dt2);
        TextView t3=(TextView)findViewById(R.id.dt3);
        e1=(EditText)findViewById(R.id.et1);
        Button b1=(Button)findViewById(R.id.button3);
        t2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i1=new Intent(Delete.this,Find.class);
                startActivity(i1);
            }
        });
        t3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i2=new Intent(Delete.this, View1.class);
                startActivity(i2);
            }
        });
    }




    public void open(View view){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setMessage("Are you sure,You wanted to make decision");

        alertDialogBuilder.setPositiveButton("yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface arg0, int arg1) {
                Toast.makeText(Delete.this, "You clicked yes button", Toast.LENGTH_LONG).show();
                String sRow1 = e1.getText().toString();
                long lRow1 = Long.parseLong(sRow1);
                datacode ex1 = new datacode(Delete.this);
                try {
                    ex1.open();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                ex1.deleteEntry(lRow1);
                ex1.close();
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
}
