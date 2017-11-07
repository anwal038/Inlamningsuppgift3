package sprinttre;

public class Spelbrickorna  {

    protected int antalRader;
    protected int antalKolumner;
    protected String ytan;

    // Default Kontstruktor för att Funktionalitet.java ska kunna ärva denna klass
    public Spelbrickorna() {}

    public Spelbrickorna(int antalRader, int antalKolumner, String ytan) {
        this.antalRader = antalRader;
        this.antalKolumner = antalKolumner;
        this.ytan = ytan;
    }

    public boolean påRättPlats(int r, int k) { return r == antalRader && k == antalKolumner; }

    public void setYtan(String nyYta) { ytan = nyYta;}
    public String getYtan() {return ytan;}
}

