package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.BeanCursoJsp;
import beans.Telefones;
import dao.DaoTelefones;
import dao.DaoUsuario;

/**
 * Servlet implementation class TelefonesServlets
 */
@WebServlet("/salvarTelefones")
public class TelefonesServlets extends HttpServlet {
	private static final long serialVersionUID = 1L;

	DaoUsuario daoUsuario = new DaoUsuario();

	private DaoTelefones daoTelefones = new DaoTelefones();

	public TelefonesServlets() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {

			String acao = request.getParameter("acao");
			String user = request.getParameter("user");

			/* if (user != null) { */
				

				if (acao.endsWith("addFone")) {
					
					BeanCursoJsp usuario = daoUsuario.consultar(user);
					
					request.getSession().setAttribute("userEscolhido", usuario);
					request.setAttribute("userEscolhido", usuario);

					RequestDispatcher view = request.getRequestDispatcher("/telefones.jsp");// redirecionamento de
																							// página
					request.setAttribute("telefones", daoTelefones.listar(usuario.getId()));
					view.forward(request, response);
				} else if (acao.endsWith("deleteFone")) {
					String foneId = request.getParameter("foneId");

					daoTelefones.delete(foneId);

					BeanCursoJsp beanCursoJsp = (BeanCursoJsp) request.getSession().getAttribute("userEscolhido");

					RequestDispatcher view = request.getRequestDispatcher("/telefones.jsp");// redirecionamento de
					// página
					request.setAttribute("telefones", daoTelefones.listar(beanCursoJsp.getId()));
					request.setAttribute("msg", "Removido Com sucesso!!");
					view.forward(request, response);

				}

			/*} else {
				RequestDispatcher view = request.getRequestDispatcher("/cadastroUsuario.jsp");
				request.setAttribute("usuarios", daoUsuario.listar());
				view.forward(request, response);
			}*/

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {
			BeanCursoJsp beanCursoJsp = (BeanCursoJsp) request.getSession().getAttribute("userEscolhido");

			String numero = request.getParameter("numero");

			String tipo = request.getParameter("tipo");

			String acao = request.getParameter("acao");

			if (acao == null || (acao != null && !acao.equalsIgnoreCase("voltar"))) {

				if (numero == null || (numero != null && numero.isEmpty())) {

					RequestDispatcher view = request.getRequestDispatcher("/telefones.jsp");// redirecionamento de
																							// página
					request.setAttribute("telefones", daoTelefones.listar(beanCursoJsp.getId()));
					request.setAttribute("msg", "Informe o número do Telefone!");
					view.forward(request, response);

				}

				else {

					Telefones telefones = new Telefones();
					telefones.setNumero(numero);
					telefones.setTipo(tipo);
					telefones.setUsuario(beanCursoJsp.getId());

					daoTelefones.salvar(telefones);

					request.getSession().setAttribute("userEscolhido", beanCursoJsp);
					request.setAttribute("userEscolhido", beanCursoJsp);

					RequestDispatcher view = request.getRequestDispatcher("/telefones.jsp");// redirecionamento de
																							// página
					request.setAttribute("telefones", daoTelefones.listar(beanCursoJsp.getId()));
					request.setAttribute("msg", "Salvo Com sucesso!!");
					view.forward(request, response);

				}

			} else {
				RequestDispatcher view = request.getRequestDispatcher("/cadastroUsuario.jsp");
				request.setAttribute("usuarios", daoUsuario.listar());
				view.forward(request, response);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
