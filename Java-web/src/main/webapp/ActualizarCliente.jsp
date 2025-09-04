<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.sql.*" %> 
<%@ page import="controlador.Conexion"%>
<%@ page import="modelo.Cliente"%>
<%@ page import="DAO.ClienteDAO"%>
<%@ page import="java.util.List"%>

<%
    // Obtener la lista de todos los clientes
    ClienteDAO clienteDAO = new ClienteDAO();
    List<Cliente> listaClientes = clienteDAO.listarClientes(); 
    
    // Obtener el cliente específico si se ha seleccionado uno
    Cliente cliente = null;
    if (request.getParameter("idCliente") != null) {
        int idCliente = Integer.parseInt(request.getParameter("idCliente"));
        cliente = clienteDAO.consultarCliente(idCliente); 
    }
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Actualizar cliente</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">
    <style>
        :root {
            --primary-color: #4e73df;
            --secondary-color: #6f42c1;
        }
        
        body {
            background-color: #f8f9fc;
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
        }
        
        .card {
            border-radius: 10px;
            box-shadow: 0 0.15rem 1.75rem 0 rgba(58, 59, 69, 0.15);
            margin-bottom: 20px;
            border: none;
        }
        
        .card-header {
            background: linear-gradient(180deg, var(--primary-color), var(--secondary-color));
            color: white;
            border-radius: 10px 10px 0 0 !important;
            font-weight: bold;
        }
        
        .navbar {
            background: linear-gradient(90deg, var(--primary-color), var(--secondary-color));
            box-shadow: 0 0.15rem 1.75rem 0 rgba(58, 59, 69, 0.15);
        }
        
        .back-btn {
            margin-bottom: 20px;
        }
    </style>
</head>
<body>
   
    <div class="container mt-5">
        <a href="Index.jsp" class="btn btn-primary back-btn">
            <i class="fas fa-arrow-left me-1"></i> Volver al Inicio
        </a>
        
        <div class="row justify-content-center">
            <div class="col-md-8">
                <div class="card">
                    <div class="card-header py-3">
                        <h5 class="card-title m-0"><i class="fas fa-user-edit me-2"></i>Actualizar Cliente</h5>
                    </div>
                    <div class="card-body">
                        <form method="post" action="ActualizarCliente.jsp" class="mb-4">
                            <label for="idCliente" class="form-label">Seleccione el Cliente a Actualizar</label>
                            <div class="input-group">
                                <select class="form-select" id="idCliente" name="idCliente" required>
                                    <option value="">-- Seleccione un cliente --</option>
                                    <% for (Cliente cli : listaClientes) { %>
                                        <option value="<%= cli.getIdcliente() %>" 
                                            <%= (cliente != null && cliente.getIdcliente() == cli.getIdcliente()) ? "selected" : "" %>>
                                            <%= cli.getNombres() %> <%= cli.getApellidos() %> (ID: <%= cli.getIdcliente() %>)
                                        </option>
                                    <% } %>
                                </select>
                                <button class="btn btn-primary" type="submit">Cargar Datos</button>
                            </div>
                        </form>
                        
                        <% if (cliente != null) { %>
                        <form action="ClienteServlet" method="post">
                            <input type="hidden" name="action" value="actualizar">
                            <input type="hidden" name="idCliente" value="<%= cliente.getIdcliente() %>">
                            <div class="row mb-3">
                            	<div class="col-md-6">
                                    <label for="cedula" class="form-label">Cédula</label>
                                    <input type="number" class="form-control" id="cedula" name="cedula" value="<%= cliente.getCedula() %>" required>
                                </div>
                                <div class="col-md-6">
                                    <label for="nombres" class="form-label">Nombres</label>
                                    <input type="text" class="form-control" id="nombres" name="nombres" value="<%= cliente.getNombres() %>" required>
                                </div>
                            </div>
                            <div class="row mb-3">
                                <div class="col-md-6">
                                    <label for="apellidos" class="form-label">Apellidos</label>
                                    <input type="text" class="form-control" id="apellidos" name="apellidos" value="<%= cliente.getApellidos() %>" required>
                                </div>
                                <div class="col-md-6">
                                    <label for="telefono" class="form-label">Teléfono</label>
                                    <input type="text" class="form-control" id="telefono" name="telefono" value="<%= cliente.getTelefono() %>">
                                </div>
                            </div>
                            <div class="mb-3">
                                <label for="direccion" class="form-label">Dirección</label>
                                <textarea class="form-control" id="direccion" name="direccion" rows="2"><%= cliente.getDireccion() %></textarea>
                            </div>
                            <button type="submit" class="btn btn-primary">Actualizar Cliente</button>
                        </form>
                        <% } %>	
                    </div>
                </div>
            </div>
        </div>
    </div>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>