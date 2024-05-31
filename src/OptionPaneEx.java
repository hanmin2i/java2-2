import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

public class OptionPaneEx extends JFrame{
    public OptionPaneEx() {
        setTitle("옵션 팬 예제");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Container c =getContentPane();
        setSize(500, 200);
        c.add(new MyPanel(), BorderLayout.NORTH);
        setVisible(true);
    }

    class MyPanel extends Panel{
        private JButton inputBtn = new JButton("input Name");
        private JTextField tf = new JTextField(10);
        private JButton confirmBtn = new JButton("Confirm");
    }
    
}
