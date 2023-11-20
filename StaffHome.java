

import java.awt.*;
import java.awt.event.*;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import com.toedter.calendar.JDateChooser;

import java.util.*;
import java.util.regex.Pattern;

public class StaffHome extends JFrame implements ActionListener
{
   
    private JSplitPane splitPane;
    private JPanel panel_left,panel_right, panel_top, panel_center, panel_bottom, panel_right_inner,panel_add_student,
                panel_view_students,panel_update_student,panel_delete_student,panel_attendance,panel_atten_add,panel_atten_update,
                panel_atten_view,panel_inner_add_attendance;


    private JButton btn_create_class,btn_my_profile, btn_delete_class, btn_available_classes,btn_add_stud,btn_stud_check, btn_refresh,
                    btn_submit_today_atten_date,btn_submit_attendance,btn_view_referesh_stud;

    private JLabel lbl_title, lbl_class_name, lbl_stud_id,lbl_stud_name,lbl_stud_mob_no,lbl_stud_dob,lbl_stud_email,lbl_stud_address,
                   lbl_stud_admission_date,lbl_stud_gender,lbl_stud_religion,lbl_stud_caste,lbl_stud_blood_group,
                   lbl_enter_stud_id,lbl_enter_today_atten_date,lbl_stud_atten_id;

    private JTextField txt_class_name, txt_stud_id,txt_stud_name,txt_stud_mob_no,txt_stud_email,txt_stud_address,txt_stud_religion,
                  txt_stud_caste,txt_enter_stud_id,txt_enter_today_atten_date;

    private JRadioButton rb_present,rb_absent;
    
    private JDateChooser dc_stud_dob,dc_stud_admission_date,dateChooser;

    private JComboBox cb_stud_gender,cb_stud_blood_group;

    private JTabbedPane tab_main,tab_sub;
    private JMenuBar menuBar_attendance;
    private JMenu menu_addAttendance,menu_updateAttendance,menu_viewAttendance;

    private JList<String> lst;
    private Vector<String> list_data;
    private DefaultListModel<String> lst_model;
    private JScrollPane scrollPane;

    private static Connection con;
    private static Statement statement;
    private static PreparedStatement ps;
    public static ResultSet rs;


    Pattern p_id,p_name,p_mob_no,p_address,p_email,p_religion,p_caste;

    private String cname, combo="",sq;
    private int cname_index,atten_result;
    String sta_name = null;

    public String std_id_for_update_student = null;

    public static String stud_id, stud_name, stud_email,stud_mob_no, stud_address, stud_gender, stud_religion, stud_caste, stud_blood_group;
    public static Date stud_dob,stud_admission_date;

    public StaffHome()
    {
        
        sta_name = StaffLogin.staff_name;

        panel_add_student = new JPanel();
       // panel_delete_student = new JPanel();
        
        panel_view_students = new JPanel();
        
        panel_update_student = new JPanel();
   
        panel_attendance = new JPanel();
        panel_attendance.setLayout(new BorderLayout());
        

        panel_atten_add = new JPanel();
        panel_atten_update = new JPanel();
        panel_atten_view = new JPanel();



        this.setLayout(new BorderLayout());
        panel_left = new JPanel();
        panel_left.setBackground(new Color(255,204,153));
        panel_left.setLayout(new BorderLayout());


        panel_top = new JPanel();
        panel_top.setBackground(new Color(255,204,153));
        panel_top.setLayout(new GridLayout(4, 1, 0, 20));

        panel_bottom = new JPanel();
        panel_bottom.setBackground(new Color(255,204,153));
        panel_bottom.setLayout(new GridLayout(3, 1, 15, 15));
        
        panel_center = new JPanel();
         panel_center.setBackground(new Color(255,204,153));
        panel_center.setLayout(new BorderLayout());

        btn_create_class = new JButton("(+)Create Class");
        btn_create_class.setBackground(new Color(204,229,255));
        btn_create_class.setForeground(Color.DARK_GRAY);
        btn_create_class.addActionListener(this);

        btn_available_classes = new JButton("      Available Classes      ");
        btn_available_classes.setBackground(new Color(0,153,0));
        btn_available_classes.setForeground(Color.WHITE);
        btn_available_classes.addActionListener(this);

        btn_my_profile = new JButton("My profile");
        btn_my_profile.setBackground(new Color(204,229,255));
        btn_my_profile.setForeground(Color.DARK_GRAY);
        btn_my_profile.addActionListener(this);

        btn_delete_class = new JButton("Delete Class");
        btn_delete_class.setBackground(new Color(204,229,255));
        btn_delete_class.setForeground(Color.DARK_GRAY);
        btn_delete_class.addActionListener(this);

        panel_top.add(new JLabel("Manage", JLabel.CENTER));
        panel_top.add(btn_create_class);
        panel_top.add(btn_delete_class);
        panel_top.add(btn_available_classes);

        btn_refresh = new JButton("Refresh");
        btn_refresh.setBackground(new Color(204,229,255));
        btn_refresh.setForeground(Color.DARK_GRAY);
        btn_refresh.addActionListener(this);
        panel_bottom.add(btn_refresh);
       // panel_bottom.add(btn_my_profile);
        
        panel_left.add(panel_top, BorderLayout.NORTH);
        panel_left.add(panel_center, BorderLayout.CENTER);
        panel_left.add(panel_bottom, BorderLayout.SOUTH);
        
        panel_right = new JPanel();
        panel_right.setBackground(new Color(204,255,204));
        panel_right.setLayout(new BorderLayout());
        panel_right_inner = new JPanel();
        panel_right_inner.setLayout(new BorderLayout());
        panel_right_inner.setVisible(false);
        panel_right.add(panel_right_inner, BorderLayout.CENTER);

        
        lbl_title = new JLabel("MANAGE STUDENTS", JLabel.CENTER);
        lbl_title.setFont(new Font("Arial", Font.BOLD, 30));
    
        panel_right.add(lbl_title, BorderLayout.NORTH);
        

        splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,panel_left,panel_right);
        splitPane.setDividerLocation(200);
        splitPane.setDividerSize(4);


        /* After click on class */

        tab_main = new JTabbedPane(JTabbedPane.TOP);
        tab_sub = new JTabbedPane(JTabbedPane.RIGHT);
        tab_main.add("ADD STUDENTS", panel_add_student);

        tab_main.add("VIEW STUDENTS",panel_view_students);
        tab_main.add("UPDATE STUDENT",panel_update_student);

        tab_main.setBackground(new Color(204,204,255));
        tab_main.setForeground(Color.DARK_GRAY);
        //tab_main.add("DELETE STUDENTS", panel_delete_student);
        //tab_main.add("ATTENDENCE",panel_attendance);

        tab_main.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                if(tab_main.getSelectedIndex()==0)
                {
                   // JOptionPane.showMessageDialog(this,"Add Student");
                  // System.out.println("Add Student");
                  tab_main.setBackground(Color.ORANGE);
                  tab_main.setForeground(Color.DARK_GRAY);
                }

                if(tab_main.getSelectedIndex()==1)
                {
                    tab_main.setBackground(Color.ORANGE);
                    tab_main.setForeground(Color.DARK_GRAY);
                    viewStudents();
                }

                // if(tab_main.getSelectedIndex()==2)
                // {
                //     // viewStudents();
                // }

                // if(tab_main.getSelectedIndex()==3)
                // {
                //     viewStudents();
                // }

            }
        });



       // tab_sub.add("ADD ATTENDENCE",panel_atten_add);
        //tab_sub.add("UPDATE ATTENDENCE",panel_atten_update);
        //tab_sub.add("VIEW ATTENDENCE   ",panel_atten_view);
        panel_right_inner.add(tab_main,BorderLayout.CENTER);
        //panel_attendance.add(tab_sub, BorderLayout.CENTER);
      



        //add students 
        panel_add_student.setLayout(null);
        panel_add_student.setBackground(new Color(204,255,255));

        lbl_title = new JLabel("ADD STUDENT", JLabel.CENTER);
        lbl_title.setBounds(300, 30, 600, 40);
        lbl_title.setFont(new Font("Arial", Font.BOLD, 20));
        panel_add_student.add(lbl_title);
        
        lbl_class_name = new JLabel("Class Name :");
        lbl_class_name.setBounds(200, 100, 150, 30);
        panel_add_student.add(lbl_class_name);
        txt_class_name = new JTextField();
        txt_class_name.setEditable(false);
        txt_class_name.setBounds(350, 100, 150, 30);
        panel_add_student.add(txt_class_name);

        lbl_stud_id = new JLabel("Id Code : ");
        lbl_stud_id.setBounds(200, 160, 150, 30);
        panel_add_student.add(lbl_stud_id);
        txt_stud_id = new JTextField(20);
        txt_stud_id.setBounds(350, 160, 150, 30);
        panel_add_student.add(txt_stud_id);

        lbl_stud_name = new JLabel("Name: ");
        lbl_stud_name.setBounds(200, 220, 150, 30);
        panel_add_student.add(lbl_stud_name);
        txt_stud_name = new JTextField(130);
        txt_stud_name.setBounds(350, 220, 150, 30);
        panel_add_student.add(txt_stud_name);

        lbl_stud_mob_no = new JLabel("Mobile No.: ");
        lbl_stud_mob_no.setBounds(200, 280, 150, 30);
        panel_add_student.add(lbl_stud_mob_no);
        txt_stud_mob_no = new JTextField();
        txt_stud_mob_no.setBounds(350, 280, 150, 30);
        panel_add_student.add(txt_stud_mob_no);

        lbl_stud_dob = new JLabel("DOB :");
        lbl_stud_dob.setBounds(700, 160, 150, 30);
        panel_add_student.add(lbl_stud_dob);
        dc_stud_dob = new JDateChooser();
        dc_stud_dob.setDateFormatString("dd-mm-yyyy");
        dc_stud_dob.setBounds(850, 160, 150, 30);
        panel_add_student.add(dc_stud_dob);

        lbl_stud_email = new JLabel("Email : ");
        lbl_stud_email.setBounds(200, 340, 150, 30);
        panel_add_student.add(lbl_stud_email);
        txt_stud_email = new JTextField(130);
        txt_stud_email.setBounds(350, 340, 150, 30);
        panel_add_student.add(txt_stud_email);

        lbl_stud_address = new JLabel("Address");
        lbl_stud_address.setBounds(200, 400, 150, 30);
        panel_add_student.add(lbl_stud_address);
        txt_stud_address = new JTextField(130);
        txt_stud_address.setBounds(350, 400, 650, 30);
        panel_add_student.add(txt_stud_address);

        lbl_stud_admission_date = new JLabel("Admission Date");
        lbl_stud_admission_date.setBounds(700, 100, 150, 30);
        panel_add_student.add(lbl_stud_admission_date);
        dc_stud_admission_date = new JDateChooser();
        dc_stud_admission_date.setDateFormatString("dd-mm-yyyy");
        dc_stud_admission_date.setBounds(850, 100, 150, 30);
        panel_add_student.add(dc_stud_admission_date);

        lbl_stud_gender = new JLabel("Gender: ");
        lbl_stud_gender.setBounds(700, 220, 150, 30);
        panel_add_student.add(lbl_stud_gender);
        String gender[] = {"Select Gender", "Male", "Female", "Other"};
        cb_stud_gender = new JComboBox(gender);
        cb_stud_gender.setBounds(850, 220, 150, 30);
        panel_add_student.add(cb_stud_gender);

        lbl_stud_religion = new JLabel("Religion : ");
        lbl_stud_religion.setBounds(700, 280, 150, 30);
        panel_add_student.add(lbl_stud_religion);
        txt_stud_religion = new JTextField(160);
        txt_stud_religion.setBounds(850, 280, 150, 30);
        panel_add_student.add(txt_stud_religion);

        lbl_stud_caste = new JLabel("Caste: ");
        lbl_stud_caste.setBounds(700, 340, 150, 30);
        panel_add_student.add(lbl_stud_caste);
        txt_stud_caste = new JTextField(50);
        txt_stud_caste.setBounds(850, 340, 150, 30);
        panel_add_student.add(txt_stud_caste);
        
        lbl_stud_blood_group = new JLabel("Blood Group : ");
        lbl_stud_blood_group.setBounds(700, 450, 150, 30);
        panel_add_student.add(lbl_stud_blood_group);
        String bloodGroup[] = {"Select Blood Group", "A+", "A-", "AB+", "AB-", "B+", "B-", "O+", "O-"};
        cb_stud_blood_group = new JComboBox(bloodGroup);
        cb_stud_blood_group.setBounds(850, 450, 150, 30);
        panel_add_student.add(cb_stud_blood_group);

        btn_add_stud = new JButton("ADD STUDENT");
        btn_add_stud.addActionListener(this);
        btn_add_stud.setBackground(Color.GREEN);
        btn_add_stud.setForeground(Color.WHITE);
        btn_add_stud.setBounds(200, 500, 165, 40);
        panel_add_student.add(btn_add_stud);


        //view students
        //viewStudents();
         panel_view_students.setLayout(null);
         panel_view_students.setBackground(new Color(255,255,204));
        //table = new JTable();
        
        //panel_view_students.add(scrollPane);

    
        //Delete students
       // panel_delete_student.setLayout(null);
        //panel_delete_student.add(scrollPane);
        
        // scrollPane = new JScrollPane(table);
        // scrollPane.setBounds(50, 50, 1200, 500);

        btn_view_referesh_stud = new JButton("REFRESH");
        btn_view_referesh_stud.addActionListener(this);
        btn_view_referesh_stud.setBackground(Color.BLUE);
        btn_view_referesh_stud.setForeground(Color.WHITE);
        btn_view_referesh_stud.setBounds(1000, 600,150,40);
        panel_view_students.add(btn_view_referesh_stud);



        //Update Students
        panel_update_student.setLayout(null);
        panel_update_student.setBackground(new Color(255,204,255));

        lbl_title = new JLabel("UPDATE   ||  DELETE  STUDENT", JLabel.CENTER);
        lbl_title.setBounds(370, 30, 600, 40);
        lbl_title.setFont(new Font("Arial", Font.BOLD, 30));
        panel_update_student.add(lbl_title);

        lbl_enter_stud_id = new JLabel("Enter Student Id Code : ");
        lbl_enter_stud_id.setFont(new Font("Arial", Font.BOLD, 20));
        lbl_enter_stud_id.setBounds(450, 200, 350, 30);
        panel_update_student.add(lbl_enter_stud_id);

        txt_enter_stud_id = new JTextField(20);
        txt_enter_stud_id.setBounds(700, 200, 165, 30);
        panel_update_student.add(txt_enter_stud_id);

        btn_stud_check = new JButton("UPDATE");
        btn_stud_check.addActionListener(this);
        btn_stud_check.setBackground(Color.ORANGE);
        btn_stud_check.setForeground(Color.WHITE);
        btn_stud_check.setBounds(700, 250, 165, 40);
        panel_update_student.add(btn_stud_check);

        btn_delete_stud = new JButton("DELETE");
        btn_delete_stud.addActionListener(this);
        btn_delete_stud.setBackground(Color.RED);
        btn_delete_stud.setForeground(Color.WHITE);
        btn_delete_stud.setBounds(450, 250,165,40);
        panel_update_student.add(btn_delete_stud);



        //Attendance

        menuBar_attendance = new JMenuBar();
        panel_attendance.add(menuBar_attendance,BorderLayout.NORTH);

        menu_addAttendance = new JMenu("ADD ATTENDANCE");
        menuBar_attendance.add(menu_addAttendance);

        menu_updateAttendance = new JMenu("UPDATE ATTENDANCE");
        menuBar_attendance.add(menu_updateAttendance);

        menu_viewAttendance = new JMenu("VIEW ATTENDANCE");
        menuBar_attendance.add(menu_viewAttendance);

        //panel_atten_add.add(new JLabel("Attendance"),BorderLayout.NORTH);

        //    panel_inner_add_attendance = new JPanel();
        //    panel_inner_add_attendance.setLayout(new GridLayout(3,3));
            //  panel_attendance.add(panel_inner_add_attendance,BorderLayout.SOUTH);
        
            // panel_inner_add_attendance.add(new JLabel("1"));
            // panel_inner_add_attendance.add(new JLabel("2"));
            // panel_inner_add_attendance.add(new JLabel("3"));
            // panel_inner_add_attendance.add(new JLabel("4"));
            // panel_inner_add_attendance.add(new JLabel("5"));
               

        //     try
        //     {
        //             connect();
        //             String sql = "select * from classes";
        //             ps = con.prepareStatement(sql);
        //             ResultSet rs = ps.executeQuery();
            
        //         while(rs.next())
        //         {
        //             combo = rs.getString(1);

        //             if(combo.equals("COMPUTER (5th SEM)"))
        //              {
        //                 sq = "select ID from students where CLASS_NAME = 'COMPUTER (5th SEM)'";
        //                 viewAttendance();
        //              }
        //              if(combo.equals("COMPUTER (6th SEM)"))
        //              {
        //                 sq = "select ID from students where CLASS_NAME = 'COMPUTER (6th SEM)'";
        //                 viewAttendance();
        //              }

                    
        //         }
                    
        //     }
        //     catch(Exception e)
        //     {
        //          JOptionPane.showMessageDialog(this, e);
        //     }
               
        //        if(combo.equals("COMPUTER (6th SEM)"))
        //        {


        //                 sq = "select ID from students where CLASS_NAME = 'COMPUTER (6th SEM)'";


        //             connect();
        //             try
        //             {
        //                 statement = con.createStatement();
        //                  rs = statement.executeQuery(sq);

        //                 while(rs.next())
        //                 {
        //                     String s = rs.getString(1);
        //                     lbl_stud_atten_id = new JLabel(s);
        //                     panel_inner_add_attendance.add(lbl_stud_atten_id);

        //                     rb_present = new JRadioButton("Present");
        //                     rb_present.addActionListener(this);
        //                     rb_present.setBounds(75,50,100,30);
        //                     panel_inner_add_attendance.add(rb_present);

        //                     rb_absent = new JRadioButton("Absent"); 
        //                     rb_absent.addActionListener(this);
        //                     rb_absent.setBounds(75,100,100,30);
        //                     panel_inner_add_attendance.add(rb_absent);

        //                 }
                        
        //                 disconnect();

        //             }
        //             catch(Exception e)
        //             {
        //                 JOptionPane.showMessageDialog(this, e);
        //             }

        //             panel_inner_add_attendance.add(new JLabel(""));
        //             btn_submit_attendance = new JButton("Submit");
        //             btn_submit_attendance.addActionListener(this);
        //             panel_inner_add_attendance.add(btn_submit_attendance,BorderLayout.SOUTH);

                    
        //        }

            
        
        //panel_atten_add.setBounds(EXIT_ON_CLOSE, ABORT, WIDTH, HEIGHT);
        //lbl_password.setFont(new Font("Arial", Font.BOLD, 20));

    
        lst_model = new DefaultListModel<String>();
        lst = new JList<>(lst_model);
        scrollPane = new JScrollPane(lst);
        panel_center.add(scrollPane, BorderLayout.CENTER);

        this.add(splitPane, BorderLayout.CENTER);
        this.setVisible(true);
        this.setSize(1920, 850);

        lst.addListSelectionListener(new ListSelectionListener() {

                @Override
                public void valueChanged(ListSelectionEvent e) {
                    panel_right_inner.setVisible(true);
                    cname = lst.getSelectedValue().toString();
                    viewStudents();
                    cname_index = lst.getSelectedIndex();
                    txt_class_name.setText(cname);
                }
            });

        
    }

    int counter = 0;
    private JTable table;
    private JScrollPane scroll;
    private JButton btn_delete_stud;
    DefaultTableModel model;

    public void viewStudents()
    {
         table = new JTable();
        //panel_view_students.add(new JLabel("Students"));
        connect();
        try
        {
            String sql = "select * from students where CLASS_NAME = ? and staff_name=?";
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, cname);
            st.setString(2,sta_name);
            ResultSet rs = st.executeQuery();
            ResultSetMetaData rsmd = rs.getMetaData();
            model = (DefaultTableModel) table.getModel();


            int cols = rsmd.getColumnCount() - 2;
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
            
           // model.setRowCount(0);
            String id, name, mobno, dob, email, address, add_date, gender, religion, caste, blood_grp, class_name;
            while(rs.next())
            {
                id = rs.getString(1);
                name = rs.getString(2);
                mobno = rs.getString(3);
                dob = rs.getString(4);
                email = rs.getString(5);
                address = rs.getString(6);
                add_date = rs.getString(7);
                gender = rs.getString(8);
                religion = rs.getString(9);
                caste = rs.getString(10);
                blood_grp = rs.getString(11);
                class_name = rs.getString(12);

                String row[] = {id, name, mobno, dob, email, address, add_date, gender, religion, caste, blood_grp, class_name};
                model.addRow(row);
            }
            // table.add(model);
            disconnect();
        }
        catch (Exception e)
        {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }

         scroll = new JScrollPane(table);
        //  scrollPane = new JScrollPane(table);

         scroll.setBounds(50, 50, 1100, 500);
        //  scrollPane.setBounds(50, 50, 1100, 500);

         panel_view_students.add(scroll);
        //  panel_delete_student.add(scrollPane);
         //panel_delete_student.add(scroll);
        // btn_refresh.setVisible(true);

    }

    public void deleteStudents()
    {
        try
        {
            //int column =0;
           // int row = table.getSelectedRow();
        

            // model.removeRow(table.getSelectedRow());
            // String id = "878";
            
           // DefaultTableModel tbl_mobel = (DefaultTableModel) table.getModel();

            // String stud_id = model.getValueAt(table.getSelectedRow(), 1).toString();

            // JOptionPane.showMessageDialog(this, stud_id);

            // if(table.getSelectedRowCount()== 1)
            // {
            //     model.removeRow(table.getSelectedRow());
            // }
            // else if(table.getRowCount() == 0)
            // {
            //     JOptionPane.showMessageDialog(this, "Table is empty");
            // }
            // else
            // {
            //     JOptionPane.showMessageDialog(this, "Select at least one row");
            // }

            connect();

            String stud_id = txt_enter_stud_id.getText();

            String sql = "DELETE FROM students WHERE ID=?";
            ps = con.prepareStatement(sql);
            ps.setString(1,stud_id);
            int n = ps.executeUpdate();

            if (n == 1)
            {
                JOptionPane.showMessageDialog(this, "Student deleted successfully !!!");
            }
            else
            JOptionPane.showMessageDialog(this, "Student ID not found !!! ");
            
            disconnect();
        }
        catch (Exception e)
        {
            JOptionPane.showMessageDialog(this, "Error "+e);
        }
    }
            
    public void showClasses()
    {
        counter++;
        try
        {
            lst_model.removeAllElements();
            connect();
            String sql = "select * from classes where staff_name=?";
            String sta_name = StaffLogin.staff_name;
            ps = con.prepareStatement(sql);
            ps.setString(1, sta_name);
            ResultSet rs = ps.executeQuery();
            

           
                while(rs.next())
                {
                    combo = rs.getString(1);
                    lst_model.addElement(combo);
                    counter++;
                    //JOptionPane.showMessageDialog(this,combo);
                }
           
    
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(this, "You have to press refresh button after deleting the class");
        }    
    }



    public void viewAttendance()
    {
        connect();
            try
            {
                statement = con.createStatement();
                ResultSet rs = statement.executeQuery(sq);

                while(rs.next())
                {
                    String s = rs.getString(1);
                    lbl_stud_atten_id = new JLabel(s);
                    panel_inner_add_attendance.add(lbl_stud_atten_id);

                    rb_present = new JRadioButton("Present");
                    rb_present.setBounds(75,50,100,30);
                    panel_inner_add_attendance.add(rb_present);

                    rb_absent = new JRadioButton("Absent");
                    rb_absent.setBounds(75,100,100,30);
                    panel_inner_add_attendance.add(rb_absent);

                }
                
                disconnect();

            }
            catch(Exception e)
            {
                JOptionPane.showMessageDialog(this, e);
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
    public void actionPerformed(ActionEvent e) {
        
        if(e.getSource() == btn_create_class)
        {
            new CreateClass();
        }

        if(e.getSource() == btn_my_profile)
        {
            new StaffMyProfile();
        }

        if(e.getSource() == btn_view_referesh_stud)
        {
            viewStudents();
        }

        
        if(e.getSource() == btn_delete_class)
        {
            try
            {
                // counter = 1;
                connect();
                String sql = "delete from classes where dept_sem = ?";
                ps = con.prepareStatement(sql);
                ps.setString(1, cname);
                
                int n = ps.executeUpdate();
                if(n == 1)
                {
                    JOptionPane.showMessageDialog(this, "Class deleted");
                    txt_class_name.setText(null);
                    cname = null;
                    showClasses();
                   // panel_right_inner.setVisible(false);
                }

                showClasses();
                disconnect();

               // txt_class_name.;
                // panel_center.setVisible(false);
            }
            catch (Exception ex)
            {
                JOptionPane.showMessageDialog(this, "You have to press refresh button after deleting the class");
            }
        }

        if(e.getSource() == btn_available_classes)
        {
            showClasses();
        }

        if(e.getSource() == btn_refresh)
        {
            showClasses();
        }

        if(e.getSource() == btn_add_stud)
        {
            String id = txt_stud_id.getText();
            String name = txt_stud_name.getText();
            String mobno = txt_stud_mob_no.getText();
            String email = txt_stud_email.getText();
            String address = txt_stud_address.getText();
            String religion = txt_stud_religion.getText();
            String caste = txt_stud_caste.getText();

            //java.sql.Date date_dob = new java.sql.Date(dc_stud_dob.getDate().getTime()); 
            //java.sql.Date date_addmission = new java.sql.Date(dc_stud_admission_date.getDate().getTime()); 
            //String gender = (String) cb_stud_gender.getSelectedItem();
            //String blood_grp = (String) cb_stud_blood_group.getSelectedItem();

            if(id.equals("") || name.equals("") || mobno.equals("") || email.equals("") || 
               address.equals("") || religion.equals("") || caste.equals(""))
            {
                JOptionPane.showMessageDialog(this,"Please enter Details !!!");
            }
            else if(!id.equals("") && !name.equals("") && !mobno.equals("") && !email.equals("") && 
               !address.equals("") && !religion.equals("") && !caste.equals(""))
            {
                //ID
                if(id.length() == 7)
                {
                    p_id = Pattern.compile(".*[!,@,#,$,%,^,&,*,(,),_,+,?,>,<,.].*");

                    if(p_id.matcher(id).matches())
                    {
                        JOptionPane.showMessageDialog(this,"Invalid Id !!!");
                    }
                }
                else
                {
                    JOptionPane.showMessageDialog(this,"Invalid Id !!!");
                }


                //Name
                if(name.length() < 29)
                {
                    p_name = Pattern.compile(".*[A-Za-z].*");
                                    
                    if(!p_name.matcher(name).matches())
                    {
                         JOptionPane.showMessageDialog(this,"Invalid Name !!!");
                    }
    
                    Pattern p1 = Pattern.compile(".*[0-9].*");
                    if(p1.matcher(name).matches()) 
                    {
                        JOptionPane.showMessageDialog(this,"Invalid Name !!!");
                    }
                }
                else
                {
                    JOptionPane.showMessageDialog(this,"Name is to Long !!!");
                }


                //Mobile No.
                if(mobno.length() == 10)
                {
                    p_mob_no= Pattern.compile(".*[0-9].*");
                    if(!p_mob_no.matcher(mobno).matches()) 
                    {
                        JOptionPane.showMessageDialog(this,"Invalid Mobile Number !!!");
                    }

                }
                else
                {
                    JOptionPane.showMessageDialog(this,"Invalid Mobile Number !!!");
                }


                //Email
                if(email.length() > 0)
                {
                    p_email = Pattern.compile("^(.+)@(.+)$");
                    if(!p_email.matcher(email).matches()) 
                    {
                        JOptionPane.showMessageDialog(this,"Invalid Email !!!");
                    }
                }


                //Address
                if(address.length() > 0)
                {
                    p_address = Pattern.compile(".*[!,@,#,$,%,^,&,*,(,),-].*");
                    if(p_address.matcher(address).matches()) 
                    {
                        JOptionPane.showMessageDialog(this,"Invalid Address !!!");
                    }
                }


                //Religion
                if(religion.length() > 0)
                {
                    p_religion = Pattern.compile(".*[A-Za-z].*");
                                    
                    if(!p_religion.matcher(religion).matches())
                    {
                         JOptionPane.showMessageDialog(this,"Invalid Religion !!!");
                    }
    
                    Pattern p1 = Pattern.compile(".*[0-9].*");
                    if(p1.matcher(religion).matches()) 
                    {
                        JOptionPane.showMessageDialog(this,"Invalid Religion !!!");
                    }
                }


                //Caste
                if(caste.length() > 0)
                {
                    p_caste = Pattern.compile(".*[A-Za-z].*");
                                    
                    if(!p_caste.matcher(caste).matches())
                    {
                         JOptionPane.showMessageDialog(this,"Invalid Caste !!!");
                    }
    
                    Pattern p1 = Pattern.compile(".*[0-9].*");
                    if(p1.matcher(caste).matches()) 
                    {
                        JOptionPane.showMessageDialog(this,"Invalid Caste !!!");
                    }
                }


                if(!p_id.matcher(id).matches()  && p_name.matcher(name).matches() && p_mob_no.matcher(mobno).matches() && p_email.matcher(email).matches()
                    && !p_address.matcher(address).matches() && p_religion.matcher(religion).matches() && p_caste.matcher(caste).matches())
                    {
                        try
                        {
                            String sid = txt_stud_id.getText().trim();
                            String sname = txt_stud_name.getText().trim().toUpperCase();
                            String mobile_no = txt_stud_mob_no.getText();
                            java.sql.Date date_dob = new java.sql.Date(dc_stud_dob.getDate().getTime()); 
                            String semail = txt_stud_email.getText().trim();
                            String saddress = txt_stud_address.getText().trim().toUpperCase();
                            java.sql.Date date_addmission = new java.sql.Date(dc_stud_admission_date.getDate().getTime()); 
                            String gender = (String) cb_stud_gender.getSelectedItem();
                            String sreligion = txt_stud_religion.getText().trim().toUpperCase();
                            String scaste = txt_stud_caste.getText().trim().toUpperCase();
                            String blood_grp = (String) cb_stud_blood_group.getSelectedItem();
            
            
                            connect();
                            String sql = "insert into students values(?,?,?,?,?,?,?,?,?,?,?,?,?)";
                            ps = con.prepareStatement(sql);
                            ps.setString(1, sid);
                            ps.setString(2, sname);
                            ps.setString(3, mobile_no);
                            ps.setDate(4, date_dob);
                            ps.setString(5, semail);
                            ps.setString(6, saddress);
                            ps.setDate(7, date_addmission);
                            ps.setString(8, gender);
                            ps.setString(9, sreligion);
                            ps.setString(10, scaste);
                            ps.setString(11, blood_grp);
                            ps.setString(12, lst.getSelectedValue().toString());
                            ps.setString(13,sta_name);
                            int n = ps.executeUpdate();
            
                            if(n == 1)
                            {
                                JOptionPane.showMessageDialog(this, "Student Added to Class "+cname);
                            }
            
                            disconnect();
                        }
                        catch(Exception ex)
                        {
                            JOptionPane.showMessageDialog(this, ex.getMessage());
                        }
                    }


            }
        }


        if(e.getSource() == btn_delete_stud)
        {
            deleteStudents();
            viewStudents();
        }


        if(e.getSource() == btn_stud_check)
        {
            std_id_for_update_student = txt_enter_stud_id.getText();

            //SimpleDateFormat sdf = new SimpleDateFormat(dc_stud_dob);

            try
            {
                connect();
                String query = "select * from students where id = ? ";
                ps = con.prepareStatement(query);
                ps.setString(1,std_id_for_update_student);

                rs = ps.executeQuery();

                
               
                // if(!rs.isFirst())
                // {
                //     JOptionPane.showMessageDialog(this,"Record Not Found...");
                // }

                    while(rs.next())
                    {
                        
                        stud_id = rs.getString(1);
                        stud_name = rs.getString(2);
                        stud_mob_no = rs.getString(3);
                        stud_dob = rs.getDate(4);
                        stud_email = rs.getString(5);
                        stud_address = rs.getString(6);
                        stud_admission_date = rs.getDate(7);
                        stud_gender = rs.getString(8);
                        stud_religion = rs.getString(9);
                        stud_caste = rs.getString(10);
                        stud_blood_group = rs.getString(11); 
                        //JOptionPane.showMessageDialog(this,stud_id);
                        
                    }

                    new UpdateStudent();


                // }
                // else
                // {
                //      JOptionPane.showMessageDialog(this,"Record Not Found...");
                // }
            }
            catch (Exception ep)
            {
                JOptionPane.showMessageDialog(this, e);
            }
            

           // new UpdateStudent();
        }

       

        // if(e.getSource() == rb_present)
        // {
        //     //Attendance
        //     String result = e.getActionCommand();
        //     //JOptionPane.showMessageDialog(this, result);

        //     connect();
        //     sq = "select ID from students where CLASS_NAME = 'COMPUTER (6th SEM)'";

        //             try
        //             {
        //                  statement = con.createStatement();
        //                  rs = statement.executeQuery(sq);

        //                 while(rs.next())
        //                 {
        //                     String s = rs.getString(1);
        //                     JOptionPane.showMessageDialog(this,s);
        //                     String sql = "insert into attendance values(?,?)";
        //                     ps = con.prepareStatement(sql);
        //                     ps.setString(1,s);
        //                     ps.setString(2,result);
        //                     atten_result = ps.executeUpdate();

        //                     //JOptionPane.showMessageDialog(this,atten_result);
                            
        //                 }
                           
        //             }
        //             catch(Exception el)
        //             {
        //                 JOptionPane.showMessageDialog(this,el);
        //             }
       
        // }

        // if(e.getSource() == btn_submit_attendance)
        // {
        //     if(atten_result == 1)
        //     {
        //         JOptionPane.showMessageDialog(this,"Insertd......");
        //     }
        //     else
        //     {
        //         JOptionPane.showMessageDialog(this,atten_result);
        //     }
                
            
            
        //  }
        
    }

   
    public static void main(String[] args) {
        new StaffHome();
    } 
}
