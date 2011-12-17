package org.wave.populator.core;

import javax.inject.Inject;

import org.wave.populator.enums.ErrorEnum;
import org.wave.populator.exceptions.PopulatorException;


/**
 * Delega a responsabilidade de povoar instancias, alem da responsabilidade de armazena-las em um repositorio.
 * 
 * @author Benedito Barbosa
 * @author Christian Peixoto
 * 
 * @see org.wave.populator.core.Filler
 * @see org.wave.populator.core.Docker
 * 
 */
public class Populator {

	@Inject
	private Filler filler;

	@Inject
	private Docker docker;

	private PatternManager manager;

	public Populator() {
		this.manager = PatternManager.getInstance();
	}

	/**
	 * Cria e povoa uma instancia de uma determinada classe. Em seguida, essa instancia e armazenada em um repositorio.
	 * 
	 * @param klass
	 * @return Instancia de uma determinada classe.
	 * @throws PopulatorException
	 *             quando a classe for nula.
	 */
	public <T> T populate(Class<T> klass) throws PopulatorException {
		if (klass == null) {
			throw new PopulatorException(ErrorEnum.NULL);
		}

		try {
			T instance = klass.newInstance();
			this.populate(instance);

			return instance;
		} catch (InstantiationException e) {
			throw new PopulatorException(e.getMessage());
		} catch (IllegalAccessException e) {
			throw new PopulatorException(e.getMessage());
		}
	}

	/**
	 * Povoa uma determinada instancia. Em seguida, essa instancia e armazenada em um repositorio.
	 * 
	 * @param instance
	 * @throws PopulatorException
	 *             quando a instancia for nula ou nao for serializavel.
	 */
	public <T> void populate(T instance) throws PopulatorException {
		this.filler.fill(instance);

		this.docker.persistInstances();

		this.manager.restore();
	}

	/**
	 * Retira as instancias armazenadas em um repositorio.
	 * 
	 * @throws PopulatorException
	 */
	public void clear() throws PopulatorException {
		this.docker.removeInstances();
	}

}
