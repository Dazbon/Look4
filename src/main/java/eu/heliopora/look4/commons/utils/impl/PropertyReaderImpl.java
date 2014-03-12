package eu.heliopora.look4.commons.utils.impl;

import java.util.Properties;

import org.apache.commons.lang3.Validate;
import org.apache.log4j.Logger;

import eu.heliopora.look4.commons.utils.PropertyReader;

public class PropertyReaderImpl implements PropertyReader {
	
	static final Logger log = Logger.getLogger(PropertyReaderImpl.class);
	
	PropertyReaderImpl propertyReaderImpl() {
		return new PropertyReaderImpl();
	}
		
	public void readStringProperty(String property, Properties props, String propertyName, String defaultValue) {
		this.readProperty(String.class, property, props, propertyName, defaultValue);
	}
	
	public void readBooleanProperty(Boolean property, Properties props, String propertyName, Boolean defaultValue) {
		this.readProperty(Boolean.class, property, props, propertyName, defaultValue);
	}
	
	public void readIntegerProperty(Integer property, Properties props, String propertyName, Integer defaultValue) {
		this.readProperty(Integer.class, property, props, propertyName, defaultValue);
	}

	@SuppressWarnings("unchecked")
	private <T> void readProperty(Class<T> typeClass, T property, Properties props, String propertyName, T defaultValue) {
		Validate.notNull(props);
		Validate.notNull(propertyName);
		Validate.notNull(defaultValue);
				
		String stringPropertyValue = props.getProperty(propertyName);
        if (stringPropertyValue == null) {
        	if (log.isInfoEnabled()) {
        		log.info("Property " + propertyName + " not found - setting default value " + defaultValue.toString());
        	}
        	property = defaultValue;
        	return;
        }
        try {
        	if (typeClass.equals(String.class)) {
        		property = (T)stringPropertyValue;
        	} else if (typeClass.equals(Boolean.class)) {
        		property = (T)Boolean.valueOf(stringPropertyValue);
        	} else if (typeClass.equals(Integer.class))  {
        		property = (T)Integer.valueOf(stringPropertyValue);
        	}
        	if (log.isInfoEnabled()) {
        		log.info("Property " + propertyName + " read - value " + stringPropertyValue);
        	}
        } catch (Exception e) {
        	log.error("Error reading property " + propertyName + " - setting default value " + defaultValue.toString());
        	property = defaultValue;
        }    
	}

}
