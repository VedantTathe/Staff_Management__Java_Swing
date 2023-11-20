import java.awt.event.*;
import java.awt.*;
import javax.swing.*;
import javax.swing.event.InternalFrameEvent;
import javax.swing.event.InternalFrameListener;

public class RegisterLogin implements ActionListener,InternalFrameListener 
{
    public static JFrame frame;
    private JToolBar toolBar;
    private static JDesktopPane pane;
    private ImageIcon icon_bg;
    public static AdminLogin admin_login = null;
    public static StaffLogin staff_login = null;
    public static RegisterAdmin register_admin = null;
    public static RegisterStaff  register_staff = null;

   
    public RegisterLogin()
    {
        
        frame = new JFrame();

        toolBar = new JToolBar();
        toolBar.setLayout(new GridLayout(1, 3));
        toolBar.setFloatable(false);
        String btn[] = {"REGISTER ADMIN", "ADMIN LOGIN", "STAFF LOGIN"};

        for (int i = 0; i < btn.length; i++)
        {
            JButton button = new JButton(btn[i]); 
            button.setBackground(new Color(150,75,0));
            button.setForeground(Color.WHITE);
            toolBar.add(button);
            button.addActionListener(this);
        }


        icon_bg = new ImageIcon("images/std_image.png");
        //icon_bg.setBounds(0,0,1920, 875);

        Image image = icon_bg.getImage();

        pane = new JDesktopPane(){
            public void paintComponent(Graphics g)
            {
                Graphics2D g2d = (Graphics2D)g;
                g2d.drawImage(image, 0, 0,1500,730, frame);
            }
        };


        
        frame.add(pane, BorderLayout.CENTER);
        frame.add(toolBar, BorderLayout.NORTH);
        frame.setVisible(true);
        frame.setSize(1920, 875);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

    }


    public void actionPerformed(ActionEvent e)
    {
        if(e.getActionCommand() == "REGISTER ADMIN")
        {
            if(register_admin == null)
            {
                register_admin = new RegisterAdmin();
                pane.add(register_admin);
                register_admin = null;
            }
        }

        if(e.getActionCommand() == "ADMIN LOGIN")
        {
            if(admin_login == null)
            {
                admin_login = new AdminLogin();
                pane.add(admin_login);
                admin_login = null;
            }

        }

        if(e.getActionCommand() == "STAFF LOGIN")
        {
            if(staff_login == null)
            {
                staff_login = new StaffLogin();
                pane.add(staff_login);
                staff_login = null;
            }
        }
    }




    public void internalFrameOpened(InternalFrameEvent e) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'internalFrameOpened'");
    }

    @Override
    public void internalFrameActivated(InternalFrameEvent e) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'internalFrameActivated'");
    }


    @Override
    public void internalFrameClosing(InternalFrameEvent e) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'internalFrameClosing'");
    }


    @Override
    public void internalFrameClosed(InternalFrameEvent e) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'internalFrameClosed'");
    }


    @Override
    public void internalFrameIconified(InternalFrameEvent e) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'internalFrameIconified'");
    }


    @Override
    public void internalFrameDeiconified(InternalFrameEvent e) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'internalFrameDeiconified'");
    }

    @Override
    public void internalFrameDeactivated(InternalFrameEvent e) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'internalFrameDeactivated'");
    }

    public static void main(String[] args) {
        new RegisterLogin();
    }

}