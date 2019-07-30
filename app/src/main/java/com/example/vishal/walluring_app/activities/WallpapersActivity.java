package com.example.vishal.walluring_app.activities;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ProgressBar;

import com.example.vishal.walluring_app.R;
import com.example.vishal.walluring_app.adapters.WallpapersAdapter;
import com.example.vishal.walluring_app.models.Wallpaper;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class WallpapersActivity extends AppCompatActivity {

    List<Wallpaper> wallpaperList;
    RecyclerView recyclerView;
    WallpapersAdapter adapter;
    Fullscreen fs;
    DatabaseReference dbWallpapers;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wallpapers);

        boolean internet = checkConnection(this);
        if (internet == false) {
            LayoutInflater lf = LayoutInflater.from(this);
            View view = lf.inflate(R.layout.dialogui, null);

            AlertDialog.Builder builder1 = new AlertDialog.Builder(this);
            builder1.setCancelable(true);
            builder1.setView(view);
            AlertDialog alert11 = builder1.create();
            alert11.show();
        } else {
            wallpaperList = new ArrayList<>();
            recyclerView = findViewById(R.id.recycler_view);
            recyclerView.setHasFixedSize(true);
            recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
            adapter = new WallpapersAdapter(this, wallpaperList);
            fs = new Fullscreen(this, wallpaperList);
            recyclerView.setAdapter(adapter);

            progressBar = findViewById(R.id.progressbar);


            Intent intent = getIntent();
            String category = intent.getStringExtra("category");

            // Toolbar toolbar = findViewById(R.id.toolbar);
            //toolbar.setTitle(category);
            //setSupportActionBar(toolbar);

            dbWallpapers = FirebaseDatabase.getInstance().getReference("images")
                    .child(category);

            progressBar.setVisibility(View.VISIBLE);
            dbWallpapers.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    progressBar.setVisibility(View.GONE);
                    if (dataSnapshot.exists()) {
                        for (DataSnapshot wallpaperSnapshot : dataSnapshot.getChildren()) {
                            String title = wallpaperSnapshot.child("title").getValue(String.class);
                            String desc = wallpaperSnapshot.child("desc").getValue(String.class);
                            String url = wallpaperSnapshot.child("url").getValue(String.class);
                            System.out.print(url);
                            Wallpaper w = new Wallpaper(title, desc, url);
                            wallpaperList.add(w);
                        }
                        adapter.notifyDataSetChanged();
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }


            });

        }
    }

    public static boolean checkConnection(Context context) {
        final ConnectivityManager connMgr = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetworkInfo = connMgr.getActiveNetworkInfo();

        if (activeNetworkInfo != null) { // connected to the internet

            if (activeNetworkInfo.getType() == ConnectivityManager.TYPE_WIFI) {
                // connected to wifi
                return true;
            } else if (activeNetworkInfo.getType() == ConnectivityManager.TYPE_MOBILE) {
                // connected to the mobile provider's data plan
                return true;
            }
        }
        return false;
    }
    }

