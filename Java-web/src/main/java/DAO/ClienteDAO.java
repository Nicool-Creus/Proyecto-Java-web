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
	
	/* En la clase "ClienteDAO": se implementa las operaciones CRUD para la entidad "Cliente" utilizando JDBC y la clase "Conexion".
	 * Cada método usa consultas SQL preparadas para evitar inyección de código y gestionar los datos.
	 * 
	 * Operaciones:
	 * 
	 * insertarCliente:inserta un nuevo registro en la tabla tblcliente.
	 * Reemplaza los parámetros (?) con los valores del objeto Cliente.
	 * 
	 * consultarCliente: busca un cliente por su id y si existe, retorna un objeto "Cliente" con los datos, si no, retorna null.
	 * 
	 * actualizarCliente: actualiza los campos de un cliente existente e identifica el registro por idcliente.
	 * 
	 * eliminarCliente: elimina el registro correspondiente al id en la tabla 
	 * 
	 * listarClientes: recupera todos los registros de "tblcliente" y crea objetos "Cliente" por cada fila y los agrega a una lista.
	 * Retorna la lista completa de clientes
	 * */
	
	// INSERTAR
    public void insertarCliente(Cliente cliente) {
    	
    	// Sentencia SQL
        String sql = "INSERT INTO tblcliente (cedula, nombres, apellidos, direccion, telefono)"
                   + "VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = new Conexion().conectarDB();
             PreparedStatement ps = conn.prepareStatement(sql)) {

        	// Se reemplazan los ? con los valores del objeto Cliente
            ps.setInt(1, cliente.getCedula());
            ps.setString(2, cliente.getNombres());
            ps.setString(3, cliente.getApellidos());
            ps.setString(4, cliente.getDireccion());
            ps.setString(5, cliente.getTelefono());

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
                            rs.getString("telefono")
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
        String sql = "UPDATE tblcliente SET cedula = ?, nombres = ?, apellidos = ?, direccion = ?, telefono = ?"
                   + "WHERE idcliente = ?";
        try (Connection conn = new Conexion().conectarDB();
             PreparedStatement ps = conn.prepareStatement(sql)) {

        	// Cargar los valores nuevos
            ps.setInt(1, cliente.getCedula());
            ps.setString(2, cliente.getNombres());
            ps.setString(3, cliente.getApellidos());
            ps.setString(4, cliente.getDireccion());
            ps.setString(5, cliente.getTelefono());
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
                    rs.getString("telefono")
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