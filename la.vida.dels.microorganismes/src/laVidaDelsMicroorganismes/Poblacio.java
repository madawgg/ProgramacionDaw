package laVidaDelsMicroorganismes;

// interface població

import java.util.ArrayList;

public interface Poblacio {

    final int PES_REPRODUCCIO = 3;
    final int NUM_MAX = 20;

    public void reduirPoblacio();

    public void reproduir(ArrayList<Esser> essers);
}
