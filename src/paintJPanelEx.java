import javax.swing.*;
import java.awt.*;
    public class paintJPanelEx extends JFrame {
        public paintJPanelEx() {
            setTitle("JPanel의 paintComponent() 예제");
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setContentPane(new MyPanel());
            setSize(250,200);
            setVisible(true);
}

    class MyPanel extends JPanel {
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.setColor(Color.BLUE); 
            g.drawRect(10,10,50,50);
            g.drawString("굿잡이에요",50,50);
            g.setColor(new Color(255, 0, 0)); 
            g.setFont(new Font("Arial", Font.ITALIC, 30));
            g.drawString("akakakak", 30, 120);
            g.drawRect(90,90,50,50);
            g.drawLine(20, 20, 100, 100);
            g.drawRoundRect(20,20,120,80,40,60);
            g.drawArc(20,100,80,80,90,270);
        }
}
    public static void main(String [] args) {
        new paintJPanelEx();
}
}