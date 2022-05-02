package praktikum.tugas_4;
public class Rumput extends Tanaman{

    public Rumput() {
        super.setSimbol("v");
        super.setH_jual(10);
    }

    
    public Rumput(int posx, int posy) {
        super(posx, posy);
        super.setProtected(false);
    }
    
}
