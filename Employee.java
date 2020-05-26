import java.sql.*;
import javax.swing.*;
import java.awt.event.*;
import org.jdesktop.swingx.JXDatePicker;

class Basic1 extends JFrame implements ActionListener
{
    Connection con;
    Statement st;
    ResultSet rs;
    JTextField t1,t2,t3,t4,t5,t6;
    JButton b1,b2,b3,b4;
    JLabel l1,l2,l3,l4,l5,l6;
    
    Basic1() throws Exception
    {
        Class.forName("oracle.jdbc.driver.OracleDriver");
        con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","scott","tiger");
        st = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
        rs = st.executeQuery("select * from student");
        
        setTitle("Student Details");
        
        l1 = new JLabel("Student ID     :");
        l2 = new JLabel("Name             :");
        l3 = new JLabel("CourseCode :");
        l4 = new JLabel("Mark 1           :");
        l5 = new JLabel("Mark 2           :");
        l6 = new JLabel("Total              :");
        
        t1 = new JTextField();
        t2 = new JTextField();
        t3 = new JTextField();
        t4 = new JTextField();
        t5 = new JTextField();
        t6 = new JTextField();
        
        b1 = new JButton("Prev");
        b2 = new JButton("Next");
        b3 = new JButton("Add");
        b4 = new JButton("Edit");
        
        l1.setBounds(40, 20, 80, 14);
        l2.setBounds(40, 60, 80, 14);
        l3.setBounds(40, 100, 80, 14);
        l4.setBounds(40, 140, 80, 14);
        l5.setBounds(40, 180, 80, 14);
        l6.setBounds(40, 220, 80, 14);
        
        b1.setBounds(60, 260, 70, 23);
        b2.setBounds(150, 260, 70, 23);
        b3.setBounds(60, 290, 70, 23);
        b4.setBounds(150, 290, 70, 23);
        
        t1.setBounds(150, 10, 110, 30);
        t2.setBounds(150, 50, 110, 30);
        t3.setBounds(150, 90, 110, 30);
        t4.setBounds(150, 130, 110, 30);
        t5.setBounds(150, 170, 110, 30);
        t6.setBounds(150, 210, 110, 30);
                
        this.add(t1);
        this.add(t2);
        this.add(t3);
        this.add(t4);
        this.add(t5);
        this.add(t6);      
        
        this.add(l1);
        this.add(l2);
        this.add(l3);
        this.add(l4);
        this.add(l5);
        this.add(l6);
        
        add(b1);
        add(b2);
        add(b3);
        add(b4);
        
        b1.addActionListener(this);
        b2.addActionListener(this);
        b3.addActionListener(this);
        b4.addActionListener(this);
                
        b1.setEnabled(false);
        
        this.setResizable(false);
        this.setSize(307, 350);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLayout(null);
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) 
    {
        
        if(e.getActionCommand().equals("Next"))
        {
            b1.setEnabled(true);
            try
            {
                rs.next();
                t1.setText(""+rs.getInt(1));
                t2.setText(rs.getString(2));
                t3.setText(""+rs.getInt(3));
                t4.setText(""+rs.getInt(4));
                t5.setText(""+rs.getInt(5));
                t6.setText(""+rs.getInt(6));   
            } 
            catch (Exception ex) {
                System.out.println(ex.toString());
            }
        }
        
        else if(e.getActionCommand().equals("Prev"))
        {
            b2.setEnabled(true);
            try {
                rs.previous();
                t1.setText(""+rs.getInt(1));
                t2.setText(rs.getString(2));
                t3.setText(""+rs.getInt(3));
                t4.setText(""+rs.getInt(4));
                t5.setText(""+rs.getInt(5));
                t6.setText(""+rs.getInt(6));
            }
            catch (Exception ex) {
                System.out.println(ex.toString());
            }
        }
        else if(e.getActionCommand().equals("Add"))
        {
            try
            {                              
                new Add();
                this.setVisible(false);
            } 
            catch (Exception ex) {
                System.out.println(ex.toString());
            }          
        }
        else if(e.getActionCommand().equals("Edit"))
        {
            try
            {                              
                new Edit();
                this.setVisible(false);
            } 
            catch (Exception ex) {
                System.out.println(ex.toString());
            }          
        }
        try {
            if(rs.isLast())
                b2.setEnabled(false);
            if(rs.isFirst())
                b1.setEnabled(false);
        } catch (Exception ex) {}      
    }
}

class Add extends JFrame implements ActionListener{
    
    Connection con;
    Statement st;
    ResultSet rs;
    JTextField t1,t2,t3,t4,t5,t6;
    JButton b3;
    JLabel l1,l2,l3,l4,l5,l6;
    
    Add() throws Exception{
        
        Class.forName("oracle.jdbc.driver.OracleDriver");
        con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","scott","tiger");
        
        setTitle("Student Details");
        
        l1 = new JLabel("Student ID     :");
        l2 = new JLabel("Name             :");
        l3 = new JLabel("CourseCode :");
        l4 = new JLabel("Mark 1           :");
        l5 = new JLabel("Mark 2           :");
        l6 = new JLabel("Total              :");
        
        t1 = new JTextField();
        t2 = new JTextField();
        t3 = new JTextField();
        t4 = new JTextField();
        t5 = new JTextField();
        t6 = new JTextField();

        b3 = new JButton("Add");
        
        l1.setBounds(40, 20, 80, 14);
        l2.setBounds(40, 60, 80, 14);
        l3.setBounds(40, 100, 80, 14);
        l4.setBounds(40, 140, 80, 14);
        l5.setBounds(40, 180, 80, 14);
        l6.setBounds(40, 220, 80, 14);
        
        b3.setBounds(110, 260, 70, 23);
        
        t1.setBounds(150, 10, 110, 30);
        t2.setBounds(150, 50, 110, 30);
        t3.setBounds(150, 90, 110, 30);
        t4.setBounds(150, 130, 110, 30);
        t5.setBounds(150, 170, 110, 30);
        t6.setBounds(150, 210, 110, 30);
            
        this.add(t1);
        this.add(t2);
        this.add(t3);
        this.add(t4);
        this.add(t5);
        this.add(t6);
        
        this.add(l1);
        this.add(l2);
        this.add(l3);
        this.add(l4);
        this.add(l5);
        this.add(l6);
      
        add(b3);

        b3.addActionListener(this);
        
        this.setResizable(false);
        this.setSize(307, 350);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLayout(null);
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equals("Add"))
        {
            try
            {                              
                PreparedStatement pst = con.prepareStatement("insert into student values(?,?,?,?,?,?)");
                pst.setInt(1, Integer.valueOf(t1.getText()));
                pst.setString(2, t2.getText());
                pst.setInt(3, Integer.valueOf(t4.getText()));
                pst.setInt(4, Integer.valueOf(t4.getText()));
                pst.setInt(5, Integer.valueOf(t5.getText()));
                pst.setInt(6, Integer.valueOf(t6.getText()));               
                pst.execute();               
                JOptionPane.showMessageDialog(null, "Added Successfully");
                this.dispose();
            } 
            catch (Exception ex) {
                System.out.println(ex.toString());
            }           
        }
    }
}

class Edit extends JFrame implements ActionListener{
    
    Connection con;
    Statement st;
    ResultSet rs;
    JTextField t1,t2,t3,t4,t5,t6;
    JButton b1,b2,b4;
    JLabel l1,l2,l3,l4,l5,l6;
    
    Edit() throws Exception
    {
        Class.forName("oracle.jdbc.driver.OracleDriver");
        con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","scott","tiger");
        st = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
        rs = st.executeQuery("select * from student");
        
        setTitle("Student Details");
        
        l1 = new JLabel("Student ID     :");
        l2 = new JLabel("Name             :");
        l3 = new JLabel("CourseCode :");
        l4 = new JLabel("Mark 1           :");
        l5 = new JLabel("Mark 2           :");
        l6 = new JLabel("Total              :");
        
        t1 = new JTextField();
        t2 = new JTextField();
        t3 = new JTextField();
        t4 = new JTextField();
        t5 = new JTextField();
        t6 = new JTextField();
        
        b1 = new JButton("Prev");
        b2 = new JButton("Next");
        b4 = new JButton("Edit");
        
        l1.setBounds(40, 20, 80, 14);
        l2.setBounds(40, 60, 80, 14);
        l3.setBounds(40, 100, 80, 14);
        l4.setBounds(40, 140, 80, 14);
        l5.setBounds(40, 180, 80, 14);
        l6.setBounds(40, 220, 80, 14);
        
        b1.setBounds(60, 260, 70, 23);
        b2.setBounds(150, 260, 70, 23);
        b4.setBounds(105, 290, 70, 23);
        
        t1.setBounds(150, 10, 110, 30);
        t2.setBounds(150, 50, 110, 30);
        t3.setBounds(150, 90, 110, 30);
        t4.setBounds(150, 130, 110, 30);
        t5.setBounds(150, 170, 110, 30);
        t6.setBounds(150, 210, 110, 30);
                
        this.add(t1);
        this.add(t2);
        this.add(t3);
        this.add(t4);
        this.add(t5);
        this.add(t6);      
        
        this.add(l1);
        this.add(l2);
        this.add(l3);
        this.add(l4);
        this.add(l5);
        this.add(l6);
        
        add(b1);
        add(b2);
        add(b4);
        
        b1.addActionListener(this);
        b2.addActionListener(this);
        b4.addActionListener(this);
                
        b1.setEnabled(false);
        t1.setEditable(false);
        
        this.setResizable(false);
        this.setSize(307, 350);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLayout(null);
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
        if(e.getActionCommand().equals("Next"))
        {
            b1.setEnabled(true);
            try{
                rs.next();
                t1.setText(""+rs.getInt(1));
                t2.setText(""+rs.getString(2));
                t3.setText(""+rs.getInt(3));
                t4.setText(""+rs.getInt(4));
                t5.setText(""+rs.getInt(5));
                t6.setText(""+rs.getInt(6));   
            } 
            catch (Exception ex) {
                System.out.println(ex.toString());
            }
        }        
        else if(e.getActionCommand().equals("Prev"))
        {
            b2.setEnabled(true);
            try {
                rs.previous();
                t1.setText(""+rs.getInt(1));
                t2.setText(""+rs.getString(2));
                t3.setText(""+rs.getInt(3));
                t4.setText(""+rs.getInt(4));
                t5.setText(""+rs.getInt(5));
                t6.setText(""+rs.getInt(6));
            }
            catch (Exception ex) {
                System.out.println(ex.toString());
            }
        }
        else if(e.getActionCommand().equals("Edit")){
            
            try{
                PreparedStatement pst = con.prepareStatement("update student set name = ?, ccode = ?, mark1 = ?, mark2 = ?, total = ? where rno = ?");
                pst.setString(1, t2.getText());
                pst.setInt(2, Integer.valueOf(t3.getText()));
                pst.setInt(3, Integer.valueOf(t4.getText()));
                pst.setInt(4, Integer.valueOf(t5.getText()));
                pst.setInt(5, Integer.valueOf(t6.getText())); 
                pst.setInt(6, Integer.valueOf(t1.getText()));
                pst.execute();  
                JOptionPane.showMessageDialog(null, "Edited Successfully");
                this.dispose();
                new Basic1();
            }catch(Exception ex) {
                System.out.println(ex.toString());
            }           
        }
        try {
            if(rs.isLast())
                b2.setEnabled(false);
            if(rs.isFirst())
                b1.setEnabled(false);
        } catch (Exception ex) {}
    }
}

public class Employee
{
    public static void main(String[] args) throws Exception
    {
        new Basic1();    
    }
}
