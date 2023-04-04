package com.example.A36154_TH.Fragment;

import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.A36154_TH.Adapter.KhoanThuAdapter;
import com.example.A36154_TH.Model.KhoanChi;
import com.example.A36154_TH.Model.KhoanThu;
import com.example.A36154_TH.R;
import com.example.A36154_TH.SQLite.Database;

import java.util.ArrayList;

public class FMKT extends Fragment {

    public FMKT(){
    }
    private View rootview;
    KhoanThuAdapter adapter;
    ArrayList<KhoanThu> list;
    ListView lv_khoanthu;
    Database database;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        rootview = inflater.inflate(R.layout.fragment_khoan_thu, container,false);
        initView();
        return rootview;
    }
    private void initView(){
        database = new Database(getActivity());
        lv_khoanthu = rootview.findViewById(R.id.lv_khoanthu);
        list = new ArrayList<>();
        adapter = new KhoanThuAdapter(getActivity(),list);
        getData();
        lv_khoanthu.setAdapter(adapter);
    }
    public void getData(){
        Cursor datakhoanthu = database.GetData("SELECT * FROM THU");
        list.clear();
        while (datakhoanthu.moveToNext()){
            String a = datakhoanthu.getString(1);
            int b = datakhoanthu.getInt(3);
            list.add(new KhoanThu(a,b));
        }
        adapter.notifyDataSetChanged();

    }
}
