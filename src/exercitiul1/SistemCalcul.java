package exercitiul1;

/**
 * Clasa pentru sistemele de calcul.
 */

public class SistemCalcul extends Echipament {
    private String tipMon;
    private double vitProc;
    private double cHdd;
    private SistemOperare sistemOperare;

    public SistemCalcul(String denumire, int nrInv, double pret, String zonaMag, StareEchipament stare, String tipMon, double vitProc, double cHdd, SistemOperare sistemOperare) {
        super(denumire, nrInv, pret, zonaMag, stare);
        this.tipMon = tipMon;
        this.vitProc = vitProc;
        this.cHdd = cHdd;
        this.sistemOperare = sistemOperare;
    }
    public void instaleazaSistemOperare(SistemOperare sistemOperare){
        this.sistemOperare=sistemOperare;
    }

    @Override
    public String toString() {
        String so;
        if(sistemOperare==null){
            so="Neinstalat";
        } else{
            so=sistemOperare.toString();
        }
        return "SistemCalcul{" + super.toString() +
                "tipMon='" + tipMon + '\'' +
                ", vitProc=" + vitProc +
                ", cHdd=" + cHdd +
                ", sistemOperare=" + sistemOperare +
                '}';
    }
}
