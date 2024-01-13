package com.example.CashFlowFrontend;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;
import java.util.concurrent.ExecutorService;


public class CurrencyListFragment extends Fragment {
//THIS CLASS IS FOR THE LIST VIEW ON THE MAIN ACTIVITY
    private RecyclerView recyclerView;
    private RecViewAdaptorCurrencies adapter;

    private String currencyName;

    private int id;



    Handler CurrencyHandler = new Handler(new Handler.Callback() {//This handler is for passing the data between info activity,as the listener opens up the correct currency
        @Override
        public boolean handleMessage(@NonNull Message msg) {
            List<Currency> currencies = (List<Currency>) msg.obj;
            adapter = new RecViewAdaptorCurrencies(requireContext(), currencies);
            recyclerView.setAdapter(adapter);

            adapter.setListener(currency -> {
                Intent intent = new Intent(getActivity(), CurrencyInfo.class);
                intent.putExtra("currencyName", currency.getMainCurrency());
                intent.putExtra("minValue", currency.getMin());
                intent.putExtra("maxValue", currency.getMax());
                intent.putExtra("currencyRate",currency.getExchangeRate());
                startActivity(intent);
            });
            return true;
        }
    });




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) { //for inflating the fragment, and initializing the rec view
        View view = inflater.inflate(R.layout.fragment_currency_list, container, false);

        recyclerView = view.findViewById(R.id.recCurrency);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));


        CurrencyRepository repo = new CurrencyRepository();

        ExecutorService srv=((CashSwing)getActivity().getApplication()).srv;

        repo.getAllCurrencies(srv,CurrencyHandler);//for getting all the currencies from the database

        return view;

    }




}