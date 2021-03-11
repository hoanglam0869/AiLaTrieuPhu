package com.hoanglam0869.ailatrieuphu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.text.DecimalFormat;

public class KetQuaActivity extends AppCompatActivity {

    TextView txvKetQua, txvTien, txvSLCauDung, txvEXPNhan;
    String ketqua;
    int viTriCauHoi, kinhNghiem, tien;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ket_qua);

        AnhXa();
        LayDuLieu();
        AddControls();
        LuuDuLieu();
    }

    private void LuuDuLieu() {
        SharedPreferences sp = getSharedPreferences("caidat", MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        MainActivity.tongKinhNghiem += kinhNghiem;
        MainActivity.tongTien += tien;
        editor.putInt("tongkinhnghiem", MainActivity.tongKinhNghiem);
        editor.putInt("tongtien", MainActivity.tongTien);
        editor.apply();
    }

    private void AddControls() {
        String noiDung;
        if (ketqua.equals("thang")) {
            noiDung = "BẠN ĐÃ CHIẾN THẮNG VANG DỘI VỚI PHẦN THƯỞNG";
        } else {
            noiDung = "BẠN SẼ RA VỀ VỚI PHẦN THƯỞNG";
        }
        txvKetQua.setText(noiDung);
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        txvTien.setText(decimalFormat.format(tien));
        noiDung = "Số câu đúng: " + viTriCauHoi;
        txvSLCauDung.setText(noiDung);
        noiDung = "Kinh nghiệm nhận được: " + kinhNghiem + " EXP";
        txvEXPNhan.setText(noiDung);
    }

    private void LayDuLieu() {
        ketqua = getIntent().getStringExtra("ketqua");
        viTriCauHoi = getIntent().getIntExtra("vitricauhoi", 0);
        tien = getIntent().getIntExtra("tien", 0);
        if (viTriCauHoi > 10) {
            kinhNghiem = viTriCauHoi * 3 - 15;
        } else if (viTriCauHoi > 5) {
            kinhNghiem = viTriCauHoi * 2 - 5;
        } else {
            kinhNghiem = viTriCauHoi;
        }
    }

    private void AnhXa() {
        txvKetQua = findViewById(R.id.textViewKetQua);
        txvTien = findViewById(R.id.textViewTien);
        txvSLCauDung = findViewById(R.id.textViewSLCauDung);
        txvEXPNhan = findViewById(R.id.textViewEXPNhan);
    }

    @Override
    public void onBackPressed() {

    }

    public void menu(View view) {
        Intent intent = new Intent(KetQuaActivity.this, MainActivity.class);
        startActivity(intent);
    }
}