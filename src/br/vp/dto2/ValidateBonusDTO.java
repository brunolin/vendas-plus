package br.vp.dto2;

import br.vp.model.ValidateBonus;

public class ValidateBonusDTO {
	
	public ValidateBonus validateBonus;

	public ValidateBonusDTO() {
		this.validateBonus = new ValidateBonus();
	}
	
	public ValidateBonusDTO(ValidateBonus validateBonus) {
		this.validateBonus = validateBonus;
	}

	public ValidateBonus getValidateBonus() {
		return validateBonus;
	}

	public void setValidateBonus(ValidateBonus validateBonus) {
		this.validateBonus = validateBonus;
	}
	
}
