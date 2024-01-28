package com.example.CashFlowFrontend;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;
import java.util.concurrent.ExecutorService;

public class RecViewAdaptorCurrencies extends RecyclerView.Adapter<RecViewAdaptorCurrencies.CurrencyViewHolder>{
//THIS IS THE RECYCLER VIEW ADAPTER
    Context ctx;

    List<Currency> data;

    interface CurrencyListListener{ //for the clicks

        public void currencyClicked(Currency currency);

    }

    CurrencyListListener listener;

    public void setListener(CurrencyListListener listener) {
        this.listener = listener;
    }

    public RecViewAdaptorCurrencies(Context ctx, List<Currency> data) {
        this.ctx = ctx;
        this.data = data;
    }



    @NonNull
    @Override
    public CurrencyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(ctx).inflate(R.layout.currency_row,parent,false);//inflating each row from this context

        return new CurrencyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CurrencyViewHolder holder, int position) {

        holder.txtName.setText(data.get(position).getMainCurrency());//the name is the each currency at that position

        ExecutorService srv = ((CashSwing)((Activity)ctx).getApplication()).srv;

        double exchangeRate = data.get(position).getExchangeRate(); //FOR THE RATE
        String exchangeRateString = String.valueOf(exchangeRate); //FOR THE NAME

        holder.txtRate.setText(exchangeRateString);

        holder.row.setOnClickListener(v -> { //the on click listener for going to do info page

            if (listener != null){

                listener.currencyClicked(data.get(holder.getAdapterPosition()));

            }




        });




    }

    @Override
    public int getItemCount() {
        return data.size();
    }


    class CurrencyViewHolder extends RecyclerView.ViewHolder{ //this is the viewHolder for the recycler view

        TextView txtName;

        TextView txtRate;

        ConstraintLayout row;

        public CurrencyViewHolder(@NonNull View itemView) { //initializes the row name and rate
            super(itemView);

            txtName = itemView.findViewById(R.id.txtCurName);
            txtRate = itemView.findViewById(R.id.txtCurRate);
            row = itemView.findViewById(R.id.currencyContainer);
        }
    }



}
