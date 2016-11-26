package br.vp.dao;

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
			pstm.setString(2, vendedor.getCpf());
			pstm.setInt(3, 0);
			pstm.setLong(4, vendedor.getTelefone());
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
		
	public VendedorDTO getInfoVendedor(String cpf) {
		String query = "SELECT * FROM TB_VENDEDOR WHERE CPF = ?";
		
		VendedorDTO vendedor = new VendedorDTO();
		
		try {

			Connection myConnection = Conexao.getConexao();

			PreparedStatement pstm = myConnection.prepareStatement(query);

			pstm.setString(1, cpf);
			
			//executeUpdate() for table update
			ResultSet rs = pstm.executeQuery();
			
			while(rs.next()){				
				vendedor.setPontos(rs.getInt("PONTOS"));
				vendedor.setNome(rs.getString("NOME_VENDEDOR"));
				vendedor.setIdVendedor(rs.getInt("ID_VENDEDOR"));
			}

			Conexao.desconectar();

			return vendedor;

		} catch (SQLException e) {

			Conexao.desconectar();
			e.printStackTrace();
			System.out.println("Algo de errado na consulta de dados do vendedor!");
			
			return vendedor;
		}
	}
	
	public VendedorDTO getInfoVendedorId(int id) {
		String query = "SELECT * FROM TB_VENDEDOR WHERE ID_VENDEDOR = ?";
		
		VendedorDTO vendedor = new VendedorDTO();
		
		try {

			Connection myConnection = Conexao.getConexao();

			PreparedStatement pstm = myConnection.prepareStatement(query);

			pstm.setInt(1, id);
			
			//executeUpdate() for table update
			ResultSet rs = pstm.executeQuery();
			
			while(rs.next()){				
				vendedor.setPontos(rs.getInt("PONTOS"));
				vendedor.setNome(rs.getString("NOME_VENDEDOR"));
				vendedor.setIdVendedor(rs.getInt("ID_VENDEDOR"));
			}

			Conexao.desconectar();

			return vendedor;

		} catch (SQLException e) {

			Conexao.desconectar();
			e.printStackTrace();
			System.out.println("Algo de errado na consulta de dados do vendedor!");
			
			return vendedor;
		}
	}
	
	public boolean cadastroVenda(VendasDTO venda) {
		String query = "INSERT INTO TB_VENDAS("
				+ "ID_VENDA, ID_PRODUTO, ID_VENDEDOR, ID_EMPRESA, NOME_PRODUTO, DATA_VENDA, APROVADA) "
				+ "VALUES(?, ?, ?, ?, ?, ?, ?)";

		try {

			Connection myConnection = Conexao.getConexao();

			PreparedStatement pstm = myConnection.prepareStatement(query);

			/*TO DO - adicionar data de cadastro para empresa e vendedor */

			//setting values for insert in pessoa table
			pstm.setString(1, venda.getIdVenda());
			pstm.setInt(2, venda.getIdProduto());
			pstm.setInt(3, venda.getIdVendedor());
			pstm.setInt(4, venda.getIdEmpresa());
			pstm.setString(5, venda.getNomeProduto());
			pstm.setString(6, venda.getData());
			pstm.setString(7, "F");


			//executeUpdate() for table update
			pstm.executeQuery();

			System.out.println(venda.toString());

			Conexao.desconectar();

			return true;

		} catch (SQLException e) {

			Conexao.desconectar();
			e.printStackTrace();
			System.out.println("Algo de errado no cadastro da nota!");
			return false;
		}
	}
	
	public boolean vendaAprovada(int idVendedor, int idProduto) {
		String query = "UPDATE TB_VENDEDOR SET PONTOS = ? WHERE ID_VENDEDOR = ?";

		try {
			
			VendedorDTO vendedor = getInfoVendedorId(idVendedor);
			EmpresaDAO empresaDAO = new EmpresaDAO();
			
			int pontos = empresaDAO.getPontosProduto(idProduto);
			
			Connection myConnection = Conexao.getConexao();

			PreparedStatement pstm = myConnection.prepareStatement(query);

			/*TO DO - adicionar data de cadastro para empresa e vendedor */

			//setting values for insert in pessoa table
			pstm.setInt(1, vendedor.getPontos() + pontos);
			pstm.setInt(2, idVendedor);
			
			//executeUpdate() for table update
			pstm.executeQuery();

			Conexao.desconectar();

			return true;

		} catch (SQLException e) {

			Conexao.desconectar();
			e.printStackTrace();
			System.out.println("Algo de errado ao creditar pontos ao vendedor!");
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
				produto.setInicioCampanha(rs.getString("INICIO_CAMPANHA"));
				produto.setVigenciaCampanha(rs.getString("VIGENCIA_CAMPANHA"));				
				
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
			Conexao.desconectar();

			return produtos;

		} catch (SQLException e) {

			Conexao.desconectar();
			e.printStackTrace();
			System.out.println("Algo de errado na busca de bonus!");
			return produtos;
		}
	}
	
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
			
			Conexao.desconectar();

			return vendas;

		} catch (SQLException e) {

			Conexao.desconectar();
			e.printStackTrace();
			System.out.println("Algo de errado no cadastro do Vendedor!");
			return null;
		}
	}
	
	public boolean resgatarBonus(int pontos, String cpf) {
		String query = "UPDATE TB_VENDEDOR SET PONTOS = ? WHERE CPF = ?";

		try {
			
			VendedorDTO vendedor = getInfoVendedor(cpf);
			
			Connection myConnection = Conexao.getConexao();

			PreparedStatement pstm = myConnection.prepareStatement(query);

			/*TO DO - adicionar data de cadastro para empresa e vendedor */

			//setting values for insert in pessoa table
			pstm.setInt(1, vendedor.getPontos() - pontos);
			pstm.setString(2, cpf);
			
			//executeUpdate() for table update
			pstm.executeQuery();

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
