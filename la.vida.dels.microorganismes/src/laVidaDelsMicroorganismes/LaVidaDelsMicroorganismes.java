package laVidaDelsMicroorganismes;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class LaVidaDelsMicroorganismes {

    public static void main(String[] args) {
        int quanAmebes;
        int quanBacteris;
        int quanAlgues;
        int total;

        ArrayList<Esser> essers = new ArrayList();

        System.out.println("Introdueïx el numero d'amebes (entre 1 i 20)");

        do {
            quanAmebes = llegirNumero();
        } while (quanAmebes == -1);  //ja que quan es llig amb error (siga de tipus dato o fora de rango) se retorna un -1 en el metode

        System.out.println("");
        System.out.println("Introdueix el numero de bacteris (entre 1 i 20)");

        do {
            quanBacteris = llegirNumero();
        } while (quanBacteris == -1); //ja que quan es llig amb error (siga de tipus dato o fora de rango) se retorna un -1 en el metode

        System.out.println("");
        System.out.println("Introdueïx el numero d' algues (entre 1 y 20)");

        do {
            quanAlgues = llegirNumero();
        } while (quanAlgues == -1);  //ja que quan es llig amb error (siga de tipus dato o fora de rango) se retorna un -1 en el metode
        crearEssers(essers, quanAmebes, quanBacteris, quanAlgues);

        processaMenu(essers, quanAmebes, quanBacteris, quanAlgues);
    }

    public static void processaMenu(ArrayList<Esser> essers, int quanAmebes, int quanBacteris, int quanAlgues) {
        Scanner teclat = new Scanner(System.in);
        int seleccio;

        do {
            System.out.println("OPCIONS ==>|| 1.-Una interaccio || 2.-Deu interaccions || 3.-Llistat || 4.-Detall || 0.-Eixir||");
            seleccio = teclat.nextInt();
            switch (seleccio) {
                case 1:
                    //faig aquesta estructura per a que en el cas de que nomes quede un esser i siga Ameba a la llista es puga elegir entre tancar el programa o obligar a reproduirla
                    //d'aquesta manera, si nomes queda 1 bitxo a la llista, la opcio de 10 interaccions queda anulada i nomes es pot optar per les altres opcions del menu
                    if (essers.size() <=1 && essers.get(0) instanceof Ameba){
                       essers.get(0).reproduir(essers);
                    }else{
                    produeixInteraccio(essers);
                    }
                    break;
                case 2:
                    for (int c = 0; c < 10 && essers.size()>1; c++) {
                        // pose el contador per a aclarir que si eixen mes de 10 linies de consola es per el metode reproduir de les algues.
                        //esperant, d'aquesta manera, facilitar la correccio de l'exercici.
                       
                        System.out.println("Interaccio nº: " + (c + 1));
                        produeixInteraccio(essers);
                    } //Aquest if controla quan queda 1 bitxo a la llista(quan quedaba 1 ameba me donava problemes de bucle infinit) i obliga a que l'usuari nomes puga fer 1 interaccio si vol reproduirlo
                    if(essers.size()<=1){
                        System.out.println("");
                        System.out.println("Nomes queda 1 esser a la llista.");
                        System.out.println("");
                         mostraLlistaEssers(essers);
                        System.out.println("SI VOLS REPRODUIR-LO, POLSA -1- PER A FER UNA NOVA INTERACCIO");
                        System.out.println("SI VOLS TANCAR LA APLICACIO, POLSA -0-");
                       
                        
                    }
                    System.out.println("");
                    break;
                case 3:
                    mostraLlistaEssers(essers);
                    break;
                case 4:
                    mostraEsser(essers);
                    break;
                case 0:
                    mostraLlistaEssers(essers);
                    
                    System.out.println("Finalitzant ecosistema ...");
                    
                    break;
                default:
                    //misssatge per a comunicar a l'usuari que no s'esta fent una eleccio valida continguda al menu
                    System.out.println("El valor introduït no correspon a cap opcio del menu");
                    System.out.println("Introdueix un valor correcte");
            }
        } while (seleccio != 0);

    }

    //Metode per a controlar amb exception la correcta introduccio de numeros per part de l'usuari al crear cada tipus de bitxo
    public static int llegirNumero() {
        Scanner individus = new Scanner(System.in);
        int num;

        try {
            num = individus.nextInt();
            if (num < 1 || num > 20) {
                throw new Exception("El numero introduït esta fora del rango entre 1 y 20.");
            }
        } catch (InputMismatchException e) {
            System.out.println("ERROR: No s’ha introduït un valor numèric.  (EX)");
            return -1; //retornat -1 per a controlar la correcta introduccio de dades per part de l'usuari a l'hora de crear-los
        } catch (Exception e) {
            System.out.println("ERROR: " + e.getMessage() + " (EX)");
            return -1;
        }

        return num;
    }

    public static void mostraLlistaEssers(ArrayList<Esser> essers) {
        for (int i = 0; i < essers.size(); i++) {

            System.out.println(essers.get(i).mostrarEstat());
        }

        System.out.println("POBLACIO: TOTAL ESSERS ==> " + Esser.dirPoblacio() + ", AMEBES=> " + Ameba.dirPoblacio() + ", BACTERIES=> " + Bacteri.dirPoblacio() + ", ALGUES=> " + Alga.dirPoblacio());
        System.out.println("");
    }

    public static void mostraEsser(ArrayList<Esser> essers) {
        Scanner teclat = new Scanner(System.in);
        System.out.println("Introdueïx el nom del Esser que vols buscar");
        String busqueda = teclat.nextLine();
        //he possat aquest boolean per a facilitar el missatge d'eixida en cas que no estiga a la llista el bitxo buscat
        boolean trobat = false;
        //bucle for per a fer la busqueda passant el text que ha introduit l'usuari a majuscules per a igualar el nom amb els que apareixen a la llista
        for (int i = 0; i < essers.size(); i++) {
            Esser esser = essers.get(i);
            if (esser.dirNom().equals(busqueda.toUpperCase())) {
                System.out.println(esser.mostrarDetall());
                System.out.println("");
                trobat = true;
            }
        }
        if (!trobat) {
            System.out.println("ERROR: el microorganisme " + busqueda + " no apareix en la llista.");
            System.out.println("");
        }
    }

    public static void produeixInteraccio(ArrayList<Esser> essers) {
         
        //Pase a la variable esserTriat el bitxo que s'ha tret de manera aleatoria am el metode get que te de index el metode genera aleatori
        Esser esserTriat = essers.get(Esser.generaAleatori(0, essers.size()));
        // si l'esser triat es una ambea o un bacteri, automaticament traura un altre numero aleatori per a cridar al metode reproduir o menjar segons corresponga
        if (esserTriat instanceof Ameba || esserTriat instanceof Bacteri) {
            int ale = Esser.generaAleatori(1, 2);
            if (ale == 1) {
                esserTriat.reproduir(essers);
            } else {
                esserTriat.menjar(essers);
            }
            // si el bitxo es un alga, ve a aquesta part del condicional i crida al metode menjar
        } else if (esserTriat instanceof Alga) {
            esserTriat.menjar(essers);
        }
        
        

    }
    //Aquest metode esta creat de manera que, segons el numero de cada tipus de bitxo que introduixca l'usuari per consola, introduixca de forma aleatoria la cuantitat de cada un dells

    public static void crearEssers(ArrayList<Esser> essers, int quanAmebes, int quanBacteris, int quanAlgues) {
        int tipo, contAmebes = 0, contBacteris = 0, contAlgues = 0;//contadors per a controlar la creacio d'essers aleatoris segons els parametres d'entrada del metode.
        
    //mentres es complisca una d'aquestes condicions el bucle while continuara creant e introduint el bitxo corresponent generat aleatoriament i que estiga dins de les especificacions de poblabio donades al pdf
        while (contAmebes < quanAmebes || contBacteris < quanBacteris || contAlgues < quanAlgues) {

            tipo = Esser.generaAleatori(1, 3);
            if (tipo == 1 && contAmebes < quanAmebes) {
                Ameba ameba = new Ameba();
                essers.add(ameba);
                contAmebes++;
            }
            if (tipo == 2 && contBacteris < quanBacteris) {
                Bacteri bacteri = new Bacteri();
                essers.add(bacteri);
                contBacteris++;
            }
            if (tipo == 3 && contAlgues < quanAlgues) {
                Alga alga = new Alga();
                essers.add(alga);
                contAlgues++;
            }
        }
    }
}
