import DDBB.*;
import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;
import com.db4o.query.Query;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

/**
 * Created by 47767573t on 25/02/16.
 */
public class Main {

    static ObjectContainer db = null;
    private static Liga liga1;
    private static Liga liga2;

    public static void main(String[] args) throws Exception {

        Scanner input = new Scanner(System.in);
        liga1 = new Liga("Division1", 1, "BBVA");
        liga2 = new Liga("Division2", 1, "Danone");


        try {
            db = DataConnection.getInstance();
            String menu;
            boolean on = true;//condicio de sortida del programa

            //DEFINIMOS TODA LA LIGA CON VARIABLES AL AZAR PARA TESTEO

            db.store(liga1);
            db.store(liga2);

            ArrayList <Equipo> newTeam = genEquiposAzar(5);
            guardarEquipos(newTeam);


            //-----------------------------------------------------------


            ////////////////////////////////////////////////////////////////////////////////

            while (on) {
                System.out.println("\n");
                System.out.println("MENU:");
                System.out.println("1--> Jugadores de un equipo solicitado");
                System.out.println("2--> Jugadores de dos equipos solicitados utilizando una consulta SODA");
                System.out.println("3--> Los jugadores de un equipo que tenga una Fuerza menor o igual que 5");
                System.out.println("4--> Jugadores pertenecientes a una Liga");
                System.out.println("5--> Características de un jugador dado");
                System.out.println("6--> Jugadores que pertenece a un entrenador dado");
                System.out.println("7--> Equipos de una liga en concreto");
                System.out.println("0--> Salir del programa");
                System.out.println(" ");
                menu = input.nextLine();

                switch (menu) {
                    case "0": {
                        System.out.println("\n...salir");
                        on = false;
                        break;
                    }

                    //BUSQUEDA JUGADORES DE UN EQUIPO
                    case "1": {
                        Scanner scn = new Scanner(System.in);
                        System.out.println("Elige equipo:\n");
                        busquedaEquipo(scn.nextLine());

                        //scn.close();
                        break;
                    }

                    //BUSQUEDA DE JUGADORES DE DOS EQUIPOS MEDIANTE SODA
                    case "2": {
                        Scanner scn = new Scanner(System.in);
                        System.out.println("Elige el primer equipo:\n");
                        String equipo1 = scn.nextLine();
                        System.out.println("Elige el segundo equipo:\n");
                        String equipo2 = scn.nextLine();

                        busquedaEquipoSODA(equipo1);
                        busquedaEquipoSODA(equipo2);

                        break;
                    }

                    case "3": {
                        Scanner scn = new Scanner(System.in);
                        System.out.println("Elige equipo:\n");
                        busquedaJugadorSegunFuerza(scn.nextLine());
                        break;
                    }

                    case "4": {
                        Scanner scn = new Scanner(System.in);
                        System.out.println("Elige liga:\n");
                        busquedaJugadorSegunLiga(scn.nextLine());
                        break;
                    }

                    case "5": {
                        Scanner scn = new Scanner(System.in);
                        System.out.println("Elija nombre de Jugador:\n");
                        String nombre = scn.nextLine();
                        System.out.println("Escribe su apellido:\n");
                        String apellido = scn.nextLine();

                        busquedaCaracteristicasSegunJugador(nombre, apellido);
                        break;
                    }

                    case "6": {
                        Scanner scn = new Scanner(System.in);
                        System.out.println("Elige equipo:\n");
                        busquedaJugadorSegunEntrenador(scn.nextLine());

                        break;
                    }

                    case "7": {

                        Scanner scn = new Scanner(System.in);
                        System.out.println("Elige liga:\n");
                        busquedaEquiposSegunLiga(scn.nextLine());

                        break;
                    }

                    default: {
                        System.out.println("\n...entrada de menu incorrecta\n");

                        break;
                    }

                }//switch

            }
        } finally {
            db.close();
            input.close();
        }

    }//main

    /**
     * Muestra por pantalla los equipos con el nombre dado
     * @param nombre nombre del equipo
     */
    private static void busquedaEquipo(String nombre){

        ObjectSet<Equipo> result = db.queryByExample(new Equipo(nombre, null, null));
        System.out.println("Resultados: " + result.size());
        if(result == null)
            System.out.println("...el equipo no existe");
        else{
            while(result.hasNext()){
                System.out.println(result.next().toString());
            }
        }
    }

    /**
     * Muestra por pantalla el equipo dado
     * @param equipo nombre del equipo
     */
    private static void busquedaEquipoSODA(String equipo){

        Query query = db.query();
        query.constrain(Equipo.class);
        query.descend("nombre").constrain(equipo);

        ObjectSet result = query.execute();
        System.out.println("Resultados: " + result.size());
        if(result == null)
            System.out.println("...el equipo no existe");
        else{
            while(result.hasNext()){
                System.out.println(result.next().toString());
            }
        }
    }

    /**
     * Muestra por pantalla informacion sobre los jugadores de un equipo dado lo cuales
     * tienen fuerza por debajo de 5
     * @param equipo nombre del equipo de los jugadores
     */
    private static void busquedaJugadorSegunFuerza(String equipo) {
        ObjectSet<Equipo> result = db.queryByExample(new Equipo(equipo, null, null));

        List<Jugador> jugadores = result.get(0).getJugadores();
        int resultados = 0;
        for (int i = 0; i < jugadores.size(); i++) {

            if (jugadores.get(i).getCaracteristicas().getFuerza() <= 5) {
                System.out.print(jugadores.get(i).getNombre() + " fuerza: " +
                        jugadores.get(i).getCaracteristicas().getFuerza() + "\n");
                resultados++;
            }
        }
        System.out.println("\nResultados: "+resultados);
    }

    /**
     * Muestra por pantalla todos los jugadores de una liga
     * @param liga nombre de la liga a buscar
     */
    private static void busquedaJugadorSegunLiga(String liga) {
        ObjectSet<Liga> result = db.queryByExample(new Liga(liga, 0, null));

        List<Equipo> equipos = result.get(0).getEquipos();
        int resultados = 0;

        System.out.println(equipos.size());

        for (int i = 0; i < equipos.size(); i++) {
            System.out.println(equipos.get(i).toString());
            resultados += equipos.get(i).getJugadores().size();
        }
        System.out.println("\nResultados: "+resultados);
    }

    /**
     * Muestra por pantalla las caracteristicas de un jugador
     * @param nombre nombre del jugador a buscar
     * @param apellido apellido del nombre a buscar
     */
    private static void busquedaCaracteristicasSegunJugador(String nombre, String apellido) {

        ObjectSet<Jugador> result = db.queryByExample(new Jugador(null, nombre, apellido, 0));

        if(result == null)
            System.out.println("...el jugador no existe");
        else{
            while(result.hasNext()){
                System.out.println(result.next().toStringCompleto());
            }
        }
    }

    /**
     * Muestra por pantalla los jugadores de un equipo segun un entrenador dado
     * @param nombre nombre del entrenador a buscar
     */
    private static void busquedaJugadorSegunEntrenador(String nombre){

        ObjectSet<Entrenador> result = db.queryByExample(new Entrenador(nombre, 1));

        Equipo equipo = result.get(0).getEquipo();
        int resultados = 0;

        for (int i = 0; i < equipo.getJugadores().size(); i++) {
            System.out.println(equipo.getJugadores().get(i).toStringSimple());
            resultados ++;
        }
        System.out.println("\nResultados: "+resultados);
    }

    /**
     * Muestra por consola los equipos de una liga
     * @param nombre nombre de la liga a buscar
     */
    private static void busquedaEquiposSegunLiga (String nombre){

        ObjectSet<Liga> result = db.queryByExample(new Liga(nombre, 1, null));
        System.out.println("Resultados: " + result.size());
        if(result == null)
            System.out.println("...la liga no existe");
        else{
            while(result.hasNext()){
                System.out.println(result.next().toString());
            }
        }
    }

    /**
     * Genera jugadores al azar para la BBDD
     * @param cantidad numero de jugadores que se solicitan generar
     * @return Listado de jugadores creados
     */
    private static ArrayList<Jugador> genJugadoresAzar(int cantidad){
        Random rnd = new Random();
        ArrayList<Jugador> js = new ArrayList<>();

        for (int i = 0; i < cantidad ; i++) {
            int id = rnd.nextInt(10000000);
            String x = String.valueOf(i);
            Jugador p = new Jugador (
                    x+x+id                  //DNI aleatorio segun i
                    ,i+"NomJ"+id
                    ,i+"ApeJ"+id
                    ,(1+(i/10))
            );

            p.setCaracteristicasAzar();
            System.out.println("Creado jugador: "+p.getNombre());
            js.add(p);
        }
        return js;
    }

    /**
     * Generador de entrenadores al azar para guardar en la BBDD
     * @return entrenador creado
     */
    private static Entrenador genEntrenadorAzar(){
        Random rnd = new Random();

        Entrenador entrenador =  new Entrenador(
                "NomE"+rnd.nextInt(5000)
                ,rnd.nextInt(50)                //Experiencia al azar
        );
        System.out.println("Creado Entrenador: "+entrenador.getNombre()+"("+entrenador.getAnyosExperiencia()+")");
        return entrenador;
    }

    /**
     * Generador de equipos al azar para guardar en la bbdd
     * @param cantidad numero de equipos a generar
     * @return Listado de equipos generados
     */
    private static ArrayList<Equipo> genEquiposAzar(int cantidad){
        ArrayList<Equipo> equipo = new ArrayList<>();

        for (int i = 0; i < cantidad ; i++) {

            Equipo t = new Equipo(
                    "Equipo"+i
                    ,"Estadio del "+i
                    ,genEntrenadorAzar()
            );
            t.setJugadores(genJugadoresAzar(10));

            if (i%2 == 0) t.setLiga(liga1);
            else t.setLiga(liga2);

            equipo.add(t);
            System.out.println("Creado Equipo:"+t.getNombre());
        }
        return equipo;
    }

    /**
     * Guarda un listado de equipos dado en la BBDD
     * @param equipos listado de equipos a guardar
     */
    private static void guardarEquipos (ArrayList<Equipo> equipos){
        System.out.println("Guardando Equipos.");
        for (int i = 0; i < equipos.size(); i++) {
            db.store(equipos.get(i));
            System.out.print("..");
        }
        System.out.print(".Guardados "+equipos.size()+" Equipos");
    }
}
