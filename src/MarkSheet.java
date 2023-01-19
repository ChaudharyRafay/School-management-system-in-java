
import com.mysql.jdbc.Statement;
import db.MyConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import student.Student;


public class MarkSheet {
     Connection conn=(Connection) MyConnection.getConnection();
    PreparedStatement ps;
      //check student email is already taken or not 
    public boolean isIDExists(int sid){
        try {
            ps=conn.prepareStatement("select * from score where student_id=?");
            ps.setInt(1, sid);
            ResultSet rs=ps.executeQuery();
            if(rs.next()){
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(MarkSheet.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
        
        
    }
    //get all values from score  table
    public void getScoreValue(JTable table,int sid){
        String sql="select * from score where student_id=?";
        try {
            ps=conn.prepareStatement(sql);
            ps.setInt(1, sid);
            ResultSet rs=ps.executeQuery();
            DefaultTableModel model = (DefaultTableModel) table.getModel();
            Object[] row;
            while (rs.next()){
            row=new Object[14];
            row[0]=rs.getInt(1);
            row[1]=rs.getInt(2);
            row[2]=rs.getInt(3);
            row[3]=rs.getString(4);
            row[4]=rs.getDouble(5);
            row[5]=rs.getString(6);
            row[6]=rs.getDouble(7);
            row[7]=rs.getString(8);
            row[8]=rs.getDouble(9);
            row[9]=rs.getString(10);
            row[10]=rs.getDouble(11);
            row[11]=rs.getString(12);
            row[12]=rs.getDouble(13);
            row[13]=rs.getDouble(14);
            model.addRow(row);
            
        }
        } catch (SQLException ex) {
            Logger.getLogger(MarkSheet.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    public double getCGPA(int sid){
        double cgpa=0.0;
        Statement st;
         try {
            st=(Statement) conn.createStatement();
            ResultSet rs=st.executeQuery("select avg(average) from score where student_id="+sid+"");
            if(rs.next()){
                cgpa=rs.getDouble(1);
            }
        } catch (Exception e) {
             System.err.println(e.getMessage());
        }
         return cgpa;
    }
    
}
