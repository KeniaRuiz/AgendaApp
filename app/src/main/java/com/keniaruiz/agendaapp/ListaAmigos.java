package com.keniaruiz.agendaapp;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ListaAmigos extends RecyclerView.Adapter<ListaAmigos.ContactoViewHolder> {

    ArrayList<Amigo> listaAmigos;

    public ListaAmigos(ArrayList<Amigo> listaAmigos){
        this.listaAmigos = listaAmigos;
    }

    @NonNull
    @Override
    public ListaAmigos.ContactoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.lista_item_amigo,null,false);
        return new ContactoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListaAmigos.ContactoViewHolder holder, int position) {
        holder.viewNombre.setText(listaAmigos.get(position).getNombre());
        holder.viewTelefono.setText(listaAmigos.get(position).getTelefono());
    }

    @Override
    public int getItemCount() {
        return listaAmigos.size();
    }

    public class ContactoViewHolder extends RecyclerView.ViewHolder {

        TextView viewNombre, viewTelefono;

        public ContactoViewHolder(@NonNull View itemView) {
            super(itemView);

            viewNombre = itemView.findViewById(R.id.nombre_text);
            viewTelefono = itemView.findViewById(R.id.telefono_text);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Context context = view.getContext();
                    Intent intent = new Intent(context, VerActivity.class);
                    intent.putExtra("ID", listaAmigos.get(getAdapterPosition()).getId());
                    context.startActivity(intent);
                }
            });
        }
    }
}
