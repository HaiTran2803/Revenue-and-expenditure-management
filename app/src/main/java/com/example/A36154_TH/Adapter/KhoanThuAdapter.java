package com.example.A36154_TH.Adapter;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.A36154_TH.MainActivity;
import com.example.A36154_TH.Model.KhoanChi;
import com.example.A36154_TH.Model.KhoanThu;
import com.example.A36154_TH.R;
import com.example.A36154_TH.SQLite.Database;

import java.util.ArrayList;
import java.util.List;

public class KhoanThuAdapter extends BaseAdapter {
    Context context;
    List<KhoanThu> khoanThuList;

    public KhoanThuAdapter(Context context, ArrayList<KhoanThu> khoanThuList) {
        this.context = context;
        this.khoanThuList = khoanThuList;
    }

    @Override
    public int getCount() {
        return khoanThuList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }
    private class ViewHolder{
        TextView txtkhoanthu;
        ImageView khoanthusua, khoanthuxoa;
    }

    @Override
    public View getView(final int i, View view, ViewGroup parent) {
        final ViewHolder holder;
        if (view == null){
            holder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.list_item_khoan_thu, null);
            holder.txtkhoanthu = (TextView)view.findViewById(R.id.txtkhoanthu);
            holder.khoanthusua = (ImageView) view.findViewById(R.id.khoanthusua);
            holder.khoanthuxoa = (ImageView)view.findViewById(R.id.khoanthuxoa);
            view.setTag(holder);
        }else {
            holder = (ViewHolder) view.getTag();
        }
        final KhoanThu kt = khoanThuList.get(i);
        holder.txtkhoanthu.setText(kt.getKhoanthu());

        holder.khoanthusua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog dialog = new Dialog(context);
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setContentView(R.layout.dialog_sua);

                final EditText edtsua = (EditText) dialog.findViewById(R.id.edt_sua);
                Button btnsua = (Button) dialog.findViewById(R.id.btn_sua);
                edtsua.setText(kt.getKhoanthu());
                btnsua.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String a = edtsua.getText().toString();
                        int b = kt.getId();
                        if (a.isEmpty()){
                            Toast.makeText(context, "vui long khong de trong ",Toast.LENGTH_SHORT).show();
                        }else {
                            Database database = new Database(context);
                            database.SendData("UPDATE THU SET KHOANTHU = '"+a+"' WHERE IDTHU = "+b+"  ");
                            Toast.makeText(context , " update thanh cong",Toast.LENGTH_SHORT).show();
                            dialog.dismiss();
                            ((MainActivity)context).recreate();
                        }
                    }
                });
                dialog.show();
            }
        });
        holder.khoanthuxoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog dialog1 = new Dialog(context);
                dialog1.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog1.setContentView(R.layout.dialog_xoa);

                Button btn_huyxoa = (Button) dialog1.findViewById(R.id.btn_huyboxoa);
                Button btn_chapnhanxoa = (Button) dialog1.findViewById(R.id.btn_chapnhanxoa);
                btn_huyxoa.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog1.dismiss();
                    }
                });
                btn_chapnhanxoa.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int a = kt.getId();
                        Database database = new Database(context);
                        database.SendData("DELETE FROM THU WHERE IDTHU = "+a+" ");
                        Toast.makeText(context , " delete thanh cong",Toast.LENGTH_SHORT).show();
                        dialog1.dismiss();
                        ((MainActivity)context).recreate();
                    }
                });
                dialog1.show();
            }
        });

        return view;
    }
}
