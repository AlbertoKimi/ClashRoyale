package com.alberto.beans;

import java.io.Serializable;

public class Carta implements Serializable{
    private String id;
    private String nombre;
    private int elixir;
    private int ataque;
    private int vida;
    private String rareza;
    private String imagen;

    public Carta(){

    }

    public Carta(String id, String nombre, int elixir, int ataque, int vida, String rareza, String imagen){
        this.id=id;
        this.nombre=nombre;
        this.elixir=elixir;
        this.ataque=ataque;
        this.vida=vida;
        this.rareza=rareza;
        this.imagen=imagen;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getElixir() {
        return this.elixir;
    }

    public void setElixir(int elixir) {
        this.elixir = elixir;
    }

    public int getAtaque() {
        return this.ataque;
    }

    public void setAtaque(int ataque) {
        this.ataque = ataque;
    }

    public int getVida() {
        return this.vida;
    }

    public void setVida(int vida) {
        this.vida = vida;
    }

    public String getRareza() {
        return this.rareza;
    }

    public void setRareza(String rareza) {
        this.rareza = rareza;
    }

    public String getImagen() {
        return this.imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }
}
