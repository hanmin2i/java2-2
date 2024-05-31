import javax.swing.*;
import java.awt.*;

public class JlableEx extends JFrame{
    public JlableEx() {
        setTitle("레이블 예제");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Container c= getContentPane();
        c.setLayout(new FlowLayout());

        JLabel textLabel = new JLabel("제임스 뭐시기");

        ImageIcon img = new ImageIcon("images/gosling.jpg");
        JLabel imageLabel = new JLabel(img);

        ImageIcon icon = new ImageIcon("images/icon.gif");
        JLabel label = new JLabel("커피 가자", icon, SwingConstants.CENTER );
        c.add(textLabel);  c.add(imageLabel); c.add(label);
        setSize(300, 500);
        setVisible(true);
    }
    public static void main(String[] args) {
        new JlableEx();
    }
} 
