package praktikum.tugas_4;
public class Corn extends Tanaman{

    public Corn() {
        super.setH_beli(25);
        super.setH_jual(60);
        super.setSiram(7);
        super.setStok(0);
        super.setSimbol("🌽");
    }

    
    public Corn(int posx, int posy) {
        super(posx, posy);
        super.setWaktu(5);
        super.setPercepat(0);
        super.setWatered(false);
        super.setProtected(false);
    }
    
}
