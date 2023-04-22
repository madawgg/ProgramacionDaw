package laVidaDelsMicroorganismes;

import java.util.ArrayList;

public class Bacteri extends Esser {

    //atributs de classe
    private static int totalBacteris;

    //atributs d'instancia
    private Aliment aliment;
    private boolean reproduccio;

    public Bacteri() {
        super("BACTERI", Alimentacio.PES_BACTERI);
        aliment = Aliment.ALGA;
        reproduccio = true;
        totalBacteris++;
    }

    public static int dirPoblacio() {
        return totalBacteris;
    }

    //metode de asignament d'un string a un boolean
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
    public String mostrarEstat() {
        return "/" + dirNom() + " => PES: " + dirPes();
    }

    @Override
    public String mostrarDetall() {
        return "/" + dirNom() + "=> PES " + dirPes() + " -ALIMENTACIO: " + aliment + " -REPRODUCCIO: " + repro();
    }

    @Override
    public void menjar(ArrayList<Esser> essers) {
        boolean algaTrobada = false;
        int i;
        int nouPes;
        Esser esser;

        for (int j = 0; j < essers.size() && !algaTrobada; j++) {
            esser = essers.get(j);
            algaTrobada = esser instanceof Alga;

        }
        if (algaTrobada) {
            algaTrobada = false;
            do {
                i = generaAleatori(0, essers.size());
                esser = essers.get(i);
                if (esser instanceof Alga) {
                    algaTrobada = true;
                }
            } while (algaTrobada == false);

            nouPes = esser.dirPes() + this.dirPes();
            this.canviaPes(nouPes);

            System.out.println("ALIMENTACIO ***** " + dirNom() + " M'he menjat a: " + esser.dirNom() + ". Ara pese: " + nouPes);

            Alga alga1 = (Alga) esser;
            alga1.reduirPoblacio();
            essers.remove(esser);

        } else {
            System.out.println("***** ALIMENTACIO BACTERI ALERT ***** NO QUEDEN ALGUES A L'ECOSISTEMA(LLISTA).");
        }

    }

    @Override
    public void reproduir(ArrayList<Esser> essers) {
        boolean reproduccioEfectuada = false;
        if (dirPes() >= (Alimentacio.PES_BACTERI * 2) * Poblacio.PES_REPRODUCCIO && totalBacteris < Poblacio.NUM_MAX) {  //pose parentesis per a claretat visual, no faria falta posarlo, l'operacio donaria el mateix resultat
            int nouPes = dirPes() / 2;
            canviaPes(nouPes);
            Bacteri bacteri = new Bacteri();
            bacteri.canviaPes(nouPes);
            essers.add(bacteri);
            System.out.println("REPRODUCCIO ***** " + dirNom() + " m'he reproduït i he creat a " + bacteri.dirNom() + ". Ara pese: " + nouPes);
            reproduccioEfectuada = true;
        }else{
            try {
                if (!reproduccioEfectuada) {

                    throw new Exception("REPRODUCCIO ***** " + dirNom() + " amb un pes de " + dirPes() + " no em puc reproduir. ");
                }
            } catch (Exception e) {
                System.out.println(e.getMessage() + " (Ex)" );

            }
        }
    }

    @Override
    public void reduirPoblacio() {
        eliminarEsser();
        totalBacteris--;
    }
}
