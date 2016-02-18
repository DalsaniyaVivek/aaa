package com.example.vivekdalsaniya.dailyexpenses;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

/**
 * Created by Vivek Dalsaniya on 1/8/2016.
 */
public class Function extends Activity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.function);
        Toolbar actionBar = (Toolbar) findViewById(R.id.actionBar);
        actionBar.setBackgroundColor(Color.parseColor("#D070D0"));

    }
}
