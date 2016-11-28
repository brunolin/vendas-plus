package br.vp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import br.vp.dto.EmpresaDTO;
import br.vp.dto.ProdutoDTO;
import br.vp.dto.VendasDTO;
import br.vp.dto.VendedorDTO;
import br.vp.jdbc.Conexao;
/**
 * 
 * @author Brunolin
 *	Classe da camada DAO de empresa que faz a ligação com o banco de dados
 *	possui vários métodos de consulta e inserção no banco de dados
 */
public class EmpresaDAO {
	
	private ArrayList<VendasDTO>listaVendas;
	private ArrayList<ProdutoDTO>listaProdutos;

	public EmpresaDAO() {

	}
	
	/**
	 * Método responsável por adicionar uma empresa
	 * @param empresa
	 * @return
	 */
	public boolean cadastroEmpresa(EmpresaDTO empresa) {
		String query = "INSERT INTO TB_EMPRESA("
				+ "ID_EMPRESA, NOME_EMPRESA, CNPJ, CIDADE, ESTADO, TELEFONE, EMAIL, SENHA"
				+ ") VALUES(id_seq.nextval, ?, ?, ?, ?, ?, ?, ?)";

		try {

			Connection myConnection = Conexao.getConexao();
			PreparedStatement pstm = myConnection.prepareStatement(query);

			pstm.setString(1, empresa.getNomeEmpresa());
			pstm.setLong(2, empresa.getCnpj());
			pstm.setString(3, empresa.getCidade());
			pstm.setString(4, empresa.getEstado());
			pstm.setLong(5, empresa.getTelefone());
			pstm.setString(6, empresa.getEmail());
			pstm.setString(7, empresa.getSenha());

			pstm.executeQuery();

			System.out.println("Empresa " + empresa.getNomeEmpresa() + " adicionada");

			Conexao.desconectar();

			return true;

		} catch (SQLException e) {

			Conexao.desconectar();
			e.printStackTrace();
			System.out.println("Algo de errado no cadastro de empresa!");
			return false;
		}
	}
	
	/**
	 * Método responsável por adicionar uma campanha por parte da empresa
	 * @param produto
	 * @return
	 */
	public boolean cadastroCampanha(ProdutoDTO produto) {
		String query = "INSERT INTO TB_PRODUTO("
				+ "NOME_PRODUTO, ID_PRODUTO, ID_EMPRESA, PONTOS_RECOMPENSA, IMG, INICIO_CAMPANHA, VIGENCIA_CAMPANHA)"
				+ " VALUES(?, produto_seq.nextval, ?, ?, ?, ?, ?)";

		try {

			Connection myConnection = Conexao.getConexao();
			PreparedStatement pstm = myConnection.prepareStatement(query);

			pstm.setString(1, produto.getNomeProduto());
			pstm.setInt(2, produto.getIdEmpresa());
			pstm.setInt(3, produto.getPontosRecompensa());
			pstm.setString(4, "no");
			pstm.setString(5, produto.getInicioCampanha());
			pstm.setString(6, produto.getVigenciaCampanha());

			pstm.executeQuery();

			System.out.println(produto.getNomeProduto() + " adicionado às campanhas");

			Conexao.desconectar();

			return true;

		} catch (SQLException e) {

			Conexao.desconectar();
			e.printStackTrace();
			System.out.println("Algo de errado no cadastro de campanha!");
			return false;
		}
	}
	
	/**
	 * Método responsável por atualizar uma campanha
	 * @param produto
	 * @return
	 */
	public boolean atualizarCampanha(ProdutoDTO produto) {
		String query = "UPDATE TB_PRODUTO SET NOME_PRODUTO = ?, VIGENCIA_CAMPANHA = ?,"
				+ " PONTOS_RECOMPENSA = ? WHERE ID_PRODUTO = ?";

		try {

			Connection myConnection = Conexao.getConexao();

			PreparedStatement pstm = myConnection.prepareStatement(query);
			
			pstm.setString(1, produto.getNomeProduto());
			pstm.setString(2, produto.getVigenciaCampanha());
			pstm.setInt(3, produto.getPontosRecompensa());
			pstm.setInt(4, produto.getIdProduto());

			pstm.executeQuery();

			System.out.println(produto.getNomeProduto() + " atualizado");

			Conexao.desconectar();

			return true;

		} catch (SQLException e) {

			Conexao.desconectar();
			e.printStackTrace();
			System.out.println("Algo de errado na atualização da campanha!");
			return false;
		}
	}
	
	/**
	 * Método responsável por retornar todas as notas pendentes para avaliação da empresa
	 * @param id
	 * @return
	 */
	public ArrayList<VendasDTO> notasPendentes(int id) {
		String query = "SELECT * FROM TB_VENDAS WHERE ID_EMPRESA = ? AND  APROVADA = ? ";
		listaVendas = new ArrayList<VendasDTO>();
		VendasDTO venda = new VendasDTO();
		try {

			Connection myConnection = Conexao.getConexao();
			PreparedStatement pstm = myConnection.prepareStatement(query);

			pstm.setInt(1, id);
			pstm.setString(2, "F");

			ResultSet rs = pstm.executeQuery();
			
			while(rs.next()){				
				venda.setData(rs.getString("DATA_VENDA"));
				venda.setIdProduto(rs.getInt("ID_PRODUTO"));
				venda.setIdVenda(rs.getString("ID_VENDA"));
				venda.setNomeProduto(rs.getString("NOME_PRODUTO"));
				venda.setIdVendedor(rs.getInt("ID_VENDEDOR"));
				venda.setIdEmpresa(rs.getInt("ID_EMPRESA"));
				
				listaVendas.add(venda);
				venda = new VendasDTO();
			}
			
			System.out.println("Select nas notas pendentes da empresa " + id);
			Conexao.desconectar();

			return listaVendas;

		} catch (SQLException e) {

			Conexao.desconectar();
			e.printStackTrace();
			System.out.println("Algo de errado na consulta de notas pendentes!");
			
			return listaVendas;
		}
	}
	
	/**
	 * Método responsável por retornar todas as notas de uma empresa
	 * @param id
	 * @return
	 */
	public ArrayList<VendasDTO> getNotasEmpresa(int id) {
		String query = "SELECT * FROM TB_VENDAS WHERE ID_EMPRESA = ?";
		listaVendas = new ArrayList<VendasDTO>();
		VendasDTO venda = new VendasDTO();
		try {

			Connection myConnection = Conexao.getConexao();
			PreparedStatement pstm = myConnection.prepareStatement(query);

			pstm.setInt(1, id);

			ResultSet rs = pstm.executeQuery();
			
			while(rs.next()){				
				venda.setData(rs.getString("DATA_VENDA"));
				venda.setIdProduto(rs.getInt("ID_PRODUTO"));
				venda.setIdVenda(rs.getString("ID_VENDA"));
				venda.setNomeProduto(rs.getString("NOME_PRODUTO"));
				venda.setIdVendedor(rs.getInt("ID_VENDEDOR"));
				venda.setIdEmpresa(rs.getInt("ID_EMPRESA"));
				venda.setAprovada(rs.getString("APROVADA"));
				
				listaVendas.add(venda);
				venda = new VendasDTO();
			}
			
			System.out.println("Select de notas da empresa " + id);
			Conexao.desconectar();

			return listaVendas;

		} catch (SQLException e) {

			Conexao.desconectar();
			e.printStackTrace();
			System.out.println("Algo de errado na consulta de notas pendentes!");
			
			return listaVendas;
		}
	}
	
	/**
	 * Métodos responsável por retornar todas as campanhas de uma empresa
	 * @param id
	 * @return
	 */
	public ArrayList<ProdutoDTO> getCampanhas(int id) {
		String query = "SELECT * FROM TB_PRODUTO WHERE ID_EMPRESA = ?";
		listaProdutos = new ArrayList<ProdutoDTO>();
		ProdutoDTO campanha = new ProdutoDTO();
		
		try {

			Connection myConnection = Conexao.getConexao();
			PreparedStatement pstm = myConnection.prepareStatement(query);

			pstm.setInt(1, id);

			ResultSet rs = pstm.executeQuery();
			
			while(rs.next()){				
				campanha.setNomeProduto(rs.getString("NOME_PRODUTO"));
				campanha.setInicioCampanha(rs.getString("INICIO_CAMPANHA"));
				campanha.setVigenciaCampanha(rs.getString("VIGENCIA_CAMPANHA"));
				campanha.setPontosRecompensa(rs.getInt("PONTOS_RECOMPENSA"));
				campanha.setIdProduto(rs.getInt("ID_PRODUTO"));
				
				listaProdutos.add(campanha);
				campanha = new ProdutoDTO();
			}
			
			System.out.println("Select de campanhas da empresa " + id);
			Conexao.desconectar();

			return listaProdutos;

		} catch (SQLException e) {

			Conexao.desconectar();
			e.printStackTrace();
			System.out.println("Algo de errado na consulta campanhas po empresa!");
			
			return listaProdutos;
		}
	}
	
	/**
	 * Método responsável por retornar dados de uma empresa através do CNPJ
	 * @param cnpj
	 * @return
	 */
	public EmpresaDTO getInfoEmpresa(String cnpj) {
		String query = "SELECT * FROM TB_EMPRESA WHERE CNPJ = ?";
		
		EmpresaDTO empresa = new EmpresaDTO();
		
		try {

			Connection myConnection = Conexao.getConexao();
			PreparedStatement pstm = myConnection.prepareStatement(query);

			pstm.setString(1, cnpj);
			
			ResultSet rs = pstm.executeQuery();
			
			while(rs.next()){				
				empresa.setNomeEmpresa(rs.getString("NOME_EMPRESA"));
				empresa.setIdEmpresa(rs.getInt("ID_EMPRESA"));
			}
			
			System.out.println("Retornado dados da empresa com cnpj " + cnpj);
			Conexao.desconectar();

			return empresa;

		} catch (SQLException e) {

			Conexao.desconectar();
			e.printStackTrace();
			System.out.println("Algo de errado na consulta de dados da empresa!");
			
			return empresa;
		}
	}
	
	/**
	 * Método responsável por validar uma venda
	 * @param venda
	 * @return
	 */
	public boolean confirmarVenda(VendasDTO venda) {
		String query = "UPDATE TB_VENDAS SET APROVADA = ?"
				+ " WHERE ID_VENDA = ?";

		try {

			Connection myConnection = Conexao.getConexao();
			PreparedStatement pstm = myConnection.prepareStatement(query);

			pstm.setString(1, "T");
			pstm.setString(2, venda.getIdVenda());
			
			pstm.executeQuery();
			
			System.out.println("Nota confirmada pela empresa " + venda.getIdEmpresa());
			Conexao.desconectar();
			
			VendedorDAO vendedorDAO = new VendedorDAO();
			vendedorDAO.vendaAprovada(venda.getIdVendedor(), venda.getIdProduto());
			
			return true;

		} catch (SQLException e) {

			Conexao.desconectar();
			e.printStackTrace();
			System.out.println("Algo de errado na confirmação nota!");
			return false;
		}
	}
	
	/**
	 * Método responsável por reprovar uma venda
	 * @param venda
	 * @return
	 */
	public boolean reprovarVenda(VendasDTO venda) {
		String query = "UPDATE TB_VENDAS SET APROVADA = ?"
				+ " WHERE ID_VENDA = ?";

		try {

			Connection myConnection = Conexao.getConexao();
			PreparedStatement pstm = myConnection.prepareStatement(query);
			
			pstm.setString(1, "X");
			pstm.setString(2, venda.getIdVenda());

			pstm.executeQuery();

			System.out.println("Nota reprovada pela empresa " + venda.getIdEmpresa());
			Conexao.desconectar();

			return true;

		} catch (SQLException e) {

			Conexao.desconectar();
			e.printStackTrace();
			System.out.println("Algo de errado na reprovação de nota!");
			return false;
		}
	}
	
	/**
	 * Método responsável por retornar a pontuação de um produto em específico
	 * @param id
	 * @return
	 */
	public int getPontosProduto(int id) {
		String query = "SELECT PONTOS_RECOMPENSA FROM TB_PRODUTO WHERE ID_PRODUTO = ?";

		try {

			Connection myConnection = Conexao.getConexao();
			PreparedStatement pstm = myConnection.prepareStatement(query);

			pstm.setInt(1, id);

			ResultSet rs = pstm.executeQuery();
			if(rs.next()) {
				return rs.getInt("PONTOS_RECOMPENSA");
			}
			
			System.out.println("Retornando pontos da campanha " + id);
			
			return 0;

		} catch (SQLException e) {

			Conexao.desconectar();
			e.printStackTrace();
			System.out.println("Algo de errado na consulta de pontuação de produto!");
			return 0;
		}
	}
}
