
package student;
import java.sql.*;
import db.MyConnection;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class Student {
    Connection conn=(Connection) MyConnection.getConnection();
    PreparedStatement ps;
    public int max(){//getting max student id in database and increment by 1 and dispplay in UI
        int id=0;
        Statement stmt; 
        try {
            stmt= conn.createStatement();
             ResultSet rs=stmt.executeQuery("select max(id) from student");
             while (rs.next()) {
               id=rs.getInt(1);
                
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
       return id+1;
    }
    //insert data into student table
    public void insert (int id,String sname,String date,String gender,String email,String phone,String father,String mother,String address ){
        String sql="insert into student values (?,?,?,?,?,?,?,?,?)";
        try {
            ps=conn.prepareStatement(sql);
            ps.setInt(1,id);
            ps.setString(2, sname);
            ps.setString(3, date);
            ps.setString(4, gender);
            ps.setString(5, email);
            ps.setString(6, phone);
            ps.setString(7, father);
            ps.setString(8, mother);
            ps.setString(9, address);
            if(ps.executeUpdate()>0){
                JOptionPane.showMessageDialog(null, "New Student added successfully");
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(Student.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    //check student email is already taken or not 
    public boolean isEmailExist(String email){
        try {
            ps=conn.prepareStatement("select * from student where email=?");
            ps.setString(1, email);
            ResultSet rs=ps.executeQuery();
            if(rs.next()){
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(Student.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
        
        
    }
      //check student email is already taken or not 
    public boolean isIDExists(int id){
        try {
            ps=conn.prepareStatement("select * from student where id=?");
            ps.setInt(1, id);
            ResultSet rs=ps.executeQuery();
            if(rs.next()){
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(Student.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
        
        
    }
    //get all values from student table
    public void getStudentValue(JTable table,String searchValue){
        String sql="select * from student where concat(id,name,email,phone)like ? order by id desc";
        try {
            ps=conn.prepareStatement(sql);
            ps.setString(1, "%"+searchValue+"%");
            ResultSet rs=ps.executeQuery();
            DefaultTableModel model = (DefaultTableModel) table.getModel();
            Object[] row;
            while (rs.next()){
            row=new Object[11];
            row[0]=rs.getInt(1);
            row[1]=rs.getString(2);
            row[2]=rs.getString(3);
            row[3]=rs.getString(4);
            row[4]=rs.getString(5);
            row[5]=rs.getString(6);
            row[6]=rs.getString(7);
            row[7]=rs.getString(8);
            row[8]=rs.getString(9);
            model.addRow(row);
            
        }
        } catch (SQLException ex) {
            Logger.getLogger(Student.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    //update student data
    
    
    public void updateStudentRecord(int id,String sname,String date,String gender,String email,String phone,String father,String mother,String address){
        String sql="update student set name=?,date_of_birth=?,gender=?,email=?,phone=?,father_name=?,mother_name=?,address=? where id=?";
        try {
            ps=conn.prepareStatement(sql);
             ps.setString(1, sname);
            ps.setString(2, date);
            ps.setString(3, gender);
            ps.setString(4, email);
            ps.setString(5, phone);
            ps.setString(6, father);
            ps.setString(7, mother);
            ps.setString(8, address);
            ps.setInt(9, id);
            if(ps.executeUpdate()>0){
                 JOptionPane.showMessageDialog(null, "Student data update successfully");
            }
        } catch (SQLException ex) {
            Logger.getLogger(Student.class.getName()).log(Level.SEVERE, null, ex);
        }
           
    }
    
    public void delete(int id){
        int yesNo=JOptionPane.showConfirmDialog( null,"Courses and score records will be deleted","Student Delete",JOptionPane.OK_CANCEL_OPTION,0);
        if(yesNo==JOptionPane.OK_OPTION){
            try {
                ps=conn.prepareStatement("delete from student where id =?");
                ps.setInt(1, id);
                if(ps.executeUpdate()>0){
                    JOptionPane.showMessageDialog(null, "Student deleted successfully");
                }
            } catch (SQLException ex) {
                Logger.getLogger(Student.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
         
    }
}
