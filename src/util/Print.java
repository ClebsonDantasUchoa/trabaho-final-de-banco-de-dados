package util;

import java.util.ArrayList;

public class Print {
	public Print() {
		
	}
	
	public void menuInicial() {
		System.out.println("[1] - Cadastrar Usuário");
		System.out.println("[2] - Fazer Login");
		System.out.println("[3] - Ver filmes");
		System.out.println("[4] - Sair");
		System.out.println("[5] - Help");
	}
	public void menuSistema() {
		System.out.println("[1] - Editar Login");
		System.out.println("[2] - Editar Senha ");
		System.out.println("[2] - Deletar Conta");
		System.out.println("[3] - Assistir Filme");
		System.out.println("[4] - Adicionar ator favorito");
		System.out.println("[5] - Adicionar genero favorito");
		System.out.println("[6] - Recomendações");
		System.out.println("[7] - Filmes Assistidos");
		System.out.println("[8] - Help");
	}
	public void menuCadastro() {
		System.out.println("Digite seu login (email) e senha, respectivamente: ");
	}
	public void cadastroSucesso() {
		System.out.println("Cadastro realizado com sucesso");
	}
	public void cadastroErro() {
		System.out.println("Erro ao cadastrar");
	}
	public void menuLogin() {
		System.out.println("Digite seu login (email) e senha, respectivamente: ");
	}
	public void menuEditarLogin() {
		System.out.println("Digite seu login (email) atual, e o novo login: ");
	}
	public void menuEditarSenha() {
		System.out.println("Digite sua senha atual e a nova senha: ");
	}
	public void menuAdicionarAtor() {
		System.out.println("Digite o nome do ator: ");
	}
	public void menuAdicionarGenero() {
		System.out.println("Digite o nome do gênero (Ex: Ação, Aventura, ... ): ");
	}
	public void menuBemVindo() {
		System.out.println("Seja Bem Vindo a LOCADORA DE FILMES\n");
	}
	public void volteSempre() {
		System.out.println("Obrigado e volte sempre. ");
	}
	public void mostrarLista(ArrayList<String> lista) {
		for(int i = 0; i < lista.size(); i++) {
			System.out.println(lista.get(i));
		}
	}
	public void mostrar(String a) {
		System.out.println(a);
	}
	

}
