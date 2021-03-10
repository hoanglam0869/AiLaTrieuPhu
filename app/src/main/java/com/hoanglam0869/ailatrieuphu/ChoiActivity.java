package com.hoanglam0869.ailatrieuphu;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.animation.ObjectAnimator;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.hoanglam0869.ailatrieuphu.adapter.TienThuongAdapter;
import com.hoanglam0869.ailatrieuphu.dialog.DialogChuyenGia;
import com.hoanglam0869.ailatrieuphu.dialog.DialogDungCuocChoi;
import com.hoanglam0869.ailatrieuphu.dialog.DialogKhanGiaTraLoi;
import com.hoanglam0869.ailatrieuphu.object.CauHoi;
import com.hoanglam0869.ailatrieuphu.object.FaceData;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Random;

public class ChoiActivity extends AppCompatActivity {

    DrawerLayout drawerLayout;
    ListView lsvTienThuong;
    TienThuongAdapter tienThuongAdapter;
    ArrayList<Integer> arrTienThuong;
    CauHoi cauHoi;
    int viTriCauHoi = 1, time, tien, tongThoiGian = 30000, thoiGianConLai;
    View.OnClickListener listener;
    TextView txvThuong, txvThoiGian, txvTienThuong, txvViTriCauHoi, txvCauHoi, txvCauTL1, txvCauTL2, txvCauTL3, txvCauTL4;
    ArrayList<TextView> arrTxvCauTraLoi;
    String cauTraLoi, noiDung;
    FaceData faceData;
    ArrayList<CauHoi> arrCauHoi;
    float loaNhacNen, loaAmThanh;
    SharedPreferences sp;
    LinearLayout bangTroGiup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choi);

        init();
        anhXa();
        setUp();
        setClick();
    }

    public void init() {
        arrTienThuong = new ArrayList<>();
        arrTienThuong.add(150000000);
        arrTienThuong.add(85000000);
        arrTienThuong.add(60000000);
        arrTienThuong.add(40000000);
        arrTienThuong.add(30000000);
        arrTienThuong.add(22000000);
        arrTienThuong.add(14000000);
        arrTienThuong.add(10000000);
        arrTienThuong.add(6000000);
        arrTienThuong.add(3000000);
        arrTienThuong.add(2000000);
        arrTienThuong.add(1000000);
        arrTienThuong.add(600000);
        arrTienThuong.add(400000);
        arrTienThuong.add(200000);

        tienThuongAdapter = new TienThuongAdapter(this, 0, arrTienThuong);
        cauHoi = new CauHoi();
        arrTxvCauTraLoi = new ArrayList<>();
        faceData = new FaceData();

        sp = getSharedPreferences("caidat", MODE_PRIVATE);
        loaNhacNen = sp.getFloat("loanhacnen", 1f);
        loaAmThanh = sp.getFloat("loaamthanh", 1f);

        mpTroGiup5050 = MediaPlayer.create(ChoiActivity.this, R.raw.trogiup5050);
        mpTroGiup5050.setVolume(loaAmThanh, loaAmThanh);

        mpTroGiupKhanGia = MediaPlayer.create(this, R.raw.trogiupkhangia);
        mpTroGiupKhanGia.setVolume(loaNhacNen, loaNhacNen);

        mpTroGiupThu4 = MediaPlayer.create(ChoiActivity.this, R.raw.trogiupthu4);
        mpTroGiupThu4.setVolume(loaAmThanh, loaAmThanh);

        mpTroGiupDoiCauHoi = MediaPlayer.create(ChoiActivity.this, R.raw.trogiupdoicauhoi);
        mpTroGiupDoiCauHoi.setVolume(loaAmThanh, loaAmThanh);

        mpTroGiupChuyenGia = MediaPlayer.create(this, R.raw.trogiupchuyengia);
        mpTroGiupChuyenGia.setVolume(loaNhacNen, loaNhacNen);
    }

    public void anhXa() {
        drawerLayout = findViewById(R.id.drawerLayout);
        lsvTienThuong = findViewById(R.id.lsvTienThuong);
        txvThuong = findViewById(R.id.textViewThuong);
        pbgThoiGian = findViewById(R.id.progressBarThoiGian);
        txvThoiGian = findViewById(R.id.textViewThoiGian);
        txvSL5050 = findViewById(R.id.txvSLTroGiup5050);
        txvSLKhanGia = findViewById(R.id.txvSLTroGiupKhanGia);
        txvSLDoiCauHoi = findViewById(R.id.txvSLTroGiupDoiCauHoi);
        txvSLChuyenGia = findViewById(R.id.txvSLTroGiupChuyenGia);
        txvTienThuong = findViewById(R.id.textViewTienThuong);
        txvViTriCauHoi = findViewById(R.id.textViewViTriCauHoi);
        txvCauHoi = findViewById(R.id.txvCauHoi);
        txvCauTL1 = findViewById(R.id.txvCauTL1);
        txvCauTL2 = findViewById(R.id.txvCauTL2);
        txvCauTL3 = findViewById(R.id.txvCauTL3);
        txvCauTL4 = findViewById(R.id.txvCauTL4);
        bangTroGiup = findViewById(R.id.troGiup);

        arrTxvCauTraLoi.add(txvCauTL1);
        arrTxvCauTraLoi.add(txvCauTL2);
        arrTxvCauTraLoi.add(txvCauTL3);
        arrTxvCauTraLoi.add(txvCauTL4);
    }

    public void setUp() {
        lsvTienThuong.setAdapter(tienThuongAdapter);
        arrCauHoi = faceData.question(this);
        SQLiteDatabase database = Database.initDatabase(this, FaceData.DATABASE_NAME);
        Cursor cursor = database.rawQuery("SELECT * FROM vatpham WHERE loai='trogiup'", null);
        cursor.moveToFirst();
        txvSL5050.setText(String.valueOf(cursor.getInt(5)));
        cursor.moveToNext();
        txvSLKhanGia.setText(String.valueOf(cursor.getInt(5)));
        cursor.moveToNext();
        txvSLDoiCauHoi.setText(String.valueOf(cursor.getInt(5)));
        cursor.moveToNext();
        txvSLChuyenGia.setText(String.valueOf(cursor.getInt(5)));
        hienCauHoi();
    }

    public void setClick() {
        listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkCauTraLoi(((TextView) v));
            }
        };
        for (TextView t : arrTxvCauTraLoi) {
            t.setOnClickListener(listener);
        }
    }

    public void checkCauTraLoi(TextView txv) {
        DungThoiGian();
        isChecking = true;
        for (TextView t : arrTxvCauTraLoi) {
            t.setEnabled(false);
        }
        cauTraLoi = txv.getText().toString();
        txv.setBackgroundResource(R.drawable.bg_cau_chon);
        mpNhacNen.stop();
        mpChon.start();
        time = 2000;
        if (viTriCauHoi % 5 == 0) {
            time = 5000;
        } else {
            time = time + (viTriCauHoi / 5) * 1000;
        }
        new CountDownTimer(time, 100) {
            @Override
            public void onTick(long millisUntilFinished) {

            }

            @Override
            public void onFinish() {
                for (TextView t : arrTxvCauTraLoi) {
                    String s = t.getText().toString();
                    if (s.equals(cauHoi.getDapAnDung())) {
                        t.setBackgroundResource(R.drawable.bg_cau_dung);
                        break;
                    }
                }
                mpChon.stop();
                if (cauTraLoi.equals(cauHoi.getDapAnDung())) {
                    mpDung.start();
                } else {
                    mpSai.start();
                }
                new CountDownTimer(time, 100) {
                    @Override
                    public void onTick(long millisUntilFinished) {

                    }

                    @Override
                    public void onFinish() {
                        if (isDungCuocChoi) {
                            XemKetQua("dungcuocchoi");
                            return;
                        }
                        if (cauTraLoi.equals(cauHoi.getDapAnDung())) {
                            tienThuongAdapter.setViTriCauHoi(viTriCauHoi);
                            drawerLayout.openDrawer(GravityCompat.END);
                        }
                        new CountDownTimer(time, 100) {
                            @Override
                            public void onTick(long millisUntilFinished) {

                            }

                            @Override
                            public void onFinish() {
                                if (cauTraLoi.equals(cauHoi.getDapAnDung())) {
                                    drawerLayout.closeDrawer(GravityCompat.END);
                                    viTriCauHoi++;
                                    if (viTriCauHoi > 15) {
                                        tien = arrTienThuong.get(0);
                                        XemKetQua("thang");
                                        return;
                                    }
                                    mpDung.stop();
                                    mpNhacNen.reset();
                                    mpChon.reset();
                                    mpDung.reset();
                                    mpSai.reset();
                                    hienCauHoi();
                                } else {
                                    tien = 0;
                                    if (viTriCauHoi > 5) {
                                        tien = arrTienThuong.get(15 - (((viTriCauHoi - 1) / 5) * 5));
                                    }
                                    mpSai.stop();
                                    XemKetQua("thua");
                                }
                            }
                        }.start();
                    }
                }.start();
            }
        }.start();
    }

    public void setCauHoi() {
        if (!doiCauHoi) {
            int vitri = ((viTriCauHoi - 1) / 5) * 6 + 5;
            cauHoi = arrCauHoi.get(vitri);
        } else {
            int vitri = viTriCauHoi + ((viTriCauHoi - 1) / 5) - 1;
            cauHoi = arrCauHoi.get(vitri);
        }
        isChecking = false;
        hoTro5050 = true;
        doiCauHoi = true;
        if (viTriCauHoi == 6) {
            mpTroGiupThu4.start();
            bangTroGiup.setWeightSum(4);
            bangTroGiup.getLayoutParams().width = LinearLayout.LayoutParams.MATCH_PARENT;
            RelativeLayout relTroGiupChuyenGia = findViewById(R.id.troGiupThu4);
            relTroGiupChuyenGia.setVisibility(View.VISIBLE);
        }
    }

    public void hienCauHoi() {
        setCauHoi();
        AmThanh();
        tien = 0;
        if (viTriCauHoi > 1) {
            tien = arrTienThuong.get(15 - (viTriCauHoi - 1));
        }
        txvThuong.setText(FaceData.FormatTienThuong(tien));
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        noiDung = decimalFormat.format(arrTienThuong.get(15 - viTriCauHoi)) + "";
        txvTienThuong.setText(noiDung);
        txvViTriCauHoi.setText("Câu " + viTriCauHoi);

        txvCauHoi.setText(cauHoi.getNoiDung());
        ArrayList<String> arrCauTraLoi = new ArrayList<>(cauHoi.getArrDapAnSai());
        arrCauTraLoi.add(cauHoi.getDapAnDung());

        Random r = new Random();
        for (int i = 0; i < 5; i++) {
            int vt1 = r.nextInt(arrCauTraLoi.size());
            int vt2 = r.nextInt(arrCauTraLoi.size());
            String a = arrCauTraLoi.get(vt1);
            arrCauTraLoi.set(vt1, arrCauTraLoi.get(vt2));
            arrCauTraLoi.set(vt2, a);
        }

        for (int i = 0; i < arrTxvCauTraLoi.size(); i++) {
            arrTxvCauTraLoi.get(i).setEnabled(true);
            arrTxvCauTraLoi.get(i).setBackgroundResource(R.drawable.bg_btn);
            arrTxvCauTraLoi.get(i).setText(arrCauTraLoi.get(i));
        }
        thoiGianConLai = tongThoiGian;
        ChayThoiGian();
    }

    public void AmThanh() {
        switch (viTriCauHoi) {
            case 1:
                TaoAmThanh(R.raw.cau1_5, R.raw.cau6_11chon, R.raw.cau1_4dung, R.raw.cau1_5sai);
                break;
            case 2:
                TaoAmThanh(R.raw.cau1_5, R.raw.cau7_12chon, R.raw.cau1_4dung, R.raw.cau1_5sai);
                break;
            case 3:
                TaoAmThanh(R.raw.cau1_5, R.raw.cau8_13chon, R.raw.cau1_4dung, R.raw.cau1_5sai);
                break;
            case 4:
                TaoAmThanh(R.raw.cau1_5, R.raw.cau9_14chon, R.raw.cau1_4dung, R.raw.cau1_5sai);
                break;
            case 5:
                TaoAmThanh(R.raw.cau1_5, R.raw.cau10_15chon, R.raw.cau5dung, R.raw.cau1_5sai);
                break;
            case 6:
                TaoAmThanh(R.raw.cau6, R.raw.cau6_11chon, R.raw.cau6_11dung, R.raw.cau6_11sai);
                break;
            case 7:
                TaoAmThanh(R.raw.cau7, R.raw.cau7_12chon, R.raw.cau7_12dung, R.raw.cau7_12sai);
                break;
            case 8:
                TaoAmThanh(R.raw.cau8, R.raw.cau8_13chon, R.raw.cau8_13dung, R.raw.cau8_13sai);
                break;
            case 9:
                TaoAmThanh(R.raw.cau9, R.raw.cau9_14chon, R.raw.cau9_14dung, R.raw.cau9_14sai);
                break;
            case 10:
                TaoAmThanh(R.raw.cau10, R.raw.cau10_15chon, R.raw.cau10dung, R.raw.cau10sai);
                break;
            case 11:
                TaoAmThanh(R.raw.cau11, R.raw.cau6_11chon, R.raw.cau11dung, R.raw.cau6_11sai);
                break;
            case 12:
                TaoAmThanh(R.raw.cau12, R.raw.cau7_12chon, R.raw.cau12dung, R.raw.cau7_12sai);
                break;
            case 13:
                TaoAmThanh(R.raw.cau13, R.raw.cau8_13chon, R.raw.cau13dung, R.raw.cau8_13sai);
                break;
            case 14:
                TaoAmThanh(R.raw.cau14, R.raw.cau9_14chon, R.raw.cau14dung, R.raw.cau9_14sai);
                break;
            case 15:
                TaoAmThanh(R.raw.cau15, R.raw.cau10_15chon, R.raw.cau15dung, R.raw.cau15sai);
                break;
        }
        mpNhacNen.setVolume(loaNhacNen, loaNhacNen);
        mpChon.setVolume(loaAmThanh, loaAmThanh);
        mpDung.setVolume(loaAmThanh, loaAmThanh);
        mpSai.setVolume(loaAmThanh, loaAmThanh);
        mpNhacNen.start();
    }

    public void trogiup5050(View view) {
        int sl5050 = Integer.parseInt(txvSL5050.getText().toString());
        if (isChecking | sl5050 == 0) {
            return;
        }
        view.setClickable(false);
        mpTroGiup5050.start();
        Random r = new Random();
        int soDapAnAnDi = 2;
        do {
            int viTriDapAnAn = r.nextInt(4);
            TextView t = arrTxvCauTraLoi.get(viTriDapAnAn);
            if (!t.getText().toString().equals(cauHoi.getDapAnDung()) && !t.getText().equals("")) {
                t.setText("");
                t.setEnabled(false);
                soDapAnAnDi--;
            }
        } while (soDapAnAnDi > 0);
        hoTro5050 = false;
        sl5050--;
        LuuDuLieu(1, sl5050);
        txvSL5050.setVisibility(View.INVISIBLE);
        ImageView imgXoaTroGiup5050 = findViewById(R.id.imgXoaTroGiup5050);
        imgXoaTroGiup5050.setVisibility(View.VISIBLE);
    }

    public void trogiupkhangia(View view) {
        int slKhanGia = Integer.parseInt(txvSLKhanGia.getText().toString());
        if (isChecking | slKhanGia == 0) {
            return;
        }
        view.setClickable(false);
        mpNhacNen.pause();
        DungThoiGian();
        mpTroGiupKhanGia.start();
        int vtD = 0, vtDapAn = 0;
        for (int i = 0; i < arrTxvCauTraLoi.size(); i++) {
            TextView t = arrTxvCauTraLoi.get(i);
            if (t.getText().equals(cauHoi.getDapAnDung())) {
                vtD = i;
            } else if (!t.getText().equals("")) {
                vtDapAn = i;
            }
        }
        DialogKhanGiaTraLoi dialogKhanGiaTraLoi = new DialogKhanGiaTraLoi(this, vtD, vtDapAn, hoTro5050);
        dialogKhanGiaTraLoi.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                mpTroGiupKhanGia.stop();
                mpNhacNen.start();
                ChayThoiGian();
            }
        });
        dialogKhanGiaTraLoi.show();
        slKhanGia--;
        LuuDuLieu(2, slKhanGia);
        txvSLKhanGia.setVisibility(View.INVISIBLE);
        ImageView imgXoaTroGiupKhanGia = findViewById(R.id.imgXoaTroGiupKhanGia);
        imgXoaTroGiupKhanGia.setVisibility(View.VISIBLE);
    }

    public void trogiupdoicauhoi(View view) {
        int slDoiCauHoi = Integer.parseInt(txvSLDoiCauHoi.getText().toString());
        if (isChecking | slDoiCauHoi == 0) {
            return;
        }
        view.setClickable(false);
        DungThoiGian();
        mpNhacNen.stop();
        mpChon.stop();
        mpDung.stop();
        mpSai.stop();
        mpNhacNen.reset();
        mpChon.reset();
        mpDung.reset();
        mpSai.reset();
        mpTroGiupDoiCauHoi.start();
        doiCauHoi = false;
        slDoiCauHoi--;
        LuuDuLieu(3, slDoiCauHoi);
        txvSLDoiCauHoi.setVisibility(View.INVISIBLE);
        ImageView imgXoaTroGiupDoiCauHoi = findViewById(R.id.imgXoaTroGiupDoiCauHoi);
        imgXoaTroGiupDoiCauHoi.setVisibility(View.VISIBLE);
        hienCauHoi();
    }

    public void trogiupchuyengia(View view) {
        int slChuyenGia = Integer.parseInt(txvSLChuyenGia.getText().toString());
        if (isChecking | slChuyenGia == 0) {
            return;
        }
        view.setClickable(false);
        mpNhacNen.pause();
        DungThoiGian();
        mpTroGiupChuyenGia.start();
        int vtD = 0;
        for (int i = 0; i < arrTxvCauTraLoi.size(); i++) {
            TextView t = arrTxvCauTraLoi.get(i);
            if (t.getText().equals(cauHoi.getDapAnDung())) {
                vtD = i;
            }
        }
        DialogChuyenGia dialogChuyenGia = new DialogChuyenGia(this, vtD);
        dialogChuyenGia.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                mpTroGiupChuyenGia.stop();
                mpNhacNen.start();
                ChayThoiGian();
            }
        });
        dialogChuyenGia.show();
        slChuyenGia--;
        LuuDuLieu(4, slChuyenGia);
        txvSLChuyenGia.setVisibility(View.INVISIBLE);
        ImageView imgXoaTroGiupChuyenGia = findViewById(R.id.imgXoaTroGiupChuyenGia);
        imgXoaTroGiupChuyenGia.setVisibility(View.VISIBLE);
    }

    public void dungcuocchoi(View view) {
        if (isChecking) {
            return;
        }
        isDungCuocChoi = true;
        isChecking = true;
        DungThoiGian();
        new DialogDungCuocChoi(this, tien).show();
    }

    public void tienthuong(View view) {
        if (isChecking) {
            return;
        }
        drawerLayout.openDrawer(GravityCompat.END);
    }

    public void trolai(View view) {
        drawerLayout.closeDrawer(GravityCompat.END);
    }

    public void TaoAmThanh(int nhacNen, int chon, int dung, int sai) {
        mpNhacNen = MediaPlayer.create(this, nhacNen);
        mpChon = MediaPlayer.create(this, chon);
        mpDung = MediaPlayer.create(this, dung);
        mpSai = MediaPlayer.create(this, sai);
    }

    public void XemKetQua(String ketqua) {
        viTriCauHoi--;
        mpNhacNen.stop();
        mpChon.stop();
        mpDung.stop();
        mpSai.stop();
        mpNhacNen.release();
        mpChon.release();
        mpDung.release();
        mpSai.release();
        mpTroGiupThu4.release();
        mpTroGiup5050.release();
        mpTroGiupKhanGia.release();
        mpTroGiupDoiCauHoi.release();
        mpTroGiupChuyenGia.release();
        Intent intent = new Intent(ChoiActivity.this, KetQuaActivity.class);
        intent.putExtra("ketqua", ketqua);
        intent.putExtra("kinhnghiem", viTriCauHoi);
        intent.putExtra("tien", tien);
        startActivity(intent);
    }

    public void ChayThoiGian() {
        cdtThoiGian = new CountDownTimer(thoiGianConLai, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                thoiGianConLai = (int) millisUntilFinished;
                int giay = thoiGianConLai / 1000;
                txvThoiGian.setText(String.valueOf(giay));
                pbgThoiGian.setProgress(giay);
            }

            @Override
            public void onFinish() {
                mpNhacNen.stop();
                mpSai.start();
                for (TextView t : arrTxvCauTraLoi) {
                    if (t.getText().toString().equals(cauHoi.getDapAnDung())) {
                        t.setBackgroundResource(R.drawable.bg_cau_dung);
                        break;
                    }
                }
                new CountDownTimer(4000, 100) {
                    @Override
                    public void onTick(long millisUntilFinished) {

                    }

                    @Override
                    public void onFinish() {
                        tien = 0;
                        if (viTriCauHoi > 5) {
                            tien = arrTienThuong.get(15 - (((viTriCauHoi - 1) / 5) * 5));
                        }
                        XemKetQua("thua");
                    }
                }.start();
            }
        }.start();
    }

    public void DungThoiGian() {
        cdtThoiGian.cancel();
    }

    public void LuuDuLieu(int id, int soluong) {
        SQLiteDatabase database = Database.initDatabase(this, FaceData.DATABASE_NAME);
        ContentValues contentValues = new ContentValues();
        contentValues.put("soluong", soluong);
        database.update("vatpham", contentValues, "id=?", new String[]{id + ""});
    }

    @Override
    public void onBackPressed() {

    }

    MediaPlayer mpNhacNen, mpChon, mpDung, mpSai, mpTroGiup5050, mpTroGiupKhanGia, mpTroGiupDoiCauHoi, mpTroGiupThu4, mpTroGiupChuyenGia;
    boolean isChecking = false, hoTro5050 = true, doiCauHoi = true, isDungCuocChoi = false;
    CountDownTimer cdtThoiGian;
    ProgressBar pbgThoiGian;
    TextView txvSL5050, txvSLKhanGia, txvSLDoiCauHoi, txvSLChuyenGia;
}