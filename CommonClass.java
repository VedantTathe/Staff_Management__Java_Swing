import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

public class CommonClass  implements ActionListener{
     private JFrame frame;

    public CommonClass()
    {
        frame = new JFrame();

        frame.setVisible(true);
        frame.setSize(1000,1000);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
       
    }
   
}
