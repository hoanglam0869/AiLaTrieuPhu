package com.hoanglam0869.ailatrieuphu.object;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.hoanglam0869.ailatrieuphu.Database;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Random;

public class FaceData {
    public static String DATABASE_NAME = "ailatrieuphu.sqlite";
    SQLiteDatabase database;
    Cursor cursor1, cursor2, cursor3;

    public static String FormatTienThuong(int tien) {
        String noiDung;
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###.#");
        if (tien < 1000000) {
            noiDung = (tien / 1000) + "K";
        } else if (tien < 1000000000) {
            noiDung = decimalFormat.format(tien / 1000000) + "M";
        } else {
            noiDung = decimalFormat.format(tien / 1000000000) + "B";
        }
        return noiDung;
    }

    public ArrayList<CauHoi> question(Activity activity) {
        database = Database.initDatabase(activity, DATABASE_NAME);

        cursor1 = database.rawQuery("SELECT * FROM ailatrieuphu WHERE cau=1 ORDER BY RANDOM() LIMIT 6", null);
        cursor2 = database.rawQuery("SELECT * FROM ailatrieuphu WHERE cau=2 ORDER BY RANDOM() LIMIT 6", null);
        cursor3 = database.rawQuery("SELECT * FROM ailatrieuphu WHERE cau=3 ORDER BY RANDOM() LIMIT 6", null);

        ArrayList<Cursor> arrCursor = new ArrayList<>();
        arrCursor.add(cursor1);
        arrCursor.add(cursor2);
        arrCursor.add(cursor3);

        ArrayList<CauHoi> arrCauHoi = new ArrayList<>();
        for (int i = 0; i < arrCursor.size(); i++) {
            arrCursor.get(i).moveToFirst();
            do {
                ArrayList<String> arrDapAnSai = new ArrayList<>();
                arrDapAnSai.add(arrCursor.get(i).getString(3));
                arrDapAnSai.add(arrCursor.get(i).getString(4));
                arrDapAnSai.add(arrCursor.get(i).getString(5));
                arrCauHoi.add(new CauHoi(arrCursor.get(i).getString(1), arrCursor.get(i).getString(2), arrDapAnSai));
            } while (arrCursor.get(i).moveToNext());
        }
        return arrCauHoi;
    }

    public static ArrayList<VatPham> vatpham(Activity activity, String loai) {
        SQLiteDatabase database = Database.initDatabase(activity, DATABASE_NAME);
        Cursor cursor;
        if (loai.equals("tatca")) {
            cursor = database.rawQuery("SELECT * FROM vatpham", null);
        } else {
            cursor = database.rawQuery("SELECT * FROM vatpham WHERE loai='" + loai + "'", null);
        }
        cursor.moveToFirst();
        ArrayList<VatPham> arrVatPham = new ArrayList<>();
        do {
            arrVatPham.add(new VatPham(cursor.getInt(0), cursor.getString(2), cursor.getBlob(3), cursor.getInt(4), cursor.getInt(5), cursor.getString(6), cursor.getString(7)));
        } while (cursor.moveToNext());
        return arrVatPham;
    }
}
