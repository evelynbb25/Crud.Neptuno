/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import com.mysql.jdbc.CallableStatement;
import com.mysql.jdbc.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import entidades.Cliente;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Luisa
 */
public class ClientesDAO {

    private Connection conexion = null;

    //establecer la conexion con la base de datos
    public ClientesDAO() {
        try {
            conexion = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/neptuno", "root", "");
            System.out.println("conexion exitosa");
        } catch (SQLException ex) {
            System.err.println("Error al conectar: " + ex.getMessage());
            //ex.printStackTrace(); 
        }
    }

    //comprobar la conexion con la base de datos
    public Connection getConexion() {
        return conexion;
    }

    //Consultar cliente por ID
    public Cliente read(Integer id) {
        Cliente cliente = null;
        PreparedStatement stm = null;
        if (this.conexion == null) {
            return null;
        }
        try {
            String sql = "SELECT * FROM clientes WHERE id = ?";
            stm = conexion.prepareStatement(sql);
            stm.setInt(1, id);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                cliente = new Cliente(
                        rs.getInt("id"),
                        rs.getString("codigo"),
                        rs.getString("empresa"),
                        rs.getString("contacto"),
                        rs.getString("cargo_Contacto"),
                        rs.getString("direccion"),
                        rs.getString("ciudad"),
                        rs.getString("region"),
                        rs.getString("cp"),
                        rs.getString("pais"),
                        rs.getString("telefono"),
                        rs.getString("fax")
                );
            }
            stm.close();
        } catch (SQLException ex) {
            System.out.println("Error en la select " + ex.getMessage() + "\nQuery: " + stm.toString());
        }
        return cliente;
    }

    //insertar un registro 
    public Boolean insert(Cliente cliente) {
        Boolean result = false;
        PreparedStatement stm = null;

        if (conexion == null || cliente == null) {
            System.err.println("Error la conexion es nula o empleado es nulo");
            return result;
        }
        //la query se realiza sin id porque la base de datos está con la opcion de autoincremento*/
        try {
            String sql = "INSERT INTO clientes (codigo, empresa, contacto, cargo_Contacto, direccion, ciudad,"
                    + "region,cp, pais, telefono, fax) VALUES (? , ?, ?, ?,?, ?, ?, ?, ?, ?, ?)";

            stm = conexion.prepareStatement(sql);

            stm.setString(1, cliente.getCodigo());
            stm.setString(2, cliente.getEmpresa());
            stm.setString(3, cliente.getContacto());
            stm.setString(4, cliente.getCargo_Contacto());
            stm.setString(5, cliente.getDireccion());
            stm.setString(6, cliente.getCiudad());
            stm.setString(7, cliente.getRegion());
            stm.setString(8, cliente.getCp());
            stm.setString(9, cliente.getPais());
            stm.setString(10, cliente.getTelefono());
            stm.setString(11, cliente.getFax());

            if (stm.executeUpdate() > 0) {
                result = true;
            }

        } catch (SQLException ex) {
            System.err.println("Error en el insert: " + ex.getMessage() + "SQL: " + stm.toString());
        } finally {
            try {
                if (stm != null) {
                    stm.close();;
                }
            } catch (SQLException e) {
                System.err.println("Error al cerrar la conexion : " + e.getMessage());
            }
        }
        return result;
    }

    //eliminar un registro
    public Boolean delete(Integer id) {
        Boolean resultado = false;
        PreparedStatement stmt = null;
        try {
            String sql = "DELETE FROM clientes WHERE id = ?";
            stmt = conexion.prepareStatement(sql);
            stmt.setInt(1, id);
            resultado = stmt.execute();
            stmt.close();
            System.out.println();
        } catch (SQLException e) {
            System.err.println("Error en el Delete: " + e.getMessage() + " " + stmt.toString());
        }
        return resultado;

    }

    //modificar un registro 
    public Boolean update(Cliente cliente) {
        Boolean result = false;
        PreparedStatement stmt = null;
        if (conexion == null || cliente == null) {
            return false;
        }
        try {

            String sql = "UPDATE clientes SET codigo = ?, empresa = ?, contacto = ?, cargo_Contacto = ?, "
                    + " direccion = ?, ciudad = ?, region = ?, cp = ?, pais = ?, telefono = ?, fax = ?"
                    + "WHERE id = ?";
            stmt = conexion.prepareStatement(sql);
            
            stmt.setString(1, cliente.getCodigo());
            stmt.setString(2, cliente.getEmpresa());
            stmt.setString(3, cliente.getContacto());
            stmt.setString(4, cliente.getCargo_Contacto());
            stmt.setString(5, cliente.getDireccion());
            stmt.setString(6, cliente.getCiudad());
            stmt.setString(7, cliente.getRegion());
            stmt.setString(8, cliente.getCp());
            stmt.setString(9, cliente.getPais());
            stmt.setString(10, cliente.getTelefono());
            stmt.setString(11, cliente.getFax());
            stmt.setInt(12, cliente.getId());
            
            if (stmt.executeUpdate() > 0) {
                result = true;
            }
        } catch (SQLException ex) {
            System.err.println("Error en el Update: " + ex.getMessage() + " Query " + stmt.toString());
        } finally {
            try {
                if (stmt != null) {
                    stmt.close();
                }
            } catch (SQLException ex) {
                System.out.println("Error al cerrar la conexion " + ex.getMessage());
            }
        }
        return result;
    }
    
    //listar con filtro 
    public ArrayList<Cliente> filter(Integer cont) {
        ArrayList<Cliente> lista = null;
        PreparedStatement pStmt = null;

        String selectLimitSql = "SELECT * FROM clientes limit 10 offset ?";
        // select with limit
        try {
            pStmt = conexion.prepareStatement(selectLimitSql);

            pStmt.setInt(1, cont);
            //pStmt.setInt(2, desde);
            ResultSet rs = pStmt.executeQuery();
            lista = new ArrayList<Cliente>();

            while (rs.next()) {
                Cliente c = new Cliente(
                        rs.getInt("id"),
                        rs.getString("codigo"),
                        rs.getString("empresa"),
                        rs.getString("contacto"),
                        rs.getString("cargo_Contacto"),
                        rs.getString("direccion"),
                        rs.getString("ciudad"),
                        rs.getString("region"),
                        rs.getString("cp"),
                        rs.getString("pais"),
                        rs.getString("telefono"),
                        rs.getString("fax"));
                lista.add(c);
            }

            //rs = pStmt.executeQuery();
            rs.close();
        } catch (SQLException e) {
            System.out.println("Error en listar empleados " + e.getMessage());
        }
        return lista;
    }

    //listar todos
    public ArrayList<Cliente> listarAll() {
        ArrayList<Cliente> lista = new ArrayList<>();
        PreparedStatement stm = null;
        String sql = "SELECT * FROM clientes";

        try {
            stm = conexion.prepareStatement(sql);
            ResultSet rs = stm.executeQuery(sql);
            while (rs.next()) {
                Cliente cliente;
                cliente = new Cliente(
                        rs.getInt("id"),
                        rs.getString("codigo"),
                        rs.getString("empresa"),
                        rs.getString("contacto"),
                        rs.getString("cargo_Contacto"),
                        rs.getString("direccion"),
                        rs.getString("ciudad"),
                        rs.getString("region"),
                        rs.getString("cp"),
                        rs.getString("pais"),
                        rs.getString("telefono"),
                        rs.getString("fax")
                );
                lista.add(cliente);
            }

        } catch (SQLException e) {
            System.out.println("Error en listar empleados " + e.getMessage());
        }
        return lista;
    }

}
