package DDBB;

import java.util.Random;

/**
 * Created by 47767573t on 25/02/16.
 */
public class Jugador {


    private String dni = "";
    private String nombre = "";
    private String apellido = "";
    private double altura = 0.0;
    private Caracteristicas caracteristicas;
    private Equipo equipo;


    public void traspaso(int indexActual, Equipo nuevoEquipo){
        equipo.seRetiraJugador(indexActual);
        setEquipo(nuevoEquipo);
        nuevoEquipo.addJugador(this);
    }

   //constr

    public Jugador(){}

    public Jugador(String dni, String nombre, String apellido, double altura) {
        this.dni = dni;
        this.nombre = nombre;
        this.apellido = apellido;
        this.altura = altura;
        this.caracteristicas = new Caracteristicas ();
    }

    //getters-setters

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public double getAltura() {
        return altura;
    }

    public void setAltura(double altura) {
        this.altura = altura;
    }

    public Caracteristicas getCaracteristicas() {
        return this.caracteristicas;
    }

    public void setCaracteristicas(Caracteristicas caracteristicas) { this.caracteristicas = caracteristicas; }


    public Equipo getEquipo() { return equipo; }

    public void setEquipo(Equipo equipo) { this.equipo = equipo; }

    public String toStringSimple(){
        return          "[Jugador: " +
                "dni = " + dni +
                " ,nombre = " + nombre +
                " ,apellido = " + apellido +
                " ,altura = " + altura +
                "]";
    }

    public String toStringCompleto(){
        return          "[Jugador: " +
                        "dni = " + dni +
                        " ,nombre = " + nombre +
                        " ,apellido = " + apellido +
                        " ,altura = " + altura +
                        "\ncaracteristicas:\n" +
                        "\tagilidad" + caracteristicas.getAgilidad() + "\n" +
                        "\tfuerza" + caracteristicas.getFuerza() + "\n" +
                        "\tvelocidad" + caracteristicas.getVelocidad() + "\n" +
                        "\tpase" + caracteristicas.getPase() + "\n" +
                        "\tpenalti" + caracteristicas.getPenalti() +
                        "]";
    }

    public void setCaracteristicasAzar (){
        Random rnd = new Random();
        caracteristicas.setAgilidad(rnd.nextInt(7)+1);
        caracteristicas.setFuerza(rnd.nextInt(7)+1);
        caracteristicas.setVelocidad(rnd.nextInt(7)+1);
        caracteristicas.setPase(rnd.nextInt(7)+1);
        caracteristicas.setPenalti(rnd.nextInt(7)+1);
    }

}
