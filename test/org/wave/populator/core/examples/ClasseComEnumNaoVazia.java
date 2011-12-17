package org.wave.populator.core.examples;

import java.io.Serializable;

public class ClasseComEnumNaoVazia implements Serializable {

	private static final long serialVersionUID = 1L;

	private EnumNaoVazia enumNaoVazia;

	public EnumNaoVazia getEnumNaoVazia() {
		return enumNaoVazia;
	}

	public void setEnumNaoVazia(EnumNaoVazia enumNaoVazia) {
		this.enumNaoVazia = enumNaoVazia;
	}

}
