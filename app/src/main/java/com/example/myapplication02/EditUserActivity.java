package com.example.myapplication02;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

import model.Data;
import model.User;

public class EditUserActivity extends AppCompatActivity {

    public User user;
    public int position;
    public ArrayList<User> listUser = Data.saveList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edituser);

        user = getIntent().getParcelableExtra("data");
        position = getIntent().getIntExtra("position", 0);
        TextView user_cardview = findViewById(R.id.user_cardview);
        TextView display_textView_age = findViewById(R.id.cardview_age_result);
        TextView display_textView_address = findViewById(R.id.cardview_address_result);
        Button detail_button_edit = findViewById(R.id.detail_button_edit);
        Button detail_button_delete = findViewById(R.id.detail_button_delete);

        user_cardview.setText(user.getNama());
        display_textView_age.setText(String.valueOf(user.getAge()));
        display_textView_address.setText(user.getAddress());

        detail_button_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigateToEditPage();
            }
        });

        detail_button_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertMessage();
            }
        });

    }

    public void navigateToEditPage() {
        Intent intent = new Intent(this, com.example.myapplication02.AddUserActivity.class);
        intent.putExtra("data", user);
        intent.putExtra("position", position);
        startActivity(intent);
        finish();
    }

    public void alertMessage() {
        DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                switch (which) {
                    case DialogInterface.BUTTON_POSITIVE:

                        listUser.remove(position);
                        Log.d("test", String.valueOf(position));

                        Toast.makeText(EditUserActivity.this, "Delete Success",
                                Toast.LENGTH_LONG).show();

                        Intent intent = new Intent(EditUserActivity.this, MainActivity.class);
                        startActivity(intent);

                    case DialogInterface.BUTTON_NEGATIVE:
                        dialog.dismiss();
                }
            }
        };

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Hapus Data");
        builder.setMessage("Yakin ingin hapus data?")
                .setPositiveButton("Hapus", dialogClickListener)
                .setNegativeButton("Batal", dialogClickListener).show();
    }
}