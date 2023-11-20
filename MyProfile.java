import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.regex.Pattern;
import java.awt.*;

import javax.imageio.ImageIO;
import javax.swing.*;

import com.toedter.calendar.JDateChooser;

public class MyProfile implements ActionListener
{
    private JFrame frame;
    private JLabel lbl_title, lbl_name, lbl_mobno, lbl_emailid, lbl_gender, lbl_dob, lbl_username, lbl_password, lbl_image;
    private JTextField txt_name, txt_mobno, txt_emailid, txt_age, txt_username, txt_image_name; 
    private JPasswordField txt_password;
    private JComboBox combobox_gender;
    private JCheckBox cb_show_hide;
    private JButton btn_register, btn_upload, btn_update, btn_save_changes, btn_close;
    private JDateChooser dateChooser;
    private JDesktopPane desktopPane;

    private Connection con;
    // private PreparedStatement ps;

    
public MyProfile()
{
    frame = new JFrame();
    frame.setLayout(null);

    lbl_title = new JLabel("MY PROFILE", JLabel.CENTER);
    lbl_title.setFont(new Font("Arial", Font.BOLD, 30));
    lbl_title.setBounds(300, 30, 400, 40);
    frame.add(lbl_title);

    lbl_name = new JLabel("Full Name");
    lbl_name.setFont(new Font("Arial", Font.BOLD, 20));
    lbl_name.setBounds(200, 90, 200, 30);
    frame.add(lbl_name);

    txt_name = new JTextField();
    txt_name.setBounds(400, 90, 200, 30);
    txt_name.setEditable(false);
    frame.add(txt_name);

    lbl_mobno = new JLabel("Mobile Number: ");
    lbl_mobno.setBounds(200, 140, 200, 30);
    lbl_mobno.setFont(new Font("Arial", Font.BOLD, 20));
    frame.add(lbl_mobno);

    txt_mobno = new JTextField(30);
    txt_mobno.setBounds(400, 140, 200, 30);
    txt_mobno.setEditable(false);
    frame.add(txt_mobno);

    lbl_emailid = new JLabel("Email-ID");
    lbl_emailid.setBounds(200, 190, 200, 30);
    lbl_emailid.setFont(new Font("Arial", Font.BOLD, 20));
    frame.add(lbl_emailid);

    txt_emailid = new JTextField(30);
    txt_emailid.setBounds(400, 190, 200, 30);
    txt_emailid.setEditable(false);
    frame.add(txt_emailid);

    lbl_gender = new JLabel("Select Gender");
    lbl_gender.setBounds(200, 240, 200, 30);
    lbl_gender.setFont(new Font("Arial", Font.BOLD, 20));
    frame.add(lbl_gender);

    String gender[] = {"Select Gender", "Male", "Female", "Other"};
    combobox_gender = new JComboBox(gender);
    combobox_gender.setBounds(400, 240, 200, 30);;
    combobox_gender.setEnabled(false);
    frame.add(combobox_gender);

    lbl_dob = new JLabel("Select DOB");
    lbl_dob.setBounds(200, 290, 200, 30);
    lbl_dob.setFont(new Font("Arial", Font.BOLD, 20));
    frame.add(lbl_dob);

    dateChooser = new JDateChooser();
    dateChooser.setDateFormatString("yyyy-mm-dd");
    dateChooser.setBounds(400, 290, 200, 30);
    dateChooser.setEnabled(false);
    frame.add(dateChooser);

    lbl_username = new JLabel("Username");
    lbl_username.setBounds(200, 340, 200, 30);
    lbl_username.setFont(new Font("Arial", Font.BOLD, 20));
    frame.add(lbl_username);

    txt_username = new JTextField(30);
    txt_username.setBounds(400, 340, 200, 30);
    txt_username.setEditable(false);
    frame.add(txt_username);

    lbl_password = new JLabel("Password");
    lbl_password.setBounds(200, 390, 200, 30);
    lbl_password.setFont(new Font("Arial", Font.BOLD, 20));
    frame.add(lbl_password);

    txt_password = new JPasswordField(30);
    txt_password.setBounds(400, 390, 200, 30);
    txt_password.setEditable(false);
    txt_password.setEchoChar('*');
    frame.add(txt_password);

    cb_show_hide = new JCheckBox("Show Password");
    cb_show_hide.setBounds(400, 430, 150, 20);
    cb_show_hide.addActionListener(this);
    frame.add(cb_show_hide);

    // btn_register = new JButton("REGISTER");
    // btn_register.setBounds(150, 330, 100, 30);
    // btn_register.addActionListener(this);
    // frame.add(btn_register);

    lbl_image = new JLabel();
    lbl_image.setBounds(645, 90, 200, 220);
    lbl_image.setBorder(BorderFactory.createLineBorder(Color.BLACK));
    frame.add(lbl_image);

    // desktopPane = new JDesktopPane();
    // desktopPane.setBounds(650, 90, 190, 190);
    // desktopPane.setBorder(BorderFactory.createLineBorder(Color.BLACK));
    // desktopPane.add(lbl_image);
    // frame.add(desktopPane);

    txt_image_name = new JTextField(30);
    txt_image_name.setEditable(false);
    txt_image_name.setBounds(630, 320, 230, 20);
    frame.add(txt_image_name);

    btn_upload = new JButton("Upload");
    btn_upload.setBounds(870, 320, 100, 20);
    btn_upload.setBackground(new Color( 153, 76, 0));
    btn_upload.setForeground(Color.WHITE);
    btn_upload.addActionListener(this);
    btn_upload.setEnabled(false);
    frame.add(btn_upload);

    // String btns[] = {"Update", "Save Changes", "CLOSE"};
    // for(int i=0; i<btns.length; i++)
    // {
    //     JButton btn = new JButton(btns[i]);
    //     btn.addActionListener(this);
    //    // frame.add(btn);
    // }
    btn_update = new JButton("UPDATE");
    btn_update.setBounds(400, 490, 150, 30);
    btn_update.setBackground(new Color(51, 153, 255));
    btn_update.setForeground(Color.WHITE);
    btn_update.addActionListener(this);
    frame.add(btn_update);

    btn_save_changes = new JButton("SAVE CHANGES");
    btn_save_changes.setBounds(600, 490, 150, 30);
    btn_save_changes.setBackground(new Color(0, 102, 51));
    btn_save_changes.setForeground(Color.WHITE);
    btn_save_changes.addActionListener(this);
    frame.add(btn_save_changes);

    btn_close = new JButton("CLOSE");
    btn_close.setBounds(800, 490, 150, 30);
    btn_close.setBackground(Color.RED);
    btn_close.setForeground(Color.WHITE);
    btn_close.addActionListener(this);
    frame.add(btn_close);


    frame.setVisible(true);
    frame.setSize(1080,600);
    frame.setLocation(150, 100);
    frame.getContentPane().setBackground(new Color(255, 255, 204));
    
    show();
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
			JOptionPane.showMessageDialog(frame, "connect Method: "+ex.toString());
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
			JOptionPane.showMessageDialog(frame, "disconnect Method: "+ex.toString());
		}
	}

    String un = null;
    String fname, email, gender, uname, upass;
    Blob blob_img;
    int mobno;
    Date date;

    public void show()
    {
        try
        {
            un = AdminHome.uname;
            
            connect();
            String sql = "select * from registeradmin where uname = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, un);
          
            ResultSet rs = ps.executeQuery();
            // rs.next();
            
            
            //rs.beforeFirst();
            while(rs.next())
            {
                fname = rs.getString(1);
                mobno = rs.getInt(2);
                email = rs.getString(3);
                gender = rs.getString(4);
                date = rs.getDate(5);
                uname = rs.getString(6);
                upass = rs.getString(7);
                blob_img =rs.getBlob(9);
            }
            
            if (blob_img == null) 
            {    
                txt_name.setText(fname);
                txt_mobno.setText(mobno+"");
                txt_emailid.setText(email);
                combobox_gender.setSelectedItem(gender);
                dateChooser.setDate(date);
                txt_username.setText(uname);
                txt_password.setText(upass);
            }
            else
            {
                path = "D:\\Images\\neww.jpg";
                byte bytes[] = blob_img.getBytes(1, (int)blob_img.length());
                FileOutputStream os = new FileOutputStream(path);
                os.write(bytes);
                ImageIcon icon = new ImageIcon(bytes);

                //     JOptionPane.showMessageDialog(frame, fname+mobno+email+gender);
                txt_name.setText(fname);
                txt_mobno.setText(mobno+"");
                txt_emailid.setText(email);
                combobox_gender.setSelectedItem(gender);
                dateChooser.setDate(date);
                txt_username.setText(uname);
                txt_password.setText(upass);
                lbl_image.setIcon(icon);
                txt_image_name.setText(path);
                file_path = path;
            }
            // rs.close();
            // ps.close();
            // disconnect();
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(frame, "Error: "+e);
        }
    }

    public void update()
    {
        String fn = txt_name.getText();
        String mn = txt_mobno.getText();
        String email = txt_emailid.getText();
        //String gender = combobox_gender.getSelectedItem(); 
        String un = txt_username.getText();
        String password = txt_password.getText();

        if(fn.equals("") || mn.equals("") || email.equals("") ||  gender.equals("") || un.equals("") 
            || password.equals(""))
        {
                JOptionPane.showMessageDialog(frame,"Please Enter Details !!!");
        }
        else if(!fn.equals("") && !mn.equals("") && !email.equals("") && !gender.equals("") && 
               !un.equals("") && !password.equals(""))
        {


            //Full name
            if(fn.length() < 29)
            {
                Pattern pattern = Pattern.compile(".*[A-Za-z].*");
                                    
                if(!pattern.matcher(fn).matches())
                {
                     JOptionPane.showMessageDialog(frame,"Invalid Full Name !!!");
                }

                Pattern p1 = Pattern.compile(".*[0-9].*");
                if(p1.matcher(fn).matches()) 
                {
                    JOptionPane.showMessageDialog(frame,"Invalid Full Name !!!");
                }
            }
            else
            {
                JOptionPane.showMessageDialog(frame, "Name is to Long !!!");
            }



            //Mobile No
            if(mn.length() == 10)
            {
                 Pattern p1 = Pattern.compile(".*[0-9].*");
                    if(!p1.matcher(mn).matches()) 
                    {
                        JOptionPane.showMessageDialog(frame,"Invalid Mobile Number !!!");
                    }

                    
            }
            else
            {
                JOptionPane.showMessageDialog(frame,"Invalid Mobile Number !!!");
            }


            //Email
            if(email.length() > 0)
            {
                Pattern p1 = Pattern.compile("^(.+)@(.+)$");
                if(!p1.matcher(email).matches()) 
                {
                    JOptionPane.showMessageDialog(frame,"Invalid Email !!!");
                }
            }   
            
            
            //Gender
            if(gender.isEmpty())
            {
                JOptionPane.showMessageDialog(frame,"Please select Gender !!!"); 
            }


            //Username
            if(un.length() > 0)
            {
                Pattern pattern = Pattern.compile(".*[A-Za-z].*");
                
                if(!pattern.matcher(un).matches())
                {
                    JOptionPane.showMessageDialog(frame,"Invalid Username !!!");
                }

                Pattern p1 = Pattern.compile(".*[0-9].*");
                if(p1.matcher(un).matches()) 
                {
                    JOptionPane.showMessageDialog(frame,"Invalid Username !!!");
                }
            }


            //Password
            if(password.length() >= 8 && password.length() <= 12)
            {

                Pattern p1 = Pattern.compile(".*[A-Za-z].*");
                if(!p1.matcher(password).matches())
                {
                    JOptionPane.showMessageDialog(frame,"Password Must Contain At Leat one Lower and Upper Case Letter !!!");
                }

                Pattern p3 = Pattern.compile(".*[0-9].*");
                if(!p3.matcher(password).matches()) 
                {
                    JOptionPane.showMessageDialog(frame,"Password Must Contain At Leat one 0-9 Digit !!!");
                }


                Pattern p4 = Pattern.compile(".*[!,@,#,$,%,^,&,*,(,),_].*");
                if(!p4.matcher(password).matches()) 
                {
                    JOptionPane.showMessageDialog(frame,"Password Must Contain At Leat one Special Symbol !!!");
                }

                Pattern p5 = Pattern.compile(".*[!,@,#,$,%,^,&,*,(,),_].*");
                if(p4.matcher(password).matches()) 
                {
                    try
                    {
                        connect();
            
                        String sql = "update registeradmin set fullname=?, mobno=?, emailid=?, gender=?, dob=?, uname=?, upass=?, img=? where uname = ?";
                        PreparedStatement ps = con.prepareStatement(sql);
                        ps.setString(1, txt_name.getText().trim());
                        ps.setInt(2, Integer.parseInt(txt_mobno.getText().trim()));
                        ps.setString(3, txt_emailid.getText().trim());
                        ps.setString(4, combobox_gender.getSelectedItem().toString());
            
                        java.sql.Date sqldob = new java.sql.Date(dateChooser.getDate().getTime()); 
                        ps.setDate(5, sqldob);
                        ps.setString(6, txt_username.getText().trim());
                        ps.setString(7, txt_password.getText().trim());
            
                        InputStream is = new FileInputStream(new File(file_path));
                        ps.setBlob(8, is);
                        ps.setString(9, un);
            
                        int n = ps.executeUpdate();
                        if(n == 1)
                        {
                            AdminHome.uname = txt_username.getText().trim();
                            JOptionPane.showMessageDialog(frame, "Profile Updated Successfully");
                        }
            
                        disconnect();
                    }
                    catch(Exception e)
                    {
                        JOptionPane.showMessageDialog(frame, e.getMessage());
                    }
                }

            }
            else
            {
                JOptionPane.showMessageDialog(frame,"Password Length must be 8 to 12 !!!");
            }

                 
        }

    }

    @Override
    public void actionPerformed(ActionEvent e) 
    {
        if(e.getSource() == btn_upload)
        {
            JFileChooser chooser = new JFileChooser();
            chooser.showOpenDialog(null);
            File f = chooser.getSelectedFile();

            lbl_image.setIcon(new ImageIcon(f.toString()));
            file_path = f.getAbsolutePath();

            try 
            {
                BufferedImage bi = ImageIO.read(new File(file_path));
                Image img = bi.getScaledInstance(200, 220, Image.SCALE_SMOOTH);
                ImageIcon icon = new ImageIcon(img);
                lbl_image.setIcon(icon);
                txt_image_name.setText(file_path);
            } 
            catch (IOException e1) 
            {
                JOptionPane.showMessageDialog(frame, e1.getMessage());
            }
        }

        if(e.getSource() == btn_update)
        {
            txt_name.setEditable(true);
            txt_mobno.setEditable(true);
            txt_emailid.setEditable(true);
            combobox_gender.setEnabled(true);
            dateChooser.setEnabled(true);
            txt_username.setEditable(true);
            txt_password.setEditable(true);
            btn_upload.setEnabled(true);
        }

        if(e.getSource() == btn_save_changes)
        {
            update();
        }

        if(e.getSource() == btn_close)
        {
            frame.dispose();
        }
    }

    //byte photo[] = null;
    String file_path = null;
    String path = null;

    public static void main(String[] args) {
        new MyProfile();
    }
}


