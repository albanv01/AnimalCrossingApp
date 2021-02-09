package com.example.animalcrossing.ui.main;

public class AnimalCrossing {
    String nombre;
    String personalidad;
    String especie;
    String icono;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPersonalidad() {
        return personalidad;
    }

    public void setPersonalidad(String personalidad) {
        this.personalidad = personalidad;
    }

    public String getEspecie() {
        return especie;
    }

    public void setEspecie(String especie) {
        this.especie = especie;
    }

    public String getIcono() {
        return icono;
    }

    public void setIcono(String icono) {
        this.icono = icono;
    }

    @Override
    public String toString() {
        return "AnimalCrossing{" +
                "nombre='" + nombre + '\'' +
                ", personalidad='" + personalidad + '\'' +
                ", especie='" + especie + '\'' +
                ", icono='" + icono + '\'' +
                '}';
    }
}
