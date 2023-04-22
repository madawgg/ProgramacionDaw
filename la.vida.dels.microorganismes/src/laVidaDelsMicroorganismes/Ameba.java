package laVidaDelsMicroorganismes;

import java.util.ArrayList;
import java.util.Random;

public final class Ameba extends Esser implements Alimentacio, Poblacio {

    //atributs de clase
    private static int totalAmebes = 0;

    //atribut d'instancia
    private Aliment aliment;
    private boolean reproduccio;

    public Ameba() {
        super("AMEBA", Alimentacio.PES_AMEBA);
        this.aliment = Aliment.TOT;
        this.reproduccio = true;
        totalAmebes++;
    }

    //metode per a dir el total de amebes existent
    public static int dirPoblacio() {
        return totalAmebes;
    }

    //metode per a dir l'estat d'amebes
    @Override
    public String mostrarEstat() {
        return "@" + dirNom() + "=> PES: " + dirPes();
    }

    //metode per a dir els detalls de les amebes
    @Override
    public String mostrarDetall() {
        return "@" + dirNom() + "=> PES " + dirPes() + "  -ALIMENTACIÓ: " + aliment + "  -REPRODUCCIÓ: " + repro();
    }

    //metode per a asignar si o no a true o false de reproduccio
    public String repro() {
        String reprod;
        if (this.reproduccio == true) {
            reprod = "SI";
        } else {
            reprod = "NO";
        }
        return reprod;
    }

    @Override
    public void menjar(ArrayList<Esser> essers) {
        boolean menjatFet= false;
        Esser esser;
       
        do {
            int i = generaAleatori(0, essers.size());
            esser = essers.get(i);
        } while (esser.equals(this));

        int pesMenjat;

        pesMenjat = esser.dirPes() + this.dirPes();
        this.canviaPes(pesMenjat);
        System.out.println("ALIMENTACIO ***** " + dirNom() + " M'he menjat a: " + esser.dirNom() + ". Ara pese: " + pesMenjat);
        menjatFet = true;
        
        
        //crear un objecte  per a accedir al metode reduir poblacio de cada clase filla
        if (esser instanceof Ameba) {
            reduirPoblacio();
        } else if (esser instanceof Bacteri) {
            Bacteri bacteri = (Bacteri) esser;
            bacteri.reduirPoblacio();
        } else if (esser instanceof Alga) {
            Alga alga = (Alga) esser;
            alga.reduirPoblacio();
        }
        
        essers.remove(esser);
       if(menjatFet == false){
           System.out.println("Nomes queda 1 ameba a la llista"); 
       
    } 
   
    }

    @Override
    public void reproduir(ArrayList<Esser> essers) {
        boolean reproduccioEfectuada = false;
        if (dirPes() >= Poblacio.PES_REPRODUCCIO * Alimentacio.PES_AMEBA && totalAmebes < Poblacio.NUM_MAX) { //es compleix quan es pot reproduir

            Ameba ameba = new Ameba();

            essers.add(ameba);

            int pesActual = dirPes() - Alimentacio.PES_AMEBA;
            this.canviaPes(pesActual);

            System.out.println("REPRODUCCIO ***** " + dirNom() + " m'he reproduït i he creat a " + ameba.dirNom() + ". Ara pese " + dirPes());
            reproduccioEfectuada = true;
        } else {
            try {

                if (!reproduccioEfectuada) {
                    throw new Exception("REPRODUCCIO ***** " + dirNom() + " amb un pes de " + dirPes() + " no em puc reproduir. ");
                }

            } catch (Exception e) {
                System.out.println(e.getMessage() + " (EX)" );
            }
        }
    }

    @Override
    public void reduirPoblacio() {
        eliminarEsser();
        totalAmebes--;
    }

}
