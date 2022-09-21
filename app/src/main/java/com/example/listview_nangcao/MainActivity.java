package com.example.listview_nangcao;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private List<MonAn> arrayMonAn = new ArrayList<>();
    private MonAnAdapter adapter;
    private  ListView lvMonAn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AnhXa();
        adapter =new MonAnAdapter(this,R.layout.item_mon_an,arrayMonAn);
        lvMonAn.setAdapter(adapter);
        lvMonAn.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                deleteMonAn(i);
            }
        });
        lvMonAn.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                showInfoMonAn(i);
                return false;
            }
        });
    }

    private void showInfoMonAn(int position){
        MonAn s = arrayMonAn.get(position);

        Intent i = new Intent(this, Activity2.class);

        i.putExtra("Ten", s.getTen());
        i.putExtra("MoTa", s.getMoTa());
        i.putExtra("Hinh",s.getHinh());
        startActivity(i);
    }

    private void AnhXa() {
        lvMonAn=(ListView) findViewById(R.id.listviewMonAn);
        arrayMonAn=new ArrayList<>();
        arrayMonAn.add(new MonAn("Spaghetti","120$",R.drawable.img_1));
        arrayMonAn.add(new MonAn("Cream","50$",R.drawable.img_2));
        arrayMonAn.add(new MonAn("Hamburger","150$",R.drawable.img_3));
        arrayMonAn.add(new MonAn("Chicken KFC","140$",R.drawable.img_4));

    }
    private void deleteMonAn(final int position){
        new AlertDialog.Builder(this)
                .setTitle("Delete Dish")
                .setMessage("Are you want delete Dish?")
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        arrayMonAn.remove(position);
                        adapter.notifyDataSetChanged();
                    }
                }).setNegativeButton("Canncel", null)
                .show();
    }

}