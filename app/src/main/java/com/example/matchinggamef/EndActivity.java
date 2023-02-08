package com.example.matchinggamef;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.File;
import java.io.FileOutputStream;

public class EndActivity extends AppCompatActivity {

    TextView tvTenNguoiDaChoi, tvTongSoLanLat, tvTenCheDo;
    String tenNguoiChoi, tongSoLanLat, tenCheDo;
    int cheDo;
    Button btnShare;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_end);

        tvTenCheDo = findViewById(R.id.tvCheDoChoiEnd);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            tenNguoiChoi = extras.getString("tenNguoiChoi");
            tongSoLanLat = extras.getString("tongSoLanLat");
            cheDo = extras.getInt("chedo");

            tenCheDo = extras.getString("tenCheDo");
            tvTenCheDo.setText("Chế độ: " + tenCheDo);

        }
        tvTenNguoiDaChoi = findViewById(R.id.tvTenNguoiDaChoi);
        tvTenNguoiDaChoi.setText(tenNguoiChoi);
        tvTongSoLanLat = findViewById((R.id.tvTongSoLanLat));
        tvTongSoLanLat.setText(tongSoLanLat);

        // luu db
        luuDiem(tenNguoiChoi,tongSoLanLat,cheDo);

//        btnShare = findViewById(R.id.btnShare);
//        btnShare.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(getBaseContext(), Share.class);
//                startActivity(intent);
//            }
//        });

        // Các nút bấm
        Button btnChoiLai = findViewById(R.id.btnChoiLai);
        btnChoiLai.setOnClickListener(view -> {
            Intent intent = new Intent(getBaseContext(), CategorySelectionActivity.class);
            intent.putExtra("ten", tenNguoiChoi);
            intent.putExtra("chedo", cheDo);
            intent.putExtra("tenCheDo", tenCheDo);
            startActivity(intent);
        });

        Button btnBXH = findViewById(R.id.btnBXH);
        btnBXH.setOnClickListener(view -> {
            Intent intent = new Intent(getBaseContext(), RankingActivity.class);
            startActivity(intent);
        });

        Button btnManHinhChinh = findViewById(R.id.btnFistActivity);
        btnManHinhChinh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getBaseContext(), FirstActivity.class);
                startActivity(intent);
            }
        });

        Button btnThoat = findViewById(R.id.btnThoat1);
        btnThoat.setOnClickListener(view -> {
            Intent startMain = new Intent(Intent.ACTION_MAIN);
            startMain.addCategory(Intent.CATEGORY_HOME);
            startActivity(startMain);
            finish();

        });
    }

    public void luuDiem(String tenNguoiChoiLuu, String lanLat, int cheDo) {
        String cheDoLuu = "";
        if (cheDo == 4) {
            cheDoLuu = "mode4";
        } else if(cheDo == 5) {
            cheDoLuu = "mode5";
        }else{
            cheDoLuu = "mode6";
        }

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference();
        myRef.child(cheDoLuu).child(tenNguoiChoiLuu).setValue(lanLat).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
//                Toast toast = Toast.makeText(EndActivity.this, "Lưu điểm", Toast.LENGTH_LONG);
//                toast.show();
            }
        });
    }
}