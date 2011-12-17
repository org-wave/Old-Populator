package org.wave.populator.core;

import org.jboss.weld.environment.se.Weld;
import org.jboss.weld.environment.se.WeldContainer;

/**
 * Classe que implementa o padrao de projeto Factory Method para criacao de objetos da classe Populator.
 * 
 * @author Benedito Barbosa
 * @author Christian Peixoto
 * 
 * @see org.wave.populator.core.Populator
 * 
 */
public class PopulatorFactory {

	private PopulatorFactory() {

	}

	/**
	 * Metodo de fabrica que encapsula a criacao de um Populator.
	 * 
	 * @return Populator
	 */
	public static Populator createPopulator() {
		WeldContainer container = new Weld().initialize();

		return container.instance().select(Populator.class).get();
	}

}
