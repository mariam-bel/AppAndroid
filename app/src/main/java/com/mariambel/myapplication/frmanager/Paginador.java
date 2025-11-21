package com.mariambel.myapplication.frmanager;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.mariambel.myapplication.mainfr.Elementos;
import com.mariambel.myapplication.mainfr.Materiales;

public class Paginador extends FragmentPagerAdapter {
    private final Context mContext;

    public Paginador(Context context, FragmentManager fm) {
        super(fm);
        mContext = context;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new Materiales();
            case 1:
                return new Elementos();
        }
        return null;
    }

    @Override
    public int getCount() {
        return 2;
    }
}
