package Sistema;

import java.util.Scanner;

import Filme.ControladorFilmes;
import Usuario.Controlador_Usuario;
import util.Print;

public class Sistema {

	public static void main(String args[]) {
		// Declaração de variaveis
		ControladorFilmes controlador_filmes = new ControladorFilmes();
		Controlador_Usuario controlador_usuario = new Controlador_Usuario();
		Print printar = new Print();
		Scanner write = new Scanner(System.in);
		String read;
		boolean sair = false;
		String arg;

		printar.menuBemVindo();
		printar.menuInicial();
		
		while(!sair) {
			read = write.nextLine();
			switch(read) {
				case "1":
					try {
						printar.menuCadastro();
						arg = write.nextLine();
						args = arg.split(" ");
						if(controlador_usuario.addUser(args[0],args[1],args[2])){
							printar.cadastroSucesso();
						}
					}catch(Exception e) {
						printar.mostrar(e.getMessage());
					}
					break;
				case "2":
					try {
						printar.menuLogin();
						arg = write.nextLine();
						args = arg.split(" ");
						if(!controlador_usuario.fazerLogin(args[0],args[1])) {
							boolean stop = false;
							String argumentos[];
							while(!stop) {
								printar.menuSistema();
								read = write.nextLine();
								String leitor;
							
								switch(read) {
									case "1": // Editar nome
										try {
											printar.menuEditarLogin();
											leitor = write.nextLine();
											argumentos = leitor.split(" ");
											
											if(controlador_usuario.updateUsernome(argumentos[0],argumentos[1] )) {
												printar.mostrar("Nome alterado com sucesso");
											}
											
										}catch(Exception e) {
											printar.mostrar(e.getMessage());
										}
										break;
									case "2": // Editar Senha
										try {
											printar.menuEditarSenha();
											leitor = write.nextLine();
											argumentos = leitor.split(" ");
											
											if(controlador_usuario.updateUserpassword(argumentos[0], argumentos[1])) {
												printar.mostrar("Senha alterada com sucesso");
											}
										}catch(Exception e) {
											printar.mostrar(e.getMessage());
										}
										
										break;
									case "3": // Deletar conta
										try {
											printar.menuLogin();
											leitor = write.nextLine();
											argumentos = leitor.split(" ");
											if(controlador_usuario.deleteUser(argumentos[0], argumentos[1])) {
												printar.mostrar("Conta Deletada com sucesso");
											}
										}catch(Exception e) {
											printar.mostrar(e.getMessage());
										}
										break;
									case "4": // assistir filme
										try {
											
										}catch(Exception e) {
											printar.mostrar(e.getMessage());
										}
										break;
									case "5": // adicionar ator favorito
										try {
											leitor = write.nextLine();
											argumentos = leitor.split(" ");
											if(controlador_usuario.addFavoriteActor(argumentos[0], argumentos[1])) {
												printar.mostrar("Ator favoritado!");
											}
										}catch(Exception e) {
											printar.mostrar(e.getMessage());
										}
										break;
									case "6": // adicionar genero favorito
										try {
											leitor = write.nextLine();
											argumentos = leitor.split(" ");
											if(controlador_usuario.addFavoriteGenero(argumentos[0], argumentos[1])) {
												printar.mostrar("Genero favoritado!");
											}
										}catch(Exception e) {
											printar.mostrar(e.getMessage());
										}
										break;
									case "7": // recomendações
										try {
											printar.mostrarLista(controlador_filmes.recomendacoes(args[0]));
										}catch(Exception e) {
											printar.mostrar(e.getMessage());
										}
										break;
									case "8": // Filmes Assistidos 
										try {
											printar.mostrarLista(controlador_usuario.filmesAssistidos(args[0]));		
										}catch(Exception e) {
											printar.mostrar(e.getMessage());
										}
										break;
									case "9": // sair
										stop = true;
										break;
								}				
							}
						}
					}catch(Exception e) {
						printar.mostrar(e.getMessage());
					}
					break;
				case "3":
					printar.mostrarLista(controlador_filmes.getFilmes());
					break;
				case "4":
					sair = true;
					printar.volteSempre();
					break;
				case "5":
					printar.menuInicial();
					break;
				default:
					printar.mostrar("Comando Inválido");
					break;
			}
		}	
		
	}

}
