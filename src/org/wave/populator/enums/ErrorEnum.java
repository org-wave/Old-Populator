package org.wave.populator.enums;

import java.text.MessageFormat;
import java.util.Locale;
import java.util.ResourceBundle;

public enum ErrorEnum {

	NULL("error.message.null"),
	NOT_SERIALIZABLE("error.message.notSerializable"),
	NOT_PERSISTENT_FIELDS("error.message.notPersistentFields"),
	UNEXPECTED_TYPE("error.message.unexpectedType"),
	EMPTY_ENUM("error.message.emptyEnum");

	private String key;

	private ErrorEnum(String key) {
		this.key = key;
	}
	
	public String getMessage(Object... params) {
		ResourceBundle bundle = ResourceBundle.getBundle("org.wave.populator.messages.messages", Locale.getDefault());

		String value = bundle.getString(this.key);
		
		return new MessageFormat(value).format(params);
	}

}
