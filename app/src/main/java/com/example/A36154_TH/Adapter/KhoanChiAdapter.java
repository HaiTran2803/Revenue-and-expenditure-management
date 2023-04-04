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

import androidx.fragment.app.FragmentActivity;

import com.example.A36154_TH.MainActivity;
import com.example.A36154_TH.Model.KhoanChi;
import com.example.A36154_TH.R;
import com.example.A36154_TH.SQLite.Database;

import java.util.ArrayList;
import java.util.List;

public class KhoanChiAdapter extends BaseAdapter {

    Context context;
    int layout;
    List<KhoanChi> khoanChiList;




    public KhoanChiAdapter(Context context, int layout, ArrayList<KhoanChi> khoanChiList) {
        this.context = context;
        this.layout = layout;
        this.khoanChiList = khoanChiList;
    }

    @Override
    public int getCount() {
        return khoanChiList.size();
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
        TextView txtkhoanchi;
        ImageView khoanchisua, khoanchixoa;
    }

    @Override
    public View getView(int i, View view, ViewGroup parent) {
        ViewHolder holder;
        if (view == null){
            holder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater)
                    context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(layout,null);
            holder.txtkhoanchi = (TextView)view.findViewById(R.id.txtkhoanchi);
            holder.khoanchisua = (ImageView) view.findViewById(R.id.khoanchisua);
            holder.khoanchixoa = (ImageView)view.findViewById(R.id.khoanchixoa);
            view.setTag(holder);
        }else {
            holder = (ViewHolder) view.getTag();
        }
        final KhoanChi kc = khoanChiList.get(i);
        holder.txtkhoanchi.setText(kc.getKhoanchi());

        holder.khoanchisua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog dialog = new Dialog(context);
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setContentView(R.layout.dialog_sua);

                final EditText edtsua = (EditText) dialog.findViewById(R.id.edt_sua);
                Button btnsua = (Button) dialog.findViewById(R.id.btn_sua);
                edtsua.setText(kc.getKhoanchi());
                btnsua.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String a = edtsua.getText().toString();
                        int b = kc.getIdchi();
                        if (a.isEmpty()){
                            Toast.makeText(context, "vui long khong de trong ",Toast.LENGTH_SHORT).show();
                        }else {
                            Database database = new Database(context);
                            database.SendData("UPDATE CHI SET KHOANCHI = '"+a+"' WHERE IDCHI = "+b+"  ");
                            Toast.makeText(context , " update thanh cong",Toast.LENGTH_SHORT).show();
                            dialog.dismiss();
                            ((MainActivity)context).recreate();
                        }
                    }
                });
                dialog.show();
            }
        });
        holder.khoanchixoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog dialog = new Dialog(context);
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setContentView(R.layout.dialog_xoa);

                Button btnhuy = (Button) dialog.findViewById(R.id.btn_huyboxoa);
                Button btnchapnhan = (Button) dialog.findViewById(R.id.btn_chapnhanxoa);
                btnhuy.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
                btnchapnhan.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int a = kc.getIdchi();
                        Database database = new Database(context);
                        database.SendData("DELETE FROM CHI WHERE IDCHI = "+a+" ");
                        Toast.makeText(context , " delete thanh cong",Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                        ((MainActivity)context).recreate();
                    }
                });
                dialog.show();
            }
        });




        return view;
    }
}
