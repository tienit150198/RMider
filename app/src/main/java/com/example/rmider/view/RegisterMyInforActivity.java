package com.example.rmider.view;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.rmider.R;
import com.example.rmider.config.Constants;
import com.example.rmider.model.Account;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class RegisterMyInforActivity extends AppCompatActivity {

    private EditText edtPhoneNumber,edtPassword,edtName,edtBirthday,edtPosition,edtCMND;
    private RadioButton rbMale,fbFemale;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_my_infor);

        initView();
    }

    private void initView() {
        edtPhoneNumber  = findViewById(R.id.edtPhoneNumber);
        edtPassword     = findViewById(R.id.edtPassword);
        edtName         = findViewById(R.id.edtName);
        edtBirthday     = findViewById(R.id.edtBirthday);
        edtPosition     = findViewById(R.id.edtPosition);
        rbMale          = findViewById(R.id.rbMale);
        fbFemale        = findViewById(R.id.fbFemale);
        edtCMND         = findViewById(R.id.edtCMND);
    }

    public void onClickNext(View view) {
        final String phoneNumber  = edtPhoneNumber.getText().toString();
        final String password     = edtPassword.getText().toString();
        final String name         = edtName.getText().toString();
        final String birthday     = edtBirthday.getText().toString();
        final String position     = edtPosition.getText().toString();
        final String cmnd         = edtCMND.getText().toString();

        if (TextUtils.isEmpty(phoneNumber)){
            Toast.makeText(this, "Bạn chưa điền số điện thoại", Toast.LENGTH_SHORT).show();
            return;
        }

        if (phoneNumber.length() != 10){
            Toast.makeText(this, "Số điện thoại không hợp lệ", Toast.LENGTH_SHORT).show();
            return;
        }

        if (TextUtils.isEmpty(password)){
            Toast.makeText(this, "Bạn chưa điền mật khẩu", Toast.LENGTH_SHORT).show();
            return;
        }

        if (password.length() < 6){
            Toast.makeText(this, "Mật khẩu phải có ít nhất 6 kí tự", Toast.LENGTH_SHORT).show();
            return;
        }

        if (TextUtils.isEmpty(name)){
            Toast.makeText(this, "Bạn chưa điền họ tên", Toast.LENGTH_SHORT).show();
            return;
        }

        if (TextUtils.isEmpty(birthday)){
            Toast.makeText(this, "Bạn chưa điền ngày sinh", Toast.LENGTH_SHORT).show();
            return;
        }

        if (TextUtils.isEmpty(position)){
            Toast.makeText(this, "Bạn chưa điền địa chỉ", Toast.LENGTH_SHORT).show();
            return;
        }

        if (TextUtils.isEmpty(cmnd)){
            Toast.makeText(this, "Bạn chưa điền địa chỉ", Toast.LENGTH_SHORT).show();
            return;
        }

        FirebaseDatabase.getInstance().getReference()
                .child("Account")
                .child(phoneNumber)
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if (dataSnapshot.exists()){
                            Toast.makeText(getApplicationContext(), "Số điện thoại đã được đăng ký", Toast.LENGTH_SHORT).show();
                        }else {
                            Account account = new Account();
                            account.setPhoneNumber(phoneNumber);
                            account.setPassword(password);
                            account.setName(name);
                            account.setBirthday(birthday);
                            account.setPosition(position);
                            account.setGender(rbMale.isChecked());
                            account.setCmnd(cmnd);

                            Intent intent = new Intent(getApplicationContext(),RegisterMyAvatarActivity.class);
                            intent.putExtra(Constants.KEY_ACCOUNT,account);
                            startActivity(intent);
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
    }
}
