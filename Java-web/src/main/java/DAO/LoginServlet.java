package DAO;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import modelo.Usuario;

import java.io.IOException;

@WebServlet(name = "LoginServlet", urlPatterns = "/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	UsuarioDAO daoUsuario = new UsuarioDAO();

    public LoginServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		
		
		
		// Cerrar sesión
      /*  HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
        response.sendRedirect("Login.jsp");*/
	} 

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String usuario = request.getParameter("usuario");
		String contrasena = request.getParameter("contraseña");
		
		Usuario usuarios = daoUsuario.validarUsuario(usuario, contrasena);
		
		if (usuarios != null) {
			HttpSession session = request.getSession();
			session.setAttribute("UsuarioLogueado", usuarios);
			response.sendRedirect("Index.jsp");
		} else {
			request.setAttribute("error", "Usuario o contraseña incorrectos");
            request.getRequestDispatcher("Login.jsp").forward(request, response);
		}
		
		
		
		/*String usuario = request.getParameter("usuario");
		String contrasena = request.getParameter("contraseña");
		
		if (usuario == null || usuario.trim().isEmpty() ||
			contrasena == null || contrasena.trim().isEmpty()) {
			request.setAttribute("Error", "Usuario y contraseña son obligatorios");
            request.getRequestDispatcher("Login.jsp").forward(request, response);
            return;
		}
		
		usuario = usuario.trim();
        contrasena = contrasena.trim();
        
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        boolean credencialesValidas = usuarioDAO.validarUsuaContra(contrasena, contrasena);
        
        if (credencialesValidas) {
			Usuario usuarios = usuarioDAO.obtenerUsuarioPorCredenciales(usuario, contrasena);
			
			if (usuario != null) {
				HttpSession sesion = request.getSession();
				sesion.setAttribute("usuario", usuario);
                response.sendRedirect("Index.jsp");
			} else {
				request.setAttribute("Error", "Error interno al obtener datos del usuario");
				request.getRequestDispatcher("Login.jsp").forward(request, response);
			}
        } else {
        	request.setAttribute("Error", "Usuario o contraseña incorrectos");
            request.setAttribute("usuario", usuario);
            request.getRequestDispatcher("Login.jsp").forward(request, response);
		} */
		
	}

}