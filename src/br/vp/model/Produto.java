package br.vp.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity()
@Table(name="TB_PRODUTO", schema="vendasplus")
public class Produto {
	
	    @Id()
	    @Column(name="ID_PRODUTO")
		@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PRODUTO_SEQ")
		@SequenceGenerator(name = "PRODUTO_SEQ", sequenceName = "PRODUTO_SEQ", allocationSize = 1)
		public int idProduto;
	    
	    @Column(name="ID_EMPRESA")
	    public int idEmpresa;
	    
	    @Column(name="PONTOS_RECOMPENSA")
	    public int pontosRecompensa;
		
	    @Column(name="NOME_PRODUTO")
		public String nomeProduto;
	    
	    @Column(name="IMG")
		public String img;
	    
	    @Column(name="INICIO_CAMPANHA")
		public String inicioCampanha;
	    
	    @Column(name="VIGENCIA_CAMPANHA")
		public String vigenciaCampanha;
		
	public Produto() {
		
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	public int getIdProduto() {
		return idProduto;
	}

	public void setIdProduto(int idProduto) {
		this.idProduto = idProduto;
	}

	public int getIdEmpresa() {
		return idEmpresa;
	}

	public void setIdEmpresa(int idEmpresa) {
		this.idEmpresa = idEmpresa;
	}

	public int getPontosRecompensa() {
		return pontosRecompensa;
	}

	public void setPontosRecompensa(int pontosRecompensa) {
		this.pontosRecompensa = pontosRecompensa;
	}

	public String getNomeProduto() {
		return nomeProduto;
	}

	public void setNomeProduto(String nomeProduto) {
		this.nomeProduto = nomeProduto;
	}

	public String getInicioCampanha() {
		return inicioCampanha;
	}

	public void setInicioCampanha(String inicioCampanha) {
		this.inicioCampanha = inicioCampanha;
	}

	public String getVigenciaCampanha() {
		return vigenciaCampanha;
	}

	public void setVigenciaCampanha(String vigenciaCampanha) {
		this.vigenciaCampanha = vigenciaCampanha;
	}
	
	
}
