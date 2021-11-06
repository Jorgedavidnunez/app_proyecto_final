package com.jd.proyecto_final;
import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.jd.proyecto_final.Models.Productos;
import com.jd.proyecto_final.Models.prueba;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Adaptador extends RecyclerView.Adapter<Adaptador.ViewHolder>
{
    //prueba[] prueba;
    Context context;
    //ArrayList<prueba> lista_original;
    ArrayList<Productos> productos;
    ArrayList<Productos> lista_original;

    public Adaptador(ArrayList<Productos> productos, recycleview activity)
    {
        this.productos=productos;
        this.context=activity;
        lista_original=new ArrayList<>();
        lista_original.addAll(productos);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void Filtrar(final String busqueda)
    {
        int longitud=busqueda.length();
        if(longitud==0)
        {
            productos.clear();
            productos.addAll(lista_original);
        }
        else
        {
            List<Productos> collection = productos.stream().filter(i -> i.getProducto().toLowerCase().contains(busqueda.toLowerCase())).collect(Collectors.toList());
            productos.clear();
            //productos.addAll(collection);
            for(Productos c: lista_original)
            {
                if(c.getProducto().toLowerCase().contains(busqueda.toLowerCase()))
                {
                    productos.add(c);
                }
            }
        }
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.cardview,parent,false);
        ViewHolder viewHolder=new ViewHolder((view));
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull Adaptador.ViewHolder holder, int position) {
        final Productos pruebaList = productos.get(position);
        holder.txt_IdProducto.setText(String.valueOf(pruebaList.getIdProducto()));
        holder.txt_producto.setText(String.valueOf(pruebaList.getProducto()));
        holder.txt_marca.setText(pruebaList.getmarca());
        holder.txt_descripcion.setText(pruebaList.getDescripcion());
        holder.txt_imagen.setText((pruebaList.getImagen()));
        holder.txt_precio_costo.setText(String.valueOf( pruebaList.getPrecio_costo()));
        holder.txt_precio_venta.setText(String.valueOf(pruebaList.getPrecio_venta()));
        holder.txt_existencia.setText(String.valueOf(pruebaList.getExistencia()));
        holder.txt_fecha.setText(pruebaList.getFecha_ingreso());


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context,String.valueOf(pruebaList.getIdProducto()),Toast.LENGTH_SHORT);
            }
        });
    }

    @Override
    public int getItemCount() {
        return productos.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {
        TextView txt_IdProducto, txt_producto, txt_marca, txt_descripcion, txt_imagen, txt_precio_costo, txt_precio_venta, txt_existencia, txt_fecha;

        public ViewHolder(@NonNull View itemView)
        {
            super(itemView);
            txt_IdProducto=itemView.findViewById(R.id.txt_IdProducto);
            txt_producto=itemView.findViewById(R.id.txt_Producto);
            txt_marca=itemView.findViewById(R.id.txt_marca);
            txt_descripcion=itemView.findViewById(R.id.txt_descripcion);
            txt_imagen=itemView.findViewById(R.id.txt_imagen);
            txt_precio_costo=itemView.findViewById(R.id.txt_precio_costo);
            txt_precio_venta=itemView.findViewById(R.id.txt_precio_venta);
            txt_existencia=itemView.findViewById(R.id.txt_existencia);
            txt_fecha=itemView.findViewById(R.id.txt_fecha);

        }
    }
}