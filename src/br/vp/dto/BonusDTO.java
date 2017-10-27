package br.vp.dto;

public class BonusDTO {
	
	public int idBonus, pontosNecessarios;
	public String descricao, nomeBonus;

	public BonusDTO() {
		
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
