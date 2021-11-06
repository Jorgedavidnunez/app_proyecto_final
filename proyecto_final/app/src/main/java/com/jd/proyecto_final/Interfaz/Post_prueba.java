package com.jd.proyecto_final.Interfaz;

import com.jd.proyecto_final.Models.prueba;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface Post_prueba {
    @GET("posts")
    Call<List<prueba>> getprueba();

    @GET("posts")
    Call<List<prueba>> find(@Query("q") String id);
}
