
package Modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;



//try {
//    Class.forName("com.mysql.jdbc.Driver");
//} catch (ClassNotFoundException ex) {
//    System.out.println("Error al registrar el driver de MySQL: " + ex);
//}

public class Conexion {
    
    Connection con;
    public Connection getConnection(){
        
        try{
            
            String myBD="jdbc:mysql://localhost:3306/sistemaventa?serverTimezone=UTC";
            con=DriverManager.getConnection(myBD,"root","");
            return con;
        }catch(SQLException e){
            System.out.println(e.toString());
        }
        return null;
    }
    
    
}
