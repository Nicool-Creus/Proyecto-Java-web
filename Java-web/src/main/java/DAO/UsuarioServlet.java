package DAO;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import modelo.Usuario;
import controlador.Conexion;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;



@WebServlet(name = "UsuarioServlet", urlPatterns = "/UsuarioServlet")
public class UsuarioServlet extends HttpServlet {
	private static final long serialVersionUID = 1L; 
	UsuarioDAO daoUsuario = new UsuarioDAO();
    
    public UsuarioServlet() {
        super();
        
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String action = request.getParameter("action");
		
		// Mostrar lista de usuarios
		HttpSession session = request.getSession();
        Usuario usuarioSesion = (Usuario) session.getAttribute("usuario");
        
        if (usuarioSesion == null) {
            response.sendRedirect("Login.jsp");
            return;
        }
        
        java.util.List<Usuario> listaUsuarios = daoUsuario.listaUsuarios();
        request.setAttribute("listaUsuarios", listaUsuarios);
        request.getRequestDispatcher("ListaUsuarios.jsp").forward(request, response);
    
	        
	        // Generar PDF cuando action es "generarPDF"
	        if ("generarPDF".equalsIgnoreCase(action)) {
	            generarPDF(response);
	            return;
	        }

	        // Si no es ninguna acción conocida, mostrar mensaje
	        response.setContentType("text/html");
	        response.getWriter().println("No existe un programa con ese código, digite otro");
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
	            response.setHeader("Content-Disposition", "inline; filename=\"usuarios.pdf\"");
	        	
	        	// Se crea el documento pdf
				Document documento = new Document();
				PdfWriter.getInstance(documento, response.getOutputStream());
				documento.open();
				
				// Crear tabla con columnas
			    PdfPTable tabla = new PdfPTable(5);
			    tabla.setWidthPercentage(100);
			    tabla.setSpacingBefore(15);
			    
			    // Agregar encabezados con el método auxiliar
		        agregarCeldaEncabezado(tabla, "Usuario");
		        agregarCeldaEncabezado(tabla, "Contraseña");
				
				stmt = connection.createStatement();
				rs = stmt.executeQuery("SELECT usuario, contrasena FROM tblusuario");
				
				// Título del pdf
				documento.add(new Paragraph("Lista de usuarios de la tienda de mascotas"));
			    
				while (rs.next()) {
							tabla.addCell(rs.getString("usuario"));
                			tabla.addCell(rs.getString("contrasena"));
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
		

	    @Override
	    protected void doPost(HttpServletRequest request, HttpServletResponse response)
	            throws ServletException, IOException {
	        
	        String action = request.getParameter("action");
	        HttpSession session = request.getSession();
	        
	        switch (action) {
			case "insertar":
				String usuario = request.getParameter("usuario");
                String contrasena = request.getParameter("contrasena");
                Usuario nuevo = new Usuario(0, usuario, contrasena);
                daoUsuario.insertarUsuario(nuevo);
				break;

			case "actualizar":
                int idUpdate = Integer.parseInt(request.getParameter("idUsuario"));
                String usuarioUp = request.getParameter("usuario");
                String contrasenaUp = request.getParameter("contrasena");
                Usuario uUpdate = new Usuario(idUpdate, usuarioUp, contrasenaUp);
                daoUsuario.actualizarUsuario(uUpdate);
                break;
				
			case "eliminar":
				int idUsuario = Integer.parseInt(request.getParameter("idUsuario"));
                daoUsuario.eliminarUsuario(idUsuario);
				break;
			}
	        
	     // Siempre redirigir al listado después de una operación
	        response.sendRedirect("UsuarioServlet");

	       /* try {
	        	// Acción insertar
	            if ("insertar".equalsIgnoreCase(action)) {
	                Usuario u = new Usuario(
	                        0, request.getParameter("usuario"),
	                        request.getParameter("contrasena")
	                );
	                dao.insertarUsuario(u);
	                session.setAttribute("mensaje", "¡Programa registrado con éxito!");
	                session.setAttribute("tipoMensaje", "exito");
	                response.sendRedirect("Index.jsp");
	                
	                // Acción eliminar
	            }  else if ("eliminar".equalsIgnoreCase(action)) {
	                int idUsuario = Integer.parseInt(request.getParameter("idUsuario"));
	                dao.eliminarUsuario(idUsuario);
	                
	                session.setAttribute("mensaje", "¡Usuario eliminado con éxito!");
	                session.setAttribute("tipoMensaje", "exito");
	                response.sendRedirect("Index.jsp");

	            } else {
	            	session.setAttribute("mensaje", "Error, no se pudo hacer la operación. Inténtelo de nuevo.");
	                session.setAttribute("tipoMensaje", "Error");
	                response.sendRedirect("Index.jsp");
	            }

	        } catch (Exception e) {
	            e.printStackTrace(response.getWriter());
	        } */

	    }
}