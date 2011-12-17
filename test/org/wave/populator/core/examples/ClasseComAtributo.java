package org.wave.populator.core.examples;

import java.io.Serializable;

public class ClasseComAtributo implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;

	private ClasseComAtributosFixos classeComAtributosFixos;

	public Long getId() {
		return id;
	}

	public ClasseComAtributosFixos getClasseComAtributosFixos() {
		return classeComAtributosFixos;
	}

	public void setClasseComAtributosFixos(ClasseComAtributosFixos classeComAtributosFixos) {
		this.classeComAtributosFixos = classeComAtributosFixos;
	}

}
