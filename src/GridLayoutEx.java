import javax.swing.*;
import java.awt.*;

public class GridLayoutEx extends JFrame{
    public GridLayoutEx() {
        setTitle("GridLayoutEx 예제");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Container contentPane = getContentPane();

        contentPane.setLayout(new GridLayout(4, 3));

        for(int i=1; i<10; i++){
            String text = Integer.toString(i);
            JButton button = new JButton(text);
            contentPane.add(button);
        }
        JButton button = new JButton("*");
        contentPane.add(button);
        JButton button1 = new JButton("0");
        contentPane.add(button1);// = cotentPane.add(new JButton("0"));

        setSize(300, 200);
        setVisible(true);
    }

    public static void main(String[] args) {
        new GridLayoutEx();
    }
}