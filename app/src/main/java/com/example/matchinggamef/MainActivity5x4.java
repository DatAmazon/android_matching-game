package com.example.matchinggamef;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class MainActivity5x4 extends AppCompatActivity implements View.OnClickListener {
    TextView tvTen, tvSoCapDaGhep, tvSoLanLat, tvTenCheDo;
    String tenNguoiChoi, chuDe, tenCheDo;
    Button btnStart;
    ProgressBar pgbProcess;
    Button btnKetThuc;


    private static final String TAG = "";
    private ImageButton[][] imageButtons = new ImageButton[5][4];//ban choi
    public ArrayList<Card> cardList = new ArrayList<>(); //mang cac card

    private int[] listAnhHoaQua = new int[]
            {R.drawable.a00, R.drawable.a01, R.drawable.a02,
                    R.drawable.a03, R.drawable.a04, R.drawable.a05,
                    R.drawable.a06, R.drawable.a07, R.drawable.a08, R.drawable.a09};
    private int[] listAnhXe = new int[]
            {R.drawable.a10, R.drawable.a11, R.drawable.a12,
                    R.drawable.a13, R.drawable.a14, R.drawable.a15,
                    R.drawable.a16, R.drawable.a17, R.drawable.a18, R.drawable.a19};
    private int[] listAnhDongVat = new int[]
            {R.drawable.a20, R.drawable.a21, R.drawable.a22,
                    R.drawable.a23, R.drawable.a24, R.drawable.a25,
                    R.drawable.a26, R.drawable.a27, R.drawable.a28, R.drawable.a29};
    private int[] listAnhHoatHinh = new int[]
            {R.drawable.a30, R.drawable.a31, R.drawable.a32,
                    R.drawable.a33, R.drawable.a34, R.drawable.a35,
                    R.drawable.a36, R.drawable.a37, R.drawable.a38, R.drawable.a39};

    private int flipTurn = 0;
    private int flip = 0;
    private int match = 9;

    int ButtonImgRes[] = new int[2];// lưu vị trí hình ảnh trong listSo
    int ButtonID[] = new int[2];// lưu vị trí hình ảnh trong ImageButton (ví dụ ImageButton10/11)
    int CardIndex[] = new int[2];//lưu tọa độ của hình ảnh


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main5x4);

        tvTen = findViewById(R.id.tvTen);
        tvSoCapDaGhep = findViewById(R.id.tvSoCapDaGhep);
        tvSoLanLat = findViewById(R.id.tvSoLanLat);
        tvTenCheDo = findViewById(R.id.tvTenCheDo5x4);
        pgbProcess = findViewById(R.id.progressBar5x4);
        btnStart = findViewById(R.id.btnStart);
        btnKetThuc = findViewById(R.id.btnKetThuc5x4);

        CountDownTimer countDownTimer = new CountDownTimer(25000, 1000) {
            @Override
            public void onTick(long l) {
                int current = pgbProcess.getProgress();
                pgbProcess.setProgress(current + 4);
            }

            @Override
            public void onFinish() {
                Intent intent = new Intent(getBaseContext(), EndActivity.class);
                intent.putExtra("tenNguoiChoi", tenNguoiChoi);
                intent.putExtra("tongSoLanLat", String.valueOf(flip));
                intent.putExtra("chedo", 4);
                intent.putExtra("tenCheDo", tenCheDo);
                startActivity(intent);
                Toast.makeText(MainActivity5x4.this, "Hết giờ!", Toast.LENGTH_SHORT).show();

            }
        };
        countDownTimer.start();
        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            tenNguoiChoi = extras.getString("tenNguoiChoi");
            chuDe = extras.getString("chuDeChoi");
            tvTen.setText("Tên: " + tenNguoiChoi);

            tenCheDo = extras.getString("tenCheDo");
            tvTenCheDo.setText("Chế độ: " + tenCheDo);
        }

        gameInit();

        btnKetThuc = findViewById(R.id.btnKetThuc5x4);
        btnKetThuc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getBaseContext(), EndActivity.class);
                intent.putExtra("tenNguoiChoi", tenNguoiChoi);
                intent.putExtra("tongSoLanLat", String.valueOf(flip));
                intent.putExtra("chedo", 4);
                intent.putExtra("tenCheDo", tenCheDo);
                startActivity(intent);
                countDownTimer.cancel();
            }
        });
    }

    private void gameInit() {
        int[] listAnh = layListAnh();
        int[] listSo = new int[]{0, 0, 1, 1, 2, 2, 3, 3, 4, 4, 5, 5, 6, 6, 7, 7, 8, 8, 9, 9};
        shuffleArray(listSo);
        int vitri = 0;
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 4; j++) {
                cardList.add(new Card(listSo[vitri], true));
                String ImagebuttonID = "ImageButton_" + i + j;
                int resID = this.getResources().getIdentifier(ImagebuttonID, "id", this.getPackageName());
                imageButtons[i][j] = findViewById(resID);
                imageButtons[i][j].setTag(vitri);

                int vtListAnh = listSo[vitri];
                int srcListAnh = listAnh[vtListAnh];
                //imageButtons[i][j].setImageResource(srcListAnh);
                imageButtons[i][j].setOnClickListener(view -> {
                    CardIndex[flipTurn] = (int) view.getTag(); // lay tag cua button == lay index cua card trong cardlist

                    String fT = String.valueOf(CardIndex[flipTurn]);
                    //Hiện index card khi click vào
//                    Toast.makeText(this, fT, Toast.LENGTH_SHORT).show();

                    if (cardList.get(CardIndex[flipTurn]).isFlipable()) {
                        ButtonImgRes[flipTurn] = cardList.get(CardIndex[flipTurn]).card_img_res;
                        //gan img res tu card vao mang ButtonImgRes, phuc vu muc dich so sanh
                        ButtonID[flipTurn] = view.getId();
                        //lay id cua button roi cho vao mang id,
                        //phuc vu muc dich neu anh ko trung thi co the dung id de chuyen ve anh cu
                        //((ImageButton) view).setImageResource(listAnh[(cardList.get(CardIndex[flipTurn]).getCard_img_res())]);
                        ((ImageButton) view).setImageResource(srcListAnh);
                        cardList.get(CardIndex[flipTurn]).setFlipable(false); // ko cho lat lai

                        flipTurn++;
                        if (flipTurn == 2) {
                            checkMatch();
                        }
                    } else {
                        Toast.makeText(this, "Thẻ đã được lật", Toast.LENGTH_SHORT).show();
                    }

                });

                vitri++;
            }
        }
    }

    private void checkMatch() {
        if (ButtonImgRes[0] == ButtonImgRes[1]) {
            match++;
            flip++;
            tvSoCapDaGhep.setText("Số cặp đã ghép: " + match);
            tvSoLanLat.setText("Số lần lật: " + flip);

            flipTurn = 0;
        } else {
            final Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    ImageButton button1 = findViewById(ButtonID[0]);
                    button1.setImageResource(R.drawable.dauhoi);
                    ImageButton button2 = findViewById(ButtonID[1]);
                    button2.setImageResource(R.drawable.dauhoi);
                    flipTurn = 0;
                    flip++;
                    tvSoLanLat.setText("Số lần lật: " + flip);
                    cardList.get(CardIndex[0]).setFlipable(true);//cho lat lai
                    cardList.get(CardIndex[1]).setFlipable(true);
                }
            }, 400);
        }
        if (match == 10) {
            Intent intent = new Intent(getBaseContext(), EndActivity.class);
            intent.putExtra("tenNguoiChoi", tenNguoiChoi);
            intent.putExtra("tongSoLanLat", String.valueOf(flip));
            intent.putExtra("chedo", 5);
            intent.putExtra("tenCheDo", tenCheDo);
            startActivity(intent);
            pgbProcess.setProgress(100);
        }
    }

    private int[] layListAnh() {
        if (Objects.equals(chuDe, "dongvat")) {
            return listAnhDongVat;
        }
        if (Objects.equals(chuDe, "oto")) {
            return listAnhXe;
        }
        if (Objects.equals(chuDe, "hoathinh")) {
            return listAnhHoatHinh;
        }
        if (Objects.equals(chuDe, "hoaqua")) {
            return listAnhHoaQua;
        } else return listAnhDongVat;
    }

    static void shuffleArray(int[] ar)// ham de random cac gia tri trong mang
    {
        // If running on Java 6 or older, use `new Random()` on RHS here
        Random rnd = ThreadLocalRandom.current();
        for (int i = ar.length - 1; i > 0; i--) {
            int index = rnd.nextInt(i + 1);
            // Simple swap
            int a = ar[index];
            ar[index] = ar[i];
            ar[i] = a;
        }
    }

    @Override
    public void onClick(View view) {

    }

}
