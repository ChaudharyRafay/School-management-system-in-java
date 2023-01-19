
package db;

import java.sql.Connection;
import java.sql.DriverManager;

public class MyConnection {
    public static Connection conn=null;
    public static Connection getConnection(){
        try {
        Class.forName("com.mysql.jdbc.Driver");
         conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/school management system","root","");

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }
      
}
