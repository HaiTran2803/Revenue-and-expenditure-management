package com.example.A36154_TH;

import android.app.Dialog;
import androidx.appcompat.widget.Toolbar;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.viewpager.widget.ViewPager;

import com.example.A36154_TH.SQLite.Database;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity
implements NavigationView.OnNavigationItemSelectedListener {
    ViewPager pager;
    Database database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        pager = (ViewPager) findViewById(R.id.view_paper);
        PagerAdapter adapter = new PagerAdapter(getSupportFragmentManager());
        pager.setAdapter(adapter);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                database = new Database(MainActivity.this);
                if (pager.getCurrentItem() == 0){
                    final Dialog dialog = new Dialog(MainActivity.this);
                    dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                    dialog.setContentView(R.layout.dialog_thu);

                    final EditText edt_khoanthu = (EditText) dialog.findViewById(R.id.edtkhoanthu);
                    final EditText edt_loaithu = (EditText) dialog.findViewById(R.id.edtloaithu);
                    Button btnvaothu = (Button) dialog.findViewById(R.id.btnvaothu);
                    btnvaothu.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            String a = edt_khoanthu.getText().toString();
                            String b = edt_loaithu.getText().toString();
                            String c = new SimpleDateFormat("hh:mm:ss dd-MM-yyyy")
                                    .format(Calendar.getInstance().getTime());
                            if (a.isEmpty() || b.isEmpty()){
                                Toast.makeText(MainActivity.this, "vui long khong de trong ", Toast.LENGTH_SHORT).show();
                            }else {
                                database.SendData("INSERT INTO THU VALUES ('"
                                 +c+ "','" +a+ "','"+b+"', NULL)");
                                PagerAdapter adapter = new PagerAdapter(getSupportFragmentManager());
                                pager.setAdapter(adapter);
                                pager.setCurrentItem(2);
                                Toast.makeText(MainActivity.this, "them du lieu thu thanh cong", Toast.LENGTH_SHORT).show();
                                dialog.dismiss();
                            }
                        }
                    });
                    dialog.show();
                }else if (pager.getCurrentItem() == 1){
                    final Dialog dialog = new Dialog(MainActivity.this);
                    dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                    dialog.setContentView(R.layout.dialog_chi);

                    final EditText edt_khoanchi = (EditText) dialog.findViewById(R.id.edtkhoanchi);
                    final EditText edt_loaichi = (EditText) dialog.findViewById(R.id.edtloaichi);
                    Button btnvaochi = (Button) dialog.findViewById(R.id.btnvaochi);
                    btnvaochi.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            String a = new SimpleDateFormat("ss:mm:hh dd-MM-yyyy")
                                    .format(Calendar.getInstance().getTime());
                            String b = edt_khoanchi.getText().toString();
                            String c = edt_loaichi.getText().toString();
                            if (a.isEmpty() || b.isEmpty()){
                                Toast.makeText(MainActivity.this, "vui long khong de trong ", Toast.LENGTH_SHORT).show();
                            }else {
                                database.SendData("INSERT INTO CHI VALUES ('"
                                        +a+ "','" +b+ "','"+c+"', NULL)");
                                PagerAdapter adapter = new PagerAdapter(getSupportFragmentManager());
                                pager.setAdapter(adapter);
                                pager.setCurrentItem(2);
                                Toast.makeText(MainActivity.this, "them du lieu chi thanh cong", Toast.LENGTH_SHORT).show();
                                dialog.dismiss();
                            }
                        }
                    });
                    dialog.show();
                }
            }
        });
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer,toolbar,R.string.navigation_drawer_open,R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout)findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)){
            drawer.closeDrawer(GravityCompat.START);
        }else {
            super.onBackPressed();
        }
    }
    @SuppressWarnings("StatemenWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.nav_khoanthu){
            PagerAdapter adapter = new PagerAdapter(getSupportFragmentManager());
            pager.setAdapter(adapter);
            pager.setCurrentItem(0);
        }if (id == R.id.nav_khoanchi){
            PagerAdapter adapter = new PagerAdapter(getSupportFragmentManager());
            pager.setAdapter(adapter);
            pager.setCurrentItem(1);
        }if (id == R.id.nav_thongke){
            PagerAdapter adapter = new PagerAdapter(getSupportFragmentManager());
            pager.setAdapter(adapter);
            pager.setCurrentItem(2);
        }if (id == R.id.nav_gioithieu){
            PagerAdapter adapter = new PagerAdapter(getSupportFragmentManager());
            pager.setAdapter(adapter);
            pager.setCurrentItem(3);
        }if (id == R.id.nav_thoat){
            finish();
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}