package org.wave.populator.setters;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;

import java.math.BigDecimal;
import java.util.Calendar;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.wave.populator.core.Filler;
import org.wave.populator.core.examples.ClasseComAtributosFixos;
import org.wave.populator.core.examples.ClasseComIdEVersion;
import org.wave.populator.enums.FixedPatternEnum;
import org.wave.populator.exceptions.PopulatorException;
import org.wave.populator.setters.FixedSetter;
import org.wave.populator.setters.Setter;


public class FixedSetterTest {

	private Setter successor;

	private Setter setter;

	@Before
	public void setUp() {
		this.successor = mock(Setter.class);

		Filler filler = mock(Filler.class);
		this.setter = new FixedSetter(filler);
		this.setter.setSuccessor(this.successor);
	}

	@Test
	public void devePreencherUmaInstanciaNaoPreenchida() throws PopulatorException {
		ClasseComAtributosFixos instance = new ClasseComAtributosFixos();

		doNothing().when(this.successor).set(instance);
		this.setter.set(instance);

		assertEquals(FixedPatternEnum.STRING.getValue(), instance.getStringField());
		assertEquals(FixedPatternEnum.INTEGER.getValue(), instance.getIntegerField());
		assertEquals(FixedPatternEnum.LONG.getValue(), instance.getLongField());
		assertEquals(FixedPatternEnum.BIG_DECIMAL.getValue(), instance.getBigDecimalField());
		assertEquals(FixedPatternEnum.BOOLEAN.getValue(), instance.getBooleanField());
		assertEquals(FixedPatternEnum.CALENDAR.getValue(), instance.getCalendarField());
		assertEquals(FixedPatternEnum.BYTE_ARRAY.getValue(), instance.getByteField());
	}

	@Test
	public void devePreencherUmaInstanciaParcialmentePreenchida() throws PopulatorException {
		String valor = "Teste";

		ClasseComAtributosFixos instance = new ClasseComAtributosFixos();
		instance.setStringField(valor);

		doNothing().when(this.successor).set(instance);
		this.setter.set(instance);

		assertEquals(valor, instance.getStringField());
		assertEquals(FixedPatternEnum.INTEGER.getValue(), instance.getIntegerField());
		assertEquals(FixedPatternEnum.LONG.getValue(), instance.getLongField());
		assertEquals(FixedPatternEnum.BIG_DECIMAL.getValue(), instance.getBigDecimalField());
		assertEquals(FixedPatternEnum.BOOLEAN.getValue(), instance.getBooleanField());
		assertEquals(FixedPatternEnum.CALENDAR.getValue(), instance.getCalendarField());
		assertEquals(FixedPatternEnum.BYTE_ARRAY.getValue(), instance.getByteField());
	}

	@Test
	public void naoDevePreencherUmaInstanciaTotalmentePreenchida() throws PopulatorException {
		String stringValue = "Teste";
		Integer integerValue = 1000;
		Long longValue = 1000L;
		BigDecimal bigDecimalValue = BigDecimal.valueOf(longValue);
		Boolean booleanValue = Boolean.TRUE;
		Calendar calendarValue = Calendar.getInstance();
		calendarValue.add(Calendar.YEAR, 1);
		byte[] byteValue = new byte[integerValue];

		ClasseComAtributosFixos instance = new ClasseComAtributosFixos();
		instance.setStringField(stringValue);
		instance.setIntegerField(integerValue);
		instance.setLongField(longValue);
		instance.setBigDecimalField(bigDecimalValue);
		instance.setBooleanField(booleanValue);
		instance.setCalendarField(calendarValue);
		instance.setByteField(byteValue);

		doNothing().when(this.successor).set(instance);
		this.setter.set(instance);

		assertEquals(stringValue, instance.getStringField());
		assertEquals(integerValue, instance.getIntegerField());
		assertEquals(longValue, instance.getLongField());
		assertEquals(bigDecimalValue, instance.getBigDecimalField());
		assertEquals(booleanValue, instance.getBooleanField());
		assertEquals(calendarValue, instance.getCalendarField());
		assertEquals(byteValue, instance.getByteField());
	}

	@Test
	public void naoDevePreencherAtributosIdEVersion() throws PopulatorException {
		ClasseComIdEVersion instance = new ClasseComIdEVersion();

		doNothing().when(this.successor).set(instance);
		this.setter.set(instance);

		assertNull(instance.getId());
		assertNull(instance.getVersion());
		assertEquals(FixedPatternEnum.STRING.getValue(), instance.getAtributo());
	}

	@After
	public void tearDown() {
		this.setter = null;
		this.successor = null;
	}

}
