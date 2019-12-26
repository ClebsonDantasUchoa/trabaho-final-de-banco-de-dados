package Usuario;
import java.util.*;
public class Usuario {
	private int id;
	private String nome;
	private String login;
	private String password;
	private List<String> tipoFilmes;
	private List<String> atores;
	
	public Usuario() {
		
	}
	public Usuario(String login, String password) {
		this.login = login;
		this.password = password;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getNome() {
		return nome;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public List<String> getTipoFilmes() {
		return tipoFilmes;
	}
	public void setTipoFilmes(List<String> tipoFilmes) {
		this.tipoFilmes = tipoFilmes;
	}
	public List<String> getAtores() {
		return atores;
	}
	public void setAtores(List<String> atores) {
		this.atores = atores;
	}
	
	@Override
	public String toString() {
		return "Usuario [id=" + id + ", login=" + login + ", password=" + password + ", tipoFilmes=" + tipoFilmes
				+ ", atores=" + atores + "]";
	}
	
}


