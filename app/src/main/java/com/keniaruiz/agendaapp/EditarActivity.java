package com.keniaruiz.agendaapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.keniaruiz.agendaapp.db.dbAmigo;

public class EditarActivity extends AppCompatActivity {

    EditText nombre, telefono, direccion, correo, nota;
    Button guardar;
    FloatingActionButton editar, eliminar;
    boolean correcto = false;

    Amigo amigo;
    int id=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver);

        nombre= findViewById(R.id.text_nombre);
        telefono= findViewById(R.id.text_telefono);
        direccion= findViewById(R.id.text_direccion);
        correo= findViewById(R.id.text_correo);
        nota= findViewById(R.id.text_nota);
        guardar= findViewById(R.id.button);
        editar = findViewById(R.id.floatingEditar);
        eliminar = findViewById(R.id.floatingEliminar);
        editar.setVisibility(View.INVISIBLE);
        eliminar.setVisibility(View.INVISIBLE);

        if (savedInstanceState == null){
            Bundle extras = getIntent().getExtras();
            if (extras == null){
                id=Integer.parseInt(null);

            }else {
                id= extras.getInt("ID");
            }
        }else{
            id = (int) savedInstanceState.getSerializable("ID");
        }

        final dbAmigo dbUsuario = new dbAmigo(EditarActivity.this);
        amigo = dbUsuario.verAmigos(id);

        if (amigo !=null){
            nombre.setText(amigo.getNombre());
            telefono.setText(amigo.getTelefono());
            direccion.setText(amigo.getDireccion());
            correo.setText(amigo.getCorreo_electronico());
            nota.setText(amigo.getNota());
        }

        guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!nombre.getText().toString().equals("") && !telefono.getText().toString().equals("") && !direccion.getText().toString().equals("") && !correo.getText().toString().equals("") && !nota.getText().toString().equals("")){

                    correcto = dbUsuario.editarAmigo(id,nombre.getText().toString(),telefono.getText().toString(), direccion.getText().toString(), correo.getText().toString(), nota.getText().toString());

                    if (correcto){
                        Toast.makeText(EditarActivity.this, "Amigo modificado", Toast.LENGTH_SHORT).show();
                        verRegistro();
                    }else{
                        Toast.makeText(EditarActivity.this, "Error al modificar amigo", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(EditarActivity.this, "Favor de llenar los campos", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    private void verRegistro(){
        Intent intent = new Intent(this, VerActivity.class);
        intent.putExtra("ID",id);
        startActivity(intent);
    }
}