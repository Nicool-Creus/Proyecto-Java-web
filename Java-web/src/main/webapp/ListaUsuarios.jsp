<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="modelo.Usuario" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Lista de usuarios</title>
</head>
<body class="container mt-5">

	<h2>Usuarios Registrados</h2>
    <a href="InsertarUsuarios.jsp" class="btn btn-success mb-3">Nuevo Usuario</a>
    <table class="table table-bordered table-striped">
        <thead class="table-dark">
            <tr>
                <th>ID</th>
                <th>Usuario</th>
            </tr>
        </thead>
        <tbody>
            <%
                List<Usuario> listaUsuarios = (List<Usuario>) request.getAttribute("ListaUsuarios");
                if (listaUsuarios != null) {
                    for (Usuario u : listaUsuarios) {
            %>
                <tr>
                    <td><%= u.getIdUsuario() %></td>
                    <td><%= u.getUsuario() %></td>
                    <td><%= u.getContrasena() %></td>
                    <td>
                        <!-- Formulario actualizar -->
                        <form action="UsuarioServlet" method="post" style="display:inline-block;">
                            <input type="hidden" name="action" value="actualizar">
                            <input type="hidden" name="idUsuario" value="<%= u.getIdUsuario() %>">
                            <input type="text" name="usuario" value="<%= u.getUsuario() %>" required>
                            <input type="text" name="contrasena" value="<%= u.getContrasena() %>" required>
                            <button type="submit" class="btn btn-primary btn-sm">Actualizar</button>
                        </form>

                        <!-- Formulario eliminar -->
                        <form action="UsuarioServlet" method="post" style="display:inline-block;">
                            <input type="hidden" name="action" value="eliminar">
                            <input type="hidden" name="idUsuario" value="<%= u.getIdUsuario() %>">
                            <button type="submit" class="btn btn-danger btn-sm">Eliminar</button>
                        </form>
                    </td>
                </tr>
            <%
                    }
                }
            %>
        </tbody>
    </table>

	<!--<h1>Lista de usuarios</h1>
	<%--
	    <%
		    Usuario u = (Usuario) request.getAttribute("usuario");
		    	        if (u != null) {
		    %>
	        <table>
	            <tr><th>Id del usuario</th><td><%= u.getIdUsuario() %></td></tr>
	            <tr><th>Usuario</th><td><%= u.getUsuario() %></td></tr>
	            <tr><th>Contraseña</th><td><%= u.getContrasena() %></td></tr>  
	        </table>
	    <%
	        } else {
	    %>
	        <h2 style="color:red;">No existe un usuario con ese id, digite otro</h2>
	    <%
	        }
	    %>
	    --%>
	
	    <br><br>
	    <a href="Index.jsp">⬅ Volver al Menú</a> -->

</body>
</html>