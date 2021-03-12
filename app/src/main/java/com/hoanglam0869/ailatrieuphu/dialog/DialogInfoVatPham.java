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

public class DialogInfoVatPham extends Dialog {
    ImageView imgDong;
    TextView txvThongTinVatPham;

    public DialogInfoVatPham(@NonNull Context context, String noiDung) {
        super(context);
        setContentView(R.layout.dialog_info_vat_pham);
        getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        setCanceledOnTouchOutside(false);

        AnhXa();
        txvThongTinVatPham.setText(noiDung);
        SetClick();
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
        imgDong = findViewById(R.id.imageViewDongInfoVatPham);
        txvThongTinVatPham = findViewById(R.id.txvThongTinVatPham);
    }
}
