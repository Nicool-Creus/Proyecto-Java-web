<%@ page import="controlador.Conexion"%>
<%@ page import="java.sql.*" %> 
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>SGRCA</title>
<style>
        body { font-family: Arial, sans-serif; background-color: #f4f4f4; }
        .container { width: 400px; margin: 100px auto; }
        .form-container { 
            background: white; 
            padding: 20px; 
            border-radius: 5px; 
            box-shadow: 0 0 10px rgba(0,0,0,0.1);
            margin-bottom: 20px;
        }
        .form-group { margin-bottom: 15px; }
        label { display: block; margin-bottom: 5px; font-weight: bold; }
        input[type="text"], input[type="password"] { 
            width: 100%; 
            padding: 8px; 
            border: 1px solid #ddd; 
            border-radius: 3px;
            box-sizing: border-box;
        }
        button { 
            width: 100%; 
            padding: 10px; 
            background-color: #007bff; 
            color: white; 
            border: none; 
            border-radius: 3px;
            cursor: pointer; 
            font-size: 16px;
        }
        button:hover { background-color: #0056b3; }
        .btn-register { 
            background-color: #28a745; 
            margin-top: 10px;
        }
        .btn-register:hover { background-color: #218838; }
        .error { 
            color: #dc3545; 
            padding: 10px;
            background-color: #f8d7da;
            border: 1px solid #f5c6cb;
            border-radius: 3px;
            margin-bottom: 15px;
        }
        .success { 
            color: #155724; 
            padding: 10px;
            background-color: #d4edda;
            border: 1px solid #c3e6cb;
            border-radius: 3px;
            margin-bottom: 15px;
        }
    </style>
</head>
<body>
    <div class="container">
        
        <div class="form-container">
            <h2 style="text-align: center;">Iniciar Sesión</h2>
            <form action="LoginServlet" method="post">
                <div class="form-group">
                    <label for="usuario">Usuario:</label>
                    <input type="text" id="usuario" name="usuario" 
                           value="<%= request.getAttribute("usaurio") != null ? 
                                   request.getAttribute("usuario") : "" %>" 
                           required>
                </div>
                <div class="form-group">
                    <label for="contrasena">Contraseña:</label>
                    <input type="password" id="contrasena" name="contrasena" required>
                </div>
                <button type="submit">Ingresar</button>
            </form>
            
            <a href="UsuarioServlet">
                <button type="button" class="btn-register">Crear Usuario</button>
            </a>
            
            <p style="color:red;">
       			${error}
    		</p>
        </div>
    </div>
</body>
</html>