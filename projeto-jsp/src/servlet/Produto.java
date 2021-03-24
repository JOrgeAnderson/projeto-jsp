package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.BeanProduto;
import dao.DaoProdutos;

/**
 * Servlet implementation class Produto
 */
@WebServlet("/produtos")
public class Produto extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private DaoProdutos daoProdutos = new DaoProdutos();

	public Produto() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			String acao = request.getParameter("acao") != null ? request.getParameter("acao") : "listarprodutos";
			String produto = request.getParameter("produto");

			RequestDispatcher view = request.getRequestDispatcher("/cadastroProdutos.jsp");
			
			if (acao.equalsIgnoreCase("delete")) {
				daoProdutos.deleteProduto(produto);
				
				request.setAttribute("produtos", daoProdutos.listarProdutos());
			
			} else if (acao.equalsIgnoreCase("editar")) {

				BeanProduto beanProduto = daoProdutos.consultarProduto(produto);

				request.setAttribute("produto", beanProduto);

			} else if (acao.equalsIgnoreCase("listarprodutos")) {

				request.setAttribute("produtos", daoProdutos.listarProdutos());
			}
			
			request.setAttribute("categorias", daoProdutos.listaCategoria());
			view.forward(request, response);
		
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String acao = request.getParameter("acao");

		if (acao != null && acao.equalsIgnoreCase("reset")) {
			try {
				RequestDispatcher view = request.getRequestDispatcher("/cadastroProdutos.jsp");
				request.setAttribute("produtos", daoProdutos.listarProdutos());
				request.setAttribute("categorias", daoProdutos.listaCategoria());
				view.forward(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {

			String id = request.getParameter("id");
			String nome = request.getParameter("nome");
			String quantidade = request.getParameter("quantidade");
			String valor = request.getParameter("valor");
			String categoria = request.getParameter("categoria_id");

			try {

				String msg = null;
				boolean podeInserir = true;

				if (id == null || id.isEmpty() && !daoProdutos.validarNome(nome)) {
					msg = "Produto '" + nome + "' já existe no banco";
					podeInserir = false;
				}

				else if (nome == null || nome.isEmpty()) {
					msg = "nome do produto deve ser informado";
					podeInserir = false;
				} else if (quantidade == null || quantidade.isEmpty()) {
					msg = "Quandtidade do produto deve ser informado";
					podeInserir = false;
				}

				else if (valor == null || valor.isEmpty()) {
					msg = "Valor do produto deve ser informado";
					podeInserir = false;
				}

				BeanProduto produto = new BeanProduto();
				produto.setId(!id.isEmpty() ? Long.parseLong(id) : 0);
				produto.setNome(nome);
				produto.setCategoria_id(Long.parseLong(categoria));

				if (quantidade != null && !quantidade.isEmpty()) {
					produto.setQuantidade(Long.parseLong(quantidade));
				
				}
				if (valor != null && !valor.isEmpty()) {
					String valorParse = valor.replaceAll("\\.", "");//10.500,20
					valorParse = valorParse.replaceAll("\\,", ".");//10500,20
					produto.setValor(Double.parseDouble(valorParse));
				
				}
				if (id == null || id.isEmpty() && daoProdutos.validarNome(nome) && podeInserir) {
					daoProdutos.salvarProduto(produto);
					msg = "Produto salvo com sucesso!!";
				
				} else if (id != null && !id.isEmpty()) {
				
					if (!daoProdutos.validarNomeUpdate(nome, quantidade)) {
						msg = "Este produto já existe no bancos";
					
					} else if (id != null && !id.isEmpty() && podeInserir) {
						daoProdutos.atualizarProduto(produto);
					}
				}

				if (msg != null) {
					request.setAttribute("msg", msg);
				}

				if (!podeInserir) {
					request.setAttribute("produto", produto);
				}

				RequestDispatcher view = request.getRequestDispatcher("/cadastroProdutos.jsp");
				request.setAttribute("produtos", daoProdutos.listarProdutos());
				request.setAttribute("categorias", daoProdutos.listaCategoria());
				view.forward(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

}
