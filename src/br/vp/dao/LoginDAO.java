package br.vp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import br.vp.jdbc.Conexao;

/**
 * 
 * @author Bruno Wellython
 *	
 *	Classe responsável pela consulta do usuário no banco de dados.
 *	O método de validação retorna um booleano caso tenha ou não validado aquele login
 *	e senha informados.
 */
public class LoginDAO {

	public LoginDAO() {

	}

	public static boolean validate(String id, String senha) {
		Connection con = null;
		PreparedStatement ps = null;

		try {
			
			if(id.length() == 11){	
				System.out.println("CPF");
				String query = "Select cpf, senha from tb_vendedor where cpf = ? and senha = ?";
				
				con = Conexao.getConexao();
				ps = con.prepareStatement(query);
				ps.setString(1, id);
				ps.setString(2, senha);
	
				ResultSet rs = ps.executeQuery();
	
				if (rs.next()) {
					System.out.println("Vendedor logado");
					return true;
				}
			} else if(id.length() == 14) {
				System.out.println("CNPJ");
				String query = "Select cnpj, senha from tb_empresa where cnpj = ? and senha = ?";
				
				con = Conexao.getConexao();
				ps = con.prepareStatement(query);
				ps.setString(1, id);
				ps.setString(2, senha);
	
				ResultSet rs = ps.executeQuery();
	
				if (rs.next()) {
					System.out.println("Empresa logada");
					return true;
				}				
			}
		} catch (SQLException ex) {
			System.out.println("Erro no login");
			return false;
		} finally {
			Conexao.desconectar();
		}
		return false;
	}
}
