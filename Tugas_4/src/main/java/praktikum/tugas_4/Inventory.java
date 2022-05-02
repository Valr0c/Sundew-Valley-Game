package praktikum.tugas_4;
public class Inventory extends Player{
    private int grass = 0, wheat = 0, corn = 0, potato = 0, stok, h_beli;

    public Inventory() {
    }
    
    public Inventory(int posx, int posy) {
        super(posx, posy);
    }

    public int getGrass() {
        return grass;
    }

    public void setGrass(int grass) {
        this.grass = grass;
    }

    public int getWheat() {
        return wheat;
    }

    public void setWheat(int wheat) {
        this.wheat = wheat;
    }

    public int getCorn() {
        return corn;
    }

    public void setCorn(int corn) {
        this.corn = corn;
    }

    public int getPotato() {
        return potato;
    }

    public void setPotato(int potato) {
        this.potato = potato;
    }

    public int getStok() {
        return stok;
    }

    public void setStok(int stok) {
        this.stok = stok;
    }

    public int getH_beli() {
        return h_beli;
    }

    public void setH_beli(int h_beli) {
        this.h_beli = h_beli;
    }

}
