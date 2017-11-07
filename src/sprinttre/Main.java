package sprinttre;

import javax.swing.*;

public class Main extends JFrame{

    public Main() {
       setVisible(true);
       setSize(500, 570);    //För rutStorlket 120 så blir denna Size bra
       setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
       setLocation(900, 400);
       setContentPane(new SpelUI());    //För att få Spelet att visas i JFrame:n
    }

    public static void main(String[] args) { Main m = new Main(); }
}
