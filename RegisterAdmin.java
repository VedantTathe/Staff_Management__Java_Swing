import java.awt.event.*;
import java.awt.*;
import javax.swing.*;
import javax.swing.event.InternalFrameEvent;
import javax.swing.event.InternalFrameListener;
import java.sql.*;
import java.sql.Date;

import java.text.*;
import java.util.*;
import java.util.regex.Pattern;
import java.time.*;
import com.toedter.calendar.JDateChooser;




public class RegisterAdmin extends JInternalFrame implements ActionListener, InternalFrameListener
{
    private JLabel lbl_title, lbl_name, lbl_mobno, lbl_emailid, lbl_gender, lbl_dob, lbl_username, lbl_password;
    private JTextField txt_name, txt_mobno, txt_emailid, txt_age, txt_username; 
    private JPasswordField txt_password;
    private JComboBox combobox_gender;
    private JCheckBox cb_show_hide;
    private JButton btn_register;
    private JDateChooser dateChooser;

    private static Connection con;
    private static PreparedStatement ps;

    public RegisterAdmin()
    {
        super("REGISTER ADMIN", false, true, false, true);

        this.setLayout(null);
       
       
        this.add(new JLabel());
        lbl_title = new JLabel("Admin Registration", JLabel.CENTER);
        lbl_title.setFont(new Font("Arial", Font.BOLD, 30));
        lbl_title.setBounds(300, 20, 400, 50);
        lbl_title.setBackground(new Color(204, 204, 255));
        lbl_title.setForeground(Color.RED);
        this.add(lbl_title);

        lbl_name = new JLabel("Full Name");
        lbl_name.setFont(new Font("Arial", Font.BOLD, 20));
        lbl_name.setBounds(250, 100, 200, 30);
        lbl_name.setForeground(Color.BLACK);
        this.add(lbl_name);

        txt_name = new JTextField(10);
        txt_name.setBounds(500, 100, 200, 30);
        this.add(txt_name);

        lbl_mobno = new JLabel("Mobile Number: ");
        lbl_mobno.setFont(new Font("Arial", Font.BOLD, 20));
        lbl_mobno.setBounds(250, 150, 200, 30);
        lbl_mobno.setForeground(Color.BLACK);
        this.add(lbl_mobno);

        txt_mobno = new JTextField(10);
        txt_mobno.setBounds(500, 150, 200, 30);
        this.add(txt_mobno);

        lbl_emailid = new JLabel("Email-ID");
        lbl_emailid.setFont(new Font("Arial", Font.BOLD, 20));
        lbl_emailid.setBounds(250, 200, 200, 30);
        lbl_emailid.setForeground(Color.BLACK);
        this.add(lbl_emailid);

        txt_emailid = new JTextField(10);
        txt_emailid.setBounds(500, 200, 200, 30);
        this.add(txt_emailid);

        lbl_gender = new JLabel("Select Gender");
        lbl_gender.setFont(new Font("Arial", Font.BOLD, 20));
        lbl_gender.setBounds(250, 250, 200, 30);
        lbl_gender.setForeground(Color.BLACK);
        this.add(lbl_gender);

        String gender[] = {"Select Gender", "Male", "Female", "Other"};
        combobox_gender = new JComboBox(gender);
        combobox_gender.setBounds(500, 250, 200, 30);
        this.add(combobox_gender);

        lbl_dob = new JLabel("Select DOB");
        lbl_dob.setFont(new Font("Arial", Font.BOLD, 20));
        lbl_dob.setBounds(250, 300, 200, 30);
        lbl_dob.setForeground(Color.BLACK);
        this.add(lbl_dob);

        //UtilDateModel model = new UtilDateModel();
        dateChooser = new JDateChooser();
        dateChooser.setDateFormatString("dd-mm-yyyy");
        dateChooser.setBounds(500, 300, 200, 30);
        this.add(dateChooser);

        lbl_username = new JLabel("Username");
        lbl_username.setFont(new Font("Arial", Font.BOLD, 20));
        lbl_username.setBounds(250, 350, 200, 30);
        lbl_username.setForeground(Color.BLACK);
        this.add(lbl_username);

        txt_username = new JTextField(10);
        txt_username.setBounds(500, 350, 200, 30);
        this.add(txt_username);

        lbl_password = new JLabel("Password");
        lbl_password.setFont(new Font("Arial", Font.BOLD, 20));
        lbl_password.setBounds(250, 400, 200, 30);
        lbl_password.setForeground(Color.BLACK);
        this.add(lbl_password);

        txt_password = new JPasswordField(10);
        txt_password.setBounds(500, 400, 200, 30);
        txt_password.setEchoChar('*');
        this.add(txt_password);

        cb_show_hide = new JCheckBox("Show Password");
        cb_show_hide.addActionListener(this);
        cb_show_hide.setBounds(500, 435, 150, 20);
        this.add(cb_show_hide);

        btn_register = new JButton("Register");
        btn_register.addActionListener(this);
        btn_register.setBounds(400, 510, 200, 30);
        btn_register.setBackground(Color.RED);
        btn_register.setForeground(Color.WHITE);
        btn_register.setFont(new Font("Arial", Font.BOLD, 20));
        this.add(btn_register, BorderLayout.NORTH);
    
        this.setVisible(true);
        this.setSize(1000, 600);
        this.setLocation(270, 80);
        this.setBackground(Color.CYAN);
       // this.setClosable(true);
    }

   
    public void connect()
	{
		try
		{
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/staffmanagement", "root", "super");										
		}
		catch(Exception ex)
		{
			JOptionPane.showMessageDialog(this, "connect Method: "+ex.toString());
		}
	}

	public void disconnect()
	{
		try
		{
			if(!con.isClosed())
			con.close();
		}
		catch(Exception ex)
		{
			JOptionPane.showMessageDialog(this, "disconnect Method: "+ex.toString());
		}
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

        if(e.getSource() == btn_register)
        {
            
                String name = txt_name.getText();
                String mn = txt_mobno.getText();
                String email = txt_emailid.getText().trim();
                //String g = txt_gender.getText();
                //String dob = dateChooser.getDate().toString();
                String un = txt_username.getText();
                String up = txt_password.getText();

                if(name.equals("") || mn.equals("") || email.equals("")
                    || un.equals("") || up.equals(""))
                {
                        JOptionPane.showMessageDialog(this, "Please Enter Details !!!");
                       // System.out.println("Please Enter Details !!!");
                }
                else if(!name.equals("") && !mn.equals("") && !email.equals("dob")
                    && !un.equals("") && !up.equals(""))
                {

                    //Name 
                    if(name.length() < 29)
                    {
                        Pattern pattern = Pattern.compile(".*[A-Za-z].*");
                                    
                        if(!pattern.matcher(name).matches())
                        {
                            JOptionPane.showMessageDialog(this,"Invalid Full Name !!!");
                        }

                        Pattern p1 = Pattern.compile(".*[0-9].*");
                        if(p1.matcher(name).matches()) 
                        {
                            JOptionPane.showMessageDialog(this,"Invalid Full Name !!!");
                        }
                        
                    }
                    else
                    {
                        JOptionPane.showMessageDialog(this,"Full Name to Long !!!");
                    }




                    //Mobile Number
                    if(mn.length() == 10)
                    {
                        Pattern p1 = Pattern.compile(".*[0-9].*");
                        if(!p1.matcher(mn).matches()) 
                        {
                            JOptionPane.showMessageDialog(this,"Invalid Mobile Number !!!");
                        }
                    }
                    else
                    {
                        JOptionPane.showMessageDialog(this,"Invalid Mobile Number !!!");
                    }


                    //Email Id
                    if(email.length() > 0)
                    {
                        // EmailValidator ev = new EmailValidator();

                        // if(!ev.validate(email))
                        // {
                        //     JOptionPane.showMessageDialog(this,"Invalid Email");
                        // }

                        Pattern p1 = Pattern.compile("^(.+)@(.+)$");
                        if(!p1.matcher(email).matches()) 
                        {
                            JOptionPane.showMessageDialog(this,"Invalid Email !!!");
                        }
                    }



                    //Username
                    if(un.length() > 0)
                    {
                        Pattern pattern = Pattern.compile(".*[A-Za-z].*");
                                    
                        if(!pattern.matcher(un).matches())
                        {
                            JOptionPane.showMessageDialog(this,"Invalid Username !!!");
                        }

                        Pattern p1 = Pattern.compile(".*[0-9].*");
                        if(p1.matcher(un).matches()) 
                        {
                            JOptionPane.showMessageDialog(this,"Invalid Username !!!");
                        }
                    }



                    //Password
                    if(up.length() >= 8 && up.length() <= 12)
                    {

                        Pattern p1 = Pattern.compile(".*[A-Za-z].*");
                        if(!p1.matcher(up).matches())
                        {
                            JOptionPane.showMessageDialog(this,"Password Must Contain At Leat one Lower and Upper Case Letter !!!");
                        }

                        Pattern p3 = Pattern.compile(".*[0-9].*");
                        if(!p3.matcher(up).matches()) 
                        {
                            JOptionPane.showMessageDialog(this,"Password Must Contain At Leat one 0-9 Digit !!!");
                        }


                        Pattern p4 = Pattern.compile(".*[!,@,#,$,%,^,&,*,(,),_].*");
                        if(!p4.matcher(up).matches()) 
                        {
                            JOptionPane.showMessageDialog(this,"Password Must Contain At Leat one Special Symbol !!!");
                        }

                        Pattern p5 = Pattern.compile(".*[!,@,#,$,%,^,&,*,(,),_].*");
                        if(p4.matcher(up).matches()) 
                        {
                         try
                         {
                            String fname = txt_name.getText().toUpperCase().trim();
                            int mno = Integer.parseInt(txt_mobno.getText());
                            String eid = txt_emailid.getText().trim();
                            String gender = combobox_gender.getSelectedItem().toString();
                            java.sql.Date sqlStartDate = new java.sql.Date(dateChooser.getDate().getTime()); 
                            String uname = txt_username.getText();
                            String upass = txt_password.getText();
                            java.sql.Date regdate = new java.sql.Date(System.currentTimeMillis());
                           // String n ="";
             
                            connect();
                            String sql = "insert into registeradmin values(?, ?, ?, ?, ?, ?, ?, ?, ?)";
                            ps = con.prepareStatement(sql);
             
                            ps.setString(1, fname);
                            ps.setInt(2, mno);
                            ps.setString(3, eid);
                            ps.setString(4, gender);
                            ps.setDate(5, sqlStartDate);
                            ps.setString(6, uname);
                            ps.setString(7, upass);
                            ps.setDate(8, regdate);
             
                            Blob blob = null;
                            ps.setBlob(9, blob);
             
                            int n = ps.executeUpdate();
                            ps.close();
                            disconnect();
             
                            if (n == 1)
                            {
                             JOptionPane.showMessageDialog(this, "Registeration Successfully Done");
                             this.dispose();
                            }
                            else
                            JOptionPane.showMessageDialog(this, "Registration Failed");
                             
                        }
                        catch(Exception el)
                        {
                            JOptionPane.showMessageDialog(this,el);
                        }
                        }


                    }
                    else
                    {
                        JOptionPane.showMessageDialog(this,"Invalid Password : Length must between 8 to 12 digits !!!");
                    }


                    
                 }

                 
  
              
                       

        }
    
        
    }

}
