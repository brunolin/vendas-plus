package br.vp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.vp.dto.EmpresaDTO;
import br.vp.dto.VendasDTO;
import br.vp.jdbc.Conexao;

public class EmpresaDAO {
	
	private List<VendasDTO>listaVendas;

	public EmpresaDAO() {

	}

	public boolean cadastroEmpresa(EmpresaDTO empresa) {
		String query = "INSERT INTO TB_EMPRESA("
				+ "ID_EMPRESA, NOME_EMPRESA, CNPJ, CIDADE, ESTADO, TELEFONE, EMAIL, SENHA"
				+ ") VALUES(id_seq.nextval, ?, ?, ?, ?, ?, ?, ?)";

		try {

			Connection myConnection = Conexao.getConexao();

			PreparedStatement pstm = myConnection.prepareStatement(query);

			/*TO DO - adicionar data de cadastro para empresa e vendedor */

			//setting values for insert in pessoa table
			pstm.setString(1, empresa.getNomeEmpresa());
			pstm.setInt(2, empresa.getCnpj());
			pstm.setString(3, empresa.getCidade());
			pstm.setString(4, empresa.getEstado());
			pstm.setInt(5, empresa.getTelefone());
			pstm.setString(6, empresa.getEmail());
			pstm.setString(7, empresa.getSenha());


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
	public List<VendasDTO> notasPendentes(EmpresaDTO empresa) {
		String query = "SELECT * FROM TB_VENDAS WHERE ID_EMPRESA = ? AND  APROVADA = FALSE";
		listaVendas = new ArrayList<VendasDTO>();
		VendasDTO venda = new VendasDTO();
		try {

			Connection myConnection = Conexao.getConexao();

			PreparedStatement pstm = myConnection.prepareStatement(query);

			//setting values for insert in pessoa table
			pstm.setInt(1, empresa.getIdEmpresa());
			
			//executeUpdate() for table update
			ResultSet rs = pstm.executeQuery();
			
			while(rs.next()){
				venda.setNomeProduto(rs.getString("NOME_PRODUTO"));
				venda.setIdProduto(rs.getInt("ID_PRODUTO"));
				venda.setData(rs.getDate("DATA"));
				
				listaVendas.add(venda);
			}

			Conexao.desconectar();

			return listaVendas;

		} catch (SQLException e) {

			Conexao.desconectar();
			e.printStackTrace();
			System.out.println("Algo de errado na consulta de notas pendentes!");
			
			return listaVendas;
		}
	}

}
