package org.wave.populator.core.examples;

import java.io.Serializable;
import java.util.Collection;

public class ClasseComColecaoDeAtributoComColecaoDeAtributoBidirecional implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Collection<ClasseComColecaoDeClasseComColecaoDeAtributoComColecaoDeAtributoBidirecional> colecao;

	public Collection<ClasseComColecaoDeClasseComColecaoDeAtributoComColecaoDeAtributoBidirecional> getColecao() {
		return colecao;
	}

	public void setColecao(Collection<ClasseComColecaoDeClasseComColecaoDeAtributoComColecaoDeAtributoBidirecional> colecao) {
		this.colecao = colecao;
	}

}
