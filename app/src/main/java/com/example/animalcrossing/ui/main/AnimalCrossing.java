package com.example.animalcrossing.ui.main;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity
public class AnimalCrossing implements Serializable {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String nombre;
    private String personalidad;
    @ColumnInfo(name = "especie")
    private String especie;
    private String icono;

    public AnimalCrossing(int id, String nombre, String personalidad, String especie, String icono) {
        this.id = id;
        this.nombre = nombre;
        this.personalidad = personalidad;
        this.especie = especie;
        this.icono = icono;
    }

    public AnimalCrossing() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", personalidad='" + personalidad + '\'' +
                ", especie='" + especie + '\'' +
                ", icono='" + icono + '\'' +
                '}';
    }
}
