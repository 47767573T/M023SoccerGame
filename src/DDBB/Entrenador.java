package DDBB;

/**
 * Created by 47767573t on 25/02/16.
 */
public class Entrenador {

    private String nombre = "";
    private int anyosExperiencia;

    public void cambioEquipo(){

    }

    //constr
    public Entrenador(){}

    public Entrenador(String nombre, int anyosExperiencia) {
        this.nombre = nombre;
        this.anyosExperiencia = anyosExperiencia;
    }

    //getters-setters
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getAnyosExperiencia() {
        return anyosExperiencia;
    }

    public void setAnyosExperiencia(int anyosExperiencia) {
        this.anyosExperiencia = anyosExperiencia;
    }

}
