package praktikum.tugas_4;
public class Scarecrow extends Inventory{
    
    public Scarecrow() {
        super.setH_beli(100);
        super.setStok(0);
        super.setSimbol("S");
    }

    public Scarecrow(int posx, int posy) {
        super(posx, posy);
    }
    
}
