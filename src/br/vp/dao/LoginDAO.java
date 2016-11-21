package br.vp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import br.vp.jdbc.Conexao;

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
					return true;
				}				
			}
		} catch (SQLException ex) {
			System.out.println("Login error -->");
			return false;
		} finally {
			Conexao.desconectar();
		}
		return false;
	}
}
