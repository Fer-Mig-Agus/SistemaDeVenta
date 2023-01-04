
package Modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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
    
    
    public boolean ActualizarStock(int cant,String cod){
        String sql="UPDATE productos SET stock = ? WHERE codigo =?";
        try{
            con=cn.getConnection();
            ps=con.prepareStatement(sql);
            ps.setInt(1, cant);
            ps.setString(2, cod);
            ps.execute();
            return true;
        }catch(SQLException e){
            System.out.println(e.toString());
            return false;
        }
    }
    
    public List ListarVentas(){
        List<Venta> ListaVenta=new ArrayList();
        String slq="SELECT * FROM ventas";
        try{
            con=cn.getConnection();
            ps=con.prepareStatement(slq);
            rs=ps.executeQuery();
            while(rs.next()){
                Venta Vent=new Venta();
                Vent.setId(rs.getInt("id"));
                Vent.setCliente(rs.getString("cliente"));
                Vent.setVendedor(rs.getString("vendedor"));
                Vent.setTotal(rs.getDouble("total"));
                ListaVenta.add(Vent);
            }
            
        }catch(SQLException e){
            System.out.println(e.toString());
        }
        return ListaVenta;
    }
    
}
