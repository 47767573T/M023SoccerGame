package DDBB;

import java.util.List;

/**
 * Created by 47767573t on 25/02/16.
 */
public class Liga {

    private String nombre = "";
    private int categoria;
    private String patrocinador = "";
    private List<Equipo> equipos;


    public void seRetiraEquipo (int index) {
        equipos.get(index).setLiga(null);
        equipos.remove(index);
    }

    public void addEquipo(Equipo equipo) {equipos.add(equipo);}

    public void cambiarPatrocinador (String patrocinador) { setPatrocinador(patrocinador); }

    //Constructor
    public Liga() {}

    public Liga(String nombre, int categoria, String patrocinador) {
        this.nombre = nombre;
        this.categoria = categoria;
        this.patrocinador = patrocinador;
    }

    //getters-setters
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getCategoria() {
        return categoria;
    }

    public void setCategoria(int categoria) {
        this.categoria = categoria;
    }

    public String getPatrocinador() {
        return patrocinador;
    }

    public void setPatrocinador(String patrocinador) {
        this.patrocinador = patrocinador;
    }

    public void setEquipos(List<Equipo> equipos) { this.equipos = equipos; }

    public List<Equipo> getEquipos() { return equipos; }

    public String toString(){
        String es = "";
        for(int i = 0; i < equipos.size(); i++){
            es = es + "\n\t" + equipos.get(i).getNombre();
        }
        return es;
    }
}
