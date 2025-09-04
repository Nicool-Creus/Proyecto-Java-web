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
    public boolean insertarCliente(Cliente cliente) {
        String sql = "INSERT INTO tblcliente (cedula, nombres, apellidos, direccion, telefono)"
                   + "VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = new Conexion().conectarDB();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, cliente.getCedula());
            ps.setString(2, cliente.getNombres());
            ps.setString(3, cliente.getApellidos());
            ps.setString(4, cliente.getDireccion());
            ps.setString(5, cliente.getTelefono());


            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // CONSULTAR
    public Cliente consultarCliente(int idCliente) {
        Cliente cliente = null;
    	String sql = "SELECT * FROM tblcliente WHERE idcliente = ?";
        try (Connection conn = new Conexion().conectarDB();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setInt(1, idCliente);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    cliente = new Cliente(
                            rs.getInt("idcliente"),
                            rs.getInt("cedula"),
                            rs.getString("nombres"),
                            rs.getString("apellidos"),
                            rs.getString("direccion"),
                            rs.getString("telefono")
                    );
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cliente;
    }

    // ACTUALIZAR
    public boolean actualizarCliente(Cliente cliente) {
        String sql = "UPDATE tblcliente SET cedula = ?, nombres = ?, apellidos = ?, direccion = ?, telefono = ?"
                   + "WHERE idcliente = ?";
        try (Connection conn = new Conexion().conectarDB();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, cliente.getCedula());
            ps.setString(2, cliente.getNombres());
            ps.setString(3, cliente.getApellidos());
            ps.setString(4, cliente.getDireccion());
            ps.setString(5, cliente.getTelefono());
            ps.setInt(6, cliente.getIdcliente());

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // ELIMINAR
    public boolean eliminarCliente(int idcliente) {
        String sql = "DELETE FROM tblcliente WHERE idcliente = ?";
        try (Connection conn = new Conexion().conectarDB();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setInt(1, idcliente);
            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    
    // Listar clientes
    public List<Cliente> listarClientes() throws SQLException {
        List<Cliente> clientes = new ArrayList<>();
        String sql = "SELECT * FROM tblCliente";

        try (Connection conn = new Conexion().conectarDB();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            
            while (rs.next()) {
                Cliente cliente = new Cliente(
                    rs.getInt("idcliente"),
                    rs.getInt("cedula"),
                    rs.getString("nombres"),
                    rs.getString("apellidos"),
                    rs.getString("direccion"),
                    rs.getString("telefono")
                );
                clientes.add(cliente);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
        return clientes;
    }
}