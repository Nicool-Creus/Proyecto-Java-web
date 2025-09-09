package DAO;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import modelo.Cliente;
import modelo.Correo;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import controlador.Conexion;

@WebServlet(name = "ClienteServlet", urlPatterns = "/ClienteServlet")
public class ClienteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	// Instancia del DAO que maneja las operaciones con la BD
    ClienteDAO daoCliente = new ClienteDAO();
	
    public ClienteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// Recibe la acción desde el formulario
		String action = request.getParameter("action");
		
		// Acción de consultar cliente
		try {
			if ("consultar".equalsIgnoreCase(action)) {
				
				// Se obtene el Id del cliente desde el formulario
				int idCliente = Integer.parseInt(request.getParameter("idCliente"));
				
				// Se consulta el cliente en la base datos por su id
				Cliente cliente = daoCliente.consultarCliente(idCliente);
				
				// Si existe el cliente se guarda en el request para enviarlo al JSP
				if (cliente != null) {
					request.setAttribute("cliente", cliente);
				} else {
					//Si no, entonces se envía un mensaje de error al JSP
					request.setAttribute("error", "No se encontró un cliente con el ID " + idCliente);
				}
				
				// Se redirige al JSP de la consulta para mostrar el resultado
				request.getRequestDispatcher("ConsultarCliente.jsp").forward(request, response);
			}
		} catch (Exception e) {
			e.printStackTrace();
			response.setContentType("text/plain");
			response.getWriter().println("Error en consultar cliente: " + e.getMessage());
		}
	
		
		
		// Si la acción es generarPDF, se invoca el método privado
		if ("generarPDF".equalsIgnoreCase(action)) {
	        generarPDF(response);
	        return;
	    }  
	    	
	    	// Lista los clientes y los envía al JSP
	    	try {
	    		if ("lista".equals(action)) {
	            List<Cliente> listarClientes = daoCliente.listarClientes();
	            request.setAttribute("listaClientes", listarClientes);
	            request.getRequestDispatcher("ListaClientes.jsp").forward(request, response);
	    		
	    		}
	    	} catch (SQLException e) {
	            throw new ServletException("Error al listar clientes", e);
	    	}

	}
	
	// Método para generar el PDF
	private void generarPDF(HttpServletResponse response) {
		Connection connection = null;
	    Statement stmt = null;
	    ResultSet rs = null;    
		
		try {
	        	
			// Conexión a la base de datos
	        	Conexion conexion = new Conexion();
	        	connection = conexion.conectarDB();
	        	
	        	response.reset();
	        	response.setContentType("application/pdf");
	            response.setHeader("Content-Disposition", "inline; filename=\"clientes.pdf\"");
	        	
	        	// Se crea el documento pdf
				Document documento = new Document();
				PdfWriter.getInstance(documento, response.getOutputStream());
				documento.open();
				
				// Crear tabla con 5 columnas
			    PdfPTable tabla = new PdfPTable(5);
			    tabla.setWidthPercentage(100);
			    tabla.setSpacingBefore(15);
			    
			    // Agregar encabezados con el método auxiliar
		        agregarCeldaEncabezado(tabla, "cedula");
		        agregarCeldaEncabezado(tabla, "nombres");
		        agregarCeldaEncabezado(tabla, "apellidos");
		        agregarCeldaEncabezado(tabla, "direccion");
		        agregarCeldaEncabezado(tabla, "telefono");
				
		        // Consulta SQL a la tabla de clientes
				stmt = connection.createStatement();
				rs = stmt.executeQuery("SELECT cedula, nombres, apellidos, direccion, telefono FROM tblcliente");
				
				// Título del pdf
				documento.add(new Paragraph("Lista de clientes de la tienda de mascotas"));
			    
				// Se agrega cada cliente a la tabla
				while (rs.next()) {
							tabla.addCell(rs.getString("cedula"));
                			tabla.addCell(rs.getString("nombres"));
                			tabla.addCell(rs.getString("apellidos"));
                			tabla.addCell(rs.getString("direccion"));
                			tabla.addCell(rs.getString("telefono"));
				}
				
				// Se inserta la tabla en el documento
				documento.add(tabla);
				
				// Cerrar recursos
				documento.close();
				rs.close();
				stmt.close();
				connection.close();
				
			} catch (Exception e) {
				try {
					// Si ocurre un error, se muestra en el navegador
		            response.reset(); // Limpiar response
		            response.setContentType("text/html;charset=UTF-8");
		            PrintWriter out = response.getWriter();
		            out.println("<html><body>");
		            out.println("<h2>Error generando PDF:</h2>");
		            out.println("<pre>");
		            e.printStackTrace(out);
		            out.println("</pre>");
		            out.println("</body></html>");
		        } catch (IOException ex) {
		            ex.printStackTrace();
		        }
		    } finally {
		    	// Cierre de recursos en caso de error
		        try {
		            if (rs != null) rs.close();
		            if (stmt != null) stmt.close();
		            if (connection != null) connection.close();
		        } catch (SQLException e) {
		            e.printStackTrace();
		        }
			}
	        
	    }


		// Método auxiliar para los encabezados de la tabla
		private void agregarCeldaEncabezado(PdfPTable tabla, String texto) {
		    PdfPCell celda = new PdfPCell(new Paragraph(texto, FontFactory.getFont(FontFactory.HELVETICA_BOLD, 12)));
		    celda.setHorizontalAlignment(Element.ALIGN_CENTER);
		    celda.setBackgroundColor(new BaseColor(220, 220, 220));
		    celda.setPadding(8);
		    celda.setBorderWidth(1.5f);
		    tabla.addCell(celda);
		}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String action = request.getParameter("action");
        HttpSession session = request.getSession();
        
        // Si no hay acción, redirige con error
        if (action == null || action.isEmpty()) {
            response.sendRedirect("Index.jsp?error=Acción no especificada");
            return;
        }
        
        // Según la acción, se ejecutaran uno de los casos
        switch (action) {
		case "insertar":
			
			// Obtiene los datos desde el formulario
			int cedula = Integer.parseInt(request.getParameter("cedula"));
			String nombres = request.getParameter("nombres");
			String apellidos = request.getParameter("apellidos");
			String direccion = request.getParameter("direccion");
			String telefono = request.getParameter("telefono");

			// Crea un nuevo cliente y lo inserta
            Cliente nuevo = new Cliente(0, cedula, nombres, apellidos, direccion, telefono);
            daoCliente.insertarCliente(nuevo);
            
            // Enviar correo notificando
            
            // Se crea un objeto de la clase Correo
            Correo correo = new Correo();
            
            try {
            	
                correo.EnviarCorreo("creusnaz.5@gmail.com", "Nuevo Cliente Registrado");
			} catch (Exception e) {
				
			}
			break;

		case "actualizar":
			
			// Obtener datos a actualizar
            int uCedula = Integer.parseInt(request.getParameter("cedula"));
            String uNombres = request.getParameter("nombres");
            String uApellidos = request.getParameter("apellidos");
            String uDireccion = request.getParameter("direccion");
			String uTelefono = request.getParameter("telefono");
			int uIdCliente = Integer.parseInt(request.getParameter("idCliente"));
			
			Cliente uCliente = new Cliente(uIdCliente, uCedula, uNombres, uApellidos, uDireccion, uTelefono);
            daoCliente.actualizarCliente(uCliente);
            
            Correo aCorreo = new Correo();
            
            try {
                aCorreo.EnviarCorreo("creusnaz.5@gmail.com", "Datos del cliente actualizado");
			} catch (Exception e) {
				
			}
            break;
			
		case "eliminar":
			
			// Eliminar cliente según el ID
			int idCliente = Integer.parseInt(request.getParameter("idCliente"));
            
			Cliente cliente = daoCliente.consultarCliente(idCliente);
			
			if (cliente != null){
				String correoCliente = cliente.get
			}
			
			daoCliente.eliminarCliente(idCliente);
			
            
            
            Correo eCorreo = new Correo();
            
            try {
                eCorreo.EnviarCorreo("creusnaz.5@gmail.com", "Cliente eliminado");
			} catch (Exception e) {
				
			}
            break;
		}
        
        // Al final, redirige siempre al Index
        response.sendRedirect("Index.jsp");
		
	}

}