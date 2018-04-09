package com.example.juanpedrog.laboratorio331serviciosweb;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

/**
 * Created by juanpedrog on 8/04/18.
 */

public class Adapter extends RecyclerView.Adapter<Adapter.ListaViewHolder>{
    List<Item> datos;
    public Adapter(List<Item> datos){
        this.datos=datos;
    }
    @Override
    public ListaViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_card,parent,false);
        ListaViewHolder listaViewHolder=new ListaViewHolder(v);
        return listaViewHolder;
    }

    @Override
    public void onBindViewHolder(ListaViewHolder holder, int position) {
        holder.id.setText(datos.get(position).getId());
        holder.nombre.setText(datos.get(position).getNombre());
        holder.direccion.setText(datos.get(position).getDireccion());
    }

    @Override
    public int getItemCount() {
        return datos.size();
    }

    public static class ListaViewHolder extends RecyclerView.ViewHolder{
        TextView id,nombre,direccion;
        Button editar,eliminar;
        public ListaViewHolder(final View itemView) {
            super(itemView);
            id=itemView.findViewById(R.id.id_alumno);
            nombre=itemView.findViewById(R.id.nombre);
            direccion=itemView.findViewById(R.id.direccion);
            editar=itemView.findViewById(R.id.editar);
            editar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(itemView.getContext(), Actualizar.class);
                    intent.putExtra("id",id.getText());
                    intent.putExtra("nombre",nombre.getText());
                    intent.putExtra("direccion",direccion.getText());
                    itemView.getContext().startActivity(intent);
                }
            });
            eliminar=itemView.findViewById(R.id.eliminar);
            eliminar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Deleter s=new Deleter(itemView.getContext(),"http://192.168.1.67/App/borrar_alumno.php",id.getText().toString());
                    s.execute();
                }
            });
        }
    }
}
