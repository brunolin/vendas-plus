package br.vp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import br.vp.dto.EmpresaDTO;
import br.vp.jdbc.Conexao;

public class EmpresaDAO {
	
	public EmpresaDAO() {
		
	}
	
	public boolean cadastroEmpresa(EmpresaDTO empresa) {
		String query = "INSERT INTO TB_EMPRESA("
				+ "ID_EMPRESA, NOME_EMPRESA, CNPJ, CIDADE, ESTADO, TELEFONE, EMAIL, SENHA"
				+ ") VALUES(?, ?, ?, ?, ?, ?, ?, ?)";
		
		try {
			
			Connection myConnection = Conexao.getConexao();
			
			PreparedStatement pstm = myConnection.prepareStatement(query);
			
			/*TO DO - adicionar data de cadastro para empresa e vendedor */
			
			//setting values for insert in pessoa table
			pstm.setInt(1, empresa.getIdEmpresa());
			pstm.setString(2, empresa.getNomeEmpresa());
			pstm.setInt(3, empresa.getCnpj());
			pstm.setString(4, empresa.getCidade());
			pstm.setString(5, empresa.getEstado());
			pstm.setInt(6, empresa.getTelefone());
			pstm.setString(7, empresa.getEmail());
			pstm.setString(8, empresa.getSenha());
			
			
			//executeUpdate() for table update
			pstm.executeQuery();
			
			System.out.println(empresa.toString());
			
			Conexao.desconectar();
			
			return true;
			
		} catch (SQLException e) {
			
			Conexao.desconectar();
			e.printStackTrace();
			System.out.println("Algo de errado no cadastro de empresa!");
			return false;
		}
	}
}
