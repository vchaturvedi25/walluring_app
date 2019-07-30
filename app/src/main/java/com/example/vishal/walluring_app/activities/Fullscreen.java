package com.example.vishal.walluring_app.activities;

import android.app.WallpaperManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.vishal.walluring_app.R;
import com.example.vishal.walluring_app.models.Category;
import com.example.vishal.walluring_app.models.Wallpaper;

import java.io.IOException;
import java.util.List;

public class Fullscreen extends AppCompatActivity {

    private Window mWindow;
    private Context mCtx;
    private List<Wallpaper> wallpaperList;

    ImageView imgv;
    Button setWallBtn, setLockBtn, homescreen, lockscreen, homelockscreen;
    AlertDialog alert11;
    AlertDialog.Builder builder1;
    Bitmap bitmap;
    WallpaperManager manager;

    public Fullscreen(){

    }

    public Fullscreen(Context mCtx, List<Wallpaper> wallpaperList) {
        this.mCtx = mCtx;
        this.wallpaperList = wallpaperList;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fullscreen);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);


        imgv = findViewById(R.id.imgview);
        Intent intent = getIntent();
        String url = intent.getStringExtra("url");
        Glide.with(this).load(url).into(imgv);
        setWallBtn = (Button)findViewById(R.id.setbgh);
        setLockBtn = (Button)findViewById(R.id.setbgl);
        homescreen = (Button)findViewById(R.id.homesc);
        lockscreen = (Button)findViewById(R.id.locksc);
        homelockscreen = (Button)findViewById(R.id.homelocksc);
        LayoutInflater lf = LayoutInflater.from(Fullscreen.this);
        View view = lf.inflate(R.layout.setwallui, null);
        builder1 = new AlertDialog.Builder(Fullscreen.this);
        builder1.setCancelable(true);
        builder1.setView(view);
        alert11 = builder1.create();






        setWallBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                setBackgroundImage();


            }


        });

        setLockBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setLockScreen();
            }
        });
/*
        homescreen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setHomeScreen();
                // alert11.dismiss();
            }
        });*/



        /*lockscreen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setLockScreen();
                alert11.dismiss();
            }
        });

        homelockscreen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setBackgroundImage();
                alert11.dismiss();
            }
        });*/


    }

 /* public void setHomeScreen() {

      //bitmap = ((BitmapDrawable)imgv.getDrawable()).getBitmap();
      //manager =  WallpaperManager.getInstance(this);

        try {
            manager.setBitmap(bitmap, null, false, manager.FLAG_SYSTEM);
            Toast.makeText(getApplicationContext(), "Home Screen Set", Toast.LENGTH_SHORT).show();
        }
        catch (IOException e)
        {
            Toast.makeText(this, "Wallpaper Not Set", Toast.LENGTH_SHORT).show();
        }
    }*/

   private void setLockScreen() {
        Bitmap bitmap = ((BitmapDrawable)imgv.getDrawable()).getBitmap();
        WallpaperManager manager =  WallpaperManager.getInstance(getApplicationContext());
        try {
            manager.setBitmap(bitmap, null, true, manager.FLAG_LOCK);
            Toast.makeText(getApplicationContext(), "Lock Screen Set ", Toast.LENGTH_SHORT).show();
        }
        catch (IOException e)
        {
            Toast.makeText(this, "Wallpaper Not Set", Toast.LENGTH_SHORT).show();
        }
    }

    /*public void setBackgroundImage() {

        bitmap = ((BitmapDrawable)imgv.getDrawable()).getBitmap();
        manager =  WallpaperManager.getInstance(this);
        try {
            manager.setBitmap(bitmap);
            Toast.makeText(getApplicationContext(), "Home and Lock Screen Set ", Toast.LENGTH_SHORT).show();
        }
        catch (IOException e)
        {
            Toast.makeText(this, "Wallpaper Not Set", Toast.LENGTH_SHORT).show();
        }
    }*/
    public void setBackgroundImage() {

        bitmap = ((BitmapDrawable)imgv.getDrawable()).getBitmap();
        manager =  WallpaperManager.getInstance(Fullscreen.this);

        try {
            manager.setBitmap(bitmap,null,true,manager.FLAG_SYSTEM);
            Toast.makeText(getApplicationContext(), "Home Screen Set ", Toast.LENGTH_SHORT).show();
        }
        catch (IOException e)
        {

        }
    }
}
