package com.example.CashFlowFrontend;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;


public class ConverterFragment extends Fragment {
//THIS CLASS IS FOR THE CURRENCY PART
    private Spinner spinner;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {



        View view = inflater.inflate(R.layout.fragment_converter, container, false); //inflating the spinner and the fragment_converter.xml
        spinner = view.findViewById(R.id.spChoose);

        Handler CurrencyHandler = new Handler(msg -> { //handler to get currency names from the database to show in spinner
            List<Currency> currencies = (List<Currency>) msg.obj;

            List<String> currencyNames = new ArrayList<>();
            for (Currency currency : currencies) {
                currencyNames.add(currency.getMainCurrency());
            }

            ArrayAdapter<String> adapter = new ArrayAdapter<>( //the spinner adaptor
                    requireContext(),
                    android.R.layout.simple_spinner_item,
                    currencyNames
            );
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);//setting the type of the spinner

            spinner.setAdapter(adapter);

            Button btnConvert = view.findViewById(R.id.btnConvert);

            btnConvert.setOnClickListener(v -> { //listener for the convert button
                EditText txtValue = view.findViewById(R.id.txtValue);
                String valueStr = txtValue.getText().toString();


                if (!valueStr.isEmpty()) { //if the value is valid,
                    double value = Double.parseDouble(valueStr);
                    int selectedCurrencyIndex = spinner.getSelectedItemPosition();//gets the position on the spinner
                    double selectedCurrencyRate = currencies.get(selectedCurrencyIndex).getExchangeRate();//gets the exchange rate of the wanted position
                    double result = value * selectedCurrencyRate; //multiplies the value with the rate

                    TextView txtCnvrtSuccess = view.findViewById(R.id.txtCnvrtSuccess);
                    txtCnvrtSuccess.setVisibility(View.VISIBLE);

                    String resultText = "The result is: " + String.format("%.2f", result); //writes the result on the screen
                    txtCnvrtSuccess.setText(resultText);
                } else {
                    TextView txtCnvrtSuccess = view.findViewById(R.id.txtCnvrtSuccess);//if the value is non-valid,toasts an error message
                    txtCnvrtSuccess.setVisibility(View.INVISIBLE);
                    Toast.makeText(requireContext(), "Please enter a value", Toast.LENGTH_SHORT).show();
                }
            });


            return true;
        });


        CurrencyRepository repo = new CurrencyRepository(); //getting the repo
        ExecutorService srv = ((CashSwing) requireActivity().getApplication()).srv;
        repo.getAllCurrencies(srv, CurrencyHandler);



        return view;
    }
}