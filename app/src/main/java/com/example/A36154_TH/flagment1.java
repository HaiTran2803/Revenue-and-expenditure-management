package com.example.A36154_TH;

import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.A36154_TH.Adapter.ThongKeChiAdapter;
import com.example.A36154_TH.Adapter.ThongKeThuAdapter;
import com.example.A36154_TH.Model.ThongKeChi;
import com.example.A36154_TH.Model.ThongKeThu;
import com.example.A36154_TH.SQLite.Database;

import java.util.ArrayList;
import java.util.List;

public class flagment1 extends Fragment {
    public flagment1(){

    }
    private View rootview;
    ArrayList<ThongKeThu> list;
    ArrayList<ThongKeChi> list1;
    ThongKeThuAdapter adapter;
    ThongKeChiAdapter adapter1;
    ListView lv_thongkethu;
    ListView lv_thongkechi;
    Database database;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootview = inflater.inflate(R.layout.flagment1,container,false);
        initView();
        return rootview;
    }
    public void initView(){
        list = new ArrayList<>();
        lv_thongkethu = rootview.findViewById(R.id.lv_thongkethu);
        database = new Database(getActivity());
        adapter = new ThongKeThuAdapter(getActivity(),R.layout.list_item_thong_ke_thu,list);
        Cursor datathongkethu = database.GetData("SELECT * FROM THU");
        list.clear();
        while (datathongkethu.moveToNext()){
            String a = datathongkethu.getString(0);
            String b = datathongkethu.getString(1);
            String c = datathongkethu.getString(2);
            list.add(new ThongKeThu(a,b,c));
        }
        adapter.notifyDataSetChanged();
        lv_thongkethu.setAdapter(adapter);

        // chi
        list1 = new ArrayList<>();
        lv_thongkechi = rootview.findViewById(R.id.lv_thongkechi);
        database = new Database(getActivity());
        adapter1 = new ThongKeChiAdapter(getActivity(),R.layout.list_item_thong_ke_chi,list1);
        Cursor datathongkechi = database.GetData("SELECT * FROM CHI");
        list1.clear();
        while (datathongkechi.moveToNext()){
            String a = datathongkechi.getString(0);
            String b = datathongkechi.getString(1);
            String c = datathongkechi.getString(2);
            list1.add(new ThongKeChi(a,b,c));
        }
        adapter1.notifyDataSetChanged();
        lv_thongkechi.setAdapter(adapter1);

    }
}
