<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="modelo.Usuario" %>
<!DOCTYPE html>

<%
    Usuario usuarios = (Usuario) session.getAttribute("usuarioLogueado");
    if (usuarios == null) {
        response.sendRedirect("Login.jsp");
    }
%>

<html>
<head>
<meta charset="UTF-8">
<title>Index</title>
</head>
<body>

	<h2>Bienvenido, <%= usuarios.getUsuario() %>!</h2>
    <a href="CerrarSesion.jsp">Cerrar sesión</a>

</body>
</html>