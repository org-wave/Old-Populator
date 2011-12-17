package org.wave.populator.core.examples;

import java.io.Serializable;
import java.util.Collection;

public class ClasseComAtributosDeTiposDiferentes implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;

	private Integer version;

	private String atributo;

	private EnumNaoVazia enumNaoVazia;

	private ClasseComAtributosFixos classeComAtributosFixos;

	private ClasseComAtributo classeComAtributo;

	private Collection<String> colecaoDeStrings;

	private Collection<ClasseComAtributo> colecao;

	public Long getId() {
		return id;
	}

	public Integer getVersion() {
		return version;
	}

	public String getAtributo() {
		return atributo;
	}

	public EnumNaoVazia getEnumNaoVazia() {
		return enumNaoVazia;
	}

	public ClasseComAtributosFixos getClasseComAtributosFixos() {
		return classeComAtributosFixos;
	}

	public ClasseComAtributo getClasseComAtributo() {
		return classeComAtributo;
	}

	public Collection<String> getColecaoDeStrings() {
		return colecaoDeStrings;
	}

	public Collection<ClasseComAtributo> getColecao() {
		return colecao;
	}

}
