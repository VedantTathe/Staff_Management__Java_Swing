
import java.awt.event.*;
import java.awt.*;
import javax.swing.*;

public class StaffMyProfile implements ActionListener
 {
    private JFrame frame;

    public StaffMyProfile()
    {
        frame = new JFrame();

        frame.setVisible(true);
        frame.setSize(1000,1000);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'actionPerformed'");
    }
    
    public static void main(String[] args) {
        new StaffMyProfile();
    }
}
