package br.vp.dto2;

import br.vp.model.Bonus;

public class BonusDTO {
	
	public Bonus bonus;

	public BonusDTO() {
		this.bonus = new Bonus();
	}
	
	public BonusDTO(Bonus bonus) {
		this.bonus = bonus;
	}

	public Bonus getBonus() {
		return bonus;
	}

	public void setBonus(Bonus bonus) {
		this.bonus = bonus;
	}
	
	
}
