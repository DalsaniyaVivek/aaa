package com.example.vivekdalsaniya.dailyexpenses;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

/**
 * Created by Vivek Dalsaniya on 1/11/2016.
 */
public class Menu extends ListActivity {

    String classes[]={"Insert","Edit","Find","Delete","Clear","View1","Calculation"
    };
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setListAdapter(new ArrayAdapter<String>(Menu.this,android.R.layout.simple_list_item_1,classes));
    }

    protected void onListItemClick(ListView l,View v,int position,long id) {
        super.onListItemClick(l,v,position,id);
        String cheese=classes[position];
        try{
            Class ourClass=Class.forName("com.example.vivekdalsaniya.dailyexpenses."+cheese);
            Intent ourIntent = new Intent(Menu.this,ourClass);
            startActivity(ourIntent);
        }catch (ClassNotFoundException e){
            e.printStackTrace();
        }
    }

}
