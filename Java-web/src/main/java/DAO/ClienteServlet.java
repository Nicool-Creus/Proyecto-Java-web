package DAO;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import modelo.Cliente;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

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
	

       
    ClienteDAO daoCliente = new ClienteDAO();
	
    public ClienteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String action = request.getParameter("action");
		
		
		if ("generarPDF".equalsIgnoreCase(action)) {
	        generarPDF(response);
	        return;
	    } else {
	    	try {
	            java.util.List<Cliente> listarClientes = daoCliente.listarClientes();
	            request.setAttribute("listaClientes", listarClientes);
	            request.getRequestDispatcher("ListaClientes.jsp").forward(request, response);
	        } catch (SQLException e) {
	            throw new ServletException("Error al listar clientes", e);
	        }
	    }
		
	}
	
	private void generarPDF(HttpServletResponse response) {
		Connection connection = null;
	    Statement stmt = null;
	    ResultSet rs = null;    
		
		try {
	        	
	        	Conexion conexion = new Conexion();
	        	connection = conexion.conectarDB();
	        	
	        	response.reset();
	        	response.setContentType("application/pdf");
	            response.setHeader("Content-Disposition", "inline; filename=\"clientes.pdf\"");
	        	
	        	// Se crea el documento pdf
				Document documento = new Document();
				PdfWriter.getInstance(documento, response.getOutputStream());
				documento.open();
				
				// Crear tabla con columnas
			    PdfPTable tabla = new PdfPTable(5);
			    tabla.setWidthPercentage(100);
			    tabla.setSpacingBefore(15);
			    
			    // Agregar encabezados con el método auxiliar
		        agregarCeldaEncabezado(tabla, "cedula");
		        agregarCeldaEncabezado(tabla, "nombres");
		        agregarCeldaEncabezado(tabla, "apellidos");
		        agregarCeldaEncabezado(tabla, "direccion");
		        agregarCeldaEncabezado(tabla, "telefono");
				
				stmt = connection.createStatement();
				rs = stmt.executeQuery("SELECT cedula, nombres, apellidos, direccion, telefono FROM tblcliente");
				
				// Título del pdf
				documento.add(new Paragraph("Lista de clientes de la tienda de mascotas"));
			    
				while (rs.next()) {
							tabla.addCell(rs.getString("cedula"));
                			tabla.addCell(rs.getString("nombres"));
                			tabla.addCell(rs.getString("apellidos"));
                			tabla.addCell(rs.getString("direccion"));
                			tabla.addCell(rs.getString("telefono"));
				}
				
				documento.add(tabla);
				
				documento.close();
				rs.close();
				stmt.close();
				connection.close();
			} catch (Exception e) {
				try {
		            // MOSTRAR ERROR REAL - Cambiar a texto para debuggear
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
		        // Cerrar recursos
		        try {
		            if (rs != null) rs.close();
		            if (stmt != null) stmt.close();
		            if (connection != null) connection.close();
		        } catch (SQLException e) {
		            e.printStackTrace();
		        }
			}
	        
	    }


		// MÉTODO AUXILIAR PARA CELDAS DE ENCABEZADO
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
        
        // Verificar si action es null o vacío
        if (action == null || action.isEmpty()) {
            response.sendRedirect("Index.jsp?error=Acción no especificada");
            return;
        }
        
        switch (action) {
		case "insertar":
			
			int cedula = Integer.parseInt(request.getParameter("cedula"));
			String nombres = request.getParameter("nombres");
			String apellidos = request.getParameter("apellidos");
			String direccion = request.getParameter("direccion");
			String telefono = request.getParameter("telefono");

            Cliente nuevo = new Cliente(cedula, nombres, apellidos, direccion, telefono);
            daoCliente.insertarCliente(nuevo);
			break;

		case "actualizar":
            int uCedula = Integer.parseInt(request.getParameter("cedula"));
            String uNombres = request.getParameter("nombres");
            String uApellidos = request.getParameter("apellidos");
            String uDireccion = request.getParameter("direccion");
			String uTelefono = request.getParameter("telefono");
			int uIdCliente = Integer.parseInt(request.getParameter("idCliente"));
			
			Cliente uCliente = new Cliente(uIdCliente, uCedula, uNombres, uApellidos, uDireccion, uTelefono);
            daoCliente.actualizarCliente(uCliente);
            break;
			
		case "eliminar":
			int idCliente = Integer.parseInt(request.getParameter("idCliente"));
            daoCliente.eliminarCliente(idCliente);
			break;
		}
        
     // Siempre redirigir al Index después de una operación
        response.sendRedirect("Index.jsp");
		
	}

}
