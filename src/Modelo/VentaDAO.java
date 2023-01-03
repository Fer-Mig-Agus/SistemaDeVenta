
package Modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class VentaDAO {
    
     Connection con;
     Conexion cn=new Conexion();
     PreparedStatement ps;
     ResultSet rs;
     int r;
     
     public int IdVenta(){
         int id=0;
         String sql="SELECT MAX(id) FROM ventas";
         try{
             con=cn.getConnection();
             ps=con.prepareStatement(sql);
             rs=ps.executeQuery();
             if(rs.next()){
                 id=rs.getInt(1);
             }
         }catch(SQLException e){
             
         }
         return id;
     }
    
    public int RegistrarVenta(Venta v){
        String slq="INSERT INTO ventas (cliente,vendedor,total) VALUES (?,?,?)";
        try{
            con=cn.getConnection();
            ps=con.prepareStatement(slq);
            ps.setString(1, v.getCliente());
            ps.setString(2,v.getVendedor());
            ps.setDouble(3, v.getTotal());
            ps.execute();
        }catch(SQLException e){
            System.out.println(e.toString());
        }finally{
            try{
                con.close();
            }catch(SQLException ex){
                System.out.println(ex.toString());
            }
        }
        
        return r;
    }
    
    public int RegistrarDetalle(Detalle Dv){
        String slq="INSERT INTO detalle (cod_pro,cantidad,precio,id_venta) VALUES (?,?,?,?)";
        try{
            con=cn.getConnection();
            ps=con.prepareStatement(slq);
            ps.setString(1, Dv.getCod_pro());
            ps.setInt(2,Dv.getCantidad());
            ps.setDouble(3, Dv.getPrecio());
            ps.setInt(4, Dv.getId());
            ps.execute();
        }catch(SQLException e){
            System.out.println(e.toString());
        }finally{
            try{
                con.close();
            }catch(SQLException ex){
                System.out.println(ex.toString());
            }
        }
        return r;
    }
    
}
