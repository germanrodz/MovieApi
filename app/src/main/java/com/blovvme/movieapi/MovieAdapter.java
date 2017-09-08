package com.blovvme.movieapi;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.blovvme.movieapi.model.Result;
import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Blovvme on 9/8/17.
 */

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.ViewHolder> {

    public static final String PATH_MOVIES_IMG = "https://image.tmdb.org/t/p/w500/";

    List<Result> resultList = new ArrayList<>();
    Context context;

    //constructor
    public MovieAdapter(List<Result> resultList, Context context) {
        this.resultList = resultList;
        this.context = context;
    }

    //onCreate
    @Override
    public MovieAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout
                .recyclerview_layout, parent, false);
        return new ViewHolder(view);
    }

    //Bind
    @Override
    public void onBindViewHolder(MovieAdapter.ViewHolder holder, int position) {
        Result result = resultList.get(position);
        holder.tvTitle.setText(resultList.get(position).getTitle());
        holder.tvPopularity.setText(resultList.get(position).getPopularity().toString());
        holder.tvOverview.setText(resultList.get(position).getOverview());
        holder.tvLanguage.setText(resultList.get(position).getOriginalLanguage());
        Glide.with(context).load(PATH_MOVIES_IMG + resultList.get(position).getBackdropPath()).into(holder.imgView);





    }

    @Override
    public int getItemCount() {
        return resultList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvTitle, tvPopularity,tvOverview,tvLanguage;
        ImageView imgView;

        public ViewHolder(View itemView) {
            super(itemView);
            tvTitle = (TextView) itemView.findViewById(R.id.tvTitle);
            tvPopularity = (TextView) itemView.findViewById(R.id.tvPopularity);
            tvOverview = (TextView) itemView.findViewById(R.id.tvOverview);
            tvLanguage = (TextView) itemView.findViewById(R.id.tvLanguage);
            imgView = (ImageView) itemView.findViewById(R.id.imgView);
        }
    }
}
