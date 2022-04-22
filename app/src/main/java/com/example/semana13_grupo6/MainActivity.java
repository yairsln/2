package com.example.semana13_grupo6;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private EditText txtCodigo, txtNombre,txtCarrera,txtCiclo;
    private Button btnNuevo,btnConsultar,btnGrabar,btnModificar,btnEliminar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtCodigo = (EditText) findViewById(R.id.txtCodigo);
        txtNombre = (EditText) findViewById(R.id.txtNombre);
        txtCarrera = (EditText) findViewById(R.id.txtCarrera);
        txtCiclo = (EditText) findViewById(R.id.txtCiclo);
        btnNuevo = (Button) findViewById(R.id.btnNuevo);
        btnConsultar = (Button) findViewById(R.id.btnConsultar);
        btnGrabar = (Button) findViewById(R.id.btnGrabar);
        btnModificar = (Button) findViewById(R.id.btnModificar);
        btnEliminar = (Button) findViewById(R.id.btnEliminar);

    }
    public void Nuevo(View view){
        txtCodigo.setText("");
        txtNombre.setText("");
        txtCarrera.setText("");
        txtCiclo.setText("");
        txtCodigo.requestFocus();


    }
    public void Consultar (View view){
        BD admin = new BD(this,"administracion", null ,1);
        SQLiteDatabase bdAlumno = admin.getWritableDatabase();
        String cod = txtCodigo.getText().toString();
        Cursor fi = bdAlumno.rawQuery("Select * from Alumno ORDER BY Nombre",null);
        if(fi.moveToFirst()){
            txtCodigo.setText(fi.getString(0));
            txtNombre.setText(fi.getString(1));
            txtCarrera.setText(fi.getString(2));
            txtCiclo.setText(fi.getString(3));

        }else{
            Toast.makeText(this,"Codigo del Alumno Incorrecto!!",Toast.LENGTH_LONG).show();

        }
        bdAlumno.close();


    }
    public  void Grabar (View view){
        BD admin = new BD(this,"administracion",null,1);
        SQLiteDatabase bdAlumno = admin.getWritableDatabase();
        String cod = txtCodigo.getText().toString();
        String nom = txtNombre.getText().toString();
        String carrera  = txtCarrera.getText().toString();
        String ciclo = txtCiclo.getText().toString();

        ContentValues registro = new ContentValues();
        registro.put("CodAlumno",cod);
        registro.put("Nombre",nom);
        registro.put("CarreraProf",carrera);
        registro.put("Ciclo",ciclo);
        bdAlumno.insert("Alumno",null, registro);
        bdAlumno.close();
        Nuevo(view);
        Toast.makeText(this,"Datos del Alumno Grabados",Toast.LENGTH_LONG).show();


    }

    public  void Modificar (View view){
        BD admin = new BD(this,"administracion",null,1);
        SQLiteDatabase bdAlumno = admin.getWritableDatabase();
        String cod = txtCodigo.getText().toString();
        String nom = txtNombre.getText().toString();
        String carrera  = txtCarrera.getText().toString();
        String ciclo = txtCiclo.getText().toString();

        ContentValues registro = new ContentValues();
        registro.put("CodAlumno",cod);
        registro.put("Nombre",nom);
        registro.put("CarreraProf",carrera);
        registro.put("Ciclo",ciclo);
        int cant  = bdAlumno.update("Alumno",registro,"CodAlumnos = '"+cod+"'",null);
        if (cant==1){
            Toast.makeText(this,"Datos del Alumno Modificados",Toast.LENGTH_LONG).show();
        }else {
            Toast.makeText(this,"Datos del Alumno Incorrecto",Toast.LENGTH_LONG).show();
        }

    }

    public  void Eliminar (View view){
        BD admin = new BD(this,"administracion",null,1);
        SQLiteDatabase bdAlumno = admin.getWritableDatabase();
        String cod = txtCodigo.getText().toString();
        String nom = txtNombre.getText().toString();
        String carrera  = txtCarrera.getText().toString();
        String ciclo = txtCiclo.getText().toString();

        ContentValues registro = new ContentValues();
        registro.put("CodAlumno",cod);
        registro.put("Nombre",nom);
        registro.put("CarreraProf",carrera);
        registro.put("Ciclo",ciclo);
        int cant  = bdAlumno.delete("Alumno","CodAlumno=' "+cod+"'",null);
        bdAlumno.close();
        txtCodigo.setText("");
        txtNombre.setText("");
        txtCarrera.setText("");
        txtCiclo.setText("");
        if (cant==1){

            Toast.makeText(this,"Datos del Alumno Eliminados",Toast.LENGTH_LONG).show();
        }else {
            Toast.makeText(this,"Datos del Alumno Incorrecto",Toast.LENGTH_LONG).show();
        }

    }



}