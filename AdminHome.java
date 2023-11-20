
import java.awt.*;
import java.awt.event.*;
import java.beans.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

public class AdminHome extends JFrame implements ActionListener
{

    private JSplitPane splitPane;
    private JPanel panel_left,panel_right, panel_right_inner;
    private JButton btn_create_staff,btn_view_staff, btn_my_profile, btn_delete_staff, btn_refresh;
    private JLabel lbl_title;
    private JTable table;
    private JScrollPane scrollPane;

    private Connection con;
    private PreparedStatement ps;
    public static String uname = null;
    
    public AdminHome()
    {
        uname = AdminLogin.details;
        panel_left = new JPanel();
        panel_left.setBackground(new Color(255, 204, 153));
        panel_left.setLayout(new GridLayout(13, 1, 0, 30));

        panel_left.add(new JLabel("Manage", JLabel.CENTER));
        btn_create_staff = new JButton("(+)Create staff");
        btn_create_staff.setBackground(new Color(204, 229, 255));
        btn_create_staff.setForeground(Color.DARK_GRAY);
        btn_create_staff.addActionListener(this);

        btn_view_staff = new JButton("View staff");
        btn_view_staff.setBackground(new Color(204, 229, 255));
        btn_view_staff.setForeground(Color.DARK_GRAY);
        btn_view_staff.addActionListener(this);

        btn_my_profile = new JButton("My profile");
        btn_my_profile.setBackground(new Color(204, 229, 255));
        btn_my_profile.setForeground(Color.DARK_GRAY);
        btn_my_profile.addActionListener(this);


        panel_left.add(btn_create_staff);
        panel_left.add(btn_view_staff);
        // panel_left.add(btn_delete_staff);
        panel_left.add(new JLabel(""));
        panel_left.add(btn_my_profile);

        panel_right = new JPanel();
        panel_right.setLayout(null);
        panel_right.setBackground(new Color(204, 255, 204));
        lbl_title = new JLabel("Available Staffs", JLabel.CENTER);
        lbl_title.setFont(new Font("Arial", Font.BOLD, 40));
        lbl_title.setBounds(400, 20, 600, 60);
        lbl_title.setForeground(Color.BLACK);
        panel_right.add(lbl_title);


        btn_refresh = new JButton("REFRESH");
        btn_refresh.addActionListener(this);
        btn_refresh.setBackground(Color.GREEN);
        btn_refresh.setForeground(Color.WHITE);
        btn_refresh.setBounds(1100, 660, 150, 30);
        btn_refresh.setVisible(false);
        panel_right.add(btn_refresh);

        btn_delete_staff = new JButton("Delete staff");
        btn_delete_staff.addActionListener(this);
        btn_delete_staff.setBackground(Color.RED);
        btn_delete_staff.setForeground(Color.WHITE);
        btn_delete_staff.setBounds(150, 660, 150, 30);
        btn_delete_staff.setVisible(false);
        panel_right.add(btn_delete_staff);
        
        // panel_right_inner = new JPanel();
        // panel_right_inner.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        // panel_right_inner.setLayout(new BorderLayout());
        // panel_right_inner.setBounds(60, 90, 1300, 700);
        // panel_right.add(panel_right_inner);



        splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,panel_left,panel_right);
        splitPane.setDividerLocation(200);
        splitPane.setBackground(Color.BLUE);
        splitPane.setDividerSize(4);
       // splitPane.setOneTouchExpandable(true);


        this.add(splitPane,BorderLayout.CENTER);
        this.setVisible(true);
        this.setSize(1920, 1080);

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
    public void actionPerformed(ActionEvent e) 
    {
        if(e.getSource() == btn_create_staff)
        {
            new CreateStaff();
        }

        if(e.getSource() == btn_my_profile)
        {
            new MyProfile();
        }

        if(e.getSource() == btn_delete_staff)
        {
            try
            {
                int staff_id = Integer.parseInt((String) model.getValueAt(table.getSelectedRow(), 0));
                System.out.println(staff_id);
                if(staff_id < 0)
                {
                    JOptionPane.showMessageDialog(this, "Please Select a staff first to delete");
                }
                else
                {
                    model.removeRow(table.getSelectedRow());
                    connect();
                    String sql = "delete from availstaff where ID = ?";
                    ps = con.prepareStatement(sql);
                    ps.setInt(1, staff_id);

                    int n = ps.executeUpdate();
                    if(n == 1)
                    {
                        staff_id = 0;
                        JOptionPane.showMessageDialog(this, "Staff deleted successfully");
                    }

                    disconnect();
                }
            }
            catch(Exception ex)
            {
                JOptionPane.showMessageDialog(this, "Error :"+ex.getMessage());
            }
        }

        if(e.getSource() == btn_view_staff)
        {
            availStaff();
        }
        if(e.getSource() == btn_refresh)
        {
            availStaff();
        }



    }
    DefaultTableModel model;

    public void availStaff()
    {

        table = new JTable();
        panel_right.add(new JLabel("staff"));
        connect();
        try
        {
            String sql = "select * from availstaff where adminname = ?";
            PreparedStatement st = con.prepareStatement(sql);
        
            st.setString(1, uname);
            ResultSet rs = st.executeQuery();
            ResultSetMetaData rsmd = rs.getMetaData();
            model = (DefaultTableModel) table.getModel();

            int cols = rsmd.getColumnCount() - 3;
            String colname[] = new String[cols];

            for (int i = 0; i < cols; i++)
            {
                colname[i] = rsmd.getColumnName(i+1);
            }

            model.setColumnIdentifiers(colname);
            JTableHeader tableHeader = table.getTableHeader();
            tableHeader.setBackground(new Color(255,204,229));
            tableHeader.setForeground(new Color(102,0,51));
            tableHeader.setFont(new Font("Arial",Font.BOLD,12));

            String id, name, mobno, address, pincode, regdate, gender, salary, qualification;
            while(rs.next())
            {
                id = rs.getString(1);
                name = rs.getString(2);
                mobno = rs.getString(3);
                address = rs.getString(4);
                pincode = rs.getString(5);
                regdate = rs.getString(6);
                gender = rs.getString(7);
                salary = rs.getString(8);
                qualification = rs.getString(9);

                String row[] = {id,name,mobno,address,pincode, regdate, gender, salary, qualification};
                model.addRow(row);
                //staff_id = (int) model.getValueAt(table.getSelectedRow(), 1);
            }
            disconnect();

        }
        catch(Exception ex)
        {
            JOptionPane.showMessageDialog(this, ex.getMessage());
        }
        scrollPane = new JScrollPane(table);
        scrollPane.setBounds(150, 150, 1100, 500);
        panel_right.add(scrollPane);
        btn_refresh.setVisible(true);
        btn_delete_staff.setVisible(true);
    }


    public static void main(String[] args) {
        new AdminHome();
    }

}
