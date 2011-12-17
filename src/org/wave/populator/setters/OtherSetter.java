package org.wave.populator.setters;

import java.lang.reflect.Field;
import java.util.List;

import org.wave.populator.core.Filler;
import org.wave.populator.enums.ErrorEnum;
import org.wave.populator.exceptions.PopulatorException;
import org.wave.utils.reflection.ReflectionUtil;


public class OtherSetter extends Setter {

	public OtherSetter(Filler filler) {
		super(filler);
	}

	@Override
	public <T> void set(T instance) throws PopulatorException {
		List<Field> fields = ReflectionUtil.getPersistentFields(instance.getClass());
		for (Field field : fields) {
			Class<?> klass = field.getType();
			boolean isNotEnum = !klass.isEnum();
			boolean isNotCollection = !ReflectionUtil.isCollection(klass);
			boolean isNotPattern = !this.getManager().hasPattern(klass);

			if (isNotEnum && isNotCollection && isNotPattern) {
				Object value = ReflectionUtil.get(field, instance);
				if (value == null) {
					try {
						value = klass.newInstance();
						ReflectionUtil.set(value, field, instance);
					} catch (InstantiationException e) {
						throw new PopulatorException(ErrorEnum.UNEXPECTED_TYPE, field.getName(), instance.getClass().getName());
					} catch (IllegalAccessException e) {
						throw new PopulatorException(e.getMessage());
					}
				}

				this.getFiller().fill(value);
			}
		}

		this.getSuccessor().set(instance);
	}

}
