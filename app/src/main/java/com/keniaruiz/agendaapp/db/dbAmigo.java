package com.keniaruiz.agendaapp.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;

import com.keniaruiz.agendaapp.Amigo;

import java.util.ArrayList;

public class dbAmigo extends db {

    Context context;

    public dbAmigo(@Nullable Context context) {
        super(context);
        this.context = context;
    }

    public long insertarAmigo(String nombre, String telefono, String direccion, String correo_electronico, String nota){
        long id=0;

        try{
            db dbHelper = new db(context);
            SQLiteDatabase db = dbHelper.getWritableDatabase();

            ContentValues values = new ContentValues();
            values.put("nombre",nombre);
            values.put("telefono",telefono);
            values.put("direccion",direccion);
            values.put("correo_electronico",correo_electronico);
            values.put("nota",nota);

            id = db.insert(TABLE_AMIGOS, null, values);
            db.close();
        }catch (Exception ex){
            ex.toString();
        }

        return id;
    }

    public ArrayList<Amigo> listaAmigos(){
        db dbHelper = new db(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ArrayList<Amigo>mostrarUsuarios = new ArrayList<>();
        Amigo amigo = null;
        Cursor cursorAmigo = null;

        cursorAmigo = db.rawQuery("SELECT * FROM " + TABLE_AMIGOS, null);
        if (cursorAmigo.moveToFirst()){
            do {
                amigo = new Amigo();
                amigo.setId(cursorAmigo.getInt(0));
                amigo.setNombre(cursorAmigo.getString(1));
                amigo.setTelefono(cursorAmigo.getString(2));
                amigo.setDireccion(cursorAmigo.getString(3));
                amigo.setCorreo_electronico(cursorAmigo.getString(4));
                amigo.setNota(cursorAmigo.getString(5));

                mostrarUsuarios.add(amigo);

            }while (cursorAmigo.moveToNext());
        }

        cursorAmigo.close();
        return mostrarUsuarios;
    }

    public Amigo verAmigos(int id){
        db dbHelper = new db(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        Amigo amigo = null;
        Cursor cursorAmigo;

        cursorAmigo = db.rawQuery("SELECT * FROM " + TABLE_AMIGOS + " WHERE id = "+ id + " LIMIT 1", null);
        if (cursorAmigo.moveToFirst()){
            amigo = new Amigo();
            amigo.setId(cursorAmigo.getInt(0));
            amigo.setNombre(cursorAmigo.getString(1));
            amigo.setTelefono(cursorAmigo.getString(2));
            amigo.setDireccion(cursorAmigo.getString(3));
            amigo.setCorreo_electronico(cursorAmigo.getString(4));
            amigo.setNota(cursorAmigo.getString(5));
        }

        cursorAmigo.close();
        return amigo;
    }

    public boolean editarAmigo(int id,String nombre, String telefono, String direccion, String correo_electronico, String nota){

        boolean correcto = false;

        db dbHelper = new db(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        try{
            db.execSQL("UPDATE " + TABLE_AMIGOS + " SET nombre = '"+ nombre
                    + "', telefono = '"+ telefono + "', direccion = '"+ direccion + "', correo_electronico = '"+
                    correo_electronico  + "', nota = '"+ nota + "' WHERE id='" + id + "' ");
            correcto= true;

        }catch (Exception ex){
            ex.toString();
            correcto=false;
        }finally {
            db.close();
        }

        return correcto;
    }

    public boolean eliminarAmigo(int id){

        boolean correcto = false;

        db dbHelper = new db(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        try{
            db.execSQL("DELETE FROM "+ TABLE_AMIGOS + " WHERE id = '"+ id +"'");
            correcto= true;

        }catch (Exception ex){
            ex.toString();
            correcto=false;
        }finally {
            db.close();
        }

        return correcto;

    }
}
