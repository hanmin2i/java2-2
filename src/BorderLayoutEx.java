import javax.swing.*;
import java.awt.*;

public class BorderLayoutEx extends JFrame{
    public BorderLayoutEx() {
        setTitle("BorderLayoutEx 예제");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Container contentPane = getContentPane();

        contentPane.setLayout(new BorderLayout(30,40));
    
        contentPane.add(new JButton("Calculate"));
        contentPane.add(new JButton("add"));
        contentPane.add(new JButton("sub"));
        contentPane.add(new JButton("mul"));
        contentPane.add(new JButton("div"));
        
        
        setSize(300, 200);
        setVisible(true);
    }
    public static void main(String[] args) {
        new BorderLayoutEx();
    }
}
