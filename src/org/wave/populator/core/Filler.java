package org.wave.populator.core;

import javax.inject.Inject;

import org.wave.populator.exceptions.PopulatorException;
import org.wave.populator.setters.CollectionSetter;
import org.wave.populator.setters.EnumSetter;
import org.wave.populator.setters.FixedSetter;
import org.wave.populator.setters.OtherSetter;
import org.wave.populator.setters.Setter;
import org.wave.utils.reflection.ReflectionUtil;


/**
 * Classe que implementa o padrao de projeto Chain of Responsibility para povoar uma determinada instancia.
 * 
 * @author Benedito Barbosa
 * @author Christian Peixoto
 * 
 * @see FixedSetter
 * @see FixedSetter
 * @see EnumSetter
 * @see CollectionSetter
 * @see OtherSetter
 * 
 */
public class Filler {

	@Inject
	private Validator validator;

	private PatternManager manager;

	private Setter enumSetter;

	private Setter collectionSetter;

	private Setter otherSetter;

	private Setter patternSetter;

	public Filler() {
		this.manager = PatternManager.getInstance();

		this.enumSetter = new EnumSetter(this);
		this.collectionSetter = new CollectionSetter(this);
		this.otherSetter = new OtherSetter(this);
		this.patternSetter = new FixedSetter(this);

		this.enumSetter.setSuccessor(this.collectionSetter);
		this.collectionSetter.setSuccessor(this.otherSetter);
		this.otherSetter.setSuccessor(this.patternSetter);
	}

	/**
	 * Preenche os atributos de uma determinada instancia.
	 * 
	 * @param instance
	 * @throws PopulatorException
	 */
	public <T> void fill(T instance) throws PopulatorException {
		this.validator.validate(instance);

		Class<?> klass = instance.getClass();
		if (this.manager.hasPattern(klass)) {
			Object value = this.manager.getValue(klass);

			ReflectionUtil.copy(value, instance);
		} else {
			this.manager.addPattern(instance);

			this.enumSetter.set(instance);
		}

	}

}
