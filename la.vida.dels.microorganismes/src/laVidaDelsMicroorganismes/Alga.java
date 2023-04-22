package laVidaDelsMicroorganismes;

import java.util.ArrayList;
import static laVidaDelsMicroorganismes.Esser.generaAleatori;

public class Alga extends Esser {

    //atribut de clase
    private static int totalAlgues;

    //atributs d'instancia
    private Aliment aliment;
    private boolean reproduccio;

    public Alga() {
        super("ALGA", Alimentacio.PES_ALGA);
        this.aliment = Aliment.NUTRIENTS;
        this.reproduccio = true;
        totalAlgues++;
    }
    
    public static int dirPoblacio() {
        return totalAlgues;
    }

    @Override
    public String mostrarEstat() {
        return "#" + dirNom() + " => PES: " + dirPes();
    }

    @Override
    public String mostrarDetall() {
        return "#" + dirNom() + " => PES " + dirPes() + " ALIMENTACIO: " + aliment + "-REPRODUCCIO: " + repro();
    }
    //metode per a cambiar de manera rapida el true o false de boolean reproduir per un SI o un NO per a traurelo per missatge
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

        int nouPes = dirPes() + Alimentacio.PES_NUTRIENT;
        canviaPes(nouPes);

        System.out.println("ALIMENTACIO ***** " + dirNom() + ": m'he menjat a " + Aliment.NUTRIENTS + ". Ara pese " + nouPes);
        
        // condicio per a cridar al metode reproduir si te les condicions oportunes despres de menjar i si no les te que llance una exception avisant de que no es pot reproduir
        if (dirPes() > (Alimentacio.PES_ALGA * 2) * Poblacio.PES_REPRODUCCIO && totalAlgues < Poblacio.NUM_MAX) {
            reproduir(essers);
        }else{
            reproduir(essers);// Aquest else es per a no deixar obert el if i a banda que cride al metode reproduir per a que llance la exception.
        }
    }

    @Override
    //aquest metode controla la reproduccio de les algues, es a dir, es crida (quan es posible) des del metode menajar() i s'executa mentre el while se complisca. en cada iteracio comproba si es pot reproduir
    //i en cas contrari llança una excepcio dient que no s'ha pogut reproduir
    public void reproduir(ArrayList<Esser> essers) {
        boolean reproduccioEfectuada= false;
        while (dirPes() >= Alimentacio.PES_ALGA  * Poblacio.PES_REPRODUCCIO && totalAlgues < Poblacio.NUM_MAX) {
            Alga alga = new Alga();
            essers.add(alga);
            canviaPes(dirPes() - Alimentacio.PES_ALGA);
            System.out.println("REPRODUCCIO ***** " + dirNom() + " m'he reproduït i he creat a " + alga.dirNom() + ". Ara pese " + dirPes());
            reproduccioEfectuada = true;
        }
      
        
        
        try{
            if(!reproduccioEfectuada || totalAlgues == 20 && dirPes() > Alimentacio.PES_ALGA  * Poblacio.PES_REPRODUCCIO ){
                throw new Exception("REPRODUCCIO ***** " + dirNom() + " amb un pes de: " + dirPes() + " no em puc reproduir. ");
            }
        }
        catch (Exception e){
            System.out.println(e.getMessage()+ " (EX)");
        }
    }

    @Override
    public void reduirPoblacio() {
        eliminarEsser();
        totalAlgues--;
    }
}
