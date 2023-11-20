import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class DeleteClass implements ActionListener
{
    private JFrame frame;
    public DeleteClass()
    {
        frame = new JFrame();

        frame.setVisible(true);
        frame.setLocation(150, 100);
        frame.setSize(1000,1000);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'actionPerformed'");
    }

    public static void main(String[] args) {
        new DeleteClass();
    }
}
