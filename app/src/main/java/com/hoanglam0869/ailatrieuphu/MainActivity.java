package com.hoanglam0869.ailatrieuphu;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.hoanglam0869.ailatrieuphu.dialog.DialogCaNhan;
import com.hoanglam0869.ailatrieuphu.dialog.DialogCaiDat;
import com.hoanglam0869.ailatrieuphu.dialog.DialogCuaHang;
import com.hoanglam0869.ailatrieuphu.object.FaceData;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    public static MediaPlayer mediaPlayer;
    ImageView imgLogo;
    float loaNhacNen, loaAmThanh;
    public static int tongKinhNghiem, tongTien;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AnhXa();
        SetAnimation();
        LayDuLieu();
        ChayNhac();
    }

    private void ChayNhac() {
        mediaPlayer = MediaPlayer.create(this, R.raw.nhacmodau);
        mediaPlayer.setVolume(loaNhacNen, loaNhacNen);
        mediaPlayer.start();
    }

    private void LayDuLieu() {
        SharedPreferences sp = getSharedPreferences("caidat", MODE_PRIVATE);
        loaNhacNen = sp.getFloat("loanhacnen", 1f);
        loaAmThanh = sp.getFloat("loaamthanh", 1f);
        tongKinhNghiem = sp.getInt("tongkinhnghiem", 0);
        tongTien = sp.getInt("tongtien", 0);
    }

    private void SetAnimation() {
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.logo_rotate);
        imgLogo.startAnimation(animation);
    }

    private void AnhXa() {
        imgLogo = findViewById(R.id.imageViewLogo);
    }

    public void batdau(View view) {
        mediaPlayer.stop();
        mediaPlayer.release();
        Intent i = new Intent(this, ChoiActivity.class);
        startActivity(i);
    }

    public void caidat(View view) {
        new DialogCaiDat(MainActivity.this).show();
    }

    public void cuahang(View view) {
        new DialogCuaHang(MainActivity.this).show();
    }

    public static void SetVolume(float loaNhacNen) {
        mediaPlayer.setVolume(loaNhacNen, loaNhacNen);
    }

    @Override
    public void onBackPressed() {
        
    }

    public void canhan(View view) {
        new DialogCaNhan(MainActivity.this).show();
    }
}