import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.event.InternalFrameEvent;
import javax.swing.event.InternalFrameListener;

public class StaffLogin extends JInternalFrame implements ActionListener, InternalFrameListener
{
    private JLabel lbl_title, lbl_username, lbl_password;
    private JTextField txt_username;
    private JPasswordField txt_password;
    private JButton btn_login;
    private JCheckBox cb_show_hide;
    public static String staff_name;

    public StaffLogin()
    {
        super("STAFF LOGIN");

        lbl_title = new JLabel("STAFF LOGIN", JLabel.CENTER);
        lbl_title.setBounds(200,20,400,70);
        lbl_title.setFont(new Font("Arial", Font.BOLD, 30));
        lbl_title.setForeground(Color.RED);
        this.add(lbl_title);

        lbl_username = new JLabel("Username", JLabel.CENTER);
        lbl_username.setBounds(150, 140, 200, 30);
        lbl_username.setFont(new Font("Arial", Font.BOLD, 20));
        lbl_username.setBackground(Color.BLACK);
        this.add(lbl_username);

        txt_username = new JTextField(150);
        txt_username.setBounds(400, 140, 200, 30);
        this.add(txt_username);

        lbl_password = new JLabel("Password", JLabel.CENTER);
        lbl_password.setBounds(150, 200, 200, 30);
        lbl_password.setFont(new Font("Arial", Font.BOLD, 20));
        lbl_password.setForeground(Color.BLACK);
        this.add(lbl_password);

        txt_password = new JPasswordField(150);
        txt_password.setBounds(400, 200, 200, 30);
        txt_password.setEchoChar('*');
        this.add(txt_password);

        cb_show_hide = new JCheckBox("Show Password");
        cb_show_hide.setBounds(400, 250, 150, 25);
        cb_show_hide.addActionListener(this);
        this.add(cb_show_hide);
    
        btn_login = new JButton("Login");
        btn_login.setBounds(300, 350, 200, 30);
        btn_login.addActionListener(this);
        btn_login.setBackground(Color.RED);
        btn_login.setForeground(Color.WHITE);
        btn_login.setFont(new Font("Arial", Font.BOLD, 20));
        this.add(btn_login);

        
        this.setLayout(null);
        this.setVisible(true);
        this.setBounds(350, 150, 800, 450);
        this.setBackground(Color.CYAN);
        this.setClosable(true);
    }


    @Override
    public void internalFrameOpened(InternalFrameEvent e) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'internalFrameOpened'");
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
    public void internalFrameActivated(InternalFrameEvent e) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'internalFrameActivated'");
    }

    @Override
    public void internalFrameDeactivated(InternalFrameEvent e) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'internalFrameDeactivated'");
    }

    @Override
    public void actionPerformed(ActionEvent e) 
    {
        if(e.getSource() == cb_show_hide)
        {
            if (cb_show_hide.isSelected()) 
            txt_password.setEchoChar((char)0);
            else
            txt_password.setEchoChar('*');
        }

        if(e.getSource() == btn_login)
        {
            staff_name = txt_username.getText().trim();
            this.setVisible(false);
            new StaffHome();
        }
    }
}
