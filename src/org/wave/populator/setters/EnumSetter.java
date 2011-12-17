package org.wave.populator.setters;

import java.lang.reflect.Field;
import java.util.List;

import org.wave.populator.core.Filler;
import org.wave.populator.enums.ErrorEnum;
import org.wave.populator.exceptions.PopulatorException;
import org.wave.utils.reflection.ReflectionUtil;


public class EnumSetter extends Setter {

	public EnumSetter(Filler filler) {
		super(filler);
	}

	@Override
	public <T> void set(T instance) throws PopulatorException {
		List<Field> fields = ReflectionUtil.getPersistentFields(instance.getClass());
		for (Field field : fields) {
			Class<?> klass = field.getType();
			boolean isEnum = klass.isEnum();
			boolean isNull = ReflectionUtil.get(field, instance) == null;

			if (isEnum && isNull) {
				Object[] constants = klass.getEnumConstants();

				if (constants.length == 0) {
					throw new PopulatorException(ErrorEnum.EMPTY_ENUM, klass.getName());
				}

				ReflectionUtil.set(constants[0], field, instance);
			}
		}

		this.getSuccessor().set(instance);
	}

}
