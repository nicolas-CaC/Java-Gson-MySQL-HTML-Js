package models;

import config.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductosDao {

    Conexion conexion;
    Connection connect;
    
    public ProductosDao(){
        conexion = new Conexion();
        connect = conexion.getConnection();
    }
    
    public List<Productos> listarProductos(){
        
        PreparedStatement ps;
        ResultSet rs;
        List<Productos> productos = new ArrayList<>();
        
        try{
            ps = connect.prepareStatement("SELECT * FROM productos");
            rs = ps.executeQuery();
            
            while(rs.next()){
                
                int id = rs.getInt("id");
                String nombre = rs.getString("nombre");
                String marca = rs.getString("marca");
                int stock = rs.getInt("stock");
                String categoria = rs.getString("categoria");
                
                Productos producto = new Productos(id,stock,nombre,marca,categoria);
                productos.add(producto);
            }
            return productos;
        }
        catch(SQLException e){
            System.out.println(e.toString());
            return null;
        }
    }
    
    public Productos mostrarProducto(int refId){
        
        PreparedStatement ps;
        ResultSet rs;
        Productos producto = null;
        
        try{
            ps= connect.prepareStatement("SELECT * FROM productos WHERE id=?;");
            ps.setInt(1, refId);
            rs = ps.executeQuery();

            while(rs.next()){
                int id = rs.getInt("id");
                String nombre = rs.getString("nombre");
                String marca = rs.getString("marca");
                int stock = rs.getInt("stock");
                String categoria = rs.getString("categoria");
                producto = new Productos(id, stock,nombre, marca, categoria);
            }
                return producto;
        }
        catch(SQLException e){
            System.out.println(e.toString());
            return producto;                
        }
        
    }
        
    public boolean agregarProducto(Productos producto){

        PreparedStatement ps;

        try{
            ps = connect.prepareStatement("INSERT INTO productos(id, nombre, marca, stock, categoria) VALUES(0,?,?,?,?);");
//            ps.setInt(1,producto.getId());
            ps.setString(1, producto.getNombre());
            ps.setString(2, producto.getMarca());
            ps.setInt(3, producto.getStock());
            ps.setString(4, producto.getCategoria());
            ps.execute();
            return true;
        }
        catch(SQLException e){
            System.out.println(e.toString());
            return false;
        }
    }

    public boolean actualizarProducto(Productos producto){

        PreparedStatement ps;

        try{
            ps = connect.prepareStatement("UPDATE productos SET nombre=?, marca=?, stock=?, categoria=? WHERE id=?;");
            ps.setString(1, producto.getNombre());
            ps.setString(2, producto.getMarca());
            ps.setInt(3, producto.getStock());
            ps.setString(4, producto.getCategoria());
            ps.setInt(5,producto.getId());
            ps.execute();
            return true;            
        }
        catch(SQLException e){
            System.out.println(e.toString());
            return false;                
        }
    }

    public boolean borrarProducto(int refId){

        PreparedStatement ps;

          try{
            ps = connect.prepareStatement("DELETE FROM productos WHERE id=?;");
            ps.setInt(1, refId);
            ps.execute();
            return true;            
        }
        catch(SQLException e){
            System.out.println(e.toString());
            return false;                
        }
    }
}
