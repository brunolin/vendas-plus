package br.vp.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import br.vp.dto.BonusDTO;
import br.vp.dto.ProdutoDTO;
import br.vp.dto.VendasDTO;
import br.vp.dto.VendedorDTO;
import br.vp.jdbc.Conexao;

/**
 * 
 * @author Brunolin
 *	Classe da camada DAO de vendedor que faz a ligação com o banco de dados
 *	possui vários métodos de consulta e inserção no banco de dados
 */
public class VendedorDAO {

	public VendedorDAO() {

	}
	
	/**
	 * Método responsável por adicionar vendedor ao banco de dados
	 * Usando Stored Procedure
	 * @param vendedor
	 * @return
	 */
	public boolean cadastroVendedor(VendedorDTO vendedor) {

		String query = "{call proc_vendedor_insert(id_seq.nextval, ?, ?, ?, ?, ?, ?, ?, ?)}";

		try {

			Connection myConnection = Conexao.getConexao();
			
			CallableStatement callableStatement = myConnection.prepareCall(query);
			callableStatement.setString(1, vendedor.getNome());
			callableStatement.setString(2, vendedor.getCpf());
			callableStatement.setInt(3, 0);
			callableStatement.setLong(4, vendedor.getTelefone());
			callableStatement.setString(5, vendedor.getCidade());
			callableStatement.setString(6, vendedor.getEstado());
			callableStatement.setString(7, vendedor.getEmail());
			callableStatement.setString(8, vendedor.getSenha());
			
			callableStatement.executeQuery();

			System.out.println("Vendedor" + vendedor.getNome() + " cadastrado!");

			Conexao.desconectar();

			return true;

		} catch (SQLException e) {

			Conexao.desconectar();
			e.printStackTrace();
			System.out.println("Algo de errado no cadastro do Vendedor!");
			return false;
		}
	}
	
	/**
	 * Método responsável por retornar infomações de um vendedor específico pelo seu CPF
	 * @param cpf
	 * @return
	 */
	public VendedorDTO getInfoVendedor(String cpf) {
		String query = "SELECT * FROM TB_VENDEDOR WHERE CPF = ?";
		
		VendedorDTO vendedor = new VendedorDTO();
		
		try {

			Connection myConnection = Conexao.getConexao();
			PreparedStatement pstm = myConnection.prepareStatement(query);

			pstm.setString(1, cpf);

			ResultSet rs = pstm.executeQuery();
			
			while(rs.next()){				
				vendedor.setPontos(rs.getInt("PONTOS"));
				vendedor.setNome(rs.getString("NOME_VENDEDOR"));
				vendedor.setIdVendedor(rs.getInt("ID_VENDEDOR"));
			}
			System.out.println("Buscando informacoes do vendedor " + vendedor.getNome() + " por CPF");
			Conexao.desconectar();

			return vendedor;

		} catch (SQLException e) {

			Conexao.desconectar();
			e.printStackTrace();
			System.out.println("Algo de errado na consulta de dados do vendedor!");
			
			return vendedor;
		}
	}
	
	public VendedorDTO getInfoVendedorByEmail(String email) {
		String query = "SELECT * FROM TB_VENDEDOR WHERE EMAIL = ?";
		
		System.out.println(email);
		VendedorDTO vendedor = new VendedorDTO();
		try {

			Connection myConnection = Conexao.getConexao();
			PreparedStatement pstm = myConnection.prepareStatement(query);

			pstm.setString(1, email);

			ResultSet rs = pstm.executeQuery();
			
			while(rs.next()){				
				vendedor.setPontos(rs.getInt("PONTOS"));
				vendedor.setNome(rs.getString("NOME_VENDEDOR"));
				vendedor.setCidade(rs.getString("CIDADE"));
				vendedor.setEstado(rs.getString("ESTADO"));
				vendedor.setEmail(rs.getString("EMAIL"));
				vendedor.setTelefone(rs.getLong("TELEFONE"));
			}
			System.out.println("Buscando informacoes do vendedor " + vendedor.getNome() + " por EMAIL");
			Conexao.desconectar();

			return vendedor;

		} catch (SQLException e) {

			Conexao.desconectar();
			e.printStackTrace();
			System.out.println("Algo de errado na consulta de dados do vendedor!");
			
			return vendedor;
		}
	}
	
	/**
	 * Método responsável por retornar infomações de um vendedor específico pelo seu ID
	 * @param id
	 * @return
	 */
	public VendedorDTO getInfoVendedorId(int id) {
		String query = "SELECT * FROM TB_VENDEDOR WHERE ID_VENDEDOR = ?";
		
		VendedorDTO vendedor = new VendedorDTO();
		
		try {

			Connection myConnection = Conexao.getConexao();
			PreparedStatement pstm = myConnection.prepareStatement(query);

			pstm.setInt(1, id);

			ResultSet rs = pstm.executeQuery();
			
			while(rs.next()){				
				vendedor.setPontos(rs.getInt("PONTOS"));
				vendedor.setNome(rs.getString("NOME_VENDEDOR"));
				vendedor.setIdVendedor(rs.getInt("ID_VENDEDOR"));
			}
			
			System.out.println("Buscando informacoes do vendedor " + vendedor.getNome() + " por ID");
			Conexao.desconectar();

			return vendedor;

		} catch (SQLException e) {

			Conexao.desconectar();
			e.printStackTrace();
			System.out.println("Algo de errado na consulta de dados do vendedor!");
			
			return vendedor;
		}
	}
	
	/**
	 * Método responsável por adicionar uma venda por parte de um vendedor
	 * @param venda
	 * @return
	 */
	public boolean cadastroVenda(VendasDTO venda) {
		String query = "INSERT INTO TB_VENDAS("
				+ "ID_VENDA, ID_PRODUTO, ID_VENDEDOR, ID_EMPRESA, NOME_PRODUTO, DATA_VENDA, APROVADA) "
				+ "VALUES(?, ?, ?, ?, ?, ?, ?)";

		try {

			Connection myConnection = Conexao.getConexao();
			PreparedStatement pstm = myConnection.prepareStatement(query);

			pstm.setString(1, venda.getIdVenda());
			pstm.setInt(2, venda.getIdProduto());
			pstm.setInt(3, venda.getIdVendedor());
			pstm.setInt(4, venda.getIdEmpresa());
			pstm.setString(5, venda.getNomeProduto());
			pstm.setString(6, venda.getData());
			pstm.setString(7, "F");

			pstm.executeQuery();

			System.out.println("Uma venda do produto " + venda.getNomeProduto() + " confirmada");

			Conexao.desconectar();

			return true;

		} catch (SQLException e) {

			Conexao.desconectar();
			e.printStackTrace();
			System.out.println("Algo de errado no cadastro da nota!");
			return false;
		}
	}
	
	/**
	 * Método responsável por adicionar pontos ao vendedor, caso sua venda seja aprovada por uma empresa
	 * 
	 * @param idVendedor
	 * @param idProduto
	 * @return
	 */
	public boolean vendaAprovada(int idVendedor, int idProduto) {
		String query = "UPDATE TB_VENDEDOR SET PONTOS = ? WHERE ID_VENDEDOR = ?";

		try {
			
			VendedorDTO vendedor = getInfoVendedorId(idVendedor);
			EmpresaDAO empresaDAO = new EmpresaDAO();
			int pontos = empresaDAO.getPontosProduto(idProduto);
			
			Connection myConnection = Conexao.getConexao();
			PreparedStatement pstm = myConnection.prepareStatement(query);

			pstm.setInt(1, vendedor.getPontos() + pontos);
			pstm.setInt(2, idVendedor);

			pstm.executeQuery();
			
			System.out.println("Venda aprovada para o vendedor  " + vendedor.getNome());
			Conexao.desconectar();

			return true;

		} catch (SQLException e) {

			Conexao.desconectar();
			e.printStackTrace();
			System.out.println("Algo de errado ao creditar pontos ao vendedor!");
			return false;
		}
	}
	
	/**
	 * Método responsável por retornar as todas as campanhas em vigência
	 * @return
	 */
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
				produto.setInicioCampanha(rs.getString("INICIO_CAMPANHA"));
				produto.setVigenciaCampanha(rs.getString("VIGENCIA_CAMPANHA"));				
				
				produtos.add(produto);
				produto = new ProdutoDTO();
			}
			
			System.out.println("Select de produtos");
			Conexao.desconectar();

			return produtos;

		} catch (SQLException e) {

			Conexao.desconectar();
			e.printStackTrace();
			System.out.println("Algo de errado no cadastro do Vendedor!");
			return null;
		}
	}
	
	/**
	 * Método responsável por retornar todos os bonus disponíveis
	 * @return
	 */
	public ArrayList<BonusDTO> getBonus() {
		
		String query = "SELECT * FROM TB_BONUS";
		ArrayList<BonusDTO> produtos = new ArrayList<BonusDTO>();
		
		try {
			
			BonusDTO bonus = new BonusDTO();
			
			Connection myConnection = Conexao.getConexao();
			PreparedStatement pstm = myConnection.prepareStatement(query);
			ResultSet rs = pstm.executeQuery();

			while(rs.next()){
				bonus.setDescricao(rs.getString("DESCRICAO"));
				bonus.setPontosNecessarios(rs.getInt("PONTOS_NECESSARIOS"));
				bonus.setIdBonus(rs.getInt("ID_BONUS"));
				bonus.setNome(rs.getString("NOME_BONUS"));
				
				produtos.add(bonus);
				bonus = new BonusDTO();
			}
			
			System.out.println("Select de bonus");
			Conexao.desconectar();

			return produtos;

		} catch (SQLException e) {

			Conexao.desconectar();
			e.printStackTrace();
			System.out.println("Algo de errado na busca de bonus!");
			return produtos;
		}
	}
	
	/**
	 * Método responsável por retornar todas as notas de um vendedor em específico
	 * @param id
	 * @return
	 */
	public ArrayList<VendasDTO> getNotasVendedor(int id) {
		
		String query = "SELECT * FROM TB_VENDAS WHERE ID_VENDEDOR = ?";
		
		try {
			
			ArrayList<VendasDTO> vendas = new ArrayList<VendasDTO>();
			VendasDTO venda = new VendasDTO();
			
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
				
				vendas.add(venda);
				venda = new VendasDTO();
			}
			
			System.out.println("Select de notas para o vendedor " + id);
			Conexao.desconectar();

			return vendas;

		} catch (SQLException e) {

			Conexao.desconectar();
			e.printStackTrace();
			System.out.println("Algo de errado no select de notas do Vendedor!");
			return null;
		}
	}
	
	/**
	 * Métodos responsável para fazer o descontos dos pontos de um vendedor quando ele resgata um bonus.
	 * @param pontos
	 * @param cpf
	 * @return
	 */
	public boolean resgatarBonus(int pontos, String cpf) {
		String query = "UPDATE TB_VENDEDOR SET PONTOS = ? WHERE CPF = ?";

		try {
			
			VendedorDTO vendedor = getInfoVendedor(cpf);
			
			Connection myConnection = Conexao.getConexao();
			PreparedStatement pstm = myConnection.prepareStatement(query);

			pstm.setInt(1, vendedor.getPontos() - pontos);
			pstm.setString(2, cpf);

			pstm.executeQuery();

			System.out.println("Foram descontados " + pontos + " pontos do vendedor " + vendedor.getNome());
			Conexao.desconectar();

			return true;

		} catch (SQLException e) {

			Conexao.desconectar();
			e.printStackTrace();
			System.out.println("Algo de errado ao reagatar bonus do vendedor!");
			return false;
		}
	}
}
