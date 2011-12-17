package org.wave.populator.core;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.List;

import org.jboss.weld.environment.se.Weld;
import org.jboss.weld.environment.se.WeldContainer;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.wave.populator.core.Filler;
import org.wave.populator.core.PatternManager;
import org.wave.populator.core.examples.ClasseComAtributo;
import org.wave.populator.core.examples.ClasseComAtributoBidirecional;
import org.wave.populator.core.examples.ClasseComAtributoDeClasseComColecaoDeAtributoBidirecional;
import org.wave.populator.core.examples.ClasseComAtributoEColecaoDeMesmoTipo;
import org.wave.populator.core.examples.ClasseComAtributosDeTiposDiferentes;
import org.wave.populator.core.examples.ClasseComAtributosFixos;
import org.wave.populator.core.examples.ClasseComColecaoDeAtributoBidirecional;
import org.wave.populator.core.examples.ClasseComColecaoDeAtributoComColecaoDeAtributoBidirecional;
import org.wave.populator.core.examples.ClasseComColecaoDeClasseComAtributosFixos;
import org.wave.populator.core.examples.ClasseComColecaoDeClasseComColecaoDeAtributoComColecaoDeAtributoBidirecional;
import org.wave.populator.core.examples.ClasseComEnumNaoVazia;
import org.wave.populator.core.examples.EnumNaoVazia;
import org.wave.populator.enums.ErrorEnum;
import org.wave.populator.enums.FixedPatternEnum;
import org.wave.populator.exceptions.PopulatorException;


public class FillerTest {

	private Filler filler;

	@Before
	public void setUp() {
		WeldContainer container = new Weld().initialize();
		this.filler = container.instance().select(Filler.class).get();
	}

	@Test(expected = PopulatorException.class)
	public void deveLancarExcecaoQuandoAInstanciaForInvalidaException() throws PopulatorException {
		this.filler.fill(null);

	}

	@Test
	public void deveLancarExcecaoQuandoAInstanciaForInvalida() {
		try {
			this.filler.fill(null);
		} catch (PopulatorException e) {
			assertEquals(ErrorEnum.NULL.getMessage(), e.getMessage());
		}

	}

	@Test
	public void devePreencherUmaInstanciaCujaClasseSejaUmPadrao() throws PopulatorException {
		String valor = "Teste";

		ClasseComAtributosFixos pattern = new ClasseComAtributosFixos();
		pattern.setStringField(valor);

		PatternManager.getInstance().addPattern(pattern);

		ClasseComAtributosFixos instance = new ClasseComAtributosFixos();
		this.filler.fill(instance);

		assertEquals(valor, instance.getStringField());
	}

	@Test
	public void naoDevePreencherUmaInstanciaQuandoAEnumEstiverPreenchida() throws PopulatorException {
		ClasseComEnumNaoVazia instance = new ClasseComEnumNaoVazia();
		instance.setEnumNaoVazia(EnumNaoVazia.CONSTANTE_02);

		this.filler.fill(instance);

		assertEquals(EnumNaoVazia.CONSTANTE_02, instance.getEnumNaoVazia());
	}

	@Test
	public void devePreencherUmaInstanciaQuandoOElementoDaColecaoForNulo() throws PopulatorException {
		List<ClasseComAtributosFixos> colecao = new ArrayList<ClasseComAtributosFixos>();
		colecao.add(null);

		ClasseComColecaoDeClasseComAtributosFixos instance = new ClasseComColecaoDeClasseComAtributosFixos();
		instance.setColecao(colecao);

		this.filler.fill(instance);

		Collection<ClasseComAtributosFixos> elementos = instance.getColecao();
		assertEquals(1, elementos.size());

		for (ClasseComAtributosFixos elemento : elementos) {
			assertNotNull(elemento);

			assertEquals(FixedPatternEnum.STRING.getValue(), elemento.getStringField());
			assertEquals(FixedPatternEnum.INTEGER.getValue(), elemento.getIntegerField());
			assertEquals(FixedPatternEnum.LONG.getValue(), elemento.getLongField());
			assertEquals(FixedPatternEnum.BIG_DECIMAL.getValue(), elemento.getBigDecimalField());
			assertEquals(FixedPatternEnum.BOOLEAN.getValue(), elemento.getBooleanField());
			assertEquals(FixedPatternEnum.CALENDAR.getValue(), elemento.getCalendarField());
			assertEquals(FixedPatternEnum.BYTE_ARRAY.getValue(), elemento.getByteField());
		}
	}

	@Test
	public void devePreencherUmaInstanciaQuandoOElementoDaColecaoNaoEstiverPreenchido() throws PopulatorException {
		ClasseComAtributosFixos objeto = new ClasseComAtributosFixos();

		List<ClasseComAtributosFixos> colecao = new ArrayList<ClasseComAtributosFixos>();
		colecao.add(objeto);

		ClasseComColecaoDeClasseComAtributosFixos instance = new ClasseComColecaoDeClasseComAtributosFixos();
		instance.setColecao(colecao);

		this.filler.fill(instance);

		Collection<ClasseComAtributosFixos> elementos = instance.getColecao();
		assertEquals(1, elementos.size());

		for (ClasseComAtributosFixos elemento : elementos) {
			assertEquals(FixedPatternEnum.STRING.getValue(), elemento.getStringField());
			assertEquals(FixedPatternEnum.INTEGER.getValue(), elemento.getIntegerField());
			assertEquals(FixedPatternEnum.LONG.getValue(), elemento.getLongField());
			assertEquals(FixedPatternEnum.BIG_DECIMAL.getValue(), elemento.getBigDecimalField());
			assertEquals(FixedPatternEnum.BOOLEAN.getValue(), elemento.getBooleanField());
			assertEquals(FixedPatternEnum.CALENDAR.getValue(), elemento.getCalendarField());
			assertEquals(FixedPatternEnum.BYTE_ARRAY.getValue(), elemento.getByteField());
		}
	}

	@Test
	public void devePreencherUmaInstanciaQuandoOElementoDaColecaoEstiverParcialmentePreenchido() throws PopulatorException {
		String valor = "Teste";

		ClasseComAtributosFixos objeto = new ClasseComAtributosFixos();
		objeto.setStringField(valor);

		List<ClasseComAtributosFixos> colecao = new ArrayList<ClasseComAtributosFixos>();
		colecao.add(objeto);

		ClasseComColecaoDeClasseComAtributosFixos instance = new ClasseComColecaoDeClasseComAtributosFixos();
		instance.setColecao(colecao);

		this.filler.fill(instance);

		Collection<ClasseComAtributosFixos> elementos = instance.getColecao();
		assertEquals(1, elementos.size());

		for (ClasseComAtributosFixos elemento : elementos) {
			assertEquals(valor, elemento.getStringField());
			assertEquals(FixedPatternEnum.INTEGER.getValue(), elemento.getIntegerField());
			assertEquals(FixedPatternEnum.LONG.getValue(), elemento.getLongField());
			assertEquals(FixedPatternEnum.BIG_DECIMAL.getValue(), elemento.getBigDecimalField());
			assertEquals(FixedPatternEnum.BOOLEAN.getValue(), elemento.getBooleanField());
			assertEquals(FixedPatternEnum.CALENDAR.getValue(), elemento.getCalendarField());
			assertEquals(FixedPatternEnum.BYTE_ARRAY.getValue(), elemento.getByteField());
		}
	}

	@Test
	public void naoDevePreencherUmaInstanciaQuandoOElementoDaColecaoEstiverTotalmentePreenchido() throws PopulatorException {
		String stringValue = "Teste";
		Integer integerValue = 1000;
		Long longValue = 1000L;
		BigDecimal bigDecimalValue = BigDecimal.valueOf(longValue);
		Boolean booleanValue = Boolean.TRUE;
		Calendar calendarValue = Calendar.getInstance();
		calendarValue.add(Calendar.YEAR, 1);
		byte[] byteValue = new byte[integerValue];

		ClasseComAtributosFixos objeto = new ClasseComAtributosFixos();
		objeto.setStringField(stringValue);
		objeto.setIntegerField(integerValue);
		objeto.setLongField(longValue);
		objeto.setBigDecimalField(bigDecimalValue);
		objeto.setBooleanField(booleanValue);
		objeto.setCalendarField(calendarValue);
		objeto.setByteField(byteValue);

		List<ClasseComAtributosFixos> colecao = new ArrayList<ClasseComAtributosFixos>();
		colecao.add(objeto);

		ClasseComColecaoDeClasseComAtributosFixos instance = new ClasseComColecaoDeClasseComAtributosFixos();
		instance.setColecao(colecao);

		this.filler.fill(instance);

		Collection<ClasseComAtributosFixos> elementos = instance.getColecao();
		assertEquals(1, elementos.size());

		for (ClasseComAtributosFixos elemento : elementos) {
			assertEquals(stringValue, elemento.getStringField());
			assertEquals(integerValue, elemento.getIntegerField());
			assertEquals(longValue, elemento.getLongField());
			assertEquals(bigDecimalValue, elemento.getBigDecimalField());
			assertEquals(booleanValue, elemento.getBooleanField());
			assertEquals(calendarValue, elemento.getCalendarField());
			assertEquals(byteValue, elemento.getByteField());
		}
	}

	@Test
	public void devePreencherUmaInstanciaQuandoOAtributoForNulo() throws PopulatorException {
		ClasseComAtributo instance = new ClasseComAtributo();

		this.filler.fill(instance);

		ClasseComAtributosFixos objeto = instance.getClasseComAtributosFixos();
		assertNotNull(objeto);

		assertEquals(FixedPatternEnum.STRING.getValue(), objeto.getStringField());
		assertEquals(FixedPatternEnum.INTEGER.getValue(), objeto.getIntegerField());
		assertEquals(FixedPatternEnum.LONG.getValue(), objeto.getLongField());
		assertEquals(FixedPatternEnum.BIG_DECIMAL.getValue(), objeto.getBigDecimalField());
		assertEquals(FixedPatternEnum.BOOLEAN.getValue(), objeto.getBooleanField());
		assertEquals(FixedPatternEnum.CALENDAR.getValue(), objeto.getCalendarField());
		assertEquals(FixedPatternEnum.BYTE_ARRAY.getValue(), objeto.getByteField());
	}

	@Test
	public void devePreencherUmaInstanciaQuandoOAtributoNaoEstiverPreenchido() throws PopulatorException {
		ClasseComAtributosFixos objetoDaInstancia = new ClasseComAtributosFixos();

		ClasseComAtributo instance = new ClasseComAtributo();
		instance.setClasseComAtributosFixos(objetoDaInstancia);

		this.filler.fill(instance);

		ClasseComAtributosFixos objeto = instance.getClasseComAtributosFixos();
		assertEquals(objetoDaInstancia, objeto);

		assertEquals(FixedPatternEnum.STRING.getValue(), objeto.getStringField());
		assertEquals(FixedPatternEnum.INTEGER.getValue(), objeto.getIntegerField());
		assertEquals(FixedPatternEnum.LONG.getValue(), objeto.getLongField());
		assertEquals(FixedPatternEnum.BIG_DECIMAL.getValue(), objeto.getBigDecimalField());
		assertEquals(FixedPatternEnum.BOOLEAN.getValue(), objeto.getBooleanField());
		assertEquals(FixedPatternEnum.CALENDAR.getValue(), objeto.getCalendarField());
		assertEquals(FixedPatternEnum.BYTE_ARRAY.getValue(), objeto.getByteField());
	}

	@Test
	public void devePreencherUmaInstanciaQuandoOAtributoEstiverParcialmentePreenchido() throws PopulatorException {
		String valor = "Teste";

		ClasseComAtributosFixos objetoDaInstancia = new ClasseComAtributosFixos();
		objetoDaInstancia.setStringField(valor);

		ClasseComAtributo instance = new ClasseComAtributo();
		instance.setClasseComAtributosFixos(objetoDaInstancia);

		this.filler.fill(instance);

		ClasseComAtributosFixos objeto = instance.getClasseComAtributosFixos();
		assertEquals(objetoDaInstancia, objeto);

		assertEquals(valor, objeto.getStringField());
		assertEquals(FixedPatternEnum.INTEGER.getValue(), objeto.getIntegerField());
		assertEquals(FixedPatternEnum.LONG.getValue(), objeto.getLongField());
		assertEquals(FixedPatternEnum.BIG_DECIMAL.getValue(), objeto.getBigDecimalField());
		assertEquals(FixedPatternEnum.BOOLEAN.getValue(), objeto.getBooleanField());
		assertEquals(FixedPatternEnum.CALENDAR.getValue(), objeto.getCalendarField());
		assertEquals(FixedPatternEnum.BYTE_ARRAY.getValue(), objeto.getByteField());
	}

	@Test
	public void naoDevePreencherUmaInstanciaQuandoOAtributoEstiverTotalmentePreenchido() throws PopulatorException {
		String stringValue = "Teste";
		Integer integerValue = 1000;
		Long longValue = 1000L;
		BigDecimal bigDecimalValue = BigDecimal.valueOf(longValue);
		Boolean booleanValue = Boolean.TRUE;
		Calendar calendarValue = Calendar.getInstance();
		calendarValue.add(Calendar.YEAR, 1);
		byte[] byteValue = new byte[integerValue];

		ClasseComAtributosFixos objetoDaInstancia = new ClasseComAtributosFixos();
		objetoDaInstancia.setStringField(stringValue);
		objetoDaInstancia.setIntegerField(integerValue);
		objetoDaInstancia.setLongField(longValue);
		objetoDaInstancia.setBigDecimalField(bigDecimalValue);
		objetoDaInstancia.setBooleanField(booleanValue);
		objetoDaInstancia.setCalendarField(calendarValue);
		objetoDaInstancia.setByteField(byteValue);

		ClasseComAtributo instance = new ClasseComAtributo();
		instance.setClasseComAtributosFixos(objetoDaInstancia);

		this.filler.fill(instance);

		ClasseComAtributosFixos objeto = instance.getClasseComAtributosFixos();
		assertEquals(objetoDaInstancia, objeto);

		assertEquals(stringValue, objeto.getStringField());
		assertEquals(integerValue, objeto.getIntegerField());
		assertEquals(longValue, objeto.getLongField());
		assertEquals(bigDecimalValue, objeto.getBigDecimalField());
		assertEquals(booleanValue, objeto.getBooleanField());
		assertEquals(calendarValue, objeto.getCalendarField());
		assertEquals(byteValue, objeto.getByteField());
	}

	@Test
	public void devePreencherUmaInstanciaQuandoOAtributoEAColecaoDeMesmoTipoForemNulos() throws PopulatorException {
		ClasseComAtributoEColecaoDeMesmoTipo instance = new ClasseComAtributoEColecaoDeMesmoTipo();

		this.filler.fill(instance);

		assertNotNull(instance.getClasseComAtributo());

		Collection<ClasseComAtributo> elementos = instance.getColecao();
		assertEquals(1, elementos.size());
	}

	@Test
	public void devePreencherUmaInstanciaQuandoOAtributoForBidirecional() throws PopulatorException {
		ClasseComAtributoBidirecional instance = new ClasseComAtributoBidirecional();

		this.filler.fill(instance);

		assertEquals(instance, instance.getClasseComAtributoBidirecional().getClasseComAtributoBidirecional());
	}

	@Test
	public void devePreencherUmaInstanciaQuandoAColecaoDeAtributoBidirecionalForNula() throws PopulatorException {
		ClasseComColecaoDeAtributoBidirecional instance = new ClasseComColecaoDeAtributoBidirecional();

		this.filler.fill(instance);

		Collection<ClasseComAtributoDeClasseComColecaoDeAtributoBidirecional> elementos = instance.getColecao();
		assertFalse(elementos.isEmpty());
		assertEquals(1, elementos.size());

		for (ClasseComAtributoDeClasseComColecaoDeAtributoBidirecional elemento : elementos) {
			assertEquals(instance, elemento.getClasseComColecaoDeAtributoBidirecional());
		}
	}

	@Test
	public void devePreencherUmaInstanciaQuandoAColecaoDeAtributoBidirecionalForVazia() throws PopulatorException {
		ClasseComColecaoDeAtributoBidirecional instance = new ClasseComColecaoDeAtributoBidirecional();
		instance.setColecao(new ArrayList<ClasseComAtributoDeClasseComColecaoDeAtributoBidirecional>());

		this.filler.fill(instance);

		Collection<ClasseComAtributoDeClasseComColecaoDeAtributoBidirecional> elementos = instance.getColecao();
		assertFalse(elementos.isEmpty());
		assertEquals(1, elementos.size());

		for (ClasseComAtributoDeClasseComColecaoDeAtributoBidirecional elemento : elementos) {
			assertEquals(instance, elemento.getClasseComColecaoDeAtributoBidirecional());
		}
	}

	@Test
	public void devePreencherUmaInstanciaQuandoOElementoDaColecaoDeAtributoBidirecionalForNulo() throws PopulatorException {
		List<ClasseComAtributoDeClasseComColecaoDeAtributoBidirecional> colecao = new ArrayList<ClasseComAtributoDeClasseComColecaoDeAtributoBidirecional>();
		colecao.add(null);

		ClasseComColecaoDeAtributoBidirecional instance = new ClasseComColecaoDeAtributoBidirecional();
		instance.setColecao(colecao);

		this.filler.fill(instance);

		Collection<ClasseComAtributoDeClasseComColecaoDeAtributoBidirecional> elementos = instance.getColecao();
		assertEquals(1, elementos.size());

		for (ClasseComAtributoDeClasseComColecaoDeAtributoBidirecional elemento : elementos) {
			assertNotNull(elemento);
			assertEquals(instance, elemento.getClasseComColecaoDeAtributoBidirecional());
		}
	}

	@Test
	public void devePreencherUmaInstanciaQuandoOElementoDaColecaoDeAtributoBidirecionalNaoEstiverPreenchido() throws PopulatorException {
		ClasseComAtributoDeClasseComColecaoDeAtributoBidirecional objeto = new ClasseComAtributoDeClasseComColecaoDeAtributoBidirecional();

		List<ClasseComAtributoDeClasseComColecaoDeAtributoBidirecional> colecao = new ArrayList<ClasseComAtributoDeClasseComColecaoDeAtributoBidirecional>();
		colecao.add(objeto);

		ClasseComColecaoDeAtributoBidirecional instance = new ClasseComColecaoDeAtributoBidirecional();
		instance.setColecao(colecao);

		this.filler.fill(instance);

		Collection<ClasseComAtributoDeClasseComColecaoDeAtributoBidirecional> elementos = instance.getColecao();
		assertEquals(1, elementos.size());

		for (ClasseComAtributoDeClasseComColecaoDeAtributoBidirecional elemento : elementos) {
			assertEquals(objeto, elemento);
			assertEquals(instance, elemento.getClasseComColecaoDeAtributoBidirecional());
		}
	}

	@Test
	public void naoDevePreencherUmaInstanciaQuandoOElementoDaColecaoDeAtributoBidirecionalEstiverPreenchido() throws PopulatorException {
		ClasseComAtributoDeClasseComColecaoDeAtributoBidirecional objeto = new ClasseComAtributoDeClasseComColecaoDeAtributoBidirecional();

		List<ClasseComAtributoDeClasseComColecaoDeAtributoBidirecional> colecao = new ArrayList<ClasseComAtributoDeClasseComColecaoDeAtributoBidirecional>();
		colecao.add(objeto);

		ClasseComColecaoDeAtributoBidirecional instance = new ClasseComColecaoDeAtributoBidirecional();
		instance.setColecao(colecao);

		objeto.setClasseComColecaoDeAtributoBidirecional(instance);

		this.filler.fill(instance);

		Collection<ClasseComAtributoDeClasseComColecaoDeAtributoBidirecional> elementos = instance.getColecao();
		assertEquals(1, elementos.size());

		for (ClasseComAtributoDeClasseComColecaoDeAtributoBidirecional elemento : elementos) {
			assertEquals(objeto, elemento);
			assertEquals(instance, elemento.getClasseComColecaoDeAtributoBidirecional());
		}
	}

	@Test
	public void devePreencherUmaInstanciaQuandoAColecaoDeAtributoComColecaoDeAtributoBidirecionalForNula() throws PopulatorException {
		ClasseComColecaoDeAtributoComColecaoDeAtributoBidirecional instance = new ClasseComColecaoDeAtributoComColecaoDeAtributoBidirecional();

		this.filler.fill(instance);

		Collection<ClasseComColecaoDeClasseComColecaoDeAtributoComColecaoDeAtributoBidirecional> elementos = instance.getColecao();
		assertEquals(1, elementos.size());

		for (ClasseComColecaoDeClasseComColecaoDeAtributoComColecaoDeAtributoBidirecional elemento : elementos) {
			Collection<ClasseComColecaoDeAtributoComColecaoDeAtributoBidirecional> itens = elemento.getColecao();
			assertEquals(1, itens.size());

			for (ClasseComColecaoDeAtributoComColecaoDeAtributoBidirecional item : itens) {
				assertEquals(instance, item);
			}
		}
	}

	@Test
	public void devePreencherUmaInstanciaQuandoAColecaoDeAtributoComColecaoDeAtributoBidirecionalForVazia() throws PopulatorException {
		List<ClasseComColecaoDeClasseComColecaoDeAtributoComColecaoDeAtributoBidirecional> colecao = new ArrayList<ClasseComColecaoDeClasseComColecaoDeAtributoComColecaoDeAtributoBidirecional>();

		ClasseComColecaoDeAtributoComColecaoDeAtributoBidirecional instance = new ClasseComColecaoDeAtributoComColecaoDeAtributoBidirecional();
		instance.setColecao(colecao);

		this.filler.fill(instance);

		Collection<ClasseComColecaoDeClasseComColecaoDeAtributoComColecaoDeAtributoBidirecional> elementos = instance.getColecao();
		assertEquals(1, elementos.size());

		for (ClasseComColecaoDeClasseComColecaoDeAtributoComColecaoDeAtributoBidirecional elemento : elementos) {
			Collection<ClasseComColecaoDeAtributoComColecaoDeAtributoBidirecional> itens = elemento.getColecao();
			assertEquals(1, itens.size());

			for (ClasseComColecaoDeAtributoComColecaoDeAtributoBidirecional item : itens) {
				assertEquals(instance, item);
			}
		}
	}

	@Test
	public void devePreencherUmaInstanciaQuandoOElementoDaColecaoDeAtributoComColecaoDeAtributoBidirecionalForNulo() throws PopulatorException {
		List<ClasseComColecaoDeClasseComColecaoDeAtributoComColecaoDeAtributoBidirecional> colecao = new ArrayList<ClasseComColecaoDeClasseComColecaoDeAtributoComColecaoDeAtributoBidirecional>();
		colecao.add(null);

		ClasseComColecaoDeAtributoComColecaoDeAtributoBidirecional instance = new ClasseComColecaoDeAtributoComColecaoDeAtributoBidirecional();
		instance.setColecao(colecao);

		this.filler.fill(instance);

		Collection<ClasseComColecaoDeClasseComColecaoDeAtributoComColecaoDeAtributoBidirecional> elementos = instance.getColecao();
		assertEquals(1, elementos.size());

		for (ClasseComColecaoDeClasseComColecaoDeAtributoComColecaoDeAtributoBidirecional elemento : elementos) {
			Collection<ClasseComColecaoDeAtributoComColecaoDeAtributoBidirecional> itens = elemento.getColecao();
			assertEquals(1, itens.size());

			for (ClasseComColecaoDeAtributoComColecaoDeAtributoBidirecional item : itens) {
				assertEquals(instance, item);
			}
		}
	}

	@Test
	public void devePreencherUmaInstanciaQuandoOElementoDaColecaoDeAtributoComColecaoDeAtributoBidirecionalNaoEstiverPreenchido() throws PopulatorException {
		ClasseComColecaoDeClasseComColecaoDeAtributoComColecaoDeAtributoBidirecional objeto = new ClasseComColecaoDeClasseComColecaoDeAtributoComColecaoDeAtributoBidirecional();

		List<ClasseComColecaoDeClasseComColecaoDeAtributoComColecaoDeAtributoBidirecional> colecao = new ArrayList<ClasseComColecaoDeClasseComColecaoDeAtributoComColecaoDeAtributoBidirecional>();
		colecao.add(objeto);

		ClasseComColecaoDeAtributoComColecaoDeAtributoBidirecional instance = new ClasseComColecaoDeAtributoComColecaoDeAtributoBidirecional();
		instance.setColecao(colecao);

		this.filler.fill(instance);

		Collection<ClasseComColecaoDeClasseComColecaoDeAtributoComColecaoDeAtributoBidirecional> elementos = instance.getColecao();
		for (ClasseComColecaoDeClasseComColecaoDeAtributoComColecaoDeAtributoBidirecional elemento : elementos) {
			assertEquals(objeto, elemento);

			Collection<ClasseComColecaoDeAtributoComColecaoDeAtributoBidirecional> itens = elemento.getColecao();
			assertEquals(1, itens.size());

			for (ClasseComColecaoDeAtributoComColecaoDeAtributoBidirecional item : itens) {
				assertEquals(instance, item);
			}
		}
	}

	@Test
	public void devePreencherUmaInstanciaQuandoAColecaoDoElementoDaColecaoDeAtributoComColecaoDeAtributoBidirecionalForVazia() throws PopulatorException {
		List<ClasseComColecaoDeAtributoComColecaoDeAtributoBidirecional> colecaoDoObjeto = new ArrayList<ClasseComColecaoDeAtributoComColecaoDeAtributoBidirecional>();

		ClasseComColecaoDeClasseComColecaoDeAtributoComColecaoDeAtributoBidirecional objeto = new ClasseComColecaoDeClasseComColecaoDeAtributoComColecaoDeAtributoBidirecional();
		objeto.setColecao(colecaoDoObjeto);

		List<ClasseComColecaoDeClasseComColecaoDeAtributoComColecaoDeAtributoBidirecional> colecao = new ArrayList<ClasseComColecaoDeClasseComColecaoDeAtributoComColecaoDeAtributoBidirecional>();
		colecao.add(objeto);

		ClasseComColecaoDeAtributoComColecaoDeAtributoBidirecional instance = new ClasseComColecaoDeAtributoComColecaoDeAtributoBidirecional();
		instance.setColecao(colecao);

		this.filler.fill(instance);

		Collection<ClasseComColecaoDeClasseComColecaoDeAtributoComColecaoDeAtributoBidirecional> elementos = instance.getColecao();
		for (ClasseComColecaoDeClasseComColecaoDeAtributoComColecaoDeAtributoBidirecional elemento : elementos) {
			assertEquals(objeto, elemento);

			Collection<ClasseComColecaoDeAtributoComColecaoDeAtributoBidirecional> itens = elemento.getColecao();
			assertEquals(1, itens.size());

			for (ClasseComColecaoDeAtributoComColecaoDeAtributoBidirecional item : itens) {
				assertEquals(instance, item);
			}
		}
	}

	@Test
	public void devePreencherUmaInstanciaQuandoOElementoDaColecaoDoElementoDaColecaoDeAtributoComColecaoDeAtributoBidirecionalForNulo() throws PopulatorException {
		List<ClasseComColecaoDeAtributoComColecaoDeAtributoBidirecional> colecaoDoObjeto = new ArrayList<ClasseComColecaoDeAtributoComColecaoDeAtributoBidirecional>();
		colecaoDoObjeto.add(null);

		ClasseComColecaoDeClasseComColecaoDeAtributoComColecaoDeAtributoBidirecional objeto = new ClasseComColecaoDeClasseComColecaoDeAtributoComColecaoDeAtributoBidirecional();
		objeto.setColecao(colecaoDoObjeto);

		List<ClasseComColecaoDeClasseComColecaoDeAtributoComColecaoDeAtributoBidirecional> colecao = new ArrayList<ClasseComColecaoDeClasseComColecaoDeAtributoComColecaoDeAtributoBidirecional>();
		colecao.add(objeto);

		ClasseComColecaoDeAtributoComColecaoDeAtributoBidirecional instance = new ClasseComColecaoDeAtributoComColecaoDeAtributoBidirecional();
		instance.setColecao(colecao);

		this.filler.fill(instance);

		Collection<ClasseComColecaoDeClasseComColecaoDeAtributoComColecaoDeAtributoBidirecional> elementos = instance.getColecao();
		for (ClasseComColecaoDeClasseComColecaoDeAtributoComColecaoDeAtributoBidirecional elemento : elementos) {
			assertEquals(objeto, elemento);

			Collection<ClasseComColecaoDeAtributoComColecaoDeAtributoBidirecional> itens = elemento.getColecao();
			assertEquals(1, itens.size());

			for (ClasseComColecaoDeAtributoComColecaoDeAtributoBidirecional item : itens) {
				assertEquals(instance, item);
			}
		}
	}

	@Test
	public void naoDevePreencherUmaInstanciaQuandoOElementoDaColecaoDoElementoDaColecaoDeAtributoComColecaoDeAtributoBidirecionalEstiverPreenchido() throws PopulatorException {
		List<ClasseComColecaoDeAtributoComColecaoDeAtributoBidirecional> colecaoDoObjeto = new ArrayList<ClasseComColecaoDeAtributoComColecaoDeAtributoBidirecional>();

		ClasseComColecaoDeClasseComColecaoDeAtributoComColecaoDeAtributoBidirecional objeto = new ClasseComColecaoDeClasseComColecaoDeAtributoComColecaoDeAtributoBidirecional();
		objeto.setColecao(colecaoDoObjeto);

		List<ClasseComColecaoDeClasseComColecaoDeAtributoComColecaoDeAtributoBidirecional> colecao = new ArrayList<ClasseComColecaoDeClasseComColecaoDeAtributoComColecaoDeAtributoBidirecional>();
		colecao.add(objeto);

		ClasseComColecaoDeAtributoComColecaoDeAtributoBidirecional instance = new ClasseComColecaoDeAtributoComColecaoDeAtributoBidirecional();
		instance.setColecao(colecao);

		colecaoDoObjeto.add(instance);

		this.filler.fill(instance);

		Collection<ClasseComColecaoDeClasseComColecaoDeAtributoComColecaoDeAtributoBidirecional> elementos = instance.getColecao();
		for (ClasseComColecaoDeClasseComColecaoDeAtributoComColecaoDeAtributoBidirecional elemento : elementos) {
			assertEquals(objeto, elemento);

			Collection<ClasseComColecaoDeAtributoComColecaoDeAtributoBidirecional> itens = elemento.getColecao();
			assertEquals(1, itens.size());

			for (ClasseComColecaoDeAtributoComColecaoDeAtributoBidirecional item : itens) {
				assertEquals(instance, item);
			}
		}
	}

	@Test
	public void devePreencherUmaInstanciaComAtributosDeTiposDiferentes() throws PopulatorException {
		ClasseComAtributosDeTiposDiferentes instance = new ClasseComAtributosDeTiposDiferentes();

		this.filler.fill(instance);

		assertNull(instance.getId());
		assertNull(instance.getVersion());
		assertEquals(FixedPatternEnum.STRING.getValue(), instance.getAtributo());
		assertEquals(EnumNaoVazia.CONSTANTE_01, instance.getEnumNaoVazia());

		ClasseComAtributosFixos atributosFixos = instance.getClasseComAtributosFixos();
		assertNotNull(atributosFixos);
		assertEquals(FixedPatternEnum.STRING.getValue(), atributosFixos.getStringField());
		assertEquals(FixedPatternEnum.INTEGER.getValue(), atributosFixos.getIntegerField());
		assertEquals(FixedPatternEnum.LONG.getValue(), atributosFixos.getLongField());
		assertEquals(FixedPatternEnum.BIG_DECIMAL.getValue(), atributosFixos.getBigDecimalField());
		assertEquals(FixedPatternEnum.BOOLEAN.getValue(), atributosFixos.getBooleanField());
		assertEquals(FixedPatternEnum.CALENDAR.getValue(), atributosFixos.getCalendarField());
		assertEquals(FixedPatternEnum.BYTE_ARRAY.getValue(), atributosFixos.getByteField());

		assertNotNull(instance.getClasseComAtributo());

		Collection<String> elementos = instance.getColecaoDeStrings();
		assertEquals(1, elementos.size());

		for (String elemento : elementos) {
			assertEquals(FixedPatternEnum.STRING.getValue(), elemento);
		}

		Collection<ClasseComAtributo> itens = instance.getColecao();
		assertEquals(1, itens.size());

		for (ClasseComAtributo item : itens) {
			assertEquals(instance.getClasseComAtributo(), item);
		}
	}

	@After
	public void tearDown() {
		PatternManager.getInstance().restore();
	}

}
