<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="controlador.Conexion"%>
<%@ page import="java.sql.*" %> 
<%@ page import="java.util.List"%>
<%@ page import="modelo.Cliente"%>
<%@ page import="DAO.ClienteServlet"%>
<%@ page import="DAO.ClienteDAO"%>

 
<%
	// Instancia del DAO para acceder a la BD
    ClienteDAO clienteDAO = new ClienteDAO();

 	// Se obtiene la lista de todos los clientes
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
<title>Eliminar cliente</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">
    <style>
        :root {
            --primary-color: #4e73df;
            --secondary-color: #6f42c1;
            --danger-color: #e74a3b;
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
            background: linear-gradient(180deg, var(--danger-color), #c03529);
            color: white;
            border-radius: 10px 10px 0 0 !important;
            font-weight: bold;
        }
        
        .btn-danger {
            background-color: var(--danger-color);
            border-color: var(--danger-color);
        }
        
        .btn-danger:hover {
            background-color: #c03529;
            border-color: #c03529;
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
    	<!-- Botón para volver al inicio -->
        <a href="Index.jsp" class="btn btn-primary back-btn">
            <i class="fas fa-arrow-left me-1"></i> Volver al Inicio
        </a>
        
        <div class="row justify-content-center">
            <div class="col-md-8">
                <div class="card">
                    <div class="card-header py-3">
                        <h5 class="card-title m-0"><i class="fas fa-user-times me-2"></i>Eliminar Cliente</h5>
                    </div>
                    <div class="card-body">
                    	<!-- Mensaje de advertencia -->
                        <div class="alert alert-danger">
                            <i class="fas fa-exclamation-triangle me-2"></i>
                            <strong>Advertencia:</strong> Esta acción no se puede deshacer. Se eliminarán permanentemente los datos del cliente.
                        </div>
                        
                        <!-- Primer formulario: seleccionar cliente -->
                        <form method="get" action="EliminarCliente.jsp" class="mb-4">
                            <label for="idCliente" class="form-label">Seleccione el Cliente a Eliminar</label>
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
                        
                        <!-- Si ya se seleccionó un cliente -->
                        <% if (cliente != null) { %>
                        
                        <!-- Mostrar la información del cliente -->
                        <div class="card mb-4">
                            <div class="card-body">
                                <h5 class="card-title">Información del Cliente</h5>
                                <p><strong>ID:</strong> <%= cliente.getIdcliente() %></p>
                                <p><strong>Cédula:</strong> <%= cliente.getCedula() %></p>
                                <p><strong>Nombres:</strong> <%= cliente.getNombres() %></p>
                                <p><strong>Apellidos:</strong> <%= cliente.getApellidos() %></p>
                                <p><strong>Teléfono:</strong> <%= cliente.getTelefono() %></p>
                                <p><strong>Dirección:</strong> <%= cliente.getDireccion() %></p>
                            </div>
                        </div>
                        
                        <!-- Eliminar cliente -->
                        <form action="ClienteServlet" method="post">
                            <input type="hidden" name="action" value="eliminar">
                            <input type="hidden" name="idCliente" value="<%= cliente.getIdcliente() %>">
                            <button type="submit" class="btn btn-danger">Eliminar Cliente Permanentemente</button>
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