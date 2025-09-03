<%@ page import="controlador.Conexion"%>
<%@ page import="java.sql.*" %> 
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Eliminar usuario</title>
</head>
<body>

	<form action="UsuariosServlet" method="POST">
        <input type="hidden" name="action" value="eliminar"/>

        <p>Id del usuario: <input type="number" name="idUsuario" required></p>

        <button type="submit">Eliminar</button>
	</form>

</body>
</html>