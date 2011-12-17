package org.wave.populator.core.examples;

import java.io.Serializable;

public class ClasseComAtributoDeClasseComColecaoDeAtributoBidirecional implements Serializable {

	private static final long serialVersionUID = 1L;

	private ClasseComColecaoDeAtributoBidirecional classeComColecaoDeAtributoBidirecional;

	public ClasseComColecaoDeAtributoBidirecional getClasseComColecaoDeAtributoBidirecional() {
		return classeComColecaoDeAtributoBidirecional;
	}

	public void setClasseComColecaoDeAtributoBidirecional(ClasseComColecaoDeAtributoBidirecional classeComColecaoDeAtributoBidirecional) {
		this.classeComColecaoDeAtributoBidirecional = classeComColecaoDeAtributoBidirecional;
	}

}
