package com.hoanglam0869.ailatrieuphu.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hoanglam0869.ailatrieuphu.R;
import com.hoanglam0869.ailatrieuphu.object.VatPham;

import java.util.ArrayList;

public class ChuyenGiaAdapter extends BaseAdapter {
    Context context;
    ArrayList<VatPham> vatPhamArrayList;

    public ChuyenGiaAdapter(Context context, ArrayList<VatPham> vatPhamArrayList) {
        this.context = context;
        this.vatPhamArrayList = vatPhamArrayList;
    }

    @Override
    public int getCount() {
        return vatPhamArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return vatPhamArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    static class ViewHolder {
        ImageView imgChuyenGia;
        TextView txvTenChuyenGia, txvTiLeTraLoiDung;
        LinearLayout suDungChuyenGia;
    }

    int tiLeTraLoiDung;

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.item_cua_hang, null);
            holder = new ViewHolder();
            holder.imgChuyenGia = convertView.findViewById(R.id.imageViewHinhVatPham);
            holder.txvTenChuyenGia = convertView.findViewById(R.id.textViewTenVatPham);
            holder.txvTiLeTraLoiDung = convertView.findViewById(R.id.textViewSLVatPham);
            holder.suDungChuyenGia = convertView.findViewById(R.id.muaVatPham);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        VatPham vatPham = vatPhamArrayList.get(position);
        Bitmap bitmap = BitmapFactory.decodeByteArray(vatPham.getHinhVatPham(), 0, vatPham.getHinhVatPham().length);
        holder.imgChuyenGia.setImageBitmap(bitmap);

        holder.txvTenChuyenGia.setText(vatPham.getTenVatPham());
        String noiDung = "Tỉ lệ đúng: " + TiLeDung(vatPham) + "%";
        holder.txvTiLeTraLoiDung.setText(noiDung);

        holder.suDungChuyenGia.setVisibility(View.INVISIBLE);
        return convertView;
    }

    public int TiLeDung(VatPham vatPham) {
        switch (vatPham.getLoaiThe()) {
            case "A":
                tiLeTraLoiDung = 85 + vatPham.getCapThe() - 1;
                break;
            case "B":
                tiLeTraLoiDung = 80 + vatPham.getCapThe() - 1;
                break;
            case "C":
                tiLeTraLoiDung = 75 + vatPham.getCapThe() - 1;
                break;
            case "D":
                tiLeTraLoiDung = 70 + vatPham.getCapThe() - 1;
                break;
        }
        return tiLeTraLoiDung;
    }
}
