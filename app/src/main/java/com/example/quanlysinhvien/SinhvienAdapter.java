package com.example.quanlysinhvien;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

public class SinhvienAdapter extends BaseAdapter {
    Context context;
    int layout;
    List<Sinhvien> sinhvienList;
    Database database;

    public SinhvienAdapter(Context context, int layout, List<Sinhvien> sinhvienList, Database database) {
        this.context = context;
        this.layout = layout;
        this.sinhvienList = sinhvienList;
        this.database = database;
    }

    @Override
    public int getCount() {
        return sinhvienList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    class ViewHolder{
        TextView txtma,txtten,txtsdt;
        Button btn_sua,btn_delete;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder =new ViewHolder();
        if (convertView==null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(layout,null);
            holder.txtma = convertView.findViewById(R.id.txtid);
            holder.txtten = convertView.findViewById(R.id.txtten);
            holder.txtsdt = convertView.findViewById(R.id.txtsdt);
            holder.btn_sua = convertView.findViewById(R.id.btn_sua);
            holder.btn_delete=convertView.findViewById(R.id.btn_xoa);
            convertView.setTag(holder);
        }else{
            holder =(ViewHolder) convertView.getTag();
        }
        final Sinhvien sinhvien = sinhvienList.get(position);

        holder.txtma.setText("MSV: "+sinhvien.masv);
        holder.txtten.setText("Tên: "+sinhvien.tensv);
        holder.txtsdt.setText("SĐT: "+sinhvien.sdtsv);

            holder.btn_sua.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context,UpdateSinhvien.class);
                    intent.putExtra("masinhvien",sinhvien.getMasv());
                    context.startActivity(intent);
                }
            });
            holder.btn_delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    database.NonQueryData("delete from Sinhvien where msv = "+sinhvien.getMasv()+"");
                    Cursor datasinhvien = database.GetQueryData("SELECT * From Sinhvien");
                    MainActivity.sinhvienArrayList.clear();
                    while (datasinhvien.moveToNext()){
                        int masinhvien = datasinhvien.getInt(0);
                        String tensinhvien=datasinhvien.getString(1);
                        String sdtsinhvien = datasinhvien.getString(2);
                        Sinhvien sinhvien = new Sinhvien(masinhvien,tensinhvien,sdtsinhvien);
                        sinhvienList.add(sinhvien);
                    }
                    MainActivity.sinhvienAdapter.notifyDataSetChanged();

                }
            });
        return convertView;
    }
}
