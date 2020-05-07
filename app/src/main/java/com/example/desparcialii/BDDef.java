package com.example.desparcialii;

public class BDDef {

    public static final String BDname = "Formularios";
    public static final String tabla_formularios = "formulario";
    public static final String c_cedula = "cedula";
    public static final String c_nombre = "nombre";
    public static final String c_estrato = "estrato";
    public static final String c_salario = "salario";
    public static final String c_nivel_educativo = "nivel_educativo";


    public static final String create_tabla_formularios = "CREATE TABLE IF NOT EXISTS "+tabla_formularios+" (" +
            "  "+c_cedula+" INTEGER PRIMARY KEY," +
            "  "+c_nombre+" TEXT," +
            "  "+c_estrato+" TEXT," +
            "  "+c_salario+" TEXT," +
            "  "+c_nivel_educativo+" TEXT" +
            ");";

}