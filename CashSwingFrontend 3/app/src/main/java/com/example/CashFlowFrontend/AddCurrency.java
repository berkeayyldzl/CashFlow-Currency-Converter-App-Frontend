package com.example.CashFlowFrontend;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.MutableLiveData;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;
import java.util.concurrent.ExecutorService;

public class AddCurrency extends AppCompatActivity {
//THIS CLASS IS FOR ADDING A NEW CURRENCY TO OUR DATABASE
    private CurrencyRepository repo; //Our repo

    private EditText nameOfCurrency, rateOfCurrency, maxOfCurrency, minOfCurrency;


    public boolean onCreateOptionsMenu(Menu menu) { //Inflating menu bar
        getMenuInflater().inflate(R.menu.menu_add_currency, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) { //for the back button
        if (item.getItemId() == R.id.mnGoBack) {

            Intent intent = new Intent(AddCurrency.this, MainActivity.class);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) { //starting the activity
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_currency);

        Toolbar toolbar = findViewById(R.id.toolbar); //for the toolbar
        setSupportActionBar(toolbar);

        nameOfCurrency = findViewById(R.id.nameOfCurrency);
        rateOfCurrency = findViewById(R.id.rateOfCurrency);
        maxOfCurrency = findViewById(R.id.maxOfCurrency);
        minOfCurrency = findViewById(R.id.minOfCurrency);

        repo = new CurrencyRepository();

        Button btnAddCur = findViewById(R.id.btnAddCur); //add currency button and its listener
        btnAddCur.setOnClickListener(v -> {
            String name = nameOfCurrency.getText().toString();
            String rateText = rateOfCurrency.getText().toString();
            String maxText = maxOfCurrency.getText().toString();
            String minText = minOfCurrency.getText().toString();

            if (name.isEmpty() || rateText.isEmpty() || maxText.isEmpty() || minText.isEmpty()) { //if the fields are empty, create an error message
                Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
                return;
            }

            double rate = Double.parseDouble(rateText);
            double max = Double.parseDouble(maxText);
            double min = Double.parseDouble(minText);

            ExecutorService srv = ((CashSwing) getApplication()).srv;
            repo.save(srv, name, rate, min, max); //saves the new currency

            Toast.makeText(this, "Currency " + name + " added successfully.", Toast.LENGTH_SHORT).show(); //displays the message
        });
    }

}