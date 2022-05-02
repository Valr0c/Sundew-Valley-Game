package praktikum.tugas_4;
public class Potato extends Tanaman{

    public Potato() {
        super.setH_beli(40);
        super.setH_jual(100);
        super.setSiram(10);
        super.setStok(0);
        super.setSimbol("🥔");
    }

    
    public Potato(int posx, int posy) {
        super(posx, posy);
        super.setWaktu(7);
        super.setPercepat(0);
        super.setWatered(false);
        super.setProtected(false);
    }
    
}
