package laVidaDelsMicroorganismes;

//interface Alimentació

import java.util.ArrayList;

public interface Alimentacio {
   final int PES_AMEBA = 20;
   final int PES_BACTERI = 10;
   final int PES_ALGA = 3;
   final int PES_NUTRIENT = 5;
   
   
    public abstract void menjar(ArrayList <Esser> essers);
     
}
