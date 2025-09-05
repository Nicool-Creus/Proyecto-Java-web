<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>

<html>
<head>
<meta charset="UTF-8">
<title>Index</title>
</head>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">
    <style>
        :root {
            --primary-color: #4e73df;
            --secondary-color: #6f42c1;
            --success-color: #1cc88a;
            --info-color: #36b9cc;
            --warning-color: #f6c23e;
            --danger-color: #e74a3b;
            --light-bg: #f8f9fc;
        }
        
        body {
            background-color: var(--light-bg);
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
        
        .btn-primary {
            background-color: var(--primary-color);
            border-color: var(--primary-color);
        }
        
        .btn-primary:hover {
            background-color: var(--secondary-color);
            border-color: var(--secondary-color);
        }
        
        .navbar {
            background: linear-gradient(90deg, var(--primary-color), var(--secondary-color));
            box-shadow: 0 0.15rem 1.75rem 0 rgba(58, 59, 69, 0.15);
        }
        
        .feature-card {
            transition: transform 0.3s;
            cursor: pointer;
        }
        
        .feature-card:hover {
            transform: translateY(-5px);
        }
    </style>
</head>
<body>

    <div class="container mt-5">
        <div class="row">
            <div class="col-12">
                <h1 class="text-center mb-4">Gestión de clientes de la tienda de mascotas</h1>
                <p class="text-center text-muted mb-5">Seleccione una opción del menú para gestionar los clientes</p>
            </div>
        </div>

        <!-- Gestión de Clientes -->
        <div class="row justify-content-center">
            <div class="col-md-8">
                <div class="card">
                    <div class="card-header py-3">
                        <h5 class="card-title m-0"><i class="fas fa-user-tie me-2"></i>Gestión de Clientes</h5>
                    </div>
                    <div class="card-body">
                        <div class="row">
                            <div class="col-md-6 mb-4">
                                <a href="InsertarCliente.jsp" class="text-decoration-none">
                                    <div class="card feature-card h-100">
                                        <div class="card-body text-center">
                                            <i class="fas fa-user-plus fa-3x mb-3 text-success"></i>
                                            <h5 class="card-title">Crear Cliente</h5>
                                            <p class="card-text">Agregar nuevos clientes al sistema</p>
                                        </div>
                                    </div>
                                </a>
                            </div>
                            <div class="col-md-6 mb-4">
                                <a href="ActualizarCliente.jsp" class="text-decoration-none">
                                    <div class="card feature-card h-100">
                                        <div class="card-body text-center">
                                            <i class="fas fa-user-edit fa-3x mb-3 text-primary"></i>
                                            <h5 class="card-title">Actualizar Cliente</h5>
                                            <p class="card-text">Modificar datos de clientes existentes</p>
                                        </div>
                                    </div>
                                </a>
                            </div>
                            <div class="col-md-6 mb-4">
                                <a href="EliminarCliente.jsp" class="text-decoration-none">
                                    <div class="card feature-card h-100">
                                        <div class="card-body text-center">
                                            <i class="fas fa-user-times fa-3x mb-3 text-danger"></i>
                                            <h5 class="card-title">Eliminar Cliente</h5>
                                            <p class="card-text">Eliminar clientes del sistema</p>
                                        </div>
                                    </div>
                                </a>
                            </div>
                            <div class="col-md-6 mb-4">
                                <a href="ConsultarCliente.jsp" class="text-decoration-none">
                                    <div class="card feature-card h-100">
                                        <div class="card-body text-center">
                                            <i class="fas fa-search fa-3x mb-3 text-info"></i>
                                            <h5 class="card-title">Consultar Cliente</h5>
                                            <p class="card-text">Buscar clientes por ID</p>
                                        </div>
                                    </div>
                                </a>
                            </div>
                            <div class="col-md-6 mb-4">
                                <a href="ListaClientes.jsp" class="text-decoration-none">
                                    <div class="card feature-card h-100">
                                        <div class="card-body text-center">
                                            <i class="fas fa-file fa-3x mb-3 text-info"></i>
                                            <h5 class="card-title">Lista de clientes</h5>
                                            <p class="card-text">Ver la lista completa de clientes</p>
                                        </div>
                                    </div>
                                </a>
                            </div>
                            <div class="col-md-6 mb-4">
                                <a href="ClienteServlet?action=generarPDF" target="_blank" class="text-decoration-none">
                                    <div class="card feature-card h-100">
                                        <div class="card-body text-center">
                                            <i class="fas fa-file-pdf fa-3x mb-3 text-danger"></i>
                                            <h5 class="card-title">Descargar PDF de clientes</h5>
                                            <p class="card-text">PDF de todos los clientes</p>
                                        </div>
                                    </div>
                                </a>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>