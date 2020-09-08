package com.example.moviesapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.Holder> {

    List<Results> results;
    Context context;

    RecyclerOnClickListener listener;

    public Adapter(Context context, List<Results> results) {
        this.results = results;
        this.context = context;
    }

    public void setOnItemClickListener(RecyclerOnClickListener listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.movies, parent, false); //use activity_meal

        return new Holder(view, listener);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        String p= results.get(position).getPoster_path();
        Picasso.get()
                .load(results.get(position).getPoster_path())
                .placeholder(R.drawable.movie)
                .error(R.drawable.movie)
                .into(holder.poster);
    }

    @Override
    public int getItemCount() {
        return results.size();
    }

    public class Holder extends RecyclerView.ViewHolder {
        ImageView poster;

        public Holder(@NonNull View itemView, RecyclerOnClickListener listener) {
            super(itemView);
            poster = (ImageView) itemView.findViewById(R.id.poster);
        }
    }

    public interface RecyclerOnClickListener {
        void onItemClick(int position);


    }
}

