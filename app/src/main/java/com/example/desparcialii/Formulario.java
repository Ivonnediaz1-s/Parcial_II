package com.example.desparcialii;

public class Formulario {
    private String Cedula;
    private String Nombre;
    private String Estrato;
    private String Salario;
    private String Nivel_educativo;


    public Formulario() {
    }

    public String getCedula() {
        return Cedula;
    }

    public void setCedula(String cedula) {
        this.Cedula = cedula;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        this.Nombre = nombre;
    }

    public String getEstrato() {
        return Estrato;
    }

    public void setEstrato(String estrato) {
        this.Estrato = estrato;
    }

    public String getSalario() {
        return Salario;
    }

    public void setSalario(String salario) {
        this.Salario = salario;
    }

    public String getNivel_educativo() {
        return Nivel_educativo;
    }

    public void setNivel_educativo(String nivel_educativo) {
        this.Nivel_educativo = nivel_educativo;
    }

    public String toString() {
        return "Encuesta{" +
                "cedula='" + Cedula + '\'' +
                ", nombre='" + Nombre + '\'' +
                ", estrato='" + Estrato + '\'' +
                ", salario='" + Salario + '\'' +
                ", nivel_educativo='" + Nivel_educativo + '\'' +
                '}';
    }
}
