package controlador;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {
	
	/* Se crea un método público de tipo "Connection" llamado "conectarDB", cuya función es establecer la conexión con la base de datos MySQL,
	 * para esto, se necitan los parametros de conexión que son: el driver, la url de conexión el usuario de la base datos y la contraseña.
	 * Dentro del método se declara un objeto "Connection" que se inicializa usando el driver de MySQL y la clase DriverManager. 
	 * Si la conexión es exitosa se retorna el objeto "connection", de lo contrario se captura la excepción y se muestra un mensaje de error.
	 */
	public Connection conectarDB() {
		//Crear e inicializar un objeto de la clase connection
		Connection connection = null;
		try {
			//Cargar el driver de MySQL
			Class.forName("com.mysql.cj.jdbc.Driver");
			//Establecer la conexión con la base de datos
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