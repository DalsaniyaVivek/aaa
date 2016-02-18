package com.example.vivekdalsaniya.dailyexpenses;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import java.sql.SQLException;

/**
 * Created by Vivek Dalsaniya on 1/5/2016.
 */

public class dataview extends Activity {

    private Toolbar supportActionBar;
    TextView tv;
    static String temp=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.database);
        Toolbar actionBar = (Toolbar) findViewById(R.id.actionBar);
        actionBar.setTitle("ProTaxi Passageiro");
        actionBar.setSubtitle("Cadastro");
        actionBar.setBackgroundColor(Color.parseColor("#800080"));
        actionBar.setTitleTextColor(Color.parseColor("#846A1A"));
        setSupportActionBar(actionBar);
        tv=(TextView)findViewById(R.id.xxx);
        if(temp.equals("name")){
            Intent i = getIntent();
            String xx = i.getExtras().getString("x");
            datacode info1 = new datacode(this);
            try {
                info1.open();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            String data = info1.FindbyName(xx);
            info1.close();
            tv.setText(data);
            temp=null;}
         else if(temp.equals("id")){
            Intent i=getIntent();
            String xx=i.getExtras().getString("y");
            datacode info2 = new datacode(this);
            try {
                info2.open();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            String data = info2.FindbyId(xx);
            info2.close();
            tv.setText(data);
            temp=null;
        }
        else if(temp.equals("price")){
                Intent i=getIntent();
                String xx=i.getExtras().getString("z");
                datacode info2 = new datacode(this);
                try {
                    info2.open();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                String data = info2.FindbyPrice(xx);
                info2.close();
                tv.setText(data);
                temp=null;
        }
        else if(temp.equals("full1")){
            datacode info3 = new datacode(this);
            try {
                info3.open();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            String data = info3.getData();
            info3.close();
            tv.setText(data);
            temp=null;
        }
        else if(temp.equals("date")){
            Intent i=getIntent();
            String xxx=i.getExtras().getString("a");
            datacode info4 = new datacode(this);
            try {
                info4.open();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            String data = info4.getDataByDate(xxx);
            info4.close();
            tv.setText(data);
            temp=null;
        }
        else if(temp.equals("month")){
            Intent i=getIntent();
            String xxx=i.getExtras().getString("b");
            String yyy=i.getExtras().getString("c");
            datacode info5 = new datacode(this);
            try {
                info5.open();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            String data = info5.getDataByMonth(xxx,yyy);
            info5.close();
            tv.setText(data);
            temp=null;
        }

    }
    public void setByName(){
        temp="name";
    }
    public void setById(){
        temp="id";
    }
    public void setByPrice(){
        temp="price";
    }
    public void viewFull1(){temp="full1";}
    public void specDate(){ temp="date"; }
    public void setMonth(){temp="month";
    }
    public void setSupportActionBar(Toolbar supportActionBar) {
        this.supportActionBar = supportActionBar;
    }



}
