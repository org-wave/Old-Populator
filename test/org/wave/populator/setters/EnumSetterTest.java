package org.wave.populator.setters;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.wave.populator.core.Filler;
import org.wave.populator.core.examples.ClasseComEnumNaoVazia;
import org.wave.populator.core.examples.ClasseComEnumVazia;
import org.wave.populator.core.examples.EnumNaoVazia;
import org.wave.populator.core.examples.EnumVazia;
import org.wave.populator.enums.ErrorEnum;
import org.wave.populator.exceptions.PopulatorException;
import org.wave.populator.setters.EnumSetter;
import org.wave.populator.setters.Setter;


public class EnumSetterTest {

	private Setter successor;

	private Setter setter;

	@Before
	public void setUp() {
		this.successor = mock(Setter.class);

		Filler filler = mock(Filler.class);
		this.setter = new EnumSetter(filler);
		this.setter.setSuccessor(this.successor);
	}

	@Test(expected = PopulatorException.class)
	public void deveLancarExcecaoQuandoAEnumForVaziaException() throws PopulatorException {
		ClasseComEnumVazia instance = new ClasseComEnumVazia();

		this.setter.set(instance);
	}

	@Test
	public void deveLancarExcecaoQuandoAEnumForVazia() {
		ClasseComEnumVazia instance = new ClasseComEnumVazia();

		try {
			this.setter.set(instance);
		} catch (PopulatorException e) {
			assertEquals(ErrorEnum.EMPTY_ENUM.getMessage(EnumVazia.class.getName()), e.getMessage());
		}
	}

	@Test
	public void devePreencherUmaInstanciaQuandoAEnumForNula() throws PopulatorException {
		ClasseComEnumNaoVazia instance = new ClasseComEnumNaoVazia();

		doNothing().when(this.successor).set(instance);
		this.setter.set(instance);

		assertEquals(EnumNaoVazia.CONSTANTE_01, instance.getEnumNaoVazia());
	}

	@After
	public void tearDown() {
		this.setter = null;
		this.successor = null;
	}

}
