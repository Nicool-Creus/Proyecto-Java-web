<%@ page import="controlador.Conexion"%>
<%@ page import="java.sql.*" %> 
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>REGISTRO CALIFICADO</title>
</head>
<body>

	<h1 align="center">FORMULARIO DE PROGRAMAS DE FORMACIÓN</h1>
	
	<% 
		
		Conexion test = new Conexion();
		
		if (!test.conectarDB().isClosed()) {
			out.print("<h2>Conectado a la BD</h2>");
		} else {
			out.print("<h2>No conectado a la BD</h2>");
		}
	
	%>
	
	<style>
    body {
        font-family: Arial, sans-serif;
        text-align: center;
        background-color: #f4f4f4;
        margin-top: 50px;
    }
    h1 {
        color: #333;
    }
    .menu {
        margin-top: 30px;
    }
    .menu a {
        display: block;
        margin: 10px auto;
        padding: 12px 20px;
        width: 250px;
        text-decoration: none;
        color: white;
        background-color: #007BFF;
        border-radius: 5px;
        font-weight: bold;
    }
    .menu a:hover {
        background-color: #0056b3;
    }
</style>
</head>
<body>

    <h1>Menú de Programas de Formación</h1>

    <div class="menu">
        <a href="InsertarPrograma.jsp">Insertar Programa</a>
        <a href="ConsultarPrograma.jsp">Consultar Programa</a>
        <a href="ActualizarPrograma.jsp">Actualizar Programa</a>
        <a href="EliminarPrograma.jsp">Eliminar Programa</a>
    </div>
    
    <form action="ProgramasFormacionServlet" method="get" target="_blank">
	    <input type="hidden" name="action" value="generarPDF">
	    <button type="submit">Descargar PDF</button>
	</form>
	
</body>
</html>