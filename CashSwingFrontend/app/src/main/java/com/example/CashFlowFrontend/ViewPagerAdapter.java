package com.example.CashFlowFrontend;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;


public class ViewPagerAdapter extends FragmentStateAdapter {
//THIS IS THE VIEW PAGER ADAPTER, MAKES IT POSSIBLE FOR TO SWIPE BETWEEN TABS
    public ViewPagerAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) { //we have 2 fragments

        switch (position){
            case 0:
                return new CurrencyListFragment();
            case 1:
                return new ConverterFragment();

            default:
                return new CurrencyListFragment();
        }


    }

    @Override
    public int getItemCount() {
        return 2;
    }





}
