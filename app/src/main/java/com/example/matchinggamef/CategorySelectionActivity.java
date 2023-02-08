package com.example.matchinggamef;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

public class CategorySelectionActivity extends AppCompatActivity {

    ImageButton btnChude1, btnChude2, btnChude3, btnChude4;
    TextView tvTenNguoiChoi, tvTenCheDo;
    String tenNguoiChoi, tenCheDo;
    int cheDo;
    //
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_selection);

        // get views
        btnChude1 = findViewById(R.id.btnChude1);
        btnChude2 = findViewById(R.id.btnChude2);
        btnChude3 = findViewById(R.id.btnChude3);
        btnChude4 = findViewById(R.id.btnChude4);
        tvTenNguoiChoi = findViewById(R.id.tvTenNguoiChoi);
        tvTenCheDo = findViewById(R.id.textViewTenCheDo);


        // get name and map
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            tenNguoiChoi = extras.getString("ten");
            cheDo = extras.getInt("chedo");

            //tên chế độ 4x4 hay 5x4
            tenCheDo = extras.getString("tenCheDo");
            tvTenCheDo.setText("Chế độ: " + tenCheDo);
        }
        tvTenNguoiChoi.setText(tenNguoiChoi);

        //choose category
        btnChude1.setOnClickListener(view -> {
            //cheDo lấy từ FirstActivity
            if (cheDo == 4) {
                Intent intent = new Intent(getBaseContext(), MainActivity4x4.class);
                intent.putExtra("chuDeChoi", "hoaqua");
                intent.putExtra("tenNguoiChoi", tenNguoiChoi);
                intent.putExtra("tenCheDo", tenCheDo);
                startActivity(intent);
            } else if (cheDo == 5) {
                Intent intent = new Intent(getBaseContext(), MainActivity5x4.class);
                intent.putExtra("chuDeChoi", "hoaqua");
                intent.putExtra("tenNguoiChoi", tenNguoiChoi);
                intent.putExtra("tenCheDo", tenCheDo);
                startActivity(intent);
            } else if (cheDo == 6) {
                Intent intent = new Intent(getBaseContext(), MainActivity5x5.class);
                intent.putExtra("chuDeChoi", "hoaqua");
                intent.putExtra("tenNguoiChoi", tenNguoiChoi);
                intent.putExtra("tenCheDo", tenCheDo);
                startActivity(intent);
            }
        });

        btnChude2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (cheDo == 4) {
                    Intent intent = new Intent(getBaseContext(), MainActivity4x4.class);
                    intent.putExtra("chuDeChoi", "oto");
                    intent.putExtra("tenNguoiChoi", tenNguoiChoi);
                    intent.putExtra("tenCheDo", tenCheDo);
                    startActivity((intent));
                } else if (cheDo == 5) {
                    Intent intent = new Intent(getBaseContext(), MainActivity5x4.class);
                    intent.putExtra("chuDeChoi", "oto");
                    intent.putExtra("tenNguoiChoi", tenNguoiChoi);
                    intent.putExtra("tenCheDo", tenCheDo);
                    startActivity((intent));
                } else if (cheDo == 6) {
                    Intent intent = new Intent(getBaseContext(), MainActivity5x5.class);
                    intent.putExtra("chuDeChoi", "oto");
                    intent.putExtra("tenNguoiChoi", tenNguoiChoi);
                    intent.putExtra("tenCheDo", tenCheDo);
                    startActivity(intent);
                }
            }
        });


        btnChude3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (cheDo == 4) {
                    Intent intent = new Intent(getBaseContext(), MainActivity4x4.class);
                    intent.putExtra("chuDeChoi", "dongvat");
                    intent.putExtra("tenNguoiChoi", tenNguoiChoi);
                    intent.putExtra("tenCheDo", tenCheDo);
                    startActivity(intent);
                } else if (cheDo == 5) {
                    Intent intent = new Intent(getBaseContext(), MainActivity5x4.class);
                    intent.putExtra("chuDeChoi", "dongvat");
                    intent.putExtra("tenNguoiChoi", tenNguoiChoi);
                    intent.putExtra("tenCheDo", tenCheDo);
                    startActivity(intent);
                } else if (cheDo == 6) {
                    Intent intent = new Intent(getBaseContext(), MainActivity5x5.class);
                    intent.putExtra("chuDeChoi", "dongvat");
                    intent.putExtra("tenNguoiChoi", tenNguoiChoi);
                    intent.putExtra("tenCheDo", tenCheDo);
                    startActivity(intent);
                }
            }
        });
        btnChude4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (cheDo == 4) {
                    Intent intent = new Intent(getBaseContext(), MainActivity4x4.class);
                    intent.putExtra("chuDeChoi", "hoathinh");
                    intent.putExtra("tenNguoiChoi", tenNguoiChoi);
                    intent.putExtra("tenCheDo", tenCheDo);
                    startActivity(intent);
                } else if (cheDo == 5) {
                    Intent intent = new Intent(getBaseContext(), MainActivity5x4.class);
                    intent.putExtra("chuDeChoi", "hoathinh");
                    intent.putExtra("tenNguoiChoi", tenNguoiChoi);
                    intent.putExtra("tenCheDo", tenCheDo);
                    startActivity(intent);
                } else if (cheDo == 6) {
                    Intent intent = new Intent(getBaseContext(), MainActivity5x5.class);
                    intent.putExtra("chuDeChoi", "hoathinh");
                    intent.putExtra("tenNguoiChoi", tenNguoiChoi);
                    intent.putExtra("tenCheDo", tenCheDo);
                    startActivity(intent);
                }
            }
        });
    }
}