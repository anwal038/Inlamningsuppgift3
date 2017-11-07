package sprinttre;

import javax.swing.*;

public class Funktionalitet extends Spelbrickorna{

    protected Spelbrickorna[][] spelYtan; // 2D array
    protected Spelbrickorna tomSpelbricka;

    // Instansvariabler för att ange hur stor spelytan ska vara:  4 * 4 = 16 brickor stor yta
    protected int rader = 4;
    protected int kolumner = 4;

    // Konstruktor
    public Funktionalitet() {
        spelYtan = new Spelbrickorna[rader][kolumner];
        resetaSpel();  //Utan detta anrop = Null PointerException

        if (DuVann()) {
        JOptionPane.showMessageDialog(new JPanel(), "Du klarade spelet!", "Klarat!", JOptionPane.INFORMATION_MESSAGE);
        }
    }
    public String getYtan (int rad, int kol) {return spelYtan[rad][kol].getYtan();}


    public void SlumpaRuntBrickorna(int r1, int k1, int r2, int k2) {
        Spelbrickorna sb = spelYtan[r1][k1];
        spelYtan[r1][k1] = spelYtan[r2][k2];
        spelYtan[r2][k2] = sb;
        //JButton är kopplad till denna metod i SpelUI.java
    }
    public boolean enLedigPlats(int r, int k) { return r >= 0 && r < rader && k >= 0 && k < kolumner;}

    public boolean ärPlatsLedig(int r, int k, int rDelta, int kDelta) {
        int radGranne = r + rDelta;
        int kolumnGranne = k + kDelta;
        if (enLedigPlats(radGranne, kolumnGranne) && spelYtan[radGranne][kolumnGranne] == tomSpelbricka) {
            SlumpaRuntBrickorna(r, k, radGranne, kolumnGranne);
            return true;
        }
        return false;
    }

    public boolean flyttaSpelbricka(int r, int k) {
        return  ärPlatsLedig(r, k, -1,  0)  ||
                ärPlatsLedig(r, k,  1,  0)  ||
                ärPlatsLedig(r, k,  0, -1)  ||
                ärPlatsLedig(r, k,  0,  1);
    }

    public void resetaSpel() {
        for (int r = 0; r < rader; r++) {
            for (int k = 0; k < kolumner; k++) {
                spelYtan[r][k] = new Spelbrickorna(r, k, " " + (k +1 + (r*kolumner))); // +1 för att få siffran på
                // varje bricka att börja på 1 istället för 0
            }
        }
        tomSpelbricka = spelYtan[rader - 1][kolumner - 1]; // -1 för att få siffrorna att stämma
        tomSpelbricka.setYtan(null);                       // <- Detta är den tomma, lediga platsen i spelet
        for (int r = 0; r < rader; r++) {
            for (int k = 0; k < kolumner; k++) {
                SlumpaRuntBrickorna(r, k, (int) (Math.random() * rader), (int) (Math.random() * kolumner));
                // detta slumpar runt brickorna
            }
        }
    }
    public boolean DuVann() {
        for (int r = 0; r < rader; r++) {
            for (int k = 0; k < kolumner; k++) {
                Spelbrickorna slutgiltigPosition = spelYtan[r][k];
                return slutgiltigPosition.påRättPlats(r, k);  // Metodanrop från Spelbrickorna.java
            }
        }
        return  true;
    }
}
