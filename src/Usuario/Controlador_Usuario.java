package Usuario;

import java.sql.SQLException;
import java.util.ArrayList;

import ControleBanco.ControladorBancoDeDados;

public class Controlador_Usuario {
	private ControladorBancoDeDados controle_banco;
	
	public Controlador_Usuario() {
		controle_banco = new ControladorBancoDeDados();
	}
	public boolean fazerLogin(String login, String password) {		
		if(controle_banco.getPasswordByLogin(login).equals(password)) {
			return true;
		}
		return false;
	}
	public boolean addUser(String nome,String login, String password) throws Exception {
		try {
			if(controle_banco.addUser(nome, login, password)) return true;
			else return false;
		}catch (SQLException e) {
			System.err.println("error ao cadastrar usuario: " + e.getMessage());
		}
		return false;
	}
	public boolean deleteUser(String login, String password) {
		try {
			if(controle_banco.getPasswordByLogin(login).equals(password)) {
				if(controle_banco.deleteUser(login)) {
					return true;
				}
			}else {
				return false;
			}
		}catch(SQLException e) {
			System.err.println("error ao deletar usuario: " + e.getMessage());
		}
		return false;
	}
	
	public boolean updateUsernome(String login,String novoNome) {
		try {
			if(controle_banco.updateNameUser(login, novoNome)) {
				return true;
			}else {
				return false;
			}
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
		return false;
	}
	
	public boolean updateUserpassword(String login, String newPassword) {
		try {
			if(controle_banco.updatePasswordUser(login, newPassword)) {
				return true;
			}
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
		return false;
	}

	public ArrayList<String> getAllUsers(){
		return controle_banco.getAllUsers();
	}
	public boolean addFavoriteActor(String login, String actor) {
		try {
			if(controle_banco.addFavoriteActor(login, actor)) {
				return true;
			}
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
		return false;
	}
	public boolean addFavoriteGenero(String login, String genero) {
		try {
			if(controle_banco.addFavoriteGender(login, genero)) {
				return true;
			}else {
				return false;
			}		
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
		return false;
	}
	public ArrayList<String> filmesAssistidos(String login){
		return controle_banco.getWatchedMovies(login);
	}
}	

