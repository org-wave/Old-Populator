package org.wave.populator.setters;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.wave.populator.core.Filler;
import org.wave.populator.core.examples.ClasseComAtributosFixos;
import org.wave.populator.core.examples.ClasseComColecao;
import org.wave.populator.core.examples.ClasseComColecaoDeClasseComAtributosFixos;
import org.wave.populator.enums.FixedPatternEnum;
import org.wave.populator.exceptions.PopulatorException;
import org.wave.populator.setters.CollectionSetter;
import org.wave.populator.setters.Setter;


public class CollectionSetterTest {

	private Setter successor;

	private Setter setter;

	@Before
	public void setUp() {
		this.successor = mock(Setter.class);

		Filler filler = mock(Filler.class);
		this.setter = new CollectionSetter(filler);
		this.setter.setSuccessor(this.successor);
	}

	@Test
	public void devePreencherUmaInstanciaQuandoAColecaoForNula() throws PopulatorException {
		ClasseComColecao instance = new ClasseComColecao();

		doNothing().when(this.successor).set(instance);
		this.setter.set(instance);

		Collection<String> elementos = instance.getColecao();
		assertFalse(elementos.isEmpty());
		assertEquals(1, elementos.size());

		for (String elemento : elementos) {
			assertEquals(FixedPatternEnum.STRING.getValue(), elemento);
		}
	}

	@Test
	public void devePreencherUmaInstanciaQuandoAColecaoForVazia() throws PopulatorException {
		ClasseComColecaoDeClasseComAtributosFixos instance = new ClasseComColecaoDeClasseComAtributosFixos();
		instance.setColecao(new ArrayList<ClasseComAtributosFixos>());

		doNothing().when(this.successor).set(instance);
		this.setter.set(instance);

		Collection<ClasseComAtributosFixos> elementos = instance.getColecao();
		assertFalse(elementos.isEmpty());
		assertEquals(1, elementos.size());

		for (ClasseComAtributosFixos elemento : elementos) {
			assertNotNull(elemento);
		}
	}

	@Test
	public void devePreencherUmaInstanciaQuandoOElementoDaColecaoDePadraoFixoForNulo() throws PopulatorException {
		List<String> colecao = new ArrayList<String>();
		colecao.add(null);

		ClasseComColecao instance = new ClasseComColecao();
		instance.setColecao(colecao);

		doNothing().when(this.successor).set(instance);
		this.setter.set(instance);

		Collection<String> elementos = instance.getColecao();
		assertEquals(1, elementos.size());

		for (String elemento : elementos) {
			assertEquals(FixedPatternEnum.STRING.getValue(), elemento);
		}
	}

	@Test
	public void devePreencherUmaInstanciaQuandoOElementoDaColecaoForNulo() throws PopulatorException {
		List<ClasseComAtributosFixos> colecao = new ArrayList<ClasseComAtributosFixos>();
		colecao.add(null);

		ClasseComColecaoDeClasseComAtributosFixos instance = new ClasseComColecaoDeClasseComAtributosFixos();
		instance.setColecao(colecao);

		doNothing().when(this.successor).set(instance);
		this.setter.set(instance);

		Collection<ClasseComAtributosFixos> elementos = instance.getColecao();
		assertEquals(1, elementos.size());

		for (ClasseComAtributosFixos elemento : elementos) {
			assertNotNull(elemento);

			verify(this.setter.getFiller()).fill(elemento);
		}
	}

	@Test
	public void devePreencherUmaInstanciaQuandoOElementoDaColecaoNaoForNulo() throws PopulatorException {
		List<ClasseComAtributosFixos> colecao = new ArrayList<ClasseComAtributosFixos>();
		colecao.add(new ClasseComAtributosFixos());

		ClasseComColecaoDeClasseComAtributosFixos instance = new ClasseComColecaoDeClasseComAtributosFixos();
		instance.setColecao(colecao);

		doNothing().when(this.successor).set(instance);
		this.setter.set(instance);

		Collection<ClasseComAtributosFixos> elementos = instance.getColecao();
		assertEquals(1, elementos.size());

		for (ClasseComAtributosFixos elemento : elementos) {
			assertNotNull(elemento);

			verify(this.setter.getFiller()).fill(elemento);
		}
	}

	@After
	public void tearDown() {
		this.setter.getManager().restore();

		this.setter = null;
		this.successor = null;
	}

}
