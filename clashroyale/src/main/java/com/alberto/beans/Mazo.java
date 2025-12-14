package com.alberto.beans;

import java.util.HashMap;
import java.util.Map.Entry;

public class Mazo {
    private HashMap<Carta, Integer> mazo;

    public Mazo() {
        mazo = new HashMap<>();
    }

    public HashMap<Carta, Integer> getMazo() {
        return this.mazo;
    }

    public boolean estaEnMazo(String id){
        for (Carta carta : mazo.keySet()){
            if(carta.getId().equals(id)){
                return true;
            }
        }
        return false;
    }

    public int getAtaqueTotal() {
        int ataqueTotal = 0;
        for (Entry<Carta, Integer> entry : mazo.entrySet()) {
            ataqueTotal += entry.getKey().getAtaque();
        }
        return ataqueTotal;
    }

    public int getVidaTotal() {
        int vidaTotal = 0;
        for (Entry<Carta, Integer> entry : mazo.entrySet()) {
            vidaTotal += entry.getKey().getVida();
        }
        return vidaTotal;
    }

    public int costeElixirMedio() {
        int elixirMedio = 0;
        int suma = 0;
        for (Entry<Carta, Integer> entry : mazo.entrySet()) {
            suma += entry.getKey().getElixir();
            elixirMedio = suma / 8;
        }
        return elixirMedio;
    }

    public int contarComunes() {
        int contar = 0;
        for (Entry<Carta, Integer> entry : mazo.entrySet()) {
            if (entry.getKey().getRareza().equals("Común")) {
                contar++;
            }
        }
        return contar;
    }

    public int contarRaras() {
        int contar = 0;
        for (Entry<Carta, Integer> entry : mazo.entrySet()) {
            if (entry.getKey().getRareza().equals("Rara")) {
                contar++;
            }
        }
        return contar;
    }

    public int contarEpica() {
        int contar = 0;
        for (Entry<Carta, Integer> entry : mazo.entrySet()) {
            if (entry.getKey().getRareza().equals("Épica")) {
                contar++;
            }
        }
        return contar;
    }

    public int contarLegen() {
        int contar = 0;
        for (Entry<Carta, Integer> entry : mazo.entrySet()) {
            if (entry.getKey().getRareza().equals("Legendaria")) {
                contar++;
            }
        }
        return contar;
    }

    public void setMazo(HashMap<Carta, Integer> mazo) {
        this.mazo = mazo;
    }

    public void addCarta(Carta carta) {
        for (Carta c : mazo.keySet()) {
            if (c.getId().equals(carta.getId())) {
                System.out.println("Error, no se puede añadir una misma carta al mazo");
                return;
            }
        }
        mazo.put(carta, 1);
    }

    public void eliminarCarta(Carta carta) {
        mazo.remove(carta);
    }

    public void vaciar() {
        mazo.clear();
    }

}
