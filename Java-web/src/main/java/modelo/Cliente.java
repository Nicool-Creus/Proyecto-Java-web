package modelo;

public class Cliente {
	
	public int idcliente;
	public int cedula; 
	public String nombres;
	public String apellidos;
	public String direccion;
	public String telefono;
 
	public Cliente(int idcliente, int cedula, String nombres, String apellidos, String direccion, String telefono) {
		super();
		this.idcliente = idcliente;
		this.cedula = cedula;
		this.nombres = nombres;
		this.apellidos = apellidos;
		this.direccion = direccion;
		this.telefono = telefono;
	}



	public Cliente(int cedula, String nombres, String apellidos, String direccion, String telefono) {
		super();
	}



	public int getIdcliente() {
		return idcliente;
	}

	public void setIdcliente(int idcliente) {
		this.idcliente = idcliente;
	}

	public int getCedula() {
		return cedula;
	}

	public void setCedula(int cedula) {
		this.cedula = cedula;
	}

	public String getNombres() {
		return nombres;
	}

	public void setNombres(String nombres) {
		this.nombres = nombres;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	
	
}