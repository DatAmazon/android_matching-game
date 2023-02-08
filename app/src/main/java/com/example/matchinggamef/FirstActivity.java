package com.example.matchinggamef;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class FirstActivity extends AppCompatActivity {

    Button btn4x4, btn5x4, btn5x5;
    String tenNguoiChoi;

    String cheDo4x4 = "4x4", CheDo5x4 = "5x4", CheDo5x5 = "5x5";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);

        // get views
        btn4x4 = findViewById(R.id.btn4x4);
        btn5x4 = findViewById(R.id.btn5x4);
        btn5x5 = findViewById(R.id.btn5x5);

        EditText etNhapTen = (EditText) findViewById(R.id.etNhapTen);
        btn4x4.setOnClickListener(view ->

        {
            tenNguoiChoi = etNhapTen.getText().toString();
            Intent intent = new Intent(getBaseContext(), CategorySelectionActivity.class);
            if (!tenNguoiChoi.isEmpty()) {
                intent.putExtra("ten", tenNguoiChoi);
            } else {
                intent.putExtra("ten", "Người nào đó");
            }

            intent.putExtra("chedo", 5);
            intent.putExtra("tenCheDo", CheDo5x4);
            startActivity(intent);
        });


        btn5x4.setOnClickListener(view ->

        {
            tenNguoiChoi = etNhapTen.getText().toString();
            Intent intent = new Intent(getBaseContext(), CategorySelectionActivity.class);
            if (!tenNguoiChoi.isEmpty()) {
                intent.putExtra("ten", tenNguoiChoi);
            } else {
                intent.putExtra("ten", "Người nào đó");
            }

            intent.putExtra("chedo", 5);
            intent.putExtra("tenCheDo", CheDo5x4);
            startActivity(intent);
        });

        btn5x5.setOnClickListener(view ->

        {
            tenNguoiChoi = etNhapTen.getText().toString();
            Intent intent = new Intent(getBaseContext(), CategorySelectionActivity.class);
            if (!tenNguoiChoi.isEmpty()) {
                intent.putExtra("ten", tenNguoiChoi);
            } else {
                intent.putExtra("ten", "Người nào đó");
            }

            intent.putExtra("chedo", 6);
            intent.putExtra("tenCheDo", CheDo5x5);
            startActivity(intent);
        });
    }
}