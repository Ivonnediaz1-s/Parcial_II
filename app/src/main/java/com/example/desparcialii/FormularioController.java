package com.example.desparcialii;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class FormularioController {

    private BaseDatos BD;

    public FormularioController(Context context) {
        this.BD = new BaseDatos(context);
    }

    public long agregarFormulario(Formulario formulario){
        try{

            SQLiteDatabase database = BD.getWritableDatabase();
            ContentValues values = new ContentValues();

            values.put(BDDef.c_cedula, formulario.getCedula());
            values.put(BDDef.c_nombre, formulario.getNombre());
            values.put(BDDef.c_estrato, formulario.getEstrato());
            values.put(BDDef.c_salario, formulario.getSalario());
            values.put(BDDef.c_nivel_educativo, formulario.getNivel_educativo());
            long id = database.insert(BDDef.tabla_formularios,null, values);
            return id;
        }
        catch (Exception exc){
            System.out.println("Error en la inserción");
            return 0;
        }
    }

    public List<Formulario> formularioFiltro(){
        SQLiteDatabase database = BD.getReadableDatabase();
        List<Formulario> formularioList = new ArrayList<>();

        Cursor c = database.query(BDDef.tabla_formularios, null, null, null, null, null, null);

        if (c.getCount()>0) {
            c.moveToFirst();
            while (!c.isAfterLast()) {
                Formulario f = new Formulario();
                f.setCedula(c.getString(c.getColumnIndexOrThrow(""+BDDef.c_cedula+"")));
                f.setNombre(c.getString(c.getColumnIndexOrThrow(""+BDDef.c_nombre+"")));
                f.setEstrato(c.getString(c.getColumnIndexOrThrow(""+BDDef.c_estrato+"")));
                f.setNivel_educativo(c.getString(c.getColumnIndexOrThrow(""+BDDef.c_nivel_educativo+"")));
                f.setSalario(c.getString(c.getColumnIndexOrThrow(""+BDDef.c_salario+"")));

                formularioList.add(f);
                c.moveToNext();
            }

            database.close();
            return formularioList;
        }
        else{
            database.close();
            return formularioList;
        }
    }

    public Formulario buscarFormulario(String Cedula){
        SQLiteDatabase database = BD.getReadableDatabase();
        Formulario formulario = new Formulario();
        String[] args ={Cedula};

        Cursor c = database.query(BDDef.tabla_formularios, null, ""+BDDef.c_cedula+"=?", args, null, null, null);

        if (c.getCount()>0) {
            c.moveToFirst();
            formulario.setCedula(Cedula);
            formulario.setNombre(c.getString(c.getColumnIndexOrThrow(""+BDDef.c_nombre+"")));
            formulario.setEstrato(c.getString(c.getColumnIndexOrThrow(""+BDDef.c_estrato+"")));
            formulario.setSalario(c.getString(c.getColumnIndexOrThrow(""+BDDef.c_salario+"")));
            formulario.setNivel_educativo(c.getString(c.getColumnIndexOrThrow(""+BDDef.c_nivel_educativo+"")));


            database.close();
            return formulario;
        }
        else{
            database.close();
            return formulario;
        }

    }

    public long eliminarFormulario(String Cedula){
        SQLiteDatabase database = BD.getWritableDatabase();
        String[] args ={Cedula};

        long id = database.delete(BDDef.tabla_formularios,""+BDDef.c_cedula+"=?",args);
        database.close();

        return id;
    }

    public long actualizarFormulario(Formulario formulario){
        try{
            SQLiteDatabase database = BD.getWritableDatabase();
            ContentValues valores = new ContentValues();

            String[] args ={formulario.getCedula()};

            valores.put(BDDef.c_nombre, formulario.getNombre());
            valores.put(BDDef.c_estrato, formulario.getEstrato());
            valores.put(BDDef.c_salario, formulario.getSalario());
            valores.put(BDDef.c_nivel_educativo, formulario.getNivel_educativo());

            long id = database.update(BDDef.tabla_formularios, valores,""+BDDef.c_cedula+"=?",args);
            database.close();

            return id;
        }
        catch (Exception exc){
            System.out.println("Error en la actualización");
            return 0;
        }

    }
    public Cursor allFormularios() {
        try {
            SQLiteDatabase database = BD.getReadableDatabase();
            Cursor cursor = database.rawQuery("select "+BDDef.c_cedula+" as _id , "+BDDef.c_nombre+", "+BDDef.c_estrato+", "+BDDef.c_salario+", " +
                    ""+BDDef.c_nivel_educativo+" from "+BDDef.tabla_formularios+"", null);
            return cursor;
        } catch (Exception exc) {
            System.out.println("Error en la consulta");
            return null;
        }
    }
}