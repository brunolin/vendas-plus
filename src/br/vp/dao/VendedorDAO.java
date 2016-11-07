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
				+ ") VALUES(id_seq.nextval, ?, ?, ?, ?, ?, ?, ?,?)";

		try {

			Connection myConnection = Conexao.getConexao();

			PreparedStatement pstm = myConnection.prepareStatement(query);

			/*TO DO - adicionar data de cadastro para empresa e vendedor */

			//setting values for insert in pessoa table
			pstm.setString(1, vendedor.getNome());
			pstm.setInt(2, vendedor.getCpf());
			pstm.setInt(3, 0);
			pstm.setInt(4, vendedor.getTelefone());
			pstm.setString(5, vendedor.getCidade());
			pstm.setString(6, vendedor.getEstado());
			pstm.setString(7, vendedor.getEmail());
			pstm.setString(8, vendedor.getSenha());


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
