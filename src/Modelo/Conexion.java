package Modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class Conexion {

    Connection con;

    public Connection getConnection() {

        try {
            //String access="jdbc:ucanaccess://D:/ventas.accdb";
            String myBD = "jdbc:mysql://localhost:3306/sistemaventa?serverTimezone=UTC";
            con = DriverManager.getConnection(myBD,"root","");
            return con;
        } catch (SQLException e) {
//            System.out.println(e.toString());
            JOptionPane.showMessageDialog(null, "No se encontró Base de Datos");
        }
        return null;
    }

}
