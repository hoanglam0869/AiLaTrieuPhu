package com.hoanglam0869.ailatrieuphu.adapter;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.hoanglam0869.ailatrieuphu.Database;
import com.hoanglam0869.ailatrieuphu.MainActivity;
import com.hoanglam0869.ailatrieuphu.R;
import com.hoanglam0869.ailatrieuphu.dialog.DialogCuaHang;
import com.hoanglam0869.ailatrieuphu.object.FaceData;
import com.hoanglam0869.ailatrieuphu.object.VatPham;

import java.util.ArrayList;

public class VatPhamAdapter extends BaseAdapter {
    Context context;
    ArrayList<VatPham> vatPhamArrayList;

    public VatPhamAdapter(Context context, ArrayList<VatPham> vatPhamArrayList) {
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
        ImageView imgHinhVatPham;
        TextView txvTenVatPham, txvSLVatPham, txvGiaVatPham;
        LinearLayout muaVatPham;
    }

    String noiDung;

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.item_cua_hang, null);
            holder = new ViewHolder();
            holder.imgHinhVatPham = convertView.findViewById(R.id.imageViewHinhVatPham);
            holder.txvTenVatPham = convertView.findViewById(R.id.textViewTenVatPham);
            holder.txvSLVatPham = convertView.findViewById(R.id.textViewSLVatPham);
            holder.txvGiaVatPham = convertView.findViewById(R.id.textViewGiaVatPham);
            holder.muaVatPham = convertView.findViewById(R.id.muaVatPham);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        VatPham vatPham = vatPhamArrayList.get(position);
        Bitmap bitmap = BitmapFactory.decodeByteArray(vatPham.getHinhVatPham(), 0, vatPham.getHinhVatPham().length);
        holder.imgHinhVatPham.setImageBitmap(bitmap);

        holder.txvTenVatPham.setText(vatPham.getTenVatPham());
        noiDung = "Có: " + vatPham.getSlVatPham();
        holder.txvSLVatPham.setText(noiDung);
        holder.txvGiaVatPham.setText(FaceData.FormatTienThuong(vatPham.getGiaVatPham()));

        holder.muaVatPham.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (MainActivity.tongTien < vatPham.getGiaVatPham()) {
                    Toast.makeText(context, "Bạn không đủ tiền", Toast.LENGTH_SHORT).show();
                } else if (vatPham.getSlVatPham() == 99) {
                    Toast.makeText(context, "Bạn không thể mua thêm", Toast.LENGTH_SHORT).show();
                } else {
                    int soluong = vatPham.getSlVatPham();
                    vatPham.setSlVatPham(++soluong);
                    noiDung = "Có: " + soluong;
                    holder.txvSLVatPham.setText(noiDung);

                    SQLiteDatabase database = Database.initDatabase((Activity) context, FaceData.DATABASE_NAME);
                    ContentValues contentValues = new ContentValues();
                    contentValues.put("soluong", soluong);
                    database.update("vatpham", contentValues, "id=?", new String[]{(position + 1) + ""});

                    MainActivity.tongTien -= vatPham.getGiaVatPham();
                    DialogCuaHang dialogCuaHang = new DialogCuaHang(context);
                    dialogCuaHang.SetTongTien();

                    SharedPreferences sp = context.getSharedPreferences("caidat", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sp.edit();
                    editor.putInt("tongtien", MainActivity.tongTien);
                    editor.apply();
                }
            }
        });
        return convertView;
    }
}
