package org.wave.populator.core;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.wave.populator.enums.FieldEnum;
import org.wave.populator.exceptions.PopulatorException;
import org.wave.repository.annotations.Transactional;
import org.wave.repository.core.Keeper;
import org.wave.repository.enums.RemoveEnum;
import org.wave.repository.exceptions.RepositoryException;
import org.wave.utils.reflection.ReflectionUtil;


public class Docker {

	private PatternManager manager;

	@Inject
	private Keeper keeper;

	private List<Object> instances;

	public Docker() {
		this.manager = PatternManager.getInstance();
		this.instances = new ArrayList<Object>();
	}

	@Transactional
	public void persistInstances() throws PopulatorException {
		for (Object instance : this.manager.getValues()) {
			try {
				Field field = ReflectionUtil.getField(FieldEnum.ID.getValue(), instance.getClass());
				Object value = ReflectionUtil.get(field, instance);
				if (value == null) {
					this.keeper.persist(instance);
				}
				this.instances.add(instance);
			} catch (RepositoryException e) {
				throw new PopulatorException(e.getMessage());
			}
		}
	}

	@Transactional
	public void removeInstances() throws PopulatorException {
		for (Object instance : this.instances) {
			try {
				this.keeper.remove(instance, RemoveEnum.PHYSICAL);
			} catch (RepositoryException e) {
				throw new PopulatorException(e.getMessage());
			}
		}

		this.instances = new ArrayList<Object>();
	}

}
