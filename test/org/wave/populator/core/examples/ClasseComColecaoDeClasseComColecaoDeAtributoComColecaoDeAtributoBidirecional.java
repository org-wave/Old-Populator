package org.wave.populator.core.examples;

import java.io.Serializable;
import java.util.Collection;

public class ClasseComColecaoDeClasseComColecaoDeAtributoComColecaoDeAtributoBidirecional implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Collection<ClasseComColecaoDeAtributoComColecaoDeAtributoBidirecional> colecao;

	public Collection<ClasseComColecaoDeAtributoComColecaoDeAtributoBidirecional> getColecao() {
		return colecao;
	}

	public void setColecao(Collection<ClasseComColecaoDeAtributoComColecaoDeAtributoBidirecional> colecao) {
		this.colecao = colecao;
	}

}
