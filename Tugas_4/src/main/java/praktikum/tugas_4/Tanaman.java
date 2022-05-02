package praktikum.tugas_4;
public class Tanaman extends Inventory{
    private int h_jual, waktu, siram, percepat;
    private boolean Watered, Protected;

    public Tanaman() {
    }

    public Tanaman(int posx, int posy) {
        super(posx, posy);
    }
    
    public int getH_jual() {
        return h_jual;
    }

    public void setH_jual(int h_jual) {
        this.h_jual = h_jual;
    }

    public int getWaktu() {
        return waktu;
    }

    public void setWaktu(int waktu) {
        this.waktu = waktu;
    }

    public int getSiram() {
        return siram;
    }

    public void setSiram(int siram) {
        this.siram = siram;
    }

    public int getPercepat() {
        return percepat;
    }

    public void setPercepat(int percepat) {
        this.percepat = percepat;
    }

    public boolean isWatered() {
        return Watered;
    }

    public void setWatered(boolean Watered) {
        this.Watered = Watered;
    }

    public boolean isProtected() {
        return Protected;
    }

    public void setProtected(boolean Protected) {
        this.Protected = Protected;
    }
    
}
