package com.example.quanlysinhvien;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

public class Search extends AppCompatActivity {
    EditText edtID, edtTen, edtSdt;
    Button btn_search,btn_back;
    ListView lvsinhvien1;

    Database database;

    public static ArrayList<Sinhvien> sinhvienArrayList;
    public static  SinhvienAdapter sinhvienAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        anhxa();

        database = new Database(this,"quanlysinhvien.sqlite",null,2);
        btn_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String masinhvien = edtID.getText().toString();
                String tensinhvien = edtTen.getText().toString();
                String sdtsinhvien = edtSdt.getText().toString();


                //đổ dũ liệu lên list view
                sinhvienAdapter = new SinhvienAdapter(Search.this,R.layout.line_sinhvien,sinhvienArrayList,database);
                lvsinhvien1.setAdapter(sinhvienAdapter);
                sinhvienArrayList.clear();
                // đổ dữ liệu vào cursor
                Cursor datasinhvien = database.GetQueryData("select * from Sinhvien where" +
                        " msv like '"+masinhvien+"'" +
                        "or hoten like '"+tensinhvien+"' " +
                        "or sdt like '"+sdtsinhvien+"'");
                while (datasinhvien.moveToNext())
                {
                    int masinhvien1 = datasinhvien.getInt(0);
                    String tensinhvien1 = datasinhvien.getString(1);
                    String dstsinhvien1 = datasinhvien.getString(2);
                    Sinhvien sinhvien = new Sinhvien(masinhvien1,tensinhvien1,dstsinhvien1);
                    sinhvienArrayList.add(sinhvien);
                }
                sinhvienAdapter.notifyDataSetChanged();
            }
        });
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Search.this,MainActivity.class));
            }
        });
    }

    private void anhxa() {
        edtID = findViewById(R.id.edt_id);
        edtTen = findViewById(R.id.edt_ten);
        edtSdt = findViewById(R.id.edt_sdt);
        btn_search = findViewById(R.id.btn_search);
        btn_back = findViewById(R.id.btn_back);
        lvsinhvien1 = findViewById(R.id.lvsinhvien1);
        sinhvienArrayList = new ArrayList<>();
    }
}
