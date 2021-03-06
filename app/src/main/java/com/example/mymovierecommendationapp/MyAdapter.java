package com.example.mymovierecommendationapp;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;
import com.thekhaeng.pushdownanim.PushDownAnim;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

    private ArrayList<MovieModel> list;
    private Context context;

    public MyAdapter(ArrayList<MovieModel> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layout,parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        MovieModel model = list.get(position);

        holder.movie_name.setText(model.getTitle());
        holder.movie_type.setText(model.getType());
        holder.movie_year.setText(model.getYear());

        Picasso.get().load(model.getPoster())
                .resize(90, 90)
                .into(holder.movie_poster);

        //Standard way setOnClickListener
//        holder.itemView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(context, MovieDetails.class);
//                intent.putExtra("movie_id", model.getImdbID());
//                intent.putExtra("movie_name", model.getTitle());
//                intent.putExtra("movie_type", model.getType());
//                intent.putExtra("movie_year", model.getYear());
//                intent.putExtra("movie_poster", model.getPoster());
//                context.startActivity(intent);
//            }
//        });

        //Bounce Animation on clicking a movie card
        PushDownAnim.setPushDownAnimTo(holder.itemView)
                .setScale(PushDownAnim.MODE_STATIC_DP,6)
                .setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(context, MovieDetails.class);
                        intent.putExtra("movie_id", model.getImdbID());
                        intent.putExtra("movie_name", model.getTitle());
                        intent.putExtra("movie_type", model.getType());
                        intent.putExtra("movie_year", model.getYear());
                        intent.putExtra("movie_poster", model.getPoster());
                        context.startActivity(intent);
                    }
                });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView movie_name;
        TextView movie_year;
        TextView movie_type;
        ImageView movie_poster;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            movie_name = itemView.findViewById(R.id.movie_title);
            movie_year = itemView.findViewById(R.id.movie_year);
            movie_type = itemView.findViewById(R.id.movie_type);
            movie_poster = itemView.findViewById(R.id.movie_image);

        }
    }
}
