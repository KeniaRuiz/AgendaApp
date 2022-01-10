package com.keniaruiz.agendaapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.keniaruiz.agendaapp.db.dbAmigo;

public class InsertarActivity extends AppCompatActivity {

    EditText nombre, telefono, direccion, correo, nota;
    Button guardar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insertar);

        nombre= findViewById(R.id.text_nombre);
        telefono= findViewById(R.id.text_telefono);
        direccion= findViewById(R.id.text_direccion);
        correo= findViewById(R.id.text_correo);
        nota= findViewById(R.id.text_nota);
        guardar= findViewById(R.id.button);

        guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dbAmigo dbUsuarios = new dbAmigo (InsertarActivity.this);
                long id = dbUsuarios.insertarAmigo(nombre.getText().toString(), telefono.getText().toString(), direccion.getText().toString(), correo.getText().toString(), nota.getText().toString());

                if (id>0){
                    Toast.makeText(InsertarActivity.this, "Amigo guardado",Toast.LENGTH_SHORT).show();
                    limpiar();
                }else{
                    Toast.makeText(InsertarActivity.this, "Error al guardar amigo",Toast.LENGTH_SHORT).show();

                }
            }
        });

    }

    private void limpiar(){
        nombre.setText("");
        telefono.setText("");
        direccion.setText("");
        correo.setText("");
        nota.setText("");
    }
}