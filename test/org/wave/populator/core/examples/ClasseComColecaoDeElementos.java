package org.wave.populator.core.examples;

import java.io.Serializable;
import java.util.Collection;

public class ClasseComColecaoDeElementos implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private Long id;

	private Collection<ClasseComAtributo> colecao;

	public Long getId() {
		return id;
	}

	public Collection<ClasseComAtributo> getColecao() {
		return colecao;
	}

	public void setColecao(Collection<ClasseComAtributo> colecao) {
		this.colecao = colecao;
	}

}
