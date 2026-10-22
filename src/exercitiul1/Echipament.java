package exercitiul1;

import java.io.Serializable;

/**
 * Clasa de baza pentru echipamente electronice.
 */

public class Echipament implements Serializable {
    private static final long serialVersionUID=1L;
    private String denumire;
    private int nrInv;
    private double pret;
    private String zonaMag;
    private StareEchipament stare;

    public Echipament(String denumire, int nrInv, double pret, String zonaMag, StareEchipament stare) {
        this.denumire = denumire;
        this.nrInv = nrInv;
        this.pret = pret;
        this.zonaMag = zonaMag;
        this.stare = stare;
    }
    public int getNrInv(){
        return nrInv;
    }
    public StareEchipament getStare() {
        return stare;
    }
    public void setStare(StareEchipament stare){
        this.stare=stare;
    }
    @Override
    public String toString() {
        return "Echipament{" +
                "nr. inventar=" + nrInv +
                ", pret=" + pret +
                ", zona magazie='" + zonaMag + '\'' +
                ", stare=" + stare +
                ", denumire='" + denumire + '\'' +
                '}';
    }
}
