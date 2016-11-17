package br.vp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.vp.dto.EmpresaDTO;
import br.vp.dto.ProdutoDTO;
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
			pstm.setLong(2, empresa.getCnpj());
			pstm.setString(3, empresa.getCidade());
			pstm.setString(4, empresa.getEstado());
			pstm.setLong(5, empresa.getTelefone());
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
	
	public boolean cadastroCampanha(ProdutoDTO produto) {
		String query = "INSERT INTO TB_PRODUTO("
				+ "NOME_PRODUTO, ID_PRODUTO, ID_EMPRESA, PONTOS_RECOMPENSA, IMG, INICIO_CAMPANHA, VIGENCIA_CAMPANHA"
				+ " VALUES(?, produto_seq.nextval, ?, ?, ?, ?, ?)";

		try {

			Connection myConnection = Conexao.getConexao();

			PreparedStatement pstm = myConnection.prepareStatement(query);

			/*TO DO - adicionar data de cadastro para empresa e vendedor */

			//setting values for insert in pessoa table
			pstm.setString(1, produto.getNomeProduto());
			pstm.setInt(2, produto.getIdEmpresa());
			pstm.setInt(3, produto.getPontosRecompensa());
			pstm.setString(4, "no");
			pstm.setString(5, produto.getInicioCampanha());
			pstm.setString(6, produto.getVigenciaCampanha());


			//executeUpdate() for table update
			pstm.executeQuery();

			System.out.println(produto.toString());

			Conexao.desconectar();

			return true;

		} catch (SQLException e) {

			Conexao.desconectar();
			e.printStackTrace();
			System.out.println("Algo de errado no cadastro de campanha!");
			return false;
		}
	}
	
	public List<VendasDTO> notasPendentes(EmpresaDTO empresa) {
		String query = "SELECT * FROM TB_VENDAS WHERE ID_EMPRESA = ? AND  APROVADA = ? ";
		listaVendas = new ArrayList<VendasDTO>();
		VendasDTO venda = new VendasDTO();
		try {

			Connection myConnection = Conexao.getConexao();

			PreparedStatement pstm = myConnection.prepareStatement(query);

			pstm.setInt(1, empresa.getIdEmpresa());
			pstm.setString(2, "N");
			
			//executeUpdate() for table update
			ResultSet rs = pstm.executeQuery();
			
			while(rs.next()){
				venda.setNomeProduto(rs.getString("NOME_PRODUTO"));
				venda.setIdProduto(rs.getInt("ID_PRODUTO"));
				venda.setData(rs.getString("DATA_VENDA"));
				
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
