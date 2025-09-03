package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import controlador.Conexion;
import modelo.Roles;

public class RolesDAO {
	
	// INSERTAR
    public static boolean insertarRol(Roles rol) {
        String sql = "INSERT INTO tblroles (idrol, nombrerol)"
                   + "VALUES (?, ?)";
        try (Connection conn = new Conexion().conectarDB();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, rol.getIdRol());
            ps.setString(2, rol.getNombreRol());

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    
    // Eliminar rol
    public static boolean eliminarRol(int idRol) {
        String sql = "DELETE FROM tblroles WHERE idrol = ?";
        try (Connection conn = new Conexion().conectarDB();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setInt(1, idRol);
            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    
    // Consultar si el rol ya existe
    public Roles consultarRol(int idRol) {
        Roles rol = null;
        String sql = "SELECT * FROM tblroles WHERE idrol = ?";
        try (Connection conn = new Conexion().conectarDB();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, idRol);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    rol = new Roles(
                        rs.getInt("idrol"),
                        rs.getString("nombrerol")
                    );
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rol;
    }
}

