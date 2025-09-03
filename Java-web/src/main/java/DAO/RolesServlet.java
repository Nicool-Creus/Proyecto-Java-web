package DAO;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import modelo.Roles;

import java.io.IOException;

@WebServlet(name = "RolesServlet", urlPatterns = "/RolesServlet")
public class RolesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	RolesDAO daoRol = new RolesDAO();
       
    public RolesServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.getRequestDispatcher("/insertarRol.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String action = request.getParameter("action");
		HttpSession session = request.getSession();
	
		switch (action) {
		case "insertar":
			
			int idRol = Integer.parseInt(request.getParameter("idRol"));
            String nombreRol = request.getParameter("nombreRol");
            Roles nuevoRol = new Roles(idRol, nombreRol);
            RolesDAO.insertarRol(nuevoRol);
            
            request.setAttribute("mensaje", "Rol insertado correctamente");
            request.getRequestDispatcher("InsertarRol.jsp").forward(request, response);

            break;
            
		case "eliminar":
            int idRolEliminar = Integer.parseInt(request.getParameter("idRol"));
            RolesDAO.eliminarRol(idRolEliminar);
            
            request.setAttribute("mensaje", "Rol eliminado correctamente");
            request.getRequestDispatcher("InsertarRol.jsp").forward(request, response);
			
            break;
		}
	
	
		
		
		
	/*	try {
        	// Acción insertar
            if ("insertar".equalsIgnoreCase(action)) {
                
            	int idRol = Integer.parseInt(request.getParameter("idRol"));
                String nombreRol = request.getParameter("nombreRol");
                
            	Roles r = new Roles(
                        Integer.parseInt(request.getParameter("idrol")),
                        request.getParameter("nombreRol")
                );
                dao.insertarRol(r);
                response.sendRedirect("Index.jsp");
		
            } else if ("eliminar".equalsIgnoreCase(action)) {
                int idRol = Integer.parseInt(request.getParameter("idRol"));
                dao.eliminarRol(idRol);
                
                session.setAttribute("mensaje", "¡Rol eliminado con éxito!");
                session.setAttribute("tipoMensaje", "exito");
                response.sendRedirect("Index.jsp");
                
            } else {
	    	session.setAttribute("mensaje", "Error, no se pudo hacer la operación. Inténtelo de nuevo.");
	        session.setAttribute("tipoMensaje", "Error");
	        response.sendRedirect("Index.jsp");
            }
		}
            
            catch (Exception e) {
        e.printStackTrace(response.getWriter());
    } */

	}
	
}