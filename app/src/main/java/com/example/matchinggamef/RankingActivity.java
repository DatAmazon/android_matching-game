package com.example.matchinggamef;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

public class RankingActivity extends AppCompatActivity {

    TableLayout tblLayout4x4, tblLayout5x5;
    ArrayList<Player> lst4x4 = new ArrayList<Player>();
    ArrayList<Player> lst5x5 = new ArrayList<Player>();

    String[] items;

    ArrayAdapter<String> adapter;
    EditText edtSearch;
    ImageButton ImgSearch;
    Button btnSort;
    boolean isDecrease4x4 = true;
    boolean isDecrease5x5 = true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ranking);

        tblLayout4x4 = (TableLayout) findViewById(R.id.tableLayout4x4);
        tblLayout5x5 = (TableLayout) findViewById(R.id.tableLayout5x5);
        Button btnExit = findViewById(R.id.btnThoat);
        edtSearch = findViewById(R.id.edtSearch);
        ImgSearch = findViewById(R.id.btnSearch);
        btnSort = findViewById(R.id.btnSort);
        btnSort.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sortData();
            }
        });
        ImgSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(RankingActivity.this, "Th", Toast.LENGTH_SHORT).show();
                String search = edtSearch.getText().toString().trim();
                //  lst4x4.clear();

//                tblLayout4x4.removeAllViews();

                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference myRef = database.getReference();
                ArrayList<Player> lst4x41 = new ArrayList<Player>();
                for (Player element : lst4x4) {
                    if (element.tenNguoiChoi.contains(search)) {
                        lst4x41.add(element);
                    }
                }
                if (lst4x41.size()>0){
                    for (Player element : lst4x41) {
                        int count = tblLayout4x4.getChildCount();
                        for (int i = 2; i < count; i++) {
                            View child = tblLayout4x4.getChildAt(i);
                            if (child instanceof TableRow) ((ViewGroup) child).removeAllViews();
                        }

                        TableRow row = new TableRow(RankingActivity.this);
                        TextView tv = new TextView(RankingActivity.this);
                        tv.setText(element.getTenNguoiChoi());
                        tv.setGravity(Gravity.LEFT);

                        TextView tv2 = new TextView(RankingActivity.this);
                        tv2.setText(String.valueOf(element.getTongSoLanLat()));
                        tv2.setGravity(Gravity.RIGHT);

                        row.addView(tv);
                        row.addView(tv2);
                        tblLayout4x4.addView(row, new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.MATCH_PARENT));

                    }
                }


                ArrayList<Player> list5x5New = new ArrayList<Player>();
                for (Player element : lst5x5) {
                    if (element.tenNguoiChoi.contains(search)) {
                        list5x5New.add(element);
                    }
                }
                if (list5x5New.size()>0){
                    for (Player element : list5x5New) {
                        int count = tblLayout5x5.getChildCount();
                        for (int i = 2; i < count; i++) {
                            View child = tblLayout5x5.getChildAt(i);
                            if (child instanceof TableRow) ((ViewGroup) child).removeAllViews();
                        }

                        TableRow row = new TableRow(RankingActivity.this);
                        TextView tv = new TextView(RankingActivity.this);
                        tv.setText(element.getTenNguoiChoi());
                        tv.setGravity(Gravity.LEFT);

                        TextView tv2 = new TextView(RankingActivity.this);
                        tv2.setText(String.valueOf(element.getTongSoLanLat()));
                        tv2.setGravity(Gravity.RIGHT);

                        row.addView(tv);
                        row.addView(tv2);
                        tblLayout5x5.addView(row, new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.MATCH_PARENT));

                    }
                }

                // root.addValueEventListener
              /*  Query queryRef = myRef.child("mode4").orderByKey().startAt(search);
                queryRef.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        if (dataSnapshot.exists()) {
                            Toast.makeText(RankingActivity.this, "123", Toast.LENGTH_SHORT).show();
                            //lst = new ArrayList<>();
                            for (DataSnapshot dss : dataSnapshot.getChildren()) {
                                String ten = dss.getKey();
                                int lanlat = Integer.parseInt(dss.getValue(String.class));
                                Player plr = new Player(ten, lanlat);
                                lst4x4.add(plr);
                            }
                        }

                        // sau khi lay arrList
                        for (Player p : lst4x4) {
                            TableRow row = new TableRow(RankingActivity.this);

                            TextView tv = new TextView(RankingActivity.this);
                            tv.setText(p.getTenNguoiChoi());
                            tv.setGravity(Gravity.LEFT);

                            TextView tv2 = new TextView(RankingActivity.this);
                            tv2.setText(String.valueOf(p.getTongSoLanLat()));
                            tv2.setGravity(Gravity.RIGHT);

                            row.addView(tv);
                            row.addView(tv2);
                            tblLayout4x4.addView(row, new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.MATCH_PARENT));
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }


                });*/


               /* myRef.child("mode4").startAt("5").addChildEventListener(new ChildEventListener() {
                    @Override
                    public void onChildAdded(DataSnapshot dataSnapshot, String prevChildKey) {
                        if (dataSnapshot.exists()) {
                            //lst = new ArrayList<>();
                            for (DataSnapshot dss : dataSnapshot.getChildren()) {
                                String ten = dss.getKey();
                                int lanlat = Integer.parseInt(dss.getValue(String.class));
                                Player plr = new Player(ten, lanlat);
                                lst4x4.add(plr);
                            }
                        }

                        // sau khi lay arrList
                        for (Player p : lst4x4) {
                            TableRow row = new TableRow(RankingActivity.this);

                            TextView tv = new TextView(RankingActivity.this);
                            tv.setText(p.getTenNguoiChoi());
                            tv.setGravity(Gravity.LEFT);

                            TextView tv2 = new TextView(RankingActivity.this);
                            tv2.setText(String.valueOf(p.getTongSoLanLat()));
                            tv2.setGravity(Gravity.RIGHT);

                            row.addView(tv);
                            row.addView(tv2);
                            tblLayout4x4.addView(row, new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.MATCH_PARENT));
                        }
                    }

                    @Override
                    public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

                    }

                    @Override
                    public void onChildRemoved(@NonNull DataSnapshot snapshot) {

                    }

                    @Override
                    public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }

                    // ...
                });*/


            }
        });

        Button btnManHinhChinh = findViewById(R.id.btnManHinhChinh);
        hienBXH();
        btnManHinhChinh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getBaseContext(), FirstActivity.class);
                startActivity(intent);
            }
        });


        btnExit.setOnClickListener(view -> {
//            Intent intent = new Intent(getApplicationContext(), FirstActivity.class);
//            startActivity(intent);

            // Tao su kien ket thuc app
            Intent startMain = new Intent(Intent.ACTION_MAIN);
            startMain.addCategory(Intent.CATEGORY_HOME);
            startActivity(startMain);
            finish();
        });
//        edtSearch.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//
//            }
//
//            @Override
//            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//                RankingActivity.this.adapter.getFilter().filter(charSequence);
//            }
//
//            @Override
//            public void afterTextChanged(Editable editable) {
//
//            }
//        });
    }
//    public void initList() {
//        items = new String[]{"Java", "JavaScript", "C#", "PHP", "С++", "Python", "C", "SQL", "Ruby", "Objective-C"};
//        lst4x4 = new ArrayList<>(Arrays.asList(Player));
//        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, lst4x4);
//        tblLayout4x4.setAdapter(adapter);
//    }

    class ComparatorDecrease implements Comparator<Player> {
        public int compare(Player s1, Player s2) {
            if (s1.getTongSoLanLat() == s2.getTongSoLanLat())
                return 0;
            else if (s1.getTongSoLanLat() > s2.getTongSoLanLat())
                return -1;
            else
                return 1;
        }
    }
    class ComparatorAscending implements Comparator<Player> {
        public int compare(Player s1, Player s2) {
            if (s1.getTongSoLanLat() == s2.getTongSoLanLat())
                return 0;
            else if (s1.getTongSoLanLat() > s2.getTongSoLanLat())
                return 1;
            else
                return -1;
        }
    }
    private void sortData(){
        if (lst4x4!=null && lst4x4.size()>0){
            int count = tblLayout4x4.getChildCount();
            for (int i = 2; i < count; i++) {
                View child = tblLayout4x4.getChildAt(i);
                if (child instanceof TableRow) ((ViewGroup) child).removeAllViews();
            }

            ArrayList<Player> lst4x4Sort = new ArrayList<Player>();
            lst4x4Sort.addAll(lst4x4);

            if (isDecrease4x4){
                Collections.sort(lst4x4Sort,new ComparatorDecrease());
                isDecrease4x4 = false;
            }else {
                Collections.sort(lst4x4Sort,new ComparatorAscending());
                isDecrease4x4 = true;
            }
            //chỉ lấy 10 bản ghi thôi
            for (int i =0; i<10; i++) {
                TableRow row = new TableRow(RankingActivity.this);
                TextView tv = new TextView(RankingActivity.this);
                tv.setText(lst4x4Sort.get(i).getTenNguoiChoi());
//                tv.setText(p.getTenNguoiChoi());
                tv.setGravity(Gravity.LEFT);

                TextView tv2 = new TextView(RankingActivity.this);
                tv2.setText(String.valueOf(lst4x4Sort.get(i).getTongSoLanLat()));
//                tv.setText(lst4x4Sort.get(i).getTongSoLanLat());
                tv.setGravity(Gravity.RIGHT);

                row.addView(tv);
                row.addView(tv2);
                tblLayout4x4.addView(row, new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.MATCH_PARENT));
            }
        }
        if (lst5x5!=null && lst5x5.size()>0){
            int count = tblLayout5x5.getChildCount();
            for (int i = 2; i < count; i++) {
                View child = tblLayout5x5.getChildAt(i);
                if (child instanceof TableRow) ((ViewGroup) child).removeAllViews();
            }

            ArrayList<Player> lst5x5Sort = new ArrayList<Player>();
            lst5x5Sort.addAll(lst5x5);
            if (isDecrease5x5){
                isDecrease5x5 = false;
                Collections.sort(lst5x5Sort,new ComparatorDecrease());
            }else {
                isDecrease5x5 = true;
                Collections.sort(lst5x5Sort,new ComparatorAscending());
            }
            for (Player p : lst5x5Sort) {
                TableRow row = new TableRow(RankingActivity.this);

                TextView tv = new TextView(RankingActivity.this);
                tv.setText(p.getTenNguoiChoi());
                tv.setGravity(Gravity.LEFT);

                TextView tv2 = new TextView(RankingActivity.this);
                tv2.setText(String.valueOf(p.getTongSoLanLat()));
                tv2.setGravity(Gravity.RIGHT);

                row.addView(tv);
                row.addView(tv2);
                tblLayout5x5.addView(row, new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.MATCH_PARENT));
            }
        }

    }
    public void hienBXH() {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference();

        myRef.child("mode4").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    //lst = new ArrayList<>();
                    for (DataSnapshot dss : snapshot.getChildren()) {
                        String ten = dss.getKey();
                        int lanlat = Integer.parseInt(dss.getValue(String.class));
                        Player plr = new Player(ten, lanlat);
                        lst4x4.add(plr);
                    }
                }

                // sau khi lay arrList
                for (Player p : lst4x4) {
                    TableRow row = new TableRow(RankingActivity.this);

                    TextView tv = new TextView(RankingActivity.this);
                    tv.setText(p.getTenNguoiChoi());
                    tv.setGravity(Gravity.LEFT);

                    TextView tv2 = new TextView(RankingActivity.this);
                    tv2.setText(String.valueOf(p.getTongSoLanLat()));
                    tv2.setGravity(Gravity.RIGHT);

                    row.addView(tv);
                    row.addView(tv2);
                    tblLayout4x4.addView(row, new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.MATCH_PARENT));
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        myRef.child("mode6").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    //lst = new ArrayList<>();
                    for (DataSnapshot dss : snapshot.getChildren()) {
                        String ten = dss.getKey();
                        int lanlat = Integer.parseInt(dss.getValue(String.class));
                        Player plr = new Player(ten, lanlat);
                        lst5x5.add(plr);
                    }
                }

                // sau khi lay arrList
                for (Player p : lst5x5) {
                    TableRow row = new TableRow(RankingActivity.this);

                    TextView tv = new TextView(RankingActivity.this);
                    tv.setText(p.getTenNguoiChoi());
                    tv.setGravity(Gravity.LEFT);

                    TextView tv2 = new TextView(RankingActivity.this);
                    tv2.setText(String.valueOf(p.getTongSoLanLat()));
                    tv2.setGravity(Gravity.RIGHT);

                    row.addView(tv);
                    row.addView(tv2);
                    tblLayout5x5.addView(row, new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.MATCH_PARENT));
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
//
//        myRef.child("mode6").addListenerForSingleValueEvent(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                if (snapshot.exists()) {
//                    //lst = new ArrayList<>();
//                    for (DataSnapshot dss : snapshot.getChildren()) {
//                        String ten = dss.getKey();
//                        int lanlat = Integer.parseInt(dss.getValue(String.class));
//                        Player plr = new Player(ten, lanlat);
//                        lst.add(plr);
//                    }
//                }
//
//                // sau khi lay arrList
//                for (Player p : lst) {
//                    TableRow row = new TableRow(RankingActivity.this);
//
//                    TextView tv = new TextView(RankingActivity.this);
//                    tv.setText(p.getTenNguoiChoi());
//                    tv.setGravity(Gravity.CENTER);
//
//                    TextView tv2 = new TextView(RankingActivity.this);
//                    tv2.setText(String.valueOf(p.getTongSoLanLat()));
//                    tv2.setGravity(Gravity.RIGHT);
//
//                    row.addView(tv);
//                    row.addView(tv2);
//                    tblLayout.addView(row, new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.MATCH_PARENT));
//                }
//
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//        });

    }
}