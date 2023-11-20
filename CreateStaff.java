
import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.regex.Pattern;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.toedter.calendar.JDateChooser;

public class CreateStaff extends JPanel implements ActionListener
{
   private JFrame frame;
   private JLabel lbl_title, lbl_staff_id, lbl_staff_full_name, lbl_staff_mobno, lbl_staff_address, lbl_staff_pincode, 
                      lbl_staff_reg_date, lbl_staff_gender, lbl_staff_sal, lbl_staff_qua, lbl_staff_uname;
   private JTextField txt_staff_id, txt_staff_full_name, txt_staff_mobno, txt_staff_address, txt_staff_pincode,
                      txt_staff_sal, txt_staff_qua, txt_staff_uname;
   private JComboBox cb_gender;
   private JDateChooser dateChooser;
   private JButton btn_add_staff, btn_reset, btn_close;
   private Date regdate = new Date(System.currentTimeMillis());

   private static Connection con = null;
   private static PreparedStatement ps;
   Pattern p_name,p_mob_no,p_pin_code,p_address,p_salary,p_qua,p_username;

   public CreateStaff()
    {
        frame = new JFrame();
        frame.setLayout(null);

        lbl_title = new JLabel("STAFF DETAILS", JLabel.CENTER);
        lbl_title.setBounds(300, 30, 600, 40);
        lbl_title.setFont(new Font("Arial", Font.BOLD, 20));
        frame.add(lbl_title);
        
        lbl_staff_id = new JLabel("Staff Id: ");
        lbl_staff_id.setBounds(200, 100, 150, 30);
        frame.add(lbl_staff_id);
        txt_staff_id = new JTextField(20);
        txt_staff_id.setBounds(350, 100, 150, 30);
        txt_staff_id.setEditable(false);
        frame.add(txt_staff_id);

        lbl_staff_full_name = new JLabel("Staff Full Name: ");
        lbl_staff_full_name.setBounds(200, 160, 150, 30);
        frame.add(lbl_staff_full_name);
        txt_staff_full_name = new JTextField(130);
        txt_staff_full_name.setBounds(350, 160, 150, 30);
        frame.add(txt_staff_full_name);

        lbl_staff_mobno = new JLabel("Staff Mobile No.: ");
        lbl_staff_mobno.setBounds(200, 220, 150, 30);
        frame.add(lbl_staff_mobno);
        txt_staff_mobno = new JTextField(130);
        txt_staff_mobno.setBounds(350, 220, 150, 30);
        frame.add(txt_staff_mobno);

        lbl_staff_address = new JLabel("Address: ");
        lbl_staff_address.setBounds(200, 280, 150, 30);
        frame.add(lbl_staff_address);
        txt_staff_address = new JTextField(130);
        txt_staff_address.setBounds(350, 280, 650, 30);
        frame.add(txt_staff_address);

        lbl_staff_pincode = new JLabel("Pin Code: ");
        lbl_staff_pincode.setBounds(200, 340, 150, 30);
        frame.add(lbl_staff_pincode);
        txt_staff_pincode = new JTextField(130);
        txt_staff_pincode.setBounds(350, 340, 150, 30);
        frame.add(txt_staff_pincode);

        lbl_staff_reg_date = new JLabel("Reg. Date");
        lbl_staff_reg_date.setBounds(700, 100, 150, 30);
        frame.add(lbl_staff_reg_date);
        dateChooser = new JDateChooser();
        dateChooser.setDateFormatString("dd-mm-yyyy");
        dateChooser.setBounds(850, 100, 150, 30);
        frame.add(dateChooser);

        lbl_staff_gender = new JLabel("Gender: ");
        lbl_staff_gender.setBounds(700, 160, 150, 30);
        frame.add(lbl_staff_gender);
        String gender[] = {"Select Gender", "Male", "Female", "Other"};
        cb_gender = new JComboBox(gender);
        cb_gender.setBounds(850, 160, 150, 30);
        frame.add(cb_gender);

        lbl_staff_sal = new JLabel("Salary: ");
        lbl_staff_sal.setBounds(700, 220, 150, 30);
        frame.add(lbl_staff_sal);
        txt_staff_sal = new JTextField(160);
        txt_staff_sal.setBounds(850, 220, 150, 30);
        frame.add(txt_staff_sal);

        lbl_staff_qua = new JLabel("Staff Qualification: ");
        lbl_staff_qua.setBounds(700, 340, 150, 30);
        frame.add(lbl_staff_qua);
        txt_staff_qua = new JTextField(50);
        txt_staff_qua.setBounds(850, 340, 150, 30);
        frame.add(txt_staff_qua);
        
        lbl_staff_uname= new JLabel("Staff Username: ");
        lbl_staff_uname.setBounds(700, 400, 150, 30);
        frame.add(lbl_staff_uname);
        txt_staff_uname = new JTextField(50);
        txt_staff_uname.setBounds(850, 400, 150, 30);
        frame.add(txt_staff_uname);

        btn_add_staff = new JButton("ADD STAFF");
        btn_add_staff.addActionListener(this);
        btn_add_staff.setBounds(200, 500, 165, 40);
        btn_add_staff.setBackground(new Color(0,102,51));
        btn_add_staff.setForeground(Color.white);
        frame.add(btn_add_staff);

        btn_reset = new JButton("RESET");
        btn_reset.addActionListener(this);
        btn_reset.setBackground(new Color(51,51,255));
        btn_reset.setForeground(Color.white);
        btn_reset.setBounds(465, 500, 165, 40);
        
        frame.add(btn_reset);

        btn_close = new JButton("CLOSE");
        btn_close.addActionListener(this);
        btn_close.setBounds(730, 500, 165, 40);
        btn_close.setBackground(Color.red);
        btn_close.setForeground(Color.white);
        frame.add(btn_close);

    
        frame.setVisible(true);
        frame.setSize(1200,700);
        frame.setLocation(150, 100);

        frame.getContentPane().setBackground(new Color(204,204,240));

        getStaffId();
        dateChooser.setDate(regdate);
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

    public int getStaffId()
	{
        int staff_id = 0;
		try
		{
			String sql = "select max(id) from availstaff";
			connect();
			ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			staff_id = 0;

			if(rs.next())
			{
				staff_id = rs.getInt(1);				
			}
			rs.close();
			ps.close();
			disconnect();

			if(staff_id == 0)
			staff_id =  1;
			else
			staff_id = staff_id + 1;

			txt_staff_id.setText(staff_id+"");
		}
		catch(Exception ex)
		{
			JOptionPane.showMessageDialog(frame, "getMaxBillNo: "+ex.toString());
		}
        return staff_id;
	}



    @Override
    public void actionPerformed(ActionEvent e)
    {
        
        
                    if(e.getActionCommand() == "ADD STAFF")
                    {
                        //JOptionPane.showMessageDialog(frame,"Please Enter Details !!!");
                        String sn = txt_staff_full_name.getText();
                        String mn = txt_staff_mobno.getText();
                        String addr = txt_staff_address.getText();
                        String pc = txt_staff_pincode.getText();
                        String gender = cb_gender.getSelectedItem().toString();
                        String sal = txt_staff_sal.getText();
                        String q = txt_staff_qua.getText();
                        String un = txt_staff_uname.getText();

                        if(sn.equals("") || mn.equals("") || addr.equals("") || pc.equals("") 
                            || sal.equals("") || q.equals("")  || un.equals(""))
                        {
                            JOptionPane.showMessageDialog(frame,"Please Enter Details !!!");
                        }
                        else if(!sn.equals("") && !mn.equals("") && !addr.equals("") && !pc.equals("") 
                            && !sal.equals("") && !q.equals("")  && !un.equals(""))
                        {

                            //Stff Full Name 
                                if(sn.length() < 29)
                                {
                                    p_name = Pattern.compile(".*[A-Za-z].*");
                                    
                                    if(!p_name.matcher(sn).matches())
                                    {
                                        JOptionPane.showMessageDialog(frame,"Invalid Staff Full Name !!!");
                                    }

                                    Pattern p1 = Pattern.compile(".*[0-9].*");
                                    if(p1.matcher(sn).matches()) 
                                    {
                                        JOptionPane.showMessageDialog(frame,"Invalid Staff Full Name !!!");
                                    }
                        
                                }
                                else
                                {
                                    JOptionPane.showMessageDialog(frame,"Name to Long !!!");
                                }



                                //Mobile no
                                if(mn.length() == 10)
                                {
                                    p_mob_no = Pattern.compile(".*[0-9].*");
                                    if(!p_mob_no.matcher(mn).matches()) 
                                    {
                                        JOptionPane.showMessageDialog(frame,"Invalid Mobile Number !!!");
                                    }
                                }
                                else
                                {
                                     JOptionPane.showMessageDialog(frame,"Invalid Mobile Number !!!");

                                }
                                

                    

                                //Address
                                if(addr.length() > 0)
                                {
                                    p_address = Pattern.compile(".*[!,@,#,%,^,&,*,(,),-,_,+,{,}].*");
                                    
                                    if(p_address.matcher(addr).matches())
                                    {
                                        JOptionPane.showMessageDialog(frame,"Invalid Address !!!");
                                    } 

                                }


                                //Pin Code

                                if(pc.length() == 6)
                                {
                                    p_pin_code = Pattern.compile(".*[0-9].*");
                                    
                                    if(!p_pin_code.matcher(pc).matches())
                                    {
                                        JOptionPane.showMessageDialog(frame,"Invalid Pin Code !!!");
                                    } 
                                }
                                else
                                {
                                JOptionPane.showMessageDialog(frame,"Invalid Pin Code !!!"); 
                                }
                    
                        

                                //Gender

                                if(gender.isEmpty())
                                {
                                    JOptionPane.showMessageDialog(frame,"Please select Gender !!!");
                                }


                                //Salary
                                if(sal.length() > 0)
                                {
                                    p_salary = Pattern.compile(".*[A-Za-z].*");
                                    if(p_salary.matcher(sal).matches())
                                    {
                                        JOptionPane.showMessageDialog(frame,"Invalid Salary !!!");
                                    }  

                                }

                                //Qualification
                                if(q.length() > 0)
                                {
                                    p_qua = Pattern.compile(".*[A-Za-z].*");
                                    
                                    if(!p_qua.matcher(q).matches())
                                    {
                                        JOptionPane.showMessageDialog(frame,"Invalid Qualification !!!");
                                    }

                                    Pattern p1 = Pattern.compile(".*[0-9].*");
                                    if(p1.matcher(q).matches()) 
                                    {
                                        JOptionPane.showMessageDialog(frame,"Invalid Qualification !!!");
                                    }
                                }


                                //Username
                                if(un.length() > 0)
                                {
                                    p_username = Pattern.compile(".*[A-Za-z].*");
                                    
                                    if(!p_username.matcher(un).matches())
                                    {
                                        JOptionPane.showMessageDialog(frame,"Invalid Username !!!");
                                    }

                                    Pattern p1 = Pattern.compile(".*[0-9].*");
                                    if(p1.matcher(un).matches()) 
                                    {
                                        JOptionPane.showMessageDialog(frame,"Invalid Username !!!");
                                    }
                                }


                                if(p_name.matcher(sn).matches() && p_mob_no.matcher(mn).matches() && !p_address.matcher(addr).matches() && p_pin_code.matcher(pc).matches()
                                   && !p_salary.matcher(sal).matches() && p_qua.matcher(q).matches() && p_username.matcher(un).matches())
                                {
                                    try
                                    {
                                        int staffid = getStaffId();
                                        String sname = txt_staff_full_name.getText().toUpperCase().trim();
                                        int smobno = Integer.parseInt(txt_staff_mobno.getText());
                                        String saddress = txt_staff_address.getText().trim();
                                        int pincode = Integer.parseInt(txt_staff_pincode.getText().trim());
                                        
                                        String sgender = cb_gender.getSelectedItem().toString();
                                        int salary = Integer.parseInt(txt_staff_sal.getText().trim());
                                        String qua = txt_staff_qua.getText().toUpperCase().trim();
                                        String suname = txt_staff_uname.getText().trim();
                        
                                        connect();
                                        String sql = "insert into availstaff values(?,?,?,?,?,?,?,?,?,?,?,?)";
                        
                                        ps = con.prepareStatement(sql);
                                        ps.setInt(1, staffid);
                                        ps.setString(2, sname);
                                        ps.setInt(3, smobno);
                                        ps.setString(4, saddress);
                                        ps.setInt(5, pincode);
                                        ps.setDate(6, regdate);
                                        ps.setString(7, sgender);
                                        ps.setInt(8, salary);
                                        ps.setString(9, qua);
                                        ps.setString(10, suname);
                                        ps.setString(11, suname);
                
                                        String adminname = AdminLogin.details;
                                        ps.setString(12,adminname);
                
                                  //  System.out.println(adminname);
                        
                                        int n = ps.executeUpdate();
                                        if (n == 1)
                                        {
                                            JOptionPane.showMessageDialog(frame, "Staff Added Successfully");
                                            frame.setVisible(false);
                                            frame.dispose();
                                        }
                                        else
                                        JOptionPane.showMessageDialog(frame, "Staff Not Added");
                                        ps.close();
                                        disconnect();
                                    }
                                    catch(Exception ex) 
                                    {
                                                JOptionPane.showMessageDialog(frame, ex.getMessage());
                                    }

                                }


                                


                            }

                    }

                        
                    


        if(e.getActionCommand() == "RESET")
        {
            //txt_staff_id.setText("");
            txt_staff_full_name.setText("");
            txt_staff_mobno.setText("");
            txt_staff_address.setText(""); 
            txt_staff_pincode.setText("");
            txt_staff_sal.setText("");
            txt_staff_qua.setText("");
            cb_gender.setSelectedIndex(0);
            txt_staff_uname.setText("");
        }
        if(e.getActionCommand() == "CLOSE")
        {
            frame.setVisible(false);
            frame.dispose();
        }

    }

    public static void main(String[] args) {
        new CreateStaff();
        }

}
