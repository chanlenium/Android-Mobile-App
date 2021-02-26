package com.example.fragmentsexample;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class ViewPagerAdapter extends FragmentStateAdapter {
    public ViewPagerAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    public ViewPagerAdapter(@NonNull Fragment fragment) {
        super(fragment);
    }

    public ViewPagerAdapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {  // position indicates fragments index
        Fragment fragment;
        if(position == 0){
            fragment = new Fragment1();
        }else{
            if(position == 1)
                fragment = new Fragment2();
            else
                fragment = new Fragment3();
        }
        return fragment;
    }

    @Override
    public int getItemCount() {
        // return number of fragments
        return 3;
    }
}
