package com.example.parcial2.io;

import com.example.parcial2.TracksList;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;

public interface TracksApiService {

    @GET("?method=chart.gettoptracks&api_key=b284db959637031077380e7e2c6f2775&format=json")
    Call<TracksList> getTracks();
}
