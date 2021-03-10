package com.hoanglam0869.ailatrieuphu.dialog;

import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.hoanglam0869.ailatrieuphu.MainActivity;
import com.hoanglam0869.ailatrieuphu.R;
import com.hoanglam0869.ailatrieuphu.object.FaceData;

import java.util.ArrayList;

public class DialogCaNhan extends Dialog {
    ImageView imgDong;
    TextView txvKinhNghiem, txvTienDo, txvTongEXP;
    ProgressBar pbgKinhNghiem;
    int[] mangEXP;
    String noiDung;

    public DialogCaNhan(@NonNull Context context) {
        super(context);
        setContentView(R.layout.dialog_ca_nhan);
        getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        AnhXa();
        TaoMang();
        GanDuLieu();
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

    private void GanDuLieu() {
        noiDung = "Tổng EXP: " + MainActivity.tongKinhNghiem;
        txvTongEXP.setText(noiDung);
        if (MainActivity.tongKinhNghiem >= mangEXP[mangEXP.length - 1]) {
            txvKinhNghiem.setText(String.valueOf(mangEXP.length));
            pbgKinhNghiem.setMax(100);
            pbgKinhNghiem.setProgress(0);
            noiDung = 0 + " / " + 0 + " EXP";
            txvTienDo.setText(noiDung);
            return;
        }
        for (int i = 0; i < 20; i++) {
            if (MainActivity.tongKinhNghiem == mangEXP[i]) {
                txvKinhNghiem.setText(String.valueOf(i + 1));
                int expLenCap = mangEXP[i + 1] - mangEXP[i];
                pbgKinhNghiem.setMax(expLenCap);
                int expHienTai = MainActivity.tongKinhNghiem - mangEXP[i];
                pbgKinhNghiem.setProgress(expHienTai);
                noiDung = expHienTai + " / " + expLenCap + " EXP";
                txvTienDo.setText(noiDung);
                break;
            } else if (MainActivity.tongKinhNghiem < mangEXP[i]) {
                txvKinhNghiem.setText(String.valueOf(i));
                int expLenCap = mangEXP[i] - mangEXP[i - 1];
                pbgKinhNghiem.setMax(expLenCap);
                int expHienTai = MainActivity.tongKinhNghiem - mangEXP[i - 1];
                pbgKinhNghiem.setProgress(expHienTai);
                noiDung = expHienTai + " / " + expLenCap + " EXP";
                txvTienDo.setText(noiDung);
                break;
            }
        }
    }

    private void TaoMang() {
        mangEXP = new int[20];
        mangEXP[0] = 0;         // cấp 1
        mangEXP[1] = 21;        // cấp 2
        mangEXP[2] = 44;        // cấp 3
        mangEXP[3] = 69;        // cấp 4
        mangEXP[4] = 96;        // cấp 5
        mangEXP[5] = 125;       // cấp 6
        mangEXP[6] = 156;       // cấp 7
        mangEXP[7] = 189;       // cấp 8
        mangEXP[8] = 224;       // cấp 9
        mangEXP[9] = 261;       // cấp 10
        mangEXP[10] = 300;      // cấp 11
        mangEXP[11] = 341;      // cấp 12
        mangEXP[12] = 384;      // cấp 13
        mangEXP[13] = 429;      // cấp 14
        mangEXP[14] = 476;      // cấp 15
        mangEXP[15] = 525;      // cấp 16
        mangEXP[16] = 576;      // cấp 17
        mangEXP[17] = 629;      // cấp 18
        mangEXP[18] = 684;      // cấp 19
        mangEXP[19] = 741;      // cấp 20
    }

    private void AnhXa() {
        imgDong = findViewById(R.id.imageViewDongCaNhan);
        txvKinhNghiem = findViewById(R.id.textViewKinhNghiem);
        txvTienDo = findViewById(R.id.textViewTienDo);
        txvTongEXP = findViewById(R.id.textViewTongEXP);
        pbgKinhNghiem = findViewById(R.id.progressBarKinhNghiem);
    }
}
