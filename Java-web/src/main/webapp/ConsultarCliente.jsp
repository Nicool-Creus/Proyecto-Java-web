<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="controlador.Conexion"%>
<%@ page import="java.sql.*" %> 
<%@ page import="modelo.Cliente"%>
<%@ page import="DAO.ClienteDAO"%>

    
<%
    Cliente cliente = null;
    if (request.getParameter("idCliente") != null) {
        int idCliente = Integer.parseInt(request.getParameter("idCliente"));
        ClienteDAO clienteDAO = new ClienteDAO();
        cliente = clienteDAO.consultarCliente(idCliente);
    }
%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Consultar clientes</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">
    <style>
        :root {
            --primary-color: #4e73df;
            --secondary-color: #6f42c1;
            --info-color: #36b9cc;
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
            background: linear-gradient(180deg, var(--info-color), #2a96a5);
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
        
        .table th {
            border-top: none;
            background-color: var(--info-color);
            color: white;
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
                        <h5 class="card-title m-0"><i class="fas fa-search me-2"></i>Consultar Cliente por ID</h5>
                    </div>
                    <div class="card-body">
                    
                        <form method="get" action="ConsultarCliente.jsp" class="mb-4">
                            <label for="idCliente" class="form-label">ID del Cliente</label>
                            <div class="input-group">
                                <input type="number" class="form-control" id="idCliente" name="idCliente" 
                                    value="<%= request.getParameter("idCliente") != null ? request.getParameter("idCliente") : "" %>" 
                                    placeholder="Ingrese el ID del cliente" required>
                                <button class="btn btn-primary" type="submit">Buscar</button>
                            </div>
                        </form>
                        
                        <% if (cliente != null) { %>
                        <div class="table-responsive">
                            <table class="table table-bordered table-hover">
                                <thead>
                                    <tr>
                                        <th>ID</th>
                                        <th>Cédula</th>
                                        <th>Nombres</th>
                                        <th>Apellidos</th>
                                        <th>Dirección</th>
                                        <th>Teléfono</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <tr>
                                        <td><%= cliente.getIdcliente() %></td>
                                        <td><%= cliente.getCedula() %></td>
                                        <td><%= cliente.getNombres() %></td>
                                        <td><%= cliente.getApellidos() %></td>
                                        <td><%= cliente.getTelefono() %></td>
                                        <td><%= cliente.getDireccion() %></td>
                                    </tr>
                                </tbody>
                            </table>
                        </div>
                        <% } else if (request.getParameter("idCliente") != null) { %>
                        <div class="alert alert-warning" role="alert">
                            No se encontró ningún cliente con el ID <%= request.getParameter("idCliente") %>
                        </div>
                        <% } %>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>