package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import controlador.Conexion;
import modelo.Usuario;

public class UsuarioDAO {
	
	// INSERTAR
    public boolean insertarUsuario(Usuario usuario) {
        String sql = "INSERT INTO tblusuario (usuario, contrasena)"
                   + "VALUES (?, ?)";
        try (Connection conn = new Conexion().conectarDB();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, usuario.getUsuario());
            ps.setString(2, usuario.getContrasena());

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    
    // Actualizar
    public boolean actualizarUsuario(Usuario usuario) {
    	String sql = "UPDATE tblusuario SET usuario = ?, contrasena = ? WHERE idUsuario = ?";
        try (Connection conn = new Conexion().conectarDB();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
        	ps.setString(1, usuario.getUsuario());
            ps.setString(2, usuario.getContrasena());
            ps.setInt(3, usuario.getIdUsuario());
            
            return ps.executeUpdate() > 0;
        
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public Usuario validarUsuario(String usuario, String contrasena) {
		
    	String sql = "SELECT * FROM tblusuario WHERE usuario = ? AND contrasena = ?";
    	
    	Usuario usuarios = null;
        Connection connection = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        try {
        	connection = new Conexion().conectarDB();
            stmt = connection.prepareStatement(sql);
            stmt.setString(1, usuario);
            stmt.setString(2, contrasena);
            rs = stmt.executeQuery();
            
            if (rs.next()) {
            	usuarios = new Usuario(
                        rs.getInt("idusuario"),
                        rs.getString("usuario"),
                        rs.getString("contrasena")
                    );
            }
		} catch (Exception e) {
			e.printStackTrace();
		}
    	
    	return usuarios;
    }
    
    // Obtener usuario por credenciales (para login)
    public Usuario obtenerUsuarioPorCredenciales(String usuario, String contrasena) {
        String sql = "SELECT idusuario, usuario FROM tblusuario WHERE usuario = ? AND contrasena = ?";
        
        try (Connection conn = new Conexion().conectarDB();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setString(1, usuario);
            ps.setString(2, contrasena);
            
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new Usuario(
                        rs.getInt("idusuario"),
                        rs.getString("usuario"),
                        null // No devolvemos la contraseña por seguridad
                    );
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
    
    // Consultar
    public Usuario obtenerUsuario(int idUsuario) {
    	String sql = "SELECT * FROM tblusuario WHERE idUsuario = ?";
        Usuario u = null;
        try (Connection conn = new Conexion().conectarDB();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, idUsuario);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                u = new Usuario(
                        rs.getInt("idUsuario"),
                        rs.getString("usuario"),
                        rs.getString("contrasena")
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return u;
    }
    
    
    // Verificar si el usuario ya existe
    /*public boolean usuarioExistente(String usuario) {
        String sql = "SELECT COUNT(*) as count FROM tblusuario WHERE usuario = ?";
        
        try (Connection conn = new Conexion().conectarDB();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setString(1, usuario);
            
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("count") > 0;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }*/
    
    // ELIMINAR
    public boolean eliminarUsuario(int idusuario) {
        String sql = "DELETE FROM tblusuario WHERE idusuario = ?";
        try (Connection conn = new Conexion().conectarDB();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setInt(1, idusuario);
            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    
    // Listar
    public java.util.List<Usuario> listaUsuarios() {
        java.util.List<Usuario> listaUsuarios = new java.util.ArrayList<>();
        String sql = "SELECT idusuario, usuario FROM tblusuario ORDER BY idusuario";
        
        try (Connection conn = new Conexion().conectarDB();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            
            while (rs.next()) {
                Usuario usuario = new Usuario(
                    rs.getInt("idusuario"),
                    rs.getString("usuario"),
                    null
                );
                listaUsuarios.add(usuario);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listaUsuarios;
    }

}