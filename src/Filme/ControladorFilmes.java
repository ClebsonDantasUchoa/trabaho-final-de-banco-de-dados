package Filme;

import java.util.ArrayList;

import ControleBanco.ControladorBancoDeDados;

public class ControladorFilmes {
	
	private ControladorBancoDeDados controle_banco;
	
	public ControladorFilmes() {	
	}
	public Filme criarFilme(String nome, String ator1, String ator2, String genero) {
		return new Filme(nome, ator1, ator2, genero);
	}
	public boolean addFilme(String nome, String ator1, String ator2, String genero) {
		try {
			Filme filme = criarFilme(nome, ator1, ator2, genero);
			if(controle_banco.addMovie(nome, ator1, ator2, genero)) { // Lembrar de ajeitar pra passar filme
				return true;
			}else {
				return false;
			}
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
		return false;
	}
	public boolean addGenero(String nome) {
		try {
			if(controle_banco.addGender(nome)) {
				return true;
			}else {
				return false;
			}
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
		return false;
	}
	public ArrayList<String> recomendacoes(String login){
		return controle_banco.recommendMovies(login);
	}
	public ArrayList<String> getFilmes(){
		return controle_banco.getAllMovies();
	}
	
}
