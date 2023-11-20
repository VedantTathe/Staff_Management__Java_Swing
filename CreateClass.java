
import java.awt.event.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Vector;
import java.awt.*;
import javax.swing.*;

public class CreateClass implements ActionListener
{
    public JFrame frame;
    public JComboBox cb_dept;
    public JComboBox cb_sem;
    public JButton btn_add_class;

    private static Connection con;
    private static PreparedStatement ps;
    

    public CreateClass()
    {
        frame = new JFrame();

        String dept[] = {"SELECT DEPARTMENT", "CIVIL", "MECHANICAL", "ELECTRICAL", "ELECTRONICS", "PLASTIC POLYMER", "PHARMACY", "CHEMICAL", 
                        "COMPUTER", "INFORMATION TECHNOLOGY"};
        cb_dept = new JComboBox(dept);

        String sem[] = {"SELECT SEMESTER", "1st SEM", "2nd SEM", "3rd SEM", "4th SEM", "5th SEM","6th SEM"};
        cb_sem = new JComboBox(sem);

        btn_add_class = new JButton("ADD CLASS");
        btn_add_class.setBackground(Color.BLUE);
        btn_add_class.setForeground(Color.WHITE);
        btn_add_class.addActionListener(this);

        frame.setLayout(new FlowLayout());

        frame.add(cb_dept);
        frame.add(cb_sem);
        frame.add(btn_add_class);

        frame.setVisible(true);
        frame.getContentPane().setBackground(new Color(255,255,204));
        frame.setBounds(400,250,500,250);
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

    @Override
    public void actionPerformed(ActionEvent e) 
    {
        try
        {
            String dept = (String) cb_dept.getSelectedItem();
            String sem = (String) cb_sem.getSelectedItem();
            String combo = dept+" ("+sem+")";
            connect();
            String sql = "insert into classes values(?,?)";
            ps = con.prepareStatement(sql);
            String staf_name = StaffLogin.staff_name;
            
            ps.setString(1, combo);
            ps.setString(2,staf_name); 

            int n = ps.executeUpdate();

            if (n == 1)
            {
                JOptionPane.showMessageDialog(frame, "Class Created Successfully");
                frame.dispose();
            }
            else
            JOptionPane.showMessageDialog(frame, "Class Not Created Successfully");


            disconnect();

        }
        catch(Exception ex) 
        {

        }
    }

   
    
    public static void main(String[] args) {
        new CreateClass();
    }
}
