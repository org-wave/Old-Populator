package org.wave.populator.core.examples;

import java.io.Serializable;
import java.util.Collection;

public class ClasseComColecaoDeClasseComAtributosFixos implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;

	private Collection<ClasseComAtributosFixos> colecao;

	public Long getId() {
		return id;
	}

	public Collection<ClasseComAtributosFixos> getColecao() {
		return colecao;
	}

	public void setColecao(Collection<ClasseComAtributosFixos> colecao) {
		this.colecao = colecao;
	}

}
