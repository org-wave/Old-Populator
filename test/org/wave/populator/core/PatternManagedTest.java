package org.wave.populator.core;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.wave.populator.core.PatternManager;
import org.wave.populator.core.examples.ClasseComAtributosFixos;
import org.wave.populator.enums.FixedPatternEnum;


public class PatternManagedTest {

	private PatternManager manager;

	@Before
	public void setUp() {
		this.manager = PatternManager.getInstance();
	}

	@Test
	public void deveAdicionarUmaInstanciaComoPadrao() {
		ClasseComAtributosFixos instance = new ClasseComAtributosFixos();

		this.manager.addPattern(instance);

		assertTrue(this.manager.hasPattern(ClasseComAtributosFixos.class));
		assertTrue(this.manager.getValues().contains(instance));
	}

	@Test
	public void deveRetornarVerdadeiroQuandoAClasseTiverPadrao() {
		assertTrue(this.manager.hasPattern(String.class));
	}

	@Test
	public void deveRetornarFalsoQuandoAClasseNaoTiverPadrao() {
		assertFalse(this.manager.hasPattern(ClasseComAtributosFixos.class));
	}

	@Test
	public void deveRetornarOValorDoPadraoFixo() {
		assertEquals(FixedPatternEnum.STRING.getValue(), this.manager.getValue(String.class));
	}

	@Test
	public void deveRetornarOValorDoPadraoAdicionado() {
		ClasseComAtributosFixos instance = new ClasseComAtributosFixos();

		this.manager.addPattern(instance);

		assertEquals(instance, this.manager.getValue(ClasseComAtributosFixos.class));
	}

	@Test
	public void deveRetornarNuloQuandoNaoHouverPadrao() {
		assertNull(this.manager.getValue(ClasseComAtributosFixos.class));
	}

	@Test
	public void deveRestaurarOsPadroes() {
		ClasseComAtributosFixos instance = new ClasseComAtributosFixos();

		this.manager.addPattern(instance);

		this.manager.restore();

		assertFalse(this.manager.hasPattern(ClasseComAtributosFixos.class));
		assertFalse(this.manager.getValues().contains(instance));
	}

	@After
	public void tearDown() {
		this.manager.restore();
	}

}
