package br.vp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import br.vp.dto.VendedorDTO;
import br.vp.jdbc.Conexao;

public class VendedorDAO {
	
	public VendedorDAO() {
		
	}
	
	public boolean cadastroVendedor(VendedorDTO vendedor) {
		String query = "INSERT INTO TB_VENDEDOR("
				+ "ID_VENDEDOR, NOME_VENDEDOR, CPF,PONTOS, TELEFONE, CIDADE, ESTADO, EMAIL, SENHA"
				+ ") VALUES(?, ?, ?, ?, ?, ?, ?, ?,?)"; 
		
		try {
			
			Connection myConnection = Conexao.getConexao();
			
			PreparedStatement pstm = myConnection.prepareStatement(query);
			
			/*TO DO - adicionar data de cadastro para empresa e vendedor */
			
			//setting values for insert in pessoa table
			pstm.setInt(1, vendedor.getIdVendedor());
			pstm.setString(2, vendedor.getNome());
			pstm.setInt(3, vendedor.getCpf());
			pstm.setInt(4,vendedor.getPontos());
			pstm.setInt(5, vendedor.getTelefone());
			pstm.setString(6, vendedor.getCidade());
			pstm.setString(7, vendedor.getEstado());
			pstm.setString(8, vendedor.getEmail());
			pstm.setString(9, vendedor.getSenha()); 	
			
			
			//executeUpdate() for table update
			pstm.executeQuery();
			
			System.out.println(vendedor.toString());
			
			Conexao.desconectar();
			
			return true;
			
		} catch (SQLException e) {
			
			Conexao.desconectar();
			e.printStackTrace();
			System.out.println("Algo de errado no cadastro do Vendedor!");
			return false;
		}
	}
}
