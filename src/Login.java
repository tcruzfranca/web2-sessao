

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.ws.Dispatch;

/**
 * Servlet implementation class Login
 */
@WebServlet("/login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
        super();
        // TODO Auto-generated constructor stub
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    	HttpSession sessao = request.getSession();
    	System.out.println("login");
    	if (sessao.isNew()) {
    		
    		sessao.invalidate();
    		System.out.println("sessao excluida");
	    	response.setContentType("text/html");
			PrintWriter out = response.getWriter();
			out.println("<form action=\"login\" method=\"POST\">"
					+ "<input type=\"text\" name=\"usuario\" /><br/>"
					+ "<input type=\"password\" name=\"password\" /><br/>"
					+ "<input type=\"submit\" /> "
					+ "</form>");
    	}else {
    		System.out.println("Despachou pra logado");
    		RequestDispatcher dispatcher = request.getRequestDispatcher("/userAuth");
    		//Dica: eu poderia pendurar atributos na requesicao antes de despachar.
    		dispatcher.forward(request, response);
    	}
    	
	}
    
    
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		System.out.println("Despachou pra logar");
		//so temos um usuario e uma senha possivel, ainda nao estamos trabalhando com banco
		String user = "tiago";
		String pass = "web2";
		
		//pegando usuario e senha do formulario
		String usuario = request.getParameter("usuario");
		String password = request.getParameter("password");
		
		//simulando o usuario fazendo login
		if ( (user.equals(usuario)) && (pass.equals(password)) ){
			System.out.println("Logando");
			HttpSession session = request.getSession();
			
			//pendurando um atributo na sessao apenas para usar para suporte de verificacao se usuario logado
			session.setAttribute(usuario, password);
			
			RequestDispatcher dispatcher= request.getRequestDispatcher("/userAuth");
    		//Dica: eu poderia pendurar atributos na requesicao antes de despachar.
			dispatcher.forward(request, response);
			
		}else {
			System.out.println("Erro de usuario e senha.");
			response.sendRedirect("login");
			return;
		}
	

	}

}
