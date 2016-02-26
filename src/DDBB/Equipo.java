package DDBB;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 47767573t on 25/02/16.
 */
public class Equipo {

    private String nombre = "";
    private String estadio = "";
    private List<Jugador> jugadores;
    private Entrenador entrenador;

    public void cambioLiga(){

    }

    public void addJugador(Jugador e){
        jugadores.add(e);
    }

    //constr
    public Equipo() {}

    public Equipo(String nombre) {
        this.nombre = nombre;
    }

    public Equipo(String nombre, String estadio, Entrenador entrenador) {
        this.nombre = nombre;
        this.estadio = estadio;
        this.jugadores = new ArrayList<Jugador>();
        this.entrenador = entrenador;
    }

    //getters-setters
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEstadio() {
        return estadio;
    }

    public void setEstadio(String estado) {
        this.estadio = estado;
    }

    public List<Jugador> getJugadores() {
        return jugadores;
    }

    public void setJugadores(List<Jugador> jugadores) {
        this.jugadores = jugadores;
    }

    public Entrenador getEntrenador() {
        return entrenador;
    }

    public void setEntrenador(Entrenador entrenador) {
        this.entrenador = entrenador;
    }

    public String toString(){
        String js = "";
        for(int i = 0; i < jugadores.size(); i++){
            js = js + "\n\t" + jugadores.get(i).toStringSimple();
        }
        return
                "[" +
                        "" +
                        "Equipo: " +
                        "nombre = " + nombre +
                        " ,estadio = " + estadio +
                        "\njugadores:\t" +
                        js + "]";
    }


}