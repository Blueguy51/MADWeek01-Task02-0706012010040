package com.example.myapplication02;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputLayout;

import java.util.regex.Pattern;

import model.User;

public class AddUserActivity extends AppCompatActivity {

    private TextInputLayout add_textInputLayout_fullname;
    private TextInputLayout add_textInputLayout_age;
    private TextInputLayout add_textInputLayout_address;
    private Button add_button;
    public User user;
    public int position;
    public TextView textView_toolbar_title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.adduser);

        user = getIntent().getParcelableExtra("data");
        position = getIntent().getIntExtra("position", 0);

        initView();
        setListener();
    }

    private void setListener() {
        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (user != null) {
                    editData();
                } else {
                    addData();
                }
            }
        });
    }

    private void initView() {
        add_textInputLayout_fullname = findViewById(R.id.add_textInputLayout_fullname);
        add_textInputLayout_age = findViewById(R.id.add_textInputLayout_age);
        add_textInputLayout_address = findViewById(R.id.add_textInputLayout_address);
        add_button = findViewById(R.id.save_button);

        add_textInputLayout_fullname.getEditText().addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String fullname = add_textInputLayout_fullname.getEditText().getText().toString().trim();

                Pattern TITLE_PATTERN = Pattern.compile("[a-zA-Z0-9\\!\\@\\#\\$]{0,100}");

                if (fullname.length() < 0 || fullname.length() > 100) {
                    add_textInputLayout_fullname.setError("Maximum Character Reached!");
                } else {
                    add_textInputLayout_fullname.setError("");
                }
                add_button.setEnabled(!fullname.isEmpty());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        add_button = (Button) findViewById(R.id.save_button);
        showData();
    }

    public void showData() {
        if (user != null) {
            add_textInputLayout_fullname.getEditText().setText(user.getNama());
            add_textInputLayout_age.getEditText().setText(String.valueOf(user.getAge()));
            add_textInputLayout_address.getEditText().setText(user.getAddress());
            textView_toolbar_title.setText("Edit User Data");
            add_button.setText("Update Data");
        } else {
            textView_toolbar_title.setText("Add User Data");
            add_button.setText("Save Data");
        }
    }

    public void editData() {
        String nama = add_textInputLayout_fullname.getEditText().getText().toString().trim();
        String address = add_textInputLayout_address.getEditText().getText().toString().trim();
        int age = Integer.parseInt(add_textInputLayout_age.getEditText().getText().toString().trim());
        User temp = new User(nama, address, age);

        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("edituser", temp);
        intent.putExtra("position", position);
        startActivity(intent);
        finish();
    }

    public void addData() {
        String nama = add_textInputLayout_fullname.getEditText().getText().toString().trim();
        String address = add_textInputLayout_address.getEditText().getText().toString().trim();
        int age = Integer.parseInt(add_textInputLayout_age.getEditText().getText().toString().trim());
        User temp = new User(nama, address, age);

        Intent intent = new Intent();
        intent.putExtra("adduser", temp);
        setResult(200, intent);
        finish();
    }
}