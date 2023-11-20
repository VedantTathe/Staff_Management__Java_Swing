import java.awt.event.*;
import java.awt.*;
import javax.swing.*;
import javax.swing.*;
import javax.swing.event.InternalFrameEvent;
import javax.swing.event.InternalFrameListener;


import com.toedter.calendar.*;

public class RegisterStaff extends JInternalFrame implements ActionListener, InternalFrameListener
{
    private JLabel lbl_title, lbl_name, lbl_mobno, lbl_emailid, lbl_gender, lbl_dob, lbl_username, lbl_password;
    private JTextField txt_name, txt_mobno, txt_emailid, txt_age, txt_username; 
    private JPasswordField txt_password;
    private JComboBox combobox_gender;
    private JCheckBox cb_show_hide;
    private JButton btn_login;


    public RegisterStaff()
    {
        super("REGISTER STAFF");

        lbl_title = new JLabel("Staff Registeration");
        lbl_title.setBounds(300,20,400,70);
        lbl_title.setFont(new Font("Arial", Font.BOLD, 30));
        this.add(lbl_title);

        lbl_name = new JLabel("Full Name");
        lbl_name.setBounds(40, 50, 160, 30);
        lbl_name.setFont(new Font("Arial", Font.BOLD, 20));
        this.add(lbl_name);

        txt_name = new JTextField(30);
        txt_name.setBounds(120, 50, 160, 30);
        this.add(txt_name);

        lbl_mobno = new JLabel("Mobile Number: ");
        lbl_mobno.setBounds(40, 80, 160, 30);
        lbl_mobno.setFont(new Font("Arial", Font.BOLD, 20));
        this.add(lbl_mobno);

        txt_mobno = new JTextField(30);
        txt_mobno.setBounds(120, 80, 160, 30);
        this.add(txt_mobno);

        lbl_emailid = new JLabel("Email-ID");
        lbl_emailid.setBounds(40, 120, 160, 30);
        lbl_emailid.setFont(new Font("Arial", Font.BOLD, 20));
        this.add(lbl_emailid);

        txt_emailid = new JTextField(30);
        txt_emailid.setBounds(120, 120, 160, 30);
        this.add(txt_emailid);

        lbl_gender = new JLabel("Select Gender");
        lbl_gender.setBounds(40, 150, 160, 30);
        lbl_gender.setFont(new Font("Arial", Font.BOLD, 20));
        this.add(lbl_gender);

        String gender[] = {"Select Gender", "Male", "Female", "Other"};
        combobox_gender = new JComboBox(gender);
        combobox_gender.setBounds(120, 150, 180, 30);
        this.add(combobox_gender);

        lbl_dob = new JLabel("Select DOB");
        lbl_dob.setBounds(40, 180, 160, 30);
        lbl_dob.setFont(new Font("Arial", Font.BOLD, 20));
        this.add(lbl_dob);

        // UtilDateModel model = new UtilDateModel();
        JDateChooser dateChooser = new JDateChooser();
        dateChooser.setBounds(120, 210, 160, 30);
        this.add(dateChooser);

        lbl_username = new JLabel("Username");
        lbl_username.setBounds(40, 240, 160, 30);
        lbl_username.setFont(new Font("Arial", Font.BOLD, 20));
        this.add(lbl_username);

        txt_username = new JTextField(30);
        txt_username.setBounds(120, 240, 160, 30);
        this.add(txt_username);

        lbl_password = new JLabel("Password");
        lbl_password.setBounds(40, 270, 160, 30);
        lbl_password.setFont(new Font("Arial", Font.BOLD, 20));
        this.add(lbl_password);

        txt_password = new JPasswordField(30);
        txt_password.setBounds(120, 270, 160, 30);
        txt_password.setEchoChar('*');
        this.add(txt_password);

        cb_show_hide = new JCheckBox("Show Password");
        cb_show_hide.setBounds(120, 300, 150, 30);
        cb_show_hide.addActionListener(this);
        this.add(cb_show_hide);

        btn_login = new JButton("LOGIN");
        btn_login.setBounds(150, 330, 100, 30);
        btn_login.addActionListener(this);
        this.add(btn_login);

        this.setLayout(null);
        this.setVisible(true);
        this.setBounds(270, 80, 1000, 600);
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
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == cb_show_hide)
        {
            if (cb_show_hide.isSelected()) 
            txt_password.setEchoChar((char)0);
            else
            txt_password.setEchoChar('*');
        }
    }
}
