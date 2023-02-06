package com.example.quanlysinhvien;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class UpdateSinhvien extends AppCompatActivity {
    EditText editma,editten,editsdt;
    Button btn_update,btn_huy;

    Database database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_sinhvien);

        anhxa();
        database = new Database(this,"quanlysinhvien.sqlite",null,2);
        Intent intent = getIntent();
        editma.setText(String.valueOf((intent.getIntExtra("masinhvien",111))));

        btn_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int masinhvien = Integer.parseInt(editma.getText().toString());
                String tensinhvien = editten.getText().toString();
                String sdtsinhvien = editsdt.getText().toString();
                database.NonQueryData("update Sinhvien set hoten='"+tensinhvien+"',sdt='"+sdtsinhvien+"' where msv="+masinhvien+" ");
                startActivity(new Intent(UpdateSinhvien.this,MainActivity.class));
            }
        });
        btn_huy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(UpdateSinhvien.this,MainActivity.class));
            }
        });
    }

    private void anhxa() {
        editma = findViewById(R.id.edt_msv);
        editten = findViewById(R.id.edt_ten);
        editsdt = findViewById(R.id.edt_sdt);
        btn_update = findViewById(R.id.btn_update);
        btn_huy = findViewById(R.id.btn_huyupdate);
    }
}
