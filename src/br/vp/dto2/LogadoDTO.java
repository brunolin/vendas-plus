package br.vp.dto2;

import br.vp.model.Logado;

public class LogadoDTO {
	
	public Logado logado;

	public LogadoDTO() {
		this.logado = new Logado();
	}
	
	public LogadoDTO(Logado logado) {
		this.logado = logado;
	}

	public Logado getLogado() {
		return logado;
	}

	public void setLogado(Logado logado) {
		this.logado = logado;
	}
	
	
}
