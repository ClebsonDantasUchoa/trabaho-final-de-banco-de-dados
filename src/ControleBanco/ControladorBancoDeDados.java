package ControleBanco;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLDataException;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class ControladorBancoDeDados {
	private Connection connection = null;
	String url = "jdbc:postgresql://localhost:5432/projeto_final_fbd";
		
 	public void initializeConnection() {
		try {
			Class.forName("org.postgresql.Driver");
			connection = DriverManager.getConnection(url, "postgres", "1154674");
		}catch(Exception e) {
			System.out.println("erro ao conectar com o banco de dados: " + e.getMessage());
		}
	}
	
	public int executeSQL(String sql) {
		try {
			initializeConnection();
			Statement stm = connection.createStatement();
			int res = stm.executeUpdate(sql);
			connection.close();
			return res;
		}catch(Exception e) {
			System.out.println(e.getMessage());	
			return 0;
		}
	}
	
	public boolean deleteUser(String login) throws SQLException {
		int resultado = 0;
		try {
			initializeConnection();
			String sql = "delete from usuario where login = '" + login + "'";
			PreparedStatement pst = connection.prepareStatement(sql);
			resultado = pst.executeUpdate();
			connection.close();
		}catch(SQLException e) {
			System.out.println("erro ao remover usuario: " + e.getMessage());
		}
		System.out.println("resultado " + resultado);
		if(resultado <= 0) {
			return false;
		}
		return true;
	}
	
	public boolean addUser(String nome, String login, String senha) throws Exception{
		int res = 0;
		try {
			initializeConnection();
			Statement stm = connection.createStatement();
			String sql = "insert into usuario(nome, login, senha) values ( '" +
						nome+ "', '" +
						login+ "', '" +
						senha + "')";
			
			res = stm.executeUpdate(sql);			
			connection.close();
			
		}catch(Exception e) {
			System.out.println("error ao cadastrar usuario: " + e.getMessage());
		}
		if(res <= 0) {
			return false;
		}
		return true;
	}
	
	public ArrayList<String> getAllUsers(){
		ArrayList<String> usuarios = null;
		
		try {
			initializeConnection();
			Statement stm = connection.createStatement();
			ResultSet rs = stm.executeQuery("select * from usuario");
			usuarios = new ArrayList<String>();
			
			while(rs.next()) {
				int id = rs.getInt("id");	
				String nome = rs.getString("nome");
				String login = rs.getString("login");
				
				String usuario = "Id: " + id + ", nome: " + nome + ", login: " + login;
				usuarios.add(usuario);
			}
			
			connection.close();		
			
		}catch(Exception e) {
			System.out.println("error ao listar todos os usuarios: " + e.getMessage());
		}
		return usuarios;
	}
	
	public String getPasswordByLogin(String login) {
		String senha = "";
		try {
			initializeConnection();
			Statement stm = connection.createStatement();
			ResultSet rs = stm.executeQuery("select senha from usuario where login = '" + login + "'");
			while(rs.next()) {
				senha = rs.getString("senha");
				break;
			}
		}catch(Exception e) {
			System.out.println("error ao pegar senha pelo Login: " + e.getMessage());
		}
		return senha;
	}
	
	public ArrayList<String> recommendMovies(String login){
		ArrayList<String> filmes = new ArrayList<String>();
		
		try {
			initializeConnection();
			Statement stm = connection.createStatement();
			ResultSet user = stm.executeQuery("select * from usuario where login = '" + login + "'");
			int id_user = 0;
			while(user.next()) {
				id_user = user.getInt("id");
				break;
			}
			ResultSet rs = stm.executeQuery("select * from recomendacaoFilmes("+id_user+")");
			while(rs.next()) {	
				String nome_filme = rs.getString("nome");
				filmes.add(nome_filme);
			}
			connection.close();		
			
		}catch(Exception e) {
			System.out.println("error ao listar recomendações de filme: " + e.getMessage());
		}
		return filmes;
	}
	
	public boolean addFavoriteActor(String login, String actor) throws SQLException {
		int res = 0;
		try {
			int idUser = 0;
			initializeConnection();
			Statement stm = connection.createStatement();
			ResultSet resultSet = stm.executeQuery("select id from usuario where login = '" + login + "'");
			while(resultSet.next()) {
				idUser = resultSet.getInt("id");
				break;
			}
			String sql = "insert into ator_preferido(id_usuario, nome_ator) values ("+
						idUser+ ", '" +
						actor+ "')";
			res = stm.executeUpdate(sql);			
			connection.close();
		}catch(SQLException e) {
			System.err.println("error ao adicionar Ator preferido: " + e.getMessage());
		}
		if(res <= 0) {
			return false;
		}
		return true;
	}

	public boolean addFavoriteGender(String login, String genero) throws SQLDataException{
		int res = 0;
		try {
			int idUser = 0;
			int idGender = 0;
			initializeConnection();
			Statement stmFG1 = connection.createStatement();
			Statement stmFG2 = connection.createStatement();
			ResultSet resultSetLogin = stmFG1.executeQuery("select id from usuario where login = '" + login + "'");
			ResultSet resultSetGender = stmFG2.executeQuery("select id from genero where gnome = '" + genero + "'");
			while(resultSetLogin.next()) {
				idUser = resultSetLogin.getInt("id");
				break;
			}
			while(resultSetGender.next()) {
				idGender = resultSetGender.getInt("id");
				break;
			}
			String sql = 
					"insert into genero_preferido(id_usuario, id_genero) values ("+idUser+ ", "+idGender+ ")";
			res = stmFG1.executeUpdate(sql);			
			connection.close();
		}catch(SQLException e) {
			System.err.println("error ao adicionar Genero preferido: " + e.getMessage());
		}
		if(res <= 0) {
			return false;
		}
		return true;
	}

	public boolean watchMovie(String login, String filme) throws SQLException{
		int res = 0;
		try {
			int idUser = 0;
			int idMovie = 0;
			initializeConnection();
			Statement stmWM1 = connection.createStatement();
			Statement stmWM2 = connection.createStatement();
			ResultSet resultSetLogin = stmWM1.executeQuery("select id from usuario where login = '" + login + "'");
			ResultSet resultSetMovie = stmWM2.executeQuery("select fid from filme where fnome = '" + filme + "'");
			while(resultSetLogin.next()) {
				idUser = resultSetLogin.getInt("id");
				break;
			}
			while(resultSetMovie.next()) {
				idMovie = resultSetMovie.getInt("fid");
				break;
			}
			String sql = 
					"insert into filme_assistido(id_usuario, id_filme) values ("+idUser+ ", "+idMovie+ ")";
			res = stmWM1.executeUpdate(sql);			
			connection.close();
		}catch(SQLException e) {
			System.err.println("error ao adicionar Genero preferido: " + e.getMessage());
		}
		if(res <= 0) {
			return false;
		}
		return true;
	}

	public boolean addGender(String nome) throws SQLException{
		int res = 0;
		try {
			initializeConnection();
			Statement stmAG = connection.createStatement();
			String sql = "insert into genero(gnome) values ( '" +nome+ "')";
			res = stmAG.executeUpdate(sql);			
			connection.close();
		}catch(Exception e) {
			System.out.println("error ao cadastrar usuario: " + e.getMessage());
		}
		if(res <= 0) {
			return false;
		}
		return true;
	}
	
	public boolean addMovie(String nome, String ator1, String ator2, String genero) {
		int res = 0;
		int idGenero = 0;
		try {
			initializeConnection();
			Statement stmM = connection.createStatement();
			ResultSet resultSetGenero = stmM.executeQuery("select id from genero where gnome = '" +genero+ "'");
			while(resultSetGenero.next()) {
				idGenero = resultSetGenero.getInt("id");
				break;
			}
			String sql = 
			"insert into filme(fnome, ator1, ator2, genero_id) values ('"+nome+"', '"+ator1+"', '"+ator2+"', "+idGenero+")";
			res = stmM.executeUpdate(sql);			
			connection.close();
		}catch(Exception e) {
			System.out.println("error ao cadastrar Filme: " + e.getMessage());
		}
		if(res <= 0) {
			return false;
		}
		return true;
	}
	
	public ArrayList<String> getAllMovies(){
		ArrayList<String> filmes = new ArrayList<String>();	
		try {
			initializeConnection();
			Statement stm = connection.createStatement();
			ResultSet rsFilmes = stm.executeQuery("select * from getallmovies()");
			while(rsFilmes.next()) {
				String cadaFilme = "";
				cadaFilme = "Nome: "+rsFilmes.getString("nome")+", Ator principal: " +
						rsFilmes.getString("atorp") + ", Ator secundario: " + rsFilmes.getString("ators") +
						", Genero: "+ rsFilmes.getString("nomeg");
				filmes.add(cadaFilme);
			}
			connection.close();					
		}catch(Exception e) {
			System.out.println("error ao listar todos os filmes: " + e.getMessage());
		}
		return filmes;
	}

	public ArrayList<String> getWatchedMovies(String login){
		ArrayList<String> filmes = new ArrayList<String>();	
		try {
			initializeConnection();
			Statement stm = connection.createStatement();
			ResultSet rsFilmes = stm.executeQuery("select * from watchedmovies('" +login+"')");
			while(rsFilmes.next()) {
				String cadaFilme = "Filme: "+ rsFilmes.getString("nomefilme");
				filmes.add(cadaFilme);
			}
			connection.close();					
		}catch(Exception e) {
			System.out.println("error ao listar filmes assistidos: " + e.getMessage());
		}
		return filmes;
	}

	public boolean updateNameUser(String login, String nome) {
		int res = 0;
		try {
			initializeConnection();
			Statement stm = connection.createStatement();
			String sql = "update usuario set nome = '"+nome+"' where login = '"+login+"'";
			res = stm.executeUpdate(sql);			
			connection.close();
		}catch(Exception e) {
			System.out.println("error ao atualizar login do usuario: " + e.getMessage());
		}
		if(res <= 0) {
			return false;
		}
		return true;
	}

	public boolean updatePasswordUser(String login, String novasenha) {
		int res = 0;
		try {
			initializeConnection();
			Statement stm = connection.createStatement();
			String sql = "update usuario set senha = '"+novasenha+"' where login = '"+login+"'";
			res = stm.executeUpdate(sql);			
			connection.close();
		}catch(Exception e) {
			System.out.println("error ao atualizar login do usuario: " + e.getMessage());
		}
		if(res <= 0) {
			return false;
		}
		return true;
	}
}

