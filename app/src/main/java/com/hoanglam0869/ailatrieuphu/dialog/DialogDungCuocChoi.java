package com.hoanglam0869.ailatrieuphu.dialog;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.hoanglam0869.ailatrieuphu.ChoiActivity;
import com.hoanglam0869.ailatrieuphu.R;

import java.text.DecimalFormat;

public class DialogDungCuocChoi extends Dialog {

    ImageView imgDong;
    TextView txvTienThuong;
    Button btnChonThu;

    public DialogDungCuocChoi(@NonNull Context context, int tien) {
        super(context);
        setContentView(R.layout.dialog_dung_cuoc_choi);
        getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        AnhXa();
        imgDong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        String noiDung = decimalFormat.format(tien) + "";
        txvTienThuong.setText(noiDung);
        btnChonThu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
    }

    private void AnhXa() {
        imgDong = findViewById(R.id.imageViewDongDungCuocChoi);
        txvTienThuong = findViewById(R.id.textViewDungCuocChoi);
        btnChonThu = findViewById(R.id.buttonChonThu);
    }
}
