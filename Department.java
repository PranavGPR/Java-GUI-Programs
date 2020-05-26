import java.sql.*;
import javax.swing.*;
import java.awt.event.*;
import static javax.swing.JFrame.EXIT_ON_CLOSE;

class Details extends JFrame implements ActionListener
{
    Connection con;
    Statement st;
    ResultSet rs;
    JTextField t1,t2,t3;
    JButton b1,b2,b3;
    JLabel l1,l2,l3;
    
    Details() throws Exception
    {
        Class.forName("oracle.jdbc.driver.OracleDriver");
        con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","scott","tiger");
        st = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
        rs = st.executeQuery("select * from department");
        
        setTitle("Department Details");
        
        l1 = new JLabel("Code    :");
        l2 = new JLabel("Name    :");
        l3 = new JLabel("Location    :");
        
        t1 = new JTextField();
        t2 = new JTextField();
        t3 = new JTextField();
       
        b1 = new JButton("Prev");
        b2 = new JButton("Next");
        b3 = new JButton("Delete");
        
        l1.setBounds(40, 20, 80, 14);
        l2.setBounds(40, 60, 80, 14);
        l3.setBounds(40, 100, 80, 14);
        
        b1.setBounds(60, 180, 73, 23);
        b2.setBounds(160, 180, 73, 23);
        b3.setBounds(60, 210, 73, 23);
        
        t1.setBounds(150, 10, 110, 30);
        t2.setBounds(150, 50, 110, 30);
        t3.setBounds(150, 90, 110, 30);
        
        this.add(t1);
        this.add(t2);
        this.add(t3);
        
        this.add(l1);
        this.add(l2);
        this.add(l3);
        
        add(b1);
        add(b2);
        add(b3);
        
        b1.addActionListener(this);
        b2.addActionListener(this);
        b3.addActionListener(this);
        
        t1.setEditable(false);
        t2.setEditable(false);
        t3.setEditable(false);
        
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
                t3.setText(rs.getString(3));
            } 
            catch (Exception ex) 
            {
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
                t3.setText(rs.getString(3));
               }
            catch (Exception ex) 
            {
                System.out.println(ex.toString());
            }
        }
        else if(e.getActionCommand().equals("Delete"))
        {
            try
            {
                PreparedStatement pst = con.prepareStatement("delete from department where dno = ?");
                pst.setInt(1, Integer.valueOf(t1.getText()));
                pst.execute();
                b1.setEnabled(false);
                JOptionPane.showMessageDialog(null, "Deleted Successfully");
                new Details();
            } 
            catch (Exception ex) 
            {
                System.out.println(ex.toString());
            }  
        }
        try {
            if(rs.isLast())
                b2.setEnabled(false);
            if(rs.isFirst())
                    b1.setEnabled(false);
        } catch (Exception ex) 
            {
                System.out.println(ex.toString());
            }      
    }
}

public class Department
{
    public static void main(String[] args) throws Exception
    {
        new Details();    
    }
}
