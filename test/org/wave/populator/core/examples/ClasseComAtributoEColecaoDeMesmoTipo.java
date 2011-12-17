package org.wave.populator.core.examples;

import java.io.Serializable;
import java.util.Collection;

public class ClasseComAtributoEColecaoDeMesmoTipo implements Serializable {

	private static final long serialVersionUID = 1L;

	private ClasseComAtributo classeComAtributo;

	private Collection<ClasseComAtributo> colecao;

	public ClasseComAtributo getClasseComAtributo() {
		return classeComAtributo;
	}

	public Collection<ClasseComAtributo> getColecao() {
		return colecao;
	}

}
