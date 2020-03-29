package com.example.rmider.view;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.rmider.R;
import com.example.rmider.model.Maintain;
import com.example.rmider.utils.AccountUtils;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddScheduleActivity extends AppCompatActivity {

    private EditText edtDate,edtDetailMaintaine,edtKm,edtPrice,edtNextDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_schedule);

        initView();
    }

    private void initView() {
        edtDate            = findViewById(R.id.edtDate);
        edtDetailMaintaine = findViewById(R.id.edtDetailMaintaine);
        edtKm              = findViewById(R.id.edtKm);
        edtPrice           = findViewById(R.id.edtPrice);
        edtNextDate        = findViewById(R.id.edtNextDate);
    }

    public void onClickAdd(View view) {
        final String date   = edtDate.getText().toString();
        final String detail = edtDetailMaintaine.getText().toString();
        final String km     = edtKm.getText().toString();
        final String price  = edtPrice.getText().toString();
        final String nextday  = edtNextDate.getText().toString();

        if(TextUtils.isEmpty(date)){
            Toast.makeText(AddScheduleActivity.this, "Bạn chưa nhập ngày bảo dưỡng", Toast.LENGTH_SHORT).show();
            return;
        }

        if(TextUtils.isEmpty(detail)){
            Toast.makeText(AddScheduleActivity.this, "Bạn chưa nhập chi tiết bảo dưỡng", Toast.LENGTH_SHORT).show();
            return;
        }

        if(TextUtils.isEmpty(km)){
            Toast.makeText(AddScheduleActivity.this, "Bạn chưa nhập số Km đã đi", Toast.LENGTH_SHORT).show();
            return;
        }

        if(TextUtils.isEmpty(price)){
            Toast.makeText(AddScheduleActivity.this, "Bạn chưa nhập chi phí bảo dưỡng", Toast.LENGTH_SHORT).show();
            return;
        }

        if(TextUtils.isEmpty(nextday)){
            Toast.makeText(AddScheduleActivity.this, "Bạn chưa nhập ngày bảo dưỡng tiếp theo", Toast.LENGTH_SHORT).show();
            return;
        }

        String key = "Key:" + System.currentTimeMillis();

        Maintain maintain = new Maintain();
        maintain.setNgay(date);
        maintain.setDetail(detail);
        maintain.setSoKm(km);
        maintain.setPrice(price);
        maintain.setNgaytt(nextday);
        maintain.setKey(key);

        FirebaseDatabase.getInstance().getReference()
                .child("Maintain")
                .child(AccountUtils.getInstance().getAccount().getPhoneNumber())
                .child(key)
                .setValue(maintain, new DatabaseReference.CompletionListener() {
                    @Override
                    public void onComplete(@Nullable DatabaseError databaseError, @NonNull DatabaseReference databaseReference) {
                        if (databaseError == null){
                            Toast.makeText(AddScheduleActivity.this, "Thêm lịch bảo dưỡng thành công", Toast.LENGTH_SHORT).show();
                            finish();
                        }else {
                            Toast.makeText(AddScheduleActivity.this, "Thêm lịch bảo dưỡng thất bại", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}
