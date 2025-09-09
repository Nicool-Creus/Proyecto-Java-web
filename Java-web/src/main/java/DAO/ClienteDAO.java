package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import controlador.Conexion;
import modelo.Cliente;

public class ClienteDAO {
	
	// INSERTAR
    public void insertarCliente(Cliente cliente) {
    	
    	// Sentencia SQL
        String sql = "INSERT INTO tblcliente (cedula, nombres, apellidos, direccion, telefono, correo)"
                   + "VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = new Conexion().conectarDB();
             PreparedStatement ps = conn.prepareStatement(sql)) {

        	// Se reemplazan los ? con los valores del objeto Cliente
            ps.setInt(1, cliente.getCedula());
            ps.setString(2, cliente.getNombres());
            ps.setString(3, cliente.getApellidos());
            ps.setString(4, cliente.getDireccion());
            ps.setString(5, cliente.getTelefono());
            ps.setString(6, cliente.getCorreo());

            // Ejecutar la sentencia (INSERT)
            ps.executeUpdate();

        } catch (Exception e) {
        	System.out.println("Error insertando cliente: " + e.getMessage());
            e.printStackTrace();
            
        }
    }

    // CONSULTAR
    public Cliente consultarCliente(int idCliente) {
        Cliente cliente = null;
    	String sql = "SELECT * FROM tblcliente WHERE idcliente = ?";
        try (Connection conn = new Conexion().conectarDB();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
        	// Establece el valor para el WHERE
            ps.setInt(1, idCliente);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) { // Si encuentra un registro
                    cliente = new Cliente(
                            rs.getInt("idcliente"),
                            rs.getInt("cedula"),
                            rs.getString("nombres"),
                            rs.getString("apellidos"),
                            rs.getString("direccion"),
                            rs.getString("telefono"),
                            rs.getString("correo")
                    );
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cliente; // Retorna el objeto Cliente o null si no existe
    }

    // ACTUALIZAR
    public void actualizarCliente(Cliente cliente) {
        String sql = "UPDATE tblcliente SET cedula = ?, nombres = ?, apellidos = ?, direccion = ?, telefono = ?, correo = ?"
                   + "WHERE idcliente = ?";
        try (Connection conn = new Conexion().conectarDB();
             PreparedStatement ps = conn.prepareStatement(sql)) {

        	// Cargar los valores nuevos
            ps.setInt(1, cliente.getCedula());
            ps.setString(2, cliente.getNombres());
            ps.setString(3, cliente.getApellidos());
            ps.setString(4, cliente.getDireccion());
            ps.setString(5, cliente.getTelefono());
            ps.setString(7, cliente.getCorreo());
            ps.setInt(8, cliente.getIdcliente());

            // Ejecutar UPDATE
            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();

        }
    }

    // ELIMINAR
    public void eliminarCliente(int idcliente) {
        String sql = "DELETE FROM tblcliente WHERE idcliente = ?";
        try (Connection conn = new Conexion().conectarDB();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
        	// ID del cliente a borrar
            ps.setInt(1, idcliente);
            ps.executeUpdate(); // Ejecuta DELETE

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    // Listar clientes
    public List<Cliente> listarClientes() throws SQLException {
        List<Cliente> listaClientes = new ArrayList<>();
        String sql = "SELECT * FROM tblcliente";

        try (Connection conn = new Conexion().conectarDB();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            
        	// Recorrer todos los registros
            while (rs.next()) {
                Cliente cliente = new Cliente(
                    rs.getInt("idcliente"),
                    rs.getInt("cedula"),
                    rs.getString("nombres"),
                    rs.getString("apellidos"),
                    rs.getString("direccion"),
                    rs.getString("telefono"),
                    rs.getString("correo")
                );
                // Se agrega a la lista
                listaClientes.add(cliente);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
        return listaClientes; // Retorna la lista de clientes
    }
}