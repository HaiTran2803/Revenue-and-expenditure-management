package com.example.A36154_TH;

import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.ListView;
import android.widget.TableLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.A36154_TH.Adapter.PagerAdapter1;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

public class flagment2 extends Fragment {
    public flagment2(){

    }
    private View rootview;
    TabLayout tl;
    PagerAdapter1 paperadapter;
    ViewPager paper;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootview = inflater.inflate(R.layout.flagment2,container,false);
        initView();
        return rootview;
    }
    public void initView(){
        paperadapter = new PagerAdapter1(getActivity().getSupportFragmentManager());
        tl = rootview.findViewById(R.id.tab_layout1);
        paper = rootview.findViewById(R.id.view_paper1);
        paper.setAdapter(paperadapter);
        tl.setupWithViewPager(paper);

    }
}
