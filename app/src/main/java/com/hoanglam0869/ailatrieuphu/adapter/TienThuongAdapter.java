package com.hoanglam0869.ailatrieuphu.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.hoanglam0869.ailatrieuphu.R;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class TienThuongAdapter extends ArrayAdapter<Integer> {
    Context mct;
    ArrayList<Integer> arr;
    int viTriCauHoi = 0;

    public TienThuongAdapter(@NonNull Context context, int resource, @NonNull List<Integer> objects) {
        super(context, resource, objects);
        this.mct = context;
        this.arr = new ArrayList<>(objects);
    }

    public void setViTriCauHoi(int viTriCauHoi) {
        this.viTriCauHoi = viTriCauHoi;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) mct.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.item_tien_thuong, null);
        }
        if (arr.size() > 0) {
            int pos = 15 - position;
            LinearLayout item = convertView.findViewById(R.id.item);
            TextView txvViTriCauHoi = convertView.findViewById(R.id.txvViTriCauHoi);
            ImageView imgDaTraLoi = convertView.findViewById(R.id.imgDaTraLoi);
            TextView txvTienThuong = convertView.findViewById(R.id.txvTienThuong);
            if (position % 5 == 0) {
                txvTienThuong.setTextColor(Color.parseColor("#FFFFFF"));
                txvViTriCauHoi.setTextColor(Color.parseColor("#FFFFFF"));
            } else {
                txvTienThuong.setTextColor(Color.parseColor("#FF9800"));
                txvViTriCauHoi.setTextColor(Color.parseColor("#FF9800"));
            }
            String textHienThi = pos + "";
            txvViTriCauHoi.setText(textHienThi);
            DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
            textHienThi = decimalFormat.format(arr.get(position)) + "";
            txvTienThuong.setText(textHienThi);

            if (pos == viTriCauHoi) {
                item.setBackgroundResource(R.drawable.bg_cau_chon);
                imgDaTraLoi.setImageResource(R.drawable.diamond);
                if (pos % 5 == 0) {
                    txvTienThuong.setTextColor(Color.parseColor("#FFFFFF"));
                    txvViTriCauHoi.setTextColor(Color.parseColor("#FFFFFF"));
                } else {
                    txvTienThuong.setTextColor(Color.parseColor("#000000"));
                    txvViTriCauHoi.setTextColor(Color.parseColor("#000000"));
                }
            } else if (pos < viTriCauHoi) {
                item.setBackgroundResource(R.drawable.bg_btn);
                textHienThi = pos + "";
                imgDaTraLoi.setImageResource(R.drawable.diamond);
                txvViTriCauHoi.setText(textHienThi);
                if (position % 5 == 0) {
                    txvTienThuong.setTextColor(Color.parseColor("#FFFFFF"));
                    txvViTriCauHoi.setTextColor(Color.parseColor("#FFFFFF"));
                } else {
                    txvTienThuong.setTextColor(Color.parseColor("#FF9800"));
                    txvViTriCauHoi.setTextColor(Color.parseColor("#FF9800"));
                }
            }
        }
        return convertView;
    }
}
