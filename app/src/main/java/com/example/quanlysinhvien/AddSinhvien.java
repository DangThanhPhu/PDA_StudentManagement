package com.example.quanlysinhvien;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddSinhvien extends AppCompatActivity {

    EditText editma,editten,editsdt;
    Button btn_add,btn_huy;

    Database database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_sinhvien);
        anhxa();
        database = new Database(this,"quanlysinhvien.sqlite",null,2);
        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int masinhvien = Integer.parseInt(editma.getText().toString());
                String tensinhvien = editten.getText().toString();
                String sdtsinhvien = editsdt.getText().toString();

                database.NonQueryData("insert into Sinhvien(msv,hoten,sdt) values("+masinhvien+",'"+tensinhvien+"','"+sdtsinhvien+"')");
                startActivity(new Intent(AddSinhvien.this,MainActivity.class));
            }
        });
        btn_huy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AddSinhvien.this,MainActivity.class));
            }
        });
    }

    private void anhxa() {
        editma = findViewById(R.id.edt_msv);
        editten = findViewById(R.id.edt_ten);
        editsdt = findViewById(R.id.edt_sdt);
        btn_add = findViewById(R.id.btn_add);
        btn_huy = findViewById(R.id.btn_huyadd);
    }
}
