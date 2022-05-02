package praktikum.tugas_4;
public class Player {
    private int posx, posy, gold, energi;
    private String simbol;

    public Player() {
    }
    
    public Player(int posx, int posy) {
        this.posx = posx;
        this.posy = posy;
        this.gold = 100;
        this.energi = 100;
        this.simbol = "P";
    }

    public int getPosx() {
        return posx;
    }

    public void setPosx(int posx) {
        this.posx = posx;
    }

    public int getPosy() {
        return posy;
    }

    public void setPosy(int posy) {
        this.posy = posy;
    }

    public int getGold() {
        return gold;
    }

    public void setGold(int gold) {
        this.gold = gold;
    }

    public int getEnergi() {
        return energi;
    }

    public void setEnergi(int energi) {
        this.energi = energi;
    }

    public String getSimbol() {
        return simbol;
    }

    public void setSimbol(String simbol) {
        this.simbol = simbol;
    }

}
