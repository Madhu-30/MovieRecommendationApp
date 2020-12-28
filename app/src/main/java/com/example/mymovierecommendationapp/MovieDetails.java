package com.example.mymovierecommendationapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

public class MovieDetails extends AppCompatActivity {

    ImageView mPoster;
    TextView mName, mType, mYear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);

        String id = getIntent().getStringExtra("movie_id");
        String name = getIntent().getStringExtra("movie_name");
        String type = getIntent().getStringExtra("movie_type");
        String year = getIntent().getStringExtra("movie_year");
        String poster = getIntent().getStringExtra("movie_poster");

        mPoster = findViewById(R.id.poster);
        mName = findViewById(R.id.name);
        mType = findViewById(R.id.type);
        mYear = findViewById(R.id.year);

        mName.setText(name);
        mType.setText(type);
        mYear.setText(year);

        Picasso.get().load(poster)
                .into(mPoster);

    }
}