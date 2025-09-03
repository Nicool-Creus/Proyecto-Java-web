package controlador;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {
	
	public Connection conectarDB() {
		//Crear e inicializar un objeto de la clase connection
		Connection connection = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/bd_tiendamascotas?useSSL=false&serverTimezone=UTC", "root", "2556229");
			System.out.println("Conectado exitosamente a la base de datos");
		} catch (ClassNotFoundException e) {
            System.out.println("Error, no se encontró el driver de MySQL");
            e.printStackTrace();
        } catch (SQLException e) {
			System.out.println("Error, no se pudo conectar a la base de datos");
		}
		return connection; //Retorna la conexión abierta con la BD
	}

}