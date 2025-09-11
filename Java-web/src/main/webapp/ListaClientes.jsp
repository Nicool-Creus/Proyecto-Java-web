<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="modelo.Cliente" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Lista de clientes</title>
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
        <div class="col-md-10">
            <div class="card">
                <div class="card-header py-3">
                    <h5 class="card-title m-0"><i class="fas fa-users me-2"></i> Lista de Clientes</h5>
                </div>
                <div class="card-body">
 
                    <div class="table-responsive">
                        <table class="table table-bordered table-hover">
                            <thead>
                                <tr>
                                    <th>Cédula</th>
                                    <th>Nombres</th>
                                    <th>Apellidos</th>
                                    <th>Dirección</th>
                                    <th>Teléfono</th>
                                </tr>
                            </thead>
                            <tbody>
                                <%
	                                List<Cliente> listaClientes = (List<Cliente>) request.getAttribute("listaClientes");
	                                if (listaClientes != null) {
	                                    for (Cliente c : listaClientes) {
                                %>
                                <tr>
                                    <td><%= c.getCedula() %></td>
                                    <td><%= c.getNombres() %></td>
                                    <td><%= c.getApellidos() %></td>
                                    <td><%= c.getDireccion() %></td>
                                    <td><%= c.getTelefono() %></td>
                                </tr>
                                <%
                                        }
                                    } else {
                                %>
                                <tr>
                                    <td colspan="5" class="text-center">No hay clientes registrados</td>
                                </tr>
                                <% } %>
                            </tbody>
                        </table>
                    </div>
                    
                </div>
            </div>
        </div>
    </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>