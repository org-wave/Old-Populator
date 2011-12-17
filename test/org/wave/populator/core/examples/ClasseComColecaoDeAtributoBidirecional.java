package org.wave.populator.core.examples;

import java.io.Serializable;
import java.util.Collection;

public class ClasseComColecaoDeAtributoBidirecional implements Serializable {

	private static final long serialVersionUID = 1L;

	private Collection<ClasseComAtributoDeClasseComColecaoDeAtributoBidirecional> colecao;

	public Collection<ClasseComAtributoDeClasseComColecaoDeAtributoBidirecional> getColecao() {
		return colecao;
	}

	public void setColecao(Collection<ClasseComAtributoDeClasseComColecaoDeAtributoBidirecional> colecao) {
		this.colecao = colecao;
	}

}
