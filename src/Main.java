import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;
import DDBB.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.Random;
import java.util.Scanner;

/**
 * Created by 47767573t on 25/02/16.
 */
public class Main {

    static ObjectContainer db = null;

    public static void main(String[] args) throws Exception {

        Scanner input = new Scanner(System.in);


        try {
            //db = Db4o.openFile("persons.data"); //deprecated
            //db = Db4oEmbedded.OpenFile(Db4oEmbedded.NewConfiguration(), YapFileName);

            /*
            Person brian = new Person("Brian", "Goetz", 39);
            db.store(brian);
            db.commit();
            // Find all the Brians
            ObjectSet objBrian = db.queryByExample(new Person("Brian", null, 0));
            while (objBrian.hasNext())
                System.out.println(objBrian.next());
             */

            //db = Db4oEmbedded.openFile("soccerData.db");

            db = DataConnection.getInstance();
            String menu;
            boolean on = true;//condicio de sortida del programa

            //DEFINIMOS TODA LA LIGA CON VARIABLES AL AZAR PARA TESTEO
            ArrayList <Equipo> newTeam = genEquiposAzar(5);
            guardarEquipos(newTeam);

            Liga liga = new Liga("1A-Division", 1, "BBVA");



            //public Liga(String nombre, int categoria, String patrocinador)
            Liga liga1 = new Liga("1a Division", 1, "Telecinco");
            Liga liga2 = new Liga("2a Division", 2, "tve2");

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
                System.out.println("7--> Equipos de una liga en concreta");
                System.out.println("0--> Salir del programa");
                System.out.println(" ");
                menu = input.nextLine();

                switch (menu) {
                    case "0": {
                        System.out.println("\n...salir");
                        on = false;
                        break;
                    }

                    case "1": {
                        Scanner scn = new Scanner(System.in);
                        System.out.println("Elige equipo:\n");
                        busquedaPorEquipo(scn.nextLine());

                        //scn.close();
                        break;
                    }

                    case "2": {


                        break;
                    }

                    case "3": {

                        break;
                    }

                    case "4": {

                        break;
                    }

                    case "5": {

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

    private static void jugadoresDeUnEquipoSolicitado() {


    }

    private static void busquedaPorEquipo(String nombre){

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


    private static Entrenador genEntrenadorAzar(){
        Random rnd = new Random();

        Entrenador entrenador =  new Entrenador(
                "NomE"+rnd.nextInt(5000)
                ,rnd.nextInt(50)                //Experiencia al azar
        );
        System.out.println("Creado Entrenador: "+entrenador.getNombre()+"("+entrenador.getAnyosExperiencia()+")");
        return entrenador;
    }

    private static ArrayList<Equipo> genEquiposAzar(int cantidad){
        ArrayList<Equipo> equipo = new ArrayList<>();

        for (int i = 0; i < cantidad ; i++) {

            Equipo t = new Equipo(
                    "Equipo"+i
                    ,"Estadio del"+i
                    ,genEntrenadorAzar()
            );
            t.setJugadores(genJugadoresAzar(10));
            equipo.add(t);
            System.out.println("Creado Equipo:"+t.getNombre());
        }
        return equipo;
    }

    private static void guardarEquipos (ArrayList<Equipo> equipos){
        System.out.println("Guardando Equipos.");
        for (int i = 0; i < equipos.size(); i++) {
            db.store(equipos.get(i));
            System.out.print("..");
        }
        System.out.print(".Guardados "+equipos.size()+" Equipos");


    }


}
