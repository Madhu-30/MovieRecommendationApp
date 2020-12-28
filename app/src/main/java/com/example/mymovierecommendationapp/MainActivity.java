package com.example.mymovierecommendationapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    //API_KEY = 5bab5e39
    RecyclerView recyclerView;
    OkHttpClient okHttpClient = new OkHttpClient();
    Call mCall;
    ArrayList<MovieModel> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);

//        list.add("Facebook");
//        list.add("Instagram");
//        list.add("LinkedIn");

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(MainActivity.this);
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);

        HttpUrl.Builder urlBuilder = HttpUrl.parse(Constants.base_url).newBuilder();
        urlBuilder.addQueryParameter("s", "Batman");
        String url = urlBuilder.build().toString();

        Request request = new Request.Builder().url(url).build();

        mCall = okHttpClient.newCall(request);
        mCall.enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                Log.d("BAMCHIKI", e.getMessage());
//                Toast.makeText(MainActivity.this, "I am sorry. I don't know", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                Log.d("BAMCHIKI", "1");

                try {
                    JSONObject jsonObject = new JSONObject(response.body().string());
                    JSONArray movies = jsonObject.getJSONArray("Search");
                    addToMoviesList(movies);

                    MyAdapter adapter = new MyAdapter(list, MainActivity.this);
                    recyclerView.setAdapter(adapter);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });


    }

    private void addToMoviesList(JSONArray movies) throws JSONException {
        for(int i = 0; i < movies.length(); i++) {
            MovieModel movieModel = new MovieModel();
            movieModel.setPoster(movies.getJSONObject(i).getString("Poster"));
            movieModel.setTitle(movies.getJSONObject(i).getString("Title"));
            movieModel.setImdbID(movies.getJSONObject(i).getString("imdbID"));
            movieModel.setType(movies.getJSONObject(i).getString("Type"));
            movieModel.setType(movies.getJSONObject(i).getString("Year"));
            list.add(movieModel);
        }

        runOnUiThread(() -> {
            MyAdapter adapter = new MyAdapter(list, MainActivity.this);
            recyclerView.setAdapter(adapter);
        });

    }
}