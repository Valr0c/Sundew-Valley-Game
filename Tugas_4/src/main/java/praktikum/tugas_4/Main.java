package praktikum.tugas_4;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Random rd = new Random();
        int inp;
        do {
            System.out.println("=====Sundew Valley=====");
            System.out.println("1. Start");
            System.out.println("0. Exit");
            System.out.println(">> ");
            inp = Integer.parseInt(sc.nextLine());
            
            int day = 1, x, y;
            boolean crow = false;
            x = rd.nextInt(25)+1;
            y = rd.nextInt(10)+1;
            String [][] ladang = new String[12][27];
            boolean [][] isPlanted = new boolean[12][27];
            int [] home = new int[2];
            int [] market = new int[2];
            Inventory p = new Inventory(x,y);
            Rumput r = new Rumput();
            Wheat w = new Wheat();
            Corn c = new Corn();
            Potato k = new Potato();
            Natural n = new Natural();
            Artificial a = new Artificial();
            Scarecrow s = new Scarecrow();
            ArrayList<Rumput> grass = new ArrayList<>();
            ArrayList<Wheat> wheat = new ArrayList<>();
            ArrayList<Corn> corn = new ArrayList<>();
            ArrayList<Potato> potato = new ArrayList<>();
            ArrayList<Scarecrow> scarecrow = new ArrayList<>();
            
            // ladang
            for (int i = 0; i < 12; i++) {
                for (int j = 0; j < 27; j++) {
                    if ((i == 0 || i == 11) && (j == 0 || j == 26)) {
                        ladang[i][j] = "+";
                    }else if ((i == 0 || i == 11) && (j > 0 && j < 26)) {
                        ladang[i][j] = "-";
                    }else if ((j == 0 || j == 26) && (i > 0 && i < 11)) {
                        ladang[i][j] = "|";
                    }else{
                        ladang[i][j] = " ";
                    }
                    isPlanted[i][j] = false;
                }
            }
            
            // pemain
            ladang[y][x] = p.getSimbol();
            
            // rumah
            do {
                home[0] = rd.nextInt(25)+1;
                home[1] = rd.nextInt(10)+1;
                
                if (ladang[home[1]][home[0]].equals(" ")) {
                    break;
                }
            } while (true);
            ladang[home[1]][home[0]] = "H";
            
            // pasar
            do {
                market[0] = rd.nextInt(25)+1;
                market[1] = rd.nextInt(10)+1;
                
                if (ladang[market[1]][market[0]].equals(" ")) {
                    break;
                }
            } while (true);
            ladang[market[1]][market[0]] = "M";
            
            // rumput
            for (int i = 0; i < 10; i++) {
                do {
                    x = rd.nextInt(25)+1;
                    y = rd.nextInt(10)+1;
                    
                    if (ladang[y][x].equals(" ")) {
                        break;
                    }
                } while (true);
                grass.add(new Rumput(x,y));
                ladang[y][x] = r.getSimbol();
            }
            
            if (inp == 1) {
                String inp2;
                do {
                    for (int i = 0; i < 12; i++) {
                        for (int j = 0; j < 27; j++) {
                            if (isPlanted[i][j] && !ladang[i][j].equals("P")) {
                                boolean isw = false, isc = false, isp = false;
                                int idx = -1;
                                for (int l = 0; l < wheat.size(); l++) {
                                    if (wheat.get(l).getPosy() == i && wheat.get(l).getPosx() == j) {
                                        isw = true;
                                        idx = l;
                                    }
                                }
                                for (int l = 0; l < corn.size(); l++) {
                                    if (corn.get(l).getPosy() == i && corn.get(l).getPosx() == j) {
                                        isc = true;
                                        idx = l;
                                    }
                                }
                                for (int l = 0; l < potato.size(); l++) {
                                    if (potato.get(l).getPosy() == i && potato.get(l).getPosx() == j) {
                                        isp = true;
                                        idx = l;
                                    }
                                }
                                if (isw && idx != -1) {
                                    if (wheat.get(idx).getWaktu() <= 0) {
                                        ladang[i][j] = w.getSimbol();
                                    }else if (wheat.get(idx).isWatered()) {
                                        ladang[i][j] = "*";
                                    }else{
                                        ladang[i][j] = ".";
                                    }
                                }else if (isc && idx != -1) {
                                    if (corn.get(idx).getWaktu() <= 0) {
                                        ladang[i][j] = c.getSimbol();
                                    }else if (corn.get(idx).isWatered()) {
                                        ladang[i][j] = "*";
                                    }else{
                                        ladang[i][j] = ".";
                                    }
                                }else if (isp && idx != -1) {
                                    if (potato.get(idx).getWaktu() <= 0) {
                                        ladang[i][j] = k.getSimbol();
                                    }else if (potato.get(idx).isWatered()) {
                                        ladang[i][j] = "*";
                                    }else{
                                        ladang[i][j] = ".";
                                    }
                                }
                            }
                        }
                    }
                    
                    for (int i = 0; i < grass.size(); i++) {
                        if (ladang[grass.get(i).getPosy()][grass.get(i).getPosx()].equals(" ")) {
                            ladang[grass.get(i).getPosy()][grass.get(i).getPosx()] = r.getSimbol();
                        }
                    }
                    
                    for (int i = 0; i < scarecrow.size(); i++) {
                        if (ladang[scarecrow.get(i).getPosy()][scarecrow.get(i).getPosx()].equals(" ")) {
                            ladang[scarecrow.get(i).getPosy()][scarecrow.get(i).getPosx()] = s.getSimbol();
                        }
                    }
                    
                    System.out.println("Day : " + day);
                    for (int i = 0; i < 12; i++) {
                        for (int j = 0; j < 27; j++) {
                            System.out.print(ladang[i][j]);
                        }
                        System.out.println();
                    }
                    System.out.println("Gold : " + p.getGold());
                    System.out.println("Energy : " + p.getEnergi());
                    System.out.println(">> ");
                    inp2 = sc.nextLine();
                    
                    boolean isHome = false, isMarket = false;
                    if (inp2.equalsIgnoreCase("w")) {
                        if (!ladang[p.getPosy()-1][p.getPosx()].equals("H") && !ladang[p.getPosy()-1][p.getPosx()].equals("M") && !ladang[p.getPosy()-1][p.getPosx()].equals("-")) {
                            if (ladang[p.getPosy()-1][p.getPosx()].equals(r.getSimbol())) {
                                if (p.getEnergi() >= 2) {
                                    for (int i = 0; i < grass.size(); i++) {
                                        if (grass.get(i).getPosx() == p.getPosx() && grass.get(i).getPosy() == p.getPosy()-1) {
                                            grass.remove((int)i);
                                            break;
                                        }
                                    }
                                    p.setGrass(p.getGrass()+1);
                                    p.setEnergi(p.getEnergi()-2);
                                }
                            }
                            ladang[p.getPosy()][p.getPosx()] = " ";
                            p.setPosy(p.getPosy()-1);
                            ladang[p.getPosy()][p.getPosx()] = p.getSimbol();
                        }else if (ladang[p.getPosy()-1][p.getPosx()].equals("H")) {
                            isHome = true;
                        }else if (ladang[p.getPosy()-1][p.getPosx()].equals("M")) {
                            isMarket = true;
                        }
                    }else if (inp2.equalsIgnoreCase("a")) {
                        if (!ladang[p.getPosy()][p.getPosx()-1].equals("H") && !ladang[p.getPosy()][p.getPosx()-1].equals("M") && !ladang[p.getPosy()][p.getPosx()-1].equals("|")) {
                            if (ladang[p.getPosy()][p.getPosx()-1].equals(r.getSimbol())) {
                                if (p.getEnergi() >= 2) {
                                    for (int i = 0; i < grass.size(); i++) {
                                        if (grass.get(i).getPosx() == p.getPosx()-1 && grass.get(i).getPosy() == p.getPosy()) {
                                            grass.remove((int)i);
                                            break;
                                        }
                                    }
                                    p.setGrass(p.getGrass()+1);
                                    p.setEnergi(p.getEnergi()-2);
                                }
                            }
                            ladang[p.getPosy()][p.getPosx()] = " ";
                            p.setPosx(p.getPosx()-1);
                            ladang[p.getPosy()][p.getPosx()] = p.getSimbol();
                        }else if (ladang[p.getPosy()][p.getPosx()-1].equals("H")) {
                            isHome = true;
                        }else if (ladang[p.getPosy()][p.getPosx()-1].equals("M")) {
                            isMarket = true;
                        }
                    }else if (inp2.equalsIgnoreCase("s")) {
                        if (!ladang[p.getPosy()+1][p.getPosx()].equals("H") && !ladang[p.getPosy()+1][p.getPosx()].equals("M") && !ladang[p.getPosy()+1][p.getPosx()].equals("-")) {
                            if (ladang[p.getPosy()+1][p.getPosx()].equals(r.getSimbol())) {
                                if (p.getEnergi() >= 2) {
                                    for (int i = 0; i < grass.size(); i++) {
                                        if (grass.get(i).getPosx() == p.getPosx() && grass.get(i).getPosy() == p.getPosy()+1) {
                                            grass.remove((int)i);
                                            break;
                                        }
                                    }
                                    p.setGrass(p.getGrass()+1);
                                    p.setEnergi(p.getEnergi()-2);
                                }
                            }
                            ladang[p.getPosy()][p.getPosx()] = " ";
                            p.setPosy(p.getPosy()+1);
                            ladang[p.getPosy()][p.getPosx()] = p.getSimbol();
                        }else if (ladang[p.getPosy()+1][p.getPosx()].equals("H")) {
                            isHome = true;
                        }else if (ladang[p.getPosy()+1][p.getPosx()].equals("M")) {
                            isMarket = true;
                        }
                    }else if (inp2.equalsIgnoreCase("d")) {
                        if (!ladang[p.getPosy()][p.getPosx()+1].equals("H") && !ladang[p.getPosy()][p.getPosx()+1].equals("M") && !ladang[p.getPosy()][p.getPosx()+1].equals("|")) {
                            if (ladang[p.getPosy()][p.getPosx()+1].equals(r.getSimbol())) {
                                if (p.getEnergi() >= 2) {
                                    for (int i = 0; i < grass.size(); i++) {
                                        if (grass.get(i).getPosx() == p.getPosx()+1 && grass.get(i).getPosy() == p.getPosy()) {
                                            grass.remove((int)i);
                                            break;
                                        }
                                    }
                                    p.setGrass(p.getGrass()+1);
                                    p.setEnergi(p.getEnergi()-2);
                                }
                            }
                            ladang[p.getPosy()][p.getPosx()] = " ";
                            p.setPosx(p.getPosx()+1);
                            ladang[p.getPosy()][p.getPosx()] = p.getSimbol();
                        }else if (ladang[p.getPosy()][p.getPosx()+1].equals("H")) {
                            isHome = true;
                        }else if (ladang[p.getPosy()][p.getPosx()+1].equals("M")) {
                            isMarket = true;
                        }
                    }else if (inp2.equalsIgnoreCase("i")) {
                        System.out.println("-Inventory-");
                        System.out.println("Grass : " + p.getGrass());
                        System.out.println("Wheat : " + p.getWheat());
                        System.out.println("Corn  : " + p.getCorn());
                        System.out.println("Potato: " + p.getPotato());
                        System.out.println("Fertilizer");
                        System.out.println("Natural    : " + n.getStok());
                        System.out.println("Artificial : " + a.getStok());
                        System.out.println("Press Enter to Continue");
                        String temp = sc.nextLine();
                    }else if (inp2.equalsIgnoreCase("p")) {
                        if (!isPlanted[p.getPosy()][p.getPosx()]) {
                            int inp3;
                            do {
                                System.out.println("Plant Menu");
                                System.out.println("1. Wheat (" + w.getStok() + ")");
                                System.out.println("2. Corn (" + c.getStok() + ")");
                                System.out.println("3. Potato (" + k.getStok() + ")");
                                System.out.println("0. Back");
                                System.out.println(">> ");
                                inp3 = Integer.parseInt(sc.nextLine());
                                
                                if (!isPlanted[p.getPosy()][p.getPosx()]) {
                                    if (inp3 == 1) {
                                        if (w.getStok() > 0) {
                                            wheat.add(new Wheat(p.getPosx(),p.getPosy()));
                                            isPlanted[p.getPosy()][p.getPosx()] = true;
                                            w.setStok(w.getStok() - 1);
                                            
                                            for (int i = 0; i < scarecrow.size(); i++) {
                                                int [] korx = {0,1,1,1,0,-1,-1,-1};
                                                int [] kory = {-1,-1,0,1,1,1,0,-1};
                                                for (int j = 0; j < 8; j++) {
                                                    if (scarecrow.get(i).getPosx() == wheat.get(wheat.size()-1).getPosx() + korx[j] && scarecrow.get(i).getPosy() == wheat.get(wheat.size()-1).getPosy() + kory[j]) {
                                                        wheat.get(wheat.size()-1).setProtected(true);
                                                    }
                                                }
                                            }
                                        }
                                    }else if (inp3 == 2) {
                                        if (c.getStok() > 0) {
                                            corn.add(new Corn(p.getPosx(),p.getPosy()));
                                            isPlanted[p.getPosy()][p.getPosx()] = true;
                                            c.setStok(c.getStok() - 1);
                                            
                                            for (int i = 0; i < scarecrow.size(); i++) {
                                                int [] korx = {0,1,1,1,0,-1,-1,-1};
                                                int [] kory = {-1,-1,0,1,1,1,0,-1};
                                                for (int j = 0; j < 8; j++) {
                                                    if (scarecrow.get(i).getPosx() == corn.get(corn.size()-1).getPosx() + korx[j] && scarecrow.get(i).getPosy() == corn.get(corn.size()-1).getPosy() + kory[j]) {
                                                        corn.get(corn.size()-1).setProtected(true);
                                                    }
                                                }
                                            }
                                        }
                                    }else if (inp3 == 3) {
                                        if (k.getStok() > 0) {
                                            potato.add(new Potato(p.getPosx(),p.getPosy()));
                                            isPlanted[p.getPosy()][p.getPosx()] = true;
                                            k.setStok(k.getStok() - 1);
                                            
                                            for (int i = 0; i < scarecrow.size(); i++) {
                                                int [] korx = {0,1,1,1,0,-1,-1,-1};
                                                int [] kory = {-1,-1,0,1,1,1,0,-1};
                                                for (int j = 0; j < 8; j++) {
                                                    if (scarecrow.get(i).getPosx() == potato.get(potato.size()-1).getPosx() + korx[j] && scarecrow.get(i).getPosy() == potato.get(potato.size()-1).getPosy() + kory[j]) {
                                                        potato.get(potato.size()-1).setProtected(true);
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }else{
                                    System.out.println("The field has been planted!");
                                }
                            } while (inp3 != 0);
                        }else{
                            boolean isw = false, isc = false, isp = false;
                            int idx = -1;
                            for (int i = 0; i < wheat.size(); i++) {
                                if (wheat.get(i).getPosy() == p.getPosy() && wheat.get(i).getPosx() == p.getPosx()) {
                                    isw = true;
                                    idx = i;
                                }
                            }
                            for (int i = 0; i < corn.size(); i++) {
                                if (corn.get(i).getPosy() == p.getPosy() && corn.get(i).getPosx() == p.getPosx()) {
                                    isc = true;
                                    idx = i;
                                }
                            }
                            for (int i = 0; i < potato.size(); i++) {
                                if (potato.get(i).getPosy() == p.getPosy() && potato.get(i).getPosx() == p.getPosx()) {
                                    isp = true;
                                    idx = i;
                                }
                            }
                            if (isw && idx != -1) {
                                if (wheat.get(idx).getWaktu() <= 0) {
                                    isPlanted[p.getPosy()][p.getPosx()] = false;
                                    p.setWheat(p.getWheat() + 1);
                                    wheat.remove((int)idx);
                                }else if (!wheat.get(idx).isWatered()) {
                                    if (p.getEnergi() >= w.getSiram()) {
                                        wheat.get(idx).setWatered(true);
                                        p.setEnergi(p.getEnergi() - w.getSiram());
                                    }else{
                                        System.out.println("Energy isn't enough!");
                                    }
                                }else{
                                    System.out.println("The plant has been watered!");
                                }
                            }else if (isc && idx != -1) {
                                if (corn.get(idx).getWaktu() <= 0) {
                                    isPlanted[p.getPosy()][p.getPosx()] = false;
                                    p.setCorn(p.getCorn() + 1);
                                    corn.remove((int)idx);
                                }else if (!corn.get(idx).isWatered()) {
                                    if (p.getEnergi() >= c.getSiram()) {
                                        corn.get(idx).setWatered(true);
                                        p.setEnergi(p.getEnergi() - c.getSiram());
                                    }else{
                                        System.out.println("Energy isn't enough!");
                                    }
                                }else{
                                    System.out.println("The plant has been watered!");
                                }
                            }else if (isp && idx != -1) {
                                if (potato.get(idx).getWaktu() <= 0) {
                                    isPlanted[p.getPosy()][p.getPosx()] = false;
                                    p.setPotato(p.getPotato() + 1);
                                    potato.remove((int)idx);
                                }else if (!potato.get(idx).isWatered()) {
                                    if (p.getEnergi() >= k.getSiram()) {
                                        potato.get(idx).setWatered(true);
                                        p.setEnergi(p.getEnergi() - k.getSiram());
                                    }else{
                                        System.out.println("Energy isn't enough!");
                                    }
                                }else{
                                    System.out.println("The plant has been watered!");
                                }
                            }
                        }
                    }else if (inp2.equalsIgnoreCase("f")) {
                        int inp3;
                        do {
                            System.out.println("Fertilizer Menu");
                            System.out.println("1. Natural");
                            System.out.println("2. Artificial");
                            System.out.println("0. Back");
                            System.out.println(">> ");
                            inp3 = Integer.parseInt(sc.nextLine());
                            
                            boolean isw = false, isc = false, isp = false;
                            int idx = -1;
                            for (int l = 0; l < wheat.size(); l++) {
                                if (wheat.get(l).getPosy() == p.getPosy() && wheat.get(l).getPosx() == p.getPosx()) {
                                    isw = true;
                                    idx = l;
                                }
                            }
                            for (int l = 0; l < corn.size(); l++) {
                                if (corn.get(l).getPosy() == p.getPosy() && corn.get(l).getPosx() == p.getPosx()) {
                                    isc = true;
                                    idx = l;
                                }
                            }
                            for (int l = 0; l < potato.size(); l++) {
                                if (potato.get(l).getPosy() == p.getPosy() && potato.get(l).getPosx() == p.getPosx()) {
                                    isp = true;
                                    idx = l;
                                }
                            }
                            
                            if (inp3 == 1) {
                                if (n.getStok() > 0) {
                                    if (isw) {
                                        wheat.get(idx).setPercepat(wheat.get(idx).getPercepat() + n.getBoost());
                                        n.setStok(n.getStok() - 1);
                                    }else if (isc) {
                                        corn.get(idx).setPercepat(corn.get(idx).getPercepat() + n.getBoost());
                                        n.setStok(n.getStok() - 1);
                                    }else if (isp) {
                                        potato.get(idx).setPercepat(potato.get(idx).getPercepat() + n.getBoost());
                                        n.setStok(n.getStok() - 1);
                                    }
                                    System.out.println("Success to boost!");
                                }else{
                                    System.out.println("There is not enough fertilizaer natural!");
                                }
                            }else if (inp3 == 2) {
                                if (a.getStok() > 0) {
                                    if (isw) {
                                        wheat.get(idx).setPercepat(wheat.get(idx).getPercepat() + a.getBoost());
                                        a.setStok(a.getStok() - 1);
                                    }else if (isc) {
                                        corn.get(idx).setPercepat(corn.get(idx).getPercepat() + a.getBoost());
                                        a.setStok(a.getStok() - 1);
                                    }else if (isp) {
                                        potato.get(idx).setPercepat(potato.get(idx).getPercepat() + a.getBoost());
                                        a.setStok(a.getStok() - 1);
                                    }
                                    System.out.println("Success to boost!");
                                }else{
                                    System.out.println("There is not enough fertilizer artificial!");
                                }
                            }
                        } while (inp3 != 0);
                    }else if (inp2.equalsIgnoreCase("c")) {
                        if (!isPlanted[p.getPosy()][p.getPosx()]) {
                            if (s.getStok() > 0) {
                                scarecrow.add(new Scarecrow(p.getPosx(),p.getPosy()));
                                s.setStok(s.getStok() - 1);
                                for (int i = 0; i < wheat.size(); i++) {
                                    int [] korx = {0,1,1,1,0,-1,-1,-1};
                                    int [] kory = {-1,-1,0,1,1,1,0,-1};
                                    for (int j = 0; j < 8; j++) {
                                        if (wheat.get(i).getPosx() == p.getPosx() + korx[j] && wheat.get(i).getPosy() == p.getPosy() + kory[j]) {
                                            wheat.get(i).setProtected(true);
                                        }
                                    }
                                }
                                for (int i = 0; i < corn.size(); i++) {
                                    int [] korx = {0,1,1,1,0,-1,-1,-1};
                                    int [] kory = {-1,-1,0,1,1,1,0,-1};
                                    for (int j = 0; j < 8; j++) {
                                        if (corn.get(i).getPosx() == p.getPosx() + korx[j] && corn.get(i).getPosy() == p.getPosy() + kory[j]) {
                                            corn.get(i).setProtected(true);
                                        }
                                    }
                                }
                                for (int i = 0; i < potato.size(); i++) {
                                    int [] korx = {0,1,1,1,0,-1,-1,-1};
                                    int [] kory = {-1,-1,0,1,1,1,0,-1};
                                    for (int j = 0; j < 8; j++) {
                                        if (potato.get(i).getPosx() == p.getPosx() + korx[j] && potato.get(i).getPosy() == p.getPosy() + kory[j]) {
                                            potato.get(i).setProtected(true);
                                        }
                                    }
                                }
                                for (int i = 0; i < grass.size(); i++) {
                                    int [] korx = {0,1,1,1,0,-1,-1,-1};
                                    int [] kory = {-1,-1,0,1,1,1,0,-1};
                                    for (int j = 0; j < 8; j++) {
                                        if (grass.get(i).getPosx() == p.getPosx() + korx[j] && grass.get(i).getPosy() == p.getPosy() + kory[j]) {
                                            grass.get(i).setProtected(true);
                                        }
                                    }
                                }
                            }else{
                                System.out.println("You don't have scarecrow anymore!");
                            }
                        }else{
                            System.out.println("The field has been planted!");
                        }
                    }else if (inp2.equals("cheat")) {
                        for (int i = 0; i < wheat.size(); i++) {
                            wheat.get(i).setWaktu(0);
                        }
                        for (int i = 0; i < corn.size(); i++) {
                            corn.get(i).setWaktu(0);
                        }
                        for (int i = 0; i < potato.size(); i++) {
                            potato.get(i).setWaktu(0);
                        }
                    }
                    
                    if (isHome) {
                        int inp3;
                        do {
                            System.out.println("-Home-");
                            System.out.println("1. Craft");
                            System.out.println("2. Sleep");
                            System.out.println("0. Exit");
                            System.out.println(">> ");
                            inp3 = Integer.parseInt(sc.nextLine());
                            
                            if (inp3 == 1) {
                                System.out.println("-Craft-");
                                System.out.println("Grass : " + p.getGrass());
                                System.out.println("Stock : ");
                                int jumlah = Integer.parseInt(sc.nextLine());
                                
                                if (p.getGrass() >= jumlah*2) {
                                    n.setStok(n.getStok() + jumlah);
                                    p.setGrass(p.getGrass() - (jumlah*2));
                                    System.out.println("Success to make " + jumlah + " fetilizer natural!");
                                }else{
                                    System.out.println("Failed to make " + jumlah + " fertilizer natural!");
                                    System.out.println("You don't have enough grass!");
                                }
                            }else if (inp3 == 2) {
                                System.out.println("Good Night~");
                                day++;
                                p.setEnergi(100);
                                crow = true;
                                
                                // rumput
                                for (int i = 0; i < 3; i++) {
                                    do {
                                        x = rd.nextInt(25)+1;
                                        y = rd.nextInt(10)+1;

                                        if (ladang[y][x].equals(" ")) {
                                            break;
                                        }
                                    } while (true);
                                    grass.add(new Rumput(x,y));
                                    for (int j = 0; j < scarecrow.size(); j++) {
                                        int [] korx = {0,1,1,1,0,-1,-1,-1};
                                        int [] kory = {-1,-1,0,1,1,1,0,-1};
                                        for (int l = 0; l < 8; l++) {
                                            if (scarecrow.get(j).getPosx() == grass.get(grass.size()-1).getPosx() + korx[l] && scarecrow.get(j).getPosy() == grass.get(grass.size()-1).getPosy() + kory[l]) {
                                                grass.get(grass.size()-1).setProtected(true);
                                            }
                                        }
                                    }
                                    ladang[y][x] = r.getSimbol();
                                }
                                
                                // tanaman
                                for (int i = 0; i < wheat.size(); i++) {
                                    if (wheat.get(i).getWaktu() > 0) {
                                        if (wheat.get(i).isWatered()) {
                                            wheat.get(i).setWatered(false);
                                        }else{
                                            wheat.remove((int)i);
                                        }
                                    }
                                }
                                for (int i = 0; i < corn.size(); i++) {
                                    if (corn.get(i).getWaktu() > 0) {
                                        if (corn.get(i).isWatered()) {
                                            corn.get(i).setWatered(false);
                                        }else{
                                            corn.remove((int)i);
                                        }
                                    }
                                }
                                for (int i = 0; i < potato.size(); i++) {
                                    if (potato.get(i).getWaktu() > 0) {
                                        if (potato.get(i).isWatered()) {
                                            potato.get(i).setWatered(false);
                                        }else{
                                            potato.remove((int)i);
                                        }
                                    }
                                }
                                
                                // wheat, corn, potato
                                for (int i = 0; i < wheat.size(); i++) {
                                    wheat.get(i).setWaktu(wheat.get(i).getWaktu() - 1 - wheat.get(i).getPercepat());
                                    wheat.get(i).setPercepat(0);
                                }
                                for (int i = 0; i < corn.size(); i++) {
                                    corn.get(i).setWaktu(corn.get(i).getWaktu() - 1 - corn.get(i).getPercepat());
                                    corn.get(i).setPercepat(0);
                                }
                                for (int i = 0; i < potato.size(); i++) {
                                    potato.get(i).setWaktu(potato.get(i).getWaktu() - 1 - potato.get(i).getPercepat());
                                    potato.get(i).setPercepat(0);
                                }
                            }
                        } while (inp3 != 0);
                    }
                    
                    if (isMarket) {
                        int inp3;
                        do {
                            System.out.println("-Market-");
                            System.out.println("1. Buy Items");
                            System.out.println("2. Sell Items");
                            System.out.println("0. Back");
                            System.out.println(">> ");
                            inp3 = Integer.parseInt(sc.nextLine());
                            
                            if (inp3 == 1) {
                                int inp4;
                                do {
                                    System.out.println("-Market-");
                                    System.out.println("Gold : " + p.getGold());
                                    System.out.println("1. Wheat - " + w.getH_beli() + "G");
                                    System.out.println("2. Corn - " + c.getH_beli() + "G");
                                    System.out.println("3. Potato - " + k.getH_beli() + "G");
                                    System.out.println("4. Natural Fertilizer - " + n.getH_beli() + "G");
                                    System.out.println("5. Artificial Fertilizer - " + a.getH_beli() + "G");
                                    System.out.println("6. Scarecrow - " + s.getH_beli() + "G");
                                    System.out.println("0. Exit");
                                    System.out.println(">> ");
                                    inp4 = Integer.parseInt(sc.nextLine());
                                    
                                    int jumlah;
                                    if (inp4 == 1) {
                                        System.out.println("Jumlah : ");
                                        jumlah = Integer.parseInt(sc.nextLine());
                                        if (p.getGold() >= jumlah*w.getH_beli()) {
                                            w.setStok(w.getStok() + jumlah);
                                            p.setGold(p.getGold() - (jumlah*w.getH_beli()));
                                            System.out.println("Wheat success to buy!");
                                        }else{
                                            System.out.println("Wheat failed to buy!");
                                            System.out.println("There is not enough gold");
                                        }
                                    }else if (inp4 == 2) {
                                        System.out.println("Jumlah : ");
                                        jumlah = Integer.parseInt(sc.nextLine());
                                        if (p.getGold() >= jumlah*c.getH_beli()) {
                                            c.setStok(c.getStok() + jumlah);
                                            p.setGold(p.getGold() - (jumlah*c.getH_beli()));
                                            System.out.println("Corn success to buy!");
                                        }else{
                                            System.out.println("Corn failed to buy!");
                                            System.out.println("There is not enough gold");
                                        }
                                    }else if (inp4 == 3) {
                                        System.out.println("Jumlah : ");
                                        jumlah = Integer.parseInt(sc.nextLine());
                                        if (p.getGold() >= jumlah*k.getH_beli()) {
                                            k.setStok(k.getStok() + jumlah);
                                            p.setGold(p.getGold() - (jumlah*k.getH_beli()));
                                            System.out.println("Potato success to buy!");
                                        }else{
                                            System.out.println("Potato failed to buy!");
                                            System.out.println("There is not enough gold");
                                        }
                                    }else if (inp4 == 4) {
                                        System.out.println("Jumlah : ");
                                        jumlah = Integer.parseInt(sc.nextLine());
                                        if (p.getGold() >= jumlah*n.getH_beli()) {
                                            n.setStok(n.getStok() + jumlah);
                                            p.setGold(p.getGold() - (jumlah*n.getH_beli()));
                                            System.out.println("Fertilizer Natural success to buy!");
                                        }else{
                                            System.out.println("Fertilizer Natural failed to buy!");
                                            System.out.println("There is not enough gold");
                                        }
                                    }else if (inp4 == 5) {
                                        System.out.println("Jumlah : ");
                                        jumlah = Integer.parseInt(sc.nextLine());
                                        if (p.getGold() >= jumlah*a.getH_beli()) {
                                            a.setStok(a.getStok() + jumlah);
                                            p.setGold(p.getGold() - (jumlah*a.getH_beli()));
                                            System.out.println("Fertilizer Artificial success to buy!");
                                        }else{
                                            System.out.println("Fertilizer Artificial failed to buy!");
                                            System.out.println("There is not enough gold");
                                        }
                                    }else if (inp4 == 6) {
                                        System.out.println("Jumlah : ");
                                        jumlah = Integer.parseInt(sc.nextLine());
                                        if (p.getGold() >= jumlah*s.getH_beli()) {
                                            s.setStok(s.getStok() + jumlah);
                                            p.setGold(p.getGold() - (jumlah*s.getH_beli()));
                                            System.out.println("Scarecrow success to buy!");
                                        }else{
                                            System.out.println("Scarecrow failed to buy!");
                                            System.out.println("There is not enough gold");
                                        }
                                    }
                                } while (inp4 != 0);
                            }else if (inp3 == 2) {
                                int inp4;
                                do {
                                    System.out.println("-Market-");
                                    System.out.println("Gold : " + p.getGold());
                                    System.out.println("1. Grass(" + p.getGrass() + ") - " + r.getH_jual() + "G");
                                    System.out.println("2. Wheat(" + p.getWheat() + ") - " + w.getH_jual() + "G");
                                    System.out.println("3. Corn(" + p.getCorn() + ") - " + c.getH_jual() + "G");
                                    System.out.println("4. Potato(" + p.getPotato() + ") - " + k.getH_jual() + "G");
                                    System.out.println("0. Back");
                                    System.out.println(">> ");
                                    inp4 = Integer.parseInt(sc.nextLine());
                                    
                                    int jumlah;
                                    if (inp4 == 1) {
                                        System.out.println("Jumlah : ");
                                        jumlah = Integer.parseInt(sc.nextLine());
                                        if (jumlah <= p.getGrass()) {
                                            p.setGrass(p.getGrass() - jumlah);
                                            p.setGold(p.getGold() + jumlah*r.getH_jual());
                                            System.out.println("Grass success to sell!");
                                        }else{
                                            System.out.println("Grass failed to sell!");
                                            System.out.println("There is not enough Grass");
                                        }
                                    }else if (inp4 == 2) {
                                        System.out.println("Jumlah : ");
                                        jumlah = Integer.parseInt(sc.nextLine());
                                        if (jumlah <= p.getWheat()) {
                                            p.setWheat(p.getWheat() - jumlah);
                                            p.setGold(p.getGold() + jumlah*w.getH_jual());
                                            System.out.println("Wheat success to sell!");
                                        }else{
                                            System.out.println("Wheat failed to sell!");
                                            System.out.println("There is not enough Wheat");
                                        }
                                    }else if (inp4 == 3) {
                                        System.out.println("Jumlah : ");
                                        jumlah = Integer.parseInt(sc.nextLine());
                                        if (jumlah <= p.getCorn()) {
                                            p.setCorn(p.getCorn() - jumlah);
                                            p.setGold(p.getGold() + jumlah*c.getH_jual());
                                            System.out.println("Corn success to sell!");
                                        }else{
                                            System.out.println("Corn failed to sell!");
                                            System.out.println("There is not enough Corn");
                                        }
                                    }else if (inp4 == 4) {
                                        System.out.println("Jumlah : ");
                                        jumlah = Integer.parseInt(sc.nextLine());
                                        if (jumlah <= p.getPotato()) {
                                            p.setPotato(p.getPotato() - jumlah);
                                            p.setGold(p.getGold() + jumlah*k.getH_jual());
                                            System.out.println("Potato success to sell!");
                                        }else{
                                            System.out.println("Potato failed to sell!");
                                            System.out.println("There is not enough Potato");
                                        }
                                    }
                                } while (inp4 != 0);
                            }
                        } while (inp3 != 0);
                    }
                    
                    if (crow) {
                        int counter = 0;
                        for (int i = 0; i < grass.size(); i++) {
                            if (!grass.get(i).isProtected()) {
                                if (rd.nextInt(10) == 0) {
                                    ladang[grass.get(i).getPosy()][grass.get(i).getPosx()] = " ";
                                    grass.remove((int)i);
                                    counter++;
                                }
                            }
                        }
                        for (int i = 0; i < wheat.size(); i++) {
                            if (!wheat.get(i).isProtected()) {
                                if (rd.nextInt(10) == 0) {
                                    isPlanted[wheat.get(i).getPosy()][wheat.get(i).getPosx()] = false;
                                    ladang[wheat.get(i).getPosy()][wheat.get(i).getPosx()] = " ";
                                    wheat.remove((int)i);
                                    counter++;
                                }
                            }
                        }
                        for (int i = 0; i < corn.size(); i++) {
                            if (!corn.get(i).isProtected()) {
                                if (rd.nextInt(10) == 0) {
                                    isPlanted[corn.get(i).getPosy()][corn.get(i).getPosx()] = false;
                                    ladang[corn.get(i).getPosy()][corn.get(i).getPosx()] = " ";
                                    corn.remove((int)i);
                                    counter++;
                                }
                            }
                        }
                        for (int i = 0; i < potato.size(); i++) {
                            if (!potato.get(i).isProtected()) {
                                if (rd.nextInt(10) == 0) {
                                    isPlanted[potato.get(i).getPosy()][potato.get(i).getPosx()] = false;
                                    ladang[potato.get(i).getPosy()][potato.get(i).getPosx()] = " ";
                                    potato.remove((int)i);
                                    counter++;
                                }
                            }
                        }
                        System.out.println("The crow attack " + counter + " plant!");
                        crow = false;
                    }
                } while (true);
            }
        } while (inp != 0);
    }
}
