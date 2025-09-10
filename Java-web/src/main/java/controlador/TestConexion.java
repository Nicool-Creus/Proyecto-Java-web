package controlador;

public class TestConexion {

	/* Se crea un método principal "main" que permite hacer un test de conexión con la base de datos
	 * Se instancia un objeto de la clase "Conexión", después se llama el método "conectarDB()" para establecer
	 * la conexión.
	 * Si el método devuleve un objeto diferente de "null", significa que la conexión fue exitosa y se mpostrará
	 * el mensaje en consola.
	 * Si devuelve "null", entonces significa que no se pudo establecer la conexión y se mostrará el mesnaje en consola*/
	public static void main(String[] args) {

		//Se crea un objeto de la clase Conexion
		Conexion test = new Conexion();
		
		//Se prueba la conexión y muestra el resultado
		if (test.conectarDB() != null) {
			System.out.println("Conectado a la BD");
		} else {
			System.out.println("No conectado a la BD");
		}

	}

}