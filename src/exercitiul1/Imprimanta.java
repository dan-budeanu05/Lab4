package exercitiul1;

/**
 * Clasa pentru imprimante.
 */

public class Imprimanta extends Echipament {
    private int ppm;
    private String dpi;
    private int pCar;
    private ModTiparire modTiparire;

    public Imprimanta(String denumire, int nrInv, double pret, String zonaMag, StareEchipament stare, int ppm, String dpi, int pCar, ModTiparire modTiparire) {
        super(denumire, nrInv, pret, zonaMag, stare);
        this.ppm = ppm;
        this.dpi = dpi;
        this.modTiparire = modTiparire;
        this.pCar = pCar;
    }
    public void setModTiparire(ModTiparire modTiparire){
        this.modTiparire=modTiparire;
    }

    @Override
    public String toString() {
        return "Imprimanta{" + super.toString() +
                "PPM=" + ppm +
                ", DPI=" + dpi +
                ", pagini/cartus=" + pCar +
                ", mod tiparire=" + modTiparire +
                '}';
    }
}
