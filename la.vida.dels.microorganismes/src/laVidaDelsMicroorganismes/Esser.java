package laVidaDelsMicroorganismes;

import java.util.Random;

public abstract class Esser implements Poblacio, Alimentacio {

    //atributs de clase
    private static int totalEssers = 0;
    private static int consecutiu = 1;

    //atributs d'instancia
    private final String NOM;
    private int pes;
    
    //constructor
    public Esser(String nom, int pes) {
        this.NOM = nom + consecutiu;
        consecutiu++;
        this.pes = pes;
        totalEssers ++;
    }
    
    //METODES PER A SUMAR Y RESTAR ESSERS AL COMPTADOR TOTAL
    //metode per a sumar esser al contador total
   
    //metode per a restar esser al contador total. He creat aquest metode per a no tindre que repetir codi a les demes clases filles. aixi en el metode reduirPoblacio de les filles
    //nomes tinc que cridar a aquest metode i reduir el contador individual de cada subclase
    public void eliminarEsser() {
        totalEssers--;  
    }
    
    //metodes get i set
   
    public final String dirNom() {
        return NOM;
    }

    public final int dirPes() {
        return pes;
    }

    public final void canviaPes(int pes) {
        this.pes = pes;
        
    }
    //metode per a crear un numero aleatori enter tenint en compte el valor inicial i la quantitat de numeros que es volen generar
    //he utilitzat la llibreria Random per preferencies personals
    protected final static int generaAleatori(int inicial, int quantitat) {
        Random random = new Random();
        int aleatori = (random.nextInt(quantitat) + inicial);
        return aleatori;
    }
   
    
    public static int dirPoblacio(){
        return totalEssers;
    }

    
    //metodes abstractes que sobreescriuran les clases segons convinga
    public abstract String mostrarEstat();
    public abstract String mostrarDetall();
    
    
    
}
