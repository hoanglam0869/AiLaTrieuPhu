package com.hoanglam0869.ailatrieuphu.dialog;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.hoanglam0869.ailatrieuphu.R;

import java.util.Random;

public class DialogChuyenGia extends Dialog {
    ImageView imgDong, imgChuyenGia;
    TextView txtDapAn;
    Random r;

    public DialogChuyenGia(@NonNull Context context, int vtD) {
        super(context);
        setContentView(R.layout.dialog_tro_giup_chuyen_gia);
        getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        AnhXa();
        SetUp();
        AddControls(vtD);
    }

    private void SetUp() {
        int[] hinhChuyenGia = new int[4];
        hinhChuyenGia[0] = R.drawable.einstein;
        hinhChuyenGia[1] = R.drawable.newton;
        hinhChuyenGia[2] = R.drawable.galilei;
        hinhChuyenGia[3] = R.drawable.darwin;

        r = new Random();
        int viTriChuyenGia = r.nextInt(hinhChuyenGia.length);
        imgChuyenGia.setImageResource(hinhChuyenGia[viTriChuyenGia]);

        imgDong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
    }

    private void AddControls(int vtD) {
        switch (vtD) {
            case 0:
                txtDapAn.setText("A");
                break;
            case 1:
                txtDapAn.setText("B");
                break;
            case 2:
                txtDapAn.setText("C");
                break;
            case 3:
                txtDapAn.setText("D");
                break;
        }
    }

    private void AnhXa() {
        imgDong = findViewById(R.id.imageViewDongTroGiupChuyenGia);
        imgChuyenGia = findViewById(R.id.imageViewChuyenGia);
        txtDapAn = findViewById(R.id.textViewDapAn);
    }
}
