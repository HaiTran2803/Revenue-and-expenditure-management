package com.example.A36154_TH.Adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.A36154_TH.Fragment.FMKC;
import com.example.A36154_TH.Fragment.FMLC;

public class PagerAdapter2 extends FragmentStatePagerAdapter {
    public PagerAdapter2(@NonNull FragmentManager fm) {
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int i) {
        if (i == 0){
            return new FMKC();
        }else if (i == 1){
            return new FMLC();
        }else {
            return null;
        }
    }

    @Override
    public int getCount() {
        return 2;
    }
    public CharSequence getPageTitle(int position){
        switch (position){
            case 0:
                return "Khoản Chi";
            case 1:
                return "Loại Chi";
        }
        return null;
    }
}
