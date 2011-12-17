package org.wave.populator.setters;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.wave.populator.core.Filler;
import org.wave.populator.core.examples.ClasseComAtributo;
import org.wave.populator.core.examples.ClasseComAtributosFixos;
import org.wave.populator.core.examples.ClasseComInterface;
import org.wave.populator.core.examples.ClasseComMapa;
import org.wave.populator.enums.ErrorEnum;
import org.wave.populator.exceptions.PopulatorException;
import org.wave.populator.setters.OtherSetter;
import org.wave.populator.setters.Setter;


public class OtherSetterTest {

	private Setter successor;

	private Setter setter;

	@Before
	public void setUp() {
		this.successor = mock(Setter.class);

		Filler filler = mock(Filler.class);
		this.setter = new OtherSetter(filler);
		this.setter.setSuccessor(this.successor);
	}

	@Test(expected = PopulatorException.class)
	public void deveLancarExcecaoQuandoOAtributoForUmaInterfaceException() throws PopulatorException {
		ClasseComInterface instance = new ClasseComInterface();

		doNothing().when(this.successor).set(instance);
		this.setter.set(instance);
	}

	@Test
	public void deveLancarExcecaoQuandoOAtributoForUmaInterface() {
		ClasseComInterface instance = new ClasseComInterface();

		try {
			doNothing().when(this.successor).set(instance);
			this.setter.set(instance);
		} catch (PopulatorException e) {
			assertEquals(ErrorEnum.UNEXPECTED_TYPE.getMessage("interfaze", instance.getClass().getName()), e.getMessage());
		}
	}

	@Test(expected = PopulatorException.class)
	public void deveLancarExcecaoQuandoOAtributoForDeClasseDesconhecidaException() throws PopulatorException {
		ClasseComMapa instance = new ClasseComMapa();

		doNothing().when(this.successor).set(instance);
		this.setter.set(instance);
	}

	@Test
	public void deveLancarExcecaoQuandoOAtributoForDeClasseDesconhecida() {
		ClasseComMapa instance = new ClasseComMapa();

		try {
			doNothing().when(this.successor).set(instance);
			this.setter.set(instance);
		} catch (PopulatorException e) {
			assertEquals(ErrorEnum.UNEXPECTED_TYPE.getMessage("mapa", instance.getClass().getName()), e.getMessage());
		}
	}

	@Test
	public void devePreencherUmaInstanciaQuandoOAtributoForNulo() throws PopulatorException {
		ClasseComAtributo instance = new ClasseComAtributo();

		doNothing().when(this.successor).set(instance);
		this.setter.set(instance);

		assertNotNull(instance.getClasseComAtributosFixos());

		verify(this.setter.getFiller()).fill(instance.getClasseComAtributosFixos());
	}

	@Test
	public void devePreencherUmaInstanciaQuandoOAtributoNaoForNulo() throws PopulatorException {
		ClasseComAtributo instance = new ClasseComAtributo();
		instance.setClasseComAtributosFixos(new ClasseComAtributosFixos());

		doNothing().when(this.successor).set(instance);
		this.setter.set(instance);

		verify(this.setter.getFiller()).fill(instance.getClasseComAtributosFixos());
	}

	@After
	public void tearDown() {
		this.setter.getManager().restore();

		this.setter = null;
		this.successor = null;
	}

}
