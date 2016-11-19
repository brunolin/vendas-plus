package br.vp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import br.vp.dto.EmpresaDTO;
import br.vp.dto.ProdutoDTO;
import br.vp.dto.VendasDTO;
import br.vp.jdbc.Conexao;

public class EmpresaDAO {
	
	private ArrayList<VendasDTO>listaVendas;

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
				+ "NOME_PRODUTO, ID_PRODUTO, ID_EMPRESA, PONTOS_RECOMPENSA, IMG, INICIO_CAMPANHA, VIGENCIA_CAMPANHA)"
				+ " VALUES(?, produto_seq.nextval, ?, ?, ?, ?, ?)";

		try {

			Connection myConnection = Conexao.getConexao();

			PreparedStatement pstm = myConnection.prepareStatement(query);

			/*TO DO - adicionar data de cadastro para empresa e vendedor */

			//setting values for insert in pessoa table
			pstm.setString(1, produto.getNomeProduto());
			pstm.setInt(2, 71);
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
	
	public ArrayList<VendasDTO> notasPendentes(int id) {
		String query = "SELECT * FROM TB_VENDAS WHERE ID_EMPRESA = ? AND  APROVADA = ? ";
		listaVendas = new ArrayList<VendasDTO>();
		VendasDTO venda = new VendasDTO();
		try {

			Connection myConnection = Conexao.getConexao();

			PreparedStatement pstm = myConnection.prepareStatement(query);

			pstm.setInt(1, id);
			pstm.setString(2, "F");
			
			//executeUpdate() for table update
			ResultSet rs = pstm.executeQuery();
			
			while(rs.next()){				
				venda.setData(rs.getString("DATA_VENDA"));
				venda.setIdProduto(rs.getInt("ID_PRODUTO"));
				venda.setIdVenda(rs.getString("ID_VENDA"));
				venda.setNomeProduto(rs.getString("NOME_PRODUTO"));
				venda.setIdVendedor(rs.getInt("ID_VENDEDOR"));
				venda.setIdEmpresa(rs.getInt("ID_EMPRESA"));
				
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
	
	public boolean confirmarVenda(VendasDTO venda) {
		String query = "UPDATE TB_VENDAS SET APROVADA = ?"
				+ " WHERE ID_VENDA = ?";

		try {

			Connection myConnection = Conexao.getConexao();

			PreparedStatement pstm = myConnection.prepareStatement(query);

			/*TO DO - adicionar data de cadastro para empresa e vendedor */

			//setting values for insert in pessoa table
			pstm.setString(1, "T");
			pstm.setString(2, venda.getIdVenda());
			
			//executeUpdate() for table update
			pstm.executeQuery();

			Conexao.desconectar();

			return true;

		} catch (SQLException e) {

			Conexao.desconectar();
			e.printStackTrace();
			System.out.println("Algo de errado na confirmação nota!");
			return false;
		}
	}
	
	public boolean reprovarVenda(VendasDTO venda) {
		String query = "UPDATE TB_VENDAS SET APROVADA = ?"
				+ " WHERE ID_VENDA = ?";

		try {

			Connection myConnection = Conexao.getConexao();

			PreparedStatement pstm = myConnection.prepareStatement(query);

			/*TO DO - adicionar data de cadastro para empresa e vendedor */

			//setting values for insert in pessoa table
			pstm.setString(1, "X");
			pstm.setString(2, venda.getIdVenda());
			
			//executeUpdate() for table update
			pstm.executeQuery();

			Conexao.desconectar();

			return true;

		} catch (SQLException e) {

			Conexao.desconectar();
			e.printStackTrace();
			System.out.println("Algo de errado na confirmação nota!");
			return false;
		}
	}

}
