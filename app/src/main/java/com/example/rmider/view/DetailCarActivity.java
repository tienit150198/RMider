package com.example.rmider.view;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.rmider.R;
import com.example.rmider.config.Constants;
import com.example.rmider.model.Account;
import com.example.rmider.model.Car;
import com.example.rmider.utils.AccountUtils;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.UploadTask;

import java.io.File;

public class DetailCarActivity extends AppCompatActivity {

    private static final int REQUEST_TAKE_PICTURE = 2;

    private Uri uri;

    private ImageView imgCar;
    private TextView txtName,txtTypeBike,txtColor,txtLicensePlates,txtNumberFrames,txtInformation;
    private ProgressDialog progressDialog;

    private Account account = AccountUtils.getInstance().getAccount();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_car);

        initView();
        initUI();
    }

    private void initUI() {
        Car myCar = account.getMyCar();

        txtName.setText(myCar.getModelName());
        txtTypeBike.setText(myCar.getType() == Constants.TYPE_MOTO ? "Moto" : "Oto");
        txtColor.setText(myCar.getColor());
        txtLicensePlates.setText(myCar.getLicensePlates());
        txtNumberFrames.setText(myCar.getNumberFrames());
        txtInformation.setText(myCar.getTechnicalInformation());
    }

    private void initView() {
        imgCar = findViewById(R.id.imgCar);
        txtName = findViewById(R.id.txtName);
        txtTypeBike = findViewById(R.id.txtTypeBike);
        txtColor = findViewById(R.id.txtColor);
        txtLicensePlates = findViewById(R.id.txtLicensePlates);
        txtNumberFrames = findViewById(R.id.txtNumberFrames);
        txtInformation = findViewById(R.id.txtInformation);

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Vui lòng chờ...");
        progressDialog.setCancelable(false);
    }

    public void onClickCarImage(View view) {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        File file = new File(getExternalCacheDir(), System.currentTimeMillis() + ".jpg");
        uri = Uri.fromFile(file);
        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
        startActivityForResult(intent, REQUEST_TAKE_PICTURE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_TAKE_PICTURE && resultCode == RESULT_OK){
            Glide.with(this)
                    .load(uri)
                    .into(imgCar);

            uploadAvatar();
        }
    }

    private void uploadAvatar() {
        progressDialog.show();

        FirebaseStorage.getInstance().getReference().child("Image").child(System.currentTimeMillis() + ".png").putFile(uri)
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        progressDialog.cancel();
                        Toast.makeText(getApplicationContext(), "Tải lên hình ảnh thất bại", Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {
                        progressDialog.cancel();

                        String picture = task.getResult().getTask().toString();

                        account.setAvatar(picture);

                        updateAvatarDb(picture);
                    }
                });
    }

    private void updateAvatarDb(String picture) {
        FirebaseDatabase.getInstance().getReference()
                .child("Account")
                .child(account.getPhoneNumber())
                .child("myCar")
                .child("picture")
                .setValue(picture);

        AccountUtils.getInstance().setAccount(account);
    }
}
