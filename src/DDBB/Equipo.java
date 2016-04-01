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
    private Liga liga = null;

    public void cambioLiga(int indexActual, Liga nuevaliga){
        liga.seRetiraEquipo(indexActual);
        setLiga(nuevaliga);
        nuevaliga.addEquipo(this);
    }

    public void addJugador(Jugador j){
        jugadores.add(j);
    }
    public void addJugadores (ArrayList<Jugador> js) { jugadores.addAll(js);}

    public void seRetiraJugador(int index) {
        jugadores.get(index).setEquipo(null);
        jugadores.remove(index);
    }

    //constructor
    public Equipo() {}

    public Equipo(String nombre) {
        this.nombre = nombre;
    }

    public Equipo(String nombre, String estadio){
        this.nombre = nombre;
        this.estadio = estadio;
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

    public Liga getLiga() { return liga; }

    public void setLiga(Liga liga) { this.liga = liga; }

    public String toString(){
        String js = "";
        for(int i = 0; i < jugadores.size(); i++){
            js = js + "\n\t" + jugadores.get(i).toStringSimple();
        }
        return         "[Equipo: " +
                        "nombre = " + nombre +
                        " ,estadio = " + estadio +
                        "\njugadores:\t" +
                        js + "]";
    }

    public String toStringSimple(){
        return  "[nombre = " + nombre +
                " ,estadio = " + estadio+"]";
    }
}
