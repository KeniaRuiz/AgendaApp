package com.keniaruiz.agendaapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.keniaruiz.agendaapp.db.dbAmigo;

public class VerActivity extends AppCompatActivity {

    EditText nombre, telefono, direccion, correo, nota;
    Button guardar;
    FloatingActionButton editar, eliminar;

    Amigo amigo;
    int id = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver);

        nombre = findViewById(R.id.text_nombre);
        telefono = findViewById(R.id.text_telefono);
        direccion = findViewById(R.id.text_direccion);
        correo = findViewById(R.id.text_correo);
        nota = findViewById(R.id.text_nota);
        guardar = findViewById(R.id.button);
        editar = findViewById(R.id.floatingEditar);
        eliminar = findViewById(R.id.floatingEliminar);

        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if (extras == null) {
                id = Integer.parseInt(null);

            } else {
                id = extras.getInt("ID");
            }
        } else {
            id = (int) savedInstanceState.getSerializable("ID");
        }

        dbAmigo dbUsuarios = new dbAmigo(VerActivity.this);
        amigo = dbUsuarios.verAmigos(id);

        if (amigo != null) {
            nombre.setText(amigo.getNombre());
            //telefono.setText(amigo.getTelefono());
            //direccion.setText(amigo.getDireccion());
            correo.setText(amigo.getCorreo_electronico());
            guardar.setVisibility(View.INVISIBLE);
            nombre.setInputType(InputType.TYPE_NULL);
            telefono.setInputType(InputType.TYPE_NULL);
            direccion.setInputType(InputType.TYPE_NULL);
            correo.setInputType(InputType.TYPE_NULL);
            nota.setInputType(InputType.TYPE_NULL);
        }

        editar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(VerActivity.this, EditarActivity.class);
                intent.putExtra("ID", id);
                startActivity(intent);
            }
        });

        eliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(VerActivity.this);
                builder.setMessage("¿Desea eliminar este contacto?")
                        .setPositiveButton("Si", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                if (dbUsuarios.eliminarAmigo(id)) {
                                    lista();
                                }
                            }
                        }).setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                }).show();
            }
        });
    }

    private void lista() {
        Intent intent = new Intent(this, MainActivity.class);

        startActivity(intent);
    }
}