<%@ page import="controlador.Conexion"%>
<%@ page import="java.sql.*" %> 
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Actualizar programa de formación</title>
</head>
<body>

	<form name="formProgramasFormacion" action="ProgramasFormacionServlet" method="POST">
		<p>Código: <input type="number" name="codigoPrograma" required></p>
       	<p>Nuevo Nombre: <input type="text" name="nombrePrograma" required></p>
       	<p>Nueva Duración lectiva: <input type="number" name="duracionEtapaLectiva" required></p>
       	<p>Nueva Duración productiva: <input type="number" name="duracionEtapaProductiva" required></p>
       	<p>Nuevos Créditos: <input type="number" name="creditos" required></p>
       	<p>Nuevas Horas: <input type="number" name="horas" required></p>

        <button type="submit">actualizar</button>
        
	</form>

</body>
</html>