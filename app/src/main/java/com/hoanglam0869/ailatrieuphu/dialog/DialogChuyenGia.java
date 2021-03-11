package com.hoanglam0869.ailatrieuphu.dialog;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.ViewFlipper;

import androidx.annotation.NonNull;

import com.hoanglam0869.ailatrieuphu.R;
import com.hoanglam0869.ailatrieuphu.adapter.ChuyenGiaAdapter;
import com.hoanglam0869.ailatrieuphu.object.FaceData;
import com.hoanglam0869.ailatrieuphu.object.VatPham;

import java.util.ArrayList;
import java.util.Random;

public class DialogChuyenGia extends Dialog {
    ImageView imgDong;
    ListView lvChuyenGia;
    ArrayList<VatPham> vatPhamArrayList;
    ChuyenGiaAdapter chuyenGiaAdapter;

    public DialogChuyenGia(@NonNull Context context, int vtD) {
        super(context);
        setContentView(R.layout.dialog_tro_giup_chuyen_gia);
        getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        AnhXa();
        SetUp();
        vatPhamArrayList = FaceData.vatpham((Activity) context, "thechuyengia");
        chuyenGiaAdapter = new ChuyenGiaAdapter(context, vatPhamArrayList);
        lvChuyenGia.setAdapter(chuyenGiaAdapter);

        lvChuyenGia.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                dismiss();
                Dialog dialog = new Dialog(context);
                dialog.setContentView(R.layout.dialog_chuyen_gia_tra_loi);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

                TextView txvTenChuyenGia = dialog.findViewById(R.id.textViewTenChuyenGia);
                ImageView imgHinhChuyenGia = dialog.findViewById(R.id.imageViewHinhChuyenGia);
                TextView txvChuyenGiaTraLoi = dialog.findViewById(R.id.textViewChuyenGiaTraLoi);
                Button btnXacNhan = dialog.findViewById(R.id.buttonXacNhan);

                VatPham vatPham = vatPhamArrayList.get(position);
                txvTenChuyenGia.setText(vatPham.getTenVatPham());
                Bitmap bitmap = BitmapFactory.decodeByteArray(vatPham.getHinhVatPham(), 0, vatPham.getHinhVatPham().length);
                imgHinhChuyenGia.setImageBitmap(bitmap);

                Random r = new Random();
                int tiLe = r.nextInt(100);
                int tiLeTraLoiDung = chuyenGiaAdapter.TiLeDung(vatPham);
                String[] dapAn = {"A", "B", "C", "D"};
                if (tiLe <= tiLeTraLoiDung - 1) {
                    txvChuyenGiaTraLoi.setText(dapAn[vtD]);
                } else {
                    while (true) {
                        int vtS = r.nextInt(4);
                        if (vtS != vtD) {
                            txvChuyenGiaTraLoi.setText(dapAn[vtS]);
                            break;
                        }
                    }
                }

                btnXacNhan.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
                dialog.show();
            }
        });
    }

    private void SetUp() {
        imgDong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
    }

    private void AnhXa() {
        imgDong = findViewById(R.id.imageViewDongTroGiupChuyenGia);
        lvChuyenGia = findViewById(R.id.listViewChuyenGia);

    }
}
