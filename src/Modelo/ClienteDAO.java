/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author usuariopc
 */
public class ClienteDAO {
    Conexion cn= new Conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    
    public boolean registrarCliente(Cliente cli){
        String sql="INSERT INTO clientes (dni,nombre,telefono,direccion,razon) VALUES(?,?,?,?,?)";
        try{
            con=cn.getConnection();
            ps=con.prepareStatement(sql);
            ps.setInt(1,cli.getDni());
            ps.setString(2,cli.getNombre());
            ps.setInt(3,cli.getTelefono());
            ps.setString(4,cli.getDireccion());
            ps.setString(5,cli.getRazon());
            ps.execute();
            return true;
            
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null,e.toString());
            return false;
        }finally{
            try{
                con.close();
            }catch(SQLException e){
                System.out.println(e.toString());
            }
        
        }
        
    }
    
    public List ListaCliente(){
        List<Cliente> ListaCl=new ArrayList();
        String slq="SELECT * FROM clientes";
        try{
            con=cn.getConnection();
            ps=con.prepareStatement(slq);
            rs=ps.executeQuery();
            while(rs.next()){
                Cliente cl=new Cliente();
                cl.setId(rs.getInt("id"));
                cl.setDni(rs.getInt("dni"));
                cl.setNombre(rs.getString("nombre"));
                cl.setTelefono(rs.getInt("telefono"));
                cl.setDireccion(rs.getString("direccion"));
                cl.setRazon(rs.getString("razon"));
                ListaCl.add(cl);
            }
            
        }catch(SQLException e){
            System.out.println(e.toString());
        }
        return ListaCl;
    }
    public boolean EliminarCliente(int id){
        String slq="DELETE FROM clientes WHERE id = ?";
        try{
            ps=con.prepareStatement(slq);
            ps.setInt(1, id);
            ps.execute();
            return true;
        }catch(SQLException e){
            System.out.println(e.toString());
            return false;
        }finally{
            try{
                con.close();
            }catch(SQLException ex){
                System.out.println(ex.toString());
            }
        }
    }
    public boolean ModificarCliente(Cliente cl){
        String sql="UPDATE clientes SET dni=?,nombre=?,telefono=?,direccion=?, razon=? WHERE id=?";
        try{
            ps=con.prepareStatement(sql);
            ps.setInt(1, cl.getDni());
            ps.setString(2,cl.getNombre());
            ps.setInt(3, cl.getTelefono());
            ps.setString(4,cl.getDireccion());
            ps.setString(5,cl.getRazon());
            ps.setInt(6, cl.getId());
            ps.execute();
            return true;
        }catch(SQLException e){
            System.out.println(e.toString());
            return false;
        }finally{
            try{
                con.close();
            }catch(SQLException ex){
                System.out.println(ex.toString());
            }
        }
        
    }
    
    public Cliente BuscarCliente(int dni){ 
        Cliente cl=new Cliente();
        String slq="SELECT * FROM clientes WHERE dni=?";
        try{
            con=cn.getConnection();
            ps=con.prepareStatement(slq);
            ps.setInt(1, dni);
            rs=ps.executeQuery();
            if(rs.next()){
                cl.setNombre(rs.getString("nombre"));
                cl.setTelefono(rs.getInt("telefono"));
                cl.setDireccion(rs.getString("direccion"));
                cl.setRazon(rs.getString("razon"));
            }
            
        }catch(SQLException e){
            System.out.println(e.toString());
        }
        return cl;
    }
    
}
