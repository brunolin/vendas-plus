package br.vp.dto;

import br.vp.model.ValidateBonus;

public class ValidateBonusDTO {
	
	public int pontos;
	public String cpf;
	
	public ValidateBonusDTO() {
		
	}

	public int getPontos() {
		return pontos;
	}

	public void setPontos(int pontos) {
		this.pontos = pontos;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
}
