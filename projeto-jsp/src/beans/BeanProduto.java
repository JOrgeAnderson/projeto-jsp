package beans;

public class BeanProduto {
	
	
	private long id;
	private String nome;
	private long quantidade;
	private double valor;
	private Long categoria_id;
	

	public Long getCategoria_id() {
		return categoria_id;
	}


	public void setCategoria_id(Long categoria_id) {
		this.categoria_id = categoria_id;
	}


	public BeanProduto() {
	}


	public long getId() {
		return id;
	}


	public void setId(long id) {
		this.id = id;
	}


	public String getNome() {
		return nome;
	}


	public void setNome(String nome) {
		this.nome = nome;
	}


	public long getQuantidade() {
		return quantidade;
	}


	public void setQuantidade(long quantidade) {
		this.quantidade = quantidade;
	}


	public double getValor() {
		return valor;
	}


	public void setValor(double valor) {
		this.valor = valor;
	}
	 public String getValorEmTexto(){
		 return Double.toString(valor).replace('.', ',');
	 }
	
}
