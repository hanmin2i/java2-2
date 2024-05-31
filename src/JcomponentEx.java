import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class JcomponentEx extends JFrame{
    public JcomponentEx() {
        super("j컴포넌트의 공동 메소드 예제");
        Container c = getContentPane();
        c.setLayout(new FlowLayout());
        JButton b1 = new JButton("magenta/tellow button");
        JButton b2 = new JButton("Disabled Button");
        JButton b3 = new JButton("getx(), gety()");
        b1.setBackground(Color.YELLOW);
        b1.setBackground(Color.MAGENTA);
        b1.setFont(new Font("Arial", Font.ITALIC, 20));
        b2.setEnabled(false);
        b3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
               JButton b =(JButton)e.getSource();
               setTitle(b.getX() + "," + b.getY());
            }
        });
        c.add(b1); c.add(b2); c.add(b3);
        setSize(260, 200); setVisible(true);
    }
    public static void main(String[] args) {
        new JcomponentEx();
    }
}
