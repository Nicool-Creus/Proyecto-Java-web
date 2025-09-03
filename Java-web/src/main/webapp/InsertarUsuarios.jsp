<%@ page import="controlador.Conexion"%>
<%@ page import="java.sql.*" %> 
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Registrar usuarios</title>
</head>
<body class="container mt-5">

	<h2>Insertar Usuario</h2>
    <form action="UsuarioServlet" method="post" class="card p-4 shadow-lg">
        <input type="hidden" name="action" value="insertar">
        <div class="mb-3">
            <label>Usuario</label>
            <input type="text" name="usuario" class="form-control" required>
        </div>
        <div class="mb-3">
            <label>Contraseña</label>
            <input type="password" name="contrasena" class="form-control" required>
        </div>
        <button type="submit" class="btn btn-success">Guardar</button>
    </form>

	<!--<form name="usuario" action="UsarioServlet" method="POST">
	<input type="hidden" name="action" value="insertar"/>
	
       	<p>Usuario: <input type="text" name="nombres" required></p>
       	<p>Contraseña: <input type="password" name="contrasena" required></p>
       	
       	<p>Tipo de documento de identidad: <select name="tipoDocumento" required>
                    <option value="1">Cédula de ciudadania</option>
                    <option value="2">Tarjeta de identidad</option>
                    <option value="3">Cédula de extranjería</option>
                    <option value="4">Permiso especial de permanencia</option>
                    <option value="5">Permiso de protección temporal</option>
            </select></p>
       	
		<button type="submit">Registrar</button>
		
	</form> -->

</body>
</html>