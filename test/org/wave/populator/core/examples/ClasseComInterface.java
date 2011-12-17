package org.wave.populator.core.examples;

import java.io.Serializable;

public class ClasseComInterface implements Serializable {

	private static final long serialVersionUID = 1L;

	private Interface interfaze;

	public Interface getInterfaze() {
		return interfaze;
	}

	public void setInterfaze(Interface interfaze) {
		this.interfaze = interfaze;
	}

}
