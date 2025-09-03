package controlador;

public class TestConexion {

	public static void main(String[] args) {

		Conexion test = new Conexion();
		
		if (test.conectarDB() != null) {
			System.out.println("Conectado a la BD");
		} else {
			System.out.println("No conectado a la BD");
		}

	}

}