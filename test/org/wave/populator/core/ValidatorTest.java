package org.wave.populator.core;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.wave.populator.core.Validator;
import org.wave.populator.core.examples.ClasseComAtributosFixos;
import org.wave.populator.core.examples.ClasseNaoSerializavel;
import org.wave.populator.core.examples.ClasseSemAtributos;
import org.wave.populator.enums.ErrorEnum;
import org.wave.populator.exceptions.PopulatorException;


public class ValidatorTest {

	private Validator validator;

	@Before
	public void setUp() {
		this.validator = new Validator();
	}

	@Test(expected = PopulatorException.class)
	public void deveLancarExcecaoQuandoAInstanciaForNulaException() throws PopulatorException {
		this.validator.validate(null);
	}

	@Test
	public void deveLancarExcecaoQuandoAInstanciaForNula() {
		try {
			this.validator.validate(null);
		} catch (PopulatorException e) {
			assertEquals(ErrorEnum.NULL.getMessage(), e.getMessage());
		}
	}

	@Test
	public void naoDeveLancarExcecaoQuandoAInstanciaNaoForNula() {
		ClasseComAtributosFixos instance = new ClasseComAtributosFixos();

		try {
			this.validator.validate(instance);
		} catch (PopulatorException e) {
			fail(e.getMessage());
		}
	}

	@Test(expected = PopulatorException.class)
	public void deveLancarExcecaoQuandoAInstanciaNaoForSerializavelException() throws PopulatorException {
		ClasseNaoSerializavel instance = new ClasseNaoSerializavel();

		this.validator.validate(instance);
	}

	@Test
	public void deveLancarExcecaoSeAInstanciaNaoForSerializavel() {
		ClasseNaoSerializavel instance = new ClasseNaoSerializavel();

		try {
			this.validator.validate(instance);
		} catch (PopulatorException e) {
			assertEquals(ErrorEnum.NOT_SERIALIZABLE.getMessage(instance.getClass().getName()), e.getMessage());
		}
	}

	@Test
	public void naoDeveLancarExcecaoQuandoAInstanciaForSerializavel() {
		ClasseComAtributosFixos instance = new ClasseComAtributosFixos();

		try {
			this.validator.validate(instance);
		} catch (PopulatorException e) {
			fail(e.getMessage());
		}
	}

	@Test(expected = PopulatorException.class)
	public void deveLancarExcecaoQuandoAInstanciaNaoTiverAtributosPersistentesException() throws PopulatorException {
		ClasseSemAtributos instance = new ClasseSemAtributos();

		this.validator.validate(instance);
	}

	@Test
	public void deveLancarExcecaoQuandoAInstanciaNaoTiverAtributosPersistentes() {
		ClasseSemAtributos instance = new ClasseSemAtributos();

		try {
			this.validator.validate(instance);
		} catch (PopulatorException e) {
			assertEquals(ErrorEnum.NOT_PERSISTENT_FIELDS.getMessage(instance.getClass().getName()), e.getMessage());
		}
	}

	@Test
	public void naoDeveLancarExcecaoQuandoAInstanciaTiverAtributosPersistentes() {
		ClasseComAtributosFixos instance = new ClasseComAtributosFixos();

		try {
			this.validator.validate(instance);
		} catch (PopulatorException e) {
			fail(e.getMessage());
		}
	}

	@After
	public void tearDown() {
		this.validator = null;
	}

}
