package sprinttre;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class SpelUI extends JPanel implements ActionListener {

    protected JButton nyttSpelKnapp = new JButton("Nytt Spel");

    protected SpelGrafikPanel spelGrafik;  // Lokal klass längre ner, rad 34
    protected Funktionalitet funktion = new Funktionalitet();

    public SpelUI() {

        spelGrafik = new SpelGrafikPanel();
        nyttSpelKnapp.addActionListener(this);

        JPanel kontrollPanel = new JPanel();
        kontrollPanel.setLayout(new FlowLayout());
        kontrollPanel.add(nyttSpelKnapp);


        this.setLayout(new BorderLayout());
        this.add(kontrollPanel, BorderLayout.NORTH); // JButton högst upp i spelprogrammet
        this.add(spelGrafik, BorderLayout.CENTER);   // Under knappen visas spelbrickorna
    }


    // ------------------  Lokal Klass ------------------------------------//
    public class SpelGrafikPanel extends  JPanel implements MouseListener {

        protected int rader = 4;
        protected int kolumner = 4;
        protected int rutStorlek = 120;

        public SpelGrafikPanel() {this.addMouseListener(this); }
        public void paintComponent(Graphics g) {
            super.paintComponent(g);

            for (int r = 0; r < rader; r++) {
                for (int k = 0; k < kolumner; k++) {
                    int rx = r * rutStorlek;
                    int kx = k * rutStorlek;
                    String text = funktion.getYtan(r, k);
                    if (text != null) {
                        g.setColor(Color.BLACK);                                                        //Brickans färg
                        g.fillRect(rx + 2, kx + 2, rutStorlek - 3, rutStorlek - 3);    //Glipan mellan varje bricka
                        g.setColor(Color.GREEN);                                                        //Siffrans färg
                        g.drawString(text, rx+50, kx + (4* rutStorlek / 6));                //Skapa siffrorna + bestämma position i rutan
                    }   // Utan drawString = inga siffror i rutorna
                }
            }
        }

        @Override
        public void mousePressed(MouseEvent e)  {
            int rad = e.getX() / rutStorlek;
            int kol = e.getY() / rutStorlek;
            if (!funktion.flyttaSpelbricka(rad, kol)){ }
            this.repaint();
        }
        @Override
        public void mouseClicked(MouseEvent e)  { }
        @Override
        public void mouseReleased(MouseEvent e) { }
        @Override
        public void mouseEntered(MouseEvent e)  { }
        @Override
        public void mouseExited(MouseEvent e)   { }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == nyttSpelKnapp) {
            funktion.resetaSpel(); //Metodanrop från Funktionalitets-klassen
            spelGrafik.repaint();
        }
    }
}
