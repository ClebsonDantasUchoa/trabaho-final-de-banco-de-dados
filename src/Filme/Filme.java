package Filme;

public class Filme {
	private String nome;
	private String ator1;
	private String ator2;
	private String genero;
	
	public Filme() {
		
	}
	public Filme(String nome, String ator1, String ator2, String genero) {
		this.nome = nome;
		this.ator1 = ator1;
		this.ator2 = ator2;
		this.genero = genero;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getAtor1() {
		return ator1;
	}
	public void setAtor1(String ator1) {
		this.ator1 = ator1;
	}
	public String getAtor2() {
		return ator2;
	}
	public void setAtor2(String ator2) {
		this.ator2 = ator2;
	}
	public String getGenero() {
		return genero;
	}
	public void setGenero(String genero) {
		this.genero = genero;
	}
	@Override
	public String toString() {
		return "Filme [nome=" + nome + ", ator1=" + ator1 + ", ator2=" + ator2 + ", genero=" + genero + "]";
	}
	
}
