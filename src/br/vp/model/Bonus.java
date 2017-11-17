package br.vp.model;

import javax.persistence.Entity;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity()
@Table(name="TB_BONUS", schema="vendasplus")
public class Bonus {
	
    @Id()
    @Column(name="ID_BONUS")
	public int idBonus;
    
    @Column(name="PONTOS_NECESSARIOS")
	public int pontosNecessarios;
    
    @Column(name="DESCRICAO")
	public String descricao;
    
    @Column(name="NOME_BONUS")
	public String nomeBonus;

	public Bonus() {
		
	}

	public String getNome() {
		return nomeBonus;
	}

	public void setNome(String nomeBonus) {
		this.nomeBonus = nomeBonus;
	}

	public int getIdBonus() {
		return idBonus;
	}

	public void setIdBonus(int idBonus) {
		this.idBonus = idBonus;
	}

	public int getPontosNecessarios() {
		return pontosNecessarios;
	}

	public void setPontosNecessarios(int pontosNecessarios) {
		this.pontosNecessarios = pontosNecessarios;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}	
	
}
