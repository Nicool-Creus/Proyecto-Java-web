<%@ page import="controlador.Conexion"%>
<%@ page import="java.sql.*" %> 
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Registrar programa de formación</title>
</head>
<body>
	
	<form name="formProgramasFormacion" action="ProgramasFormacionServlet" method="POST">
		<input type="hidden" name="action" value="insertar"/>

        <p>Código: <input type="number" name="codigoPrograma" required></p>
        <p>Nombre: <input type="text" name="nombrePrograma" required></p>
        <p>Duración lectiva: <input type="number" name="duracionEtapaLectiva" required></p>
        <p>Duración productiva: <input type="number" name="duracionEtapaProductiva" required></p>
        <p>Créditos: <input type="number" name="creditos" required></p>
        <p>Horas: <input type="number" name="horas" required></p>

        <button type="submit">Insertar</button>
    </form>

</body>
</html>