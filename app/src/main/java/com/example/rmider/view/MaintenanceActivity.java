package com.example.rmider.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.rmider.R;
import com.example.rmider.adapter.MaintainAdapter;
import com.example.rmider.model.Maintain;
import com.example.rmider.utils.AccountUtils;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class MaintenanceActivity extends AppCompatActivity {

    private RecyclerView   rycMaintain;
    private Toolbar toolbarMaintain;
    private ArrayList<Maintain> arrmaintain = new ArrayList<>();
    private MaintainAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maintenance);
        
        initView();
        setupToolbar();
        initRecyclerview();
        initData();
    }

    private void initData() {
        FirebaseDatabase.getInstance().getReference()
                .child("Maintain")
                .child(AccountUtils.getInstance().getAccount().getPhoneNumber())
                .addChildEventListener(new ChildEventListener() {
                    @Override
                    public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                        if (dataSnapshot.exists()){
                            Maintain maintain = dataSnapshot.getValue(Maintain.class);
                            arrmaintain.add(maintain);
                            adapter.notifyItemInserted(arrmaintain.size() - 1);
                        }
                    }

                    @Override
                    public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                    }

                    @Override
                    public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

                    }

                    @Override
                    public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

    }

    private void initRecyclerview() {
        rycMaintain.setHasFixedSize(true);
        rycMaintain.setLayoutManager(new LinearLayoutManager(this));
        adapter = new MaintainAdapter(arrmaintain);
        rycMaintain.setAdapter(adapter);
    }
    
    public void onClickAdd (View view) {
        startActivity(new Intent(getApplicationContext(),AddScheduleActivity.class));
    }

    private void setupToolbar() {
        setSupportActionBar(toolbarMaintain);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_arrow_back_black_24dp);
    }

    private void initView() {
        rycMaintain     = findViewById(R.id.rycMaintain);
        toolbarMaintain = findViewById(R.id.toolbarMaintain);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        finish();
        return super.onOptionsItemSelected(item);
    }
}
