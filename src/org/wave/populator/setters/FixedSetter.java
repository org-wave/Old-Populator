package org.wave.populator.setters;

import java.lang.reflect.Field;
import java.util.List;

import org.wave.populator.core.Filler;
import org.wave.populator.enums.FieldEnum;
import org.wave.populator.exceptions.PopulatorException;
import org.wave.utils.reflection.ReflectionUtil;


public class FixedSetter extends Setter {

	public FixedSetter(Filler filler) {
		super(filler);
	}

	@Override
	public <T> void set(T instance) throws PopulatorException {
		List<Field> fields = ReflectionUtil.getPersistentFields(instance.getClass());
		for (Field field : fields) {
			Class<?> klass = field.getType();
			boolean isPattern = this.getManager().hasPattern(klass);
			boolean isNull = ReflectionUtil.get(field, instance) == null;

			if (isPattern && isNull && isFillable(field)) {
				ReflectionUtil.set(this.getManager().getValue(klass), field, instance);
			}
		}
	}

	private boolean isFillable(Field field) {
		String fieldName = field.getName();
		if (fieldName.equals(FieldEnum.ID.getValue()) || fieldName.equals(FieldEnum.VERSION.getValue())) {
			return false;
		}

		return true;
	}

}
