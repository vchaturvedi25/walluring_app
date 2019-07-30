package com.example.vishal.walluring_app.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.vishal.walluring_app.R;
import com.example.vishal.walluring_app.activities.Fullscreen;
import com.example.vishal.walluring_app.activities.WallpapersActivity;
import com.example.vishal.walluring_app.models.Category;
import com.example.vishal.walluring_app.models.Wallpaper;

import java.util.List;

public class WallpapersAdapter extends RecyclerView.Adapter<WallpapersAdapter.CategoryViewHolder> {

    private Context mCtx;
    private List<Wallpaper> wallpaperList;


    public WallpapersAdapter(Context mCtx, List<Wallpaper> wallpaperList) {
        this.mCtx = mCtx;
        this.wallpaperList = wallpaperList;
    }

    @Override
    public WallpapersAdapter.CategoryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
       View view = LayoutInflater.from(mCtx).inflate(R.layout.recyclerview_wallpapers, parent, false);
        return new CategoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(WallpapersAdapter.CategoryViewHolder holder, int position) {
       Wallpaper w = wallpaperList.get(position);
       //holder.textview.setText(w.title);
        Glide.with(mCtx).load(w.url).into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return wallpaperList.size();
    }

    class CategoryViewHolder extends RecyclerView.ViewHolder implements  View.OnClickListener{

        TextView textview;
        ImageView imageView;
        public CategoryViewHolder(View itemView){

            super(itemView);

            //textview = itemView.findViewById(R.id.text_view_title);
            imageView = itemView.findViewById(R.id.image_view);

            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View view) {

            int p = getAdapterPosition();
            Wallpaper img = wallpaperList.get(p);

            Intent intent = new Intent(mCtx, Fullscreen.class);
            intent.putExtra("url",img.url);
            mCtx.startActivity(intent);
        }
    }
}
