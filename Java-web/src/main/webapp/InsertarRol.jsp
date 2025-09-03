<%@ page import="controlador.Conexion"%>
<%@ page import="java.sql.*" %> 
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Tienda de mascotas</title>
</head>
<body>

	<form name="rol" action="RolesServlet" method="POST">
	<input type="hidden" name="action" value="insertar"/>
	
		<p>Id del rol: <input type="number" name="idRol" required></p>
       	
       	<p>Nombre del rol: <select name="nombreRol" required>
       		<option value="Administrador">Administrador</option>
            <option value="Empleado">Empleado</option>
        </select></p>
		
		<button type="submit">Registrar</button>
		
	</form>

</body>
</html>