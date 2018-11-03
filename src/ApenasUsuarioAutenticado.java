

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class ApenasUsuarioAutenticado
 */
@WebServlet("/userAuth")
public class ApenasUsuarioAutenticado extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ApenasUsuarioAutenticado() {
        super();
        // TODO Auto-generated constructor stub
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	System.out.println("Vai pro do post");
    	doPost(request,response);
    }
    
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		System.out.println("Chegou no local de imprimir o nome.");
		HttpSession sessao = request.getSession();
		
		if (sessao.getAttributeNames().hasMoreElements()) {
		

			System.out.println("Chegou no lugar");
			response.setContentType("text/html");
			//se logado, faz o que deve ser feito (neste caso, apenas informar)
			response.getWriter().println("<h1> usuario logado </h1>");

			
		}else {
			System.out.println("Tentou acessar sem estar logado.");
			sessao.invalidate();
			//se usuario nao estiver logado, enviar para servlet de login
			RequestDispatcher dispatcher = request.getRequestDispatcher("/login");
			dispatcher.forward(request, response);			
		}
		
	
	}

}
