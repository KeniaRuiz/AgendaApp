package com.keniaruiz.agendaapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.keniaruiz.agendaapp.db.db;
import com.keniaruiz.agendaapp.db.dbAmigo;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    RecyclerView listaUsuario;
    ArrayList<Amigo> listaArrayUsuarios;
    FloatingActionButton insertar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listaUsuario = findViewById(R.id.lista_amigos);
        listaUsuario.setLayoutManager(new LinearLayoutManager(this));

        dbAmigo dbUsuarios = new dbAmigo(MainActivity.this);
        listaArrayUsuarios = new ArrayList<>();

        ListaAmigos adapter = new ListaAmigos(dbUsuarios.listaAmigos());
        listaUsuario.setAdapter(adapter);

        insertar = findViewById(R.id.floatingInsertar);

        /*
        db dbHelper = new db(MainActivity.this);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        if (db!=null){
            Toast.makeText(MainActivity.this, "Base de datos creada", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(MainActivity.this, "Error al crear base de datos", Toast.LENGTH_SHORT).show();
        }
        */

        insertar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nuevoRegistro();
            }
        });
    }


    private void nuevoRegistro(){
        Intent intent = new Intent(this, InsertarActivity.class);
        startActivity(intent);
    }
}