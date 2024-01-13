package com.example.CashFlowFrontend;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class CurrencyInfo extends AppCompatActivity {
//THIS CLASS IS FOR THE CURRENCY INFO ACTIVITY
    private TextView txtCurInfoName, txtCurInfoMin, txtCurInfoMax, txtCurInfoRate;

    public boolean onCreateOptionsMenu(Menu menu) { //inflating the menu bar
        getMenuInflater().inflate(R.menu.menu_currency_info, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.mnGoToList) {//for the return button

            Intent intent = new Intent(CurrencyInfo.this, MainActivity.class);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_currency_info);


        Toolbar toolbar = findViewById(R.id.toolbar2);
        setSupportActionBar(toolbar);

        txtCurInfoName = findViewById(R.id.txtCurInfoName);
        txtCurInfoMin = findViewById(R.id.txtCurInfoMin);
        txtCurInfoMax = findViewById(R.id.txtCurInfoMax);
        txtCurInfoRate = findViewById(R.id.txtCurInfoRate);

        Intent intent = getIntent();
        if (intent != null) { //getting all the wanted information for the info, from the list fragment
            String currencyName = intent.getStringExtra("currencyName");
            double min = intent.getDoubleExtra("minValue", 0.0);
            double max = intent.getDoubleExtra("maxValue", 0.0);
            double rate = intent.getDoubleExtra("currencyRate",0.0);

            // setting the extracted data to TextViews on the info activity
            txtCurInfoName.setText(currencyName);
            txtCurInfoMin.setText(String.valueOf(min));
            txtCurInfoMax.setText(String.valueOf(max));
            txtCurInfoRate.setText(String.valueOf(rate));
        }
    }
}