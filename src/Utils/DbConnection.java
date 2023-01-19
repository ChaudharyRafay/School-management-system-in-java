
package Utils;
import java.sql.*;
public class DbConnection {
   
    static final String DB_URL="jdbc:mysql://localhost:3306/school";
    static final String USER="root";
    static final String PASS="";
    public static Connection connectDB(){
         Connection conn=null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn=DriverManager.getConnection(DB_URL,USER,PASS);
            return conn;
        } catch (Exception ex) {
            System.out.println("There was an error while connecting to db");
            return  null;
        }
    }
    
}
