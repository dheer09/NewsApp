package com.example.android.newsapp;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class CategoryAdapter extends FragmentStateAdapter {

    public CategoryAdapter(@NonNull FragmentActivity fragmentActivity){super(fragmentActivity);}

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        if(position == 0){
            return new TrendingFragment();
        }
        if(position == 1){
            return new BusinessFragment();
        }
        if(position == 2){
            return new TechnologyFragment();
        }
        if(position == 3){
            return new EntertainmentFragment();
        }
        if(position == 1){
            return new SportsFragment();
        }
        if(position == 1){
            return new ScienceFragment();
        }
        if(position == 1){
            return new HealthFragment();
        }
        return null;
    }

    @Override
    public int getItemCount() {
        return 7;
    }
}
