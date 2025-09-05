package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import controlador.Conexion;
import modelo.Usuario;

public class UsuarioDAO {
	
	// INSERTAR
    public void insertarUsuario(Usuario usuario) {
        String sql = "INSERT INTO tblusuario (usuario, contrasena, idrol)"
                   + "VALUES (?, ?, ?)";
        try (Connection conn = new Conexion().conectarDB();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, usuario.getUsuario());
            ps.setString(2, usuario.getContrasena());
            ps.setInt(3, usuario.getIdRol());

            ps.executeUpdate();

        } catch (Exception e) {
        	System.out.println("Error insertando usuario: " + e.getMessage());
            e.printStackTrace();
            
        }
    }

    // CONSULTAR
    public Usuario consultarUsuario(int idUsuario) {
        Usuario usuario = null;
    	String sql = "SELECT * FROM tblusuario WHERE idusuario = ?";
        try (Connection conn = new Conexion().conectarDB();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setInt(1, idUsuario);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    usuario = new Usuario(
                            rs.getInt("idusuario"),
                            rs.getString("usuario"),
                            rs.getString("contrasena"),
                            rs.getInt("idrol")
                    );
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return usuario;
    }

    // ACTUALIZAR
    public void actualizarUsuario(Usuario usuario) {
        String sql = "UPDATE tblusuario SET usuario = ?, contrasena = ?"
                   + "WHERE idusuario = ?";
        try (Connection conn = new Conexion().conectarDB();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, usuario.getUsuario());
            ps.setString(2, usuario.getContrasena());
            ps.setInt(3, usuario.getIdUsuario());

            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();

        }
    }

    // ELIMINAR
    public void eliminarUsuario(int idUsuario) {
        String sql = "DELETE FROM tblusuario WHERE idusuario = ?";
        try (Connection conn = new Conexion().conectarDB();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setInt(1, idUsuario);
            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    // Listar usuarios
    public List<Usuario> listarUsuarios() throws SQLException {
        List<Usuario> usuarios = new ArrayList<>();
        String sql = "SELECT * FROM tblusuario";

        try (Connection conn = new Conexion().conectarDB();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            
            while (rs.next()) {
                Usuario usuario = new Usuario(
                    rs.getInt("idusuario"),
                    rs.getString("usuario"),
                    rs.getString("contrasena"),
                    rs.getInt("idrol")
                );
                usuarios.add(usuario);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
        return usuarios;
    }
    
    public void 

}
