package com.example.myapplication02;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import model.Data;
import model.User;

public class MainActivity extends AppCompatActivity {

    private RecyclerView main_recyclerView;
    private ArrayList<User> listUser = Data.saveList;
    private UserRVAdapter adapter;
    private FloatingActionButton main_FAB_add;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        initView();
        setupRecyclerView();
        addDummyData();
        setListener();
        getUpdatedData();
    }

    public void getUpdatedData() {
        int position = getIntent().getIntExtra("position", 0);

        User user = getIntent().getParcelableExtra("orangEdit");
        if (user != null) {
            listUser.remove(position);
            listUser.add(position, user);
            adapter.notifyItemChanged(position);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1) {
            if (resultCode == 200) {
                User adduser = data.getParcelableExtra("adduser");
                listUser.add(adduser);
                adapter.notifyDataSetChanged();
            }
        }

    }

    private void setListener() {
        main_FAB_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), AddUserActivity.class);
                startActivityForResult(intent, 1);
            }
        });
    }

    private void addDummyData() {
        adapter.notifyDataSetChanged();
    }

    private void setupRecyclerView() {
        RecyclerView.LayoutManager manager = new LinearLayoutManager(getBaseContext());
        main_recyclerView.setLayoutManager(manager);
        main_recyclerView.setAdapter(adapter);
    }

    private void initView() {
        main_recyclerView = findViewById(R.id.main_recyclerView);
        adapter = new UserRVAdapter(listUser, this);
        main_FAB_add = findViewById(R.id.addbutton);
    }

    private static final int TIME_INTERVAL = 2000;
    private long doubleClick;

    @Override
    public void onBackPressed() {
        if (doubleClick + TIME_INTERVAL > System.currentTimeMillis()) {
            super.onBackPressed();
            return;
        } else {
            Toast.makeText(getBaseContext(), "Press back again to exit", Toast.LENGTH_SHORT).show();
        }
        doubleClick = System.currentTimeMillis();
    }
}