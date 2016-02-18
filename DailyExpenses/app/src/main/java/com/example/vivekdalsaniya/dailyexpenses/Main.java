package com.example.vivekdalsaniya.dailyexpenses;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

/**
 * Created by Vivek Dalsaniya on 10/27/2015.
 */
public class Main extends Activity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        Toolbar actionBar = (Toolbar) findViewById(R.id.actionBar);
        actionBar.setBackgroundColor(Color.parseColor("#800080"));
        actionBar.setTitle("Daily Expenses");
        actionBar.setTitleTextColor(Color.parseColor("#ffffff"));
        CardView c1 = (CardView) findViewById(R.id.card_view);
        registerForContextMenu(c1);
        c1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i1 = new Intent(Main.this, Menu.class);
                startActivity(i1);
            }
        });
        CardView c2 = (CardView) findViewById(R.id.card_view50);
        c2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i2=new Intent(Main.this,Edit.class);
                startActivity(i2);
            }
        });
        CardView c3 = (CardView) findViewById(R.id.card_view3);
        CardView c4= (CardView) findViewById(R.id.card_view4);
    }
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo)
    {
        super.onCreateContextMenu(menu, v, menuInfo);
        menu.setHeaderTitle("Select The Action");
        menu.add(0, v.getId(), 0, "Insert");//groupId, itemId, order, title
        menu.add(0, v.getId(), 0, "Edit");
        menu.add(0,v.getId(),0,"Find");
        menu.add(0, v.getId(), 0, "Delete");
        menu.add(0, v.getId(), 0, "Clear");
        menu.add(0, v.getId(), 0, "View1");
    }
    public boolean onContextItemSelected(MenuItem item){
        if(item.getTitle()=="Insert"){
            Toast.makeText(getApplicationContext(),"Inserting....",Toast.LENGTH_LONG).show();
            Intent i1=new Intent(Main.this,Insert.class);
            startActivity(i1);
        }else if(item.getTitle()=="Edit"){
            Toast.makeText(getApplicationContext(),"Editing....",Toast.LENGTH_LONG).show();
            Intent i2=new Intent(Main.this,Edit.class);
            startActivity(i2);
        }else if(item.getTitle()=="Find"){
            Intent i6=new Intent(Main.this,Find.class);
            startActivity(i6);
        }else if(item.getTitle()=="Delete"){
            Toast.makeText(getApplicationContext(),"Deleting....",Toast.LENGTH_LONG).show();
            Intent i3=new Intent(Main.this,Delete.class);
            startActivity(i3);
        }else if(item.getTitle()=="Clear"){
            Toast.makeText(getApplicationContext(),"Clearing....",Toast.LENGTH_LONG).show();
            Intent i4=new Intent(Main.this,Clear.class);
            startActivity(i4);
        }else if(item.getTitle()=="View1"){
            Toast.makeText(getApplicationContext(),"Please Wait....",Toast.LENGTH_LONG).show();
            Intent i5=new Intent(Main.this, View1.class);
            startActivity(i5);
        }else{
            return false;
        }

        return true;
    }

}


 /*  PopupMenu popup = new PopupMenu(Main.this,c1);
                popup.getMenuInflater().inflate(R.menu.pop_up, popup.getMenu());
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    public boolean onMenuItemClick(MenuItem item) {
                        Toast.makeText(Main.this, "You Clicked : " + item.getTitle(), Toast.LENGTH_SHORT).show();
                        return true;
                    }
                });

                popup.show();*/