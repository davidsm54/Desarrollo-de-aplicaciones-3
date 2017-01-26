package com.davidsm54.ejemplo_002;

/**
 * Created by davidsm54 on 21/01/17.
 */

public class Persona {
    private String nombre;
    private double peso;
    private double estatura;
    private char sexo;
    private byte ejercicio;
    private double imc;
    private String status;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    public double getEstatura() {
        return estatura;
    }

    public void setEstatura(double estatura) {
        this.estatura = estatura;
    }

    public char getSexo() {
        return sexo;
    }

    public void setSexo(char sexo) {
        this.sexo = sexo;
    }

    public byte getEjercicio() {
        return ejercicio;
    }

    public void setEjercicio(byte ejercicio) {
        this.ejercicio = ejercicio;
    }

    public double getImc() {
        return imc;
    }

    public void setImc(double imc) {
        this.imc = imc;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }


    public void calcularIMC(){
            imc = peso / (estatura * estatura);
    }

    @Override
    public String toString() {
        return "\n"+nombre+
                "\n" + peso +" KG"+
                "\n" + estatura +" Mts"+
                "\n" + sexo +
                "\nIMC: " + imc +
                "\nStatus: " + status
                ;
    }
}
