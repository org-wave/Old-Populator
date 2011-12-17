package org.wave.populator.core;
//package br.com.wave.populator.core;
//
//import static org.junit.Assert.assertEquals;
//
//import java.util.List;
//
//import org.junit.After;
//import org.junit.Before;
//import org.junit.Test;
//import org.mockito.Mockito;
//
//import br.com.brasilti.repository.core.Keeper;
//import br.com.wave.populator.core.examples.ClasseComAtributo;
//import br.com.wave.populator.core.examples.ClasseComAtributosFixos;
//import br.com.wave.populator.core.examples.ClasseComColecaoDeElementos;
//import br.com.wave.populator.core.examples.EnumNaoVazia;
//import br.com.wave.populator.exceptions.PopulatorException;
//
//public class DockerTest {
//
//	private Docker docker;
//
//	private PatternManager manager;
//
//	private ClasseComColecaoDeElementos colecao;
//
//	private ClasseComAtributo objetoComAtributoNaoPadrao;
//
//	private ClasseComAtributosFixos objetoComAtributosPadrao;
//
//	@Before
//	public void setUp() {
//		this.manager = PatternManager.getInstance();
//		this.manager.restore();
//
//		this.colecao = new ClasseComColecaoDeElementos();
//		this.objetoComAtributoNaoPadrao = new ClasseComAtributo();
//		this.objetoComAtributosPadrao = new ClasseComAtributosFixos();
//
//		this.manager.addPublicPattern(ClasseComColecaoDeElementos.class, this.colecao);
//		this.manager.addPublicPattern(ClasseComAtributo.class, this.objetoComAtributoNaoPadrao);
//		this.manager.addPublicPattern(ClasseComAtributosFixos.class, this.objetoComAtributosPadrao);
//		this.manager.addPublicPattern(EnumNaoVazia.class, EnumNaoVazia.CONSTANTE_01);
//
//		Keeper keeper = Mockito.mock(Keeper.class);
//		this.docker = new Docker(keeper);
//	}
//
//	@Test
//	public void deveArmazenarAsInstanciasNaOrdemInversaEmQueForamAdicionadasComoPadrao() throws PopulatorException {
//		this.docker.addInstances();
//
//		List<Object> instances = this.docker.getInstances();
//
//		assertEquals(3, instances.size());
//		assertEquals(this.objetoComAtributosPadrao, instances.get(0));
//		assertEquals(this.objetoComAtributoNaoPadrao, instances.get(1));
//		assertEquals(this.colecao, instances.get(2));
//	}
//
//	@Test
//	public void deveRetirarAsInstanciasNaMesmaOrdemEmQueForamAdicionadasComoPadrao() throws PopulatorException {
//		this.docker.removeInstances();
//
//		List<Object> instances = this.docker.getInstances();
//
//		assertEquals(3, instances.size());
//		assertEquals(this.colecao, instances.get(0));
//		assertEquals(this.objetoComAtributoNaoPadrao, instances.get(1));
//		assertEquals(this.objetoComAtributosPadrao, instances.get(2));
//	}
//
//	@After
//	public void tearDown() {
//		this.docker = null;
//	}
//
//}
