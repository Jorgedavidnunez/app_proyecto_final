package com.jd.proyecto_final;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.animation.LayoutTransition;
import android.os.Build;
import android.os.Bundle;
import android.transition.AutoTransition;
import android.transition.TransitionManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jd.proyecto_final.Interfaz.Post_prueba;
import com.jd.proyecto_final.Models.prueba;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Busqueda extends AppCompatActivity {
    EditText palabra_buscar;
    TextView Idtexto;
    Button btn_buscar;
    TextView texto_tarjeta;
    LinearLayout segundo_layout;
    RecyclerView recycle_view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_busqueda);
        palabra_buscar = findViewById(R.id.palabra_buscar);
        Idtexto = findViewById(R.id.Idtexto);
        btn_buscar = findViewById(R.id.Buscar);
        texto_tarjeta=findViewById(R.id.texto_tarjeta);
        segundo_layout=findViewById(R.id.layout_segundo);
        segundo_layout.getLayoutTransition().enableTransitionType(LayoutTransition.CHANGING);

        //recycle_view=findViewById(R.id.recycle_view);
        //recycle_view.setHasFixedSize(true);
        //recycle_view.setLayoutManager(new LinearLayoutManager(this));

        btn_buscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                texto_tarjeta.setText("");
                //Idtexto.setText("");
                busqueda(palabra_buscar.getText().toString());

            }
        });
    }

    public void busqueda(String id)
    {
        Retrofit retrofit = new Retrofit.Builder().baseUrl("https://jsonplaceholder.typicode.com/").addConverterFactory(GsonConverterFactory.create()).build();
        Post_prueba post_prueba = retrofit.create(Post_prueba.class);
        Call<List<prueba>> call = post_prueba.find(id);
        call.enqueue(new Callback<List<prueba>>() {
            @Override
            public void onResponse(Call<List<prueba>> call, Response<List<prueba>> response) {
                List<prueba> lista_busqueda = response.body();
                for(prueba pr:lista_busqueda)
                {
                    String content="";
                    content+="UserId: "+pr.getUserId() + "\n";
                    content+="ID: "+pr.getId() + "\n";
                    content+="Title: "+pr.getTitle() + "\n";
                    content+="Body: "+pr.getBody() + "\n\n";
                    texto_tarjeta.append(content);
                }
            }

            @Override
            public void onFailure(Call<List<prueba>> call, Throwable t) {
                texto_tarjeta.setText(t.getMessage());
            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public void expand(View view) {
        int v=(texto_tarjeta.getVisibility()==View.GONE)?View.VISIBLE:View.GONE;
        TransitionManager.beginDelayedTransition(segundo_layout, new AutoTransition());
        texto_tarjeta.setVisibility(v);
    }
}