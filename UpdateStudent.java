
import java.awt.event.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Vector;
import java.util.regex.Pattern;
import java.awt.*;
import javax.swing.*;

public class UpdateStudent implements ActionListener 
{
    private JFrame frame;
    private JLabel lbl_title,lbl_stud_id,lbl_stud_name,lbl_stud_mob_no,lbl_stud_email,lbl_stud_address,lbl_stud_gender,
                   lbl_stud_religion, lbl_stud_caste, lbl_stud_blood_group;


    private JTextField txt_title,txt_stud_id,txt_stud_name,txt_stud_mob_no,txt_stud_email,txt_stud_address,
                   txt_stud_religion, txt_stud_caste;

    private JComboBox cb_stud_gender,cb_stud_blood_group;

    private JButton btn_update,btn_save_changes;

    String stud_id, stud_name, stud_email,stud_mob_no, stud_address, stud_gender, stud_religion, stud_caste, stud_blood_group;

    private  Connection con;
    private  Statement statement;
    private  PreparedStatement ps;
    public   ResultSet rs;
    Pattern p_name1,p_name2,p_mob_no,p_email,p_address,p_gender,p_blood_group,p_religion,p_caste;

    //Date stud_dob,stud_admission_date;



    public UpdateStudent()
    {
        
        stud_id = StaffHome.stud_id;
        stud_name = StaffHome.stud_name;
        stud_mob_no = StaffHome.stud_mob_no;
        stud_email = StaffHome.stud_email;
        stud_address = StaffHome.stud_address;
        //stud_admission_date = StaffHome.stud_admission_date;
        //stud_dob = StaffHome.stud_dob;
        stud_gender = StaffHome.stud_gender;
        stud_religion = StaffHome.stud_religion;
        stud_caste = StaffHome.stud_caste;
        stud_blood_group = StaffHome.stud_blood_group;

        //JOptionPane.showMessageDialog(frame,"id"+stud_id);



        frame = new JFrame();
        frame.setLayout(null);

        lbl_title = new JLabel("UPDATE STUDENT");
        lbl_title.setFont(new Font("Arial", Font.BOLD, 30));
        lbl_title.setBounds(330, 65, 400, 50);
        frame.add(lbl_title);

        lbl_stud_id = new JLabel("ID");
        lbl_stud_id.setFont(new Font("Arial", Font.BOLD, 20));
        lbl_stud_id.setBounds(115, 170, 400, 50);
        frame.add(lbl_stud_id);

        txt_stud_id = new JTextField();
        txt_stud_id.setBounds(250, 180, 200, 32);
        txt_stud_id.setText(stud_id);
        txt_stud_id.setEditable(false);
        frame.add(txt_stud_id);

        lbl_stud_name = new JLabel("NAME");
        lbl_stud_name.setFont(new Font("Arial", Font.BOLD, 20));
        lbl_stud_name.setBounds(500, 170, 400, 50);
        frame.add(lbl_stud_name);

        txt_stud_name = new JTextField();
        txt_stud_name.setBounds(600, 180, 200, 32);
        txt_stud_name.setText(stud_name);
        txt_stud_name.setEditable(false);
        frame.add(txt_stud_name);

        lbl_stud_mob_no = new JLabel("MOBILE NO");
        lbl_stud_mob_no.setFont(new Font("Arial", Font.BOLD, 20));
        lbl_stud_mob_no.setBounds(115, 235, 400, 50);
        frame.add(lbl_stud_mob_no);

        txt_stud_mob_no = new JTextField();
        txt_stud_mob_no.setBounds(250, 245, 200, 32);
        txt_stud_mob_no.setText(stud_mob_no);
        txt_stud_mob_no.setEditable(false);
        frame.add(txt_stud_mob_no);


        lbl_stud_email = new JLabel("EMAIL");
        lbl_stud_email.setFont(new Font("Arial", Font.BOLD, 20));
        lbl_stud_email.setBounds(500, 235, 400, 50);
        frame.add(lbl_stud_email);

        txt_stud_email = new JTextField();
        txt_stud_email.setBounds(600, 245, 200, 32);
        txt_stud_email.setText(stud_email);
        txt_stud_email.setEditable(false);
        frame.add(txt_stud_email);


        lbl_stud_address = new JLabel("ADDRESS");
        lbl_stud_address.setFont(new Font("Arial", Font.BOLD, 20));
        lbl_stud_address.setBounds(115, 295, 400, 50);
        frame.add(lbl_stud_address);

        txt_stud_address = new JTextField();
        txt_stud_address.setBounds(250, 305, 550, 32);
        txt_stud_address.setText(stud_address);
        txt_stud_address.setEditable(false);
        frame.add(txt_stud_address);


        lbl_stud_gender = new JLabel("GENDER");
        lbl_stud_gender.setFont(new Font("Arial", Font.BOLD, 20));
        lbl_stud_gender.setBounds(115, 355, 400, 50);
        frame.add(lbl_stud_gender);

        String gender[] = {"Select Gender", "Male", "Female", "Other"};
        cb_stud_gender = new JComboBox(gender);
        cb_stud_gender.setSelectedItem(stud_gender);
        cb_stud_gender.setEditable(false);
        cb_stud_gender.setBounds(250, 365, 190, 30);
        frame.add(cb_stud_gender);


        lbl_stud_blood_group = new JLabel("BLOOD GROUP");
        lbl_stud_blood_group.setFont(new Font("Arial", Font.BOLD, 20));
        lbl_stud_blood_group.setBounds(500, 355, 400, 50);
        frame.add(lbl_stud_blood_group);

        String bg[] = {"Select Blood Group", "A+", "A-", "AB+", "AB-", "B+", "B-", "O+", "O-"};
        cb_stud_blood_group = new JComboBox(bg);
        cb_stud_blood_group.setSelectedItem(stud_blood_group);
        cb_stud_blood_group.setEditable(false);
        cb_stud_blood_group.setBounds(660, 365, 140, 30);
        frame.add(cb_stud_blood_group);


        lbl_stud_religion = new JLabel("RELIGION");
        lbl_stud_religion.setFont(new Font("Arial", Font.BOLD, 20));
        lbl_stud_religion.setBounds(115, 415, 400, 50);
        frame.add(lbl_stud_religion);

        txt_stud_religion = new JTextField();
        txt_stud_religion.setBounds(250, 425, 200, 32);
        txt_stud_religion.setText(stud_religion);
        txt_stud_religion.setEditable(false);
        frame.add(txt_stud_religion);


        lbl_stud_caste = new JLabel("CASTE");
        lbl_stud_caste.setFont(new Font("Arial", Font.BOLD, 20));
        lbl_stud_caste.setBounds(500, 415, 400, 50);
        frame.add(lbl_stud_caste);

        txt_stud_caste = new JTextField();
        txt_stud_caste.setBounds(600, 425, 200, 32);
        txt_stud_caste.setText(stud_caste);
        txt_stud_caste.setEditable(false);
        frame.add(txt_stud_caste);

        btn_update = new JButton("UPDATE");
        btn_update.setBackground(Color.BLUE);
        btn_update.setForeground(Color.WHITE);
        btn_update.setFont(new Font("Arial", Font.BOLD, 20));
        btn_update.addActionListener(this);
        btn_update.setBounds(210,540,250,40);
        frame.add(btn_update);



        btn_save_changes = new JButton("SAVE CHANGES");
        btn_save_changes.setBackground(Color.GREEN);
        btn_save_changes.setForeground(Color.WHITE);
        btn_save_changes.setFont(new Font("Arial", Font.BOLD, 20));
        btn_save_changes.addActionListener(this);
        btn_save_changes.setBounds(500,540,250,40);
        frame.add(btn_save_changes);


        //frame.setSize(900,750);
        
        frame.setBounds(350,30,900,750);
        frame.setVisible(true);
        frame.getContentPane().setBackground(new Color(204, 255, 204));
        
    }


    @Override
    public void actionPerformed(ActionEvent e)
    {
        if(e.getSource() == btn_update)
        {
            //txt_stud_id.setEditable(true);
            txt_stud_name.setEditable(true);
            txt_stud_mob_no.setEditable(true);
            txt_stud_email.setEditable(true);
            txt_stud_address.setEditable(true);
            cb_stud_gender.setEditable(true);
            cb_stud_blood_group.setEditable(true);
            txt_stud_religion.setEditable(true);
            txt_stud_caste.setEditable(true);

            JOptionPane.showMessageDialog(frame,"Now you can Update !!!");
        }


        if(e.getSource() == btn_save_changes)
        {
            String s_id = txt_stud_id.getText();
            String s_name = txt_stud_name.getText();
            String s_mob_no = txt_stud_mob_no.getText();
            String s_email = txt_stud_email.getText();
            String s_address = txt_stud_address.getText();
            String s_gender = cb_stud_gender.getSelectedItem().toString();
            String s_blood_group = cb_stud_blood_group.getSelectedItem().toString();
            String s_religion = txt_stud_religion.getText();
            String s_caste = txt_stud_caste.getText();

            if(s_name.equals("") || s_mob_no.equals("") || s_email.equals("") || 
                s_address.equals("") || s_gender.equals("") || s_blood_group.equals("") || 
                s_religion.equals("") || s_caste.equals(""))
            {
                JOptionPane.showMessageDialog(frame,"Please enter Details !!!");
            }
            else
            {
                    //name
                    if(s_name.length() < 29)
                    {
                        p_name1 = Pattern.compile(".*[A-Za-z].*");
                                            
                        if(!p_name1.matcher(s_name).matches())
                        {
                            JOptionPane.showMessageDialog(frame,"Invalid Name !!!");
                        }

                         p_name2 = Pattern.compile(".*[0-9].*");
                        if(p_name2.matcher(s_name).matches()) 
                        {
                            JOptionPane.showMessageDialog(frame,"Invalid Name !!!");
                        }
                    }
                    else
                    {
                        JOptionPane.showMessageDialog(frame, "Name is to Long !!!");
                    }



                    //s_mobile no
                    if(s_mob_no.length() == 10)
                    {
                            p_mob_no = Pattern.compile(".*[0-9].*");
                            if(!p_mob_no.matcher(s_mob_no).matches()) 
                            {
                                JOptionPane.showMessageDialog(frame,"Invalid Mobile Number !!!");
                            }
        
                            
                    }
                    else
                    {
                        JOptionPane.showMessageDialog(frame,"Invalid Mobile Number !!!");
                    }

                    
                    //Email
                    if(s_email.length() > 0)
                    {
                        p_email = Pattern.compile("^(.+)@(.+)$");
                        if(!p_email.matcher(s_email).matches()) 
                        {
                            JOptionPane.showMessageDialog(frame,"Invalid Email !!!");
                        }
                    } 


                    //Address
                    if(s_address.length() > 0)
                    {
                        p_address = Pattern.compile(".*[!,@,#,%,^,&,*,(,),-,_,+,{,}].*");
                        
                        if(p_address.matcher(s_address).matches())
                        {
                            JOptionPane.showMessageDialog(frame,"Invalid Address !!!");
                        } 

                    }


                    //Gender
                    if(s_gender.equals(""))
                    {
                        JOptionPane.showMessageDialog(frame,"Please select Gender !!!");
                    }

                    //Blood Group
                    if(s_blood_group.equals(""))
                    {
                        JOptionPane.showMessageDialog(frame,"Please select Blood Group !!!");
                    }



                     //Religion
                    if(s_religion.length() > 0)
                    {
                        p_religion = Pattern.compile(".*[A-Za-z].*");
                                        
                        if(!p_religion.matcher(s_religion).matches())
                        {
                            JOptionPane.showMessageDialog(frame,"Invalid Religion !!!");
                        }
        
                        Pattern p1 = Pattern.compile(".*[0-9].*");
                        if(p1.matcher(s_religion).matches()) 
                        {
                            JOptionPane.showMessageDialog(frame,"Invalid Religion !!!");
                        }
                    }


                    //Caste
                    if(s_caste.length() > 0)
                    {
                        p_caste = Pattern.compile(".*[A-Za-z].*");
                                        
                        if(!p_caste.matcher(s_caste).matches())
                        {
                            JOptionPane.showMessageDialog(frame,"Invalid Caste !!!");
                        }
        
                        Pattern p1 = Pattern.compile(".*[0-9].*");
                        if(p1.matcher(s_caste).matches()) 
                        {
                            JOptionPane.showMessageDialog(frame,"Invalid Caste !!!");
                        }
                    }



                    //save Changes
                    if(p_name1.matcher(s_name).matches() && p_mob_no.matcher(s_mob_no).matches() && p_email.matcher(s_email).matches() && 
                        !p_address.matcher(s_address).matches() && p_religion.matcher(s_religion).matches() && p_caste.matcher(s_caste).matches())
                    {
                        String id = txt_stud_id.getText().trim().toUpperCase();
                        String name = txt_stud_name.getText().trim().toUpperCase();
                        String mob_no = txt_stud_mob_no.getText().trim();
                        String email = txt_stud_email.getText().trim();
                        String address = txt_stud_address.getText().trim();
                        String gender = cb_stud_gender.getSelectedItem().toString();
                        String blood_group = cb_stud_blood_group.getSelectedItem().toString();
                        String religion = txt_stud_religion.getText().trim();
                        String caste = txt_stud_caste.getText().trim();
            
                        try
                        {
                            connect();
            
                            String query = "update students set STUDENT_NAME = ?, MOBILE_NUMBER = ?, EMAIL = ?, ADDRESS = ?, GENDER = ?,  BLOOD_GROUP = ?, RELIGION = ?, CASTE = ? where ID = ?";
                            ps = con.prepareStatement(query);
                            ps.setString(1,name);
                            ps.setString(2, mob_no);
                            ps.setString(3,email);
                            ps.setString(4, address);
                            ps.setString(5,gender);
                            ps.setString(6, blood_group);
                            ps.setString(7,religion);
                            ps.setString(8,caste);
                            ps.setString(9,id);
            
                            int n = ps.executeUpdate();
            
                            if(n == 1)
                            {
                                JOptionPane.showMessageDialog(frame,"Student Updated !!!");
                                frame.dispose();
                            }
                            else
                            {
                                JOptionPane.showConfirmDialog(frame,"Student Not Updated !!!");
                            }
            
                            disconnect();
            
                        }
                        catch(Exception exception)
                        {
                            JOptionPane.showMessageDialog(frame,"Exception : "+exception);
                        }
                    }

            }
        }
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


    public static void main(String[] args) 
    {
       new UpdateStudent(); 
    }
}