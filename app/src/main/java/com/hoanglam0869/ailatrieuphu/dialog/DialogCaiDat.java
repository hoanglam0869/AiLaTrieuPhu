package com.hoanglam0869.ailatrieuphu.dialog;

import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;

import androidx.annotation.NonNull;

import com.hoanglam0869.ailatrieuphu.MainActivity;
import com.hoanglam0869.ailatrieuphu.R;

public class DialogCaiDat extends Dialog {
    ImageView imgDong;
    SeekBar sbNhacNen, sbAmThanh;
    Button btnOK;
    SharedPreferences sp;
    float loaNhacNen, loaAmThanh;

    public DialogCaiDat(@NonNull Context context) {
        super(context);
        setContentView(R.layout.dialog_cai_dat);
        getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        setCanceledOnTouchOutside(false);

        imgDong = findViewById(R.id.imageViewDongCaiDat);
        sbNhacNen = findViewById(R.id.seekBarNhacNen);
        sbAmThanh = findViewById(R.id.seekBarAmThanh);
        btnOK = findViewById(R.id.buttonOK);

        sp = context.getSharedPreferences("caidat", Context.MODE_PRIVATE);
        loaNhacNen = sp.getFloat("loanhacnen", 1f);
        loaAmThanh = sp.getFloat("loaamthanh", 1f);

        sbNhacNen.setProgress((int) (loaNhacNen * sbNhacNen.getMax()));
        sbAmThanh.setProgress((int) (loaAmThanh * sbAmThanh.getMax()));

        imgDong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

        btnOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                float NhacNen = sbNhacNen.getProgress() * 1f / sbNhacNen.getMax();
                float AmThanh = sbAmThanh.getProgress() * 1f / sbAmThanh.getMax();
                SharedPreferences.Editor editor = sp.edit();
                editor.putFloat("loanhacnen", NhacNen);
                editor.putFloat("loaamthanh", AmThanh);
                editor.apply();
                MainActivity.SetVolume(NhacNen);
                dismiss();
            }
        });
    }
}
