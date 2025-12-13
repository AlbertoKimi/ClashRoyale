package com.alberto.beans;

import java.util.ArrayList;

public class FuenteDeDatos {

    private ArrayList<Carta> cartas;
    private static FuenteDeDatos instancia;

    private FuenteDeDatos() {
        inicializar();
    }

    public static FuenteDeDatos getInstancia() {
        if (instancia == null)
            return new FuenteDeDatos();
        else
            return instancia;
    }

    private void inicializar() {
        cartas = new ArrayList<>();
        cartas.add(new Carta("1", "Arqueras", 3, 112, 304, "Común", "imagenes/Archers.png"));
        cartas.add(new Carta("2", "Caballero", 3, 159, 1399, "Común", "imagenes/Knight.png"));
        cartas.add(new Carta("3", "Duendes con lanza", 2, 50, 110, "Común", "imagenes/SpearGobs.png"));
        cartas.add(new Carta("4", "Esqueletos", 1, 67, 67, "Común", "imagenes/Skellies.png"));
        cartas.add(new Carta("5", "Esbirros", 3, 84, 190, "Común", "imagenes/Minions.png"));
        cartas.add(new Carta("6", "Horda de esbirros", 5, 84, 190, "Común", "imagenes/Horde.png"));
        cartas.add(new Carta("7", "Bárbaros", 5, 159, 346, "Común", "imagenes/Barbs.png"));
        cartas.add(new Carta("8", "Gigante", 5, 211, 3276, "Rara", "imagenes/Giant.png"));
        cartas.add(new Carta("9", "Mosquetera", 4, 289, 955, "Rara", "imagenes/Musk.png"));
        cartas.add(new Carta("10", "Valquiria", 4, 360, 1736, "Rara", "imagenes/Valk.png"));
        cartas.add(new Carta("11", "Montapuercos", 4, 384, 1484, "Rara", "imagenes/Hog.png"));
        cartas.add(new Carta("12", "Mago", 5, 373, 955, "Rara", "imagenes/Wiz.png"));
        cartas.add(new Carta("13", "Mini P.E.K.K.A.", 4, 598, 1648, "Rara", "imagenes/MP.png"));
        cartas.add(new Carta("14", "Bebé dragón", 4, 161, 1152, "Épica", "imagenes/BabyD.png"));
        cartas.add(new Carta("15", "P.E.K.K.A.", 7, 678, 3458, "Épica", "imagenes/PEKKA.png"));
        cartas.add(new Carta("16", "Gólem", 8, 259, 6784, "Épica", "imagenes/Golem.png"));
        cartas.add(new Carta("17", "Bruja", 5, 113, 524, "Épica", "imagenes/Witch.png"));
        cartas.add(new Carta("18", "Ejército de esqueletos", 3, 67, 67, "Épica", "imagenes/Skarmy.png"));
        cartas.add(new Carta("19", "Globo bombástico", 5, 798, 1746, "Épica", "imagenes/Balloon.png"));
        cartas.add(new Carta("20", "Príncipe", 5, 330, 1591, "Épica", "imagenes/Prince.png"));
        cartas.add(new Carta("21", "Mago eléctrico", 4, 110, 944, "Legendaria", "imagenes/eWiz.png"));
        cartas.add(new Carta("22", "Dragón infernal", 4, 30, 1070, "Legendaria", "imagenes/InfernoD.png"));
        cartas.add(new Carta("23", "Sabueso de lava", 7, 45, 5700, "Legendaria", "imagenes/Lava.png"));
        cartas.add(new Carta("24", "Espíritu de hielo", 1, 91, 190, "Común", "imagenes/IceSpirit.png"));
        cartas.add(new Carta("25", "Flechas", 3, 144, 0, "Común", "imagenes/Arrows.png"));
        cartas.add(new Carta("26", "Descarga (Zap)", 2, 159, 0, "Común", "imagenes/Zap.png"));
        cartas.add(new Carta("27", "Recolector de elixir", 6, 0, 1280, "Épica", "imagenes/Pump.png"));
        cartas.add(new Carta("28", "Choza de bárbaros", 7, 0, 1673, "Épica", "imagenes/BarbHut.png"));
        cartas.add(new Carta("29", "Torre Tesla", 4, 135, 954, "Común", "imagenes/Tesla.png"));
        cartas.add(new Carta("30", "Torre infernal", 5, 75, 1070, "Rara", "imagenes/Inferno.png"));
    }

    public ArrayList<Carta> getCartas() {
        return cartas;
    }

    public Carta getCarta(String id) {
        for (Carta carta : cartas) {
            if (id.equals(carta.getId()))
                return carta;
        }

        return null;
    }

    public boolean eliminarCarta(String id) {
        for (int i = 0; i < cartas.size(); i++) {
            if (id.equals(cartas.get(i).getId())) {
                cartas.remove(i);
                return true;
            }
        }
        return false;
    }

    public void anadirCarta(Carta carta){
        if(carta!=null){
            cartas.add(carta);
        }
    }
}
