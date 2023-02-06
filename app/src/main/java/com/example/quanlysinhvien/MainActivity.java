package com.example.quanlysinhvien;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView lvsinhvien;

    Database database;

    public static ArrayList<Sinhvien> sinhvienArrayList;
    public static SinhvienAdapter sinhvienAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lvsinhvien = findViewById(R.id.lvsinhvien);
        sinhvienArrayList = new ArrayList<>();

        database = new Database(this,"quanlysinhvien.sqlite",null,2);
        database.NonQueryData("CREATE TABLE IF NOT EXISTS Sinhvien(msv integer primary key, hoten varchar(150), sdt varchar(150))");
        sinhvienAdapter = new SinhvienAdapter(MainActivity.this,R.layout.line_sinhvien,sinhvienArrayList,database);
        lvsinhvien.setAdapter(sinhvienAdapter);
        Cursor datasinhvien =database.GetQueryData("SELECT * from Sinhvien");
        while (datasinhvien.moveToNext()){
            int masinhvien = datasinhvien.getInt(0);
            String tensinhvien=datasinhvien.getString(1);
            String sdtsinhvien = datasinhvien.getString(2);
            Sinhvien sinhvien = new Sinhvien(masinhvien,tensinhvien,sdtsinhvien);
            sinhvienArrayList.add(sinhvien);
        }
        sinhvienAdapter.notifyDataSetChanged();
    }
    public static void UpdateSinhvien(int masinhvien,final int i){
        masinhvien = sinhvienArrayList.get(i).masv;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.add,menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
           switch(item.getItemId()){
               case R.id.menu_add:
                   startActivity(new Intent(MainActivity.this,AddSinhvien.class));
                   break;
               case R.id.menu_search:
                   startActivity(new Intent(MainActivity.this,Search.class));
                   break;
           }
        return super.onOptionsItemSelected(item);
    }
}
