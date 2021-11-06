package com.jd.proyecto_final;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.JsonReader;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.jd.proyecto_final.Interfaz.Post_Productos;
import com.jd.proyecto_final.Interfaz.Post_prueba;
import com.jd.proyecto_final.Models.Productos;
import com.jd.proyecto_final.Models.prueba;

import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    private TextView Idtexto;
    private EditText  palabra_buscar;
    private Button btn_buscar;
    private Button btn_redirect_busqueda, btn_prueba;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        palabra_buscar = findViewById(R.id.palabra_buscar);
        btn_buscar=findViewById(R.id.Buscar);
        Idtexto = findViewById(R.id.Idtexto);
        btn_redirect_busqueda = findViewById(R.id.redirect_buscar);
        btn_prueba=findViewById(R.id.redirect_prueba);

        btn_buscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Idtexto.setText("");
                busqueda(palabra_buscar.getText().toString());
            }
        });
        btn_redirect_busqueda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(MainActivity.this, Busqueda.class );
                startActivity(intent);
            }
        });

        btn_prueba.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(MainActivity.this, recycleview.class );
                startActivity(intent);
            }
        });

        Obtener_Datos();
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
                for(prueba pr: lista_busqueda)
                {

                    String content="";
                    content+="UserId: "+pr.getUserId() + "\n";
                    content+="ID: "+pr.getId() + "\n";
                    content+="Title: "+pr.getTitle() + "\n";
                    content+="Body: "+pr.getBody() + "\n\n";
                    Idtexto.append(content);
                }
            }

            @Override
            public void onFailure(Call<List<prueba>> call, Throwable t) {
                Idtexto.setText(t.getMessage());
            }
        });
    }

    private void Obtener_Datos()
    {
        Retrofit retrofit = new Retrofit.Builder().baseUrl("http://10.0.2.2:5000/api/").addConverterFactory(GsonConverterFactory.create()).build();
        Post_Productos post_productos = retrofit.create(Post_Productos.class);
        Call<List<Productos>> call = post_productos.getDatos();
        call.enqueue(new Callback<List<Productos>>() {
            @Override
            public void onResponse(Call<List<Productos>> call, Response<List<Productos>> response) {
                if(!response.isSuccessful())
                {
                    Idtexto.setText("codigo: "+response.code());
                    return;
                }
                List<Productos> producto = response.body();
                for(Productos prod:producto)
                {
                    String content="";
                    content+="Id_prod: "+prod.getIdProducto() + "\n";
                    content+="Producto: "+prod.getProducto() + "\n";
                    content+="Marca: "+prod.getmarca() + "\n";
                    content+="Descripcion: "+prod.getDescripcion() + "\n";
                    content+="Imagen: "+prod.getImagen() + "\n";
                    content+="Precio_costo: "+prod.getPrecio_costo() + "\n";
                    content+="Precio_venta: "+prod.getPrecio_venta() + "\n";
                    content+="Existencias: "+prod.getExistencia() + "\n";
                    content+="Fecha_ingreso: "+prod.getFecha_ingreso() + "\n\n";
                    Idtexto.append(content);
                }
            }

            @Override
            public void onFailure(Call<List<Productos>> call, Throwable t) {
                Idtexto.setText(t.getMessage());
            }
        });
    }
    /*
    private void Obtener_Datos()
    {
        Retrofit retrofit = new Retrofit.Builder().baseUrl("https://jsonplaceholder.typicode.com/").addConverterFactory(GsonConverterFactory.create()).build();
        Post_prueba post_prueba=retrofit.create(Post_prueba.class);
        Call<List<prueba>> call = post_prueba.getprueba();
        call.enqueue(new Callback<List<prueba>>() {
            @Override
            public void onResponse(Call<List<prueba>> call, Response<List<prueba>> response) {
                if(!response.isSuccessful())
                {
                    Idtexto.setText("codigo: "+response.code());
                    return;
                }
                List<prueba> post = response.body();
                for(prueba prueb:post)
                {
                    String content="";
                    content+="UserId: "+prueb.getUserId() + "\n";
                    content+="ID: "+prueb.getId() + "\n";
                    content+="Title: "+prueb.getTitle() + "\n";
                    content+="Body: "+prueb.getBody() + "\n\n";
                    Idtexto.append(content);
                }
            }

            @Override
            public void onFailure(Call<List<prueba>> call, Throwable t) {
                Idtexto.setText(t.getMessage());
            }
        });
    }*/


}