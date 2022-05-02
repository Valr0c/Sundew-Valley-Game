package praktikum.tugas_4;
public class Wheat extends Tanaman{

    public Wheat() {
        super.setH_beli(15);
        super.setH_jual(30);
        super.setSiram(4);
        super.setStok(0);
        super.setSimbol("🌾");
    }
    
    public Wheat(int posx, int posy) {
        super(posx, posy);
        super.setWaktu(3);
        super.setPercepat(0);
        super.setWatered(false);
        super.setProtected(false);
    }
    
}
