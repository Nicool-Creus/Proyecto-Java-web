<%@ page import="controlador.Conexion"%>
<%@ page import="java.sql.*" %> 
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Consultar programa de formación</title>
</head>
<body>

	<form action="ProgramasFormacionServlet" method="GET">
        <input type="hidden" name="action" value="consultar"/>

        <p>Código: <input type="number" name="codigoPrograma" required></p>

        <button type="submit">Consultar</button>
    </form>

</body>
</html>