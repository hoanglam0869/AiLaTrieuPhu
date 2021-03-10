package com.hoanglam0869.ailatrieuphu.dialog;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.hoanglam0869.ailatrieuphu.R;

import java.util.ArrayList;
import java.util.Random;

public class DialogKhanGiaTraLoi extends Dialog {
    ImageView imgDong;
    TextView txvChonA, txvChonB, txvChonC, txvChonD;
    ProgressBar pgbChonA, pgbChonB, pgbChonC, pgbChonD;
    ArrayList<TextView> arrTxvChon;
    ArrayList<ProgressBar> arrPbgChon;
    ValueAnimator valueAnimator;
    ObjectAnimator objectAnimator;
    int time = 4000;

    public DialogKhanGiaTraLoi(@NonNull Context context, int vtD, int vtDapAn, boolean hoTro5050) {
        super(context);
        setContentView(R.layout.dialog_tro_giup_khan_gia);
        getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        AnhXa();
        SetUp();

        Random r = new Random();
        if (!hoTro5050) {
            int ptDung = r.nextInt(50) + 50;
            int ptSai = 100 - ptDung;

            AddControls5050(vtD, vtDapAn, ptDung, ptSai);
        } else {
            int ptDung = r.nextInt(50) + 50;
            int ptSai1 = r.nextInt(100 - ptDung);
            int ptSai2 = r.nextInt(100 - ptDung - ptSai1);
            int ptSai3 = 100 - ptDung - ptSai1 - ptSai2;

            switch (vtD) {
                case 0:
                    AddControls(ptDung, ptSai1, ptSai2, ptSai3);
                    break;
                case 1:
                    AddControls(ptSai1, ptDung, ptSai2, ptSai3);
                    break;
                case 2:
                    AddControls(ptSai1, ptSai2, ptDung, ptSai3);
                    break;
                case 3:
                    AddControls(ptSai1, ptSai2, ptSai3, ptDung);
                    break;
            }
        }
    }

    private void AddControls(int pt1, int pt2, int pt3, int pt4) {
        valueAnimator = ValueAnimator.ofInt(0, pt1);
        valueAnimator.setDuration(time);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                txvChonA.setText(animation.getAnimatedValue().toString() + "%");
            }
        });
        valueAnimator.start();
        objectAnimator = ObjectAnimator.ofInt(pgbChonA, "progress", 0, pt1);
        objectAnimator.setDuration(time);
        objectAnimator.start();

        valueAnimator = ValueAnimator.ofInt(0, pt2);
        valueAnimator.setDuration(time);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                txvChonB.setText(animation.getAnimatedValue().toString() + "%");
            }
        });
        valueAnimator.start();
        objectAnimator = ObjectAnimator.ofInt(pgbChonB, "progress", 0, pt2);
        objectAnimator.setDuration(time);
        objectAnimator.start();

        valueAnimator = ValueAnimator.ofInt(0, pt3);
        valueAnimator.setDuration(time);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                txvChonC.setText(animation.getAnimatedValue().toString() + "%");
            }
        });
        valueAnimator.start();
        objectAnimator = ObjectAnimator.ofInt(pgbChonC, "progress", 0, pt3);
        objectAnimator.setDuration(time);
        objectAnimator.start();

        valueAnimator = ValueAnimator.ofInt(0, pt4);
        valueAnimator.setDuration(time);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                txvChonD.setText(animation.getAnimatedValue().toString() + "%");
            }
        });
        valueAnimator.start();
        objectAnimator = ObjectAnimator.ofInt(pgbChonD, "progress", 0, pt4);
        objectAnimator.setDuration(time);
        objectAnimator.start();
    }

    private void AddControls5050(int vtD, int vtDapAn, int ptDung, int ptSai) {
        valueAnimator = ValueAnimator.ofInt(0, ptDung);
        valueAnimator.setDuration(time);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                arrTxvChon.get(vtD).setText(animation.getAnimatedValue().toString() + "%");
            }
        });
        valueAnimator.start();
        objectAnimator = ObjectAnimator.ofInt(arrPbgChon.get(vtD), "progress", 0, ptDung);
        objectAnimator.setDuration(time);
        objectAnimator.start();

        valueAnimator = ValueAnimator.ofInt(0, ptSai);
        valueAnimator.setDuration(time);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                arrTxvChon.get(vtDapAn).setText(animation.getAnimatedValue().toString() + "%");
            }
        });
        valueAnimator.start();
        objectAnimator = ObjectAnimator.ofInt(arrPbgChon.get(vtDapAn), "progress", 0, ptSai);
        objectAnimator.setDuration(time);
        objectAnimator.start();
    }

    private void SetUp() {
        for (int i = 0; i < arrTxvChon.size(); i++) {
            arrTxvChon.get(i).setText("0%");
            arrPbgChon.get(i).setProgress(0);
        }
        imgDong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
    }

    private void AnhXa() {
        imgDong = findViewById(R.id.imageViewDongTroGiupKhanGia);
        txvChonA = findViewById(R.id.txvChonA);
        txvChonB = findViewById(R.id.txvChonB);
        txvChonC = findViewById(R.id.txvChonC);
        txvChonD = findViewById(R.id.txvChonD);
        pgbChonA = findViewById(R.id.progressBarChonA);
        pgbChonB = findViewById(R.id.progressBarChonB);
        pgbChonC = findViewById(R.id.progressBarChonC);
        pgbChonD = findViewById(R.id.progressBarChonD);

        arrTxvChon = new ArrayList<>();
        arrTxvChon.add(txvChonA);
        arrTxvChon.add(txvChonB);
        arrTxvChon.add(txvChonC);
        arrTxvChon.add(txvChonD);

        arrPbgChon = new ArrayList<>();
        arrPbgChon.add(pgbChonA);
        arrPbgChon.add(pgbChonB);
        arrPbgChon.add(pgbChonC);
        arrPbgChon.add(pgbChonD);
    }
}
