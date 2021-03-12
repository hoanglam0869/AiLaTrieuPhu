package com.hoanglam0869.ailatrieuphu.dialog;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.hoanglam0869.ailatrieuphu.MainActivity;
import com.hoanglam0869.ailatrieuphu.R;
import com.hoanglam0869.ailatrieuphu.adapter.VatPhamAdapter;
import com.hoanglam0869.ailatrieuphu.object.FaceData;
import com.hoanglam0869.ailatrieuphu.object.VatPham;

import java.util.ArrayList;

public class DialogCuaHang extends Dialog {
    ImageView imgDong;
    public TextView txvTongTien;
    ListView lvVatPham;
    ArrayList<VatPham> vatPhamArrayList;
    VatPhamAdapter vatPhamAdapter;

    public DialogCuaHang(@NonNull Context context) {
        super(context);
        setContentView(R.layout.dialog_cua_hang);
        getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        setCanceledOnTouchOutside(false);

        AnhXa();
        txvTongTien.setText(FaceData.FormatTienThuong(MainActivity.tongTien));
        vatPhamArrayList = FaceData.vatpham((Activity) context, "tatca");
        vatPhamAdapter = new VatPhamAdapter(context, vatPhamArrayList);
        lvVatPham.setAdapter(vatPhamAdapter);

        SetClick();
        lvVatPham.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String noiDung = vatPhamArrayList.get(position).getMoTaVatPham();
                new DialogInfoVatPham(context, noiDung).show();
            }
        });
    }

    private void SetClick() {
        imgDong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
    }

    private void AnhXa() {
        imgDong = findViewById(R.id.imageViewDongCuaHang);
        txvTongTien = findViewById(R.id.textViewTongTien);
        lvVatPham = findViewById(R.id.listViewTroGiup);
        vatPhamArrayList = new ArrayList<>();
    }
}
