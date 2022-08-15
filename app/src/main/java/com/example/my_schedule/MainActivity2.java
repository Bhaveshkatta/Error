package com.example.my_schedule;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.app.AlarmManager;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.Settings;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

import com.bumptech.glide.load.model.Model;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Calendar;

public class MainActivity2 extends AppCompatActivity {
    private RecyclerView recycleView;
    private ArrayList<String> phoneNumber, name, calendar, time, e1, e2, e3;
    DataBase dataBase;

    DatabaseReference databaseReference;


    FloatingActionButton buttonf;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        dataBase= new DataBase(this);
        name = new ArrayList<>();
        calendar = new ArrayList<>();
        e1 = new ArrayList<>();
        displayAllDat();
        buttonf = findViewById(R.id.fab);
        recycleView = findViewById(R.id.recycleview);
        adaptetr adaptetr = new adaptetr(this,name, calendar, e1);
        recycleView.setAdapter(adaptetr);
        SwipeRefreshLayout swipeRefreshLayout = findViewById(R.id.swiperefershlayocut);
        swipeRefreshLayout.setOnRefreshListener(
                new SwipeRefreshLayout.OnRefreshListener() {
                    @Override
                    public void onRefresh() {
                        displayAllDat();
                        adaptetr.notifyDataSetChanged();
                        swipeRefreshLayout.setRefreshing(false);
                    }
                }
        );
        recycleView.setLayoutManager(new LinearLayoutManager(this));




        Intent intent = new Intent(MainActivity2.this, MainActivity3.class);

        buttonf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(intent);
            }
        });


    }

//    private boolean isAccessibilityOn(Context applicationContext, Context context) {
//        int accessibilityEnabled = 0;
//        final String service = context.getPackageName () + "/" + WhatAppAccessibilityService.class.getCanonicalName();
//        try {
//            accessibilityEnabled = Settings.Secure.getInt(context.getApplicationContext().getContentResolver(), Settings.Secure.ACCESSIBILITY_ENABLED);
//        }catch (Settings.SettingNotFoundException ignored) {    }
//
//        TextUtils.SimpleStringSplitter colonSplitter = new TextUtils.SimpleStringSplitter(':');
//
////        if(accessibilityEnabled == 1){
////            String settingValue = Settings.Secure.getString(context.getApplicationContext().getContentResolver(), Settings.Secure.ENABLED_ACCESSIBILITY_SERVICES);
////            if (settingValue != null) {
////                colonSplitter.setString(settingValue);
////                while (colonSplitter.hasNext()){
////                    String accessibilityService = colonSplitter.next();
////                    if(accessibilityService.equalsIgnoreCase(service)){
////                        return true;
////                    }
////                    //;)
////                    if(!isAccessibilityOn(getApplicationContext(), context)){
////                        Intent intent = new Intent(Settings.ACTION_ACCESSIBILITY_SETTINGS);
////                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
////                        startActivity(intent);
////                    }
////
////                }
////            }
////        }
//        return false;
//    }

    void displayAllDat(){
        Cursor cuor= dataBase.readall();
        if(dataBase == null){
            Toast.makeText(this, "No ddata", Toast.LENGTH_LONG);

        }
        else{
            while(cuor.moveToNext()){
                name.add(cuor.getString(1));
                calendar.add(cuor.getString(4));
                e1.add(cuor.getString(2));

            }
        }}


//Start whatsApp
//MySMSservice.startActionWHATSAPP(getApplicationContext (), text_message.getText().toString(),text_count.getText().toString(), result);


}