package br.vp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import br.vp.dto.ProdutoDTO;
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
	
	public ArrayList<ProdutoDTO> getCampanhas() {
		
		String query = "SELECT * FROM TB_PRODUTO";
		
		try {
			
			ArrayList<ProdutoDTO> produtos = new ArrayList<ProdutoDTO>();
			ProdutoDTO produto = new ProdutoDTO();
			
			Connection myConnection = Conexao.getConexao();
			PreparedStatement pstm = myConnection.prepareStatement(query);
			ResultSet rs = pstm.executeQuery();

			while(rs.next()){
				produto.setNomeProduto(rs.getString("NOME_PRODUTO"));
				produto.setIdProduto(rs.getInt("ID_PRODUTO"));
				produto.setIdEmpresa(rs.getInt("ID_EMPRESA"));
				produto.setPontosRecompensa(rs.getInt("PONTOS_RECOMPENSA"));
				produto.setImg(rs.getString("IMG"));
				produto.setInicioCampanha(rs.getDate("INICIO_CAMPANHA"));
				produto.setVigenciaCampanha(rs.getDate("VIGENCIA_CAMPANHA"));				
				
				produtos.add(produto);
				produto = new ProdutoDTO();
			}
			Conexao.desconectar();

			return produtos;

		} catch (SQLException e) {

			Conexao.desconectar();
			e.printStackTrace();
			System.out.println("Algo de errado no cadastro do Vendedor!");
			return null;
		}
	}
}
