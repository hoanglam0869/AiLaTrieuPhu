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
    Random r;
    ListView lvChuyenGia;
    ArrayList<VatPham> vatPhamArrayList;
    ChuyenGiaAdapter chuyenGiaAdapter;

    public DialogChuyenGia(@NonNull Context context, int vtD) {
        super(context);
        setContentView(R.layout.dialog_tro_giup_chuyen_gia);
        getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        AnhXa();
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

                ImageView imgHinhChuyenGia = dialog.findViewById(R.id.imageViewHinhChuyenGia);
                TextView txvChuyenGiaTraLoi = dialog.findViewById(R.id.textViewChuyenGiaTraLoi);
                Button btnXacNhan = dialog.findViewById(R.id.buttonXacNhan);

                VatPham vatPham = vatPhamArrayList.get(position);
                Bitmap bitmap = BitmapFactory.decodeByteArray(vatPham.getHinhVatPham(), 0, vatPham.getHinhVatPham().length);
                imgHinhChuyenGia.setImageBitmap(bitmap);

                switch (vtD) {
                    case 0:
                        txvChuyenGiaTraLoi.setText("A");
                        break;
                    case 1:
                        txvChuyenGiaTraLoi.setText("B");
                        break;
                    case 2:
                        txvChuyenGiaTraLoi.setText("C");
                        break;
                    case 3:
                        txvChuyenGiaTraLoi.setText("D");
                        break;
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
        SetUp();
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
