package com.example.parcial2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.example.parcial2.io.TracksApiAdapter;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    TrackAdapter adapter;
    List<Artist> artistList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getPosts();
    }

    private void getPosts(){
        Call<TracksList> call = TracksApiAdapter.getApiService().getTracks();
        call.enqueue(new Callback<TracksList>() {
            @Override
            public void onResponse(Call<TracksList> call, Response<TracksList> response) {
                if (response.isSuccessful()){
                    TracksList tracksList = response.body();
                    Tracks tracks = tracksList.getTracks();
                    List<Track> trackList1 = tracks.getTrack();
                    for(Track track: trackList1){
                        Artist artist = track.getArtist();
                        artistList.add(artist);
                    }
                    RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);

                    LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
                    recyclerView.setLayoutManager(layoutManager);

                    adapter = new TrackAdapter(trackList1, artistList);
                    recyclerView.setAdapter(adapter);
                    Log.d("onResponse Track","Size of Track => " + tracksList);
                }
            }

            @Override
            public void onFailure(Call<TracksList> call, Throwable t) {
                Log.d("onFailure Track","Size of Track => " + t );
            }
        });

    }

}
